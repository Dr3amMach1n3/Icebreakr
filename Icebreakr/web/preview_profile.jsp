<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
         </nav> 
    </div>
    <div class="container">
        <div class="row">
            <div class="col">
                <c:if test="${currentUser.score gt 25}">
                    <div class="spacer_picture"><img src="${otherPictures.picture1}"></div>
                </c:if>
                <c:if test="${currentUser.score le 25}">
                    <div class="spacer_picture"><img src="test.jpg"></div>
                </c:if>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col">
                        <div class="spacer">Name: ${otherUser.name}</div>
                    </div>
                    <div class="col">
                        <div class="spacer">Location: ${otherUser.location}</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="spacer">Age: ${otherUser.age_string}</div>
                    </div>
                    <div class="col">
                        <div class="spacer">Gender: ${otherUser.gender_string}</div>
                    </div>
                </div>                
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="row">
                    <c:if test="${currentUser.score gt 25}">
                        <div class="spacer">Height: ${otherUser.heightFeet} ft. ${otherUser.heightInches} in.</div>
                    </c:if>
                    <c:if test="${currentUser.score le 25}">
                        <div class="spacer">Height: ? ft. ? in.</div>
                    </c:if>
                </div>
                <div class="row">
                    <c:if test="${currentUser.score gt 25}">
                        <div class="spacer">Hair: ${otherUser.hairColor_string}"</div>
                    </c:if>
                    <c:if test="${currentUser.score le 25}">
                        <div class="spacer">Hair: ?"</div>
                    </c:if>
                </div>
                <div class="row">
                    <c:if test="${currentUser.score gt 25}">
                        <div class="spacer">Zodiac Sign: ${otherUser.zodiac}</div>
                    </c:if>
                    <c:if test="${currentUser.score le 25}">
                        <div class="spacer">Zodiac Sign: ?</div>
                    </c:if>
                </div>
                <div class="row">
                    <c:if test="${currentUser.score gt 25}">
                        <div class="spacer">Children: ${otherUser.children}</div>
                    </c:if>
                    <c:if test="${currentUser.score le 25}">
                        <div class="spacer">Children: ?</div>
                    </c:if>
                </div>
                <div class="row">
                    <c:if test="${currentUser.score gt 25}">
                        <div class="spacer">Ethnicity: ${otherUser.ethnicity}</div>
                    </c:if>
                    <c:if test="${currentUser.score le 25}">
                        <div class="spacer">Ethnicity: ?</div>
                    </c:if>
                </div>
                <div class="row">
                    <c:if test="${currentUser.score gt 25}">
                        <div class="spacer">Glasses: ${otherUser.glasses_string}</div>
                    </c:if>
                    <c:if test="${currentUser.score le 25}">
                        <div class="spacer">Glasses: ?</div>
                    </c:if>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="spacer">Interested in<br>${otherUser.lookingfor_string}"</div>
                </div>
                <div class="row">
                    <div class="spacer">Hobbies<br>${otherUser.hobbies_string}</div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="spacer">Conversation Starters<br>${otherUser.starters}</div>
        </div>
    </div>
</body>
</html>