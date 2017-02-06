$(document).ready(function(){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/registerController/uzmiRestorane',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,restoran){
				$('#restoranMenadzera').append('<option value = "' +restoran.name +'" >' + restoran.name + '</option>');
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
});

$(document).on('submit','#registracijaMenadzera',function(e){
	e.preventDefault();
	var name = $('#imeMenadzera').val();
	var surname = $('#prezimeMenadzera').val();
	var address = $('#adresaMenadzera').val();
	var email = $('#emailMenadzera').val();
	var contact = $('#kontaktMenadzera').val();
	var password = $('#lozinkaMenadzera').val();
	var restaurant = $('#restoranMenadzera option:selected').val();
	var dataa = JSON.stringify({
		"name" : name,
		"surname" : surname,
		"address" : address,
		"email" : email,
		"contact" : contact,
		"password" : password,
		"restaurant" : restaurant
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
				
				$.ajax({
	    			type : 'POST',
	    			url :  '/guestController/regIn',
	    			contentType : 'application/json',
	    			dataType :'json',
	    			data : JSON.stringify({
	    				"email" :email,
	    				"password" : password,
	    				"role" : "restaurantManager",
	    				"accept" : "true",
	    				"name" : name,
	    				"surname" : surname
	    			}),
	    			success : function(data){
	    				alert("Registracija je uspesno izvrsena!");
	    				window.location.reload();
	    			},

	    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
	    				alert("AJAX ERROR: " + errorThrown);
	    			}
	    			});
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		});
	}
});