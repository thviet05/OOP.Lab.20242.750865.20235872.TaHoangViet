package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends JFrame {
    private Store store;
    private JFrame parentScreen;

    // Fields
    private JTextField tfId;
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfCost;
    private JTextField tfArtist;
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddCompactDiscToStoreScreen(Store store, JFrame parent) {
        this.store = store;
        this.parentScreen = parent;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(5, 5));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5)); // Thêm dòng cho Artist, Director, Length
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("ID:"));
        tfId = new JTextField();
        formPanel.add(tfId);

        formPanel.add(new JLabel("Title:"));
        tfTitle = new JTextField();
        formPanel.add(tfTitle);

        formPanel.add(new JLabel("Category:"));
        tfCategory = new JTextField();
        formPanel.add(tfCategory);

        formPanel.add(new JLabel("Cost (€):"));
        tfCost = new JTextField();
        formPanel.add(tfCost);

        formPanel.add(new JLabel("Artist:"));
        tfArtist = new JTextField();
        formPanel.add(tfArtist);

        formPanel.add(new JLabel("Director:"));
        tfDirector = new JTextField();
        formPanel.add(tfDirector);

        formPanel.add(new JLabel("Length (minutes):"));
        tfLength = new JTextField();
        formPanel.add(tfLength);

        cp.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAdd = new JButton("Add CD");
        JButton btnBack = new JButton("Back to Store");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnBack);
        cp.add(buttonPanel, BorderLayout.SOUTH);


        btnAdd.addActionListener(e -> addCDAction());
        btnBack.addActionListener(e -> goBackAction());


        setTitle("Add New Compact Disc");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addCDAction() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String artist = tfArtist.getText();
            String director = tfDirector.getText();
            int length = Integer.parseInt(tfLength.getText());

            if (title.isEmpty() || category.isEmpty() || artist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in Title, Category, and Artist.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            ArrayList<Track> tracks = null;
            CompactDisc newCD = new CompactDisc(id, title, category, director, length, cost, tracks);



            store.addMedia(newCD);
            JOptionPane.showMessageDialog(this, "CD added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            goBackAction();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format for ID, Cost, or Length.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding CD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackAction() {
        this.dispose();
        parentScreen.setVisible(true);
    }
}