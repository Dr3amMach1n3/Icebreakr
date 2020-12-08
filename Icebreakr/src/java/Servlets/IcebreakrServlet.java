/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import beans.*;
import beans.User.Genders;
import beans.User.HairColors;
import beans.User.Hobbies;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Pierce, Logan, Wesley
 */
public class IcebreakrServlet extends HttpServlet {
    
    private String getBitString(HttpServletRequest request, int numArgs, String prefix){
        int returnMe = 0;
        int bitSetter = 1;
        for(int i = 0; i < numArgs; i++){
            if(request.getParameter(prefix + Integer.toString(i)) != null){
                returnMe |= bitSetter;
            }
            bitSetter <<= 1;
        }
        return Integer.toString(returnMe);
    }
    
    /** Attempt to populate the specified user with information from the database
     * 
     * @param user the user to add info to
     * @param username the user's username
     * @param connection the current database connection
     */
    private void loadUser(User user, String username, Connection connection){
        try{
            String preparedSQL = "SELECT * FROM User WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, username);
            ResultSet results = ps.executeQuery();
            
            //set all parameters in user to values in database
            results.next();
            user.setUsername(results.getNString(1));
            user.setPassword(results.getNString(2));
            user.setName(results.getNString(3));
            user.setBirthday(results.getDate(4));
            user.setAge_string(Integer.toString(user.generateAge()));
            user.setZodiac(user.generateZodiac());
            int gender = results.getInt(5);
            switch(gender) {
                case 0:
                    user.setGender(Genders.Male);
                    break;
                case 1:
                    user.setGender(Genders.Female);
                    break;
                case 2:
                    user.setGender(Genders.Transgender);
                    break;
                case 3:
                    user.setGender(Genders.Transsexual);
                    break;
                default:
                    user.setGender(Genders.Non_Binary);
            }
            user.setGender_string(user.getGender().toString());
            int lookingfor = results.getInt(6);
            ArrayList<Genders> l_lookingfor = new ArrayList<>();
            for(int i = 0; i < 5; i++) {
                int bit = lookingfor & 1;
                if(bit > 0) {
                    switch(i) {
                        case 0:
                            l_lookingfor.add(Genders.Male);
                            break;
                        case 1:
                            l_lookingfor.add(Genders.Female);
                            break;
                        case 2:
                            l_lookingfor.add(Genders.Transgender);
                            break;
                        case 3:
                            l_lookingfor.add(Genders.Transsexual);
                            break;
                        default:
                            l_lookingfor.add(Genders.Non_Binary);
                    }
                }
                lookingfor = lookingfor >> 1;
            }
            user.setLookingfor(l_lookingfor);
            user.setLookingfor_string(user.generateLookingFor());
            user.setLocation(results.getNString(7));
            int hobbies = results.getInt(8);
            ArrayList<Hobbies> l_hobbies = new ArrayList<>();
            for(int i = 0; i < 16; i++) {
                int bit = hobbies & 1;
                if(bit > 0) {
                    switch(i) {
                        case 0:
                            l_hobbies.add(Hobbies.Yoga);
                            break;
                        case 1:
                            l_hobbies.add(Hobbies.Musician);
                            break;
                        case 2:
                            l_hobbies.add(Hobbies.Singing);
                            break;
                        case 3:
                            l_hobbies.add(Hobbies.Dancing);
                            break;
                        case 4:
                            l_hobbies.add(Hobbies.Art);
                            break;
                        case 5:
                            l_hobbies.add(Hobbies.Hiking);
                            break;
                        case 6:
                            l_hobbies.add(Hobbies.Biking);
                            break;
                        case 7:
                            l_hobbies.add(Hobbies.Swimming);
                            break;
                        case 8:
                            l_hobbies.add(Hobbies.Cooking);
                            break;
                        case 9:
                            l_hobbies.add(Hobbies.Gardening);
                            break;
                        case 10:
                            l_hobbies.add(Hobbies.Driving);
                            break;
                        case 11:
                            l_hobbies.add(Hobbies.Comedy);
                            break;
                        case 12:
                            l_hobbies.add(Hobbies.Fighting);
                            break;
                        case 13:
                            l_hobbies.add(Hobbies.Philosophy);
                            break;
                        case 14:
                            l_hobbies.add(Hobbies.Buisness);
                            break;
                        default:
                            l_hobbies.add(Hobbies.Investing);
                    }
                }
                hobbies = hobbies >> 1;
            }
            user.setHobbies(l_hobbies);
            user.setHobbies_string(user.generateHobbies());
            user.setStarters(results.getNString(9));
            int height = results.getInt(10);
            user.setHeightInches(height % 12);
            user.setHeightFeet((height - (height % 12)) / 12);
            int hairColor = results.getInt(11);
            switch(hairColor) {
                case 0:
                    user.setHairColor(HairColors.Brown);
                    break;
                case 1:
                    user.setHairColor(HairColors.Bald);
                    break;
                case 2:
                    user.setHairColor(HairColors.Blonde);
                    break;
                case 3:
                    user.setHairColor(HairColors.Dirty_Blonde);
                    break;
                case 4:
                    user.setHairColor(HairColors.Black);
                    break;
                case 5:
                    user.setHairColor(HairColors.Auburn);
                    break;
                case 6:
                    user.setHairColor(HairColors.Ginger);
                    break;
                case 7:
                    user.setHairColor(HairColors.Gray);
                    break;
                case 8:
                    user.setHairColor(HairColors.White);
                    break;
                case 9:
                    user.setHairColor(HairColors.Silver);
                    break;
                case 10:
                    user.setHairColor(HairColors.Red);
                    break;
                case 11:
                    user.setHairColor(HairColors.Orange);
                    break;
                case 12:
                    user.setHairColor(HairColors.Yellow);
                    break;
                case 13:
                    user.setHairColor(HairColors.Green);
                    break;
                case 14:
                    user.setHairColor(HairColors.Blue);
                    break;
                default:
                    user.setHairColor(HairColors.Multi_Colored);
            }
            user.setHairColor_string(user.getHairColor().toString());
            user.setChildren(results.getInt(13));
            user.setWeight(results.getInt(14));
            user.setEthnicity(results.getNString(16));
            user.setGlasses(results.getBoolean(17));
            user.setGlasses_string(user.wearsGlasses());
        } catch (SQLException ex) {
            Logger.getLogger(IcebreakrServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Attempt to populate the specified picture collection with information from the database
     * 
     * @param pictures the picture collection to be updated
     * @param username the corresponding user of the photos
     * @param connection the current database connection
     */
    private void loadPictures(Pictures pictures, String username, Connection connection){
        try {
            for(int i = 1; i < 10; i++) {
                String preparedSQL = "SELECT * FROM Photo WHERE username = ? AND position = ?";
                PreparedStatement ps = connection.prepareStatement(preparedSQL);
                ps.setString(1, username);
                ps.setString(2, Integer.toString(i));
                ResultSet results = ps.executeQuery();
            
                //set all parameters in user to values in database
                String url = results.getNString(2);
                switch(i) {
                    case 1:
                        pictures.setPicture1(url);
                        break;
                    case 2:
                        pictures.setPicture2(url);
                        break;
                    case 3:
                        pictures.setPicture3(url);
                        break;
                    case 4:
                        pictures.setPicture4(url);
                        break;
                    case 5:
                        pictures.setPicture5(url);
                        break;
                    case 6:
                        pictures.setPicture6(url);
                        break;
                    case 7:
                        pictures.setPicture7(url);
                        break;
                    case 8:
                        pictures.setPicture8(url);
                        break;
                    case 9:
                        pictures.setPicture9(url);
                        break;
                }
                ps.close();
                results.close();
            }
            pictures.updatePictureCount();
        } catch (SQLException ex) {
            Logger.getLogger(IcebreakrServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IcebreakrServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IcebreakrServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String driver = "org.mariadb.jdbc.Driver";
            Class.forName(driver);
            String action = request.getParameter("action");
            
            String dbURL = "jdbc:mariadb://localhost:3306/apollo9_icebreakr";
            String dbUsername = "apollo9";
            String dbPassword = "DreamTeam69(nice)";
            String url = "index.jsp";
            Connection dbConnection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
          
            String genderPrefix = "g";
            String hobbyPrefix = "h";
            int numGenders = 5;
            int numHobbies = 16;
            HttpSession session = request.getSession();
            CredentialError credErr = (CredentialError) session.getAttribute("credErr");
            if(credErr == null){
                credErr = new CredentialError();
                session.setAttribute("credErr", credErr);
            }
            credErr.setNameErr(false);
            credErr.setPassErr(false);
            credErr.setEmptyErr(false);
            
            User currentUser = (User) session.getAttribute("currentUser");
            if(currentUser == null){
                currentUser = new User();
                session.setAttribute("currentUser", currentUser);
            }
            Pictures currentPictures = (Pictures) session.getAttribute("currentPictures");
            if(currentPictures == null){
                currentPictures = new Pictures();
                session.setAttribute("currentPictures", currentPictures);
            }
            User otherUser = (User) session.getAttribute("otherUser");
            if(otherUser == null){
                otherUser = new User();
                session.setAttribute("otherUser", otherUser);
            }
            Pictures otherPictures = (Pictures) session.getAttribute("otherPictures");
            if(otherPictures == null){
                otherPictures = new Pictures();
                session.setAttribute("otherPictures", otherPictures);
            }
            
            if(action == null){
                action = "logout";
            }
            //Determine servlet action
            if (action.equals("login")){
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                PreparedStatement statement = dbConnection.prepareStatement("SELECT password FROM User WHERE username=?");
                statement.setString(1, username);
                ResultSet result = statement.executeQuery();
                boolean notEmpty = result.next();
                
                if(!notEmpty){
                    credErr.setNameErr(true);
                    url = "index.jsp";
                }else if(!password.equals(result.getString("password"))){
                    credErr.setPassErr(true);
                    url = "index.jsp";
                }else{
                    loadPictures(currentPictures, username, dbConnection);
                    loadUser(currentUser, username, dbConnection);
                    url = "profile.jsp";
                }
                
                statement.close();
                result.close();
            }else if(action.equals("register")){
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                
                PreparedStatement statement = dbConnection.prepareStatement("SELECT username FROM User WHERE username=?");
                statement.setString(1, username);
                ResultSet result = statement.executeQuery();
                boolean notEmpty = result.next();
                
                //Succesful register
                if(!notEmpty && password.equals(repassword)){

                    
                    String name = request.getParameter("name");
                    String birthday = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
                    String gender = request.getParameter("gender");
                    String lookingfor = getBitString(request, numGenders, genderPrefix);
                    String location = request.getParameter("location");
                    String hobbies = getBitString(request, numHobbies, hobbyPrefix);
                    String starters = request.getParameter("starters");
                    
                    if(name != "" && location != "" && starters != "" && password != ""){
                       statement = dbConnection.prepareStatement("SELECT username FROM User");
                        result = statement.executeQuery();

                        String createSwipe = "INSERT INTO Swipe (userA,userB) VALUES ";
                        while(result.next()){
                            if(result.getRow() != 1){
                                createSwipe += ", ";
                            }
                            createSwipe += "(?,'" + result.getString("username") + "')";
                        }
                        statement = dbConnection.prepareStatement(createSwipe);
                        result.previous();
                        for(int i = 1; i <= result.getRow(); i++){
                            statement.setString(i, username);
                        }
                        result.beforeFirst();
                        if(result.next()){
                            statement.executeUpdate();
                        }
                        
                        statement = dbConnection.prepareStatement("INSERT INTO User (username, password, name, birthday, gender, lookingfor, location, hobbies, starters) VALUES (?,?,?,?,?,?,?,?,?)");
                        statement.setString(1, username);
                        statement.setString(2, password);
                        statement.setString(3, name);
                        statement.setString(4, birthday);
                        statement.setString(5, gender);
                        statement.setString(6, lookingfor);
                        statement.setString(7, location);
                        statement.setString(8, hobbies);
                        statement.setString(9, starters);
                        statement.executeUpdate();
                        loadUser(currentUser, username, dbConnection);
                        
                        for(int i = 1; i < 10; i++){
                            statement = dbConnection.prepareStatement("INSERT INTO Photo (username, source, position) VALUES (?, ?, ?)");
                            statement.setString(1, username);
                            statement.setString(2, "test.jpg");
                            statement.setString(3, Integer.toString(i));
                            statement.executeUpdate();
                        }
                        loadPictures(currentPictures, username, dbConnection);
                        
                        url = "profile.jsp";
                    }else{
                        credErr.setEmptyErr(true);
                        url = "signup.jsp";
                    } 
                }else{
                    if(notEmpty){
                        credErr.setNameErr(true);
                    }
                    if(!password.equals(repassword)){
                        credErr.setPassErr(true);
                    }
                    url = "signup.jsp";
                }
                statement.close();
                result.close();
            /* code for when a logged-in user wants to return to their profile */
            }else if(action.equals("profile")){
                String username = request.getParameter("username");
                loadUser(currentUser, username, dbConnection);
                loadPictures(currentPictures, username, dbConnection);
                url = "profile.jsp";
            /* code for when a user updates their profile */
            }else if(action.equals("update_profile")){
                /* get the updated info */
                String username = currentUser.getUsername();
                //String gender = currentUser.getGender_string();
                String lookingfor = getBitString(request, numGenders, genderPrefix);
                String location = currentUser.getLocation();
                String hobbies = getBitString(request, numHobbies, hobbyPrefix);
                String starters = request.getParameter("starters");
                String height = "0";
                if(request.getParameter("height_feet") != null && request.getParameter("height_inches") != null) {
                    try {
                        int hf = Integer.parseInt(request.getParameter("height_feet"));
                        int hi = Integer.parseInt(request.getParameter("height_inches"));
                        if(hf < 1 || hf > 9 || hi < 0 || hi > 11) {
                            height = "0";
                        } else {
                            height = Integer.toString((12 * hf) + hi);
                        }
                    } catch(Exception e) {
                        height = "0";
                    }
                }
                String haircolor = request.getParameter("hair_color");
                String children = request.getParameter("children");
                if(children == null || children.equals("")) {
                    children = "0";
                }
                String weight = request.getParameter("weight");
                if(weight == null || weight.equals("")) {
                    weight = "0";
                }
                String ethnicity = request.getParameter("ethnicity");
                if(ethnicity == null || ethnicity.equals("")) {
                    ethnicity = "";
                }
                String glasses = request.getParameter("glasses");
                
                /* attempt to insert the new data into the database */
                PreparedStatement statement = dbConnection.prepareStatement("UPDATE User SET lookingfor=?, location=?, hobbies=?, starters=?, height=?, haircolor=?, children=?, weight=?, ethnicity=?, glasses=? WHERE User.username=?");
                //statement.setString(1, gender);
                statement.setString(1, lookingfor);
                statement.setString(2, location);
                statement.setString(3, hobbies);
                statement.setString(4, starters);
                statement.setString(5, height);
                statement.setString(6, haircolor);
                statement.setString(7, children);
                statement.setString(8, weight);
                statement.setString(9, ethnicity);
                statement.setString(10, glasses);
                statement.setString(11, username);
                
                statement.executeUpdate();
                
                loadUser(currentUser, username, dbConnection); //load the updated profile
                loadPictures(currentPictures, username, dbConnection);
                url = "profile.jsp";
                
                statement.close();
            }else if(action.equals("logout")){
                session.setAttribute("credErr", null);
                session.setAttribute("currentUser", null);
                session.setAttribute("otherUser", null);
                session.setAttribute("swipeQueue", null);
                url = "index.jsp";
            }else if(action.equals("preview_user")){
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("SELECT COUNT(username) FROM User");
                int numUsers = result.getInt(1);
                Random rand =  new Random();
                result = statement.executeQuery("SELECT username FROM User ORDER BY username LIMIT 1 OFFSET " + Integer.toString(rand.nextInt(numUsers)));
                result.next();
                String username = result.getString("username");
                loadPictures(otherPictures, username, dbConnection);
                loadUser(otherUser, username, dbConnection);
                url = "other_profile.jsp";
                result.close();
                statement.close();
            }else if(action.equals("other_user")){
                String username = request.getParameter("target");
                loadUser(otherUser, username, dbConnection);
                loadPictures(otherPictures, username, dbConnection);
                url = "other_profile.jsp";
            }else if(action.equals("match")){
                String username = currentUser.getUsername();

                SwipeQueue swipeQueue = (SwipeQueue) session.getAttribute("swipeQueue");
                if(swipeQueue == null){
                    swipeQueue = new SwipeQueue();
                    session.setAttribute("swipeQueue", swipeQueue);
                }
                String nextUser = swipeQueue.peekUser(username, dbConnection);
                if(nextUser != null){
                    loadPictures(otherPictures, nextUser, dbConnection);
                    loadUser(otherUser, nextUser, dbConnection);
                }else{
                    otherUser.clear();
                }
                url = "match.jsp";
            }else if(action.equals("swipe")){
                String username = currentUser.getUsername();
                String swipe = request.getParameter("swipe");
                SwipeQueue swipeQueue = (SwipeQueue) session.getAttribute("swipeQueue");
                if(swipeQueue == null){
                    swipeQueue = new SwipeQueue();
                    session.setAttribute("swipeQueue", swipeQueue);
                }
                String nextUser = swipeQueue.pollUser(username, dbConnection);
                
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("UPDATE Swipe SET decisionA='" + request.getParameter("swipe") + "' WHERE userA='" + username + "' AND userB='" + nextUser + "'");
                boolean notEmpty = result.next();
                
               if(!notEmpty){
                   result = statement.executeQuery("UPDATE Swipe Set decisionB='" +  request.getParameter("swipe") + "' WHERE userB='" + username + "' AND userA='" + nextUser + "'");
               }
                
                nextUser = swipeQueue.peekUser(username, dbConnection);
                if(nextUser != null){
                    loadPictures(otherPictures, nextUser, dbConnection);
                    loadUser(otherUser, nextUser, dbConnection);
                }else{
                    otherUser.clear();
                }
                url = "match.jsp";
            }else if(action.equals("conversation")){
                Statement statement = dbConnection.createStatement();
                ResultSet resultA = statement.executeQuery("SELECT userB FROM Swipe WHERE userA='" + currentUser.getUsername() + "' AND decisionA='1' AND decisionB='1'");
                ResultSet resultB = statement.executeQuery("SELECT userA FROM Swipe WHERE userB='" + currentUser.getUsername() + "' AND decisionA='1' AND decisionB='1'");
                
                Conversations conversations = new Conversations();
                
                conversations.setCurrentUsername(currentUser.getUsername());
                while(resultA.next()) {
                    loadPictures(otherPictures,resultA.getString("userB"), dbConnection);
                    loadUser(otherUser,resultA.getString("userB"), dbConnection);
                    conversations.addUsers(otherUser);
                }
                while(resultB.next()) {
                    loadUser(otherUser,resultB.getString("userA"), dbConnection);
                    loadPictures(otherPictures,resultB.getString("userA"), dbConnection);
                    conversations.addUsers(otherUser);
                }
                //conversations.sort();
               
                session.setAttribute("conversations", conversations);
                
                url = "/conversations.jsp";
                statement.close();
                resultA.close();
                resultB.close();
            }else if(action.equals("messages")){
                String other = request.getParameter("target");
                loadPictures(otherPictures, other, dbConnection);
                loadUser(otherUser,other,dbConnection);
                
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM Message WHERE (sender='" + currentUser.getUsername()
                        + "' OR receiver='" + currentUser.getUsername() + "') AND (receiver='" + other + "' OR sender='" + other + "') ORDER BY time ASC");
                
                Messages messages = new Messages();
                
                messages.setTarget(otherUser);
                while(result.next()) {
                    messages.addSender(result.getString("sender"));
                    messages.addReceiver(result.getString("receiver"));
                    messages.addContent(result.getString("content"));
                }
                
                session.setAttribute("messages", messages);
                
                url = "/conversations.jsp";
                statement.close();
                result.close();
            }else if(action.equals("sendMessage")){
                String text = request.getParameter("text");
                if (!text.isEmpty()) {
                    String target = request.getParameter("target");
                
                    Statement statement = dbConnection.createStatement();

                    statement.executeQuery("INSERT INTO Message (receiver, sender, content) VALUES ('" + target + "', '" + currentUser.getUsername() + "', '" + text + "')");

                    statement.close();
                }
                url = "/conversations.jsp";
            /* code for when a user wants to manage their photos */
            }else if(action.equals("pictures")){
                String username = request.getParameter("username");
                loadPictures(currentPictures, username, dbConnection);
                url = "pictures.jsp";
            /* code for when a user wants to submit changes to their photos */
            }else if(action.equals("update_pictures")){
                /* get the updated info */
                String username = currentUser.getUsername();
                String url1 = request.getParameter("picture1");
                if(url1 == null || url1.equals("")) {
                    url1 = "test.jpg";
                }
                String url2 = request.getParameter("picture2");
                if(url2 == null || url2.equals("")) {
                    url2 = "test.jpg";
                }
                String url3 = request.getParameter("picture3");
                if(url3 == null || url3.equals("")) {
                    url3 = "test.jpg";
                }
                String url4 = request.getParameter("picture4");
                if(url4 == null || url4.equals("")) {
                    url4 = "test.jpg";
                }
                String url5 = request.getParameter("picture5");
                if(url5 == null || url5.equals("")) {
                    url5 = "test.jpg";
                }
                String url6 = request.getParameter("picture6");
                if(url6 == null || url6.equals("")) {
                    url6 = "test.jpg";
                }
                String url7 = request.getParameter("picture7");
                if(url7 == null || url7.equals("")) {
                    url7 = "test.jpg";
                }
                String url8 = request.getParameter("picture8");
                if(url8 == null || url8.equals("")) {
                    url8 = "test.jpg";
                }
                String url9 = request.getParameter("picture9");
                if(url9 == null || url9.equals("")) {
                    url9 = "test.jpg";
                }
                
                /* attempt to insert the new data into the database */
                for(int i = 1; i < 10; i++) {
                    PreparedStatement statement = dbConnection.prepareStatement("UPDATE Photo SET source=? WHERE Photo.username=? AND Photo.position=?");
                    switch(i) {
                        case 1:
                            statement.setString(1, url1);
                            break;
                        case 2:
                            statement.setString(1, url2);
                            break;
                        case 3:
                            statement.setString(1, url3);
                            break;
                        case 4:
                            statement.setString(1, url4);
                            break;
                        case 5:
                            statement.setString(1, url5);
                            break;
                        case 6:
                            statement.setString(1, url6);
                            break;
                        case 7:
                            statement.setString(1, url7);
                            break;
                        case 8:
                            statement.setString(1, url8);
                            break;
                        case 9:
                            statement.setString(1, url9);
                            break;
                        default:
                            statement.setString(1, url1);
                    }
                    statement.setString(2, username);
                    statement.setString(3, Integer.toString(i));
                    statement.executeUpdate();
                    statement.close();
                }
                
                loadUser(currentUser, username, dbConnection); //load the updated profile
                loadPictures(currentPictures, username, dbConnection);
                url = "profile.jsp";
            }
            dbConnection.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IcebreakrServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            response.setHeader("errMsg", ex.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
