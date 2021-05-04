
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
    $("#deconnexion").click(function () {
		localStorage.removeItem("Mail");
		location.reload();   	
	});
});	
    
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
    if(localStorage.getItem("Mail") === null){
        document.getElementById("connexion").style.display='block';
        document.getElementById("inscription").style.display='block';        
        document.getElementById("deconnexion").style.display='none';

    }else{ 
        document.getElementById("connexion").style.display='none';
        document.getElementById("inscription").style.display=	'none';  
        document.getElementById("deconnexion").style.display='block';
    }