


window.onload = function() {
	
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url :'/userController/isValidate',
		success : function(data){
			var roleData = data.role;
			alert(roleData);
				if(roleData != "guest"){
					document.location.href="index.html";
				}else{
					$.ajax({ //ajax poziv za dobavljanje informacija o guest
						type : 'POST',
						url :'/guestController/getGuest/',
						contentType : 'application/json',
						dataType : 'json',
						data :  JSON.stringify({
		    				"email" : data.email,
		    				"password" : data.password
		    			}),
						success : function(dataa){
							
							 document.getElementById("ime").value=dataa.name;
							 document.getElementById("prezime").value= dataa.surname;
							 
							
						},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("GRESKA U ISPISU OSOBINA");
					}
					
					});	
					
					
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
							
							$('#tabelaPrijatelja').empty();
							for(var i = 0 ; i < friends.length ; i++){
								 var item = friends[i];
								$('#tabelaPrijatelja').append('<li>'+item.name +'</li><li> &nbsp;</li><li>'+item.surname +'</li></br>');
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
	
	   document.getElementById('izmjeni').onclick = function() {
		   document.getElementById("ime").readOnly = false;
		   document.getElementById("prezime").readOnly = false;
	   }
	
    document.getElementById('potvrdiIzmjenu').onclick = function() {
    	var ime = $("#ime").val();
    	var prezime = $("#prezime").val();
    	var idToChange = -1;
    
    	
    	if(ime == ""){
    		toastr.error("Ime is empty");
    	}
    	else if(prezime == ""){
    		toastr.error("Adresa is empty");
    	}
    	else{
    		
        	$.ajax({
        		type : 'GET',
        		dataType : 'json',
        		url :'/userController/isValidate',
        		success : function(data){
        			idToChange = data.id;
        			
        			$.ajax({
            			type : 'PUT',
            			url :  '/guestController/change/'+ idToChange,
            			contentType : 'application/json',
            			dataType :'json',
            			data : JSON.stringify({
            				"name" : ime,
            				"surname" : prezime
            			}),
            			success : function(data){
            				
            				alert("Uspjesno izvrsena promjena");
            				
            			
            			},

            			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
            				alert("AJAX ERROR: " + errorThrown);
            			}
            		
            		});
        			
        		},
        	error : function(XMLHttpRequest, textStatus, errorThrown) {
        		alert("Problem sa pronalazenjem id-ja");
        	}	
        	});//kraj ajax poziva za id
        	
    		
    		
    		
    		}
    	
    		
    };
    
    
    
    
    document.getElementById('mojNalog').onclick = function() {
    	
    					window.location.href= "userProfile.html"
    	
    };
    
    document.getElementById('prijatelji').onclick = function() {
    	
		window.location.href= "friendsOfGuest.html"

};

    
    
    
    
    
    
    
    
    
    
    
};
