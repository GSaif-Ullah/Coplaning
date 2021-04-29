
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function callDone(result){
	var templateExample = _.template($('#templateExample').html());

	var html = templateExample({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}

$(function () {
    $("#buttonSearch").click(function () {
    
	   var departure=$("#departure").val();
	   
	   var arrival=$("#arrival").val();
	    
	   var seat=$("#seat").val();
	   	    
       getServerData("ws/flight/search/" + departure +"/"+arrival+"/"+seat, function (result) {
       
        if (result==true){
        	window.location.replace("home.html");
        }
        else{
        		alert("Identifiants incorrect ");
        }
        });
    });
});