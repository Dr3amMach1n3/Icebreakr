<!DOCTYPE html>
<html>
<head>
    <title>homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style type="text/css">body {background: #A2C4C9 !important;}</style>
    <link rel="stylesheet" href="match.css">
</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto">Icebreakr</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <div class="btn-group">
                <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                    <input type="hidden" name="action" value="match">
                    <button class="btn btn-info my-2 my-sm-0" type="submit">Start Matching!</button>
                </form>
                <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                    <input type="hidden" name="action" value="conversation">
                    <button class="btn btn-warning my-2 my-sm-0" type="submit">Conversations</button
                </form>
                <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                    <input type="hidden" name="action" value="profile">
                    <button class="btn btn-success my-2 my-sm-0" type="submit" >My Profile</button>
                </form>
                <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                    <input type="hidden" name="action" value="logout">
                    <button class="btn btn-danger my-2 my-sm-0" type="submit" >Logout</button>
                </form>
            </div>
        </nav> 
    </div>
    <c:if test=${not empty otherUser.name}>
        <div class="container spacer_large">
            <div class="row">
                <div class="col">
                    <div class="spacer text-center">${otherUser.name}</div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <%@ page import="beans.User" %>
                    <%
                        User otherUser = (User) session.getAttribute("otherUser");
                        int age = 0;
                        if(otherUser != null){
                            age = otherUser.generateAge();
                        }
                    %>
                    <div class="spacer text-center">Age: <%=age%></div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="spacer text-center">Gender: ${otherUser.gender}</div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="spacer text-center">Location: ${otherUser.location}</div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="spacer text-center">Hobbies/Interests:<br>
                        <%boolean first=true;
                        String commaSpace = "";%>
                        <c:forEach var="hobby" items="${otherUser.hobbies}">
                            <span><%=commaSpace%>${hobby}</span>
                            <%if(first==true){
                                first = false;
                                commaSpace = ", ";}%>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="spacer text-center">${otherUser.starters }</div>
                </div>
            </div>
            <div class="row">
                <div class="col spacer_large text-center">
                    <form id="form1" action="IcebreakrServlet" method="post">
                        <input type="hidden" name="action" value="swipe">
                        <input type="hidden" name="swipe" value="1">
                        <input type="submit" value="Yay!" class="btn btn-success">
                    </form>
                </div>
                <div class="col spacer_large text-center">
                    <form id="form2" action="IcebreakrServlet" method="post">
                        <input type="hidden" name="action" value="swipe">
                        <input type="hidden" name="swipe" value="0"> 
                        <input type="submit" value="Nay!" class="btn btn-danger">
                    </form>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test=${empty otherUser.name}>
        <div>Sorry, there are no more compatible users at this time, please check back later</div>
    </c:if>
</body>
</html>