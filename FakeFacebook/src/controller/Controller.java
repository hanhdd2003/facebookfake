/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    public Set<User> getFriends(User user) {

        Set<User> friendList = new HashSet<>();
        for (Vertex<User> friendVertex : listUser.findNeighbors(user)) {
            friendList.add(friendVertex.getLabel());
        }
        return friendList;
    }

    public boolean addUser(String id, String name, String address, String dateOfBirth) {
        User newUser = new User(id, name, address, dateOfBirth);
        if (this.checkIdUser(id)) {
            return false;
        }
        listUser.addV(newUser);
        return true;
    }

    public boolean addFriend(String id1, String id2) {
        User user1 = getUserById(id1);
        User user2 = getUserById(id2);

        if (user1 != null && user2 != null) {
            listUser.addE(user1, user2);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String id) {
        User user = getUserById(id);

        if (user != null) {
            listUser.removeV(user);
            return true;
        }
        return false;
    }

    public boolean deleteFriend(User user1, User user2) {
        if (user1 != null && user2 != null) {
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

   
}
