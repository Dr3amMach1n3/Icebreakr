/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Pierce
 */
public class SwipeQueue implements Serializable{
    private ArrayBlockingQueue<String> swipeQueue;
    
    public SwipeQueue() {
        swipeQueue = new ArrayBlockingQueue(16);
    }
    
    public String pollUser(String username, Connection connection) throws SQLException{
        if(swipeQueue.isEmpty()){
            loadQueue(username, connection);
        }
        return swipeQueue.poll();
    }
    
    public String peekUser(String username, Connection connection) throws SQLException{
        if(swipeQueue.isEmpty()){
            loadQueue(username, connection);
        }
        return swipeQueue.peek();
    }
   
    private void loadQueue(String username, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT gender, lookingfor, hobbies FROM User WHERE username=?");
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        result.next();
        int gender = result.getInt("gender");
        int preference = result.getInt("lookingfor");  
        int hobbies = result.getInt("hobbies");
        result = statement.executeQuery("SELECT username, lookingfor, hobbies FROM User WHERE " + compatibleGendersQuery(preference));
        
        HashMap<String, Integer> compatibleUsers =  new HashMap<String, Integer>();
        while(result.next()){
            if(isGenderCompatible(result.getInt("lookingfor"), gender)){
                compatibleUsers.put(result.getString("username"), hobbyScore(hobbies, result.getInt("hobbies")));
            }
        }
        //users you havent swiped on that swiped yes to you
        result = statement.executeQuery("SELECT userB FROM Swipe WHERE userA='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "B") + ") AND decisionB='1' AND decisionA IS NULL");
        queueByHobbies(result, compatibleUsers, "B");
        if(swipeQueue.remainingCapacity() > 0){
            result = statement.executeQuery("SELECT userA FROM Swipe WHERE userB='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "A") + ") AND decisionA='1' AND decisionB IS NULL");
            queueByHobbies(result, compatibleUsers, "A");
        }
        //users you havent swiped on that havent swiped on you
        if(swipeQueue.remainingCapacity() > 0){
            result = statement.executeQuery("SELECT userB FROM Swipe WHERE userA='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "B") + ") AND decisionB IS NULL AND decisionA IS NULL");
            queueByHobbies(result, compatibleUsers, "B");
        }
        if(swipeQueue.remainingCapacity() > 0){
            result = statement.executeQuery("SELECT userA FROM Swipe WHERE userB='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "A") + ") AND decisionA IS NULL AND decisionB IS NULL");
            queueByHobbies(result, compatibleUsers, "A");
        }
        //users you swiped no to that swiped yes to you
        if(swipeQueue.remainingCapacity() > 0){
            result = statement.executeQuery("SELECT userB FROM Swipe WHERE userA='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "B") + ") AND decisionB='1' AND decisionA='0'");
            queueByHobbies(result, compatibleUsers, "B");
        }
        if(swipeQueue.remainingCapacity() > 0){
            result = statement.executeQuery("SELECT userA FROM Swipe WHERE userB='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "A") + ") AND decisionA='1' AND decisionB='0'");
            queueByHobbies(result, compatibleUsers, "A");
        }
        //users you swiped no to that haven't swiped to you
        if(swipeQueue.remainingCapacity() > 0){
            result = statement.executeQuery("SELECT userB FROM Swipe WHERE userA='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "B") + ") AND decisionB IS NULL AND decisionA='0'");
            queueByHobbies(result, compatibleUsers, "B");
        }
        if(swipeQueue.remainingCapacity() > 0){
            result = statement.executeQuery("SELECT userA FROM Swipe WHERE userB='" + username + "' AND (" + compatibleUsersQuery(compatibleUsers, "A") + ") AND decisionA IS NULL AND decisionB='0'");
            queueByHobbies(result, compatibleUsers, "A");
        }
        
    }
    
    private boolean isGenderCompatible(int preference, int gender){
        int bitMatcher = 1 << gender;
        if((preference & bitMatcher) == 0){
            return false;
        }else{
            return true;
        }
    }
    
    private String compatibleGendersQuery(int preference){
        String returnMe = "";
        boolean first = true;
        int bitMatcher = 1;
        
        for(int i = 0; i < User.Genders.values().length; i++){
            if((bitMatcher & preference) != 0){
                if(!first){
                    returnMe += " OR "; 
                }
                returnMe += "gender='" + Integer.toString(i) + "'";
                first = false;
            }
            bitMatcher <<= 1;
        }
        return returnMe;
    }
    
    private String compatibleUsersQuery(HashMap<String, Integer> compatibleUsers, String suffix){
        String compatibleUsersQuery = "";
        boolean first = true;
        for(String user : compatibleUsers.keySet()){
            if(!first){
                compatibleUsersQuery += " OR ";
            }
            compatibleUsersQuery += "user" + suffix + "='" + user + "'";
            first = false;
        }
        return compatibleUsersQuery;
    }
    
    private int hobbyScore(int hobbiesA, int hobbiesB){
        int sameHobbies = hobbiesA & hobbiesB;
        int score = 0;
        while(sameHobbies != 0){
            score += sameHobbies & 1;
            sameHobbies >>>= 1;
        }
        return score;
    }
    
    private ArrayList<String> sortUsersMap(HashMap<String, Integer> userMap){
        ArrayList<String> returnMe = new ArrayList();
        for(Map.Entry<String, Integer> entry : userMap.entrySet()){
            if(returnMe.size() < swipeQueue.remainingCapacity()){
                int i;
                for(i = 0; i < returnMe.size(); i++){
                    if(entry.getValue() >= userMap.get(returnMe.get(i))){
                        break;
                    }
                }
                returnMe.add(i, entry.getKey());
            }else{
                break;
            }
        }
        return returnMe;
    }
    
   private void queueByHobbies(ResultSet result, HashMap<String, Integer> compatibleUsers, String suffix) throws SQLException{
       HashMap<String, Integer> queueWorthy = new HashMap();
       while(result.next()){
           String username = result.getString("user" + suffix);
           queueWorthy.put(username, compatibleUsers.get(username));
       }
       if(!queueWorthy.isEmpty()){
           swipeQueue.addAll(sortUsersMap(queueWorthy));
       }
   }
}
