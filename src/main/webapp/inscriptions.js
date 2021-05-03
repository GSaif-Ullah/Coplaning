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
        data: JSON.stringify(data),
        url: url
    }).done(success);
}

$(function () {
    $("#buttonAdd").click(function () {
        var data = {
        	"passenger":{
	            "firstname":$("#firstname").val(),
	            "name":$("#name").val(),
	            "password":$("#password").val(),
	            "email":$("#email").val(),
	            "phone":$("#phone").val(),
	            "birth":$("#birth").val()
	            }                  
        };
        var password2=$("#password2").val();
        
        if (data.passenger.firstname=="" ||  data.passenger.name==""  || data.passenger.password==""  || data.passenger.password2=="" || data.passenger.email=="" || data.passenger.phone==""  || data.passenger.birth==""){
            alert("Veuillez vérifier les champs vides ");
        }
        
	    else{
			if(data.passenger.email.indexOf("@")<1 ||data.passenger.email.lastIndexOf(".")<data.passenger.email.indexOf("@")+2 ){
				alert("Veuillez entrer une adresse mail valide");
			}
			
			if (data.passenger.password.length<8){
				alert("Erreur !Le mot de passe doit faire au moins 8 caractères ");
			} 
		    
			if (data.passenger.password!=password2){
				alert("Erreur !Les mots de passe ne correspondent pas ");
			}
				
		    if (gridCheck.checked==false){
		    	alert("Veuillez accepter les conditions d'utilisation");
		    }
		    else{				
				getServerData("ws/passenger/check/" + data.passenger.email, function (result) {
					if (result==true){
					   alert("Adresse mail déjà utilisée");
					}
					else {
					   putServerData("ws/passenger", data, function (result) {           
					   alert("Inscription réussie, bonne réservation ! ");
					   window.location.replace("home.html");
					   }); 
					}
				});
		    }
	    }
        	
    });
});