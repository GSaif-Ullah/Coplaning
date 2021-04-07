function getServerData(url, success) {
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function putServerData(url, data, success) {
    $.ajax({
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: data,
        url: url
    }).done(success);
}

function fillTable(container) {
    var template = _.template($('#templateRow').html());
    var result = "";

    container.passengers.forEach(passenger => result += template(passenger));

    $("#result").html(result);
}

$(function () {
    $("#buttonAdd").click(function () {
        var passenger = {
            "firstname":$("#firstname").val(),
            "name":$("#name").val(),
            "password":$("#password").val(),
            "email":$("#email").val(),
            "phone":$("#phone").val(),
            "birth":$("#birth").val()                   
        };
		var data = JSON.stringify({
			"passengers": [
			JSON.stringify(passenger)
			]
		});
		
        putServerData("ws/passenger", data, function (result) {
            alert("Success " + result);
        });
    });

});