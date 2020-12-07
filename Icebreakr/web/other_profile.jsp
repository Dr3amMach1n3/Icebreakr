<!DOCTYPE html>
<html>
<head>
    <title>homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style type="text/css">body {background: #A2C4C9 !important;}</style>
    <link rel="stylesheet" href="profile.css">
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
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="spacer_picture"><img src="El_Guapo.jpg"></div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col">
                        <div class="spacer">Name: <span id="name" value="${profile.otherUser.name}"></span></div>
                    </div>
                    <div class="col">
                        <div class="spacer">Location: <span id="location" value="${profile.otherUser.location}"></span></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="spacer">Age: <span id="age" value="${profile.otherUser.generateAge()}"></span></div>
                    </div>
                    <div class="col">
                        <div class="spacer">Gender: <span id="gender" value="${profile.otherUser.gender.toString()}"></span></div>
                    </div>
                </div>                
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="row">
                    <div class="spacer">Height: <span id="height_feet" value="${profile.otherUser.heightFeet}"></span>ft. <span id="height_inches" value="${profile.otherUser.heightInches}"></span>in.</div>
                </div>
                <div class="row">
                    <div class="spacer">
                        Hair: <span id="hair_color" value="${profile.otherUser.hairColor.toString()}"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="spacer">Zodiac Sign: <span id="zodiac" value="${profile.otherUser.generateZodiac()}"></span></div>
                </div>
                <div class="row">
                    <div class="spacer">Children: <span id="num_children" value="${profile.otherUser.children}"></span></div>
                </div>
                <div class="row">
                    <div class="spacer">Ethnicity: <span id="ethnicity" value="${profile.otherUser.ethnicity}"></span></div>
                </div>
                <div class="row">
                    <div class="spacer">Glasses: <span id="glasses" value="${profile.otherUser.wearsGlasses()}"></span></div>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="spacer">Interested in<br>
                        <span id="looking_for" value="${profile.otherUser.generateLookingFor()}"></span></div>
                </div>
                <div class="row">
                    <div class="spacer">Hobbies<br>
                        <span id="hobbies" value="${profile.otherUser.generateHobbies()}"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="spacer">Conversation Starters<br>
                    <span id="conversation_starters" value="${profile.otherUser.starters}"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>