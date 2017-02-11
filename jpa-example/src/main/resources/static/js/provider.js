$(document).ready(function(){
	
	$('#content').empty();
	$('#start').empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/providerController/uzmiPonudjaca',
		success : function(ponudjac){
				if(ponudjac.logFirstTime=="true"){
					
					$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
							'<div class="login-page wrapper centered centered-block">'+ 
							'<div class = "form-group"><form id="submitIzmeniLozinku" method="post">'+
							'Postavite lozinku:<br/><br/>'+
							'Lozinka:<input type = "text" id = "novaLozinka"  class="in-text"/><br/>'+
							'Ponovite lozinku:<input type = "text" id = "ponovljenaLozinka"  class="in-text"/><br/>'+
							'<input type = "submit" value="Submit" class="btn orange">'+
							'<input type="hidden" id="providerId" value='+ponudjac.id+'>'+
							'<input type="hidden" id="providerName" value='+ponudjac.name+'>'+
							'<input type="hidden" id="providerSurname" value='+ponudjac.surname+'>'+
							'<input type="hidden" id="providerAddress" value='+ponudjac.address+'>'+
							'<input type="hidden" id="providerContact" value='+ponudjac.contact+'>'+
							'</form></div></div></div></div>');
				}
				else
					$('#start').append('<ul><li>'+
							'<li class="dropdown"><a class="dropbtn">Profil</a>'+
							'<div class="dropdown-content">'+	
							'<a id="ponudjacIzmenaPodataka">Izmeni osnovne podatke</a>'+
							'<a id="ponudjacIzmenaLozinke">Promeni sifru</a></div></li>'+
							'<li class="dropdown"><a class="dropbtn">Restoran</a>'+
							'<div class="dropdown-content">'+	
							'<a id="aktivnePonude">Aktivne ponude</a>'+
							'<a id="spisakPonuda">Spisak ponuda</a></div></li>'+
							'<li><a id="dugmeOdjava">Odjavi se</a></li></ul>');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});


$(document).on('submit','#submitIzmeniLozinku',function(e){
	e.preventDefault();
	var id = $(this).find("input[id='providerId']").val();
	var password = $('#novaLozinka').val();
	var checkPassword = $('#ponovljenaLozinka').val();
	var name = $(this).find("input[id='providerName']").val();
	var surname = $(this).find("input[id='providerSurname']").val();
	var contact = $(this).find("input[id='providerContact']").val();
	var address = $(this).find("input[id='providerAddress']").val();
	if(password == ""){
		alert("Morate uneti lozinku!");
	}else if(checkPassword == ""){
		alert("Morate ponoviti lozinku!");
	}else{
		if(password!=checkPassword){
			alert("Niste potvrdili lozinku!");
			window.location.reload();
		}
		
		var data2 = JSON.stringify({
			"id" : id,
			"email" : "",
			"password" : password,
			"role" : "provider",
			"accept" : "true",
			"name" : name,
			"surname" : surname,
			"address" : address,
			"contact" : contact,
			"restaurant" : "",
			"logFirstTime" : "false"
		});
		
		$.ajax({
			type : 'POST',
			url :  '/providerController/updateUserPassword',
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



$(document).on('click','#ponudjacIzmenaLozinke',function(e){
	e.preventDefault();

	$('#content').empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/providerController/uzmiPonudjaca',
		success : function(ponudjac){
					
			$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
					'<div class="login-page wrapper centered centered-block">'+ 
					'<div class = "form-group"><form id="submitIzmeniLozinku" method="post">'+
					'Postavite lozinku:<br/><br/>'+
					'Lozinka:<input type = "text" id = "novaLozinka"  class="in-text"/><br/>'+
					'Ponovite lozinku:<input type = "text" id = "ponovljenaLozinka"  class="in-text"/><br/>'+
					'<input type = "submit" value="Submit" class="btn orange">'+
					'<input type="hidden" id="providerId" value='+ponudjac.id+'>'+
					'<input type="hidden" id="providerName" value='+ponudjac.name+'>'+
					'<input type="hidden" id="providerSurname" value='+ponudjac.surname+'>'+
					'<input type="hidden" id="providerAddress" value='+ponudjac.address+'>'+
					'<input type="hidden" id="providerContact" value='+ponudjac.contact+'>'+
					'</form></div></div></div></div>');
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});


$(document).on('click','#ponudjacIzmenaPodataka',function(e){
	e.preventDefault();

	$('#content').empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/providerController/uzmiPonudjaca',
		success : function(ponudjac){
			
			$('#content').append('<div id="wraper"><div class="centered-content-wrap">'+
					'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
					'<form method="post" id="izmenaPonudjaca">'+
					'Podaci o menadzeru:<br/><br/>'+
					'Ime:<input type = "text" id = "imePonudjaca" class="in-text"/><br/>'+
					'Prezime:<input type = "text" id = "prezimePonudjaca" class="in-text"/><br/>'+
					'Adresa:<input type = "text" id = "adresaPonudjaca" class="in-text"/><br/>'+
					'Email:<input type = "text" id = "emailPonudjaca" class="in-text" readonly="true"/><br/>'+
					'Kontakt:<input type = "text" id = "kontaktPonudjaca" class="in-text"/><br/>'+
					'<input type="hidden" id="providerPassword" value='+ponudjac.password+'>'+
					'<input type="hidden" id="providerId" value='+ponudjac.id+'>'+
					'<input type = "submit" id = "submit" value="Submit" class="btn orange">'+
					'</form></div></div></div>');
	
	
			$('#imePonudjaca').val(ponudjac.name);
			$('#prezimePonudjaca').val(ponudjac.surname);
			$('#adresaPonudjaca').val(ponudjac.address);
			$('#kontaktPonudjaca').val(ponudjac.contact);
			$('#emailPonudjaca').val(ponudjac.email);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});


$(document).on('submit','#izmenaPonudjaca',function(e){
	e.preventDefault();
	var id = $(this).find("input[id='providerId']").val();
	var password = $(this).find("input[id='providerPassword']").val();
	var name = $('#imePonudjaca').val();
	var surname = $('#prezimePonudjaca').val();
	var contact = $('#kontaktPonudjaca').val();
	var address = $('#adresaPonudjaca').val();
	if(name == ""){
		alert("Morate uneti ime!");
	}else if(surname == ""){
		alert("Morate prezime!");
	}else{
		
		var data2 = JSON.stringify({
			"id" : id,
			"email" : "",
			"password" : password,
			"role" : "provider",
			"accept" : "true",
			"name" : name,
			"surname" : surname,
			"address" : address,
			"contact" : contact,
			"restaurant" : "",
			"logFirstTime" : "false"
		});
		
		$.ajax({
			type : 'POST',
			url :  '/providerController/updateUserPassword',
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


$(document).on('click','#aktivnePonude',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th>ID</th><th>RESTORAN</th><th></th>/tr></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/providerController/uzmiSvePonude',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,ponuda){
				$('#tabelaPrikaz').append('<tr><td>'+ponuda.endDate+'</td><td>'+ponuda.restaurant+'</td>'+
						'<td><form id="formVidiPonudu" method="get" action="">'+
							'<input type="submit" value="Vidi ponudu/porudzbinu">' +
							'<input type="hidden" id="idIzmenaId" value='+ponuda.id+'></form></td></tr>');
				
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});


$(document).on('submit','#formVidiPonudu',function(e){
	e.preventDefault();
	var id = $(this).find("input[type=hidden]").val();
	$('#content').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block">'+
		'<div class = "form-group"><form method="post" id="submitDodajPonudu">'+
		'Podaci o ponudi:<br/><br/>'+
		'Datum zavrsetka ponude:<input type = "date" id = "krajPonude" class="in-text"/ readonly="true"><br/>'+
		'Trazena jela:<ul class="cao" id="spisakJela"></ul><br/>'+
		'Trazena pica:<ul class="cao" id="spisakPica"></ul><br/><br/>'+
			'<input type = "submit" id = "submit" value="Submit" class="btn orange">'+
			'</form></div></div></div></div>');
	
	var data2 = JSON.stringify({
		"id" : id,
		"endDate" : "",
		"meals" : [],
		"drinks" : [],
		"restaurant" : 0
	});
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){
				$('#spisakPica').append('<li class="caocao"><input type="checkbox" value="pice_'+pice.id+'k" id="'+
						pice.id+'">'+pice.name+'</li>');
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/mealAndDrinkController/uzmiObroke',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(list, function(index,jela){
						$('#spisakJela').append('<li class="caocao"><input type="checkbox" value="jelo_'+jela.id+'k" id="'+
								jela.id+'">'+jela.name+'</li>');
					});
					
					$.ajax({
						type: 'GET',
						contentType : 'application/json',
						dataType : 'json',
						data : data2,
						url : '/providerController/uzmiPonudu',
						success : function(ponuda){
							$('#krajPonude').val(ponuda.endDate);
							
							for(var i=0;i<ponuda.drinks.length;i++) {
								document.getElementById(ponuda.drinks[i]).checked = true;
								
							}
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
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
	
	
	
});
