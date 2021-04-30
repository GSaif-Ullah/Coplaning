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
        data: JSON.stringify(data),
        url: url
    }).done(success);
}

$(function () {
    $("#buttonAdd").click(function () {
        var data = {
        	"passenger":{
	            "firstname":$("#firstname").val(),
	            "name":$("#name").val(),
	            "password":$("#password").val(),
	            "email":$("#email").val(),
	            "phone":$("#phone").val(),
	            "birth":$("#birth").val()
	            }                  
        };
		
        putServerData("ws/passenger", data, function (result) {
            alert("Success " + result);
        });
    });
});