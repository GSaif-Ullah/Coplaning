
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
	var seat=searchParams.get('Seat')

	   	    
	  // getServerData("ws/flight/" + departure +"/"+arrival+"/"+seat, fillTable2);
	getServerData("ws/flight/all",fillTable2);
	    console.log(result);

        });