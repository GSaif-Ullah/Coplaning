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
        var data2 = {
            	"pilot":{
    	            "firstname":$("#firstname").val(),
    	            "name":$("#name").val(),
    	            "password":$("#password").val(),
    	            "email":$("#email").val(),
    	            "phone":$("#phone").val(),
    	            "id_passenger":27,
    	            "birth":$("#birth").val()   	       
    	            }                  
            };
        var password2=$("#password2").val();
        var a=data.passenger.firstname=="" ||  data.passenger.name==""  || data.passenger.password==""  || data.passenger.password2=="" || data.passenger.email=="" || data.passenger.phone==""  || data.passenger.birth=="";
        var b=data.passenger.email.indexOf("@")<1 ||data.passenger.email.lastIndexOf(".")<data.passenger.email.indexOf("@")+2;
        var c=data.passenger.password.length<8 ;
        var d= data.passenger.password!=password2;
        var e=gridCheck.checked==false;
        
        if (a){
            alert("Veuillez vérifier les champs vides ");
        }
        
	    else{
			if(b ){
				alert("Veuillez entrer une adresse mail valide");
			}
			
			if (c){
				alert("Le mot de passe doit faire au moins 8 caractères ");
			} 
		    
			if ( d){
				alert("Les deux mots de passe ne correspondent pas ");
			}
				
		    if (e){
		    	alert("Veuillez accepter les conditions d'utilisation");
		    }
		    else{				
				getServerData("ws/passenger/check/" + data.passenger.email, function (result) {
					if (result==true){
					   alert("Adresse mail déjà utilisée");
					}
					if (!a && !b && !c && !d && !e && result==false) {
					   putServerData("ws/passenger", data, function (result){
						   data2.pilot.id_passenger=result;
						   putServerData("ws/pilot", data2, function (result) {           
							   alert("Inscription réussie ! ");
							   window.location.replace("home.html");
							   }); 
					   });
					   
					}
				});
		    }
	    }
        	
    });
});