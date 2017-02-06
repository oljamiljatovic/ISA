window.onload = function() {
    document.getElementById('dugmeRegistracija').onclick = function() {
    	var email = $("#email").val();
    	var name = $("#name").val();
    	var surname = $("#surname").val();
    	var password = $("#password").val();
    	var repeatPassword =$("#repeatPassword").val(); 
    	
    	if(email == ""){
    		toastr.error("Username is empty");
    	}
    	else if(password == ""){
    		toastr.error("Password is empty");
    	}else if(repeatPassword == ""){
    		toastr.error("Repeat password is empty");
    	}
    	else{
    		if(password != repeatPassword ){
    			alert("Molimo Vas ponovite password");
    			
    		}else{
    		
    				//drugi ajax poziv
    	    		$.ajax({
    	    			type : 'POST',
    	    			url :  '/guestController/regIn',
    	    			contentType : 'application/json',
    	    			dataType :'json',
    	    			data : JSON.stringify({
    	    				
    	    				"email" :email,
    	    				"password" : password,
    	    				"role" : "guest",
    	    				"accept" : "false",
    	    				"name" : name,
    	    				"surname" : surname
    	    			}),
    	    			success : function(dataGuest){
    	    				alert("Registracija je uspjesno izvrsena!");
    	    				window.location.href= "index.html"
    	    				
    	    			
    	    			},

    	    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    	    			
    	    				alert("AJAX ERROR: " + errorThrown);
    	    			}
    	    		
    	    		
    			
    			

    		
    		});
    		
    		
    		
    		
    		}
    	
    		}
    };
    
    document.getElementById('dugmeSign').onclick = function() {
    	
    					window.location.href= "signIn.html"
    	
    };
    
    
};
