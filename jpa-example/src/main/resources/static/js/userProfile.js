


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
    
    
    
    
    
    
    
    
    
    
    
};