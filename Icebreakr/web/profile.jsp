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
                        <div class="spacer">Name: <span id="name">${currentUser.name}</span></div>
                    </div>
                    <div class="col">
                        <div class="spacer">Location: <span id="location">${currentUser.location}</span></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="spacer">Age: <span id="age">${currentUser.age_string}</span></div>
                    </div>
                    <div class="col">
                        <div class="spacer">Gender: <span id="gender">${currentUser.gender_string}</span></div>
                    </div>
                </div>                
            </div>
        </div>
        <div>Pictures: <span id="picture_count">1</span></div>
        <a href="pictures.html" >Manage Pictures</a>
        <form action="IcebreakrServlet" method="post">
            <input type="hidden" name="action" value="update_profile">
            <div class="row">
                <div class="col">
                    <div class="row">
                        <div class="spacer">Height: <input id="height_feet" type="number" min="0" max ="9" step="1" size="1" value="${currentUser.heightFeet}"/> ft. <input id="height_inches" type="number" min="0" max ="11" step="1" size="1" value="${currentUser.heightInches}"/> in.</div>
                    </div>
                    <div class="row">
                        <div class="spacer">
                            Hair: <select id="hair_color">
                                <option value="${currentUser.hairColor}">Currently ${currentUser.hairColor_string}</option>
                                <option value="Brown">Brown</option>
                                <option value="Bald">Bald</option>
                                <option value="Blonde">Blonde</option>
                                <option value="Dirty Blonde">Dirty Blonde</option>
                                <option value="Black">Black</option>
                                <option value="Auburn">Auburn</option>
                                <option value="Ginger">Ginger</option>
                                <option value="Gray">Gray</option>
                                <option value="White">White</option>
                                <option value="Silver">Silver</option>
                                <option value="Red">Red</option>
                                <option value="Orange">Orange</option>
                                <option value="Yellow">Yellow</option>
                                <option value="Green">Green</option>
                                <option value="Blue">Blue</option>
                                <option value="Multi-Colored">Multi-Colored</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Zodiac Sign: <span id="zodiac">${currentUser.zodiac}</span></div>
                    </div>
                    <div class="row">
                        <div class="spacer">Children: <input id="num_children" type="number" min="0" max ="70" step="1" size="1" value="${currentUser.children}"/></div>
                    </div>
                    <div class="row">
                        <div class="spacer">Ethnicity: <input id="ethnicity" type="text" value="${currentUser.ethnicity}"/></div>
                    </div>
                    <div class="row">
                        <div class="spacer">Glasses: <select id="glasses">
                                    <option value="${currentUser.glasses}">Currently ${currentUser.glasses_string}</option>
                                    <option value="0">Yes</option>
                                    <option value="1">No</option>
                        </select></div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="spacer">Interested in<br>${currentUser.lookingfor_string}<br>Change selection below:<br>
                            <input type="checkbox" name="lookingfor" value="male">Male</input>
                            <input type="checkbox" name="lookingfor" value="female">Female</input>
                            <input type="checkbox" name="lookingfor" value="transgender">Transgender</input>
                            <input type="checkbox" name="lookingfor" value="transsexual">Transsexual</input>
                            <input type="checkbox" name="lookingfor" value="non_binary">Non-Binary</input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Hobbies<br>${currentUser.hobbies_string}<br>Change selection below:<br>
                            <input type="checkbox" name="hobbies" value="yoga">Yoga</input>
                            <input type="checkbox" name="hobbies" value="music">Music</input>
                            <input type="checkbox" name="hobbies" value="singing">Singing</input>
                            <input type="checkbox" name="hobbies" value="dancing">Dancing</input><br>
                            <input type="checkbox" name="hobbies" value="art">Art</input>
                            <input type="checkbox" name="hobbies" value="hiking">Hiking</input>
                            <input type="checkbox" name="hobbies" value="biking">Biking</input>
                            <input type="checkbox" name="hobbies" value="swimming">Swimming</input><br>
                            <input type="checkbox" name="hobbies" value="cooking">Cooking</input>
                            <input type="checkbox" name="hobbies" value="gardening">Gardening</input>
                            <input type="checkbox" name="hobbies" value="driving">Driving</input>
                            <input type="checkbox" name="hobbies" value="comedy">Comedy</input><br>
                            <input type="checkbox" name="hobbies" value="fighting">Fighting</input>
                            <input type="checkbox" name="hobbies" value="philosophy">Philosophy</input>
                            <input type="checkbox" name="hobbies" value="business">Business</input>
                            <input type="checkbox" name="hobbies" value="investing">Investing</input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Conversation Starters<br>
                        <textarea id="conversation_starters" class="form-control" rows="8">${currentUser.starters}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <input type="submit" value="update_profile">
            </div>
        </form>
    </div>
</body>
</html>