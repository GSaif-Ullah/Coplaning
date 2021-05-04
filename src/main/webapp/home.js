
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
	    
	   var date=$("#date").val();
        console.log(date);
	   
		if(departure.length > 0 && arrival.length>0 && date.length>0) {
		      document.location.href='recherche.html?Departure='+departure + '&Arrival='+arrival + '&Date='+date;		
		}
  	    
    });
});
    if(localStorage.getItem("Mail") === null){
        document.getElementById("connexion").style.display='initial';
        document.getElementById("inscription").style.display='initial';        
        document.getElementById("deconnexion").style.display='none';
        document.getElementById("inscription_pilote").style.display='initial';
        document.getElementById("pilote").style.display='none';


    }else{ 
        document.getElementById("connexion").style.display='none';
        document.getElementById("inscription").style.display=	'none';  
        document.getElementById("inscription_pilote").style.display='none';
        document.getElementById("deconnexion").style.display='initial';
        document.getElementById("pilote").style.display='initial';

    }