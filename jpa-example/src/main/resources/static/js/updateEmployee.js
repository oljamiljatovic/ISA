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

$(document).on('click','#definisiRaspored',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
			'<form method="post" id="registracijaSmena">Raspored radnika:<br/><br/>'+
			'Ime i sifra radnika:<select id="radnikSifra"></select><br/><br/>'+
			'Pocetni datum:<input type = "date" id = "dateStart" class="in-text"/><br/<br/><br/>'+
			'Krajnji datum:<input type = "date" id = "dateEnd" class="in-text"/><br/<br/><br/>'+
			'Smene:<select id="radneSmene"><option value="prva">prva</option>'+
			'<option value="druga">druga</option><option value="treca">treca</option></select>'+
			'<br/><br/><input type = "submit" id = "submitSmene" value="Submit" class="btn orange">'+
			'</form></div></div></div></div>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/restaurantManagerController/uzmiRadnike',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,radnik){
				$('#radnikSifra').append('<option value = "' +radnik.id +'" >' + radnik.name + '</option>');
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
});


$(document).on('click','#submitSmene', function(e){
	e.preventDefault();
	
	var id = $('#radnikSifra').val();
	var dateStart = $('#dateStart').val();
	var dateEnd = $('#dateEnd').val();
	var smene = $('#radneSmene option:selected').val();
	if(id == ""){
		alert("Ime je prazno");
	}else if(dateStart == ""){
		alert("Pocetni datum je prazan");
	}else if(dateEnd == ""){
		alert("Datum rodjenja je prazan");
	}else if(smene ==""){
		alert("Smena je prazna");
	}else{
		var data2 = JSON.stringify({
			"worker_id" : id,
			"dateStart" : dateStart,
			"dateEnd" : dateEnd,
			"shift" : smene
		});
		$.ajax({
			type : 'POST',
			url :  '/restaurantManagerController/scheduleEmployee',
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


$(document).on('click','#dodeliReon',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
			'<form method="post" id="dodelaREONA">Dodela reona:<br/><br/>'+
			'Ime konobara:<select id="konobarSifra"></select><br/><br/>'+
			'Reon restorana:<select id="reonOznaka"></select><br/><br/>'+
			'<input type = "submit" id = "submitDodelaReona" value="Submit" class="btn orange">'+
			'</form></div></div></div></div>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/restaurantManagerController/uzmiKonobare',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,radnik){
				$('#konobarSifra').append('<option value = "' +radnik.id +'" >' + radnik.name + '</option>');
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/restaurantManagerController/uzmiReone',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(list, function(index,reon){
						$('#reonOznaka').append('<option value = "' +reon.id +'" >' + reon.name + '</option>');
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Admin ERROR: " + errorThrown);
				}	
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
});

$(document).on('click','#submitDodelaReona', function(e){
	e.preventDefault();

	var konobar = $('#konobarSifra option:selected').val();
	var reon = $('#reonOznaka option:selected').val();
	if(konobar == ""){
		alert("Izaberite konobara!");
	}else if(reon == ""){
		alert("Izaberite reon!");
	}else{
		var data2 = JSON.stringify({
			"waiter_id" : konobar,
			"reon_id" : reon
		});
		$.ajax({
			type : 'POST',
			url :  '/restaurantManagerController/assignReon',
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
