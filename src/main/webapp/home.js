
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}


function fillTable(FlightContainer) {
    var template = _.template($('#templateRow').html());;
    var result = "";
    FlightContainer.forEach(f=>result+=template(f));
    $("#result").html(result);
}

$(function () {
    $("#buttonGet").click(function () {
    
	   var departure=$("#departure").val();
	   
	   var arrival=$("#arrival").val();
	    
	   var seat=$("#seat").val();
	   	    
	   getServerData("ws/flight/search/" + departure +"/"+arrival+"/"+seat, fillTable());
    });
});