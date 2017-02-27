window.onload = function() {
	
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url :'/userController/isValidate',
		success : function(data){
			var roleData = data.role;
	
				if(roleData != "guest"){
					document.location.href="index.html";
				}else{
			
					
					$.ajax({ //ajax poziv za dobijanje ko su prijatelji
						type : 'POST',
						url :'/guestController/getFriends/',
						contentType : 'application/json',
						dataType : 'json',
						data :  JSON.stringify({
		    				"email" : data.email,
		    				"password" : data.password
		    			}),
						success : function(friends){
							alert("Usao kod prijatelja");
							var table = document.getElementById("tabelaPrijatelji");
							$('#tabelaPrijatelji').empty();
							for(var i = 0 ; i < friends.length ; i++){
								 var item = friends[i];
								 var row = table.insertRow(i);
								 
								 
								 var cell1 = row.insertCell(0);
								 var cell2 = row.insertCell(1);
								 var cell3 = row.insertCell(2);
								 
								 var itemID = item.id;
								 cell1.innerHTML = item.name;
								 cell2.innerHTML = item.surname;
								 cell3.innerHTML =  '<input type = "button" class="btn green" onclick="IzbrisiPrijatelja()" id ="'+itemID+'"value="Obrisi"></td> ';
								 
								
							}
						},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("GRESKA kod prijatelja");
						alert("AJAX ERROR: " + errorThrown);
					}
					
					});	//kraj ajax za prijatelje
					
					alert("DATA JE"+ data.id);
					
					$.ajax({ //ajax poziv za zahtjeve
						type : 'POST',
						url :'/invitationController/getRequests/'+ data.id,
						dataType : 'json',
						success : function(requests){
						//if(requests.accept.equals("false")){
							//alert("Usao u ispis zahtjeva");
							var table = document.getElementById("zahtjevi");
							$('#zahtjevi').empty();
							for(var i = 0 ; i < requests.length ; i++){
								
								 var item = requests[i];
								
									
								 var row = table.insertRow(i);
								 
								 var cell0 = row.insertCell(0);
								 var cell1 = row.insertCell(1);
								 var cell2 = row.insertCell(2);
								 var cell3 = row.insertCell(3);
								 var cell4 = row.insertCell(4);
								 
									$.ajax({ //ajax poziv za zahtjeve
										type : 'PUT',
										url :'/guestController/findById/'+ item.sender.id,
										dataType : 'json',
										success : function(guest){
											alert("nasao onog sto je poslao zahtjev");
											cell1.innerHTML = guest.name;
											cell2.innerHTML = guest.surname;
										},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert("GRESKA kod findById");
										alert("AJAX ERROR: " + errorThrown);
									}
									
									});	
									
								 cell0.innerHTML = "Zahtjev od : ";
								 cell3.innerHTML = '<input type = "button" class="btn green" onclick="Prihvati()" id ="'+requests[i].sender.id+'/'+ requests[i].recipient.id+'"value="Prihvati"></td> ';
								 cell4.innerHTML = '<input type = "button" class="btn green" onclick="Odbij()" id ="'+requests[i].sender.id+'/'+ requests[i].recipient.id+'"value="Odbij"></td> ';
							
							}
							//}
						},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("GRESKA kod zahtjeva");
						alert("AJAX ERROR: " + errorThrown);
					}
					
					});	//kraj ajax za zahtjeve
					
				}
			
		},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		alert("GRESKA");
	}	
	});
	
	
////////////////////PRETRAGA///////////////////////////
document.getElementById('trazi').onchange = function() {
		  
		  var text = document.getElementById('trazi').value;
		  
		var spliting =  text.split(" ");
		var name = spliting[0];
		var surname = spliting[1];
		
		
		  
		if(name!="" || surname!=""){
		var search = {
			      "first" : name,
			      "second" :surname
			   };
		
		 $.ajax({
		      type: 'POST',contentType : 'application/json',
		      
		      dataType : 'json',
		      url: '/guestController/search/',
		      data: name, // Note it is important
		      success :function(result) {
		      
		       var table = document.getElementById("pronadjeni");
				$('#pronadjeni').empty();
				for(var i = 0 ; i < result.length ; i++){
					 var item = result[i];
					 var row = table.insertRow(i);
					 
					 
					 var cell1 = row.insertCell(0);
					 var cell2 = row.insertCell(1);
					 var cell3 = row.insertCell(2);
					 
					 var itemID = item.id;
					 cell1.innerHTML = item.name;
					 
					 cell2.innerHTML = item.surname;
					 cell3.innerHTML =  '<input type = "button" class="btn green" onclick="PosaljiZahtjev()" id ="'+itemID+'"value="Dodaj"></td> ';
					 
					 
					 /*var button = document.getElementById(item.id)
					 button.disabled = "true";
					 
					 button.className ="btn red";*/
					
				}
		       
		       
		       
		       
		  	},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("GRESKA kod pretrage");
				alert("AJAX ERROR: " + errorThrown);
			}
			
		  });
		  
		}//else za prazan  
	}
  
    
};



///////////////IZBRISI PRIJATELJA/////////////////////
function IzbrisiPrijatelja(){
	
	    var idFriend = IzbrisiPrijatelja.caller.arguments[0].target.id;
	  	
	    var idToChange = -1;

    	$.ajax({
    		type : 'GET',
    		dataType : 'json',
    		url :'/userController/isValidate',
    		success : function(data){
    			idToChange = data.id;
    		
    			$.ajax({
        			type : 'PUT',
        			url :  '/guestController/changeDeleteFriend/'+ idToChange + '/'+ idFriend,
        			dataType :'json',
        			success : function(dataa){
        				
        				document.location.href="friendsOfGuest.html";
        			
        			},

        			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
        				alert("AJAX ERROR: " + errorThrown);
        			}
        		
        		});
    		},
    			
    		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		
		});

	
    
}


//////////////////////POSALJI ZAHTJEV////////////////////

function PosaljiZahtjev(){
	
	    
	    var idFriend = PosaljiZahtjev.caller.arguments[0].target.id; //njega dodajem
	  	
	    var idToChange = -1;

    	$.ajax({
    		type : 'GET',
    		dataType : 'json',
    		url :'/userController/isValidate',
    		success : function(data){
    			idToChange = data.id;
    		
    			
    			$.ajax({
        			type : 'POST',
        			url :  '/invitationController/sendRequest/'+ idToChange + '/'+ idFriend,
        			dataType :'json',
        			success : function(dataa){
        			
        				 var buttonAdd = document.getElementById(idFriend);
        				 buttonAdd.value="Poslat zahtjev";
        				
        				 var element = document.getElementById(idFriend);
        				 
        				var elem = element.parentNode.parentNode;
        				$(elem).append('<td><input type = "button" class="btn green" onclick="PrekiniZahtjev()" id ="'+idFriend+'"value="Otkazi zahtjev"></td>');
        				
        			},

        			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
        				alert("AJAX ERROR: " + errorThrown);
        			}
        		
        		})
    			
    		
    		},
    			
    		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		
		});

	
    
}

///////////////////PREKINI ZAHTJEV//////////////////////////
function PrekiniZahtjev(){
	
    
    var idFriend = PrekiniZahtjev.caller.arguments[0].target.id; //njega dodajem
  	
    var idToChange = -1;

	$.ajax({
		type : 'GET',
		dataType : 'json',
		url :'/userController/isValidate',
		success : function(data){
			idToChange = data.id;
		
			
			$.ajax({
    			type : 'PUT',
    			url :  '/invitationController/cancelRequest/'+ idToChange + '/'+ idFriend,
    			dataType :'json',
    			success : function(dataa){
    				
    				$('#pronadjeni').empty();
    			//IZBRISI ZAHTJEVE , nevalidan, prekinut
    				 
    				
    				
    				/*var buttonAdd = document.getElementById(idFriend);
    				 buttonAdd.value="Posalji zahtjev";
    				
    				 var element = document.getElementById(idFriend);
    				 
    				var elem = element.parentNode.parentNode;
    				$(elem).append('<td><input type = "button" class="btn green" onclick="PrekiniZahtjev()" id ="'+idFriend+'"value="Otkazi zahtjev"></td>');*/
    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		
    		})
		},
			
		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	
	});



}

//////////////////////PRIHVATI ZAHTJEV/////////////////////
function Prihvati(){
	
    var idFriend = Prihvati.caller.arguments[0].target.id; //njega dodajem
  	alert("Kod prihvatanja"+ idFriend);
  	
  	 var substr = idFriend.split("/");
     
     var sender =  substr[0];
     var recipient = substr[1];
     
     
			$.ajax({
    			type : 'POST',
    			url :  '/invitationController/changeInvitation/'+ sender + '/'+ recipient,
    			contentType : 'application/json',
    			dataType :'json',
    			data : "true",
    			success : function(invitation){ //vraca invitation
    			
    				var idToChange = invitation.sender.id;
    				var idFriend = invitation.recipient.id;
    				//Ovdje je prihvacen zahtjev 
    				$.ajax({
            			type : 'PUT',
            			url :  '/guestController/changeAddFriend/'+ idToChange + '/'+ idFriend,
            			dataType :'json',
            			success : function(friends){ //vraca sada listu prijatelja
            			//ali oljinih umjesto vesninih
            				var table = document.getElementById("tabelaPrijatelji");
							$('#tabelaPrijatelji').empty();
							for(var i = 0 ; i < friends.length ; i++){
								 var item = friends[i];
								 var row = table.insertRow(i);
								 
								 
								 var cell1 = row.insertCell(0);
								 var cell2 = row.insertCell(1);
								 var cell3 = row.insertCell(2);
								 
								 var itemID = item.id;
								 cell1.innerHTML = item.name;
								 cell2.innerHTML = item.surname;
								 cell3.innerHTML =  '<input type = "button" class="btn green" onclick="IzbrisiPrijatelja()" id ="'+itemID+'"value="Obrisi"></td> ';
								 
								
							}
            			
            			},

            			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
            				alert("AJAX ERROR: " + errorThrown);
            			}
            		
            		});
    				
    				
    				
    				$.ajax({ //kraj ajax posle prihvatanja requests
						type : 'POST',
						url :'/invitationController/getRequests/'+ idFriend,
						dataType : 'json',
						success : function(requests){
						//if(requests.accept.equals("false")){
							//alert("Usao u ispis zahtjeva");
							var table = document.getElementById("zahtjevi");
							$('#zahtjevi').empty();
							for(var i = 0 ; i < requests.length ; i++){
								
								 var item = requests[i];
								if(requests[i].accept.valueOf() == "false"){
									
								 var row = table.insertRow(i);
								 
								 var cell0 = row.insertCell(0);
								 var cell1 = row.insertCell(1);
								 var cell2 = row.insertCell(2);
								 var cell3 = row.insertCell(3);
								 var cell4 = row.insertCell(4);
								 
									$.ajax({ //ajax poziv za zahtjeve
										type : 'PUT',
										url :'/guestController/findById/'+ item.sender.id,
										dataType : 'json',
										success : function(guest){
											alert("nasao onog sto je poslao zahtjev");
											cell1.innerHTML = guest.name;
											cell2.innerHTML = guest.surname;
										},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert("GRESKA kod findById");
										alert("AJAX ERROR: " + errorThrown);
									}
									
									});	
									
								 cell0.innerHTML = "Zahtjev od : ";
								 cell3.innerHTML = '<input type = "button" class="btn green" onclick="Prihvati()" id ="'+requests[i].sender.id+'/'+ requests[i].recipient.id+'"value="Prihvati"></td> ';
								 cell4.innerHTML = '<input type = "button" class="btn green" onclick="Odbij()" id ="'+requests[i].sender.id+'/'+ requests[i].recipient.id+'"value="Odbij"></td> ';
							}
							}
							//}
						},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("GRESKA kod zahtjeva");
						alert("AJAX ERROR: " + errorThrown);
					}
					
					});	//kraj ajax posle prihvatanja requests
    				
    				
    				
    				

    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		
    		});

}

///////////////////////ODBIJ ZAHTJEV////////////////////
function Odbij(){
	 
    var idFriend = Odbij.caller.arguments[0].target.id; //njega dodajem
  	alert("Kod prihvatanja"+ idFriend);
  	
  	 var substr = idFriend.split("/");
     
     var sender =  substr[0];
     var recipient = substr[1];
     
     
			$.ajax({
    			type : 'POST',
    			url :  '/invitationController/changeInvitation/'+ sender + '/'+ recipient,
    			contentType : 'application/json',
    			dataType :'json',
    			data : "nevalidan",
    			success : function(dataa){
    			
    				//Ovdje je odbijen zahtjev zahtjev 
    				
    				
    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		
    		});
		
}







