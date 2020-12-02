/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Pierce
 */
public class Pictures implements Serializable{
    private ArrayList<String> pictures;

    public void addPicture(String url) {
        pictures.add(url);
    }
    
    public void addPicture(int i, String url) {
        pictures.set(i,url);
    }

    public String getPicture(int i) {
        return pictures.get(i);
    }
    
    public void pad() {
        while (pictures.size() < 9) {
            pictures.add("test.jpg");
        }
    }
    
}
