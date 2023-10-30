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
        Post p1 = new Post("PO1", "Status", "Hung hut hit dang an com trong nha ve sinh", "Friend", "28/10/2023  10:13:52",user1);
        Post p2 = new Post("PO2", "Status", "Hung hut", "Public", "28/10/2023 10:13:52",user1);
        user1.addPost(p1);
        user1.addPost(p2);
        this.sharePostWithAllFriends(user2, p1);
//        user1.addPost("PO2", "Story", "Thuy dang di choi voi hanh", "Friend", "22/10/2023");
//        this.sharePostWithAllFriends("US1", "PO2");
//
//        User user2 = con.getUserById("US2");
//        user2.addPost("PO3", "Status", "Thang an com cung hung hut hit trong nha ve sinh", "public", "28/10/2023");
//        this.sharePostWithAllUsers("US2", "PO3");
    }

    public void displayUser() {
        System.out.printf("%-5s%-20s%-15s%-20s\n", "ID", "Name", "Address", "Date Of Birth");
        for (Vertex<User> vertice : con.getListUser().getVertices()) {
            System.out.print(vertice.getLabel().toString());;
        }

//        con.getListUser().BFSTraversal();
    }



    public void sharePostWithAllFriends(User user, Post postShare) {
        if (postShare == null) {
            System.out.println("Post not found.");
            return;
        }
        Post newPost = new Post("S" + postShare.getId(),postShare.getType() , postShare, "Friend", val.getDateNow(), user);
        postShare.share();
        user.addPost(newPost);
        System.out.println("Share successful");
        
        
    }

    public void sharePostWithAllUsers(User user, Post postShare) {
        if (postShare == null) {
            System.out.println("Post not found.");
            return;
        }
        Post newPost = new Post("S" + postShare.getId(),postShare.getType() , postShare, "Public", val.getDateNow(), user);
        postShare.share();
        user.addPost(newPost);
        System.out.println("Share successful");
        
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
        System.out.println("Welcome to facebook");
        System.out.println("1. login");
        System.out.println("2. sign up");
        System.out.println("3. exit");
        return val.inputChoice(1, 3);
    }

    public User login() {
        String user = val.instring("Enter username: ");
        String password = val.instring("Enter password: ");
        return con.getUserByAccount(user, password);
    }

    public void signUp() {
        String username = val.instring("Enter username: ");
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
        System.out.println("Hello " + u.getName());
        System.out.println("1. Post");  // tạo post, sửa, xóa, xem danh sách các bài đã đăng của bản thân
        System.out.println("2. Friend"); // thêm bạn, xóa bạn, xem danh sách bạn bè
        System.out.println("3. View Post"); // xem các bài viết của bạn bè và tương tác(thêm sửa xóa cảm xúc, comment, chia sẻ...)
        System.out.println("4. Profile");
        System.out.println("5. Delete User");
        System.out.println("6. Notification");
        System.out.println("7. Exit");
        return val.inputChoice(1, 7);
    }

    public int menuPosts() {
        System.out.println("1. Create post");
        System.out.println("2. Edit post");
        System.out.println("3. Delete post");
        System.out.println("4. Display post");
        System.out.println("5. Exit");
        return val.inputChoice(1, 5);
    }

    public int friend() {
        System.out.println("1. Add friend");
        System.out.println("2. Delete Friend");
        System.out.println("3. Display friend");
        System.out.println("4. Search");
        System.out.println("5. Exit");
        return val.inputChoice(1, 5);
    }

    public int profile() {
        System.out.println("1. View Infomation");
        System.out.println("2. Edit Infomation");
        System.out.println("3. View Posted");
        System.out.println("4. Edit Posted");
        System.out.println("5. Exit");
        return val.inputChoice(1, 5);
    }

    public int postDetail() {
        System.out.println("1. View Post");
        System.out.println("2. View Emotion Of Post");
        System.out.println("3. View Comment Of Post");
        System.out.println("4. Add Emotion");
        System.out.println("5. Delete Emotion");
        System.out.println("6. Add Comment");
        System.out.println("7. Edit Comment");
        System.out.println("8. Delete Comment");
        System.out.println("9. Share");
        System.out.println("10. Exit");
        return val.inputChoice(1, 10);
    }
    
    public int menuShare(){
        System.out.println("1. Share With Friend");
        System.out.println("2. Share With all User");
        System.out.println("3. Quit");
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
        } else {
            System.out.println("Add post fail");
        }
    }

    public void editPostByUser(User user) {
        String postId = val.inID();
        Post post = user.getPost(postId);
        if (post != null) {
            String newType = val.inType();
            String newContent = val.instring("Enter new content: ");
            String newDayPost = val.getDateNow();
            String newPrivacy = val.inPrivacy();
            user.editPost(post, newType, newContent, newPrivacy, newDayPost);
            if (!postId.startsWith("S")) {
                String postShareId = "S" + postId;
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.editPost(postShare, newType, newContent, newPrivacy, newDayPost);
                    }
                }
            }
            System.out.println("Edit post successfull");
        } else {
            System.out.println("Edit post fail");
        }
    }

    public Post getPost(User user) {
        ArrayList<Post> ls = this.getPostDisplayForUser(user);
        if (!ls.isEmpty()) {
            for (Post p : ls) {
                p.display();
            }
            String idP = val.instring("Enter id of Post: ");
            for (Post p : ls) {
                if (p.getId().equalsIgnoreCase(idP)) {
                    return p;
                }
            }
        }
        return null;
    }

    public void displayPostOfUser(User user) {
        for (Post post : user.getPosts()) {
            post.display();
        }
    }

    public void displayNotification(User user) {
        for (String no : user.getNotification()) {
            System.out.println(no);
        }
    }

    public void deletePostByUser(User user) {
        if (user != null) {
            String postId = val.inID();
            if (user.deletePost(postId)) {
                con.deletePostShare(postId);
                System.out.println("Delete Post successful");
            } else {
                System.out.println("Delete Post fail");
            }
        } else {
            System.out.println("Delete Post fail");
        }
    }

    public ArrayList<Post> getPostDisplayForUser(User user) {
        ArrayList<Post> result = new ArrayList<>();
// Khởi tạo result
        ArrayList<Post> postOfUser;
        Set<User> friend = con.getFriends(user);
        result.addAll(user.getPosts());

        Iterator<User> friendIterator = friend.iterator();
        while (friendIterator.hasNext()) {
            User u = friendIterator.next();
            postOfUser = u.getPosts();
            for (Post p1 : postOfUser) {
                if (p1.getPrivacy().equalsIgnoreCase("friend")) {
                    result.add(p1);
                }
            }
        }
        ArrayList<Post> p2;
        for (Vertex<User> vertice : con.getListUser().getVertices()) {
            User u = vertice.getLabel();
            p2 = u.getPosts();
            if (p2 != null) {
                for (Post p : p2) {
                    if (p.getPrivacy().equalsIgnoreCase("public")) {
                        result.add(p);
                    }
                }
            }
        }
        return result;

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
        } else {
            System.out.println("Add friend fail");
        }
    }

    public void deleteFriendByUser(User user1) {
        String id2 = val.inIdUser();
        User user2 = con.getUserById(id2);
        if (con.deleteFriend(user1, user2)) {
            System.out.println("Delete friend successful");
        } else {
            System.out.println("Delete friend fail");
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
            /*if (post.getId().startsWith("S")) {
                String postOriginId = post.getId().substring(1);
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postOrigin = user1.getPost(postOriginId);
                    if (postOrigin != null) {
                        user1.addEmotion(postOrigin, emotion);
                        break;
                    }
                }

                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user2 = vertex.getLabel();
                    Post postOrigin = user2.getPost(post.getId());
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.addEmotion(postOrigin, emotion);
                    }
                }
            } else {
                String postShareId = "S" + post.getId();
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.addEmotion(postShare, emotion);
                    }
                }
            }*/
            System.out.println("Add emotion successful");
        } else {
            System.out.println("Add emotion failed");
        }
    }

    public void deleteEmotionByUser(User user, Post post) {

        if (post != null) {
            user.deleteEmotion(post);
            if (post.getId().startsWith("S")) {
                String postOriginId = post.getId().substring(1);
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postOrigin = user1.getPost(postOriginId);
                    if (postOrigin != null) {
                        user1.deleteEmotion(postOrigin);
                        break;
                    }
                }

                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user2 = vertex.getLabel();
                    Post postOrigin = user2.getPost(post.getId());
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.deleteEmotion(postOrigin);
                    }
                }
            } else {
                String postShareId = "S" + post.getId();
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.deleteEmotion(postShare);
                    }
                }
            }
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
            /*if (p.getId().startsWith("S")) {
                String postOriginId = p.getId().substring(1);
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postOrigin = user1.getPost(postOriginId);
                    if (postOrigin != null) {
                        user1.deleteComment(postOriginId, comment);
                        break;
                    }
                }

                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user2 = vertex.getLabel();
                    Post postOrigin = user2.getPost(p.getId());
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.deleteComment(postOriginId, comment);
                    }
                }
            } else {
                String postShareId = "S" + p.getId();
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.deleteComment(postShareId, comment);
                    }
                }
            }

            System.out.println("Delete comment successful");*/
        } else {
            System.out.println("Delete comment fail");
        }
    }

    // khi thêm sửa xóa comment và emotion thì sẽ tìm
    // tất cả các bài viết chứa post và edit tất cả
    public void viewInfomation(User u) {
        System.out.println("ID: " + u.getUserID());
        System.out.println("Name: " + u.getName());
        System.out.println("Address: " + u.getAddress());
        System.out.println("Date Of Birth: " + u.getDateOfBirth());
    }

    public void editInfomation(User u) {
        int choice;
        boolean a = true;
        String newName = null;
        String newAddress = null;
        String newDate = null;
        while (a) {
            System.out.println("1. Edit name");
            System.out.println("2. Edit Address");
            System.out.println("3. Edit Date Of Birth");
            System.out.println("4. Quit");
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

    }

    public void share(User u, Post p) {

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
            System.out.print(user.toString());;
        }

//        con.getListUser().BFSTraversal();
    }

}
