package persistence;

import model.Book;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.Reading;

import org.json.*;

import java.util.List;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public List<Book> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookList(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private List<Book> parseBookList(JSONObject jsonObject) {
        List<Book> bl = new ArrayList<>();
        addBooks(bl, jsonObject);
        return bl;
    }

    private void addBooks(List<Book> bl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBooks = (JSONObject) json;
            addBook(bl, nextBooks);
        }
    }

    private void addBook(List<Book> bl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        Reading reading = Reading.valueOf(jsonObject.getString("reading"));
        Book book = new Book(name, author);
        //MAKE CHANGE READING FUNCTION
        //MAKE BOOKLIST A CLASS???
        bl.add(book);
    }
}
