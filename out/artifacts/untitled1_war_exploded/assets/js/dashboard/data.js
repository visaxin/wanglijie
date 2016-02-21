/**
 * Created by jason on 12/13/15.
 */
var hostPort = "http://localhost:8080/"
var searchRoomUrl = hostPort + "room?isSearch=true";
var addRoomUrl = hostPort+ "room";
var sellPosDataUrl = hostPort + "possell";
var checkInUrl = hostPort + "checkin";
var addFoodUrl = hostPort + "food";
var selectFoodUrl = hostPort + "food";
var hrSelectUrl = "http://localhost:8080/employee";
var hrAddUrl = "http://localhost:8080/employee";
var is_first_room = true;

//var sessionKey = localStorage.sessionKey;
//searchRoomUrl +="?sessionKey="+sessionKey;
//sellPosDataUrl +="?sessionKey="+sessionKey;
//addFoodUrl +="?sessionKey="+sessionKey;
//hrSelectUrl +="?sessionKey="+sessionKey;

var isFirstRoom = true;
var roomListId = "room_list_tbody";
function roomList(data) {
    if (isFirstRoom == true) {
        $.each(data, function (index, dataObject) {
            var createTr = $("<tr>");
            $.each(dataObject, function (k, v) {
                if (k != "id") {
                    createTr.append("<td>" + v + "</td>");
                }
            });
            createTr.appendTo("#" + roomListId);
            isFirstRoom = false;
        })
    } else {
        var t_body = $("<tbody>", {
            id: roomListId
        });

        $.each(data, function (index, dataObject) {
            var createTr = $("<tr>");
            $.each(dataObject, function (k, v) {
                if (k != "id") {
                    createTr.append("<td>" + v + "</td>");
                }
            });
            createTr.appendTo(t_body);
        });

        $("#" + roomListId).replaceWith(t_body);
    }
}
function showSearchDataTable(res){


    if ($.fn.dataTable.isDataTable('#room_list_table')) {
        var table = $('#room_list_table').DataTable();
        table.destroy();
        table = $("#room_list_table").DataTable(
            {
                data: res.data,
                deferRender: true,
                "bProcessing": true,
                "bAutoWidth": true,
                "order": [1, "DESC"],
                "columns": [
                    {"data": "roomId"},
                    {"data": "roomType"},
                    {"data": "roomPrice"},
                    {"data": "roomStatus"},
                    {"data": "updateTime"}
                ]
            }
        )
    } else {
        table = $('#room_list_table').DataTable({
            data: res.data,
            deferRender: true,
            "bProcessing": true,
            "bAutoWidth": true,
            "order": [1, "DESC"],
            "columns": [
                {"data": "roomId"},
                {"data": "roomType"},
                {"data": "roomPrice"},
                {"data": "roomStatus"},
                {"data": "updateTime"}
            ]
        });
    }
}
//search room
$("#first_form").submit(function (event) {
    event.preventDefault();

    $.ajax({
        type: "GET",
        url: searchRoomUrl,
        data: $("#first_form").serialize(),
        success: function (data) {
            console.log(data);
            $("first_form_result").append("<li class='list-group-item list-group-item-success'>成功!</li>");
            //roomList(data);
            showSearchDataTable(data);

        }
    })
});
//check in
$("#second_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: checkInUrl,
        data: $("#second_form").serialize(),
        statusCode: {
            503: function () {
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
        type: "POST",
        url: addFoodUrl,
        data: $("#third_form").serialize(),
        statusCode: {
            401: function () {
                $("#third_form").append("<li class='list-group-item list-group-item-success'>增加失败!</li>")
            },
            200: function () {
                $("#third_form").append("<li class='list-group-item list-group-item-success'>成功!</li>")

                window.setTimeout(function () {
                    $("#third_form").children("li:first").remove();
                }, 5000);
            }
        }
    })
});
//add new employee==> works
$("#fourth_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: hrAddUrl,
        data: $("#fourth_form").serialize(),
        success: function (data) {
            console.log(data);
            $("#fourth_form").append("<li class='list-group-item list-group-item-success'>成功!</li>")
            window.setTimeout(function () {
                $("#fourth_form").children("li:first").remove();
            }, 5000);
        }
    }).fail(function () {
        $("#fourth_form").append("<li class='list-group-item list-group-item-success'>失败!</li>")
        window.setTimeout(function () {
            $("#fourth_form").children("li:first").remove();
        }, 5000);
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
    $('#sell_pos_table').DataTable({
        "ajax": sellPosDataUrl,
        deferRender: true,
        "bProcessing": true,
        "bAutoWidth": true,
        "order": [3, "DESC"],
        "columns": [
            {"data": "roomNumber"},
            {"data": "roomPrice"},
            {"data": "roomStatus"},
            {"data": "updateTime"},
            {"data": "realIncome"}
        ]
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
            if (isFirstRoom == true) {
                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(foodDataId);
                    isFirstRoom = false;
                })
            } else {
                var t_body = $("<tbody>", {
                    id: foodDataId
                });

                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(t_body);
                });

                $("#" + foodDataId).replaceWith(t_body);
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
            if (isFirstRoom == true) {
                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(hr_tbody);
                    isFirstRoom = false;
                })
            } else {
                var t_body = $("<tbody>", {
                    id: hr_tbody
                });

                $.each(data, function (index, dataObject) {
                    var createTr = $("<tr>");
                    $.each(dataObject, function (k, v) {
                        createTr.append("<td>" + v + "</td>");
                    });
                    createTr.appendTo(t_body);
                });

                $("#" + hr_tbody).replaceWith(t_body);
            }
        },
    });
}
workerHRData();


$(document).ready(function () {
    // run the first time; all subsequent calls will take care of themselves
    //setInterval(workerSellData, 1400);
    //setInterval(workerFoodData, 1800);
    //setInterval(workerHRData, 2200);
    $('#hr_table').DataTable({
        "ajax": hrSelectUrl,
        deferRender: true,
        "bProcessing": true,
        "bAutoWidth": true,
        "order": [1, "DESC"],
        "columns": [
            {"data": "idNumber"},
            {"data": "name"},
            {"data": "email"},
            {"data": "gender"},
            {"data": "role"},
            {"data": "age"},
            {"data": "salary"},
            {"data": "updateTime"}
        ]
    });
    $('#room_list_table').DataTable({
        "ajax": addRoomUrl,
        deferRender: true,
        "bProcessing": true,
        "bAutoWidth": true,
        "order": [1, "DESC"],
        "columns": [
            {"data": "roomId"},
            {"data": "roomType"},
            {"data": "roomPrice"},
            {"data": "roomStatus"},
            {"data": "updateTime"}
        ]
    });


    $("#food_list").DataTable({
        "ajax": selectFoodUrl,
        deferRender: true,
        "bProcessing": true,
        "bAutoWidth": true,
        "order": [1, "DESC"],
        "columns": [
            {"data": "foodName"},
            {"data": "foodPrice"},
            {"data": "foodLeft"},
            {"data": "realOutput"},
            {"data": "updateTime"}
        ]
    })
});






