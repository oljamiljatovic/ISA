var roleURL = "../WebProjekat/rest/users/role";

window.onload = function() {
    document.getElementById('dugmeLogovanje').onclick = function() {
    	var username = document.getElementById('username').value;
    	var password = document.getElementById('password').value;
    	if(username == ""){
    		toastr.error("Username is empty");
    	}
    	else if(password == ""){
    		toastr.error("Password is emptry");
    	}
    	else{
    		$.ajax({
    			type : 'POST',
    			url :  '/loginController/login',
    			contentType : 'application/json',
    			data : formToJSON(username, password),
    			success : function(){
    			/*	if(roleOfUser == "false"){
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
    				}*/
    			},

    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
    				alert("AJAX ERROR: " + errorThrown);
    			}
    		});
    		
    	
    		alert("To");
    		}
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
function formToJSON(username,password){
	return JSON.stringify({
		"username" : username,
		"password" : password
	});
}