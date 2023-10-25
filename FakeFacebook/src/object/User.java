/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import java.util.HashMap;
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
    private final ArrayList<Post> post = new ArrayList<>();
    private final HashMap<Post, ArrayList<String>> commented = new HashMap<>();
    private final HashMap<Post, String> emotion = new HashMap<>();
    private final HashMap<Post, Integer> amountShared = new HashMap<>();
    
    public User() {
    }

    public User(String id ,String name, String address, String dateOfBirth) {
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

    public ArrayList<Post> getPost() {
        return post;
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
    public void addPost(Post p){
        for (Post post1 : post) {
            if(post1.getId().equalsIgnoreCase(p.getId())){
                return;
            }
        }
        this.post.add(p);
    }
    
    public void editPost(Post oldP, String content, String privacy, String day){
        for (Post post1 : post) {
            if(post1.getId().equalsIgnoreCase(oldP.getId())){
                post1.setContent(content);
                post1.setDayPost(day);
                post1.setPrivacy(privacy);
            }
        }
    }
    
    public void deletePost(Post oldP){
        this.post.remove(oldP);
    }
//============================================================
    public void addComment(Post p, String comment){
        p.addComment(this, comment);
        if(commented.containsKey(p)){
            commented.get(p).add(comment);
        }else{
            ArrayList<String> list = new ArrayList<>();
            list.add(comment);
            commented.put(p, list);
        }
    }
    public void editComment(Post p, String oldComment, String newComment){
        p.setComment(this, oldComment, newComment);
        if(commented.containsKey(p) && commented.get(p).contains(oldComment)){
            commented.get(p).remove(oldComment);
            commented.get(p).add(newComment);
        }
    } 
    public void deleteComment(Post p, String comment){
        p.deleteComment(this, comment);
        if(commented.containsKey(p) ){
            commented.get(p).remove(comment);
        }
    }
 //============================================================
    public void addEmotion(Post p, String emotion){
        p.addEmotion(this, emotion);
        if(this.emotion.containsKey(p)){
            this.emotion.put(p, emotion);
        }
    }
    public void deleteEmotion(Post p){
        p.deleteEmotion(this);
        this.emotion.remove(p);
    }
 //==============================================
    public void share(Post p){
        p.share();
    }

    @Override
    public String toString() {
        return "[" + userID + " - " + name + " - " + address + " - " + dateOfBirth + ']';
    }

}
