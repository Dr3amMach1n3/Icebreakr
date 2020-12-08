/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Pierce
 */
public class Conversations implements Serializable{
    
    private String currentUsername;
    private ArrayList<User> users;
    
    public Conversations() {
        users = new ArrayList<User>();
    }
    
    public void setCurrentUsername(String u) {
        currentUsername = u;
    }
    
    public String getCurrentUsername() {
        return currentUsername;
    }
    
    public void addUsers(User u) {
        users.add(u);
    }
    
    public void addUsers(ArrayList<User> u) {
        users.addAll(u);
    }
    
/*    public void sort() {
        Collections.sort(users);
    }*/
    
    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(int i) {
        return users.get(i);
    }
}
