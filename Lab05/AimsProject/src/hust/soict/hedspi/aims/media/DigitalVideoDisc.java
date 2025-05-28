package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.InvalidMediaFieldException;
import hust.soict.hedspi.aims.exception.PlayerException;

// public class DigitalVideoDisc extends Media implements Playable { // Trước khi có Disc
public class DigitalVideoDisc extends Disc implements Playable { // Sau khi có Disc

    // Constructor gọi super của Disc

    public DigitalVideoDisc() {
        super();
    }

    public DigitalVideoDisc(String title) throws InvalidMediaFieldException {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) throws InvalidMediaFieldException {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, float cost, String director) throws InvalidMediaFieldException {
        super(title, category, cost, director);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) throws InvalidMediaFieldException {
        super(title, category, director, length, cost);
    }

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) throws InvalidMediaFieldException {
        super(id, title, category, director, length, cost);
    }


    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength()); // getLength() từ lớp Disc
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    @Override
    public String toString() {
        return "DVD - " + super.toString(); // Gọi toString của Disc
    }
}
