$(document).on('submit','#registracijaMenadzera',function(e){
	e.preventDefault();
	var name = $('#imeMenadzera').val();
	var surname = $('#prezimeMenadzera').val();
	var address = $('#adresaMenadzera').val();
	var email = $('#emailMenadzera').val();
	var contact = $('#kontaktMenadzera').val();
	var dataa = JSON.stringify({
		"name" : name,
		"surname" : surname,
		"address" : address,
		"email" : email,
		"contact" : contact
	});
	
	if(name == ""){
		alert("Ime je prazno");
	}else if(surname == ""){
		alert("Prezime je prazno");
	}else if(address == ""){
		alert("Adresa je prazna");
	}else if(email == ""){
		alert("Email je prazan");
	}else if(contact == ""){
		alert("Kontakt je prazan");
	}else{
		$.ajax({
			type : 'POST',
			url :  '/registerController/registerManager',
			contentType : 'application/json',
			dataType : 'json',
			data : dataa,
			success : function(data){
				alert(data.id);
				window.location.reload();
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		});
	}
});