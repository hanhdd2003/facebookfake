/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package container;

import controller.Controller;
import controller.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import object.Post;
import object.User;

/**
 *
 * @author Hanh
 */
public class Manager {

    private final Validate val = new Validate();
    private final Controller con = new Controller();
    private int countShare = 0;

    //khởi tạo ===============================================
    public void initUser() {
        con.addUser("US1", "Hung", "Bac Ninh", "8/7/2003", "hung", "123");
        con.addUser("US2", "Hanh", "Hai Phong", "3/3/2003", "hanh", "123");
        con.addUser("US3", "Thuy", "Vinh Phuc", "2/8/2003", "thuy", "123");
        con.addUser("US4", "Chung", "Tau", "1/11/2003", "chung", "123");
        con.addUser("US5", "Thang", "Lao Cai", "1/11/2003", "thang", "123");
    }

    public void initFriend() {
        con.addFriendById("US1", "US2");
        con.addFriendById("US1", "US3");
        con.addFriendById("US2", "US3");
        con.addFriendById("US2", "US5");
        con.addFriendById("US3", "US4");
    }

    public void initPost() {
        User user1 = con.getUserById("US1");
        User user2 = con.getUserById("US2");
        Post p1 = new Post("PO1", "Status", "Hung hut hit dang an com trong nha ve sinh", "Friend", "28/10/2023  10:13:52", user1);
        Post p2 = new Post("PO2", "Status", "Hung hut", "Public", "28/10/2023 10:13:52", user1);
        user1.addPost(p1);
        user1.addPost(p2);
        this.sharePostWithAllFriends(user2, p1);
    }

    public void displayUser() {
        System.out.printf("%-5s%-20s%-15s%-20s\n", "ID", "Name", "Address", "Date Of Birth");
        for (Vertex<User> vertice : con.getListUser().getVertices()) {
            System.out.print(vertice.getLabel().toString());
        }

    }

    public void init() {
        User user1 = new User("US1", "Hung", "Bac Ninh", "8/7/2003", "hung", "123");
        User user2 = new User("US2", "Hanh", "Hai Phong", "3/3/2003", "hanh", "123");
        User user3 = new User("US3", "Thuy", "Vinh Phuc", "2/8/2003", "thuy", "123");
        User user4 = new User("US4", "Chung", "Tau", "1/11/2003", "chung", "123");
        User user5 = new User("US5", "Thang", "Lao Cai", "1/11/2003", "thang", "123");
        Post p1 = new Post("PO1", "Status", "Đây là bài viết của Hùng", "Friend", "28/10/2023  10:13:52", user1);
        Post p2 = new Post("PO2", "Status", "Đây là bài viết của Hanh", "Public", "29/10/2023 12:13:52", user2);
        Post p3 = new Post("PO3", "Story", "Đây là bài viết của Thủy", "private", "30/10/2023 10:15:52", user3);
        user1.addPost(p1);
        user2.addPost(p2);
        user3.addPost(p3);
        con.addUser(user1);
        con.addUser(user2);
        con.addUser(user3);
        con.addUser(user4);
        con.addUser(user5);
        con.addFriend(user1, user2);
        con.addFriend(user1, user3);
        con.addFriend(user2, user3);
        con.addFriend(user2, user5);
        con.addFriend(user3, user4);
    }

    public void sharePostWithAllFriends(User user, Post postShare) {
        if (postShare == null) {
            System.out.println("Post not found.");
            return;
        }
        countShare++;
        postShare.share();
        Post newPost = new Post("S" + countShare + postShare.getId(), postShare.getType(), postShare, "Friend", val.getDateNow(), user);
        user.addPost(newPost);
        System.out.println("Share successful");
        user.addNotification("you shared post " + postShare.getId() + " (" + val.getDateNow() + ")");

    }

    public void sharePostWithAllUsers(User user, Post postShare) {
        if (postShare == null) {
            System.out.println("Post not found.");
            return;
        }
        countShare++;
        postShare.share();
        Post newPost = new Post("S" + countShare + postShare.getId(), postShare.getType(), postShare, "Public", val.getDateNow(), user);
        user.addPost(newPost);
        System.out.println("Share successful");
        user.addNotification("you shared post " + postShare.getId() + " (" + val.getDateNow() + ")");
    }

    public void displayCommentOfPost(String postId) {
        Post post = null;
        for (Vertex<User> vertex : con.getListUser().getVertices()) {
            User user = vertex.getLabel();
            post = user.getPost(postId);
            if (post != null) {
                break;
            }
        }

        if (post != null) {
            HashMap<User, ArrayList<String>> comments = post.getComments();
            if (comments.isEmpty()) {
                System.out.println("Post has no comment");
            } else {
                for (Map.Entry<User, ArrayList<String>> entry : comments.entrySet()) {
//                    User user = entry.getKey();
                    ArrayList<String> userComments = entry.getValue();
                    for (String comment : userComments) {
                        System.out.println(comment);
                    }
                }
            }
        } else {
            System.out.println("Post is not exist");
        }
    }

    public void displayAllCommentOfUser(String userId) {
        User user = con.getUserById(userId);
        user.viewAllComment();
    }

    // menu ====================================================================================
    public int menuMain() {
        System.out.println("+====== Welcome to facebook =====+");
        System.out.printf("|%-10s%-21s |\n", "", "1. login");
        System.out.printf("|%-10s%-21s |\n", "", "2. sign up");
        System.out.printf("|%-10s%-21s |\n", "", "3. exit");
        System.out.println("+================================+");
        return val.inputChoice(1, 3);
    }

    public User login() {
        String user = val.instring("Enter username: ");
        String password = val.instring("Enter password: ");
        return con.getUserByAccount(user, password);
    }

    public void signUp() {
        String username = val.instring("Enter username: ");
        if (con.checkUsernameExist(username)) {
            System.out.println("Username is exist");
            return;
        }

        String password = val.instring("Enter password: ");
        String id = val.inIdUser();
        String name = val.inName();
        String address = val.instring("Enter address: ");
        String dob = val.inDate();
        if (con.addUser(id, name, address, dob, username, password)) {
            System.out.println("Add user successful");
        } else {
            System.out.println("Add user fail");
        }
    }

    public int menuLoginComplete(User u) {
        System.out.printf("+===== Welcome to Fake Facebook======+\n");
        System.out.printf("|    %-13s %-15s   |\n", "1. Post", "5. Delete User");
        System.out.printf("|    %-13s %-15s   |\n", "2. Friend", "6. Notification");
        System.out.printf("|    %-13s %-15s   |\n", "3. View Post", "7. Exit");
        System.out.printf("|    %-13s %-15s   |\n", "4. Profile", "");
        System.out.printf("+====================================+\n");
        return val.inputChoice(1, 7);
    }

    public int menuPosts() {
        System.out.println("+------------------------------------+");
        System.out.printf("|  %-15s  %-10s  |\n", "1. Create post", "4. Display post");
        System.out.printf("|  %-15s  %-10s       |\n", "2. Edit post", "5. Exit");
        System.out.printf("|  %-15s  %-10s       |\n", "3. Delete post", "");
        System.out.println("+------------------------------------+");
        return val.inputChoice(1, 5);
    }

    public int friend() {
        System.out.println("+-------------------------------+");
        System.out.printf("|  %-15s   %-10s |\n", "1. Add friend", "4. Search");
        System.out.printf("|  %-15s  %-10s |\n", "2. Delete Friend", "5. Exit");
        System.out.printf("|  %-15s  %-10s|\n", "3. Display friend", "");
        System.out.println("+-------------------------------+");
        return val.inputChoice(1, 5);
    }

    public int profile() {
        System.out.println("+--------------------------------------+");
        System.out.printf("|  %-15s  %-10s  |\n", "1. View Infomation", "4. Edit Posted");
        System.out.printf("|  %-15s  %-10s      |\n", "2. Edit Infomation", "5. Exit");
        System.out.printf("|  %-15s  %-10s         |\n", "3. View Posted", "");
        System.out.println("+--------------------------------------+");
        return val.inputChoice(1, 5);
    }

    public int postDetail() {
        System.out.println("+-------------------------------------------------+");
        System.out.printf("| %-26s%-21s |\n", "1. View Post", "6. Add Comment");
        System.out.printf("| %-26s%-21s |\n", "2. View Emotion Of Post", "7. Edit Comment");
        System.out.printf("| %-26s%-21s |\n", "3. View Comment Of Post", "8. Delete Comment");
        System.out.printf("| %-26s%-21s |\n", "4. Add Emotion", "9. Share");
        System.out.printf("| %-26s%-21s |\n", "5. Delete Emotion", "10. Exit");
        System.out.println("+-------------------------------------------------+");
        return val.inputChoice(1, 10);
    }

    public int menuShare() {
        System.out.println("+-------------------------+");
        System.out.println("| 1. Share With Friend    |");
        System.out.println("| 2. Share With all User  |");
        System.out.println("| 3. Quit                 |");
        System.out.println("+-------------------------+");
        return val.inputChoice(1, 3);
    }

    public void deleteUser(User user) {
        if (con.deleteUser(user)) {
            System.out.println("Delete user successful");
        } else {
            System.out.println("Delete user fali");
        }
    }

    // post==================================================
    public void addPostByUser(User user) {
        if (user == null) {
            System.out.println("User is not exist");
            return;
        }

        String postID = val.inIdPost();
        for (Vertex<User> vertice : con.getListUser().getVertices()) {
            User u = vertice.getLabel();
            ArrayList<Post> p = u.getPosts();
            for (Post post : p) {
                if (post.getId().equalsIgnoreCase(postID)) {
                    System.out.println("Post already exist");
                    return;
                }
            }
        }
        String postType = val.inType();
        String postContent = val.instring("Enter content: ");
        String postPrivacy = val.inPrivacy();
        String postDate = val.getDateNow();

        if (user.addPost(postID, postType, postContent, postPrivacy, postDate)) {
            System.out.println("Add post successful");
            user.addNotification("Add post have id " + postID + " Successful " + " (" + val.getDateNow() + ")");
        } else {
            System.out.println("Add post fail");
            user.addNotification("Add post have id " + postID + " fail " + " (" + val.getDateNow() + ")");
        }
    }

    public void editPostByUser(User user, Post post) {
        if (!user.getPosts().contains(post)) {
            return;
        }
        int choice;
        boolean a = true;
        String newType = null;
        String newContent = null;
        String newPrivacy = null;
        while (a) {
            System.out.println("+----------------------+");
            System.out.println("|   1. Edit type       |");
            System.out.println("|   2. Edit content    |");
            System.out.println("|   3. Edit privacy    |");
            System.out.println("|   4. Quit            |");
            System.out.println("+----------------------+");
            choice = val.inputChoice(1, 4);
            switch (choice) {
                case 1:
                    newType = val.inType();
                    break;
                case 2:
                    newContent = val.instring("Enter new content: ");
                    break;
                case 3:
                    newPrivacy = val.inPrivacy();
                    break;
                case 4:
                    a = false;
                    break;
            }
        }
        if (newType != null) {
            post.setType(newType);
        }
        if (newContent != null) {
            post.setContent(newContent);
        }
        if (newPrivacy != null) {
            post.setPrivacy(newPrivacy);
        }
        post.setDayPost(val.getDateNow());
        System.out.println("Edit successfull");

    }

    public Post getPostOfUser(User user) {
        ArrayList<Post> ls = user.getPosts();
        if (!ls.isEmpty()) {
            for (Post post : ls) {
                post.display();
            }

            String postId = val.inIdPost();
            for (Post post : ls) {
                if (post.getId().equalsIgnoreCase(postId)) {
                    return post;
                }
            }
        }
        System.out.println("Post is not exist");
        return null;
    }

    public Post getPostCanView(User user) {
        ArrayList<Post> ls = this.getPostDisplayForUser(user);
        if (!ls.isEmpty()) {
            for (Post p : ls) {
                p.display();
                System.out.println();
            }
            String idP = val.instring("Enter id: ");
            for (Post p : ls) {
                if (p.getId().equalsIgnoreCase(idP)) {
                    return p;
                }
            }
        }
        System.out.println("Post is not exist");
        return null;
    }

    public ArrayList<Post> getPostDisplayForUser(User user) {
        ArrayList<Post> result = new ArrayList<>();
        result.addAll(user.getPosts());
        
        Set<User> listUser = con.getListUser().BFS1(user);

        Iterator<User> listUserIterator = listUser.iterator();
        while (listUserIterator.hasNext()) {
            User u = listUserIterator.next();
            if (con.checkFriend(u, user)) {
                ArrayList<Post> postOfUser = u.getPosts();
                for (Post p1 : postOfUser) {
                    if (p1.getPrivacy().equalsIgnoreCase("friend")) {
                        result.add(p1);
                    }
                }
            }

            ArrayList<Post> p2 = u.getPosts();
            if (p2 != null) {
                for (Post p : p2) {
                    if (p.getPrivacy().equalsIgnoreCase("public") && u != user) {
                        result.add(p);
                    }
                }
            }
        }
        
        return result;
//        
//        ArrayList<Post> postOfUser;
//        Set<User> friend = con.getFriends(user);
//        result.addAll(user.getPosts());
//
//        Iterator<User> friendIterator = friend.iterator();
//        while (friendIterator.hasNext()) {
//            User u = friendIterator.next();
//            postOfUser = u.getPosts();
//            for (Post p1 : postOfUser) {
//                if (p1.getPrivacy().equalsIgnoreCase("friend")) {
//                    result.add(p1);
//                }
//            }
//        }
//        ArrayList<Post> p2;
//        for (Vertex<User> vertice : con.getListUser().getVertices()) {
//            User u = vertice.getLabel();
//            p2 = u.getPosts();
//            if (p2 != null) {
//                for (Post p : p2) {
//                    if (p.getPrivacy().equalsIgnoreCase("public") && u != user) {
//                        result.add(p);
//                    }
//                }
//            }
//        }
//        return result;

    }

    public void displayPostOfUser(User user) {
        for (Post post : user.getPosts()) {
            post.display();
        }
    }

    public void displayNotification(User user) {
        if (!user.getNotification().isEmpty()) {
            for (String no : user.getNotification()) {
                System.out.println(no);
            }
        } else {
            System.out.println("Have no notification");
        }
    }

    public void deletePostByUser(User user) {
        if (user != null) {
            String postId = val.inID();
            if (user.deletePost(postId)) {
                con.deletePostShare(postId);
                System.out.println("Delete Post successful");
                user.addNotification("Delete post have id " + postId + " Successful" + " (" + val.getDateNow() + ")");
            } else {
                System.out.println("Delete Post fail");
                user.addNotification("Delete post have id " + postId + " fail" + " (" + val.getDateNow() + ")");
            }
        } else {
            System.out.println("Delete Post fail");
        }
    }

    // friend===================================================
    public void addFriendByUser(User user) {
        String userId2 = val.inIdUser();
        User u2 = con.getUserById(userId2);
        if (con.checkFriend(user, u2)) {
            System.out.println(user.getUsername() + " and " + u2.getUsername() + " are friend");
            return;
        }
        if (con.addFriend(user, u2)) {
            System.out.println("Add friend successfull");
            user.addNotification("You and " + u2.getName() + " are friend" + " (" + val.getDateNow() + ")");
        } else {
            System.out.println("Add friend fail");
            user.addNotification("You and " + u2.getName() + " can't add friend" + "(" + val.getDateNow() + ")");
        }
    }

    public void deleteFriendByUser(User user1) {
        String id2 = val.inIdUser();
        User user2 = con.getUserById(id2);
        if (con.deleteFriend(user1, user2)) {
            System.out.println("Delete friend successful");
            user1.addNotification("you deleted friend with " + user2.getName() + "(" + val.getDateNow() + ")");
            user2.addNotification("you and " + user1.getName() + " aren't friend " + "(" + val.getDateNow() + ")");
        } else {
            System.out.println("Delete friend fail");
            user1.addNotification("you deleted friend with " + user2.getName() + "fail" + "(" + val.getDateNow() + ")");
        }
    }

    public void displayFriendsByUser(User user) {
        Set<User> friends = con.getFriends(user);
        if (friends.isEmpty()) {
            System.out.println(user.getName() + " has no friends.");
        } else {
            System.out.println(user.getName() + "'s friends: ");
            System.out.printf("%-5s%-20s%-15s%-20s\n", "ID", "Name", "Address", "Date Of Birth");
            for (User friend : friends) {
                System.out.print(friend.toString());
            }
        }
    }

    //cảm xúc =============================================
    public void addEmotionByUser(User user, Post post) {
        String emotion = val.inEmotion();
        if (post != null) {
            user.addEmotion(post, emotion);
            System.out.println("Add emotion successful");
            user.addNotification("You are " + emotion + " to post have id " + post.getId() + "(" + val.getDateNow() + ")");
            post.getUserPost().addNotification(user.getName() + " added " + emotion + " to your post have id " + post.getId() + "(" + val.getDateNow() + ")");
        } else {
            System.out.println("Add emotion failed");
        }
    }

    public void deleteEmotionByUser(User user, Post post) {
        if (post != null) {
            user.deleteEmotion(post);
            System.out.println("Delete emotion successful");
        } else {
            System.out.println("Delete emotion failed");
        }
    }

    //******************************************************************************
    public void addCommentInPost(User user, Post post) {
        if (post != null) {
            String comment = val.instring("Enter comment: ");
            user.addComment(post, comment);

            // tìm những người dùng có bài viết này và thêm comment vào
            System.out.println("Add comment successfull");
            post.getUserPost().addNotification(user.getName() + " commented: " + comment + " to your post " + "(" + val.getDateNow() + ")");
        } else {
            System.out.println("Add comment fail");
        }
    }

    public String editCommet(User u, Post p) {
        ArrayList<String> cmt = u.getCommented().get(p);
        int count = 1;
        if (cmt != null) {
            for (String s : cmt) {
                System.out.print(count + ". ");
                System.out.println(s);
                count++;
            }
            int choice = val.inputChoice(1, cmt.size());
            return cmt.get(choice - 1);
        }
        return null;
    }

    public void editCommnentInPost(User user, Post post) {
        if (post != null) {
            String oldComment = this.editCommet(user, post);
            String newComment = val.instring("Enter new comment: ");
            if (user.editComment(post, oldComment, newComment)) {
                System.out.println("Edit comment successful");
            } else {
                System.out.println("Edit comment fail");
            }


            /* if (post.getId().startsWith("S")) {
                String postOriginId = post.getId().substring(1);
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postOrigin = user1.getPost(postOriginId);
                    if (postOrigin != null) {
                        user1.editComment(postOrigin, oldComment, newComment);
                        break;
                    }
                }

                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user2 = vertex.getLabel();
                    Post postOrigin = user2.getPost(post.getId());
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.editComment(postOrigin, oldComment, newComment);
                    }
                }
            } else {
                String postShareId = "S" + post.getId();
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.editComment(postShare, oldComment, newComment);
                    }
                }
            }
            System.out.println("Edit comment successful");*/
        } else {
            System.out.println("Edit comment fail");
        }
    }

    public void deleteCommentInPost(User user, Post p) {
        String comment = this.editCommet(user, p);
        if (p != null) {
            if (user.deleteComment(p, comment)) {
                System.out.println("Delete comment successful");
            } else {
                System.out.println("Delete comment fail");
            }
        } else {
            System.out.println("Delete comment fail");
        }
    }

    // khi thêm sửa xóa comment và emotion thì sẽ tìm
    // tất cả các bài viết chứa post và edit tất cả
    public void viewInfomation(User u) {
        System.out.println("+----------------------------------+");
        System.out.printf("| ID: %-28s %-10s\n", u.getUserID(), "|");
        System.out.printf("| Name: %-26s %-10s\n", u.getName(), "|");
        System.out.printf("| Address: %-23s %-10s\n", u.getAddress(), "|");
        System.out.printf("| Date Of Birth: %-17s %-10s\n", u.getDateOfBirth(), "|");
        System.out.println("+----------------------------------+");
    }

    public void editInfomation(User u) {
        int choice;
        boolean a = true;
        String newName = null;
        String newAddress = null;
        String newDate = null;
        while (a) {
            System.out.println("+-----------------------------+");
            System.out.println("|       1. Edit name          |");
            System.out.println("|       2. Edit Address       |");
            System.out.println("|       3. Edit Date Of Birth |");
            System.out.println("|       4. Quit               |");
            System.out.println("+-----------------------------+");
            choice = val.inputChoice(1, 4);
            switch (choice) {
                case 1:
                    newName = val.instring("Enter new Name: ");
                    break;
                case 2:
                    newAddress = val.instring("Enter new Address: ");
                    break;
                case 3:
                    newDate = val.inDate();
                    break;
                case 4:
                    a = false;
                    break;
            }
        }
        if (newName != null) {
            u.setName(newName);
        }
        if (newAddress != null) {
            u.setAddress(newAddress);
        }
        if (newDate != null) {
            u.setDateOfBirth(newDate);
        }
        u.addNotification("You are update profile successful " + "(" + val.getDateNow() + ")");
    }

    public void search(User u) {
        String s = val.instring("Enter key: ");
        Set<User> result = con.search(s);
        if (!result.isEmpty()) {
            this.displayUser(result);
        }
    }

    public void displayUser(Set<User> ls) {
        System.out.printf("%-5s%-20s%-15s%-20s\n", "ID", "Name", "Address", "Date Of Birth");
        for (User user : ls) {
            System.out.print(user.toString());
        }

//        con.getListUser().BFSTraversal();
    }

}
