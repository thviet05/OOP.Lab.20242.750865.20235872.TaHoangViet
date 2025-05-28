package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreManagerScreen extends JFrame {
    private Store store;


    public StoreManagerScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }


    JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JMenu menu = new JMenu("Options");


        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            System.out.println("Already viewing store.");
        });
        menu.add(viewStoreItem);


        JMenu smUpdateStore = new JMenu("Update Store");


        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> {
            this.setVisible(false);
            new AddBookToStoreScreen(store, this);
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> {
            this.setVisible(false);
            new AddCompactDiscToStoreScreen(store, this);
        });
        smUpdateStore.add(addCDItem);

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> {
            this.setVisible(false);
            new AddDigitalVideoDiscToStoreScreen(store, this);
        });
        smUpdateStore.add(addDVDItem);

        menu.add(smUpdateStore);
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        int numItems = store.getItemsInStore().size();
        int rows = (int) Math.ceil(numItems / 3.0);
        if (rows == 0) rows = 1;
        center.setLayout(new GridLayout(rows, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        int displayCount = Math.min(mediaInStore.size(), 9);
        for (int i = 0; i < displayCount; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    public static void main(String[] args) {
        Store sampleStore = new Store();

        Book book1 = new Book(1, "The Lord of the Rings", "Fantasy", 19.99f);
        book1.addAuthor("J.R.R. Tolkien");
        sampleStore.addMedia(book1);

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(2, "The Lion King", "Animation",
                "Roger Allers", 89, 15.50f);
        sampleStore.addMedia(dvd1);

        CompactDisc cd1 = new CompactDisc(3, "Abbey Road", "Rock", "Adam", "James Gun", 45.5f);
        Track track1 = new Track("Come Together", 4);
        Track track2 = new Track("Something", 3);
        cd1.addTrack(track1);
        cd1.addTrack(track2);
        sampleStore.addMedia(cd1);

        Book book2 = new Book(4, "Dune", "Sci-Fi", 22.50f);
        book2.addAuthor("Frank Herbert");
        sampleStore.addMedia(book2);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(5, "Inception", "Sci-Fi", "Maverick", 148, 500);
        sampleStore.addMedia(dvd2);


        new StoreManagerScreen(sampleStore);
    }
}