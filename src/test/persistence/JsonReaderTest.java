package persistence;

import model.Book;
import model.Reading;
import model.WishList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WishList wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyWishList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWishList.json");
        try {
            WishList wl = reader.read();
            assertEquals("wishlist", wl.getName());
            assertEquals(0, wl.getWishList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWishList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWishList.json");
        try {
            WishList wl = reader.read();
            assertEquals("wishlist", wl.getName());
            List<Book> books = wl.getWishList();
            assertEquals(2, books.size());
            checkBook("politics", "aristotle", Reading.READING, books.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }


}
