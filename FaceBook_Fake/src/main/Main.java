
package main;

import container.Manager;
import object.Post;
import object.User;


public class Main {

    private static final Manager man = new Manager();
    private static User login = null;
    private static Post post = null;

    public static void main(String[] args) {
        int choice;
//        man.initUser();
//        man.initFriend();
//        man.initPost();
        man.init();
        while (true) {
            System.out.println("");
            choice = man.menuMain();
            switch (choice) {
                case 1:
                    login = man.login();
                    if (login != null) {
                        loginSuccess();
                    } else {
                        System.out.println("Login false");
                    }
                    break;
                case 2:
                    man.signUp();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void loginSuccess() {
        int choice;
        boolean s = true;
        if (login != null) {
            while (s) {
                System.out.println("");
                choice = man.menuLoginComplete(login);
                switch (choice) {
                    case 1:
                        //Post
                        menuPost();
                        break;
                    case 2:
                        friend();
                        break;
                    case 3:
                        // xem và tương tác 
                        post = man.getPostCanView(login);
                        postDetail();
                        break;
                    case 4:
                        profile();
                        break;
                    case 5:
                        //delete user
                        man.deleteUser(login);
                        login = null;
                        s = false;
                        break;
                    case 6:
                        man.displayNotification(login);
                        break;
                    case 7:
                        s = false;
                        break;
                }
            }
        }
    }

    public static void menuPost() {
        int choice;
        boolean a = true;
        if (login != null) {
            while (a) {
                System.out.println("");
                choice = man.menuPosts();
                switch (choice) {
                    case 1:
                        // creat post
                        man.addPostByUser(login);
                        break;
                    case 2:
                        // edit post
                        post = man.getPostOfUser(login);
                        man.editPostByUser(login, post);
                        break;
                    case 3:
                        //delete post
                        man.deletePostByUser(login);
                        break;
                    case 4:
                        //display post
                        man.displayPostOfUser(login);
                        break;
                    case 5:
                        a = false;
                        break;
                }
            }
        }
    }

    public static void friend() {
        int choice;
        boolean a = true;
        if (login != null) {
            while (a) {
                System.out.println("");
                choice = man.friend();
                switch (choice) {
                    case 1:
                        // add friend
                        man.addFriendByUser(login);
                        break;
                    case 2:
                        //delete friend
                        man.deleteFriendByUser(login);
                        break;
                    case 3:
                        //display
                        man.displayFriendsByUser(login);
                        break;
                    case 4:
                        //search
                        man.search(login);
                        break;
                    case 5:
                        a = false;
                        break;
                }
            }
        }
    }

    public static void profile() {
        int choice;
        boolean a = true;
        if (login != null) {
            while (a) {
                System.out.println("");
                choice = man.profile();
                switch (choice) {
                    case 1:
                        //view information
                        man.viewInfomation(login);
                        break;
                    case 2:
                        //edit information
                        man.editInfomation(login);
                        break;
                    case 3:
                        // view posted 
                        man.displayPostOfUser(login);
                        break;
                    case 4:
                        //edit posted
                        post = man.getPostOfUser(login);
                        man.editPostByUser(login, post);
                        break;
                    case 5:
                        a = false;
                        break;
                }
            }
        }
    }

    public static void postDetail() {
        int choice;
        boolean a = true;
        if (post != null) {
            while (a) {
                System.out.println("");
                choice = man.postDetail();
                switch (choice) {
                    case 1:
                        // View Post
                        post.display();
                        break;
                    case 2:
                        //View Emotion Of Post
                        post.displayEmotionDetail();
                        break;
                    case 3:
                        //View Comment Of Post
                        post.displayCommentDetail();
                        break;
                    case 4:
                        //Add Emotion
                        man.addEmotionByUser(login, post);
                        break;
                    case 5:
                        //Delete Emotion
                        man.deleteEmotionByUser(login, post);
                        break;
                    case 6:
                        //Add Comment
                        man.addCommentInPost(login, post);
                        break;
                    case 7:
                        //Edit Comment
                        man.editCommnentInPost(login, post);
                        break;
                    case 8:
                        //Delete Comment
                        man.deleteCommentInPost(login, post);
                        break;
                    case 9:
                        //Share
                        menuShare();
                        break;
                    case 10:
                        a = false;
                        break;
                }
            }
        }
    }

    public static void menuShare() {
        int choice;
        boolean a = true;
        while (a) {
            System.out.println("");
            choice = man.menuShare();
            switch (choice) {
                case 1:
                    //share friend
                    man.sharePostWithAllFriends(login, post);
                    break;
                case 2:
                    //share all
                    man.sharePostWithAllUsers(login, post);
                    break;
                case 3:
                    a = false;
                    break;
            }
        }
    }

}
