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
    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;
    private String picture5;
    private String picture6;
    private String picture7;
    private String picture8;
    private String picture9;
    private int pictureCount;
    
    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }
    
    public void setPicture1(String url) {
        this.picture1 = url;
    }
    
    public void setPicture2(String url) {
        this.picture1 = url;
    }
    
    public void setPicture3(String url) {
        this.picture1 = url;
    }
    
    public void setPicture4(String url) {
        this.picture1 = url;
    }
    
    public void setPicture5(String url) {
        this.picture1 = url;
    }
    
    public void setPicture6(String url) {
        this.picture1 = url;
    }
    
    public void setPicture7(String url) {
        this.picture1 = url;
    }
    
    public void setPicture8(String url) {
        this.picture1 = url;
    }
    
    public void setPicture9(String url) {
        this.picture1 = url;
    }
    
    public void setPictureCount() {
        int count = 0;
        for(String pict : pictures) {
            if(!(pict.equals("test.jpg"))) {
                count++;
            }
        }
        this.pictureCount = count;
    }
    
    public String getPicture1() {
        return picture1;
    }
    
    public String getPicture2() {
        return picture2;
    }
    
    public String getPicture3() {
        return picture3;
    }
    
    public String getPicture4() {
        return picture4;
    }
    
    public String getPicture5() {
        return picture5;
    }
    
    public String getPicture6() {
        return picture6;
    }
    
    public String getPicture7() {
        return picture7;
    }
    
    public String getPicture8() {
        return picture8;
    }
    
    public String getPicture9() {
        return picture9;
    }
    
    public int getPictureCount() {
        return pictureCount;
    }

    public void addPicture(String url) {
        if(pictures.size() < 9) {
            pictures.add(url);
        }
    }
    
    public void addPicture(int i, String url) {
        if(i > 0 && i < 10) {
            pictures.set(i, url);
        }
    }

    public String getPicture(int i) {
        if(i > 0 && i < 10) {
            return pictures.get(i);
        } else {
            return pictures.get(1);
        }
    }
    
    public void pad() {
        while (pictures.size() < 9) {
            pictures.add("test.jpg");
        }
    }
    
}
