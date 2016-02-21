/**
 * Created by jason on 12/13/15.
 */
var loginUrl = "http://localhost:8080/login";
var dashBoardUrl = "/";
var registerUrl = "http://localhost:8080/register";
var showRegister = false;
if (showRegister == false) {
    $(".row.centered-form").hide();
}

$("#show-register").bind("click", function (event) {
    event.preventDefault();
    if (showRegister == false) {
        $(".row.centered-form").show();
        showRegister = true;
        $("#show-register").find("span").attr("class", "glyphicon glyphicon-menu-up");
    } else {
        $(".row.centered-form").hide();
        showRegister = false;
        $("#show-register").find("span").attr("class", "glyphicon glyphicon-menu-down");
    }
});

//$("button[name='sigin-in']").bind("click",function(event){
$("#form-login").submit(function (event) {
    event.preventDefault();
    $("#login-btn").text("Connecting...");
    $.ajax({
        type: "POST",
        url: loginUrl,
        data: $("#form-login").serialize(),
        success: function (data) {
            window.location.href = "http://localhost:8080/jsp/dashboard/dashboard.html";
            $("#login-btn").text("Sign in");
            if (data.status == "200" || data.status == "307") {
                console.log("ok!");
                localStorage.setItem("session", "new");
                window.location.href = "http://localhost:8080/jsp/dashboard/dashboard.html";
            } else {
                $(".form-signin-heading").text(data.info);
                $("#login-btn").text("Sign in");
            }
        }
    });
});
//});

$("#form-register").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: registerUrl,
        data: $("#form-register").serialize(),
        statusCode: {
            401: function (response) {
                $("button .btn.btn-lg.btn-primary.btn-block").prop("value", response.toString + "请重试!");
            },
            200: function () {
                $(".row.centered-form").hide();
                showRegister = false;
                $(".form-signin-heading").text("注册成功!");
            }
        }
    })
});
