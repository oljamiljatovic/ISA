function message(){
	toastr.options = {
			  "closeButton": false,
			  "debug": false,
			  "newestOnTop": false,
			  "progressBar": false,
			  "positionClass": "toast-top-right",
			  "preventDuplicates": false,
			  "onclick": null,
			  "showDuration": "100",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
}

$(document).ready(function(){
	var messageList = $("#messages");
	var socket = new SockJS('/stomp');
	var stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		stompClient.subscribe("/topic/acceptOffer", function(data) {

			var mess = data.body;
			var order = mess.split(';')[0];
			var provider = mess.split(';')[1];
			var offer = mess.split(';')[2];
			var seen = mess.split(';')[3];
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/providerController/uzmiPonudjaca',
				success : function(ponudjac){
					
					if(ponudjac.id==provider ){
						Command: toastr["info"]("Prihvacena je vasa ponuda!", "Info!")
						message();
					
					}else{
						Command: toastr["error"]("Odbijena je vasa ponuda za restoran!", "Info!")
						message();
					}
					
					var data = JSON.stringify({
						"id" : order,
						"offer" : null,
						"restaurant" : null,
						"timeDeliver" : "",
						"price" : 0,
						"provider" : null,
						"flag" : 0
					});
					
					$.ajax({
						type: 'POST',
						contentType : 'application/json',
						dataType : 'json',
						data : data,
						url : '/providerController/updateSeen',
						success : function(ponudjac){
							toastr.info("uspesno!");
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							toastr.error("Admin ERROR: " + errorThrown);
						}
					});
			
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					toastr.error("Admin ERROR: " + errorThrown);
				}
			});
			
		});
		
	});
	
	
	
	$('#content').empty();
	$('#start').empty();
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/providerController/uzmiPonudjaca',
		success : function(ponudjac){
			
			$.ajax({
				type: 'GET',
				contentType : 'application/json',
				url : '/providerController/uzmiSvePorudzbenicePonudjaca',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(list, function(index,ponuda){
						if(ponuda.flag!=0 && ponuda.seen==false){
							
							if(ponudjac.id==ponuda.flag ){
								Command: toastr["info"]("Prihvacena je vasa ponuda!", "Info!")
								message();
							
							}else{
								Command: toastr["error"]("Odbijena je vasa ponuda za restoran!", "Info!")
								message();
							}
							
							var data = JSON.stringify({
								"id" : ponuda.id,
								"offer" : null,
								"restaurant" : null,
								"timeDeliver" : "",
								"price" : 0,
								"provider" : null,
								"flag" : 0
							});
							
							$.ajax({
								type: 'POST',
								contentType : 'application/json',
								dataType : 'json',
								data : data,
								url : '/providerController/updateSeen',
								success : function(ponudjac){
									toastr.info("uspesno!");
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									toastr.error("Admin ERROR: " + errorThrown);
								}
							});
						}
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					toastr.error("Admin ERROR: " + errorThrown);
				}	
			});
			
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
		toastr.error("Morate uneti lozinku!");
	}else if(checkPassword == ""){
		toastr.error("Morate ponoviti lozinku!");
	}else{
		if(password!=checkPassword){
			toastr.error("Niste potvrdili lozinku!");
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
			"restaurant" : null,
			"logFirstTime" : "false"
		});
		
		$.ajax({
			type : 'POST',
			url :  '/providerController/updateUserPassword',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
			success : function(data){
				window.location.reload();
				toastr.info("uspesno!");
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { 
				toastr.error("AJAX ERROR: " + errorThrown);
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
			toastr.error("AJAX ERROR: " + errorThrown);
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
			toastr.error("AJAX ERROR: " + errorThrown);
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
		toastr.error("Morate uneti ime!");
	}else if(surname == ""){
		toastr.error("Morate uneti prezime!");
	}else if(contact == ""){
		toastr.error("Morate uneti kontakt!");
	}else if(address == ""){
		toastr.error("Morate uneti adresu!");
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
			"restaurant" : null,
			"logFirstTime" : "false"
		});
		
		$.ajax({
			type : 'POST',
			url :  '/providerController/updateUserPassword',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
			success : function(data){
				window.location.reload();
				toastr.info('Promenjena lozinka');
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				toastr.error("AJAX ERROR: " + errorThrown);
			}
		});
	}
});


$(document).on('click','#aktivnePonude',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th>ID NAMIRNICA/PICE</th><th>FLAG</th><th>KOLICINA</th>'+
			'<th>KRAJNI DATUM</th><th>RESTORAN</th><th></th></tr></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/providerController/uzmiSvePonude',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,ponuda){
				//var date1 = new Date();
				//var date2 = new Date(ponuda.endDate);
				if(ponuda.accepted==false)
			    {
			   
				$('#tabelaPrikaz').append('<tr><td>'+ponuda.foodOrDrink+'</td><td>'+ponuda.flag+'</td>'+
						+ponuda.amount+'</td><td>'+ponuda.endDate+'</td><td>'+ponuda.restaurant.name+'</td>'+
						'<td><form id="formVidiPonudu" method="get" action="">'+
							'<input type="submit" value="Vidi ponudu/porudzbinu">' +
							'<input type="hidden" id="idIzmenaId" value='+ponuda.id+'></form></td></tr>');
			    }
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			toastr.error("Admin ERROR: " + errorThrown);
		}	
	});
	
});


$(document).on('submit','#formVidiPonudu',function(e){
	e.preventDefault();
	var id = $(this).find("input[type=hidden]").val();
	$('#content').empty();
	
	var data2 = JSON.stringify({
		"id" : id,
		"endDate" : "",
		"foodOrDrink" : "",
		"flag" : "",
		"restaurant" : null,
		"amount" : 0,
		"accepted" : false
	});
	
	var obj = JSON.parse(data2);
	var data = JSON.stringify({
		"offer" : obj,
		"restaurant" : null,
		"timeDeliver" : "",
		"price" : 0,
		"provider" : null,
		"flag" : 0
	});
	
	$.ajax({
		type: 'POST',
		contentType : 'application/json',
		dataType : 'json',
		data : data,
		url : '/providerController/uzmiPorudzbenicu',
		success : function(ponuda){
			
			if(ponuda==null)
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
		'<div class="login-page wrapper centered centered-block">'+
		'<div class = "form-group"><form method="post" id="submitDodajPorudzbenicu">'+
		'Podaci o ponudi:<br/><br/>'+
		'Namirnica/pice: <input type = "text" id = "foodDrinkPonude" class="in-text"/ readonly="true"><br/>'+
		'Flag pica/namirnice: <input type = "text" id = "flagPonude" class="in-text"/ readonly="true"><br/>'+
		'Datum zavrsetka ponude:<input type = "date" id = "krajPonude" class="in-text"/ readonly="true"><br/>'+
		'Potrebna kolicina:<input type = "text" id = "kolicinaPonude" class="in-text"/ readonly="true"><br/>'+
		
		'------------------------------------------------------------------------------------:<br/><br/>'+

		'Cena porudzbine:<input type = "text" id = "cenaPorudzbenice" class="in-text"/><br/>'+
		'Broj dana dostave:<input type = "text" id = "daniPorudzbenice" class="in-text"/><br/>'+
			'<input type = "submit" id = "submit" value="Dodaj porudzbenicu" class="btn orange">'+
			'<input type="hidden" id="porudzbenicaId"><input type="hidden" id="porudzbenicaOffer">' +
			'<input type="hidden" id="porudzbenicaRestoran">'+
			'</form></div></div></div></div>');
			
			else{
				$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
						'<div class="login-page wrapper centered centered-block">'+
						'<div class = "form-group"><form method="post" id="submitIzmeniPorudzbenicu">'+
						'Podaci o ponudi:<br/><br/>'+
						'Namirnica/pice: <input type = "text" id = "foodDrinkPonude" class="in-text"/ readonly="true"><br/>'+
						'Flag pica/namirnice: <input type = "text" id = "flagPonude" class="in-text"/ readonly="true"><br/>'+
						'Datum zavrsetka ponude:<input type = "date" id = "krajPonude" class="in-text"/ readonly="true"><br/>'+
						'Potrebna kolicina:<input type = "text" id = "kolicinaPonude" class="in-text"/ readonly="true"><br/>'+
						'------------------------------------------------------------------------------------:<br/><br/>'+

						'Cena porudzbine:<input type = "text" id = "cenaPorudzbenice" class="in-text"/><br/>'+
						'Broj dana dostave:<input type = "text" id = "daniPorudzbenice" class="in-text"/><br/>'+
							'<input type = "submit" id = "submit" value="Izmeni porudzbenicu" class="btn orange">'+
							'<input type="hidden" id="porudzbenicaId"><input type="hidden" id="porudzbenicaOffer">' +
							'<input type="hidden" id="porudzbenicaRestoran">'+
							'</form></div></div></div></div>');
				
				$('#porudzbenicaId').val(ponuda.id);
				$('#cenaPorudzbenice').val(ponuda.price);
				$('#daniPorudzbenice').val(ponuda.timeDeliver);
			}
				
	var data2 = JSON.stringify({
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
		data : data2,
		url : '/providerController/uzmiPonudu',
		success : function(ponuda){
			$('#krajPonude').val(ponuda.endDate);
			$('#porudzbenicaOffer').val(ponuda.id);
			$('#porudzbenicaRestoran').val(ponuda.restaurant.id);
			$('#foodDrinkPonude').val(ponuda.foodOrDrink);
			$('#flagPonude').val(ponuda.flag);
			$('#kolicinaPonude').val(ponuda.amount);
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			toastr.error("Admin ERROR: " + errorThrown);
		}	
	});
	
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			toastr.error("Admin ERROR: " + errorThrown);
		}
	});
});


$(document).on('submit','#submitDodajPorudzbenicu',function(e){
	e.preventDefault();
	var restaurant = $(this).find("input[id='porudzbenicaRestoran']").val();
	var offer = $(this).find("input[id='porudzbenicaOffer']").val();
	var days = $('#daniPorudzbenice').val();
	var price = $('#cenaPorudzbenice').val();
	
	if(days == ""){
		toastr.error("Morate uneti broj dana potrebnih!");
	}else if(price == ""){
		toastr.error("Morate uneti cenu!");
	}else{
		
		var offer = JSON.stringify({
			"id" : offer,
			"endDate" : "",
			"foodOrDrink" : 0,
			"flag" : "",
			"restaurant" : null,
			"amount" : 0,
			"accepted" : false
		});
		
		var obj = JSON.parse(offer);
		
		var data2 = JSON.stringify({
			"offer" : obj,
			"restaurant" : null,
			"timeDeliver" : days,
			"price" : price,
			"provider" : null,
			"flag" : 0
		});
		
		$.ajax({
			type : 'POST',
			url :  '/providerController/dodajPorudzbenicu',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
			success : function(data){
				window.location.reload();
				toastr.info('Dodata porudzbenica');
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				toastr.error("AJAX ERROR: " + errorThrown);
			}
		});
	}
});


$(document).on('submit','#submitIzmeniPorudzbenicu',function(e){
	e.preventDefault();
	var id = $(this).find("input[id='porudzbenicaId']").val();
	var restaurant = $(this).find("input[id='porudzbenicaRestoran']").val();
	var offer = $(this).find("input[id='porudzbenicaOffer']").val();
	var days = $('#daniPorudzbenice').val();
	var price = $('#cenaPorudzbenice').val();
	
	if(days == ""){
		toastr.error("Morate uneti broj potrebnih dana!");
	}else if(price == ""){
		toastr.error("Morate uneti cenu!");
	}else{
		
		var data2 = JSON.stringify({
			"id" : id,
			"offer" : null,
			"restaurant" : null,
			"timeDeliver" : days,
			"price" : price,
			"flag" : 0,
			"provider" : null
		});
		
		$.ajax({
			type : 'POST',
			url :  '/providerController/izmeniPorudzbenicu',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
			success : function(data){
				window.location.reload();
				toastr.info('Izmenjena porudzbenica!');
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				toastr.error("AJAX ERROR: " + errorThrown);
			}
		});
	}
});


$(document).on('click','#spisakPonuda', function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#content').append('<table id="tabelaPrikaz"><tr><th> ID PONUDE</th><th>IME RESTORANA</th><th>VREME ISPORUKE</th>'+
			'<th>CENA</th><th>STANJE</th></tr></table>');
	
	$.ajax({
		type: 'GET',
		contentType : 'application/json',
		url : '/providerController/uzmiSvePorudzbenicePonudjaca',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,ponuda){
				
				if(ponuda.flag==ponuda.provider.id ){
				$('#tabelaPrikaz').append('<tr id="nesto"><td>'+ponuda.offer.id+'</td><td>'+ponuda.restaurant.name+'</td><td>'
						+ponuda.timeDeliver+'</td><td>'+ponuda.price+'</td><td>prihvacena</td></tr>');
				}else if(ponuda.flag!=ponuda.provider.id && ponuda.flag!=0)
			    {
					$('#tabelaPrikaz').append('<tr id="nesto"><td>'+ponuda.offer.id+'</td><td>'+ponuda.restaurant.name+'</td><td>'
						+ponuda.timeDeliver+'</td><td>'+ponuda.price+'</td><td>odbijeno</td></tr>');
			    }else{
					$('#tabelaPrikaz').append('<tr id="nesto"><td>'+ponuda.offer.id+'</td><td>'+ponuda.restaurant.name+'</td><td>'
							+ponuda.timeDeliver+'</td><td>'+ponuda.price+'</td><td>cekanje</td></tr>');}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			toastr.error("Admin ERROR: " + errorThrown);
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
			toastr.error("billOrders: " + errorThrown);
		}	
	});
});
