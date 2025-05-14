package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (itemsInStore.contains(media)) {
            System.out.println("Media '" + media.getTitle() + "' already exists in the store.");
        } else {
            itemsInStore.add(media);
            System.out.println("Media '" + media.getTitle() + "' added to the store.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Media '" + media.getTitle() + "' removed from the store.");
        } else {
            System.out.println("Media '" + media.getTitle() + "' not found in the store.");
        }
    }


    public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null; // Not found
    }


    public void printStore() {
        System.out.println("***********************STORE***********************");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            System.out.println("Available Items:");
            for (int i = 0; i < itemsInStore.size(); i++) {
                // Chỉ hiển thị thông tin cơ bản, không phải toString() đầy đủ của cart
                System.out.println((i + 1) + ". " + itemsInStore.get(i).getTitle() + " - " + itemsInStore.get(i).getCategory() + " (" + itemsInStore.get(i).getClass().getSimpleName() + ")");
            }
        }
        System.out.println("***************************************************");
    }


    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}
