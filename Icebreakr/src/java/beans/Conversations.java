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
    
    private ArrayList<String> users;
    
    public Conversations() {
        users = new ArrayList<String>();
    }
    
    public void addUsers(String u) {
        users.add(u);
    }
    
    public void addUsers(ArrayList<String> u) {
        users.addAll(u);
    }
    
    public void sort() {
        Collections.sort(users);
    }

    public String getUsers(int i) {
        return users.get(i);
    }
}
