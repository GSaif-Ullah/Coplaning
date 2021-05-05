
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
        localStorage.removeItem("id_pilot");
		location.reload();   	
	});
});	
    
$(function () {
    $("#buttonSearch").click(function () {
    
	   var departure=$("#departure").val();
	   
	   var arrival=$("#arrival").val();
	    
	   var datedep=$("#datedep").val();
       var datedar=$("#datedar").val();
       	
       	if (datedep>datedar){
       		alert("Erreur votre date de départ et d'arrivée ne sont valide");
			location.reload();   	
       	}
	   
		if(departure.length > 0 && arrival.length>0 && datedep.length>0 && datedar.length>0) {
		      document.location.href='recherche.html?Departure='+departure + '&Arrival='+arrival + '&Date1='+datedep + '&Date2='+datedar;		
		}
        if(departure.length > 0 && arrival.length>0 && datedep.length>0 && datedar.length==0) {
		      document.location.href='recherche.html?Departure='+departure + '&Arrival='+arrival + '&Date1='+datedep ;		
		}
  	    if(departure.length > 0 && datedar.length>0 && datedep.length>0 && arrival.length==0) {
		      document.location.href='recherche.html?Departure='+departure + '&Date1='+datedep + '&Date2='+datedar;		
		}
        if(departure.length > 0 && arrival.length>0 && datedep.length==0 && datedar.length==0) {
		      document.location.href='recherche.html?Departure='+departure + '&Arrival='+arrival;		
		}
        if(departure.length > 0 && arrival.length==0 && datedep.length==0 && datedar.length==0)  {
		      document.location.href='recherche.html?Departure='+departure;		
		}
        if (departure.length==0){
            alert("Veuillez renseigner un aérodrome de départ")
        }
        if (departure.length!=0 && arrival.length==0 && datedep.length==0 && datedar.length>0) {
        	  alert("Veuillez renseigner une date de départ")
        	
        }
    });
});
    // Cache les affichages inutiles 
    if(localStorage.getItem("Mail") === null){
        document.getElementById("connexion").style.display='initial';
        document.getElementById("inscription").style.display='initial';        
        document.getElementById("deconnexion").style.display='none';
        document.getElementById("inscription_pilote").style.display='initial';
        document.getElementById("pilote").style.display='none';
        document.getElementById("user").style.display='none';


    }
    else{ 
        if (localStorage.getItem("id_pilot")==0){
            document.getElementById("connexion").style.display='none';
            document.getElementById("inscription").style.display='none';  
            document.getElementById("inscription_pilote").style.display='none';
            document.getElementById("deconnexion").style.display='initial';
            document.getElementById("pilote").style.display='none';    
            document.getElementById("user").style.display='initial';    

        }
        else{
            document.getElementById("connexion").style.display='none';
            document.getElementById("inscription").style.display='none';  
            document.getElementById("inscription_pilote").style.display='none';
            document.getElementById("deconnexion").style.display='initial';
            document.getElementById("user").style.display='none';    
            document.getElementById("pilote").style.display='initial';   
        }

    }