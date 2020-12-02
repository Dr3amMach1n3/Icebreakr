/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import beans.*;
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
 * @author Pierce
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
    
    private void loadUser(User user, String username, Connection connection){
        try{
            Statement statement = connection.createStatement();
            ResultSet userInfo = statement.executeQuery("SELECT * FROM User WHERE username='" + username +"'");
            //set all parameters in user to values in database
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
        
            String dbURL = "jdbc:mariadb://localhost:3306/apollo9_Icebreakr";
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

                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("SELECT password FROM User WHERE username='" + username + "'");
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
                
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("SELECT username FROM User WHERE username='" + username + "'");
                boolean notEmpty = result.next();
                
                if(!notEmpty && password.equals(repassword)){
                    String name = request.getParameter("name");
                    String birthday = request.getParameter("year") + "-" + request.getParameter("day") + "-" + request.getParameter("month");
                    String gender = request.getParameter("gender");
                    String lookingfor = getBitString(request, numGenders, genderPrefix);
                    String location = request.getParameter("location");
                    String hobbies = getBitString(request, numHobbies, hobbyPrefix);
                    String starters = request.getParameter("starters");
                    result = statement.executeQuery("INSERT INTO User (username, password, name, birthday, gender, lookingfor, location, hobbies, starters) "
                            + "VALUES (" + username + ", " + password + "," + name + "," + birthday + "," + gender + "," + lookingfor + "," + location + "," + hobbies + "," + starters);
                    loadUser(currentUser, username, dbConnection);
                    url = "profile.jsp";
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
            }else if(action.equals("logout")){
                credErr.setNameErr(false);
                credErr.setPassErr(false);
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
                String username = swipeQueue.first();
                loadUser(otherUser, username, dbConnection);
                url = "match.jsp";
            }else if(action.equals("swipe")){
                String username = swipeQueue.first();
                swipeQueue.remove(username);
                
                Statement statement = dbConnection.createStatement();
                ResultSet result = statement.executeQuery("UPDATE decisionA FROM Swipe WHERE userA='" + currentUser.getUsername() + "' AND userB='" + username + "'");
                boolean notEmpty = result.next();
                
               if(!notEmpty){
                   result = statement.executeQuery("UPDATE decisionB FROM Swipe WHERE userB='" + currentUser.getUsername() + "' AND userA='" + username + "'");
               }
                
                username = swipeQueue.first();
                loadUser(otherUser, username, dbConnection);
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
            }
            dbConnection.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IcebreakrServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IcebreakrServlet.class.getName()).log(Level.SEVERE, null, ex);
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
