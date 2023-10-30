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

    //menu ===============================================
    public int menu() {
        System.out.println("======= WELCOME TO FACEBOOK FA KE VERSION 1 =======");
        System.out.println("1. Create user");
        System.out.println("2. Delete user");
        System.out.println("3. Display list user");
        System.out.println("4. Display list all post");
        System.out.println("5. Display list post of user");
        System.out.println("6. Display list friends of user");
        System.out.println("7. Check friend");
        System.out.println("8. Create friend");
        System.out.println("9. Delete friend");
        System.out.println("10. Login");
        System.out.println("11. Exit");
        return val.inputChoice(1, 11);
    }

    public int menuLogin() {
        System.out.println("======= USER INTERFACE =======");
        System.out.println("1. Create friend");
        System.out.println("2. Delete friend");
        System.out.println("3. Display list friend");
        System.out.println("4. Create post");
        System.out.println("5. Edit post");
        System.out.println("6. Delete post");
        System.out.println("7. Display list post");
        System.out.println("8. Choose a post to see information");
        System.out.println("9. Delete User");
        System.out.println("10. Exit");
        return val.inputChoice(1, 10);
    }

    public int menuPost() {
        System.out.println("========= Post =========");
        System.out.println("1. Display Comment");
        System.out.println("2. Create Comment");
        System.out.println("3. Edit Comment");
        System.out.println("4. Delete Comment");
        System.out.println("5. Add emontion");
        System.out.println("6. Delete emontion");
        System.out.println("7. Share post for a friend");
        System.out.println("8. Share post for all friends");
        System.out.println("9. Share post for all users");
        System.out.println("10. Exit");
        return val.inputChoice(1, 12);
    }

    //khởi tạo ===============================================
    public void initUser() {
        con.addUser("US1", "Hung", "Bac Ninh", "8/7/2003");
        con.addUser("US2", "Hanh", "Hai Phong", "3/3/2003");
        con.addUser("US3", "Thuy", "Vinh Phuc", "2/8/2003");
        con.addUser("US4", "Chung", "Tau", "1/11/2003");
        con.addUser("US5", "Thang", "Lao Cai", "1/11/2003");
    }

    public void initFriend() {
        con.addFriend("US1", "US2");
        con.addFriend("US1", "US3");
        con.addFriend("US2", "US3");
        con.addFriend("US2", "US5");
        con.addFriend("US3", "US4");
    }

    public void initPost() {
        User user1 = con.getUserById("US1");
        user1.addPost("PO1", "Status", "Hung hut hit dang an com trong nha ve sinh", "Friend", "28/10/2023");
        this.sharePostWithAllFriends("US1", "PO1");

//        user1.addPost("PO2", "Story", "Thuy dang di choi voi hanh", "Friend", "22/10/2023");
//        this.sharePostWithAllFriends("US1", "PO2");
//
//        User user2 = con.getUserById("US2");
//        user2.addPost("PO3", "Status", "Thang an com cung hung hut hit trong nha ve sinh", "public", "28/10/2023");
//        this.sharePostWithAllUsers("US2", "PO3");
    }

    // người dùng ===============================================
    public void addUser() {
        String id = val.inID();
        if (con.checkIdUser(id)) {
            System.out.println("User is exist");
            return;
        }
        String name = val.inName();
        String address = val.instring("Enter address: ");
        String dob = val.inDate();
        if (con.addUser(id, name, address, dob)) {
            System.out.println("Add user successful");
        } else {
            System.out.println("Add user fail");
        }
    }

    public void deleteUser() {
        String id = val.inID();

        if (con.deleteUser(id)) {
            System.out.println("Delete user successful");
        } else {
            System.out.println("Delete user fali");
        }
    }
    
    public void deleteUserByUser(String id) {
        User user = con.getUserById(id);
        
        //tạo bản sao danh sách post
        ArrayList<Post> userPosts = new ArrayList<>(user.getPosts());
        if (con.deleteUser(id)) {
            for (Post post : userPosts) {
                con.deletePostShare(post.getId());
            }
            System.out.println("Delete user successful");
        } else {
            System.out.println("Delete user fali");
        }
    }

    public void displayUser() {
        System.out.printf("%-5s%-20s%-15s%-20s\n", "ID", "Name", "Address", "Date Of Birth");
        for (Vertex<User> vertice : con.getListUser().getVertices()) {
            System.out.print(vertice.getLabel().toString());;
        }

//        con.getListUser().BFSTraversal();
    }

    public String loginUser() {
        this.displayUser();
        System.out.println("Enter id of user which you want to sign in: ");
        String id = val.inID();
        User user = con.getUserById(id);
        if (user != null) {
            return id;
        } else {
            System.out.println("User not exist");
            return null;
        }
    }

    // bài viết ===============================================
    public void addPost() {
        String userId = val.inID();
        User user = con.getUserById(userId);
        if (user == null) {
            System.out.println("User is not exist");
            return;
        }

        String postID = val.inID();
        if (user.getPost(postID) != null) {
            System.out.println("Post is exit");
            return;
        }
        String postType = val.inType();
        String postContent = val.instring("Enter content: ");
        String postPrivacy = val.inPrivacy();
        String postDate = val.getDateNow();

        if (user.addPost(postID, postType, postContent, postPrivacy, postDate)) {
            System.out.println("Add post successful");
            if (postPrivacy.equalsIgnoreCase("friend")) {
                this.sharePostWithAllFriends(userId, postID);
            } else if (postPrivacy.equalsIgnoreCase("public")) {
                this.sharePostWithAllUsers(userId, postID);
            }
        } else {
            System.out.println("Add post fail");
        }
    }

    public void addPostByUser(String userId) {
        User user = con.getUserById(userId);
        if (user == null) {
            System.out.println("User is not exist");
            return;
        }

        String postID = val.inID();
        if (user.getPost(postID) != null) {
            System.out.println("Post is exit");
            return;
        }
        String postType = val.inType();
        String postContent = val.instring("Enter content: ");
        String postPrivacy = val.inPrivacy();
        String postDate = val.getDateNow();

        if (user.addPost(postID, postType, postContent, postPrivacy, postDate)) {
            System.out.println("Add post successful");
            if (postPrivacy.equalsIgnoreCase("friend")) {
                this.sharePostWithAllFriends(userId, postID);
            } else if (postPrivacy.equalsIgnoreCase("public")) {
                this.sharePostWithAllUsers(userId, postID);
            }
        } else {
            System.out.println("Add post fail");
        }
    }

    public void editPostByUser(String userId) {
        User user = con.getUserById(userId);
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

    public void deletePost() {
        String userId = val.inID();
        User user = con.getUserById(userId);
        if (user != null) {
            String postId = val.inID();
            if (user.deletePost(postId)) {
                if (!postId.startsWith("S")) {
                    String postShareId = "S" + postId;
                    for (Vertex<User> vertex : con.getListUser().getVertices()) {
                        User user1 = vertex.getLabel();
                        Post postShare = user1.getPost(postShareId);
                        if (postShare != null) {
                            user1.deletePost(postShareId);
                        }
                    }
                }
                System.out.println("Delete Post successful");
            } else {
                System.out.println("Delete Post fail");
            }
        } else {
            System.out.println("Delete Post fail");
        }
    }

    public void deletePostByUser(String userId) {
        User user = con.getUserById(userId);
        if (user != null) {
            String postId = val.inID();
            if (user.deletePost(postId)) {
//                if (!postId.startsWith("S")) {
//                    String postShareId = "S" + postId;
//                    for (Vertex<User> vertex : con.getListUser().getVertices()) {
//                        User user1 = vertex.getLabel();
//                        Post postShare = user1.getPost(postShareId);
//                        if (postShare != null) {
//                            user1.deletePost(postShareId);
//                        }
//                    }
//                }
                con.deletePostShare(postId);
                System.out.println("Delete Post successful");
            } else {
                System.out.println("Delete Post fail");
            }
        } else {
            System.out.println("Delete Post fail");
        }
    }

    public void displayPostOfUser() {
        String userId = val.inID();
        User user = con.getUserById(userId);

        System.out.println("");
        if (user != null) {
            for (Post post : user.getPosts()) {
            post.display1();
        }
        }
        
    }

    public void displayPostByUser(String userId) {
        User user = con.getUserById(userId);

        System.out.println("");

        for (Post post : user.getPosts()) {
            post.display1();
        }
    }

    //hiển thị lần lượt
    public void displayAllPost() {
        for (Vertex<User> vertex : con.getListUser().getVertices()) {
            User user = vertex.getLabel();
            for (Post post : user.getPosts()) {
                post.display1();
            }
        }
    }
    
    public void sharePostWithFriend(String userId, String postId) {
        User user = con.getUserById(userId);
        Post post = user.getPost(postId);
        String friendId = val.inID();
        User friend = con.getUserById(friendId);
        if (con.getListUser().containEdge(user, friend)) {
            // Create a copy of the post to share
            Post sharedPost = new Post("S" + post.getId(), post.getType(), post.getContent(), post.getPrivacy(), post.getDayPost(), user);
            friend.addPost(sharedPost);
            
            post.share();
            
            System.out.println("Share successful with " + friend.getName());
        } else {
            System.out.println(friend.getName() + " is not friend. Can't share");
        }
    }
    
    public void sharePostWithAllFriends(String userId, String postId) {
        User user = con.getUserById(userId);
        Set<User> friends = con.getFriends(user);

        if (friends.isEmpty()) {
            System.out.println(user.getName() + " has no friend");
            return;
        }

        Post postShare = user.getPost(postId);

        if (postShare == null) {
            System.out.println("Post not found.");
            return;
        }

        for (User friend : friends) {

            boolean isShared = false;
            for (Post sharedPost : friend.getPosts()) {
                if (sharedPost != null && sharedPost.getId() != null && sharedPost.getId().equalsIgnoreCase(postId)) {
                    isShared = true;
                    break;
                }
            }

            if (!isShared) {
                Post sharedPost = new Post();
                sharedPost.setId("S" + postId);
                sharedPost.setType(postShare.getType());
                sharedPost.setContent(postShare.getContent());
                sharedPost.setPrivacy(postShare.getPrivacy());
                sharedPost.setDayPost(postShare.getDayPost());
                sharedPost.setUserPost(user);

                if (friend.addPost(sharedPost)) {
                    postShare.share();
                    System.out.println("Share successful with " + friend.getName());
                } else {
                    System.out.println("Share fail with " + friend.getName());
                }
            } else {
                System.out.println("The post has already been shared with " + friend.getName());
            }
        }
    }

    public void sharePostWithAllUsers(String userId, String postId) {
        User user = con.getUserById(userId);
        Post post = user.getPost(postId);
        if (post != null) {
            for (Vertex<User> vertex : con.getListUser().getVertices()) {
                User otherUser = vertex.getLabel();
                if (!otherUser.getUserID().equalsIgnoreCase(user.getUserID())) {
                    boolean isShared = false;
                    for (Post sharedPost : otherUser.getPosts()) {
                        if (sharedPost != null && sharedPost.getId() != null && sharedPost.getId().equalsIgnoreCase(postId)) {
                            isShared = true;
                            break;
                        }
                    }

                    if (!isShared) {
                        Post sharedPost = new Post();
                        sharedPost.setId("S" + postId);
                        sharedPost.setType(post.getType());
                        sharedPost.setContent(post.getContent());
                        sharedPost.setPrivacy(post.getPrivacy());
                        sharedPost.setDayPost(post.getDayPost());
                        sharedPost.setUserPost(user);

                        if (otherUser.addPost(sharedPost)) {
                            post.share();
                            System.out.println("Share successful with " + otherUser.getName());
                        } else {
                            System.out.println("Share fail with " + otherUser.getName());
                        }
                    } else {
                        System.out.println("The post has already been shared with " + otherUser.getName());
                    }
                }
            }
        } else {
            System.out.println("Post not found.");
        }
    }
    
    public String choicePostByUser(String userId) {
        User user = con.getUserById(userId);
        this.displayPostByUser(userId);
        System.out.println("Enter id of post which you want to choise: ");
        String postId = val.inID();
        Post post = user.getPost(postId);
        if (post != null) {
            return postId;
        } else {
            System.out.println("Post is not exist");
            return null;
        }
    }

    // bạn bè ===============================================
    public void addFriend() {
        String userId1 = val.inID();
        String userId2 = val.inID();

        if (con.addFriend(userId1, userId2)) {
            System.out.println("Add friend successfull");
        } else {
            System.out.println("Add friend fail");
        }
    }

    public void addFriendByUser(String userId1) {
        String userId2 = val.inID();

        if (con.addFriend(userId1, userId2)) {
            System.out.println("Add friend successfull");
        } else {
            System.out.println("Add friend fail");
        }
    }

    public void checkFriend() {
        String userId1 = val.inID();
        String userId2 = val.inID();

        User user1 = con.getUserById(userId1);
        User user2 = con.getUserById(userId2);

        if (con.getListUser().containEdge(user1, user2)) {
            System.out.println(userId1 + " and " + userId2 + " are friend");
        } else {
            System.out.println(userId1 + " and " + userId2 + " aren't friend");
        }
    }

    public void deleteFriend() {
        String id1 = val.inID();
        String id2 = val.inID();

        User user1 = con.getUserById(id1);
        User user2 = con.getUserById(id2);

        if (con.deleteFriend(user1, user2)) {
            System.out.println("Delete friend successful");
        } else {
            System.out.println("Delete friend fail");
        }
    }

    public void deleteFriendByUser(String id1) {
        String id2 = val.inID();

        User user1 = con.getUserById(id1);
        User user2 = con.getUserById(id2);

        if (con.deleteFriend(user1, user2)) {
            System.out.println("Delete friend successful");
        } else {
            System.out.println("Delete friend fail");
        }
    }

    public void displayFriends() {
        String userId = val.inID();
        User user = con.getUserById(userId);
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

    public void displayFriendsByUser(String userId) {
        User user = con.getUserById(userId);
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

    // comment ===============================================
    // thêm vào bài viết bất kì
    public void addComment(User user) {
        String postId = val.inID();
        Post post = user.getPost(postId);
        if (post != null) {
            String comment = val.instring("Enter comment: ");
            user.addComment(post, comment);
            System.out.println("Add comment successfull");
        } else {
            System.out.println("Add comment fail");
        }
    }

    // thêm vào bài viết cụ thể
    public void addCommentInPost(String userId, String postId) {
        User user = con.getUserById(userId);
        Post post = user.getPost(postId);

        if (post != null) {
            String comment = val.instring("Enter comment: ");
            user.addComment(post, comment);

            //thêm vào bài viết gốc
            if (postId.startsWith("S")) {
                String postOriginId = postId.substring(1);
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postOrigin = user1.getPost(postOriginId);
                    if (postOrigin != null) {
                        user1.addComment(postOrigin, comment);
                        break;
                    }
                }

                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user2 = vertex.getLabel();
                    Post postOrigin = user2.getPost(postId);
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.addComment(postOrigin, comment);
                    }
                }
            } else {
                String postShareId = "S" + postId;
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.addComment(postShare, comment);
                    }
                }
            }
            System.out.println("Add comment successfull");
        } else {
            System.out.println("Add comment fail");
        }
    }

    public void editCommnentInPost(String userId, String postId) {
        User user = con.getUserById(userId);
        Post post = user.getPost(postId);

        if (post != null) {
            String oldComment = val.instring("Enter old comment: ");
            
            
            String newComment = val.instring("Enter new comment: ");
            user.editComment(post, oldComment, newComment);

            if (postId.startsWith("S")) {
                String postOriginId = postId.substring(1);
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
                    Post postOrigin = user2.getPost(postId);
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.editComment(postOrigin, oldComment, newComment);
                    }
                }
            } else {
                String postShareId = "S" + postId;
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.editComment(postShare, oldComment, newComment);
                    }
                }
            }
            System.out.println("Edit comment successful");
        } else {
            System.out.println("Edit comment fail");
        }
    }

    public void deleteCommentInPost(String userId, String postId) {
        User user = con.getUserById(userId);
        String comment = val.instring("Enter comment: ");
        if (user.deleteComment(postId, comment)) {
            if (postId.startsWith("S")) {
                String postOriginId = postId.substring(1);
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
                    Post postOrigin = user2.getPost(postId);
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.deleteComment(postOriginId, comment);
                    }
                }
            } else {
                String postShareId = "S" + postId;
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.deleteComment(postShareId, comment);
                    }
                }
            }

            System.out.println("Delete comment successful");
        } else {
            System.out.println("Delete comment fail");
        }
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

    //cảm xúc =============================================
    public void addEmotionByUser(String userId, String postId) {
        User user = con.getUserById(userId);
        Post post = user.getPost(postId);
        String emotion = val.inEmotion();
        if (post != null) {
            user.addEmotion(post, emotion);
            if (postId.startsWith("S")) {
                String postOriginId = postId.substring(1);
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
                    Post postOrigin = user2.getPost(postId);
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.addEmotion(postOrigin, emotion);
                    }
                }
            } else {
                String postShareId = "S" + postId;
                for (Vertex<User> vertex : con.getListUser().getVertices()) {
                    User user1 = vertex.getLabel();
                    Post postShare = user1.getPost(postShareId);
                    if (postShare != null) {
                        user1.addEmotion(postShare, emotion);
                    }
                }
            }
            System.out.println("Add emotion successful");
        } else {
            System.out.println("Add emotion failed");
        }
    }
    
    public void deleteEmotionByUser(String userId, String postId) {
        User user = con.getUserById(userId);
        Post post = user.getPost(postId);

        if (post != null) {
            user.deleteEmotion(post);
            if (postId.startsWith("S")) {
                String postOriginId = postId.substring(1);
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
                    Post postOrigin = user2.getPost(postId);
                    if (postOrigin != null && !user2.equals(user)) {
                        user2.deleteEmotion(postOrigin);
                    }
                }
            } else {
                String postShareId = "S" + postId;
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
}
