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
			"passengers": [{
             "firstname":passenger.firstname,
             "name":passenger.name,
             "password":passenger.password,
             "email":passenger.email,
             "phone":passenger.phone,
             "birth":passenger.birth             
            }
			]
		});
		
        putServerData("ws/passenger", data, function (result) {
            alert("Success " + result);
        });
    });
});