package hust.soict.hedspi.test.store;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("DVD Title 1", "Category 1", "Director 1", 120, 15.0f);
        Book book1 = new Book("Book Title 1", "Category 2", 20.0f);
        CompactDisc cd1 = new CompactDisc("CD Title 1", "Category 3", "Artist 1", "Director 2", 25.0f);

        store.addMedia(dvd1);
        store.addMedia(book1);
        store.addMedia(cd1);
        store.addMedia(dvd1); // Test thêm trùng

        System.out.println("\n--- Store contents after adding ---");
        store.printStore();


        System.out.println("\n--- Testing removeMedia() ---");
        store.removeMedia(book1);
        store.removeMedia(new Book("Non Existent Book", "", 0f)); // Test xóa không tồn tại

        System.out.println("\n--- Store contents after removing ---");
        store.printStore();

        System.out.println("\n--- Testing findMediaByTitle() ---");
        Media found = store.findMediaByTitle("DVD Title 1");
        if (found != null) System.out.println("Found: " + found.getTitle());
        found = store.findMediaByTitle("Unknown Title");
        if (found == null) System.out.println("Correctly did not find 'Unknown Title'");

    }
}