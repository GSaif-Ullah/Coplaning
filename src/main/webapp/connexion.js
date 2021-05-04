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
    $("#buttonLogin").click(function () {
    
	   var username=$("#mail").val();
	   
	   var password=$("#psswrd").val();
	   getServerData("ws/passenger/add/" + username, function (result2) {
       	storage=localStorage;
       	localStorage.setItem('id_pilot',result2.passenger.id_pilot);
       });
       getServerData("ws/passenger/check/" + username +"/"+ password, function (result) {
        if (result==true){
        	window.location.replace("home.html");
        	localStorage.setItem('Mail',username);                    
        	}
        
        else{
        		alert("Identifiants incorrect ");
        }
        });
    });
});