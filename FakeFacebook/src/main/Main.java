/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.HashMap;
import object.Post;
import object.User;

/**
 *
 * @author Hanh
 */
public class Main {
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
}
