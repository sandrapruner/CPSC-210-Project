package ui;

import model.Book;
import model.Library;
import model.WishList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// WishList application
public class CreateWishList {
    private static final String JSON_STORE = "./data/bookList.json";
    private List<Library> libraries;
    private WishList wishlist;
    private Book book1;
    private Book book2;
    private Book book3;
    private List<Book> library1;
    private List<Book> library2;
    private Library ubc;
    private Library home;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: constructs and runs WishList
    public CreateWishList() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runWishList();
    }

    //MODIFIES: this
    //EFFECTS: runs WishList and processes user command
    public void runWishList() {
        create();

        boolean keepRunning = true;

        while (keepRunning) {
            displayMenu();
            String command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                System.out.println("Do you want to save your BookList?");
                System.out.println("\ty = yes");
                String save = input.next();
                if (save.equals("y")) {
                    saveWishList();
                }
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }


    //EFFECTS: displays menu with choices for user to go forward.
    public void displayMenu() {
        System.out.println("Select from:");
        System.out.println("\tw => add a book to your wishlist");
        System.out.println("\tb => add a new book to Library");
        System.out.println("\tl => add a new library");
        System.out.println("\tv => view your WishList");
        System.out.println("\tc => change a Books status in your WishList");
        System.out.println("\ts -> save book list to file");
        System.out.println("\tf -> load book list from file");
        System.out.println("\te => end WishList");
    }

    //MODIFIES: this
    //EFFECTS: takes users command and runs program based off of choice.
    @SuppressWarnings("methodlength")
    public void processCommand(String command) {
        switch (command) {
            case "w" :
                addBookToWishList();
                break;
            case "b" :
                createNewBook();
                break;
            case "l" :
                newLibrary();
                break;
            case "v" :
                viewBooks();
                break;
            case "c" :
                changeStatus();
                break;
            case "s" :
                saveWishList();
                break;
            case "f" :
                loadBookList();
                break;
            default :
                System.out.println("Not valid, please try again");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Book from library to wishlist,
    // if the book is not in the library, creates book.
    private void addBookToWishList() {
        System.out.println("What is the name of the book you want to add?");
        String name = input.next();
        Book found = new Book(null, null);
        Book isfound = bookInLibrary(name, found);
        if (isfound.getName() != null) {
            addBook(isfound);
        } else {
            System.out.println("Cannot find book in Library");
            newBook();
        }
    }


    //MODIFIES: Library
    //EFFECTS: creates a book and adds to a library, returns created Book
    private Book createNewBook() {
        Book newBook = new Book(null, null);
        System.out.println("What is the name of your Book?");
        String name = input.next();
        System.out.println("Who is the author of your Book?");
        String author = input.next();
        System.out.println("Are you adding this Book to your WishList?");
        System.out.println("\t y = yes");
        String answer = input.next();
        newBook.changeName(name);
        newBook.changeAuthor(author);

        addToWishList(answer, newBook);

        addBookToLibrary(newBook);

        return newBook;

    }

    //MODIFIES: Library
    //EFFECTS: creates a new library with a catalogue of books.
    private void newLibrary() {
        System.out.println("What is the name of your library?");
        String libraryname = input.next();
        List<Book> emptyBook = new ArrayList<>();
        Library newLibrary = new Library(libraryname, emptyBook);
        addBookToNewLibrary(newLibrary);
    }

    //EFFECTS: prints out list of Books in wishlist.
    private void viewBooks() {
        System.out.println("The Books on your WishList are:");
        for (Book b : wishlist.getWishList()) {
            System.out.println(b.getName() + " by " + b.getAuthor() + " and you are of status " + b.getReading());
        }
    }

    //MODIFIES: this
    //EFFECTS: changes Reading status of a book in WishList.
    private void changeStatus() {
        if (0 == wishlist.getWishList().size()) {
            System.out.println("You don't have any books in your WishList");
        } else {
            System.out.println("Please enter the name of the Book:");
            String answer = input.next();
            int hasbook = 0;
            for (Book b : wishlist.getWishList()) {
                if (answer.equals(b.getName())) {
                    b.changeReading();
                    System.out.println(b.getName() + "'s status has been changed to " + b.getReading());
                    hasbook++;
                }
            }
            if (hasbook == 0) {
                System.out.println("That doesn't look like a book in your wishlist...");
            }
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
        System.out.println("Added " +  book.getName() + " by " + book.getAuthor() + " to your WishList");
    }

    //MODIFIES: this
    //EFFECTS: Creates new Book if user answers "n" to prompt
    private void newBook() {
        Book newBook = new Book(null, null);
        System.out.println("Create new Book?");
        System.out.println("\t n = no");
        String answer = input.next();
        if (answer.equals("n")) {
            System.out.println("Ok, you are not creating a new Book");
        } else {
            newBook = createNewBook();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Book to WishList and changes reading status if answer is y,
    private void addToWishList(String answer, Book book) {
        if (answer.equals("y")) {
            book.changeReading();
            wishlist.addBook(book);
            System.out.println("Added " + book.getName() + " by " + book.getAuthor() + " to your WishList");

        }
    }

    //MODIFIES: Library
    //EFFECTS: Adds book to library of users choice
    private void addBookToLibrary(Book book) {
        System.out.println("Which Library would you like to add the Book to");

        for (Library l : libraries) {
            System.out.println(l.getName());
        }

        String libraryname = input.next();
        int addedbooktolibrary = 0;
        for (Library l : libraries) {
            if (libraryname.equals(l.getName())) {
                l.addBook(book);
                addedbooktolibrary++;
            }

        }
        if (0 == addedbooktolibrary) {
            System.out.println("That's not a Library...");
        }
    }

    //MODIFIES: Library
    //EFFECTS: adds book to Library if user inputs "y"
    private void addBookToNewLibrary(Library library) {
        System.out.println("Would you like to add books to your library?");
        System.out.println("\ty = yes");
        String answer = input.next();

        if (answer.equals("y")) {
            System.out.println("What is the name of the Book?");
            String name = input.next();
            Book found = new Book(null, null);
            Book isfound = bookInLibrary(name, found);
            if (isfound.getName() != null) {
                library.addBook(isfound);
                System.out.println(isfound.getName() + " Has been added to " + library.getName());
                addBookToNewLibrary(library);
            } else {
                System.out.println("Cannot find book in Library");
            }
        }
        libraries.add(library);
    }


    //EFFECTS: saves wishList to file
    private void saveWishList() {
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
    private void loadBookList() {
        try {
            wishlist = jsonReader.read();
            System.out.println("Loaded BookList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
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
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }



}