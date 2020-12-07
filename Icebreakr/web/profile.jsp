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
                     <button class="btn text-dark font-weight-bold my-2 my-sm-0" type="submit">Start Matching!</button>
                 </form>
                 <form class="form-inline my-2 my-lg-0" action="IcebreakrServlet" method="post">
                     <input type="hidden" name="action" value="conversation">
                     <button class="btn my-2 my-sm-0" type="submit">Conversations</button
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
                        <div class="spacer">
                            Height: ${currentUser.heightFeet} ft. ${currentUser.heightInches} in.<br>Change selection below:<br>
                            <input id="height_feet" type="number" min="0" max ="9" step="1" size="1"/> ft. <input id="height_inches" type="number" min="0" max ="11" step="1" size="1"/> in.
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">
                            Hair: ${currentUser.hairColor_string}<br>Change selection below:<br>
                            <select id="hair_color">
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
                        <div class="spacer">Children: ${currentUser.children}<br>Change selection below:<br>
                            <input id="num_children" type="number" min="0" max ="70" step="1" size="1"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Ethnicity: ${currentUser.ethnicity}<br>Change selection below:<br>
                            <input id="ethnicity" type="text" value="${currentUser.ethnicity}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Glasses: ${currentUser.glasses_string}<br>Change selection below:<br>
                            <select id="glasses">
                                    <option name="glasses" value="yes">Yes</option>
                                    <option name="glasses" value="no">No</option>
                        </select></div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="spacer">Interested in<br>${currentUser.lookingfor_string}<br>Change selection below:<br>
                            <input type="checkbox" name="g1" value="1">Male</input>
                            <input type="checkbox" name="g2" value="1">Female</input>
                            <input type="checkbox" name="g3" value="1">Transgender</input>
                            <input type="checkbox" name="g4" value="1">Transsexual</input>
                            <input type="checkbox" name="g5" value="1">Non-Binary</input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="spacer">Hobbies<br>${currentUser.hobbies_string}<br>Change selection below:<br>
                            <input type="checkbox" name="h1" value="1">Yoga</input>
                            <input type="checkbox" name="h2" value="1">Music</input>
                            <input type="checkbox" name="h3" value="1">Singing</input>
                            <input type="checkbox" name="h4" value="1">Dancing</input><br>
                            <input type="checkbox" name="h5" value="1">Art</input>
                            <input type="checkbox" name="h6" value="1">Hiking</input>
                            <input type="checkbox" name="h7" value="1">Biking</input>
                            <input type="checkbox" name="h8" value="1">Swimming</input><br>
                            <input type="checkbox" name="h9" value="1">Cooking</input>
                            <input type="checkbox" name="h10" value="1">Gardening</input>
                            <input type="checkbox" name="h11" value="1">Driving</input>
                            <input type="checkbox" name="h12" value="1">Comedy</input><br>
                            <input type="checkbox" name="h13" value="1">Fighting</input>
                            <input type="checkbox" name="h14" value="1">Philosophy</input>
                            <input type="checkbox" name="h15" value="1">Business</input>
                            <input type="checkbox" name="h16" value="1">Investing</input>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="spacer">Conversation Starters<br>
                <textarea id="starters" class="form-control" rows="8" value="starters">${currentUser.starters}</textarea>
                </div>
            </div>
            <div class="row">
                <button class="btn-default" type="submit">Update Profile</button>
            </div>
        </form>
    </div>
</body>
</html>