package hust.soict.hedspi.test.aims;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.*;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismTest {
    public static void main(String[] args) {
        ArrayList<Media> mediae = new ArrayList<>();

        CompactDisc cd = new CompactDisc("Abbey Road", "Rock", "The Beatles", "George Martin", 22.95f);
        Track track1 = new Track("Come Together", 259);
        Track track2 = new Track("Something", 183);
        cd.addTrack(track1);
        cd.addTrack(track2);

        DigitalVideoDisc dvd = new DigitalVideoDisc("Pulp Fiction", "Crime", "Quentin Tarantino", 154, 17.99f);

        Book book = new Book("1984", "Dystopian", 15.50f);
        book.addAuthor("George Orwell");

        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);
        mediae.add(dvd); // Thêm lại dvd để kiểm tra

        System.out.println("--- Iterating through media list (Polymorphism with toString()) ---");
        for (Media m : mediae) {
            System.out.println(m.toString()); // Gọi toString() tương ứng với đối tượng thực tế
        }

        System.out.println("\n--- Iterating and playing (Polymorphism with Playable) ---");
        for(Media m : mediae) {
            if (m instanceof Playable) { // Kiểm tra xem đối tượng có thể play không
                ((Playable) m).play(); // Ép kiểu về Playable và gọi play()
            } else {
                System.out.println("Media '" + m.getTitle() + "' is not playable.");
            }
            System.out.println("-----");
        }
    }
}
