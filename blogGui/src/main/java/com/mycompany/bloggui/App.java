package com.mycompany.bloggui;

import blog.model.PostRepository; 
import blog.controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main application class for the Blog GUI.
 * @author marcelkuczek
 * @version 1.0
 */
public class App extends Application {

    private Scene scene;
    private PostRepository repository;

    /**
     * Initializes the PostRepository when the application starts.
     */
    @Override
    public void init() {
        repository = new PostRepository();
    }

    /**
     * Starts the application, loading the main MenuView and setting up the repository.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the MenuView FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MenuView.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();
        menuController.setRepository(repository);
        scene = new Scene(root, 1050, 500);
        scene.getStylesheets().add(getClass().getResource("/FXML/style.css").toExternalForm());
        stage.setTitle("Blog");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml the name of the FXML file to load (without extension)
     * @throws IOException if the specified FXML file cannot be loaded
     */
    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads the specified FXML file and returns the root node.
     *
     * @param fxml the name of the FXML file to load (without extension)
     * @return the root node of the loaded FXML file
     * @throws IOException if the specified FXML file cannot be loaded
     */
    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Returns the application's PostRepository.
     *
     * @return the PostRepository instance for this application
     */
    public PostRepository getRepository() {
        return repository;
    }

    /**
     * Main method to launch the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
