package ui;

import model.Book;
import model.Library;
import model.WishList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gui {
    private static final String JSON_STORE = "./data/bookList.json";
    private java.util.List<Library> libraries;
    private WishList wishlist;
    private Book book1;
    private Book book2;
    private Book book3;
    private java.util.List<Book> library1;
    private List<Book> library2;
    private Library ubc;
    private Library home;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel panel;
    private JButton addbutton;
    private JButton bookbutton;
    private JButton savebutton;
    private JButton loadbutton;
    private JLabel label;
    private JTextArea editTextArea;
    private JTextArea titleTextArea;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public Gui() {
        create();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.label = new JLabel(wishlist.getBooks());

        this.panel = new JPanel();

        this.frame = new JFrame();

        this.addbutton = new JButton("Add Book");
        addbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBookToGui();
                label.setText(wishlist.getBooks());

            }
        });

        this.savebutton = new JButton("Save WishList");
        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWishList();
            }
        });

        this.loadbutton = new JButton("Load WishList");
        loadbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWishList();
                label.setText(wishlist.getBooks());
            }
        });


        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setPreferredSize(new Dimension(1000, 500));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label, BorderLayout.CENTER);
        panel.add(addbutton, BorderLayout.SOUTH);
        panel.add(savebutton);
        panel.add(loadbutton);



        newFrame();




        this.editTextArea = new JTextArea("What is The title of Your Book? ");
        this.bookbutton = new JButton("Send Title");
        editTextArea.setEditable(false);
        this.titleTextArea = new JTextArea("");

    }



    public void addBookToGui() {
        panel.add(editTextArea);
        panel.add(titleTextArea);
        panel.add(bookbutton);
        newFrame();


        bookbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextArea.getText();
                Book found = new Book(null, null);
                Book isfound = bookInLibrary(title, found);
                if (isfound.getName() != null) {
                    addBook(isfound);
                } else {
                    System.out.println("Cannot find book in Library");
                }
                titleTextArea.setText("");
                label.setText(wishlist.getBooks());
                panel.remove(editTextArea);
                panel.remove(titleTextArea);
                panel.remove(bookbutton);
            }
            });


    }

    //MODIFIES: this
    //EFFECTS: creates 2 libraries and 3 books, adds books to libraries.
    //          creates empty WishList.
    private void create() {
        this.library1 = new ArrayList<>();
        this.library2 = new ArrayList<>();
        this.book1 = new Book("Politics", "Aristotle");
        this.book2 = new Book("Heroes", "Stephen Fry");
        this.book3 = new Book("The Stars are also Fire", "Paul Anderson");
        this.library1.add(book1);
        this.library1.add(book2);
        this.library1.add(book3);
        this.library2.add(book1);
        this.library2.add(book2);
        this.ubc = new Library("ubc", this.library2);
        this.home = new Library("home", this.library1);
        this.libraries = new ArrayList<>();
        this.libraries.add(this.ubc);
        this.libraries.add(this.home);
        this.wishlist = new WishList("wishlist");
    }

    //REQUIRES: name is of non-zero length, found has name null
    //MODIFIES: this
    //EFFECTS: checks for book in library of name name, if there is, sets found to book.
    private Book bookInLibrary(String name, Book found) {
        for (Library l : libraries) {
            Book looking = l.inLibrary(name);
            if (null != looking.getName()) {
                found = looking;
            }
        }
        return found;
    }

    //REQUIRES: Book has reading status null
    //MODIFIES: this
    //EFFECTS: adds Book to WishList and changes Reading status to WANTTOREAD
    private void addBook(Book book) {
        book.changeReading();
        wishlist.addBook(book);
        System.out.println("Added " + book.getName() + " by " + book.getAuthor() + " to your WishList");
    }

    //EFFECTS: saves wishList to file
    public void saveWishList() {
        try {
            jsonWriter.open();
            jsonWriter.write(wishlist);
            jsonWriter.close();
            System.out.println("Saved BookList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    //MODIFIES: this
    //EFFECTS: loads wishlist from file.
    private void loadWishList() {
        try {
            wishlist = jsonReader.read();
            System.out.println("Loaded BookList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    private void newFrame() {

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WishList");
        frame.pack();
        frame.setVisible(true);
    }


}
