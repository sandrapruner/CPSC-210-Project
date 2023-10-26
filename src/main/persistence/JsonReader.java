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

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public WishList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWishList(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private WishList parseWishList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WishList wl = new WishList(name);
        addBooks(wl, jsonObject);
        return wl;
    }

    private void addBooks(WishList wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBooks = (JSONObject) json;
            addBook(wl, nextBooks);
        }
    }

    private void addBook(WishList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        Reading reading = Reading.valueOf(jsonObject.getString("reading"));
        Book book = new Book(name, author);
        book.changeReading(reading);
        //MAKE CHANGE READING FUNCTION
        //MAKE BOOKLIST A CLASS???
        wl.addBook(book);
    }
}
