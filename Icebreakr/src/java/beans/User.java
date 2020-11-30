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
public class User implements Serializable{
    private String username;
    private String password;
    private String name;
    private String gender;
    private String location;
    public enum Hobbies {Yoga, Musician, Singing, Dancing, Art, Hiking, Biking,
    Swimming, Cooking, Gardening, Driving, Comedy, Fighting, Philosophy, Buisness, Investing}
    private ArrayList<Hobbies> hobbies;
    private String starters;
    private int heightFeet;
    private int heightInches;
    private String hairColor;
    private String zodiac;
    private int children;
    private int weight;
    private String eyecolor;
    private String ethnicity;
    private boolean glasses;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHobbies(ArrayList<Hobbies> hobbies) {
        this.hobbies = hobbies;
    }
    
    public void setStarters(String starters) {
        this.starters = starters;
    }

    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setEyecolor(String eyecolor) {
        this.eyecolor = eyecolor;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setGlasses(boolean glasses) {
        this.glasses = glasses;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Hobbies> getHobbies() {
        return hobbies;
    }

    public String getStarters() {
        return starters;
    }

    public int getHeightFeet() {
        return heightFeet;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getZodiac() {
        return zodiac;
    }

    public int getChildren() {
        return children;
    }

    public int getWeight() {
        return weight;
    }

    public String getEyecolor() {
        return eyecolor;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public boolean isGlasses() {
        return glasses;
    }
    
}
