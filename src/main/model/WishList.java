package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class WishList implements Writable {
    private String name;
    private List<Book> wishlist;

    public WishList(String name) {
        this.name = name;
        wishlist = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getWishList() {
        return wishlist;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void addBook(Book b) {
        wishlist.add(b);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("books", booksToJson());
        return json;
    }

    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : wishlist) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
