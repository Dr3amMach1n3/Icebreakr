<!DOCTYPE html>
<html>
<head>
    <title>homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style type="text/css">body {background: #A2C4C9 !important;}</style>
    <link rel="stylesheet" href="signup.css">
</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto">Icebreakr</h5>
    </div>
    <div class="col text-center">
        <%@ page import="beans.CredentialError" %>
        <%
            CredentialError credErr = (CredentialError) session.getAttribute("credErr");
            String hidden = "hidden";
            String errMsg = response.getHeader("errMsg");
            if(errMsg != null){
                hidden = "";
            }
            
            if(credErr != null){
                if(credErr.isNameErr()){
                    errMsg = "Username already taken";
                    hidden = "";
                }else if(credErr.isPassErr()){
                    errMsg = "Password does not match re-entered password";
                    hidden = "";
                }else if(credErr.isEmptyErr()){
                    errMsg = "Please fill out ALL fields";
                    hidden = "";
                }
            }
        %>
        <span <%=hidden%> class="alert alert-danger"><%=errMsg%></span> 
    </div>
    <br <%=hidden%>>
    <div class="container text-center mb-4">
        <div class="row">
            <div class="col">
                <form action="IcebreakrServlet" method="post">
                    <input type="hidden" name="action" value="register">
                    <div class="row">
                        <div class="spacer align-items-center pb-1">Username: <input name="username" type="text"></div>
                    </div>
                    <div class="row">
                        <div class="spacer align-items-center pb-1">Password: <input name="password" type="text"/></div>
                    </div>
                    <div class="row">
                        <div class="spacer align-items-center pb-1">Re-Enter Password: <input name="repassword" type="text"/></div>
                    </div>
                    <div class="row">
                        <div class="spacer align-items-center pb-1">Name: <input input name="name" type="text"/></div>
                    </div>
                    <div class="row spacer">
                        <div class="col">
                            Birthday: 
                        </div>
                        <div class="col">
                            Year: <input name="year" type="number" min="1900" max ="2020" step="1" size="5" value="2020" onkeydown="return false"/>
                        </div>
                        <div class="col">
                            Month: <input name="month" type="number" min="1" max ="12" step="1" size="3" value="1" onkeydown="return false"/>
                        </div>
                        <div class="col">
                            Day <input name="day" type="number" min="1" max ="31" step="1" size="3" value="1" onkeydown="return false"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="spacer">Gender: 
                                <select name="gender">
                                            <option name="gender" value="0">Male</option>
                                            <option name="gender" value="1">Female</option>
                                            <option name="gender" value="2">Transgender</option>
                                            <option name="gender" value="3">Transsexual</option>
                                            <option name="gender" value="4">Non-Binary</option>
                                </select>
                            </div>
                        </div>
                        <div class="col">
                            <div class="spacer">Location: <input name="location" type="text" value=""/></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Who are you interested in?<br>
                            <input type="checkbox" name="g0" value="1">Male </input>
                            <input type="checkbox" name="g1" value="1">Female </input>
                            <input type="checkbox" name="g2" value="1">Transgender </input>
                            <input type="checkbox" name="g3" value="1">Transsexual </input>
                            <input type="checkbox" name="g4" value="1">Non-Binary </input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Interests/Hobbies<br>
                            <input type="checkbox" name="h0" value="1">Yoga </input>
                            <input type="checkbox" name="h1" value="1">Musician </input>
                            <input type="checkbox" name="h2" value="1">Singing </input>
                            <input type="checkbox" name="h3" value="1">Dancing </input>
                            <input type="checkbox" name="h4" value="1">Art </input>
                            <input type="checkbox" name="h5" value="1">Hiking </input>
                            <input type="checkbox" name="h6" value="1">Biking </input>
                            <input type="checkbox" name="h7" value="1">Swimming </input>
                            <input type="checkbox" name="h8" value="1">Cooking </input>
                            <input type="checkbox" name="h9" value="1">Gardening </input>
                            <input type="checkbox" name="h10" value="1">Driving </input>
                            <input type="checkbox" name="h11" value="1">Comedy </input>
                            <input type="checkbox" name="h12" value="1">Fighting </input>
                            <input type="checkbox" name="h13" value="1">Philosophy </input>
                            <input type="checkbox" name="h14" value="1">Business </input>
                            <input type="checkbox" name="h15" value="1">Investing </input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Conversation Starters<br>
                        <textarea name="starters" class="form-control" rows="8"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <input type="submit" value="Register">
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
