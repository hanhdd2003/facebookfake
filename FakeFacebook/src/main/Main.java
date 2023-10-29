/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import container.Manager;
import java.util.ArrayList;
import java.util.HashMap;
import object.Post;
import object.User;

/**
 *
 * @author Hanh
 */
public class Main {

    private static final Manager man = new Manager();

    public static void main(String[] args) {

        man.initUser();
        man.initFriend();
        man.initPost();
        int choice;
        while (true) {
            System.out.println("");
            choice = man.menu();
            System.out.println("");
            switch (choice) {
                case 1:
                    man.addUser();
                    break;
                case 2:
                    man.deleteUser();
                    break;
                case 3:
                    man.displayUser();
                    break;
                case 4:
                    man.displayAllPost();
                    break;
                case 5:
                    man.displayPostOfUser();
                    break;
                case 6:
                    man.displayFriends();
                    break;
                case 7:
                    man.checkFriend();
                    break;
                case 8:
                    man.addFriend();
                    break;
                case 9:
                    man.deleteFriend();
                    break;
                case 10:
                    String userId = man.loginUser();
                    if (userId != null) {
                        menuLogin(userId);
                    }
                    break;
                case 11:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void menuLogin(String userId) {
        int choice;
        while (true) {
            System.out.println("");
            choice = man.menuLogin();
            switch (choice) {
                case 1:
                    man.addFriendByUser(userId);
                    break;
                case 2:
                    man.deleteFriendByUser(userId);
                    break;
                case 3:
                    man.displayFriendsByUser(userId);
                    break;
                case 4:
                    man.addPostByUser(userId);
                    break;
                case 5:
                    man.editPostByUser(userId);
                    break;
                case 6:
                    man.deletePostByUser(userId);
                    break;
                case 7:
                    man.displayPostByUser(userId);
                    break;
                case 8:
                    String postId = man.choicePostByUser(userId);
                    if (postId != null) {
                        menuPost(userId, postId);
                    }
                    break;
                case 9:
                    man.deleteUserByUser(userId);
                    return;
                case 10:
                    return;
            }
        }
    }

    public static void menuPost(String userId, String postId) {
        int choice;
        while (true) {
            System.out.println("");
            choice = man.menuPost();
            switch (choice) {
                case 1:
                    man.displayCommentOfPost(postId);
                    break;
                case 2:
                    man.addCommentInPost(userId, postId);
                    break;
                case 3:
                    man.editCommnentInPost(userId, postId);
                    break;
                case 4:
                    man.deleteCommentInPost(userId, postId);
                    break;
                case 5:
                    man.addEmotionByUser(userId, postId);
                    break;
                case 6:
                    man.deleteEmotionByUser(userId, postId);
                    break;
                case 7:
                    man.sharePostWithFriend(userId, postId);
                    break;
                case 8:
                    man.sharePostWithAllFriends(userId, postId);
                    break;
                case 9:
                    man.sharePostWithAllUsers(userId, postId);
                    break;
                case 10:
                    return;
            }
        }
    }

    /*
    public static void main(String[] args) {
        Post p = new Post("p1","Status", "Hello, this is content of post by user hanh", "Friend","24/11/2023");
        User hanh = new User("h1", "Hanh","Hải Phòng", "2/11/2003");
        User duc = new User("h2", "Đức Lỏ", "Hà Nội", "8/11/2003");
        p.setUserPost(hanh);
        hanh.addEmotion(p, "Haha");
        duc.addEmotion(p, "Sad");
        duc.addComment(p, "ok");
        duc.addComment(p, "ok");
        duc.addComment(p, "ok");
        duc.share(p);
        duc.share(p);
        
        p.display();
        HashMap<Post, ArrayList<String>> comment = duc.getCommented();
        ArrayList<String> listComment;
        for(Post p1 : comment.keySet()){
            listComment = comment.get(p1);
            for (String string : listComment) {
                System.out.println(string);
            }
        }
        System.out.println("----------");
        
        duc.editComment(p, "ok", "kkk");
        HashMap<Post, ArrayList<String>> commentt = duc.getCommented();
        ArrayList<String> listCommentt;
        for(Post p1 : commentt.keySet()){
            listCommentt = comment.get(p1);
            for (String string : listCommentt) {
                System.out.println(string);
            }
        }
        
        duc.deleteComment(p, "kkk");
        p.display();
        HashMap<Post, ArrayList<String>> commenttt = duc.getCommented();
        ArrayList<String> listCommenttt;
        for(Post p1 : commenttt.keySet()){
            listCommenttt = comment.get(p1);
            for (String string : listCommenttt) {
                System.out.println(string);
            }
        }
    }
     */
}
