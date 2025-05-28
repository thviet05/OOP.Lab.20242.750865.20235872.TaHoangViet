package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AddBookToStoreScreen extends JFrame {
    private Store store;
    private JFrame parentScreen;


    private JTextField tfId;
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfCost;
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store, JFrame parent) {
        this.store = store;
        this.parentScreen = parent;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(5, 5));


        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
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

        formPanel.add(new JLabel("Authors (comma-separated):"));
        tfAuthors = new JTextField();
        formPanel.add(tfAuthors);

        cp.add(formPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAdd = new JButton("Add Book");
        JButton btnBack = new JButton("Back to Store");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnBack);
        cp.add(buttonPanel, BorderLayout.SOUTH);


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookAction();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackAction();
            }
        });


        setTitle("Add New Book");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addBookAction() {
        try {

            int id = Integer.parseInt(tfId.getText());
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String authorsInput = tfAuthors.getText();


            if (title.isEmpty() || category.isEmpty() || authorsInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            Book newBook = new Book(id, title, category, cost);

            String[] authors = authorsInput.split(",");
            for (String author : authors) {
                newBook.addAuthor(author.trim());
            }


            store.addMedia(newBook);


            JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            goBackAction();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format for ID or Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackAction() {
        this.dispose();
        parentScreen.setVisible(true);
    }
}