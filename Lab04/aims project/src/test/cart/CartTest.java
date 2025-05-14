package hust.soict.hedspi.test.cart;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        cart.addMedia(dvd1);
        Book book1 = new Book("Introduction to Java", "Programming", 25.5f);
        book1.addAuthor("John Doe");
        cart.addMedia(book1);
        CompactDisc cd1 = new CompactDisc("Greatest Hits", "Pop", "Artist Name", "Director Name", 30.0f);
        // Thêm track cho cd1 nếu cần
        cart.addMedia(cd1);


        System.out.println("--- Testing print() method ---");
        cart.print();

        System.out.println("\n--- Testing searchByID() method ---");
        cart.searchByID(dvd1.getId()); // Giả sử ID tự động gán hoặc set thủ công
        cart.searchByID(99); // ID không tồn tại

        System.out.println("\n--- Testing searchByTitle() method ---");
        cart.searchByTitle("The Lion King");
        cart.searchByTitle("Non Existent Title");
        cart.searchAllByTitle("King"); // Test search chứa chuỗi


    }
}