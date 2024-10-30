/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blog.model;

/**
 * The PostNotFoundException class is a custom exception that is thrown when
 * a requested post cannot be found in the PostRepository.
 * 
 * @author marcelkuczek
 * @version 1.0
 */
public class PostNotFoundException extends Exception {
    
    /**
    * Constructs a new PostNotFoundException with the specified detail message.
    * 
    * @param message The message that describes the error condition.
    */
    public PostNotFoundException(String message) {
        super(message);
    }
}
