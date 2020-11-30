<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style type="text/css">body {background: #A2C4C9 !important;}</style>
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto">Icebreakr</h5>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="container">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 main px-3 mb-5 pt-3 pb-4">
                <form action="IcebreakrServlet" method="post">
                    <div class="row pt-2">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-8">
                            <div class="spacer align-items-center pb-1">Username:
                                    <br>
                                    <input id="usr" type="text"/>
                            </div>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>
                    <div class="row pb-2">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-8">
                            <div class="spacer align-items-center pb-1">Password:
                                <br>
                                <input id="usr" type="text"/>
                            </div>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>
                </form>
                <div class="row">
                	<div class="col text-center">
                		<a class="btn btn-light mt-2" href="profile.html">Login</a>
                	</div>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
    <div class="container">
    	<div class="row">
    		<div class="col text-center">
    			New user? <a href="signup.html"><u>Register new account!</u></a>
    		</div>
    	</div>
    </div>
    <br>
    <br>
    <div class="container">
    	<div class="row">
    		<div class="col-sm-4"></div>
    		<div class="col-sm-4 text-center">
    			<a class="btn container secondary pt-1 pb-1" href="other_profile.html">
    				Newest Member!
    			</a>
    		</div>
    		<div class="col-sm-4"></div>
    	</div>
    </div>
</body>
</html>