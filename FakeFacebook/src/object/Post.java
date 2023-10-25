/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Hanh
 */
public class Post {
    private User userPost;
    private String id;
    private String type;
    private String content;
    private String privacy;
    private String dayPost;
    private HashMap<User, String> emotion = new HashMap<>();
    private int numberShare;
    private HashMap<User, ArrayList<String>> comment = new HashMap<>();

    public Post() {
    }

    public Post(String id,String type, String content, String privacy, String date) {
        this.dayPost = date;
        this.id = id;
        this.type = type;
        this.content = content;
        this.privacy = privacy;
    }
//---------------------------------------------------------------------
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public HashMap<User, String> getEmotion() {
        return emotion;
    }

    public void setEmotion(HashMap<User, String> emotion) {
        this.emotion = emotion;
    }

    public String getDayPost() {
        return dayPost;
    }

    public void setDayPost(String dayPost) {
        this.dayPost = dayPost;
    }
    
    public int getNumberEmotion() {
        return emotion.size();
    }

    public int getNumberShare() {
        return numberShare;
    }

    public void setNumberShare(int numberShare) {
        this.numberShare = numberShare;
    }

    public User getUserPost() {
        return userPost;
    }

    public void setUserPost(User userPost) {
        this.userPost = userPost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<User, ArrayList<String>> getComment() {
        return comment;
    }
//--------------------------------------------------------------------------
    public void setComment(User user,String oldComment, String comment) {
        ArrayList<String> listComment = this.comment.get(user);
        if(listComment.contains(oldComment)){
            listComment.set(this.getIndexComment(listComment, oldComment), comment);
        }
    }

    private int getIndexComment(ArrayList<String> listComment, String comment){
        int count = 0;
        for (String string : listComment) {
            if(string.equalsIgnoreCase(comment)){
                break;
            }
            count++;
        }
        return count;
    }
    
    public int getNumberComment(){
        int count = 0;
        ArrayList<String> listComment = new ArrayList<>();
        for(User user : comment.keySet()){
            listComment = comment.get(user);
            for(String s : listComment){
                count++;
            }
        }
        return count;
    }
    
    public void addComment(User user, String comment) {
        if (this.comment.containsKey(user)) {
            this.comment.get(user).add(comment);
        } else {
            ArrayList<String> listcomment = new ArrayList<>();
            listcomment.add(comment);
            this.comment.put(user, listcomment);
        }
    }

    public void deleteComment(User user, String comment){
        if (this.comment.containsKey(user)) {
            this.comment.get(user).remove(comment);
        }
    }
    
    public void addEmotion(User user, String emotion) {
        this.emotion.put(user, emotion);
    }
    
    public void deleteEmotion(User user){
        this.emotion.remove(user);
    }

    public void share() {
        this.numberShare++;
    }

    public void display(){
        System.out.println("+------------------------------------------------------------+");
        System.out.printf("| %5s%20s%20s\n","",userPost,"|");
        System.out.printf("| %-15s%-15s%-15s%-14s%-15s\n",id, type, privacy, dayPost,"|");
        System.out.printf("%-60s %-40s\n","|","|");
        System.out.printf("%-1s%-60s%-20s\n","|",content,"|");
        System.out.printf("%-60s %-40s\n","|","|");
        System.out.printf("%-5s%-20s%-20s%-16s%-10s\n","|",this.getNumberEmotion(),this.getNumberComment(),numberShare, "|");
        System.out.println("+------------------------------------------------------------+");
    }
    
    
}
