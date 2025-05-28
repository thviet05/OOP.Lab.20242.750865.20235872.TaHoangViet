package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.AuthorException;
import hust.soict.hedspi.aims.exception.InvalidMediaFieldException;
import java.util.ArrayList;
import java.util.List;

// public class Book { // Trước khi có Media
public class Book extends Media { // Sau khi có Media
    private ArrayList<String> authors = new ArrayList<>();


    // Constructor (sau khi có Media)
    public Book() {
        super();
    }

    public Book(String title) throws InvalidMediaFieldException {
        super(title);
    }

    public Book(String title, String category, float cost) throws InvalidMediaFieldException {
        super(title, category, cost);
    }

    public Book(String title, String category, float cost, ArrayList<String> authors) throws InvalidMediaFieldException {
        super(title, category, cost);
        this.authors = authors;
    }

    public Book(int id, String title, String category, float cost) throws InvalidMediaFieldException {
        super(id, title, category, cost);
    }


    public void addAuthor(String authorName) throws AuthorException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new AuthorException("ERROR: Author name cannot be empty");
        }

        if (authors.contains(authorName)) {
            throw new AuthorException("ERROR: Author '" + authorName + "' already exists for this book");
        } else {
            authors.add(authorName);
            System.out.println("Author '" + authorName + "' added.");
        }
    }

    public void removeAuthor(String authorName) throws AuthorException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new AuthorException("ERROR: Author name cannot be empty");
        }

        if (authors.remove(authorName)) {
            System.out.println("Author '" + authorName + "' removed.");
        } else {
            throw new AuthorException("ERROR: Author '" + authorName + "' not found for this book");
        }
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        // Ví dụ toString cho Book
        return "Book - ID: " + getId() + " - Title: " + getTitle() +
                " - Category: " + getCategory() +
                " - Authors: " + String.join(", ", authors) +
                " - Cost: " + getCost() + "$";
    }
}
