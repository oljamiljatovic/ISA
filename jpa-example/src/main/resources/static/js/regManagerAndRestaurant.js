$(document).ready(function(){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/registerController/uzmiUlogovanog',
		success : function(data){
			if(data.email!="mica")
				$('#registrMenadzSis').empty();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
});

$(document).on('click','#registrMenadz',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap">'+
			'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
				'<form method="post" id="registracijaMenadzera">'+
					'Podaci o menadzeru:<br/><br/>'+
					'Ime:<input type = "text" id = "imeMenadzera" class="in-text"/><br/>'+
					'Prezime:<input type = "text" id = "prezimeMenadzera" class="in-text"/><br/>'+
					'Adresa:<input type = "text" id = "adresaMenadzera" class="in-text"/><br/>'+
					'Email:<input type = "text" id = "emailMenadzera" class="in-text"/><br/>'+
					'Kontakt:<input type = "text" id = "kontaktMenadzera" class="in-text"/><br/>'+
					'Lozinka:<input type = "password" id = "lozinkaMenadzera" class="in-text"/><br/>'+
					'Izaberi restoran:<select id="restoranMenadzera"> </select><br/>'+
					'<input type = "submit" id = "submit" value="Submit" class="btn orange">'+
					'</form></div></div></div>');
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/registerController/uzmiRestorane',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,restoran){
				$('#restoranMenadzera').append('<option value = "' +restoran.id +'" >' + restoran.name + '</option>');
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
	var data2 = JSON.stringify({
		"id" : restaurant,
		"name" : "",
		"type" : "",
		"address" : "",
		"contact" : ""
	});
	
	var obj = JSON.parse(data2);
	
	var dataa = JSON.stringify({
		"name" : name,
		"surname" : surname,
		"address" : address,
		"email" : email,
		"contact" : contact,
		"password" : password,
		"restaurant" : obj,
		"role" : "restaurantManager",
		"accept" : "true"
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


$(document).on('click','#registrRest',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap">'+
			'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
			'<form method="post" id="registracijaRestorana">'+
			'Podaci o restoranu:<br/><br/>'+
			'Naziv:<input type = "text" id = "imeRestorana" class="in-text"/><br/>'+
			'Vrsta restorana:<input type = "text" id = "vrstaRestorana" class="in-text"/><br/>'+
			'Adresa:<input type = "text" id = "adresaRestorana" class="in-text"/><br/>'+
			'Kontakt:<input type = "text" id = "kontaktRestorana" class="in-text"/><br/>'+
			'<input type = "submit" id = "submit" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
});

$(document).on('submit','#registracijaRestorana',function(e){
	e.preventDefault();
	var name = $('#imeRestorana').val();
	var address = $('#adresaRestorana').val();
	var contact = $('#kontaktRestorana').val();
	var type = $('#vrstaRestorana').val();
	if(name == ""){
		alert("Ime je prazno");
	}else if(address == ""){
		alert("Adresa je prazna");
	}else if(contact == ""){
		alert("Kontakt je prazno");
	}else{
		
		var data2 = JSON.stringify({
			"name" : name,
			"type" : type,
			"address" : address,
			"contact" : contact
		});
		$.ajax({
			type : 'POST',
			url :  '/registerController/registerRestaurant',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
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


$(document).on('click','#registrMenadzSis',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap">'+
			'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
				'<form method="post" id="registracijaMenadzeraSistema">'+
					'Podaci o menadzeru:<br/><br/>'+
					'Ime:<input type = "text" id = "imeMenadzera" class="in-text"/><br/>'+
					'Prezime:<input type = "text" id = "prezimeMenadzera" class="in-text"/><br/>'+
					'Adresa:<input type = "text" id = "adresaMenadzera" class="in-text"/><br/>'+
					'Email:<input type = "text" id = "emailMenadzera" class="in-text"/><br/>'+
					'Kontakt:<input type = "text" id = "kontaktMenadzera" class="in-text"/><br/>'+
					'Lozinka:<input type = "password" id = "lozinkaMenadzera" class="in-text"/><br/>'+
					'<input type = "submit" id = "submit" value="Submit" class="btn orange">'+
					'</form></div></div></div>');
});



$(document).on('submit','#registracijaMenadzeraSistema',function(e){
	e.preventDefault();
	var name = $('#imeMenadzera').val();
	var surname = $('#prezimeMenadzera').val();
	var address = $('#adresaMenadzera').val();
	var email = $('#emailMenadzera').val();
	var contact = $('#kontaktMenadzera').val();
	var password = $('#lozinkaMenadzera').val();
	var dataa = JSON.stringify({
		"name" : name,
		"surname" : surname,
		"address" : address,
		"email" : email,
		"contact" : contact,
		"password" : password,
		"role" : "systemManager",
		"accept" : "true"
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
			url :  '/registerController/registerManagerSys',
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