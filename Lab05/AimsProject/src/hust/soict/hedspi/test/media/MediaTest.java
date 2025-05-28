package hust.soict.hedspi.test.media;

import hust.soict.hedspi.aims.exception.InvalidMediaFieldException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class MediaTest {
    public static void main(String[] args) {
        try {
            // Create some media objects
            Media dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
            Media dvd2 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
            Media dvd3 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 24.95f);
            Media book1 = new Book("The Hobbit", "Fantasy", 19.95f);
            Media cd1 = new CompactDisc("Abbey Road", "Rock", "The Beatles", "George Martin", 19.95f);
            
            // Test equals method
            System.out.println("Testing equals() method:");
            System.out.println("dvd1 equals dvd2: " + dvd1.equals(dvd2)); // Should be true (same title and cost)
            System.out.println("dvd1 equals dvd3: " + dvd1.equals(dvd3)); // Should be false (different cost)
            System.out.println("dvd1 equals book1: " + dvd1.equals(book1)); // Should be false (different title)
            System.out.println("dvd1 equals cd1: " + dvd1.equals(cd1)); // Should be false (different title)
            System.out.println("book1 equals null: " + book1.equals(null)); // Should be false
            
            // Test compareTo method
            System.out.println("\nTesting compareTo() method:");
            System.out.println("dvd1 compareTo dvd2: " + dvd1.compareTo(dvd2)); // Should be 0 (equal)
            System.out.println("dvd1 compareTo dvd3: " + dvd1.compareTo(dvd3)); // Should be negative (same title, lower cost)
            System.out.println("dvd3 compareTo dvd1: " + dvd3.compareTo(dvd1)); // Should be positive (same title, higher cost)
            System.out.println("dvd1 compareTo book1: " + dvd1.compareTo(book1)); // Depends on title comparison
            System.out.println("book1 compareTo dvd1: " + book1.compareTo(dvd1)); // Depends on title comparison
            
            // Test null handling in compareTo
            try {
                System.out.println("dvd1 compareTo null: " + dvd1.compareTo(null));
            } catch (NullPointerException e) {
                System.out.println("NullPointerException caught as expected: " + e.getMessage());
            }
            
            // Test with same title but different cost
            Media dvd4 = new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 121, 24.95f);
            Media dvd5 = new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 121, 19.95f);
            System.out.println("\nTesting with same title but different cost:");
            System.out.println("dvd4 equals dvd5: " + dvd4.equals(dvd5)); // Should be false (different cost)
            System.out.println("dvd4 compareTo dvd5: " + dvd4.compareTo(dvd5)); // Should be positive (same title, higher cost)
            
        } catch (InvalidMediaFieldException e) {
            System.err.println("Error creating media: " + e.getMessage());
        }
    }
}