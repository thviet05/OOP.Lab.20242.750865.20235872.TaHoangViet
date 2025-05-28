package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

import java.util.ArrayList;
import java.util.Collections;
import javax.naming.LimitExceededException;


public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    // private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    // private int qtyOrdered = 0;

    private ArrayList<Media> itemsOrdered = new ArrayList<>();


    public void addMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            System.out.println("Media '" + media.getTitle() + "' is already in the cart.");
        } else {
            itemsOrdered.add(media);
            System.out.println("Media '" + media.getTitle() + "' added to the cart.");
        }
    }


    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) { // Sử dụng equals() của Media
            System.out.println("Media '" + media.getTitle() + "' removed from the cart.");
        } else {
            System.out.println("Media '" + media.getTitle() + "' not found in the cart.");
        }
    }


    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void sortMediaByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortMediaByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }


    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        if (itemsOrdered.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (int i = 0; i < itemsOrdered.size(); i++) {
                System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
            }
        }
        System.out.println("Total cost: " + totalCost() + "$");
        System.out.println("***************************************************");
    }

    public Media searchByID(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found Media by ID: " + media.toString());
                return media;
            }
        }
        System.out.println("No media found with ID: " + id);
        return null;
    }
    public Media searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            // Sử dụng isMatch hoặc equalsIgnoreCase tùy thuộc vào định nghĩa isMatch/equals
            if (media.getTitle().equalsIgnoreCase(title)) { // Hoặc dùng isMatch nếu có
                System.out.println("Found Media by Title: " + media.toString());
                found = true;
                // Có thể trả về media đầu tiên tìm thấy hoặc một danh sách
                return media; // Trả về cái đầu tiên
            }
        }
        if (!found) {
            System.out.println("No media found with title matching: " + title);
        }
        return null;
    }

    public ArrayList<Media> searchAllByTitle(String title) {
        ArrayList<Media> results = new ArrayList<>();
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found Media containing Title: " + media.toString());
                results.add(media);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with title containing: " + title);
        }
        return results;
    }


    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void emptyCart() {
        itemsOrdered.clear();
        System.out.println("Cart has been emptied.");
    }
}
