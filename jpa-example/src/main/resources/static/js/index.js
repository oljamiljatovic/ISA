window.onload = function() {
    document.getElementById('dugmeLogovanje').onclick = function() {
    	var email = $("#email").val();
    	var password = $("#password").val();
    	if(email == ""){
    		toastr.error("Email is empty");
    	}
    	else if(password == ""){
    		toastr.error("Password is empty");
    	}
    	else{
    		$.ajax({
    			type : 'POST',
    			url :  '/userController/logIn',
    			contentType : 'application/json',
    			dataType :'json',
    			data : JSON.stringify({
    				"email" : email,
    				"password" : password
    			}),
    			success : function(dataUser){
    				
    				if(dataUser == null){
    					window.location.reload();
    				}
    				
    				if(dataUser.accept== "false"){
    					alert("Molimo Vas da potvrdite registraciju");
    					window.location.href= "index.html";
    				}else{
    				
    				
    				var userRole =  dataUser.role;
    				var userEmail = dataUser.email;
    				if(userRole == "guest"){
    			
    				
    				window.location.href= "userProfile.html";
    				
    				}else if(userRole == "employee"){
    					window.location.href= "waiter.html"
    				}else if(userRole=="restaurantManager"){
    					window.location.href = "restaurantManager.html";
    				}else if(userRole=="waiter"){
    					window.location.href = "waiter.html";
    				}else if(userRole=="cook" || userRole=="saladCook" ||userRole=="grilledCook"){
    					window.location.href = "cook.html";
    				}else if(userRole=="barman"){
    					window.location.href = "barman.html";
    				}else if(userRole=="provider"){
    					window.location.href = "provider.html";
    				}else if(userRole=="systemManager"){
    					window.location.href = "systemManager.html";
    				}
    				
    				}
    				
    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		});
    		
    	
    		}
    };
    
   
document.getElementById('dugmeSign').onclick = function() {
    	
    					window.location.href= "signIn.html"
    	
};
        
};

