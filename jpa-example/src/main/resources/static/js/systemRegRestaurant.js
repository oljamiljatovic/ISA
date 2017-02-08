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


/*$(document).ready(function(){
$.ajax({
	type: 'GET',
	dataType: 'json',
	url : '/registerController/uzmiPica',
	success : function(data){
		var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
		$.each(list, function(index,pice){
			$('#izborPica').append('<input name="drink_dr" type="checkbox" id = "' +pice[1] +'" value="' + pice[1] + '">' + pice[1]);
		});
		
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url : '/registerController/uzmiObroke',
			success : function(data){
				var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
				$.each(list, function(index,obrok){
					$('#izborObroka').append('<input name="meal_dr" type="checkbox" id = "' +obrok[1] +'" value="' + obrok[1] + '">' + obrok[1]);
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
});*/