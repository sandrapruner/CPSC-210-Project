package persistence;

import model.Book;
import model.Reading;
import model.WishList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads WishList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WishList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWishList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses WishList from JSON object and returns it
    private WishList parseWishList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WishList wl = new WishList(name);
        addBooks(wl, jsonObject);
        return wl;
    }

    // MODIFIES: wl
    // EFFECTS: parses books from JSON object and adds them to WishList
    private void addBooks(WishList wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBooks = (JSONObject) json;
            addBook(wl, nextBooks);
        }
    }

    // MODIFIES: wl
    // EFFECTS: parses book from JSON object and adds it to WishList
    private void addBook(WishList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        Reading reading = Reading.valueOf(jsonObject.getString("reading"));
        Book book = new Book(name, author);
        book.changeReading(reading);
        wl.addBook(book);
    }
}
