$(document).on('click','#definisiRaspored',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
			'<form method="post" id="registracijaSmena">Raspored radnika:<br/><br/>'+
			'Ime i sifra radnika:<select id="radnikSifra"></select><br/><br/>'+
			'Pocetni datum:<input type = "date" id = "dateStart" class="in-text"/><br/<br/><br/>'+
			'Krajnji datum:<input type = "date" id = "dateEnd" class="in-text"/><br/<br/><br/>'+
			'<input type="hidden" id="workerId">'+
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
				$('#radnikSifra').append('<option value = "' +radnik.id +'" name="'+radnik.name+'" >' + radnik.name + '</option>');
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
	var worker_name = ""
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
			"worker_name" : worker_name,
			"dateStart" : dateStart,
			"dateEnd" : dateEnd,
			"shift" : smene,
			"rest" : 0
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


$(document).on('click','#pregledRasporeda',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th>ID RADNIKA</th><th>NAZIV RADNIKA</th>'+
		'<th>POCETAK ZADUZENJA</th><th>KRAJ ZADUZENJA</th><th>SMENA</th></tr></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/scheduleAndReonController/uzmiSmene',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,smena){
				$('#tabelaPrikaz').append('<tr><td>'+smena.worker_id+'</td><td>'+smena.worker_name
						+'</td><td>'+smena.dateStart+'</td><td>'+smena.dateEnd+'</td><td>'+smena.shift+'</td>'+
						'<td><form id="formIzbrisiSmenu" method="get" action=""><input type="submit" id="submitIzbrisiSmenu" value="Izbrisi"><input type="hidden" value='
							+smena.id+'></form></td><td><form id="formIzmeniSmenu" method="get" action="">'+
							'<input type="submit" value="Izmeni">' +
							'<input type="hidden" id="idIzmenaId" value='+smena.id+'>'+
							'<input type="hidden" id="idIzmenaWorker" value='+smena.worker_id+'>'+
							'<input type="hidden" id="idIzmenaWorkerName" value='+smena.worker_name+'>'+
							'<input type="hidden" id="idIzmenaStart" value='+smena.dateStart+'>'+
							'<input type="hidden" id="idIzmenaEnd" value='+smena.dateEnd+'>'+
							'<input type="hidden" id="idIzmenaShift" value='+smena.shift+'>'+
							'</form></td></tr>');
				
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});


$(document).on('submit','#formIzbrisiSmenu',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[type=hidden]").val();
	var worker_name = "";
	var dateStart = "";
	var dateEnd = "";
	var smene = "";
	
	var data2 = JSON.stringify({
		"id" : id,
		"worker_id" : 0,
		"worker_name" : worker_name,
		"dateStart" : dateStart,
		"dateEnd" : dateEnd,
		"shift" : smene,
		"rest" : 0
	});
	
	$.ajax({
		type : 'POST',
		url :  '/scheduleAndReonController/izbrisiSmene',
		contentType : 'application/json',
		data : data2,
		success : function(){
			window.location.reload();
			
			alert('Uspesno brisanje pica!');
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});


$(document).on('submit','#formIzmeniSmenu',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='idIzmenaId']").val();
	var worker = $(this).find("input[id='idIzmenaWorker']").val();
	var worker_name = $(this).find("input[id='idIzmenaWorkerName']").val();
	var start = $(this).find("input[id='idIzmenaStart']").val();
	var end = $(this).find("input[id='idIzmenaEnd']").val();
	var shift = $(this).find("input[id='idIzmenaShift']").val();
	
	
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
			'<form method="post" id="registracijaSmena">Raspored radnika:<br/><br/>'+
			'Ime i sifra radnika:<select id="radnikSifra"></select><br/><br/>'+
			'Pocetni datum:<input type = "date" id = "dateStart" class="in-text"/><br/<br/><br/>'+
			'Krajnji datum:<input type = "date" id = "dateEnd" class="in-text"/><br/<br/><br/>'+
			'Smene:<select id="radneSmene"><option value="prva">prva</option>'+
			'<option value="druga">druga</option><option value="treca">treca</option></select>'+
			'<input type="hidden" id="idSched" value='+id+'>'+
			'<br/><br/><input type = "submit" id = "submitSmeneIzmena" value="Submit" class="btn orange">'+
			'</form></div></div></div></div>');

	$('#radnikSifra').append('<option value = "' +worker +'" name="'+worker_name
			+'" >' + worker_name + '</option>');
	$('#dateStart').val(start);
	$('#dateEnd').val(end);
	$('#radneSmene option:selected').val(shift);
});



$(document).on('click','#submitSmeneIzmena', function(e){
	e.preventDefault();
	var id = $('#idSched').val();
	var worker_id = $('#radnikSifra').val();
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
			"id" : id,
			"worker_id" : worker_id,
			"worker_name" : "",
			"dateStart" : dateStart,
			"dateEnd" : dateEnd,
			"shift" : smene,
			"rest" : 0
		});
		$.ajax({
			type : 'POST',
			url :  '/scheduleAndReonController/updateScheduleEmployee',
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
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
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
				$('#konobarSifra').append('<option value = "' +radnik.id +'" name="'+
						radnik.name+'">' + radnik.name + '</option>');
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/restaurantManagerController/uzmiReone',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(list, function(index,reon){
						$('#reonOznaka').append('<option value = "' +reon.id +'" name="'+
								reon.name+'">' + reon.name + '</option>');
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
	var konobar_name = ""
	var reon = $('#reonOznaka option:selected').val();
	var reon_name = "";
	if(konobar == ""){
		alert("Izaberite konobara!");
	}else if(reon == ""){
		alert("Izaberite reon!");
	}else{
		var waiter = JSON.stringify({
			"id" : konobar,
			"name" : "",
			"surname" : "",
			"dateBirth" : "",
			"type" : "",
			"confNumber" : "",
			"shoeNumber" : "",
			"restaurant" : "",
			"email" : "",
			"accept" : "",
			"password" : ""
		});

		var obj1 = JSON.parse(waiter);
		
		var reon = JSON.stringify({
			"id" : reon,
			"name" : "",
			"location" : "",
			"numberTable" : 0,
			"restaurant" : null
		});
		var obj2 = JSON.parse(reon);
		var data2 = JSON.stringify({
			"waiter" : obj1,
			"reon" : obj2,
			"restaurant" : null,
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




$(document).on('click','#pregledDodeleReon',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th>ID KONOBARA</th><th>REON KONOBARA</th>'+
		'<th>DODELJENI REON</th></tr></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/scheduleAndReonController/uzmiDodelu',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,reon){
				$('#tabelaPrikaz').append('<tr><td>'+reon.waiter.id+'</td><td>'+reon.waiter.name
						+'</td><td>'+reon.reon.id+'</td><td>'+reon.reon.name+'</td>'+
						'<td><form id="formIzbrisiDodelu" method="get" action=""><input type="submit" id="submitIzbrisiKonfig" value="Izbrisi"><input type="hidden" value='
							+reon.id+'></form></td><td>');
				
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});




$(document).on('submit','#formIzbrisiDodelu',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[type=hidden]").val();
	
	var data2 = JSON.stringify({
		"id" : id,
		"waiter" : null,
		"reon" : null
	});
	
	$.ajax({
		type : 'POST',
		url :  '/scheduleAndReonController/izbrisiDodelu',
		contentType : 'application/json',
		data : data2,
		success : function(){
			window.location.reload();
			
			alert('Uspesno brisanje dodele reona!');
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});

$(document).on('click','#konfiguracijaRasporeda',function(e){
	
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
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


$(document).on('click','#pregledKonfiguracijeRasporeda',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th>ID REONA</th><th>NAZIV REONA</th>'+
		'<th>LOKACIJA REONA</th><th>BROJ STOLOVA</th></tr></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/scheduleAndReonController/uzmiReone',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,reon){
				$('#tabelaPrikaz').append('<tr><td>'+reon.id+'</td><td>'+reon.name
						+'</td><td>'+reon.location+'</td><td>'+reon.numberTable+'</td>'+
						'<td><form id="formIzbrisiKonfig" method="get" action=""><input type="submit" id="submitIzbrisiKonfig" value="Izbrisi"><input type="hidden" value='
							+reon.id+'></form></td><td><form id="formIzmeniReone" method="get" action="">'+
							'<input type="submit" value="Izmeni">' +
							'<input type="hidden" id="reonId" value='+reon.id+'>'+
							'<input type="hidden" id="reonName" value='+reon.name+'>'+
							'<input type="hidden" id="reonLocation" value='+reon.location+'>'+
							'<input type="hidden" id="reonNumberTable" value='+reon.numberTable+'>'+
							'</form></td><td>');
				
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});




$(document).on('submit','#formIzbrisiKonfig',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[type=hidden]").val();
	
	var data2 = JSON.stringify({
		"id" : id,
		"name" : 0,
		"location" : "",
		"numberTable" : 0,
		"restaurant" : 0
	});
	
	$.ajax({
		type : 'POST',
		url :  '/scheduleAndReonController/izbrisiReone',
		contentType : 'application/json',
		data : data2,
		success : function(){
			window.location.reload();
			
			alert('Uspesno brisanje dodele reona!');
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});


$(document).on('submit','#formIzmeniReone',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='reonId']").val();
	var name = $(this).find("input[id='reonName']").val();
	var location = $(this).find("input[id='reonLocation']").val();
	var numberTable = $(this).find("input[id='reonNumberTable']").val();
	var restaurant = "";
	

	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
			'<form method="post" id="izmenaaReona">Podaci o reonu:<br/><br/>'+
				'Oznaka reona:<input type = "text" id = "nazivReona" class="in-text"/><br/>'+
				'Lokacija reona:<input type = "text" id = "lokacijaReona" class="in-text"/><br/>'+
				'Broj stolova:<input type = "text" id = "brojStolova" class="in-text"/><br/>'+
				'<input type="hidden" id="reonId" value='+id+'>'+
				'<input type = "submit" id = "submitIzmeniKonfiguraciju" value="Submit" class="btn orange">'+
				'</form></div></div></div></div>');

	$('#nazivReona').val(name);
	$('#lokacijaReona').val(location);
	$('#brojStolova').val(numberTable);
});



$(document).on('click','#submitIzmeniKonfiguraciju', function(e){
	e.preventDefault();
	var id = $('#reonId').val();
	var name = $('#nazivReona').val();
	var location = $('#lokacijaReona').val();
	var number = $('#brojStolova').val();
	
	if(name == ""){
		alert("Ime je prazno");
	}else if(location == ""){
		alert("Lokacija je prazna");
	}else if(number == ""){
		alert("Broj stolova je prazan");
	}else{
		
		var data2 = JSON.stringify({
			"id" : id,
			"name" : name,
			"location" : location,
			"numberTable" : number,
			"restaurant" : 0
		});
		
		$.ajax({
			type : 'POST',
			url :  '/scheduleAndReonController/updateReon',
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


