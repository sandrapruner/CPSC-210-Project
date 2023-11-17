package model;

import java.util.List;

// Represents Library having a name and list of Books
public class Library {
    private final String name;
    private final List<Book> books;

    //REQUIRES: name is of non-zero length
    //EFFECTS: this.name is set to name, this.books is set to books.
    public Library(String name, List<Book> books) {
        this.books = books;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    //MODIFIES: this
    //EFFECTS: adds a book to list of book.
    public void addBook(Book book) {
        books.add(book);
    }

    //REQUIRES: name is of non-zero length
    //MODIFIES: this
    //EFFECTS: goes through books in library, if a name of Book matches with name,
    //          sets found to that book, returns found
    public Book inLibrary(String name) {
        Book found = new Book(null, null, null);
        for (Book b : books) {
            if (name.equals(b.getName())) {
                found = b;
            }
        }
        return found;
    }

    //EFFECTS: return String ListOfBooks
    public String getAllBooks() {
        String s = "";
        for (Book b : books) {
            s = s + b.getName() + " by " + b.getAuthor() + ". ";
        }
        return s;
    }


}
