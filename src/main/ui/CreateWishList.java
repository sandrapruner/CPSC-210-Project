package ui;

import model.Book;
import model.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateWishList {
    private List<Library> libraries;
    private List<Book> bookList;
    private Book book1;
    private Book book2;
    private Book book3;
    private List<Book> library1;
    private List<Book> library2;
    private Library ubc;
    private Library home;
    private Scanner input;




    public CreateWishList() {
        runWishList();
    }

    private void runWishList() {
        boolean keepRunning = true;
        String command = null;

        create();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }

    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("\tw => add a book to your wishlist");
        System.out.println("\tb => add a new book to Library");
        System.out.println("\tl => add a new library");
        System.out.println("\tv => view your WishList");
        System.out.println("\tc => change a Books status in your WishList");
        System.out.println("\te => end WishList");
    }

    private void processCommand(String command) {
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
            default :
                System.out.println("Not valid, please try again");
        }
    }

    private void changeStatus() {
        if (0 == bookList.size()) {
            System.out.println("You don't have any books in your WishList");
        } else {
            System.out.println("Please enter the name of the Book:");
            String answer = input.next();
            int hasbook = 0;
            for (Book b : bookList) {
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

    private void viewBooks() {
        System.out.println("The Books on your WishList are:");
        for (Book b : bookList) {
            System.out.println(b.getName() + " by " + b.getAuthor() + " and you are of status " + b.getReading());
        }

    }

    private void addBookToWishList() {
        System.out.println("What is the name of the book you want to add?");
        String name = input.next();
        Book found = new Book(null, null);
        for (Library l : libraries) {
            Book looking = l.inLibrary(name);
            if (null != looking.getName()) {
                found = looking;
            }
        }

        if (null != found.getName()) {
            found.changeReading();
            bookList.add(found);
            System.out.println("Added " + found.getName() + " by " + found.getAuthor() + " to your WishList");
        } else {
            System.out.println("Cannot find book in Library");
            newBook();
        }

    }


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
        addBookToLibrary(newBook);
    }



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

        if (answer.equals("y")) {
            newBook.changeReading();
            bookList.add(newBook);
            System.out.println("Added " + newBook.getName() + " by " + newBook.getAuthor() + " to your WishList");

        }
        return newBook;
    }

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
            System.out.println("Thats not a Library...");
        }
    }

    private void newLibrary() {
        System.out.println("What is the name of your library?");
        String libraryname = input.next();
        List<Book> emptyBook = new ArrayList<>();
        Library newLibrary = new Library(libraryname, emptyBook);
        System.out.println("Would you like to add books to your library?");
        System.out.println("\ty = yes");
        String answer = input.next();
        if (answer.equals("y")) {
            System.out.println("What is the name of the Book?");
            String name = input.next();
            Book found = new Book(null, null);
            for (Library l : libraries) {
                Book looking = l.inLibrary(name);
                if (null != looking.getName()) {
                    found = looking;
                }
            }
            if (null != found.getName()) {
                newLibrary.addBook(found);
                System.out.println(found.getName() + " Has been added to " + newLibrary.getName());
//had line which was else, do not have that book
            }
        }
    }

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
        this.bookList = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
}

