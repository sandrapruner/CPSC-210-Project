package model;

import static model.Reading.*;

public class Book {
    private String name;
    private String author;
    private Reading reading;

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

    public void changeName(String name) {
        this.name = name;
    }

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
                default:
                    reading = WANTTOREAD;
                    break;
            }
        }

    }
}