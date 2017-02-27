window.onload = function() {
	
	var query = window.location.search.substring(1);
	var idReservation = query.split("=")[1];
	
	
	 //var idGuest = PrihvatiPorudzbinu.caller.arguments[0].target.id; //njega dodajem
	   // var idReservation = PrihvatiPorudzbinu.caller.arguments[0].target.name; //njega dodajem
	    alert("id rezervacije"+ idReservation)
	    
	    var desk = -1;
	    
	    $.ajax({
		type : 'GET',
		url :  '/reservationController/getReservedTableForReservation/'+ idReservation,
		dataType :'json',
		success : function(tables){
			alert(tables.length);
			
			desk = tables[0].id;
			alert("Sto"+ desk);	
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}

	    });
	    
	    
	    $("#content").empty();
		var allDrinks = [];
		var allMeals = [];
		var allReservations= [];
		
				$.ajax({
					type: 'PUT',
					dataType: 'json',
					url : '/mealAndDrinkController/getAllDrinks/'+ idReservation,
					success : function(data){
						
						var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
						//$("#tableAddOrder").append('<tr><td>Pića: &nbsp; </td><td><select id="comboDrinks" multiple="multiple" size="5" style="width:170px;">');
						$.each(list, function(index,pice){
							//$('#comboDrinks').append('<option>'+pice.name+'</option>');
							allDrinks.push(pice.name);
						
						});
						$.ajax({
							type: 'PUT',
							dataType: 'json',
							url : '/mealAndDrinkController/getAllMeals/'+ idReservation,
							success : function(data){
								
								var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
								//$("#tableAddOrder").append('<tr><td>Jela: &nbsp;</td><td><select id="comboMeals" multiple="multiple" size="5" style="width:170px;">');
								$.each(list, function(index,obrok){
									//$('#comboMeals').append('<option>'+obrok.name+'</option>');
									allMeals.push(obrok.name);
									
								});
								$('#content').empty();
								$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
										'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
												'Dodavanje porudžbine:<br/>'+
												'<br/>Rezervacija:<br/>'+
												'<input type="text" id="desk" value="'+desk+'"><br/>'+
												'<input type="text" id="comboReservations" value="'+idReservation+'"><br/>'+
												//'<input type="text" id="timeOfOrder" value="'+idReservation+'"><br/>'+
												'<br/>Pića:'+
												'<select id="comboDrinks" multiple="multiple" size="5" style="width:300px"></select>'+
												'<br/>Jela:'+
												'<select id="comboMeals" multiple="multiple" size="5" style="width:300px"></select>'+
												'<br/><input type = "button" name ="'+idReservation+'" class="btn green" onclick="KreirajPorudzbinu()" id="dodaj" value="Dodaj">');
								
								$('#comboReservations').append('<option>'+idReservation+'</option>');
								
								$.each(allDrinks, function(index,drink){
									$('#comboDrinks').append('<option>'+drink+'</option>');
								});
								$.each(allMeals, function(index,meal){
									$('#comboMeals').append('<option>'+meal+'</option>');
								});
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert("Meal ERROR: " + errorThrown);
							}	
						});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("Drink ERROR: " + errorThrown);
					}	
				});
				
		
		
			
	
	
	
	
	
	
};



function KreirajPorudzbinu(){
	
	
	 var idReservation = KreirajPorudzbinu.caller.arguments[0].target.name;
	
	//var reservationId = $('#comboReservations').val();
	var desk = $('#desk').val();
	var drinks = $('#comboDrinks').val();
	var meals = $('#comboMeals').val();
	var listOfDrinkss = [];
	var timeOfOrder = new Date().getTime();

	alert("rese id "+ idReservation +" desk" +desk + "drinks" + drinks.length+ "meals"+ meals.length);

	$.ajax({
		type : 'PUT',
		url :'/reservationController/getReservationById/'+ idReservation,
		dataType : 'json',
		success : function(reservation){

			$.ajax({
				type : 'POST',
				url :  '/orderController/addOrderInReservation',
				contentType : 'application/json',
				dataType :'json',
				data : JSON.stringify({
					"waiter_id" :"1",
					"table_id" : desk,
					"restaurant" : reservation.idRestaurant.id,
					"barman_state" : "kreirana",
					"cook_state" : "kreirana",
					"date" : reservation.date,
					"time": reservation.time,
					"drinks" : drinks,
					"meals" : meals,
					"reservation" : idReservation
				}),
				success : function(data){
					
					alert("Uspjesno dodavanje porudzbine za konobara "+data.waiter.email);
					window.location.href= "userProfile.html";
					
				},

				error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
					alert("addOrder ERROR: " + errorThrown);
				}
			});
			
			
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("newOrder ERROR: " + errorThrown);
		}
	
	});
	
			
	
}