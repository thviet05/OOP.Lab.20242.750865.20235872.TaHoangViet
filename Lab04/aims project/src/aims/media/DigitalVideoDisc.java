package hust.soict.hedspi.aims.media;

// public class DigitalVideoDisc extends Media implements Playable { // Trước khi có Disc
public class DigitalVideoDisc extends Disc implements Playable { // Sau khi có Disc

    // Constructor gọi super của Disc

    public DigitalVideoDisc() {
        super();
    }
    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category,  float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, float cost, String director) {
        super(title, category, cost, director);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, director, length, cost);
    }


    @Override
    public void play() {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength()); // getLength() từ lớp Disc
        } else {
            System.out.println("Cannot play DVD: " + this.getTitle() + " - Length is 0 or less.");
        }
    }

    @Override
    public String toString() {
        return "DVD - " + super.toString(); // Gọi toString của Disc
    }
}