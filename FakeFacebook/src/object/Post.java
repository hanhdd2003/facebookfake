/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public void setEmotion(HashMap<User, String> emotion) {
        this.emotions = emotion;
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
        if (this.comments.containsKey(user)) {
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

    public void share() {
        this.numberShare++;
    }

    public void display() {
        System.out.println("+------------------------------------------------------------+");
        System.out.printf("| %5s%20s%20s\n", "", userPost, "|");
        System.out.printf("| %-15s%-15s%-15s%-14s%-15s\n", id, type, privacy, dayPost, "|");
        System.out.printf("%-60s %-40s\n", "|", "|");
        System.out.printf("%-1s%-60s%-20s\n", "|", content, "|");
        System.out.printf("%-60s %-40s\n", "|", "|");
        System.out.printf("%-5s%-20s%-20s%-16s%-10s\n", "|", this.getNumberEmotions(), this.getNumberComment(), numberShare, "|");
        System.out.println("+------------------------------------------------------------+");
    }

    public void display1() {
        System.out.println("+-----------------------------------------------------+");
        System.out.printf("User ID: %-20s\n", userPost.getUserID());
        System.out.printf("User name: %-20s\n", userPost.getName());
        System.out.printf("ID: %-10s\n", this.id);
        System.out.printf("%-15s%-15s%-15s\n", "Type", "Privacy", "Day post");
        System.out.printf("%-15s%-15s%-15s\n", type, privacy, dayPost);
        System.out.printf("\n");
        System.out.printf("Content:\n");
        displayContent();
        System.out.printf("%-15s%-15s%-15s\n", "Total emotion", "Total comment", "Total share");
        System.out.printf("%-15d%-15d%-15d\n", this.getNumberEmotions(), this.getNumberComment(), this.getNumberShare());
        System.out.println("+-----------------------------------------------------+");
        System.out.println("");
    }

    public void displayContent() {
        String content = this.getContent();
        int chieuRongCuaDong = 80;

        String[] words = content.split(" ");
        StringBuilder vanBanXuongDong = new StringBuilder();
        StringBuilder dongHienTai = new StringBuilder();

        for (String word : words) {
            if (dongHienTai.length() + word.length() <= chieuRongCuaDong) {
                dongHienTai.append(word).append(" ");
            } else {
                //thêm dòng mới hiện tại vào vanBanXuongDong
                vanBanXuongDong.append(dongHienTai.toString().trim()).append("\n");

                //reset dongHienTai
                dongHienTai.setLength(0);
                dongHienTai.append(word).append(" ");
            }
        }

        //kiểm tra xem dòng hiện tại vẫn chứa từ
        if (dongHienTai.length() > 0) {
            vanBanXuongDong.append(dongHienTai.toString().trim());
        }

        System.out.println(vanBanXuongDong.toString());
        System.out.println("");
    }

    public void displayListEmotion() {
        if (!emotions.isEmpty()) {
            
            HashMap<String, Integer> emotionCounts = new HashMap<>();
            
            for (String emotion : emotions.values()) {
                emotionCounts.put(emotion, emotionCounts.getOrDefault(emotion, 0) + 1);
            }
            
            emotionCounts.forEach((emotion, count) -> {
                System.out.println(emotion + " " + count);
            });
            
            /*
            C2:
            emotions.entrySet().stream()
                    .collect(Collectors.groupingBy(Entry::getValue, Collectors.counting()))
                    .forEach((emotion, count) -> {
                        System.out.println(emotion + " " + count);
                    });
            */
            
        } 
        else {
            System.out.println("No emotion");
        }
    }
    
    public void displayListEmotionDetail() {
        if (!emotions.isEmpty()) {
            emotions.forEach((user, emotion) -> {
                System.out.println(user.getName() + " express " + emotion);
            });
        }
        else {
            System.out.println("No emotion");
        }
    }
}
