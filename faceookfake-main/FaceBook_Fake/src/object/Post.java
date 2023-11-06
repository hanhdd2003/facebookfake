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
    private HashMap<User, String> emotions = new HashMap<>();
    private int numberShare;
    private ArrayList<Comment> comments = new ArrayList<>();
    private Post sharedPost;

    public Post() {
    }

    public Post(String id, String type, Post p, String privacy, String date, User userPost) {
        this.dayPost = date;
        this.id = id;
        this.type = type;
        this.sharedPost = p;
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
        this.sharedPost = null;
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
        return this.comments.size();
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

    public ArrayList<Comment> getComments() {
        return comments;
    }
//--------------------------------------------------------------------------

    public void setComment(int index, String comment) {
        this.comments.get(index).setContent(comment);
    }


    public void addComment(User user, Comment c) {
        this.comments.add(c);
    }

    public void deleteComment(Comment comment) {
        this.comments.remove(comment);
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
        if (this.sharedPost == null) {
            System.out.printf("%-1s%-69s%-20s\n", "|", content, "|");
        } else {
            this.sharedPost.displaySharedPost();
        }
        System.out.printf("%-69s %-40s\n", "|", "|");
        System.out.printf("%-15s%-20s%-20s%-15s%-10s\n", "|", "Emotion", "Comment", "Share", "|");
        System.out.printf("%-15s%-20s%-20s%-15s%-10s\n", "|", this.getNumberEmotions(), this.getNumberComment(), numberShare, "|");
        System.out.println("+---------------------------------------------------------------------+");
    }
    
    private void displaySharedPost() {
        System.out.println("+---------------------------------------------------------------------+");
        System.out.printf("| %5s%30s%34s\n", "", userPost.getName(), "|");
        System.out.printf("| %-15s%-15s%-15s%-23s%-19s\n", "ID", "type", "privacy", "dayPost", "|");
        System.out.printf("| %-15s%-15s%-15s%-23s%-15s\n", id, type, privacy, dayPost, "|");
        System.out.printf("%-70s%-40s\n", "|", "|");
        if (this.sharedPost == null) {
            System.out.printf("%-1s%-69s%-20s\n", "|", content, "|");
        } else {
            this.sharedPost.displaySharedPost();
        }
        System.out.printf("%-69s %-40s\n", "|", "|");
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
        this.comments.stream().forEach(c ->  {
            System.out.print(c.getOwner().getName() + ": " + c.getContent() + "    " + c.getTimeAgo());
            if (c.isEdited()) {
                System.out.print("(edited)");
            }
            System.out.println("");
                });
    }

}