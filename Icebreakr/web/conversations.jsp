<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style type="text/css">body {background: #A2C4C9 !important;}</style>
    <link rel="stylesheet" href="conversations.css">
</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto">Icebreakr</h5>
        <nav class="my-2 my-md-0 mr-md-3">
             <div class="btn-group">
                 <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                     <input type="hidden" name="action" value="match">
                     <button class="btn text-dark font-weight-bold my-2 my-sm-0" type="submit">Start Matching!</button>
                 </form>
                 <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                     <input type="hidden" name="action" value="conversation">
                     <button class="btn my-2 my-sm-0" type="submit">Conversations</button>
                 </form>
                 <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                     <input type="hidden" name="action" value="profile">
                     <button class="btn my-2 my-sm-0" type="submit" >My Profile</button>
                 </form>
                 <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                     <input type="hidden" name="action" value="logout">
                     <button class="btn btn-danger my-2 my-sm-0" type="submit" >Logout</button>
                 </form>
             </div>
         </nav> 
    </div>

    <div class="container">
    	<div class="row">
        <div class="col-sm-3 border secondary text-center pl-0 pr-0">
            <c:forEach var="user" items="${conversations.users}">
            <a class="msgr btn btn-light text-center">
                <form "msgr btn btn-light text-center" action="IcebreakrServlet" method="post">
                    <input type="hidden" name="action" value="messages">
                    <input type="hidden" name="target" value="${user.getUsername()}">
                    <input type="submit" value="${user.getName()}">
                </form>
            </a>
            </c:forEach>
        </div>
        <div class="col-sm-9">
        	<a class="btn container secondary pt-1 pb-1 text-center mt-3 mb-3">
                    <form action="IcebreakrServlet" method="post">
                        <input type="hidden" name="action" value="other_user">
                        <input type="hidden" name="target" value="${messages.target.getName()}">
                        <input type="submit" value="${messages.target.getName()}">
                    </form>
    		</a>
    		<div class="container secondary pt-3 pb-3 mb-4">
                    <c:if test = "${messages.getLength() > 0}">
                    <c:forEach var="message" begin = "0" end = "${messages.getLength()-1}">
                        <c:choose>
                            <c:when test = "${messages.getSender(message).equals(conversations.getCurrentUsername())}">
                                <div class="row">
                                    <div class="col-sm-6"></div>
                                    <div class="col-sm-6">
                                        <div class="container primary pt-1 pb-1 mt-1 mb-1 border border-dark">
                                            ${messages.getContent(message)}
                                        </div>
                                    </div>
                                </div>
                                </c:when>
                                <c:otherwise>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="container secondary pt-1 pb-1 mt-1 mb-1 border border-dark">
                                            ${messages.getContent(message)}
                                        </div>
                                    </div>
                                    <div class="col-sm-6>"></div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </c:if>
    		</div>
    		<div class="container">
    			<div class="row align-items-end">
    				<form action="IcebreakrServlet" method="post">
                                    <input type=textarea name="text">
                                    <input type="hidden" name="action" value="sendMessage">
                                    <input type="hidden" name="target" value="${messages.target}">
                                    <input type="submit" value="Update">
                                </form>
    			</div>
    		</div>
        </div>
    </div>
</body>
</html>