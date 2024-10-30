/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package blog.model;

/**
 * The Post class represents a blog post with a title, author, and content.
 * 
 * @author marcelkuczek
 * @version 1.0
 */
public class Post {
    private String title;   
    private String author;  
    private String content; 
    
    /**
     * Constructor for the Post class.
     * 
     * @param title   The title of the post
     * @param author  The author of the post
     * @param content The content of the post
     */
    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    /**
     * Gets the author of the post.
     * 
     * @return The author of the post
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Gets the title of the post.
     * 
     * @return The title of the post
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Gets the content of the post.
     * 
     * @return The content of the post
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Sets a new title for the post.
     * 
     * @param newTitle The new title to set
     */
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    
    /**
     * Sets new content for the post.
     * 
     * @param newContent The new content to set
     */
    public void setContent(String newContent) {
        content = newContent;
    }

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