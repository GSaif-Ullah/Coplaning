
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
	var date=searchParams.get('Date')

	   	    
	   getServerData("ws/flight/" + departure +"/"+arrival+"/"+date, function(result){
	   fillTable2(result);
	   console.log(result.flight);
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
//});
	   });
	   
	  });
