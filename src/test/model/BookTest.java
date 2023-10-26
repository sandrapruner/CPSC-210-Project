package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static model.Reading.*;


import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book testBook;


    @BeforeEach
    void runBefore() {
        testBook = new Book("Politics", "Aristotle");


    }

    @Test
    void testConstructor() {
        assertEquals("Politics", testBook.getName());
        assertEquals("Aristotle", testBook.getAuthor());
        assertNull(testBook.getReading());
    }

    @Test
    void testChangeReading() {
        // check that changeReading works for each change
        assertNull(testBook.getReading());
        testBook.changeReading();
        assertEquals(WANTTOREAD, testBook.getReading());
        testBook.changeReading();
        assertEquals(READING, testBook.getReading());
        testBook.changeReading();
        assertEquals(READ, testBook.getReading());
        testBook.changeReading();
        assertEquals(READ, testBook.getReading());
    }

    @Test
    void testChangeBookName() {
        assertEquals("Politics", testBook.getName());
        testBook.changeName("The Iliad");
        assertEquals("The Iliad", testBook.getName());
        assertEquals("Aristotle", testBook.getAuthor());
        assertNull(testBook.getReading());

        testBook.changeName("The Odyssey");
        testBook.changeName("Philosophy");
        assertEquals("Philosophy", testBook.getName());
        assertEquals("Aristotle", testBook.getAuthor());
        assertNull(testBook.getReading());
    }

    @Test
    void testChangeBookAuthor() {
        assertEquals("Aristotle", testBook.getAuthor());
        testBook.changeAuthor("Socrates");
        assertEquals("Politics", testBook.getName());
        assertEquals("Socrates", testBook.getAuthor());
        assertNull(testBook.getReading());

        testBook.changeAuthor("Plato");
        testBook.changeAuthor("Alexander");
        assertEquals("Politics", testBook.getName());
        assertEquals("Alexander", testBook.getAuthor());
        assertNull(testBook.getReading());

    }

    @Test
    void printBook() {
        assertEquals("Politics,Aristotle,null", testBook.toString());
    }












}
