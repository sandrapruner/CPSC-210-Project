package persistence;

import model.Book;
import model.Reading;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String name, String author, Reading reading, Book book) {
        assertEquals(name, book.getName());
        assertEquals(reading, book.getReading());
        assertEquals(author, book.getAuthor());

    }
}
