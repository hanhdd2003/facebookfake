/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Hanh
 */
public class User {

    private String userID;
    private String name;
    private String address;
    private String dateOfBirth;
    private final ArrayList<Post> posts = new ArrayList<>();
    private final HashMap<Post, ArrayList<String>> commented = new HashMap<>();
    private final HashMap<Post, String> emotion = new HashMap<>();
    private final HashMap<Post, Integer> amountShared = new HashMap<>();

    public User() {
    }

    public User(String id, String name, String address, String dateOfBirth) {
        this.userID = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
//==================================================================================

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public HashMap<Post, ArrayList<String>> getCommented() {
        return commented;
    }

    public HashMap<Post, String> getEmotion() {
        return emotion;
    }

    public HashMap<Post, Integer> getAmountShared() {
        return amountShared;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//=======================================================================
    public boolean addPost(Post post) {
        for (Post existingPost : posts) {
            if (existingPost != null && existingPost.getId() != null && existingPost.getId().equals(post.getId())) {
                return false; 
            }
        }
        
        this.posts.add(post);
        return true;
    }

    public boolean addPost(String postId, String postType, String postContent, String postPrivacy, String postDate) {
        for (Post post1 : posts) {
            if (post1 != null && post1.getId() != null && post1.getId().equalsIgnoreCase(postId)) {
                return false;
            }
        }
        Post newPost = new Post(postId, postType, postContent, postPrivacy, postDate, this);
        this.posts.add(newPost);
        return true;
    }

    public void editPost(Post oldP, String type, String content, String privacy, String day) {
        for (Post post1 : posts) {
            if (post1.getId().equalsIgnoreCase(oldP.getId())) {
                post1.setType(type);
                post1.setContent(content);
                post1.setDayPost(day);
                post1.setPrivacy(privacy);
            }
        }
    }

    public boolean deletePost(String oldPostId) {
        Post port = this.getPost(oldPostId);
        if (port != null) {
            this.posts.remove(port);
            return true;
        }
        return false;
    }

    public Post getPost(String id) {
        for (Post post1 : posts) {
            if (post1 != null && post1.getId() != null && post1.getId().equalsIgnoreCase(id)) {
                return post1;
            }
        }
        return null;
    }
//============================================================

    public void addComment(Post p, String comment) {
        p.addComment(this, comment);
        if (commented.containsKey(p)) {
            commented.get(p).add(comment);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(comment);
            commented.put(p, list);
        }
    }

    public void editComment(Post p, String oldComment, String newComment) {
        p.setComment(this, oldComment, newComment);
        
        if (commented.containsKey(p) && commented.get(p).contains(oldComment)) {
            commented.get(p).remove(oldComment);
            commented.get(p).add(newComment);
        }
    }

    public boolean deleteComment(String postId, String comment) {
        Post port = this.getPost(postId);
        if (port != null) {
            port.deleteComment(this, comment);
            if (commented.containsKey(port) && commented.get(port).contains(comment)) {
                commented.get(port).remove(comment);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
    
    public void viewAllComment() {
        for (Map.Entry<Post, ArrayList<String>> entry : commented.entrySet()) {
            Post post = entry.getKey();
            ArrayList<String> comments = entry.getValue();
            for (String comment : comments) {
                if (post.getUserPost().getUserID().equalsIgnoreCase(this.getUserID()))
                    System.out.println(post.getId() + ": " + comment);
            }
        }
        
        if (commented.isEmpty()) 
            System.out.println("User has no comment");
    }
    //============================================================

    public void addEmotion(Post p, String emotion) {
        p.addEmotion(this, emotion);
        if (this.emotion.containsKey(p)) {
            this.emotion.put(p, emotion);
        }
    }

    public void deleteEmotion(Post p) {
        p.deleteEmotion(this);
        this.emotion.remove(p);
    }
    //==============================================

    public void share(Post p) {
        p.share();
    }

//    public void displayUser() {
//        System.out.printf("%-5s%-20s%-15s%-20s\n", this.userID, this.name, this.address, this.dateOfBirth);
//    }
    @Override
    public String toString() {
        return String.format("%-5s%-20s%-15s%-20s\n", this.userID, this.name, this.address, this.dateOfBirth);
    }

}
