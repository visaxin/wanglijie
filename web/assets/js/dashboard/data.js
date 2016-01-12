/**
 * Created by jason on 12/13/15.
 */

var searchRoomUrl = "http://localhost:8080/room";
var addRoomUrl = "http://localhost:8080/room";
var sellPosDataUrl;
var checkInUrl;
var addFoodUrl;
var selectFoodUrl;
var hrSelectUrl;
var hrAddUrl;
var is_first = true;

//var sessionKey = localStorage.sessionKey;
//searchRoomUrl +="?sessionKey="+sessionKey;
//sellPosDataUrl +="?sessionKey="+sessionKey;
//addFoodUrl +="?sessionKey="+sessionKey;
//hrSelectUrl +="?sessionKey="+sessionKey;

//search room
$("#first_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "GET",
        url: searchRoomUrl,
        data: $("#first_form").serialize(),
        statusCode: {
            401: function () {
                $("first_form_result").append("<li class='list-group-item list-group-item-success'>增加失败!</li>")
            },
            200: function () {
                $("first_form_result").append("<li class='list-group-item list-group-item-success'>成功!</li>")
            }
        }
    })
});
//check in
$("#second_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "post",
        url: checkInUrl,
        data: $("#second_form").serialize(),
        statusCode: {
            401: function () {
                $("second_form").append("<li class='list-group-item list-group-item-success'>增加失败!</li>")
            },
            200: function () {
                $("second_form").append("<li class='list-group-item list-group-item-success'>成功!</li>")
            }
        }
    })
});
//add food
$("#third_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "GET",
        url: addFoodUrl,
        data: $("#third_form").serialize(),
        statusCode: {
            401: function () {
                $("#third_form").append("<li class='list-group-item list-group-item-success'>增加失败!</li>")
            },
            200: function () {
                $("#third_form").append("<li class='list-group-item list-group-item-success'>成功!</li>")
            }
        }
    })
});
//add new employee
$("#fourth_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "GET",
        url: hrAddUrl,
        data: $("#fourth_form").serialize(),
        statusCode: {
            401: function () {
                $("#fourth_form").append("<li class='list-group-item list-group-item-success'>增加失败!</li>")
            },
            200: function () {
                $("#fourth_form").append("<li class='list-group-item list-group-item-success'>成功!</li>")
            }
        }
    })
});
$("#fifth_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: addRoomUrl,
        data: $("#fifth_form").serialize(),
        statusCode: {
            503: function () {
                alert("房间编号不能重复,请重试!");
            },
            200: function () {
                $("#myModal").modal("toggle");
            }
        }
    })
});

function workerSellData() {
    var sellDataId = "sell_pos_tbody";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: sellPosDataUrl,
        success: function (data) {
            if (is_first == true) {
                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(sellDataId);
                    is_first = false;
                })
            } else {
                var t_body = $("<tbody>",{
                    id:sellDataId
                });

                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(t_body);
                });

                $("#"+sellDataId).replaceWith(t_body);
            }
        },
    });
}
workerSellData();

function workerFoodData() {
    var foodDataId = "food_manage_data_tbody";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: selectFoodUrl,
        success: function (data) {
            if (is_first == true) {
                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(foodDataId);
                    is_first = false;
                })
            } else {
                var t_body = $("<tbody>",{
                    id:foodDataId
                });

                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(t_body);
                });

                $("#"+foodDataId).replaceWith(t_body);
            }
        },
    });
}
workerFoodData();


function workerHRData() {
    var hr_tbody = "hr_tbody";
    $.ajax({
        type: "GET",
        dataType: "json",
        url: hrSelectUrl,
        success: function (data) {
            if (is_first == true) {
                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(hr_tbody);
                    is_first = false;
                })
            } else {
                var t_body = $("<tbody>",{
                    id:hr_tbody
                });

                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(t_body);
                });

                $("#"+hr_tbody).replaceWith(t_body);
            }
        },
    });
}
workerHRData();


$(document).ready(function () {
    // run the first time; all subsequent calls will take care of themselves
    setInterval(workerSellData, 1400);
    setInterval(workerFoodData,1800);
    setInterval(workerHRData,2200);
});







