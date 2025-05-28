package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.InvalidMediaFieldException;

public abstract class Disc extends Media {
    private int length;
    private String director;

    // Constructor
    public Disc() {
        super();
    }

    public Disc(String title) throws InvalidMediaFieldException {
        super(title);
    }

    public Disc(String title, String category, float cost) throws InvalidMediaFieldException {
        super(title, category, cost);
    }

    public Disc(String title, String category, float cost, String director) throws InvalidMediaFieldException {
        super(title, category, cost);
        if (director == null || director.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Director name cannot be empty");
        }
        this.director = director;
    }

    public Disc(String title, String category, String director, int length, float cost) throws InvalidMediaFieldException {
        super(title, category, cost); // Gọi constructor của Media
        if (director == null || director.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Director name cannot be empty");
        }
        if (length < 0) {
            throw new InvalidMediaFieldException("ERROR: Length cannot be negative");
        }
        this.director = director;
        this.length = length;
    }

    // Constructor với ID
    public Disc(int id, String title, String category, String director, int length, float cost) throws InvalidMediaFieldException {
        super(id, title, category, cost);
        if (director == null || director.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Director name cannot be empty");
        }
        if (length < 0) {
            throw new InvalidMediaFieldException("ERROR: Length cannot be negative");
        }
        this.director = director;
        this.length = length;
    }


    // Getters
    public int getLength() { return length; }
    public String getDirector() { return director; }

    // Setters (nếu cần)
    public void setLength(int length) throws InvalidMediaFieldException { 
        if (length < 0) {
            throw new InvalidMediaFieldException("ERROR: Length cannot be negative");
        }
        this.length = length; 
    }

    public void setDirector(String director) throws InvalidMediaFieldException { 
        if (director == null || director.trim().isEmpty()) {
            throw new InvalidMediaFieldException("ERROR: Director name cannot be empty");
        }
        this.director = director; 
    }



    @Override
    public String toString() {
        return super.toString() + " - Director: " + director + " - Length: " + length;
    }
}
