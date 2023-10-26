package persistence;

import model.WishList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

    public void write(WishList wl) {
        JSONObject json = wl.toJson();
        saveToFile(json.toString(TAB));
    }

//    private JSONObject bookListToJson(WishList wl) {
//        JSONObject json = new JSONObject();
//        json.put("books", booksToJson(wl));
//        json.put("name", "bookList");
//        return json;
//    }
//
//    private JSONArray booksToJson(WishList wl) {
//        JSONArray jsonArray = new JSONArray();
//        for (Book b : wl.getWish) {
//            jsonArray.put(b.toJson());
//        }
//        return jsonArray;
//    }


    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }
}
