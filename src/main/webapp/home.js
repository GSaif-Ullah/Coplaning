
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


function ListFlights(container) {
    var template = $('#output').html();

   var listFlight="";
   container.flight.forEach(flight=> listFlight+= template(flight.departure));
};

$(function () {
    $("#buttonGet").click(function () {
    
	   var departure=$("#departure").val();
	   
	   var arrival=$("#arrival").val();
	    
	   var seat=$("#seat").val();
	   	    
	   getServerData("ws/flight/search/" + departure +"/"+arrival+"/"+seat, function(){
	   	console.log(result.flight);});
    });
});