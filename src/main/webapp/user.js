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
    $("#buttonInfo").click(function () {
        var data = {
        	"passenger":{
	            "firstname":$("#FirstName").val(),
	            "name":$("#LastName").val(),
	            "email":$("#email").val(),
	            "phone":$("#phone").val(),
	            "birth":$("#birth").val()
	            }                  
        };
        
        var a=data.passenger.firstname=="" ||  data.passenger.name==""  ||  data.passenger.email=="" || data.passenger.phone==""  || data.passenger.birth=="";
        var b=data.passenger.email.indexOf("@")<1 ||data.passenger.email.lastIndexOf(".")<data.passenger.email.indexOf("@")+2;
      
        
        if (a){
            alert("Veuillez vérifier les champs vides ");
        }
        
	    else{
			if(b ){
				alert("Veuillez entrer une adresse mail valide");
			}
			
		
		    else{				
				getServerData("ws/passenger/check/" + data.passenger.email, function (result) {
					if (result==true){
					   alert("Adresse mail déjà utilisée");
					}				
					if (!a && !b && result==false) {
					   putServerData("ws/passenger", data, function (result) {						
					   alert("Chengement Réussi !");
					   window.location.replace("user.html");
					   }); 
					}
				});
		    }
	    }
        	
    });
});

$(function () {
    $("#buttonMdp").click(function () {
        var data = {
        	"passenger":{
	            "password":$("#password").val(),
	            }                  
        };
        var password2=$("#password2").val();
        var a= data.passenger.password==""  || data.passenger.password2=="";
        var c=data.passenger.password.length<8 ;
        var d= data.passenger.password!=password2;
 
        
        if (a){
            alert("Veuillez vérifier les champs vides ");
        }
        
	    else{
		
			if (c){
				alert("Le mot de passe doit faire au moins 8 caractères ");
			} 
		    
			if ( d){
				alert("Les deux mots de passe ne correspondent pas ");
			}
				
		  
		    else{				
				getServerData("ws/passenger/check/" + data.passenger.email, function (result) {
							
					if (!a && !c && !d && result==false) {
					   putServerData("ws/passenger", data, function (result) {						
					   alert("Changement de mot de passe réussi ! ");
					   window.location.replace("user.html");
					   }); 
					}
				});
		    }
	    }
        	
    });
});