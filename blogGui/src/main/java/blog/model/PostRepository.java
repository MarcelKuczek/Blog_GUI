/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package blog.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The PostRepository class is responsible for managing a collection of blog posts.
 * It provides methods to add, delete, modify, and retrieve posts by author.
 * 
 * @author marcelkuczek
 * @version 1.0
 */
public class PostRepository {

    private final List<Post> posts;

    /**
     * Constructor for the PostRepository class. Initializes the repository with some default posts.
     */
    public PostRepository() {
        posts = new ArrayList<>();
        posts.add(new Post("Pierwszy post", "Marcel Kuczek", "Kuczek pierwszy post"));
        posts.add(new Post("Drugi post", "Jan Kowalski", "Jan Kowalski pierwszy post."));
        posts.add(new Post("Czwarty post", "Marcel Kuczek", "Kolejny post Marcel Kuczek."));
    }
    
    /**
     * Retrieves a list of posts by a selecteed author.
     * 
     * @param author The author's name to filter posts
     * @return A list of posts written by the selected author
     */
    public List<Post> getPostsByAuthor(String author) {

        return posts.stream()
                .filter(post -> post.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a list of posts.
     * 
     * 
     * @return A list of posts
     */
    public List<Post> getallPosts() {
        return posts.stream()
                .collect(Collectors.toList());
    }
    
    /**
     * Adds a new post to the repository.
     * 
     * @param post The Post object to add
     */
    public void addPost(Post post) {
        posts.add(post);
    }
    
    /**
     * Deletes a post from the repository by its title.
     * 
     * @param postTitleToDelete The title of the post to delete
     */
    public void deletePost(String postTitleToDelete) throws PostNotFoundException {
        Post postToRemove = posts.stream()
            .filter(post -> post.getTitle().equalsIgnoreCase(postTitleToDelete)) 
            .findFirst() 
            .orElseThrow(() -> new PostNotFoundException("Post '" + postTitleToDelete + "' not found."));

        posts.remove(postToRemove); 
    }

    public enum ModificationType {
        TITLE, CONTENT
    }
    
    /**
     * Modifies the title of an post.
     * 
     * @param postTitleToModify The current title of the post to modify
     */
    public void modifyPost(String postTitleToModify, String newValue, ModificationType type) throws PostNotFoundException {
        Post postToModify = null;
        for (Post post : posts) {
            if (post.getTitle().equalsIgnoreCase(postTitleToModify)) {
                postToModify = post;
                break;
            }
        }
        if (postToModify != null) {
            if (type == ModificationType.TITLE) {
                postToModify.setTitle(newValue);
            } else if (type == ModificationType.CONTENT) {
                postToModify.setContent(newValue);
            }
        } else {
            throw new PostNotFoundException("Post '" + postTitleToModify + "' not found.");
        }
    }
}