package hust.soict.hedspi.aims;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Aims {

    private static Store store = new Store();
    public static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        setupStore();

        int choice;
        do {
            showMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore(); // Cần quyền admin? Tạm thời đơn giản
                    break;
                case 3:
                    viewCart();
                    break;
                case 0:
                    System.out.println("Exiting AIMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // Thiết lập dữ liệu mẫu
    public static void setupStore() {
        try {
            DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
            CompactDisc cd1 = new CompactDisc("Abbey Road", "Rock", "The Beatles", "George Martin", 22.95f);
            Track t1 = new Track("Come Together", 259);
            Track t2 = new Track("Something", 183);
            cd1.addTrack(t1); 
            cd1.addTrack(t2);
            Book book1 = new Book("1984", "Dystopian", 15.50f);
            book1.addAuthor("George Orwell");
            DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Sci-fi", "George Lucas", 121, 24.95f);
            DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "Guy Ritchie", 128, 18.99f);

            store.addMedia(dvd1);
            store.addMedia(cd1);
            store.addMedia(book1);
            store.addMedia(dvd2);
            store.addMedia(dvd3); // Add more items
        } catch (Exception e) {
            System.err.println("Error setting up store: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error setting up store: " + e.getMessage(), 
                "Setup Exception", 
                JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void viewStore() {
        store.printStore();
        int choice;
        do {
            storeMenu();
            choice = getUserChoice();
            switch (choice) {
                case 1: // See media details
                    System.out.print("Enter the title of the media to see details: ");
                    String title = scanner.nextLine();
                    Media media = store.findMediaByTitle(title);
                    if (media != null) {
                        System.out.println("Details:\n" + media.toString());
                        mediaDetailsMenu(media);
                    } else {
                        System.out.println("Media with title '" + title + "' not found in store.");
                    }
                    break;
                case 2: // Add media to cart
                    System.out.print("Enter the title of the media to add to cart: ");
                    title = scanner.nextLine();
                    media = store.findMediaByTitle(title);
                    if (media != null) {
                        try {
                            cart.addMedia(media);
                            System.out.println("Number of items in cart: " + cart.getItemsOrdered().size());
                        } catch (javax.naming.LimitExceededException e) {
                            System.err.println("Error adding media to cart: " + e.getMessage());
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, 
                                "Error adding media to cart: " + e.getMessage(), 
                                "Cart Exception", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("Media with title '" + title + "' not found in store.");
                    }
                    break;
                case 3: // Play media
                    System.out.print("Enter the title of the media to play: ");
                    title = scanner.nextLine();
                    media = store.findMediaByTitle(title);
                    if (media != null) {
                        if (media instanceof Playable) {
                            try {
                                ((Playable) media).play();
                            } catch (PlayerException e) {
                                System.err.println("Error playing media: " + e.getMessage());
                                System.err.println("Exception details: " + e.toString());
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null, 
                                    "Error playing media: " + e.getMessage(), 
                                    "Player Exception", 
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            System.out.println("This media ('" + media.getTitle() + "') is not playable.");
                        }
                    } else {
                        System.out.println("Media with title '" + title + "' not found in store.");
                    }
                    break;
                case 4: // See current cart
                    viewCart();
                    return;
                case 0: // Back
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void mediaDetailsMenu(Media media) {
        System.out.println("\nOptions for " + media.getTitle() + ":");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (media instanceof Playable) { // Chỉ hiện Play nếu đối tượng là Playable
            System.out.println("2. Play");
        }
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");

        int choice = getUserChoice();
        switch (choice) {
            case 1: // Add to cart
                try {
                    cart.addMedia(media);
                } catch (javax.naming.LimitExceededException e) {
                    System.err.println("Error adding media to cart: " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, 
                        "Error adding media to cart: " + e.getMessage(), 
                        "Cart Exception", 
                        JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2: // Play
                if (media instanceof Playable) {
                    try {
                        ((Playable) media).play();
                    } catch (PlayerException e) {
                        System.err.println("Error playing media: " + e.getMessage());
                        System.err.println("Exception details: " + e.toString());
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, 
                            "Error playing media: " + e.getMessage(), 
                            "Player Exception", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    System.out.println("Invalid choice for this media type.");
                }
                break;
            case 0: // Back
                System.out.println("Returning to store menu...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void updateStore() {
        // Chức năng đơn giản: thêm hoặc xóa media khỏi store
        System.out.println("\nUpdate Store Options:");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("0. Back");
        System.out.print("Choose option: ");
        int choice = getUserChoice();

        if (choice == 1) {
            System.out.println("Add Media function not fully implemented yet.");
            // Ví dụ thêm nhanh 1 DVD:
            // DigitalVideoDisc newDvd = new DigitalVideoDisc("New DVD", "Test", "Test Director", 90, 10f);
            // store.addMedia(newDvd);
        } else if (choice == 2) {
            System.out.print("Enter title of media to remove: ");
            String title = scanner.nextLine();
            Media mediaToRemove = store.findMediaByTitle(title);
            if (mediaToRemove != null) {
                try {
                    store.removeMedia(mediaToRemove);
                } catch (hust.soict.hedspi.aims.exception.StoreException e) {
                    System.err.println("Error removing media from store: " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, 
                        "Error removing media from store: " + e.getMessage(), 
                        "Store Exception", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Media not found.");
            }
        } else if (choice == 0) {
            System.out.println("Returning to main menu...");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public static void viewCart() {
        cart.print();
        int choice;
        do {
            cartMenu();
            choice = getUserChoice();
            switch (choice) {
                case 1: // Filter media
                    filterCartMenu();
                    break;
                case 2: // Sort media
                    sortCartMenu();
                    break;
                case 3: // Remove media from cart
                    System.out.print("Enter the title of the media to remove from cart: ");
                    String title = scanner.nextLine();
                    // Cần tìm media trong cart dựa trên title
                    Media mediaToRemove = cart.searchByTitle(title); // Dùng searchByTitle để tìm object trong cart
                    if (mediaToRemove != null) {
                        try {
                            cart.removeMedia(mediaToRemove);
                        } catch (hust.soict.hedspi.aims.exception.MediaNotFoundException e) {
                            System.err.println("Error removing media from cart: " + e.getMessage());
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, 
                                "Error removing media from cart: " + e.getMessage(), 
                                "Cart Exception", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // searchByTitle đã in thông báo not found
                    }
                    cart.print(); // In lại giỏ hàng sau khi xóa
                    break;
                case 4: // Play media
                    System.out.print("Enter the title of the media in cart to play: ");
                    title = scanner.nextLine();
                    Media mediaToPlay = cart.searchByTitle(title);
                    if (mediaToPlay != null) {
                        if (mediaToPlay instanceof Playable) {
                            try {
                                ((Playable) mediaToPlay).play();
                            } catch (PlayerException e) {
                                System.err.println("Error playing media: " + e.getMessage());
                                System.err.println("Exception details: " + e.toString());
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null, 
                                    "Error playing media: " + e.getMessage(), 
                                    "Player Exception", 
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            System.out.println("This media ('" + mediaToPlay.getTitle() + "') is not playable.");
                        }
                    } // searchByTitle đã in thông báo not found nếu null
                    break;
                case 5: // Place order
                    System.out.println("Order placed successfully!");
                    cart.emptyCart();
                    return;
                case 0: // Back
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void cartMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }

    public static void filterCartMenu() {
        System.out.println("\nFilter Options:");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by Title");
        System.out.println("0. Back");
        System.out.print("Choose option: ");
        int choice = getUserChoice();
        if (choice == 1) {
            System.out.print("Enter ID to filter: ");
            int id = getUserChoice();
            cart.searchByID(id);
        } else if (choice == 2) {
            System.out.print("Enter Title to filter: ");
            String title = scanner.nextLine();
            cart.searchAllByTitle(title);
        } else if (choice != 0) {
            System.out.println("Invalid choice.");
        }
    }

    public static void sortCartMenu() {
        System.out.println("\nSort Options:");
        System.out.println("1. Sort by Title then Cost");
        System.out.println("2. Sort by Cost then Title");
        System.out.println("0. Back");
        System.out.print("Choose option: ");
        int choice = getUserChoice();
        ObservableList<Media> items = cart.getItemsOrdered();
        if (choice == 1) {
            Collections.sort(items, Media.COMPARE_BY_TITLE_COST);
            System.out.println("Cart sorted by Title then Cost:");
        } else if (choice == 2) {
            Collections.sort(items, Media.COMPARE_BY_COST_TITLE);
            System.out.println("Cart sorted by Cost then Title:");
        } else if (choice == 0) {
            System.out.println("Returning to cart menu...");
            return;
        } else {
            System.out.println("Invalid choice.");
            return; // Quay lại nếu chọn sai
        }
        cart.print(); // In lại giỏ hàng đã sắp xếp
    }


    // Helper method để lấy lựa chọn số từ người dùng, xử lý lỗi nhập sai
    private static int getUserChoice() {
        int choice = -1;
        while (choice == -1) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Reset choice để vòng lặp tiếp tục
                System.out.print("Please choose again: "); // Yêu cầu nhập lại
            }
        }
        return choice;
    }
}
