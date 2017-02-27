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
    			
    				window.location.href= "index.html"
    				
    				
    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		
    		});
		
}







