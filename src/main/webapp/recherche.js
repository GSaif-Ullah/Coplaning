
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

function postServerData(url, success) {
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: url
    }).done(success);
}

function fillTable(FlightContainer) {
    var template = _.template($('#templateRow').html());;
    var result = "";
    container.forEach(f=>result+=template(f));
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
	var departure=searchParams.get('Departure');
	var arrival=searchParams.get('Arrival');
	var datedep=searchParams.get('Date1');
	var datedar=searchParams.get('Date2');
    var cost1=searchParams.get('CostMin');
    var cost2=searchParams.get('CostMax');
    var seat=searchParams.get('Seat');

	   if (departure!=null && arrival !=null && datedep != null && datedar !=null && cost1==null && cost2==null && seat==null){    
           getServerData("ws/flight/" + departure +"/"+arrival+"/"+datedep+"/"+datedar, function(result){
           fillTable2(result)});
        }
 	   if (departure!=null && arrival !=null && datedep != null && datedar ==null && cost1==null && cost2==null && seat==null){    
           getServerData("ws/flight/" + departure +"/"+arrival+"/"+datedep, function(result){
           fillTable2(result)});
        }
	   if (departure!=null && arrival ==null && datedep != null && datedar !=null && cost1==null && cost2==null && seat!=null)  {  
           getServerData("ws/flight/" + departure +"/"+datedep+"/"+datedar, function(result){
           fillTable2(result)});
        }
 	   if (departure!=null && arrival !=null && datedep == null && datedar ==null && cost1==null && cost2==null && seat==null){    
           getServerData("ws/flight/location/" + departure +"/"+arrival, function(result){
           fillTable2(result)});
        }
 	   if (departure!=null && arrival ==null && datedep == null && datedar ==null && cost1==null && cost2==null && seat==null) {   
           getServerData("ws/flight/location/" + departure, function(result){
           fillTable2(result)});
        }
         if (departure!=null && arrival ==null && datedep != null && datedar ==null && cost1==null && cost2==null && seat==null) {   
           getServerData("ws/flight/" + departure + "/" +datedep, function(result){
           fillTable2(result)});
        }
        if (departure!=null && arrival !=null && datedep == null && datedar ==null && cost1!=null && cost2==null && seat!=null) {   
           getServerData("ws/flight/seat/" + departure +"/"+arrival +"/"+seat+"/"+cost1, function(result){
           fillTable2(result)});
        }
       if (departure!=null && arrival !=null && datedep == null && datedar ==null && cost1==null && cost2==null && seat!=null) {   
           getServerData("ws/flight/seat/" + departure +"/"+arrival +"/"+seat, function(result){
           fillTable2(result)});
        }
     	if (departure!=null && arrival !=null && datedep != null && datedar !=null && cost1==null && cost2==null && seat!=null) {   
           getServerData("ws/flight/dep/" + departure +"/"+arrival+"/"+seat+"/"+datedep+"/"+datedar, function(result){
           fillTable2(result)});
        }
        if (departure!=null && arrival !=null && datedep != null && datedar !=null && cost1!=null && cost2!=null && seat!=null) {   
           getServerData("ws/flight/dep/" + departure +"/"+arrival+"/"+seat+"/"+cost1+"/"+cost2+"/"+datedep+"/"+datedar, function(result){
           fillTable2(result)});
        }
        if (departure!=null && arrival !=null && datedep == null && datedar ==null && cost1!=null && cost2!=null && seat!=null) {   
           getServerData("ws/flight/dep/" + departure +"/"+arrival+"/"+seat+"/"+cost1+"/"+cost2, function(result){
           fillTable2(result)});
        }
    
    });
$( document ).ready(function() {
    $("#buttonSearch").click(function () {
    var seat=$("#nbpassager").val();
    var newdeparture=$("#departure").val();
    var newarrival=$("#arrival").val();
    var newdatedep=$("#date1").val();
    var newdatedar=$("#date2").val();
    var pricemini=$("#cost1").val();
    var pricemax=$("#cost2").val();
    var id_flight=$("#buttonBook").val();
    var seatbook=$("#seatbook").val();

       //all
       if (newdeparture!="" && newarrival !="" && newdatedep != "" && newdatedar !="" && pricemini!="" && pricemax!="" && seat!=0){  
		  document.location.href='recherche.html?Departure='+newdeparture + '&Arrival='+newarrival + '&Date1='+newdatedep + '&Date2='+newdatedar + '&Seat='+seat+ '&CostMin='+pricemin+ '&CostMax='+pricemax;		      
        }
 	   if (newdeparture!="" && newarrival !="" && newdatedep != "" && newdatedar !="" && pricemini=="" && pricemax=="" && seat!=0){ 
		  document.location.href='recherche.html?Departure='+newdeparture + '&Arrival='+newarrival + '&Date1='+newdatedep + '&Date2='+newdatedar + '&Seat='+seat;		             
        }
       
       if (newdeparture!="" && newarrival !="" && newdatedep == "" && newdatedar =="" && pricemini!="" && pricemax!="" && seat!=0){ 
 		  document.location.href='recherche.html?Departure='+newdeparture + '&Arrival='+newarrival + '&Seat='+seat+ '&CostMin='+pricemin+ '&CostMax='+pricemax;		                
        }
       if (newdeparture!="" && newarrival =="" && newdatedep == "" && newdatedar =="" && pricemini=="" && pricemax=="" && seat==0){    
		      document.location.href='recherche.html?Departure='+newdeparture;		

        }
       if (newdeparture!="" && newarrival !="" && newdatedep == "" && newdatedar =="" && pricemini=="" && pricemax=="" && seat==0){ 
          		  document.location.href='recherche.html?Departure='+newdeparture + '&Arrival='+newarrival;		      
 
        }
       if (newdeparture!="" && newarrival =="" && newdatedep != "" && newdatedar !="" && pricemini=="" && pricemax=="" && seat!=0){  
           		  document.location.href='recherche.html?Departure='+newdeparture + '&Seat='+seat;		      

        }
       if (newdeparture!="" && newarrival !="" && newdatedep == "" && newdatedar =="" && pricemini=="" && pricemax=="" && seat!=0){  
           		  document.location.href='recherche.html?Departure='+newdeparture + '&Arrival='+newarrival + '&Seat='+seat;		      

        }  
   });
});

//Reservation
function tdclick(){
        var id_flight=$("#buttonBook").val();
        if(localStorage.getItem("Mail") === null){
          alert("Veuillez vous connecter pour pouvoir réserver");
        }
        else{
            getServerData("ws/passenger/get/"+localStorage.getItem("Mail"),function (result1) {
                if (result1.passenger.flights.indexOf(parseInt(id_flight))==-1){
                    postServerData("ws/passenger/"+result1.id+"/"+ id_flight, function (result2) {});
                    postServerData("ws/flight/"+id_flight+"/"+result1.id, function (result) {});
                    postServerData("ws/flight/seat/"+id_flight+"/"+seatbook,function(result){});
                }
                else{
                    alert("Vous avez déjà réserver ce vol");
                }
                
            });
        }
};


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
