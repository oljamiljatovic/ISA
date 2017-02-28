$(document).on('click','#restoran',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form method="post" id="registracijaRestorana">'+
		'Podaci o restoranu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imeRestorana" class="in-text"/><br/>'+
		'Vrsta restorana:<input type = "text" id = "vrstaRestorana" class="in-text"/><br/>'+
		'Adresa:<input type = "text" id = "adresaRestorana" class="in-text"/><br/>'+
		'Kontakt:<input type = "text" id = "kontaktRestorana" class="in-text"/><br/>'+
		'Geografska sirina:<input type = "text" id = "sirinaRestorana" class="in-text"/><br/>'+
		'Geofrafska duzina:<input type = "text" id = "duzinaRestorana" class="in-text"/><br/>'+
		'<input type = "button" id = "lokacijaRestorana" value="izaberi lokacija" >'+
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
			$('#sirinaRestorana').val(data.width);
			$('#duzinaRestorana').val(data.height);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});


$(document).on('click','#lokacijaRestorana',function(e){
	e.preventDefault();
	
	$('#content').empty();	
	$('#ubaci_mapu').empty();
	
	$.ajax({
		type : 'GET',
		url :  '/restaurantManagerController/uzmiRestoranMenadzera',
		contentType : 'application/json',
		success : function(data){


			$('#content').append('<div id="mica_mapa"></div>');
			var dok = document.getElementById('mica_mapa');
			//var uluru = {lat: 45.239630, lng: 19.840992};
			var uluru = {lat: data.width, lng: data.height};
			var map = new google.maps.Map(document.getElementById('mica_mapa'), {
		        center: uluru,
		        zoom: 8
		      });
			
			var marker = new google.maps.Marker({
		        position: uluru,
		        map: map
		      });
			
		    $('#ubaci_mapu').append(map);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { 
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
	var sirina = $('#sirinaRestorana').val();
	var duzina = $('#duzinaRestorana').val();
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
			"contact" : contact,
			"width" : sirina,
			"height" : duzina
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


$(document).on('click','#izmeniPodatke',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap">'+
			'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
			'<form method="post" id="izmenaMenadzera">'+
				'Podaci o menadzeru:<br/><br/>'+
				'Ime:<input type = "text" id = "imeMenadzera" class="in-text"/><br/>'+
				'Prezime:<input type = "text" id = "prezimeMenadzera" class="in-text"/><br/>'+
				'Adresa:<input type = "text" id = "adresaMenadzera" class="in-text"/><br/>'+
				'Email:<input type = "text" id = "emailMenadzera" class="in-text"/><br/>'+
				'Kontakt:<input type = "text" id = "kontaktMenadzera" class="in-text"/><br/>'+
				'Lozinka:<input type = "text" id = "lozinkaMenadzera" class="in-text"/><br/>'+
				'<input type = "submit" id = "submit" value="Submit" class="btn orange">'+
				'</form></div></div></div>');
	
	$.ajax({
		type : 'GET',
		url :  '/restaurantManagerController/uzmiMenadzera',
		contentType : 'application/json',
		success : function(data){
			
			$('#imeMenadzera').val(data.name);
			$('#prezimeMenadzera').val(data.surname);
			$('#adresaMenadzera').val(data.address);
			$('#emailMenadzera').val(data.email);
			$('#kontaktMenadzera').val(data.contact);
			$('#lozinkaMenadzera').val(data.password);
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("Da li je ovdje problem");
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});

$(document).on('submit','#izmenaMenadzera',function(e){
	e.preventDefault();
	
	var name = $('#imeMenadzera').val();
	var surname = $('#prezimeMenadzera').val();
	var address = $('#adresaMenadzera').val();
	var contact = $('#kontaktMenadzera').val();
	var email = $('#emailMenadzera').val();
	var password = $('#lozinkaMenadzera').val();
	
	if(name == ""){
		alert("Ime je prazno");
	}else if(address == ""){
		alert("Adresa je prazna");
	}else if(contact == ""){
		alert("Kontakt je prazno");
	}else{
		
		var dataa = JSON.stringify({
			"name" : name,
			"surname" : surname,
			"address" : address,
			"email" : email,
			"contact" : contact,
			"password" : password,
			"restaurant" : null,
			"role" : "restaurantManager",
			"accept" : "true"
		});
		
		$.ajax({
			type : 'POST',
			url :  '/restaurantManagerController/updateRestaurantManager',
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

$(document).on('click','#dodajRadnika',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
			'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
				'<form method="post" id="registracijaMenadzera">'+
					'Podaci o radniku:<br/><br/>Vrsta:'+
					'<select id="vrstaRadnika"><option value="saladCook">salad - cook</option>'+
					'<option value="cook">cooked meals  - cook</option><option value="grilledCook">grilled dish - cook</option>'+
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
			"restaurant" : null,
			"email" : email,
			"accept" : accept,
			"password" : password,
			"firstLog" : "true"
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


$(document).on('click','#dodajPonudjaca',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap">'+
			'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
				'<form method="post" id="registracijaPonudjaca">'+
					'Podaci o ponudjacu:<br/><br/>'+
					'Ime:<input type = "text" id = "imePonudjaca" class="in-text"/><br/>'+
					'Prezime:<input type = "text" id = "prezimePonudjaca" class="in-text"/><br/>'+
					'Adresa:<input type = "text" id = "adresaPonudjaca" class="in-text"/><br/>'+
					'Email:<input type = "text" id = "emailPonudjaca" class="in-text"/><br/>'+
					'Kontakt:<input type = "text" id = "kontaktPonudjaca" class="in-text"/><br/>'+
					'Lozinka:<input type = "password" id = "lozinkaPonudjaca" class="in-text"/><br/>'+
					'<input type = "submit" id = "submitNewProvider" value="Submit" class="btn orange">'+
					'</form></div></div></div>');
});


$(document).on('click','#submitNewProvider',function(e){
	e.preventDefault();
	var name = $('#imePonudjaca').val();
	var surname = $('#prezimePonudjaca').val();
	var address = $('#adresaPonudjaca').val();
	var email = $('#emailPonudjaca').val();
	var contact = $('#kontaktPonudjaca').val();
	var password = $('#lozinkaPonudjaca').val();
	var restaurant = "";
	var logFirstTime = "true";
	var dataa = JSON.stringify({
		"name" : name,
		"surname" : surname,
		"address" : address,
		"email" : email,
		"contact" : contact,
		"password" : password,
		"restaurant" : null,
		"role" : "provider",
		"accept" : "true",
		"logFirstTime" : logFirstTime
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
			url :  '/restaurantManagerController/addProvider',
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

$(document).on('click','#dodajPonudu',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block">'+
		'<div class = "form-group"><form method="post" id="submitDodajPonudu">'+
		'Podaci o ponudi:<br/><br/>'+
		'Datum zavrsetka ponude:<input type = "date" id = "krajPonude" class="in-text"/><br/>'+
		'Izaberi namirnicu ili pice:<select id="foodAndDrink"> </select><br/><br/><br/>'+
		'Potrebna kolicina:<input type = "text" id = "kolicina" class="in-text"/><br/>'+
			'<input type = "submit" id = "submit" value="Submit" class="btn orange">'+
			'</form></div></div></div></div>');
	
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){

				$('#foodAndDrink').append('<option value = "pice_' +pice.id
						+'" >' + pice.name + '</option>');
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiNamirnice',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,jela){
				
				$('#foodAndDrink').append('<option value = "jelo_' +jela.id
						+'" >' + jela.name + '</option>');
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
});


$(document).on('submit','#submitDodajPonudu',function(e){
	e.preventDefault();

	var parts = $('#foodAndDrink option:selected').val();
	var flag = parts.split('_')[0];
	var foodAndDrink = parts.split('_')[1];
	var restaurant = "";
	var amount = $('#kolicina').val();
	var datum = $('#krajPonude').val();
	var data2 = JSON.stringify({
		"endDate" : datum,
		"foodOrDrink" : foodAndDrink,
		"flag" : flag,
		"restaurant" : null,
		"amount" : amount,
		"accepted" : false
	});
	$.ajax({
		type : 'POST',
		url :  '/mealAndDrinkController/addOffer',
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
});


$(document).on('click','#aktuelnePonude',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th>NAMIRNICA/PICE</th><th>FLAG</th><th>KOLICINA</th>'+
			'<th>KRAJNJI ROK</th></tr></table>');
	
	$.ajax({
		type: 'GET',
		contentType : 'application/json',
		url : '/providerController/uzmiSveAktuelnePonudeRestorana',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,ponuda){
				//var date1 = new Date();
				//var date2 = new Date(ponuda.endDate);
				if( ponuda.accepted==false)
			    {
				$('#tabelaPrikaz').append('<tr><td>'+ponuda.foodOrDrink+'</td><td>'+ponuda.flag+'</td><td>'
						+ponuda.amount+'</td><td>'+ponuda.endDate+'</td><td><form id="formVidiPonude" method="get" action="">'+
							'<input type="submit" value="Vidi sve ponude">' +
							'<input type="hidden" id="ponudaId" value='+ponuda.id+'>'+
							'</form></td></tr>');
			    }
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});



$(document).on('submit','#formVidiPonude',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th>CENA DOSTAVE</th><th>VREME DOSTAVE</th><th>ID PONUDJACA</th>/tr></table>');
	var id = $(this).find("input[type=hidden]").val();
	var data = JSON.stringify({
		"id" : id,
		"endDate" : "",
		"foodOrDrink" : 0,
		"flag" : "",
		"restaurant" : null,
		"amount" : 0
	});
	
	$.ajax({
		type: 'POST',
		contentType : 'application/json',
		dataType : 'json',
		data : data,
		url : '/providerController/uzmiSvePorudzbeniceAktuelnePonude',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,porudz){

				$('#tabelaPrikaz').append('<tr><td>'+porudz.price+'</td><td>'+porudz.timeDeliver+'</td><td>'+porudz.provider.name+'</td>'+
						'<td><form id="formPrihvatiPonudu" method="get" action="">'+
							'<input type="submit" value="Prihvati ponudu">' +
							'<input type="hidden" id="porudzId" value='+porudz.id+'>'+
							'<input type="hidden" id="porudzTime" value='+porudz.timeDeliver+'>'+
							'<input type="hidden" id="porudzPrice" value='+porudz.price+'>'+
							'<input type="hidden" id="porudzFlag" value='+porudz.flag+'>'+
							'<input type="hidden" id="porudzOffer" value='+porudz.offer.id+'>'+
							'<input type="hidden" id="porudzRestaurant" value='+porudz.restaurant.id+'>'+
							'<input type="hidden" id="porudzProvider" value='+porudz.provider.id+'>'+
							'</form></td></tr>');
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});


$(document).on('submit','#formPrihvatiPonudu',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='porudzId']").val();
	var flag = $(this).find("input[id='porudzProvider']").val();
	var timeDeliver = $(this).find("input[id='porudzTime']").val();
	var price = $(this).find("input[id='porudzPrice']").val();
	var offer = $(this).find("input[id='porudzOffer']").val();
	var restaurant= $(this).find("input[id='porudzRestaurant']").val();
	
	var data = JSON.stringify({
		"id" : offer,
		"endDate" : "",
		"foodOrDrink" : "",
		"flag" : "",
		"restaurant" : null,
		"amount" : 0,
		"accepted" : true
	});
	
	var obj = JSON.parse(data);
	var data2 = JSON.stringify({
		"id" : id,
		"offer" : obj,
		"restaurant" : null,
		"timeDeliver" : timeDeliver,
		"price" : price,
		"flag" : flag
	});
		$.ajax({
			type : 'POST',
			url :  '/providerController/izmeniPorudzbenicu',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
			success : function(data){
				alert(data.id);
				
				$.ajax({
					type : 'POST',
					url :  '/acceptOffer',
					data :  {"acceptOffer" : id},
					error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
						alert("AJAX ERROR: " + errorThrown);
					}
				});
				
				window.location.reload();
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		});
});


$(document).on('click', '#dugmeOdjava', function(e) {
	e.preventDefault();
	$("#content").empty();
	$.ajax({
		type: 'GET',
		dataType: 'text',
		url : '/userController/logout',
		success : function(data){
			window.location.href= "index.html";
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("billOrders: " + errorThrown);
		}	
	});
});