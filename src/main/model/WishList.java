package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a WishList with a name and list of Books.
public class WishList implements Writable {
    private String name;
    private List<Book> wishlist;

    //EFFECTS: constructs a wishlist with name and empty list of Books.
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

    //MODIFIES: this.
    //EFFECTS: changes name of WishList to name.
    public void changeName(String name) {
        this.name = name;
    }

    //MODIFIES: this.
    //EFFECTS: adds a Book to list of Books in WishList.
    public void addBook(Book b) {
        wishlist.add(b);
    }

    //EFFECTS: returns string of WishList
    public String getBooks() {
        String wl = name + ": ";
        for (Book b : wishlist) {
            wl = wl + b.getName() + " by " + b.getAuthor() + ", ";
        }
        return wl;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("books", booksToJson());
        return json;
    }

    //EFFECTS: retrieves and returns List of Books in WishList as a JSON Array.
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : wishlist) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
