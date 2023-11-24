package persistence;

import model.Book;
import model.Reading;
import model.WishList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            WishList wr = new WishList("wishlist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WishList wr = new WishList("wishlist");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWishList.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWishList.json");
            wr = reader.read();
            assertEquals("wishlist", wr.getName());
            assertEquals(0, wr.getWishList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WishList wr = new WishList("wishlist");
            wr.addBook(new Book("politics", "aristotle", null));
            wr.addBook(new Book("hh", "hh", null));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWishList.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWishList.json");
            wr = reader.read();
            assertEquals("wishlist", wr.getName());
            List<Book> books = wr.getWishList();
            assertEquals(2, books.size());
            checkBook("politics", "aristotle", Reading.READING, books.get(0));
            checkBook("hh", "hh", Reading.READING, books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
