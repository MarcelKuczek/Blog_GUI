/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package blog.controller;

import blog.model.Post;
import blog.model.PostNotFoundException;
import blog.model.PostRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

/**
 * Controller for modifying existing posts in the repository.
 *
 * @version 1
 * @author marcelkuczek
 */
public class ModifyPostController {

    private PostRepository repository;

    @FXML
    private Button modifyPostButton;

    @FXML
    private Button closeWindowButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField newTitleTextField;

    @FXML
    private TextArea newContentTextArea;

    @FXML
    private TableView<Post> postsTableView; 

    @FXML
    private TableColumn<Post, String> titleColumn;

    @FXML
    private TableColumn<Post, String> authorColumn;

    @FXML
    private TableColumn<Post, String> contentColumn;

    /**
     * Sets the repository for accessing post data.
     *
     * @param repository the PostRepository instance
     */
    public void setRepository(PostRepository repository) {
        this.repository = repository;
        loadPosts();
    }

    /**
     * Initializes the table columns and button tooltips.
     */
    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        contentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));

        modifyPostButton.setTooltip(new Tooltip("Modify selected post (Alt+D)"));
        closeWindowButton.setTooltip(new Tooltip("Close window (Alt+C)"));

        modifyPostButton.setAccessibleText("Modify selected post");
        closeWindowButton.setAccessibleText("Close window");

        modifyPostButton.setMnemonicParsing(true);
        modifyPostButton.setText("_Modify");
        closeWindowButton.setMnemonicParsing(true);
        closeWindowButton.setText("_Close");
    }

    /**
     * Loads posts from the repository into the table view.
     */
    private void loadPosts() {
        List<Post> posts = repository.getallPosts(); 
        postsTableView.getItems().clear(); 
        postsTableView.getItems().addAll(posts);
    }

    /**
     * Modifies the selected post based on the provided new title and content.
     */
    @FXML
    private void modifyPost() {
        String currentTitle = titleField.getText().trim();
        String newTitle = newTitleTextField.getText().trim();
        String newContent = newContentTextArea.getText().trim();

        if (currentTitle.isEmpty()) {
            showAlert("Please enter the current title of the post to modify.");
            return;
        }

        if (!newTitle.isEmpty() && !newContent.isEmpty()) {
           
            try{
            repository.modifyTitle(currentTitle, newTitle);
            repository.modifyContent(newTitle, newContent);
            showAlert("Post updated successfully.");
            } catch (PostNotFoundException e) {
                showAlert(e.getMessage());}
            
        } else if (!newTitle.isEmpty()) {
            try{
            repository.modifyTitle(currentTitle, newTitle);
            } catch (PostNotFoundException e) {
                showAlert(e.getMessage());}
            showAlert("Post's title updated successfully.");
        } else if (!newContent.isEmpty()) {
            try {
            repository.modifyContent(newTitle, newContent);
            } catch (PostNotFoundException e) {
                showAlert(e.getMessage());}
            showAlert("Post's content updated successfully.");
        } else {
            showAlert("No changes were made.");
        }
        returnToMenu();
    }

    /**
     * Cancels the modification operation and returns to the menu view.
     */
    @FXML
    private void cancel() {
        returnToMenu();
    }

    /**
     * Returns to the main menu view, reloading the post list from the repository.
     */
    private void returnToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuView.fxml"));
            Parent root = loader.load();

            MenuController menuController = loader.getController();
            menuController.setRepository(repository);
            menuController.refreshPosts();

            Scene scene = titleField.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error loading the menu view.");
        }
    }

    /**
     * Displays an alert with the specified message.
     *
     * @param message the message to display in the alert
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}
