/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package blog.controller;

import blog.model.Post;
import blog.model.PostRepository;
import blog.model.PostNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;

/**
 * Controller for deleting posts from the repository and managing the delete view.
 *
 * @version 1
 * @author marcelkuczek
 */
public class DeletePostController {

    private PostRepository repository;

    /**
     * Sets the repository for accessing post data and loads existing posts.
     *
     * @param repository the PostRepository instance
     */
    public void setRepository(PostRepository repository) {
        this.repository = repository;
        loadPosts();
    }

    @FXML
    private TextField titleField;

    @FXML
    private Button deletePostButton;

    @FXML
    private Button closeWindowButton;

    @FXML
    private TableView<Post> postsTableView;

    @FXML
    private TableColumn<Post, String> titleColumn;

    @FXML
    private TableColumn<Post, String> authorColumn;

    @FXML
    private TableColumn<Post, String> contentColumn;

    public DeletePostController() {}

    /**
     * Initializes the table columns and button tooltips.
     */
    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        contentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));

        deletePostButton.setTooltip(new Tooltip("Delete selected post (Alt+D)"));
        closeWindowButton.setTooltip(new Tooltip("Close window (Alt+C)"));

        deletePostButton.setAccessibleText("Delete selected post");
        closeWindowButton.setAccessibleText("Close window");

        deletePostButton.setMnemonicParsing(true);
        deletePostButton.setText("_Delete");
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
     * Deletes the selected post from the repository based on the title entered.
     */
    @FXML
    private void deletePost() {
        String titleToDelete = titleField.getText().trim();
        if (titleToDelete.isEmpty()) {
            showAlert("Please enter a title to delete.");
            return;
        }

       try {
        repository.deletePost(titleToDelete);
        returnToMenu();
    } catch (PostNotFoundException e) {
        showAlert(e.getMessage());
    }
    }

    /**
     * Cancels the delete operation and returns to the menu view.
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
