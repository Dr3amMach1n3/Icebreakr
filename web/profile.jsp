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
                        <div class="spacer">
                            Name: <span>
                                <form action="IcebreakrServlet" method="post">
                                    <input type="hidden" name="action" value="profile">
                                    <input type=text name="name" id="name" value="${profile.user.name}">
                                </form>  
                            </span>
                        </div>
                    </div>
                    <div class="col">
                        <div class="spacer">
                            Location: <form action="" method="post">
                                <input type=text id="location" value="${user.location}">
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="spacer">
                            Age: <form action="" method="post">
                                <input type=text id="age" value="${user.generateAge()}">
                            </form>
                        </div>
                    </div>
                    <div class="col">
                        <div class="spacer">
                            Gender: <form action="" method="post">
                                <input type=text id="gender" value="${user.gender}">
                        </div>
                    </div>
                </div>                
            </div>
        </div>
        <div>Pictures: <span id="picture_count">1</span></div>
        <a href="pictures.html" >Manage Pictures</a>
        <div class="row">
            <div class="col">
                <div class="row">
                    <div class="spacer">Height: <input id="height_feet" type="number" min="2" max ="9" step="1" size="1" value="6"/>ft. <input id="height_inches" type="number" min="0" max ="11" step="1" size="1" value="2"/>in.</div>
                </div>
                <div class="row">
                    <div class="spacer">Hair: <select id="hair">
		                <option value="Brown">Brown</option>
		                <option value="Bald">Bald</option>
		                <option value="Blonde">Blonde</option>
		                <option value="Dirty Blonde">Dirty Blonde</option>
		                <option value="Black">Black</option>
		                <option value="Auburn">Aubrun</option>
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
                    </select></div>
                </div>
                <div class="row">
                    <div class="spacer">Zodiac Sign: <select id="zodiac">
		                <option value = "Aries">Aries</option>
		                <option value = "Leo">Leo</option>
		                <option value = "Cancer">Cancer</option>
		                <option value = "Pisces">Pisces</option>
		                <option value = "Scorpio">Scorpio</option>
		                <option value = "Taurus">Taurus</option>
		                <option value = "Sagittarius">Sagittarius</option>
		                <option value = "Gemini">Gemini</option>
		                <option value = "Virgo">Virgo</option>
		                <option value = "Libra">Libra</option>
		                <option value = "Libra">Libra</option>
		                <option value = "Capricorn">Capricorn</option>
		                <option value = "Aquarius">Aquarius</option>
                    </select></div>
                </div>
                <div class="row">
                    <div class="spacer">Children: <input id="num_children" type="number" min="0" max ="70" step="1" size="1" value="0"/></div>
                </div>
                <div class="row">
                    <div class="spacer">Ethnicity: <input id="ethnicity" type="text" value="White and Nerdy"/></div>
                </div>
                <div class="row">
                    <div class="spacer">Glasses: <select id="glasses">
		                <option value="Sometimes">Sometimes</option>
		                <option value="No">Yes</option>
		                <option value="No">No</option>
                    </select></div>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="spacer">Interests/Hobbies<br>
                    <select id="interests_hobbies">
		                <option value="Fitness">Fitness</option>
		                <option value="Music">Music</option>
		                <option value="Art">Art</option>
		                <option value="Musician">Musician</option>
		                <option value="Hiking">Hiking</option>
		                <option value="Biking">Biking</option>
		                <option value="Cooking">Cooking</option>
                    </select></div>
                </div>
                <div class="row">
                    <div class="spacer">Conversation Starters<br>
                    <textarea id="conversation_starters" class="form-control" rows="8">Is there funk after death?</textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>