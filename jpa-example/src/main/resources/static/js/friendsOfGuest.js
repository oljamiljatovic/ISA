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
					
				}
			
		},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		alert("GRESKA");
	}	
	});
	
	
	
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
		      type: 'POST',
		      contentType : 'application/json',
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
					 cell3.innerHTML =  '<input type = "button" class="btn green" onclick="DodajPrijatelja()" id ="'+itemID+'"value="Dodaj"></td> ';
					 
					
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




function DodajPrijatelja(){
	
	    
	    var idFriend = DodajPrijatelja.caller.arguments[0].target.id; //njega dodajem
	  	
	    var idToChange = -1;

    	$.ajax({
    		type : 'GET',
    		dataType : 'json',
    		url :'/userController/isValidate',
    		success : function(data){
    			idToChange = data.id;
    		
    			$.ajax({
        			type : 'PUT',
        			url :  '/guestController/changeAddFriend/'+ idToChange + '/'+ idFriend,
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



