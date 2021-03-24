function putServerData(url, data,success){
    $.ajax({
        dataType: "json",
        url: url,
        data: data,
        type:"PUT"
    }).done(success);
}

function ButtonInscription(){
        var user ={
            "prenom":$("#txt_prenom").val().trim(),
            "nom":$("#txt_nom").val().trim(),
            "telephone":$("#txt_tel").val().trim(),
            "mail":$("#txt_mail").val().trim(),
            "Date de naissance":$("#txt_naissance").val().trim()
            
        };
        putServerData("/ws/passenger/93120",user,function(){
            if($("#txt_prenom").val.length==0){
                $("#txt_prenom").after("<span>Merci de remplir ce champ</span>");

            }
            console.log("Ok");
        })
}