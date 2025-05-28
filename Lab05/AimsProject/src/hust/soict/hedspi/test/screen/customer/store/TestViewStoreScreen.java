package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.MediaException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {
    private static Store store;
    private static Cart cart;

    public static Store getStore() {
        return store;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Cart getCart() {
        return cart;
    }

    public static void main(String[] args) throws MediaException {
        store = new Store();
        cart = new Cart();

        // Add DVDs to store
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);

        // Add Books to store
        Book book1 = new Book("The Hobbit", "Fantasy", 15.99f);
        book1.addAuthor("J.R.R. Tolkien");

        Book book2 = new Book("Harry Potter and the Philosopher's Stone", "Fantasy", 12.99f);
        book2.addAuthor("J.K. Rowling");

        // Add CDs to store
        CompactDisc cd1 = new CompactDisc("Greatest Hits", "Rock", "Queen", "Various Directors", 22.95f);
        cd1.addTrack(new Track("Bohemian Rhapsody", 355));
        cd1.addTrack(new Track("Another One Bites the Dust", 214));

        CompactDisc cd2 = new CompactDisc("Thriller", "Pop", "Michael Jackson", "Quincy Jones", 19.99f);
        cd2.addTrack(new Track("Thriller", 358));
        cd2.addTrack(new Track("Billie Jean", 294));

        // Add media to store
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(cd1);
        store.addMedia(cd2);

        // Print store contents
        store.printStore();

        launch(args);
    }
}
