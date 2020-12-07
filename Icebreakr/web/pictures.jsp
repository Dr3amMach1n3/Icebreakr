<!DOCTYPE html>
<html>
<head>
    <title>homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style type="text/css">body {background: #A2C4C9 !important;}</style>
    <link rel="stylesheet" href="pictures.css">
</head>
<body>
    <div class="container spacer_large">
        <form action="IcebreakrServlet" method="post">
            <input type="hidden" name="action" value="update_pictures">
            <div class="row">
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture1}" class="img-fluid">
                        <input id="picture1" name="picture1" type="text"/>
                    </div>
                </div>
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture2}" class="img-fluid">
                        <input id="picture2" name="picture2" type="text"/>
                    </div>
                </div>
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture3}" class="img-fluid">
                        <input id="picture3" name="picture3" type="text"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture4}" class="img-fluid">
                        <input id="picture4" name="picture4" type="text"/>
                    </div>
                </div>
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture5}" class="img-fluid">
                        <input id="picture5" name="picture5" type="text"/>
                    </div>
                </div>
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture6}" class="img-fluid">
                        <input id="picture6" name="picture6" type="text"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture7}" class="img-fluid">
                        <input id="picture7" name="picture7" type="text"/>
                    </div>
                </div>
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture8}" class="img-fluid">
                        <input id="picture8" name="picture8" type="text"/>
                    </div>
                </div>
                <div class="col">
                    <div class="spacer_pict text-center">
                        <img src="${currentPictures.picture9}" class="img-fluid">
                        <input id="picture9" name="picture9" type="text"/>
                    </div>
                </div>
            </div>
            <div class="pict_inst">
                Enter a photo url below the photo you'd like to add. The photos are displayed from top-left to bottom-right.
            </div>
            <div class="row">
                <div class="spacer text-center">
                    <button class="btn-default" type="submit">Finish Changes</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>