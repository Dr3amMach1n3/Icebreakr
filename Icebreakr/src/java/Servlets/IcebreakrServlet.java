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
            int lookingfor = results.getInt(6);
            ArrayList<Genders> l_lookingfor = new ArrayList<>();
            for(int i = 0; i < 5; i++) {
                int bit = lookingfor & 1;
                if(bit > 0) {
                    l_lookingfor.add(Genders.values()[i]);
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
            user.setChildren(results.getInt(13));
            user.setWeight(results.getInt(14));
            user.setEthnicity(results.getNString(16));
            user.setGlasses(results.getBoolean(17));
        } catch (SQLException ex) {
            Logger.getLogger(IcebreakrServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            User otherUser = (User) session.getAttribute("otherUser");
            if(otherUser == null){
                otherUser = new User();
                session.setAttribute("otherUser", otherUser);
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
            /* code for when a user updates their profile */
            }else if(action.equals("update_profile")){
                /* get the updated info */
                String username = request.getParameter("username");
                String gender = request.getParameter("gender");
                String lookingfor = getBitString(request, numGenders, genderPrefix);
                String location = request.getParameter("location");
                String hobbies = getBitString(request, numHobbies, hobbyPrefix);
                String starters = request.getParameter("starters");
                String height = Integer.toString((12 * Integer.parseInt(request.getParameter("height_feet"))) + Integer.parseInt(request.getParameter("height_inches")));
                String haircolor = request.getParameter("hair_color");
                String children = request.getParameter("children");
                String weight = request.getParameter("weight");
                String ethnicity = request.getParameter("ethnicity");
                String glasses = request.getParameter("glasses");
                
                /* attempt to insert the new data into the database */
                try (PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO User (gender, lookingfor, location, hobbies, starters, height, haircolor, children, weight, ethnicity, glasses) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)")) {
                    statement.setString(1, gender);
                    statement.setString(2, lookingfor);
                    statement.setString(3, location);
                    statement.setString(4, hobbies);
                    statement.setString(5, starters);
                    statement.setString(6, height);
                    statement.setString(7, haircolor);
                    statement.setString(8, children);
                    statement.setString(9, weight);
                    statement.setString(10, ethnicity);
                    statement.setString(11, glasses);
                    
                    statement.executeUpdate();
                    
                    loadUser(currentUser, username, dbConnection); //load the updated profile
                    url = "profile.jsp";
                    
                    statement.close();
                } catch (Exception e) {
                    response.setHeader("ERROR: ", e.toString());
                    url = "index.jsp"; //return to login page
                }
            }else if(action.equals("logout")){
                session.setAttribute("credErr", null);
                session.setAttribute("currentUser", null);
                session.setAttribute("otherUser", null);
                session.setAttribute("swipeQueue", null);
                url = "index.jsp";
            }else if(action.equals("preview_user")){
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("SELECT COUNT (username) FROM User");
                int numUsers = result.getInt("total");
                Random rand =  new Random();
                result = statement.executeQuery("SELECT username FROM User ORDER BY username LIMIT 1 OFFSET " + rand.nextInt(numUsers));
                loadUser(otherUser, result.getString("username"), dbConnection);
                url = "other_profile";
                result.close();
                statement.close();
            }else if(action.equals("match")){
                String username = currentUser.getUsername();

                SwipeQueue swipeQueue = (SwipeQueue) session.getAttribute("swipeQueue");
                if(swipeQueue == null){
                    swipeQueue = new SwipeQueue();
                    session.setAttribute("swipeQueue", swipeQueue);
                }
                String nextUser = swipeQueue.peekUser(username, dbConnection);
                if(nextUser != null){
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
                
                while(resultA.next()) {
                    conversations.addUsers(resultA.getString("userB"));
                }
                while(resultB.next()) {
                    conversations.addUsers(resultB.getString("userB"));
                }
                conversations.addUsers((ArrayList<String>) resultA);
                conversations.addUsers((ArrayList<String>) resultB);
                conversations.sort();
               
                session.setAttribute("conversations", conversations);
                
                url = "/conversations.jsp";
                statement.close();
                resultA.close();
                resultB.close();
            }else if(action.equals("messages")){
                String other = request.getParameter("target");
                
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM Message WHERE (sender='" + currentUser.getUsername() + "' AND receiver='" + other
                        + "') OR (receiver='" + currentUser.getUsername()
                        + "' AND sender='" + other + "') ORDER BY time DESC");
                
                Messages messages = new Messages();
                
                messages.addTarget(other);
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
                String target = request.getParameter("target");
                
                Statement statement = dbConnection.createStatement();
                
                statement.executeQuery("INSERT INTO Message (receiver, sender, content) VALUES (" + target + ", " + currentUser.getUsername() + ", " + text + ")");
                
                url = "/conversations.jsp";
                statement.close();
            }else if(action.equals("pictures")){
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM Photo WHERE username='" + currentUser.getUsername() + "' ORDER BY position ASC");
                
                Pictures pictures = new Pictures();
                pictures.pad();
                
                while(result.next()) {
                    String source = result.getString("source");
                    int pos = Integer.parseInt(request.getParameter("position"));
                    pictures.addPicture(pos,source);
                }
                try {
                    String new_url = request.getParameter("url");
                    int pos = Integer.parseInt(request.getParameter("pos"));
                    pictures.addPicture(pos,new_url);
                    statement.executeQuery("DELETE FROM Picture WHERE username='" + currentUser.getUsername() + "' AND position='" + String.valueOf(pos) + "'");
                    statement.executeQuery("INSERT INTO Picture (username, source, position) VALUES (" + currentUser.getUsername() + ", " + new_url + ", " + String.valueOf(pos));
                } catch(Exception e) { }
                
                session.setAttribute("pictures", pictures);
                url = "pictures.jsp";
                statement.close();
                result.close();
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
