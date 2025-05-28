package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.test.screen.customer.store.TestViewStoreScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;


public class CartController {

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private Label costLabel;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TextField tfFilter;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Play Media");
                alert.setHeaderText(null);
                alert.setContentText("Now playing " + media.getTitle());
                alert.showAndWait();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        try {
            this.cart.removeMedia(media);
            updateCostLabel();
        } catch (MediaNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            // Lines 65-68: load the fxml file of the Store Screen
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            ViewStoreController viewStoreController = new ViewStoreController(store, cart);
            fxmlLoader.setController(viewStoreController);
            Parent root = fxmlLoader.load();

            // Line 69: The getSource() method returns an object on which the event initially occurred
            // Call getScene().getWindow() on the node on which the action occurred to get the "current" window
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            // Line 70-72: Set new scene for the current stage
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(
                new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost"));
        if (this.cart != null && this.cart.getItemsOrdered() != null) {
            tblMedia.setItems(this.cart.getItemsOrdered());
        }

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>(){
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                updateButtonBar(newValue);
            }
        });

        updateCostLabel();

        // Set up filter functionality
        if (tfFilter != null) {
            tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
                filterMedia(newValue);
            });
        }
    }

    private void filterMedia(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
        } else {
            ObservableList<Media> filteredList = FXCollections.observableArrayList();

            // Get selected radio button
            RadioButton selectedRadioButton = (RadioButton) filterCategory.getSelectedToggle();
            String filterType = selectedRadioButton.getText();

            for (Media media : cart.getItemsOrdered()) {
                if (filterType.equals("By ID")) {
                    if (String.valueOf(media.getId()).contains(filterText)) {
                        filteredList.add(media);
                    }
                } else if (filterType.equals("By Title")) {
                    if (media.getTitle().toLowerCase().contains(filterText.toLowerCase())) {
                        filteredList.add(media);
                    }
                }
            }

            tblMedia.setItems(filteredList);
        }
    }

    private void updateCostLabel() {
        if (cart != null) {
            costLabel.setText(String.format("%.2f $", cart.totalCost()));
        } else {
            costLabel.setText("0.00 $");
        }
    }

    void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }




    public CartController() {
        // Default constructor for FXML loader
    }

    private Cart cart;
    private Store store;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    public CartController(Cart cart) {
        this.cart = cart;
    }

    public void btnPlaceOrderPressed(ActionEvent actionEvent) {
        if (cart != null && !cart.getItemsOrdered().isEmpty()) {
            cart.emptyCart();
            tblMedia.setItems(cart.getItemsOrdered());
            updateCostLabel();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been placed successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Please add items before placing an order.");
            alert.showAndWait();
        }
    }
}
