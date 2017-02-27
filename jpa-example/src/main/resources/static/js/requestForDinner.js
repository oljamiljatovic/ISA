window.onload = function() {
	
	var query = window.location.search.substring(1);

	var pair = query.split("&");

	var pairGuest = pair[0];
	var pairReservation = pair[1];
	
	var idGuest = pairGuest.split("=")[1];
	var idReservation = pairReservation.split("=")[1];

	$.ajax({ //ajax poziv za dobijanje rezervacije
		type : 'PUT',
		url :'/reservationController/getReservationById/'+ idReservation,
		dataType : 'json',
		success : function(reservation){
			
			$('#tabelaOdgovorNaPoziv').empty();
			var table = document.getElementById("tabelaOdgovorNaPoziv");
			
			var idSender = reservation.idGuest.id;
			
			$.ajax({ //ajax poziv za dobijanje informacija o senderu
				type : 'PUT',
				url :'/reservationController/getSenderById/'+idSender ,
				dataType : 'json',
				success : function(sender){
					var row1 = table.insertRow(0);
					
					var row1cell1 = row1.insertCell(0);
					var row1cell2 = row1.insertCell(1);
					var row1cell3 = row1.insertCell(2);
					var row1cell4 = row1.insertCell(3);
					
					row1cell1.innerHTML = "Poziv od :";
					row1cell2.innerHTML = sender.name;
					row1cell3.innerHTML = sender.surname;
					row1cell4.innerHTML = sender.email;
					 
					
					var row2 = table.insertRow(1);
					
					var row2cell1 = row2.insertCell(0);
					var row2cell2 = row2.insertCell(1);
					var row2cell3 = row2.insertCell(2);
					var row2cell4 = row2.insertCell(3);
					
					
					row2cell1.innerHTML = "Restoran :";
					row2cell2.innerHTML = reservation.idRestaurant.id;
					row2cell3.innerHTML = reservation.date;
					row2cell4.innerHTML = reservation.time;
					
					
					
					var row3 = table.insertRow(2);
					
					var row3cell1 = row3.insertCell(0);
					var row3cell2 = row3.insertCell(1);
					var row3cell3 = row3.insertCell(2);
					var row3cell4 = row3.insertCell(3);
					
					row3cell1.innerHTML = " ";
					row3cell2.innerHTML =  '<input type = "button" name ="'+idReservation+'" class="btn orange" onclick="PrihvatiPozivZaVeceru()" id ="'+idGuest+'"value="Prihvati"></td> ';
					row3cell3.innerHTML =  '<input type = "button" name ="'+idReservation+'"  class="btn orange" onclick="OdbijPozivZaVeceru()" id ="'+idGuest+'"value="Odbij"></td> ';
					row3cell4.innerHTML = " ";	
						 
					
					
				},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("AJAX ERROR: " + errorThrown);
			}
			
			});	//ajax poziv za dobijanje informacija o senderu
			
		},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		alert("AJAX ERROR: " + errorThrown);
	}
	
	});	//ajax poziv za dobijanje rezervacije
	
   
};


function PrihvatiPozivZaVeceru(){
	 
    var idGuest = PrihvatiPozivZaVeceru.caller.arguments[0].target.id; //njega dodajem
    var idReservation = PrihvatiPozivZaVeceru.caller.arguments[0].target.name; //njega dodajem

   // alert("Kod prihvatanja"+ idGuest + "reservation" + idReservation);
  	
  	
     
			$.ajax({
    			type : 'POST',
    			url :  '/reservationController/addFriendToReservation/'+ idGuest,
    			contentType : 'application/json',
    			dataType :'json',
    			data : idReservation,
    			success : function(reservation){
    			alert("prihvatio ");
    				$('#tabelaOdgovorNaPoziv').empty();
    				$('#tabelaOdabir').empty();
    				
    				
    				var table = document.getElementById("tabelaOdabir");
    				
    				var row1 = table.insertRow(0);
					
					var row1cell1 = row1.insertCell(0);
					var row1cell2 = row1.insertCell(1);
					
					
					row1cell1.innerHTML = "Da li zelite" ;
					row1cell2.innerHTML = "da narucite?";
				
					 
					
					var row2 = table.insertRow(1);
					
					var row2cell1 = row2.insertCell(0);
					var row2cell2 = row2.insertCell(1);
					
					
					row2cell1.innerHTML = '<input type = "button"  class="btn orange" onclick="OdbijPorudzbinu()" value="Ne">';
					row2cell2.innerHTML = '<input type = "button" name ="'+idReservation+'" class="btn orange" onclick="PrihvatiPorudzbinu()" id ="'+idGuest+'"value="Da">';
					
    		
    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		
    		});
		
}


function OdbijPorudzbinu(){
	 
	window.location.href= "index.html"
		
}

function PrihvatiPorudzbinu(){
	 
    var idGuest = PrihvatiPorudzbinu.caller.arguments[0].target.id; //njega dodajem
    var idReservation = PrihvatiPorudzbinu.caller.arguments[0].target.name; //njega dodajem
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
										'<form method="post" id="updateForm">'+
											'Dodavanje porudžbine:<br/>'+
											'<br/>Rezervacija:<br/>'+
											'<input type="text" id="desk" value="'+desk+'"><br/>'+
											'<select id="comboReservations" style="width:300px"></select>'+
											'<br/>Pića:'+
											'<select id="comboDrinks" multiple="multiple" size="5" style="width:300px"></select>'+
											'<br/>Jela:'+
											'<select id="comboMeals" multiple="multiple" size="5" style="width:300px"></select>'+
											'<br/><input type = "submit" name = "'+idReservation+'" id = "submitOrder" value="Potvrdi" class="btn orange">'+
											'</form></div></div></div></div>');
							
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
			
	
	
		
}




$(document).on('click','#submitOrder',function(e){
	e.preventDefault();
	var reservationId = $('#comboReservations').val();
	var drinks = $('#comboDrinks').val();
	var meals = $('#comboMeals').val();
	var listOfDrinkss = [];
	var timeOfOrder = new Date().getTime();

	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/reservationController/getTablesForReservation/'+reservationId,
		success : function(data){
			var tables = data == null ? [] : (data instanceof Array ? data : [ data ]);
			if(tables.legth==0){
				Command: toastr["error"]("Nemate pravo primiti porudžbinu. Rezervisani stolovi nisu u vašem reonu!", "Greška!")
				message();
				$('#content').empty();
			}else{
				///kreiera za svaki sto po jednu porudzbinu na ime ovog konobara
				$.each(tables, function(index,table){
					$.ajax({
						type : 'POST',
						url :  '/orderController/addOrder',
						contentType : 'application/json',
						dataType :'json',
						data : JSON.stringify({
							"waiter_id" :"1",
							"table_id" : table.id,
							"restaurant" : "1",
							"barman_state" : "kreirana",
							"cook_state" : "kreirana",
							"timeOfOrder" : timeOfOrder,
							"drinks" : drinks,
							"meals" : meals,
							"reservation" : reservationId
						}),
						success : function(data){
							Command: toastr["success"]("Uspješno dodata porudžbina.", "Odlično!")
							message();
							showOrders();
							$.ajax({
								type : 'POST',
								url :  '/send/newOrder',
								data : {
									"newOrder" : "Stigla je nova porudžbina!"
								},
								success : function(data){	
									//Command: toastr["success"]("Uspjela je notifikacija.", "Odlično!")
									//message();
								},
		
								error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
									alert("newOrder ERROR: " + errorThrown);
								}
							
							});
						},
		
						error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
							alert("addOrder ERROR: " + errorThrown);
						}
					});
				});
				
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("getTablesForReservation ERROR: " + errorThrown);
		}	
	});
	
});





