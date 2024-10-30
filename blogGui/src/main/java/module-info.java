module com.mycompany.blog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.bloggui to javafx.fxml;
    opens blog.controller to javafx.fxml;
    exports com.mycompany.bloggui;
    exports blog.controller;
    exports blog.model;
}
