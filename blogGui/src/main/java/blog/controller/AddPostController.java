/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package blog.controller;

import blog.model.Post;
import blog.model.PostRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the Add Post view, handling post creation and adding it to the repository.
 *
 * @version 1
 * @author marcelkuczek
 */
public class AddPostController {

    private MenuController menuController;
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

    /**
     * Sets the MenuController reference to enable post refresh in the main menu.
     *
     * @param menuController the MenuController instance
     */
    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @FXML
    private Button addPostButton;

    @FXML
    private Button closeWindowButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextArea contentArea;

    @FXML
    private TableView<Post> postsTableView;
    @FXML
    private TableColumn<Post, String> titleColumn;
    @FXML
    private TableColumn<Post, String> authorColumn;
    @FXML
    private TableColumn<Post, String> contentColumn;

    public AddPostController() {}

    /**
     * Initializes the table columns, button tooltips, accessibility texts, and mnemonics.
     */
    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        contentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
        
        addPostButton.setTooltip(new Tooltip("Add post (Alt+A)"));
        closeWindowButton.setTooltip(new Tooltip("Close window (Alt+C)"));
        
        addPostButton.setAccessibleText("Add post");
        closeWindowButton.setAccessibleText("Close window");
        
        addPostButton.setMnemonicParsing(true);
        addPostButton.setText("_Add");
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
     * Adds a new post to the repository and returns to the menu view if all fields are filled.
     * Shows an alert if any field is left empty.
     */
    @FXML
    private void addPost() {
        String title = titleField.getText();
        String author = authorField.getText();
        String content = contentArea.getText();

        if (!title.isEmpty() && !author.isEmpty() && !content.isEmpty()) {
            Post newPost = new Post(title, author, content);
            repository.addPost(newPost);
            showAlert("Post added successfully.");
            menuController.refreshPosts();
            returnToMenu();
        } else {
            showAlert("All fields must be filled.");
        }
    }

    /**
     * Cancels the post addition and returns to the menu view.
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
