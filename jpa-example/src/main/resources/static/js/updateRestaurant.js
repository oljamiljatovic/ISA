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
	
	/*$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/registerController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){
				$('#izborPica').append('<input type="checkbox" name="drink_dr" id = "' +pice[1] +'" value="' + pice[1] + '">' + pice[1]);
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/registerController/uzmiObroke',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(list, function(index,obrok){
						$('#izborObroka').append('<input type="checkbox" name="meal_dr" id = "' +obrok[1] +'" value="' + obrok[1] + '">' + obrok[1]);
					});
					
					$.ajax({
		    			type : 'GET',
		    			url :  '/registerController/uzmiRestoranMenadzera',
		    			contentType : 'application/json',
		    			success : function(data){
		    				
		    				$('#imeRestorana').val(data.name);
		    				$('#vrstaRestorana').val(data.type);
		    				$('#adresaRestorana').val(data.address);
		    				$('#kontaktRestorana').val(data.contact);
		    				for(var i=0;i<data.drinks.length;i++) {
								document.getElementById(data.drinks[i]).checked = true;
							}
		    				for(var i=0;i<data.meals.length;i++) {
								document.getElementById(data.meals[i]).checked = true;
							}
		    		
		    			},

		    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
		    				alert("Da li je ovdje problem");
		    				alert("AJAX ERROR: " + errorThrown);
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
	});*/
	
	$.ajax({
		type : 'GET',
		url :  '/registerController/uzmiRestoranMenadzera',
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
		/*$('input[name="drink_dr"]:checked').each(function() {
			  drinks.push(this.value);
			  console.log(this.value);
		});
		$('input[name="meal_dr"]:checked').each(function() {
			  meals.push(this.value);
			  console.log(this.value);
		});*/
		
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
	var restaurant = "";
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
			url :  '/restaurantManagerController/addReon',
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

$(document).on('click','#pica',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<table id="tabelaPrikaz" ><tr><th>ID</th><th>NAZIV</th><th>CENA</th></tr>'+
			'<th></th><th></th></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/registerController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){
				$('#tabelaPrikaz').append('<tr><td>'+pice.id+'</td><td>'+pice.name+'</td><td>'+pice.price+'</td>'+
						'<td><form id="formIzbrisiPice" method="get" action=""><input type="submit" id="submitIzbrisiPice" value="Izbrisi"><input type="hidden" value='
							+pice.id+'></form></td><td><form id="formIzmeniPice" method="get" action="">'+
							'<input type="submit" value="Izmeni">' +
							'<input type="hidden" id="idIzmenaId" value='+pice.id+'>'+
							'<input type="hidden" id="idIzmenaName" value='+pice.name+'>'+
							'<input type="hidden" id="idIzmenaPrice" value='+pice.price+'>'+
							'</form></td></tr>');
				
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});

$(document).on('submit','#formIzbrisiPice',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[type=hidden]").val();
	var name = "";
	var price = 0;
	var data2 = JSON.stringify({
		"id" : id,
		"name" : name,
		"price" : price
	});
	$.ajax({
		type : 'POST',
		url :  '/restaurantManagerController/deleteDrink',
		contentType : 'application/json',
		data : data2,
		success : function(){
			window.location.reload();
			
			$('#content').empty();
			$('#content').append('<table id="tabelaPrikaz" ><tr><th>ID</th><th>NAZIV</th><th>CENA</th></tr>'+
					'<th></th><th></th></table>');
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/registerController/uzmiPica',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(list, function(index,pice){
						$('#tabelaPrikaz').append('<tr><td>'+pice.id+'</td><td>'+pice.name+'</td><td>'+pice.price+'</td>'+
								'<td><form id="formIzbrisiPice" method="get" action=""><input type="submit" id="submitIzbrisiPice" value="Izbrisi"><input type="hidden" value='
									+pice.id+'></form></td><td><form id="formIzmeniPice" method="get" action=""><input type="submit" value="Izmeni"><input type="hidden" id="idIzmena" value='
									+pice.id+'></form></td></tr>');
						
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Admin ERROR: " + errorThrown);
				}	
			});
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});

$(document).on('submit','#formIzmeniPice',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='idIzmenaId']").val();
	var name = $(this).find("input[id='idIzmenaName']").val();
	var price = $(this).find("input[id='idIzmenaPrice']").val();
	
	$('#content').empty();
	
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form method="post">'+
		'Podaci o picu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imePica"  class="in-text"/><br/>'+
		'Cena:<input type = "text" id = "cenaPica" class="in-text"/><br/>'+
		'<input type = "submit" id = "submitIzmeniPice" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
	
	$('#imePica').val(name);
	$('#cenaPica').val(price);
	var data2 = JSON.stringify({
		"id" : id,
		"name" : name,
		"price" : price
	});
});



