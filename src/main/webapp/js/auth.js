$(document).ready(function () {
    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyAZxIuB2ZS3PWSqlBcWDwB9xge_IzKdxFM",
        authDomain: "randomrecom.firebaseapp.com",
        databaseURL: "https://randomrecom.firebaseio.com",
        projectId: "randomrecom",
        storageBucket: "randomrecom.appspot.com",
        messagingSenderId: "683499265182"
    };
    firebase.initializeApp(config);
    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
            var gallery = "<li><a href='gallery.jsp'>Gallery</a></li>";
            var profile = "<li><a href='profile.jsp'>Profile</a></li>";
            var signOut = "<li onclick='signOut()'><a href='#'>Sign Out</a></li>";
            $("#navbar-items").html(gallery + profile + signOut);
            var file = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
            if(file === "register.html")
                window.location = "index.html"
            hideForm();
        } else {
            var register = "<li><a href='register.html'>Register</a></li>";
            var login = "<li onclick='showForm()'><a href='#'>Login</a></li>";
            $("#navbar-items").html(register + login);
        }
    });
});

function login() {
    var data = $('#login-form').serializeArray().reduce(function(obj, item) {
        obj[item.name] = item.value;
        return obj;
    }, {});
    firebase.auth().signInWithEmailAndPassword(data["email"], data["password"]).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // ...
    });
}

function signOut() {
    firebase.auth().signOut().then(function() {
        // Sign-out successful.
    }, function(error) {
        // An error happened.
    });
}

function register() {
    var data = $('#register-form').serializeArray().reduce(function(obj, item) {
        obj[item.name] = item.value;
        return obj;
    }, {});
    if(data["password"] !== "" && data["password"] !== data["passwordConfirm"]){
        $("#r-confirm").get(0).setCustomValidity("Passwords don't match");
    } else{
        firebase.auth().createUserWithEmailAndPassword(data["email"], data["password"]).catch(function(error) {
            var errorCode = error.code;
            var errorMessage = error.message;
            if(errorCode === "auth/weak-password")
                $("#r-password").get(0).setCustomValidity(errorMessage);
            else
                $("#r-email").get(0).setCustomValidity(errorMessage);
        });
    } return false;
}

function reLogin() {
    var user = firebase.auth().currentUser;
    var credential;
    user.reauthenticate(credential).then(function() {
        // User re-authenticated.
    }, function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // ...
    });
}

function showForm() {
    $("#dim").css("display", "flex");
    $("#auth").css("display", "flex");
}

function hideForm() {
    $("#dim").hide();
    $("#auth").hide();
}

function getUserId() {
    var user = firebase.auth().currentUser;
    if (user !== null) {
        return user.uid;
    }
}

function changePassword(password){
    var user = firebase.auth().currentUser;
    user.updatePassword(password).then(function() {
        // Update successful.
    }, function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // ...
    });
}

function changeEmail(email) {
    var user = firebase.auth().currentUser;
    user.updateEmail(email).then(function() {
        // Update successful.
    }, function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // ...
    });
}