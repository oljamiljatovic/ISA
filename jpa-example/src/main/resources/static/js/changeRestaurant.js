$(document).on('click','#restoran',function(e){
	e.preventDefault();
	
	$('#content').empty();
	
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form method="post" id="registracijaRestorana">'+
		'Podaci o restoranu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imeRestorana" class="in-text"/><br/>'+
		'Vrsta restorana:<input type = "text" id = "vrstaRestorana" class="in-text"/><br/>'+
		'Adresa:<input type = "text" id = "adresaRestorana" class="in-text"/><br/>'+
		'Kontakt:<input type = "text" id = "kontaktRestorana" class="in-text"/><br/>'+
		'<input type = "submit" id = "submitIzmenaRestorana" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
	$.ajax({
		type : 'GET',
		url :  '/restaurantManagerController/uzmiRestoranMenadzera',
		contentType : 'application/json',
		success : function(data){
			
			$('#imeRestorana').val(data.name);
			$('#vrstaRestorana').val(data.type);
			$('#adresaRestorana').val(data.address);
			$('#kontaktRestorana').val(data.contact);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("Da li je ovdje problem");
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});


$(document).on('click','#submitIzmenaRestorana',function(e){
	e.preventDefault();
	var name = $('#imeRestorana').val();
	var address = $('#adresaRestorana').val();
	var contact = $('#kontaktRestorana').val();
	var type = $('#vrstaRestorana').val();
	var drinks = [];
	var meals = [];
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
			url :  '/restaurantManagerController/updateRestaurant',
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


$(document).on('click','#dodajRadnika',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
			'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
				'<form method="post" id="registracijaMenadzera">'+
					'Podaci o radniku:<br/><br/>Vrsta:'+
					'<select id="vrstaRadnika"><option value="cook">cook</option>'+
				'<option value="waiter">waiter</option><option value="barman">barman</option></select>'+
					'<br/>Ime:<input type = "text" id = "imeRadnika" class="in-text"/>'+
					'<br/>Prezime:<input type = "text" id = "prezimeRadnika" class="in-text"/>'+
					'<br/>Email:<input type = "text" id = "emailRadnika" class="in-text"/>'+
					'<br/>Datum rodjenja:<input type = "date" id = "rodjenjeRadnika" class="in-text"/>'+
					'<br/>Konfekcijski broj:'+
					'<input type = "text" id = "konfekcijskiRadnik" class="in-text"/>'+
					'<br/>Velicina obuca:<input type = "text" id = "obucaRadnik" class="in-text"/>'+
					'<br/><input type = "submit" id = "submitNewEmployee" value="Submit" class="btn orange">'+
					'</form></div></div></div></div>');
});


$(document).on('click','#submitNewEmployee',function(e){
	e.preventDefault();
	var name = $('#imeRadnika').val();
	var surname = $('#prezimeRadnika').val();
	var date = $('#rodjenjeRadnika').val();
	var type = $('#vrstaRadnika option:selected').val();
	var konfekc = $('#konfekcijskiRadnik').val();
	var obuca = $('#obucaRadnik').val();
	var email = $('#emailRadnika').val();
	var rest = 0;
	var accept = "false";
	var password = "111";
	if(name == ""){
		alert("Ime je prazno");
	}else if(surname == ""){
		alert("Prezime je prazno");
	}else if(date == ""){
		alert("Datum rodjenja je prazan");
	}else if(type ==""){
		alert("Vrsta je prazna");
	}else if(konfekc ==""){
		alert("Konfekcijski broj je prazan");
	}else if(obuca ==""){
		alert("Informacije o obuci su prazne");
	}else{
		var data2 = JSON.stringify({
			"role" : type,
			"name" : name,
			"surname" : surname,
			"dateBirth" : date,
			"confNumber" : konfekc,
			"shoeNumber" : obuca,
			"restaurant" : rest,
			"email" : email,
			"accept" : accept,
			"password" : password
		});
		$.ajax({
			type : 'POST',
			url :  '/restaurantManagerController/addEmployee',
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


