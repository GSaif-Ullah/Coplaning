
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

$(function () {
	       $("#buttonGet").click(function () {
	   
	   	    
	   getServerData("ws/flight/search/departure1/arrival1/1", fillTable2); 
        });
});