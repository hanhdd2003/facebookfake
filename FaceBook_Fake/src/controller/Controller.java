/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import object.Post;
import object.User;

/**
 *
 * @author Hanh
 */
public class Controller {

    private MyGraph<User> listUser = new MyGraph<>();

    public MyGraph<User> getListUser() {
        return this.listUser;
    }

    public User getUserById(String userId) {
        for (Vertex<User> vertice : listUser.getVertices()) {
            User user = vertice.getLabel();
            if (user.getUserID().equalsIgnoreCase(userId)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByAccount(String username, String password){
        for (Vertex<User> vertice : listUser.getVertices()) {
            User user = vertice.getLabel();
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public Set<User> getFriends(User user) {
        Set<User> friendList = new HashSet<>();
        for (Vertex<User> friendVertex : listUser.findNeighbors(user)) {
            friendList.add(friendVertex.getLabel());
        }
        return friendList;
    }

    public boolean addUser(String id, String name, String address, String dateOfBirth, String username, String password) {
        User newUser = new User(id, name, address, dateOfBirth,username, password);
        if (this.checkIdUser(id) && this.checkUsernameExist(username)) {
            return false;
        }
        listUser.addV(newUser);
        return true;
    }

    public boolean addFriend(User user1, User user2) {
        if (user1 != null && user2 != null && !user1.getUserID().equalsIgnoreCase(user2.getUserID())) {
            listUser.addE(user1, user2);
            return true;
        }
        return false;
    }
    
    public boolean addFriendById(String u1, String u2) {
        User user1 = this.getUserById(u1);
        User user2 = this.getUserById(u2);
        if (user1 != null && user2 != null && !user1.getUserID().equalsIgnoreCase(user2.getUserID())) {
            listUser.addE(user1, user2);
            return true;
        }
        return false;
    }

    public boolean deleteUser(User user) {
        if (user != null) {
            listUser.removeV(user);
            return true;
        }
        return false;
    }

    public boolean deleteFriend(User user1, User user2) {
        if(checkFriend(user1, user2) && user1 != null && user2 != null){
            listUser.removeE(user1, user2);
            return true;
        }
        return false;
    }

    public void deletePostShare(String postId) {
        for (Vertex<User> vertex : listUser.getVertices()) {
            User user = vertex.getLabel();
            Post postShare = user.getPost("S" + postId);
            if (postShare != null) {
                user.getPosts().remove(postShare);
            }
        }
    }
    
    public boolean checkIdUser(String id) {
        for (Vertex<User> vertice : getListUser().vertices) {
            if (vertice.getLabel().getUserID().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUsernameExist(String userName){
        for (Vertex<User> vertice : getListUser().vertices) {
            if (vertice.getLabel().getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }
   
    public boolean checkFriend(User u1, User u2){
        if(listUser.containEdge(u1, u2)){
            return true;
        }
        return false;
    }
    
    public Set<User> search(String content){
        Set<User> result = new HashSet<>();
        for (Vertex<User> vertex : listUser.getVertices()) {
            User user = vertex.getLabel();
            if(user.getUserID().toLowerCase().contains(content.trim().toLowerCase())
                    || user.getName().toLowerCase().contains(content.trim().toLowerCase())
                    || user.getAddress().toLowerCase().contains(content.trim().toLowerCase())){
                result.add(user);
            }
        }
        return result;
    }
    
}
