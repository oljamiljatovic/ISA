$(document).on('click','#restoran',function(e){
	e.preventDefault();
	
	$('#restaurantHtml').empty();
	
	$('#restoranHtml').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form method="post" id="registracijaRestorana">'+
		'Podaci o restoranu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imeRestorana" class="in-text"/><br/>'+
		'Vrsta restorana:<input type = "text" id = "vrstaRestorana" class="in-text"/><br/>'+
		'Adresa:<input type = "text" id = "adresaRestorana" class="in-text"/><br/>'+
		'Kontakt:<input type = "text" id = "kontaktRestorana" class="in-text"/><br/>'+
		'<div id="izborPica"></div><br/><div id="izborObroka"></div><br/>'+
		'<input type = "submit" id = "submitIzmenaRestorana" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
	$.ajax({
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
	}else if($("input[type=checkbox]:checked").length==0){
		alert("Morate uneti pice");
	}else{
		$('input[name="drink_dr"]:checked').each(function() {
			  drinks.push(this.value);
			  console.log(this.value);
		});
		$('input[name="meal_dr"]:checked').each(function() {
			  meals.push(this.value);
			  console.log(this.value);
		});
		
		var data2 = JSON.stringify({
			"name" : name,
			"type" : type,
			"address" : address,
			"contact" : contact,
			"drinks" : drinks,
			"meals" : meals
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