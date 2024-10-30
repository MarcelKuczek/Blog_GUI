/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package blog.controller;

import blog.model.Post;
import blog.model.PostRepository;
import javafx.fxml.FXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;

/**
 * Controller for the main menu, managing post display, addition, deletion, and modification actions.
 *
 * @version 1
 * @author marcelkuczek
 */
public class MenuController {
    
    private PostRepository repository;

    /**
     * Sets the repository for post data and initializes the table view with posts.
     *
     * @param repository the PostRepository instance
     */
    public void setRepository(PostRepository repository) {
        this.repository = repository;
        loadPosts();
    }

    @FXML
    private TableView<Post> postsTableView;
    @FXML
    private TableColumn<Post, String> titleColumn;
    @FXML
    private TableColumn<Post, String> authorColumn;
    @FXML
    private TableColumn<Post, String> contentColumn;
    @FXML
    private Button addPostButton;
    @FXML
    private Button deletePostButton;
    @FXML
    private Button modifyPostButton;
    @FXML
    private Button exitAppButton;

    /**
     * Initializes the table columns, button tooltips, accessibility texts, and mnemonics.
     */
    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        contentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));
        
        addPostButton.setTooltip(new Tooltip("Add a new post (Alt+A)"));
        deletePostButton.setTooltip(new Tooltip("Delete selected post (Alt+D)"));
        modifyPostButton.setTooltip(new Tooltip("Modify selected post (Alt+M)"));
        exitAppButton.setTooltip(new Tooltip("Exit application (Alt+E)"));

        addPostButton.setAccessibleText("Add a new post");
        deletePostButton.setAccessibleText("Delete selected post");
        modifyPostButton.setAccessibleText("Modify selected post");
        exitAppButton.setAccessibleText("Exit application");

        addPostButton.setMnemonicParsing(true);
        addPostButton.setText("_Add");
        deletePostButton.setMnemonicParsing(true);
        deletePostButton.setText("_Delete");
        modifyPostButton.setMnemonicParsing(true);
        modifyPostButton.setText("_Modify");
        exitAppButton.setMnemonicParsing(true);
        exitAppButton.setText("_Exit");
    }

    /**
     * Refreshes the posts displayed in the table view by reloading from the repository.
     */
    public void refreshPosts() {
        loadPosts();
    }

    /**
     * Loads posts from the repository into the table view.
     */
    private void loadPosts() {
        if (repository != null) {
            List<Post> posts = repository.getallPosts();
            postsTableView.getItems().clear();
            postsTableView.getItems().addAll(posts);
        } else {
            showAlert("Repository is not initialized.");
        }
    }

    /**
     * Opens the Add Post view.
     *
     * @param event the ActionEvent triggered by the Add Post button
     */
    @FXML
    private void addPost(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AddPostView.fxml"));
            Parent root = loader.load();

            AddPostController addPostController = loader.getController();
            addPostController.setRepository(this.repository);
            addPostController.setMenuController(this);

            Scene scene = ((javafx.scene.Node) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error opening the post addition form.");
        }
    }

    /**
     * Opens the Delete Post view.
     *
     * @param event the ActionEvent triggered by the Delete Post button
     */
    @FXML
    private void deletePost(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DeletePostView.fxml"));
            Parent root = loader.load();

            DeletePostController deletePostController = loader.getController();
            deletePostController.setRepository(this.repository);

            Scene scene = ((javafx.scene.Node) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error opening the delete post form.");
        }
    }

    /**
     * Opens the Modify Post view.
     *
     * @param event the ActionEvent triggered by the Modify Post button
     */
    @FXML
    private void modifyPost(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifyPostView.fxml"));
            Parent root = loader.load();

            ModifyPostController modifyPostController = loader.getController();
            modifyPostController.setRepository(this.repository);

            Scene scene = ((javafx.scene.Node) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error opening the modify post form.");
        }
    }

    /**
     * Exits the application.
     *
     * @param event the ActionEvent triggered by the Exit button
     */
    @FXML
    private void exitApp(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
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
