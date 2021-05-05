
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

function postServerData(url, data, success) {
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: data,
        url: url
    }).done(success);
}

function fillTable(FlightContainer) {
    var template = _.template($('#templateRow').html());
    var result = "";

    FlightContainer.forEach(flight=>result+=JSON.stringify(flight))	;
    $("#result").html(result);
}

function fillTable2(container){
    var template = _.template($('#templateRow').html());;
    var result = "";
    container.forEach(f=>result+=template(f));
    $("#result").html(result);

}


$(function fonction() {
	var searchParams=new URLSearchParams(window.location.search)
	var departure=searchParams.get('Departure')
	var arrival=searchParams.get('Arrival')
	var datedep=searchParams.get('Date1')
	var datedar=searchParams.get('Date2')

	   if (departure!=null && arrival !=null && datedep != null && datedar !=null){    
           getServerData("ws/flight/" + departure +"/"+arrival+"/"+date1+"/"+date2, function(result){
           fillTable2(result)});
        }
 	   if (departure!=null && arrival !=null && datedep != null && datedar ==null){    
           getServerData("ws/flight/" + departure +"/"+arrival+"/"+datedep, function(result){
           fillTable2(result)});
        }
	   if (departure!=null && arrival ==null && datedep != null && datedar !=null)  {  
           getServerData("ws/flight/" + departure +"/"+datedep+"/"+datedar, function(result){
           fillTable2(result)});
        }
 	   if (departure!=null && arrival !=null && datedep == null && datedar ==null){    
           getServerData("ws/flight/" + departure +"/"+arrival, function(result){
           fillTable2(result)});
        }
 	   if (departure!=null && arrival ==null && datedep == null && datedar ==null) {   
           getServerData("ws/flight/" + departure, function(result){
           fillTable2(result)});
        }    
	   /*console.log(result.flight);
//	   $(function () {
   // $("#buttonBook").click(function () {
        if(localStorage.getItem("Mail") === null){
          alert("Veuillez vous connecter pour pouvoir réserver");
        }
        else{
            getServerData("ws/passenger/add/"+localStorage.getItem("Mail"),function (result1) {
            	postServerData("ws/passenger/"+result1.id+"/"+result.id,result, function (result2) {});							
					   alert("Réservation faite! ");
            });
        }
//    });
//});*/
	   }); 
	   
	 // });
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
