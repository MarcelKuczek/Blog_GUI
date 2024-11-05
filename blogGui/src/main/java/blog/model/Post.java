/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package blog.model;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
/**
 * The Post class represents a blog post with a title, author, and content.
 * 
 * @author marcelkuczek
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class Post {
    private String title;   
    private String author;  
    private String content; 

    /**
     * Returns a string representation of the post.
     * 
     * @return A string containing the title, author, and content of the post
     */
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nContent: " + content + "\n";
    }
}