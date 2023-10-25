package model;

import org.json.JSONObject;
import persistence.Writable;

import static model.Reading.*;

// represents Book with name, author, and reading status
public class Book implements Writable {
    private String name;
    private String author;
    private Reading reading;

    // REQUIRES: Book has non-zero length name and author
    // EFFECTS: this.name is set to name, this.author is set to author,
    //          reading status set to null.
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.reading = null;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Reading getReading() {
        return reading;
    }

    //REQUIRES: name is of non-zero length
    //MODIFIES: this
    //EFFECTS: changes this.name of book to name
    public void changeName(String name) {
        this.name = name;
    }

    //REQUIRES: author is of non-zero length
    //MODIFIES: this
    //EFFECTS: changes this.author of book to author
    public void changeAuthor(String author) {
        this.author = author;
    }

    //MODIFIES: this
    //EFFECTS: changes reading status of Book to next status. Sets to WANTTOREAD if no current status.
    public void changeReading() {
        if (null == reading) {
            reading = WANTTOREAD;
        } else {
            switch (reading) {
                case WANTTOREAD:
                    reading = READING;
                    break;
                case READING:
                    reading = READ;
                    break;
                case READ:
                    break;
            }
        }

    }

    public String toString() {
        return name + "," + author + "," + reading;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("author", author);
        json.put("reading", reading);
        return json;
    }
}