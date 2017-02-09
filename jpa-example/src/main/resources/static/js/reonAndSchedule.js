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
			url :  '/scheduleAndReonController/scheduleEmployee',
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
			url :  '/scheduleAndReonController/assignReon',
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


$(document).on('click','#konfiguracijaRasporeda',function(e){
	
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
			'<form method="post" id="registracijaMenadzera">Podaci o reonu:<br/><br/>'+
				'Oznaka reona:<input type = "text" id = "nazivReona" class="in-text"/><br/>'+
				'Lokacija reona:<input type = "text" id = "lokacijaReona" class="in-text"/><br/>'+
				'Broj stolova:<input type = "text" id = "brojStolova" class="in-text"/><br/>'+
				'<input type = "submit" id = "submitDodajKonfiguraciju" value="Submit" class="btn orange">'+
				'</form></div></div></div></div>');
});


$(document).on('click','#submitDodajKonfiguraciju',function(e){
	e.preventDefault();
	var name = $('#nazivReona').val();
	var location = $('#lokacijaReona').val();
	var number = $('#brojStolova').val();
	var restaurant = 0;
	if(name == ""){
		alert("Ime je prazno");
	}else if(location == ""){
		alert("Lokacija je prazna");
	}else if(number == ""){
		alert("Broj stolova je prazan");
	}else{
		
		var data2 = JSON.stringify({
			"name" : name,
			"location" : location,
			"numberTable" : number,
			"restaurant" : restaurant
		});
		
		$.ajax({
			type : 'POST',
			url :  '/scheduleAndReonController/addReon',
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