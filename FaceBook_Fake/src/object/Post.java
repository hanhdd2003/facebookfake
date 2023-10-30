/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import container.Manager;
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
    private HashMap<User, String> emotions = new HashMap<>();
    private int numberShare;
    private HashMap<User, ArrayList<String>> comments = new HashMap<>();

    public Post() {
    }

    public Post(String id, String type, Post p, String privacy, String date, User userPost) {
        this.dayPost = date;
        this.id = id;
        this.type = type;
        this.content = p.getFormattedPostString();
        this.privacy = privacy;
        this.userPost = userPost;
    }

    public Post(String id, String type, String content, String privacy, String date, User userPost) {
        this.dayPost = date;
        this.id = id;
        this.type = type;
        this.content = content;
        this.privacy = privacy;
        this.userPost = userPost;
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

    public HashMap<User, String> getEmotions() {
        return emotions;
    }

    public void setEmotion(User s, String emotion) {
        emotions.put(s, emotion);
    }

    public String getDayPost() {
        return dayPost;
    }

    public void setDayPost(String dayPost) {
        this.dayPost = dayPost;
    }

    public int getNumberEmotions() {
        return emotions.size();
    }

    public int getNumberShare() {
        return numberShare;
    }

    public int getNumberComment() {
        int count = 0;
        ArrayList<String> listComment = new ArrayList<>();
        for (User user : comments.keySet()) {
            listComment = comments.get(user);
            for (String s : listComment) {
                count++;
            }
        }
        return count;
    }

    public void share() {
        this.numberShare++;
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

    public HashMap<User, ArrayList<String>> getComments() {
        return comments;
    }
//--------------------------------------------------------------------------

    public void setComment(User user, String oldComment, String comment) {
        ArrayList<String> listComment = this.comments.get(user);
        if (listComment.contains(oldComment)) {
            listComment.set(this.getIndexComment(listComment, oldComment), comment);
        }
    }

    private int getIndexComment(ArrayList<String> listComment, String comment) {
        int count = 0;
        for (String string : listComment) {
            if (string.equalsIgnoreCase(comment)) {
                break;
            }
            count++;
        }
        return count;
    }

    public void addComment(User user, String comment) {
        if (this.comments.containsKey(user)) {
            this.comments.get(user).add(comment);
        } else {
            ArrayList<String> listcomment = new ArrayList<>();
            listcomment.add(comment);
            this.comments.put(user, listcomment);
        }
    }

    public void deleteComment(User user, String comment) {
        if (this.comments.containsKey(user) && this.comments.get(user).contains(comment)) {
            this.comments.get(user).remove(comment);
        }
    }

    // ==============================================
    public void addEmotion(User user, String emotion) {
        this.emotions.put(user, emotion);
    }

    public void deleteEmotion(User user) {
        this.emotions.remove(user);
    }

    public void display() {
        System.out.println("+---------------------------------------------------------------------+");
        System.out.printf("| %5s%30s%34s\n", "", userPost.getName(), "|");
        System.out.printf("| %-15s%-15s%-15s%-23s%-19s\n", "ID", "type", "privacy", "dayPost", "|");
        System.out.printf("| %-15s%-15s%-15s%-23s%-15s\n", id, type, privacy, dayPost, "|");
        System.out.printf("%-70s%-40s\n", "|", "|");
        System.out.printf("%-1s%-69s%-20s\n", "|", content, "|");
        System.out.printf("%-69s %-40s\n", "|", "|");
        System.out.printf("%-15s%-20s%-20s%-15s%-10s\n", "|", "Emotion", "Comment", "Share", "|");
        System.out.printf("%-15s%-20s%-20s%-15s%-10s\n", "|", this.getNumberEmotions(), this.getNumberComment(), numberShare, "|");
        System.out.println("+---------------------------------------------------------------------+");
    }

    public void displayEmotionDetail() {
        System.out.println("List emotion");
        String emotion;
        for (User u : emotions.keySet()) {
            emotion = emotions.get(u);
            System.out.println(u.getName() + ": " + emotion);
        }
    }

    public void displayCommentDetail() {
        ArrayList<String> listCMT = new ArrayList<>();
        for (User user : comments.keySet()) {
            listCMT = comments.get(user);
            System.out.println("---------------------------------");
            System.out.println(user.getName() + ": ");
            for (String string : listCMT) {
                System.out.println(string);
            }
            System.out.println("---------------------------------");

            System.out.println();
        }
    }


    public static void main(String[] args) {
        User u = new User("US1", "Hung", "Bac Ninh", "8/7/2003", "hung", "123");
        User u1 = new User("US2", "hanh", "hp", "2/11/2003", "hanh", "123");
        Post p = new Post("PO1", "Status", "Hung hut hit dang an com trong nha ve sinh", "Friend", "28/10/2023 10:13:52", u);

        u.addPost(p);

        Manager man = new Manager();

        p.display();
    }

    public String getFormattedPostString() {
        StringBuilder formattedPost = new StringBuilder();
        formattedPost.append("+---------------------------------------------------------------------+\n");
        formattedPost.append(String.format("| %5s%30s%34s\n", "", userPost.getName(), "|\n"));
        formattedPost.append(String.format("| %-15s%-15s%-15s%-23s%-19s\n", "ID", "type", "privacy", "dayPost", "|\n"));
        formattedPost.append(String.format("| %-15s%-15s%-15s%-23s%-15s\n", id, type, privacy, dayPost, "|\n"));
        formattedPost.append(String.format("%-70s%-40s\n", "|", "|\n"));
        formattedPost.append(String.format("%-1s%-69s%-20s\n", "|", content, "|\n"));
        formattedPost.append(String.format("%-69s %-40s\n", "|", "|\n"));
        formattedPost.append(String.format("%-15s%-20s%-20s%-15s%-10s\n", "|", "Emotion", "Comment", "Share", "|\n"));
        formattedPost.append(String.format("%-15s%-20s%-20s%-15s%-10s\n", "|", this.getNumberEmotions(), this.getNumberComment(), numberShare, "|\n"));
        formattedPost.append("+---------------------------------------------------------------------+");

        return formattedPost.toString();
    }

}
