package persistence;

import model.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.List;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(List<Book> bl) {
        //JSONObject json = bl.toJson();
        saveToFile(bookListToJson(bl).toString(TAB));
    }

    private JSONObject bookListToJson(List<Book> bl) {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson(bl));
        json.put("name", "bookList");
        return json;
    }

    private JSONArray booksToJson(List<Book> bl) {
        JSONArray jsonArray = new JSONArray();
        for (Book b : bl) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }


    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }
}
