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
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel panel;
    private JButton addbutton;
    private JButton bookbutton;
    private JButton savebutton;
    private JButton loadbutton;
    private JButton librarybutton;
    private JLabel label;
    private JTextArea editTextArea;
    private JTextArea titleTextArea;
    private ImageIcon cover;
    private JLabel imageAsLabel;
    private List<JLabel> alllibraries;
    private JButton startbutton;
    private JLabel start;
    private JButton addlib;
    private JTextArea eextArea;
    private JTextArea textArea;
    private JTextArea aextArea;
    private JButton sendbook;
    private JTextArea lextArea;

    public Gui() {
        create();
        initialize();

        this.start = new JLabel(getImageIcon("wishlist.png"));

        this.startbutton = new JButton("Start Wishlist:");
        startbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        this.panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setPreferredSize(new Dimension(1000, 1000));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(start);
        panel.add(startbutton);

        initializeFrame();
    }

    //MODIFIES: this
    //EFFECTS: creates next screen
    private void next() {
        panel.remove(start);
        panel.remove(startbutton);

        addButton();

        addlibButton();

        saveButton();

        loadButton();

        librariesButton();


        this.cover = new ImageIcon();
        this.imageAsLabel = new JLabel(cover);


        initializePanel();
        initializeFrame();






        this.editTextArea = new JTextArea("What is The title of Your Book? ");
        this.bookbutton = new JButton("Send Title");
        editTextArea.setEditable(false);
        this.titleTextArea = new JTextArea("");
        this.alllibraries = new ArrayList<>();

        this.eextArea = new JTextArea("What is the Title and Author of Your Book? What Library will it go to? ");
        this.sendbook = new JButton("Send Book");
        eextArea.setEditable(false);
        this.textArea = new JTextArea("");
        this.aextArea = new JTextArea("");
        this.lextArea = new JTextArea("");
    }

    //MODIFIES: this
    //EFFECTS: creates panel and adds Buttons to panel;
    private void initializePanel() {


        panel.add(label);


        panel.add(addbutton);

        panel.add(addlib);


        panel.add(savebutton);


        panel.add(loadbutton);


        panel.add(imageAsLabel);


        panel.add(librarybutton);



    }

    //MODIFIES: this
    //EFFECTS: adds Panel to frame and initializes it
    private void initializeFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WishList");
        frame.pack();
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates loadWishList button
    private void loadButton() {
        this.loadbutton = new JButton("Load WishList");
        loadbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadWishList();
                label.setText(wishlist.getBooks());
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: create saveWishList Button
    private void saveButton() {
        this.savebutton = new JButton("Save WishList");
        savebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveWishList();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Creates addlib button
    private void addlibButton() {
        this.addlib = new JButton("Add Book To Library");
        addbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBookToLib();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Creates addBook button
    private void addButton() {
        this.addbutton = new JButton("Add Book To Wishlist");
        addbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBookToGui();
                label.setText(wishlist.getBooks());
            }
        });
    }



    //MODIFIES: this
    //EFFECTS: creates viewLibraries Button
    private void librariesButton() {
        this.librarybutton = new JButton("View all Libraries");
        librarybutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewLibraries();
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: intializes jsonWriter/Reader, label, panel, and fram
    private void initialize() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.label = new JLabel(wishlist.getBooks());

        this.panel = new JPanel();

        this.frame = new JFrame();
    }



    private void viewLibraries() {
        for (JLabel jl : alllibraries) {
            panel.remove(jl);
        }
        initializeFrame();
        alllibraries = new ArrayList<>();
        JLabel jlabel = new JLabel("Libraries: ");
        alllibraries.add(jlabel);
        panel.add(jlabel);

        for (Library l : libraries) {
            JLabel j = new JLabel(l.getName() + "; " + l.getAllBooks());
            alllibraries.add(j);
            panel.add(j);
        }
        //alllibraries.setText(s);
        initializeFrame();
    }




    public void addBookToGui() {

        panel.add(editTextArea);

        panel.add(titleTextArea);

        panel.add(bookbutton);
        initializeFrame();


        bookButton();
    }

    public void addBookToLib() {

        panel.add(eextArea);

        panel.add(textArea);

        panel.add(aextArea);

        panel.add(lextArea);

        panel.add(sendbook);

        initializeFrame();


        libButton();
    }

    //MODIFIES: this
    //EFFECTS: creates New lib button
    private void libButton() {
        sendbook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book b = new Book(eextArea.getText(), aextArea.getText(), null);
                eextArea.setText("");
                aextArea.setText("");

                addbooktothislib(lextArea.getText(), b);

                panel.remove(eextArea);
                panel.remove(textArea);
                panel.remove(lextArea);
                panel.remove(aextArea);
                panel.remove(sendbook);





                initializeFrame();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds book to lib given, does nothing if no library
    private void addbooktothislib(String lname, Book b) {
        for (Library l : libraries) {
            if (l.getName() == lname) {
                l.addBook(b);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates New Book button
    private void bookButton() {
        bookbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JLabel img = findBook();
                titleTextArea.setText("");
                label.setText(wishlist.getBooks());
                panel.remove(editTextArea);
                panel.remove(titleTextArea);
                panel.remove(bookbutton);
                panel.remove(imageAsLabel);

                imageAsLabel = img;
                //initializePanel();


                initializeFrame();
            }
            });
    }

    //MODIFIES: this
    //EFFECTS: goes through books in Library and adds to wishlist if found
    private JLabel findBook() {
        String title = titleTextArea.getText();
        Book found = new Book(null, null, null);
        Book isfound = bookInLibrary(title, found);
        if (isfound.getName() != null) {
            addBook(isfound);
            return new JLabel(isfound.getCover());

        } else {
            System.out.println("Cannot find book in Library");
            return new JLabel();
        }
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

    //EFFECTS: returns ImageIcon of png
    private ImageIcon getImageIcon(String png) {
        String sep = System.getProperty("file.separator");
        ImageIcon img = new ImageIcon(System.getProperty("user.dir")
                + sep + "images" + sep + png);
        return img;
    }

    //MODIFIES: this
    //EFFECTS: creates 2 libraries and 3 books, adds books to libraries.
    //          creates empty WishList.
    private void create() {
        String sep = System.getProperty("file.separator");

        this.library1 = new ArrayList<>();
        this.library2 = new ArrayList<>();
        this.book1 = new Book("Politics", "Aristotle", getImageIcon("Politics.png"));
        this.book2 = new Book("Heroes", "Stephen Fry", getImageIcon("Heroes.png"));
        this.book3 = new Book("The Stars are also Fire", "Paul Anderson", getImageIcon("Stars.png"));
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


}
