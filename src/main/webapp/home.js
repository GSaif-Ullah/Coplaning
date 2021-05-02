
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
    $("#buttonSearch").click(function () {
    
	   var departure=$("#departure").val();
	   
	   var arrival=$("#arrival").val();
	    
	   var seat=$("#seat").val();
	   
		if(departure.length > 0 && arrival.length>0 && seat.length>0) {
		      document.location.href='recherche.html?Departure='+departure + '&Arrival='+arrival + '&Seat='+seat;

		
		}
  	    
    });
});