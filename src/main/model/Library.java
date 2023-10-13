package model;

import java.util.List;

public class Library {
    private final String name;
    private final List<Book> books;


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

    public Book inLibrary(String name) {
        Book found = new Book(null, null);
        for (Book b : books) {
            if (name.equals(b.getName())) {
                found = b;
            }
        }
        return found;
    }
}
