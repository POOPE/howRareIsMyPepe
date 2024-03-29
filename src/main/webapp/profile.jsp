<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://www.gstatic.com/firebasejs/3.9.0/firebase.js"></script>
    <script src="js/auth.js"></script>
    <title>Profile</title>
</head>
<body>

<!------------------------------------------------- Navbar ------------------------------------------------>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">How Rare Is My Pepe</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul id="navbar-items" class="nav navbar-nav navbar-right">

            </ul>
        </div>
    </div>
</nav>

<div id="dim" class="overlay flexbox-row" onclick="hideForm()"></div>
<div id="auth" class="overlay flexbox-row">
    <div class="row auth-box">
        <div class="col-md-12">
            <form id="login-form" class="form" role="form" method="post" action="javascript:void(0);" onsubmit="return login()" accept-charset="UTF-8">
                <div class="form-group">
                    <label class="sr-only" for="email">Email address</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email address" required>
                </div>
                <div class="form-group">
                    <label class="sr-only" for="password">Password</label>
                    <input type="password" class="form-control" id="password"name="password" placeholder="Password" required>
                    <div class="help-block text-center"><a href="#">Forgot your password ?</a></div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                </div>
            </form>
        </div>
        <div class="bottom text-center">
            New here ? <a href="register.html"><b>Join Us</b></a>
        </div>
    </div>
</div>

<!--------------------------------------------------------------------------------------------------------->

</body>
</html>
