package hust.soict.hedspi.aims.media;

public abstract class Disc extends Media {
    private int length;
    private String director;

    // Constructor
    public Disc() {
        super();
    }

    public Disc(String title) {
        super(title);
    }
    public Disc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public Disc(String title, String category, float cost, String director) {
        super(title, category, cost);
        this.director = director;
    }
    public Disc(String title, String category, String director, int length, float cost) {
        super(title, category, cost); // Gọi constructor của Media
        this.director = director;
        this.length = length;
    }
    // Constructor với ID
    public Disc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }


    // Getters
    public int getLength() { return length; }
    public String getDirector() { return director; }

    // Setters (nếu cần)
    public void setLength(int length) { this.length = length; }
    public void setDirector(String director) { this.director = director; }



    @Override
    public String toString() {
        return super.toString() + " - Director: " + director + " - Length: " + length;
    }
}
