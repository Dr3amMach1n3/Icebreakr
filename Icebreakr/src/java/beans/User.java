/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Pierce, Logan
 */
public class User implements Serializable{
    public enum Hobbies {Yoga, Musician, Singing, Dancing, Art, Hiking, Biking,
    Swimming, Cooking, Gardening, Driving, Comedy, Fighting, Philosophy, Buisness, Investing}
    public enum Genders {
        Male, Female, Transgender, Transsexual, Non_Binary;
        @Override
        public String toString() {
            if(null == this) {
                return "";
            } else switch (this) {
                    case Non_Binary:
                        return "Non Binary";
                    default:
                        return this.name();
                }
        }
    }
    public enum HairColors {
        Brown, Bald, Blonde, Dirty_Blonde, Black, Auburn, Ginger,Gray, White, Silver, Red, Orange, Yellow, Green, Blue, Multi_Colored;
        @Override
        public String toString() {
            if(null == this) {
                return "";
            } else switch (this) {
                    case Multi_Colored:
                        return "Multi-Colored";
                    default:
                        return this.name();
                }
        }
    }
    
    private String username;
    private String password;
    private String name;
    private Date birthday;
    private String age_string;
    private Genders gender;
    private String gender_string;
    private ArrayList<Genders> lookingfor;
    private String lookingfor_string;
    private String location;
    private ArrayList<Hobbies> hobbies;
    private String hobbies_string;
    private String starters;
    private int heightFeet;
    private int heightInches;
    private HairColors hairColor;
    private String hairColor_string;
    private String zodiac;
    private int children;
    private int weight;
    private String eyeColor;
    private String ethnicity;
    private boolean glasses;
    private String glasses_string;
    private int score;

    public void clear(){
        username = null;
        password = null;
        name = null;
        birthday = null;
        age_string = null;
        gender = null;
        gender_string = null;
        lookingfor = null;
        lookingfor_string = null;
        location = null;
        hobbies = null;
        hobbies_string = null;
        starters = null;
        heightFeet = 0;
        heightInches = 0;
        hairColor = null;
        hairColor_string = null;
        zodiac = null;
        children = 0;
        weight = 0;
        eyeColor = null;
        ethnicity = null;
        glasses = false;
        glasses_string = null;
        score = 0;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public void setAge_string(String age) {
        this.age_string = age;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }
    
    public void setGender_string(String gender) {
        this.gender_string = gender;
    }
    
    public void setLookingfor(ArrayList<Genders> lookingfor) {
        this.lookingfor = lookingfor;
    }
    
    public void setLookingfor_string(String lookingfor) {
        this.lookingfor_string = lookingfor;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHobbies(ArrayList<Hobbies> hobbies) {
        this.hobbies = hobbies;
    }
    
    public void setHobbies_string(String hobbies) {
        this.hobbies_string = hobbies;
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

    public void setHairColor(HairColors hairColor) {
        this.hairColor = hairColor;
    }
    
    public void setHairColor_string(String hairColor) {
        this.hairColor_string = hairColor;
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
        this.eyeColor = eyecolor;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setGlasses(boolean glasses) {
        this.glasses = glasses;
    }
    
    public void setGlasses_string(String glasses) {
        this.glasses_string = glasses;
    }
    
    public void setScore(int score) {
        this.score = score;
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
    
    public Date getBirthday() {
        return birthday;
    }
    
    public String getAge_string() {
        return age_string;
    }

    public Genders getGender() {
        return gender;
    }
    
    public String getGender_string() {
        return gender_string;
    }
    
    public ArrayList<Genders> getLookingfor() {
        return lookingfor;
    }
    
    public String getLookingfor_string() {
        return lookingfor_string;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Hobbies> getHobbies() {
        return hobbies;
    }
    
    public String getHobbies_string() {
        return hobbies_string;
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

    public HairColors getHairColor() {
        return hairColor;
    }
    
    public String getHairColor_string() {
        return hairColor_string;
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
        return eyeColor;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public boolean isGlasses() {
        return glasses;
    }
    
    public String getGlasses_string() {
        return glasses_string;
    }
    
    public int getScore() {
        return score;
    }
    
    /** Use the birthday to return the current age
     * 
     * @return this user's current age in years
     */
    public int generateAge() {
        /* java.Date is deprecated, so convert to java.Calendar */
        long time = this.birthday.getTime();
        
        Calendar now = Calendar.getInstance();
        Calendar bday = Calendar.getInstance();
        bday.setTimeInMillis(time);
        
        int age = now.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
        /* account for when user hasn't had birthday yet */
        if(now.get(Calendar.DAY_OF_YEAR) < bday.get(Calendar.DAY_OF_YEAR)) {
            age = age - 1;
        }
        
        return age;
    }
    
    /** Generates a yes/no response to the question "Does this user wear glasses"
     * 
     * @return either "yes" or "no" depending on the glasses var
     */
    public String wearsGlasses() {
        if(this.glasses) {
            return "Yes";
        } else {
            return "No";
        }
    }
    
    /** Finds the user's Zodiac symbol based on their birthday
     * 
     * @return the user's Zodiac as a string
     */
    public String generateZodiac() {
        /* java.Date is deprecated, so convert to java.Calendar */
        long time = this.birthday.getTime();
        
        Calendar bday = Calendar.getInstance();
        bday.setTimeInMillis(time);
        
        int month = bday.get(Calendar.MONTH);
        int day = bday.get(Calendar.DAY_OF_MONTH);
        
        switch(month) {
            case 0:
                if(day < 20) {
                    return "Capricorn";
                } else {
                    return "Aquarius";
                }
            case 1:
                if(day < 19) {
                    return "Aquarius";
                } else {
                    return "Pisces";
                }
            case 2:
                if(day < 21) {
                    return "Pisces";
                } else {
                    return "Aries";
                }
            case 3:
                if(day < 20) {
                    return "Aries";
                } else {
                    return "Taurus";
                }
            case 4:
                if(day < 21) {
                    return "Taurus";
                } else {
                    return "Gemini";
                }
            case 5:
                if(day < 21) {
                    return "Gemini";
                } else {
                    return "Cancer";
                }
            case 6:
                if(day < 23) {
                    return "Cancer";
                } else {
                    return "Leo";
                }
            case 7:
                if(day < 23) {
                    return "Leo";
                } else {
                    return "Virgo";
                }
            case 8:
                if(day < 23) {
                    return "Virgo";
                } else {
                    return "Libra";
                }
            case 9:
                if(day < 23) {
                    return "Libra";
                } else {
                    return "Scorpio";
                }
            case 10:
                if(day < 22) {
                    return "Scorpio";
                } else {
                    return "Sagittarius";
                }
            case 11:
                if(day < 22) {
                    return "Sagittarius";
                } else {
                    return "Capricorn";
                }
            default:
                return "Scorpio";
        }
    }
    
    /** Create a String list of desired genders
     * 
     * @return the list of desired genders
     */
    public String generateLookingFor() {
        String return_string = new String();
        for(Genders g : this.lookingfor) {
            return_string = return_string.concat(g.toString());
            return_string = return_string.concat(", ");
        }
        return_string = return_string.substring(0, return_string.length() - 2); //remove last ", "
        return return_string;
    }
    
    /** Create a String list of hobbies
     * 
     * @return the list of hobbies
     */
    public String generateHobbies() {
        String return_string = new String();
        for(Hobbies h : this.hobbies) {
            return_string = return_string.concat(h.toString());
            return_string = return_string.concat(", ");
        }
        return_string = return_string.substring(0, return_string.length() - 2); //remove last ", "
        return return_string;
    }
    
    public void addToScore(int i) {
        this.setScore(this.getScore() + i);
    }
}
