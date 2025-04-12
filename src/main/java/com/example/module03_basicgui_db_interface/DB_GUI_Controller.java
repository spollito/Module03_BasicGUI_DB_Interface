package com.example.module03_basicgui_db_interface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.example.module03_basicgui_db_interface.db.ConnDbOps;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class DB_GUI_Controller implements Initializable {
    private final ConnDbOps dbOps = new ConnDbOps();
    private final ObservableList<Person> data = FXCollections.observableArrayList();

    @FXML
    private ImageView profileImageView;


    @FXML
    private TextField nameField, emailField, phoneField, addressField, passwordField;

    @FXML
    private TableView<Person> tv;

    @FXML
    private TableColumn<Person, Integer> tv_id;

    @FXML
    private TableColumn<Person, String> tv_name, tv_email, tv_phone, tv_address, tv_password;


    private boolean isDarkMode = false;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tv_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tv_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tv_password.setCellValueFactory(new PropertyValueFactory<>("password"));



        data.addAll(dbOps.getAllPersons());
        tv.setItems(data);

        applyTheme(false);

    }

    @FXML
    private void toggleTheme() {
        isDarkMode = !isDarkMode;  // Toggle the theme state
        applyTheme(isDarkMode);
    }

    private void applyTheme(boolean darkMode) {
        Scene scene = tv.getScene();  // Get scene from any FXML element
        if (scene != null) {
            scene.getStylesheets().clear();  // Clear existing stylesheets

            String themePath = darkMode ? "styling/dark-theme.css" : "styling/light-theme.css";
            URL resourceUrl = getClass().getResource(themePath);

            if (resourceUrl == null) {
                System.err.println("Theme file not found: " + themePath);
                return;
            }

            try {
                scene.getStylesheets().add(resourceUrl.toExternalForm());
            } catch (Exception e) {
                System.err.println("Failed to load theme: " + e.getMessage());
            }
        }
    }



    @FXML
    protected void addNewRecord() {
        Person newPerson = new Person(
                null,
                nameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                passwordField.getText()
        );

        dbOps.insertPerson(newPerson);
        data.clear();
        data.addAll(dbOps.getAllPersons());
        clearForm();
    }

    @FXML
    protected void clearForm() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        passwordField.clear();
    }

    @FXML
    protected void editRecord() {
        Person selectedPerson = tv.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            Person updatedPerson = new Person(
                    selectedPerson.getId(),
                    nameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    addressField.getText(),
                    passwordField.getText()
            );

            dbOps.updatePerson(updatedPerson);
            data.clear();
            data.addAll(dbOps.getAllPersons());
        }
    }

    @FXML
    protected void deleteRecord() {
        Person selectedPerson = tv.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            dbOps.deletePerson(selectedPerson.getId());
            data.remove(selectedPerson);
        }
    }

    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        Person p = tv.getSelectionModel().getSelectedItem();
        if (p != null) {
            nameField.setText(p.getName());
            emailField.setText(p.getEmail());
            phoneField.setText(p.getPhone());
            addressField.setText(p.getAddress());
            passwordField.setText(p.getPassword());
        }
    }

    @FXML
    protected void closeApplication() {
        System.exit(0);
    }
}