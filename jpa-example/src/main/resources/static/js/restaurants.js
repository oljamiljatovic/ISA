window.onload = function() {
	
   	$.ajax({
		type : 'GET',
		dataType : 'json',
		url :'/registerController/uzmiRestorane',
		success : function(data){

			var table = document.getElementById("listaRestorana");
				$('#listaRestorana').empty();
				for(var i = 0 ; i < data.length ; i++){
					
					 var item = data[i];
					 $('#listaRestorana').append('<img src="rest1.jpg" alt="description here"  height="200" width="200" id = "'+data[i].id+'" onclick="OpenPicture()"/>'+
						 	'<input type = "button" onclick="OtvoriRestoran()" id ="'+data[i].id+'" value="'+data[i].name+'"> ' +
						 	'<input type = "button" onclick="PronadjiRestoran()" id ="'+data[i].id+'" value="'+data[i].address+'">');
					 if(i%3 == 2){
							$("#listaRestorana").append("<br/><br/><br/><br/>");
						}
					 
				}	
		},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		alert("Problem sa pronalazenjem id-ja");
	}	
	});//kraj ajax poziva za id
 
};





function OpenPicture(){
	
	var idGuest = -1;
	var idRestorana = OpenPicture.caller.arguments[0].target.id;
	
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url :'/userController/isValidate',
		success : function(data){
			
			idGuest = data.id;
		
		  	$('#listaRestorana').empty();
		  	  	
			$('#datum').empty();
			$('#datum').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
				'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
					'<form method="post" id="registracijaSmena">Odaberite datum:<br/><br/>'+
					'Pocetni datum:<input type = "date" id = "dateStart" class="in-text"/><br/<br/><br/>'+
					'Vrijeme:<input type = "time" id = "timeStart" class="in-text"/><br/<br/><br/>'+
					'<input type="hidden" id="idRest" value='+idRestorana+'>'+
					'<input type="hidden" id="idGuest" value='+idGuest+'>'+
					'Duzina boravka:<select id="radneSmene"><option value="1">1</option>'+
					'<option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option></select>'+
					'<br/><br/><input type = "submit" id = "submitSmene" value="Submit" class="btn orange">'+
					'</form></div></div></div></div>');
			
		
			
		},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		alert("Problem sa pronalazenjem id-ja");
	}	
	});//kraj ajax poziva za id
	


}


function OtvoriRestoran(){
	
	var idGuest = -1;
	var idRestorana = OtvoriRestoran.caller.arguments[0].target.id;
	
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url :'/userController/isValidate',
		success : function(data){
			
			idGuest = data.id;
		
		  	$('#listaRestorana').empty();
		  	  	
			$('#datum').empty();
			$('#datum').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
				'<div class="login-page wrapper centered centered-block"><div class = "form-group">'+
					'<form method="post" id="registracijaSmena">Odaberite datum:<br/><br/>'+
					'Pocetni datum:<input type = "date" id = "dateStart" class="in-text"/><br/<br/><br/>'+
					'Vrijeme:<input type = "time" id = "timeStart" class="in-text"/><br/<br/><br/>'+
					'<input type="hidden" id="idRest" value='+idRestorana+'>'+
					'<input type="hidden" id="idGuest" value='+idGuest+'>'+
					'Duzina boravka:<select id="radneSmene"><option value="1">1</option>'+
					'<option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option></select>'+
					'<br/><br/><input type = "submit" id = "submitSmene" value="Submit" class="btn orange">'+
					'</form></div></div></div></div>');
				
		},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		alert("Problem sa pronalazenjem id-ja");
	}	
	});//kraj ajax poziva za id
	


}


$(document).on('click','#submitSmene', function(e){

	e.preventDefault();
	

	var dateStart = $('#dateStart').val();
	var timeStart = $('#timeStart').val();
	var idRestorana = $('#idRest').val();
	var idGuest = $('#idGuest').val();
	var smene = $('#radneSmene option:selected').val();

	$('#listaRestorana').empty();
	$('#datum').empty();
	
	var guest = JSON.stringify({
		"id" : idGuest,
		"name" : "",
		"surname" : "",
	});
	var obj1 = JSON.parse(guest);

	
	
	var restaurant = JSON.stringify({
		"id" : idRestorana,
		"name" : "",
		"type" : "",
		"address" : "",
		"contact" : "",
	});
	var obj2 = JSON.parse(restaurant);
	

		var data2 = JSON.stringify({
			"idGuest" : obj1,
			"idRestaurant" : obj2,
			"date" : dateStart,
			"time" : timeStart,
			"duration" : parseInt(smene),
			"flag" : "aktivna",
		});
		$.ajax({
			type: 'POST',
			contentType : 'application/json',
			dataType: 'json',
			url : '/waiterController/getTablesForReservation',
			data : data2,
			success : function(tables){
				
				$("#content").empty();
				$("#content").append("<br/>");
				$("#content").append("<br/>")
				var numberTable = tables.length;
				var  x= numberTable/5;
				var iter = 0;
				var iterNum = parseInt(x, 10);
				
				for(var j= 0; j<tables.length; j++){
					
					$("#content").append('<input type = "button" name= "'+idGuest+"/"+idRestorana+"/"+dateStart+"/"+timeStart+"/"+smene+'" class="btn green" style="height:50px;width:90px"  onclick="OdabranSto()"'+ 
					'id ="'+tables[j].id+'"  value="Broj '+tables[j].id+'"></td> ');
					
					if(j%5 == 4){
						$("#content").append("<br/>");
					}
				
				}
				
			
				
/////////////////////////////////////////////				
				$.ajax({
					type: 'POST',
					contentType : 'application/json',
					dataType: 'json',
					url : '/waiterController/getReservedTables',
					data : data2,
					success : function(zauzeti){ //data su rezervisani reservedtables
						
						for(var i = 0 ; i < zauzeti.length ; i++){
							
							 var idStola = zauzeti[i].idTable.id;
							
							document.getElementById(idStola).disabled="true";
							document.getElementById(idStola).value="Zauzet";
							document.getElementById(idStola).className="btn red";
							
						
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("AssignReon ERROR: " + errorThrown);
					}	
				});	
				
				
				
				
				
				
	//////////////////////			
				
				
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("Tables ERROR: " + errorThrown);
			}	
		});
		
	
});





function OdabranSto(){
	 
   var idStola = OdabranSto.caller.arguments[0].target.id; //njega dodajem
   var idKomplet = OdabranSto.caller.arguments[0].target.name;
  
   
  
   var obj = idKomplet.split("/");
   
 
   var idGuest = obj[0];
   var idRestorana = obj[1];
   var dateStart = obj[2];
   var timeStart = obj[3];
   var trajanje = obj[4];

   var restaurant = JSON.stringify({
		"id" : idRestorana,
		"name" : "",
		"type" : "",
		"address" : "",
		"contact" : "",
	});
	var obj2 = JSON.parse(restaurant);
	
   var guest = JSON.stringify({
		"id" : idGuest,
		"name" : "",
		"surname" : "",
	});
	var obj1 = JSON.parse(guest);

	
		var data2 = JSON.stringify({
			"idGuest" : obj1,
			"idRestaurant" : obj2,
			"date" : dateStart,
			"time" : timeStart,
			"duration" : parseInt(trajanje),
			"flag" : "aktivna",
		});
   
   
			$.ajax({
   			type : 'POST',
   			url :  '/reservationController/addReservation/'+ idStola,
   			contentType : 'application/json',
   			dataType :'json',
   			data : data2,
   			success : function(reservation){
   			
   			$("#dugmee").empty();
   			
   			document.getElementById(idStola).disabled="true";
			document.getElementById(idStola).value="Zauzet";
			document.getElementById(idStola).className="btn red";
   			
   			
   			$("#dugmee").append('<br/><br/><input type="button"  style="height:50px;width:90px" id="'+reservation.id+'"  onclick="PotvrdiStolove()" class="btn orange" value="Potvrdi">');
			
   			},

   			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
   				alert("AJAX ERROR: " + errorThrown);
   			}
   		
   		});
		
}





function PotvrdiStolove(){
	 
   var idRezervacije = PotvrdiStolove.caller.arguments[0].target.id; 

   $("#content").empty();
   $("#dugmee").empty();

   
			$.ajax({
   			type : 'PUT',
   			url :  '/reservationController/getFriends/'+ idRezervacije,
   			dataType :'json',
   			success : function(friends){
   			
   		var table = document.getElementById("tabelaPrijateljiVecera");
   		$("#tabelaPrijateljiVecera").empty();
   		$("#dugmici").empty();
   			for(var i = 0 ; i < friends.length ; i++){
				
				
				 var item = friends[i];
				 var row = table.insertRow(i);
				 
				 
				 var cell1 = row.insertCell(0);
				 var cell2 = row.insertCell(1);
				 var cell3 = row.insertCell(2);
				 
				 var itemID = item.id;
				 cell1.innerHTML = item.name;
				 cell2.innerHTML = item.surname;
				 cell3.innerHTML =  '<input type = "button" name="'+idRezervacije+'" class="btn green" onclick="PozoviPrijateljaNaVeceru()" id ="'+itemID+'"value="Pozovi"></td> ';
				 
						
   			}
   			
   			var dugmici = document.getElementById("dugmici");
   			var row1 = dugmici.insertRow(0);
   			var row1cell1 = row1.insertCell(0);
			 var row1cell2 = row1.insertCell(1);
			 row1cell1.innerHTML =  '<input type = "button" name="'+idRezervacije+'" class="btn green" onclick="OdabirNarudzbineSendera()" id ="Nastavi" value="Nastavi"></td> ';;
			 row1cell2.innerHTML =  '<input type = "button" name="'+idRezervacije+'" class="btn green" onclick="OdustaniOdPozivaPrijatelja()" value="Odustani"></td> ';
			 
   			
   			},

   			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
   				alert("AJAX ERROR: " + errorThrown);
   			}
   		
   		});
		
}



function PozoviPrijateljaNaVeceru(){
	 
   var idPozvanogPrijatelja = PozoviPrijateljaNaVeceru.caller.arguments[0].target.id; 
   var idRezervacije = PozoviPrijateljaNaVeceru.caller.arguments[0].target.name; 

			$.ajax({
   			type : 'POST',
   			url :  '/reservationController/sendRequestByMail/'+ idPozvanogPrijatelja,
   			contentType : 'application/json',
   			dataType :'json',
   			data: idRezervacije,
   			success : function(friend){
   			
   			alert("Poslao zahtjev!");
   			var table = document.getElementById(friend.id).className="btn red";
   			var table = document.getElementById(friend.id).value="Poslat";
   			var table = document.getElementById(friend.id).disabled="true";
   			
   		
   			},

   			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
   				alert("AJAX ERROR: " + errorThrown);
   			}
   		
   		});
		
}


function OdustaniOdPozivaPrijatelja(){
	var idRezervacije = OdustaniOdPozivaPrijatelja.caller.arguments[0].target.name;
	window.location.href= "narudzbinaSendera.html?idReservation="+idRezervacije;
		
}


function OdabirNarudzbineSendera(){
	 
	var idRezervacije = OdabirNarudzbineSendera.caller.arguments[0].target.name;
	window.location.href= "narudzbinaSendera.html?idReservation="+idRezervacije;
		
}




$(document).on('click','#restorani',function(e){
	window.location.href= "restaurants.html";
	
});

$(document).on('click','#prijatelji',function(e){
	window.location.href= "friendsOfGuest.html";
	
});

$(document).on('click','#mojNalog',function(e){
	window.location.href= "userProfile.html";
	
});

$(document).on('click','#istorija',function(e){
	window.location.href= "history.html";
	
});
