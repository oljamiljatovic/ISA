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
    			
    					
    					if(dataUser.email=="mica")
    						window.location.href= "systemManager.html";
    					else
    						window.location.href= "userProfile.html";
    				
    				}else if(userRole == "employee"){
    					window.location.href= "waiter.html"
    				}else if(userRole=="restaurantManager"){
    					window.location.href = "restaurantManager.html";
    				}else if(userRole=="waiter"){
    					window.location.href = "waiter.html";
    				}else if(userRole=="cook"){
    					window.location.href = "cook.html";
    				}else if(userRole=="barman"){
    					window.location.href = "barman.html";
    				}
    				
    				}
    				/*alert(data.username);
    				username = data.username;
    				if(username == "desa"){
    					window.location.href= "waiter.html"
    				}else if(username == "mica"){
    					window.location.href = "cook.html";
    				}else if(username == "oljka"){
    					window.location.href = "barman.html";
    				}*/
    		
    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("Da li je ovdje problem");
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		});
    		
    	
    		}
    };
    
    
    
    
    document.getElementById('dugmeSign').onclick = function() {
    	
    					window.location.href= "signIn.html"
    	
    };
    
    
    
    
    
    
    
    
    
    
    
};
/*$(document).on('click','#dugmeLogovanje',function(e){
	e.preventDefault();
	
	var username = $("#username").val();
	var password = $("#password").val();
	if(username == ""){
		toastr.error("Username is empty");
	}
	else if(password == ""){
		toastr.error("Password is emptry");
	}
	else{
		$.ajax({
			type : 'POST',
			url : loginURL,
			contentType : 'application/json',
			dataType : 'text', 
			data : formToJSON(username, password),
			success : function(roleOfUser){
				if(roleOfUser == "false"){
					alert("Wrong username or password");
					window.location.href= "LogIn.html"
				}
				else if(roleOfUser == "admin"){
					
					window.location.href = "AdminHome.html";
				}
				else if(roleOfUser == "customer"){
					
					window.location.href = "CustomerHome.html";
				}
				else{ //seller
					
					window.location.href = "SellerHome.html";
				}
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		});
		
	
		alert("To");
		}
	
});


function formToJSON(username,password){
	return JSON.stringify({
		"username" : username,
		"password" : password
	});
}*/
