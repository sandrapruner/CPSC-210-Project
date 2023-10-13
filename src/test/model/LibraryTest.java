package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library testLibrary;
    List<Book> testLibraryBooks;
    Book book1;
    Book book2;
    Book book3;



    @BeforeEach
    void runBefore() {
        testLibraryBooks = new ArrayList<>();
        testLibrary = new Library("home", testLibraryBooks);
        book1 = new Book("Insomnia", "King");
        book2 = new Book("The Iliad", "Homer");
        book3 = new Book("Passenger", "Smith");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testLibrary.getBooks().size());
        assertEquals("home", testLibrary.getName());
        assertEquals(testLibraryBooks, testLibrary.getBooks());
    }

    @Test
    void testAddBook() {
        assertEquals(0, testLibrary.getBooks().size());
        testLibrary.addBook(book1);
        assertEquals(1, testLibrary.getBooks().size());
        assertTrue(testLibrary.getBooks().contains(book1));

        testLibrary.addBook(book2);
        testLibrary.addBook(book3);
        assertEquals(3, testLibrary.getBooks().size());
        assertTrue(testLibrary.getBooks().contains(book2));
        assertTrue(testLibrary.getBooks().contains(book3));

        testLibrary.addBook(book1);
        assertEquals(4, testLibrary.getBooks().size());
    }

    @Test
    void testInLibrary(){
        Book testBook = testLibrary.inLibrary("Insomnia");
        assertNull(testBook.getName());
        assertNull(testBook.getAuthor());
        assertNull(testBook.getReading());

        testLibrary.addBook(book1);
        testBook = testLibrary.inLibrary("The Iliad");
        assertNull(testBook.getName());
        assertNull(testBook.getAuthor());
        assertNull(testBook.getReading());

        testLibrary.addBook(book2);
        testLibrary.addBook(book3);
        testBook = testLibrary.inLibrary("The Iliad");
        assertEquals("The Iliad", testBook.getName());
        assertEquals("Homer", testBook.getAuthor());
        assertNull(testBook.getReading());
    }

}
