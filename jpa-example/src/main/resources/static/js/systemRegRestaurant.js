$(document).ready(function(){
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
});

$(document).on('submit','#registracijaRestorana',function(e){
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