


window.onload = function() {
	
   	$.ajax({
		type : 'GET',
		dataType : 'json',
		url :'/registerController/uzmiRestorane',
		success : function(data){

			//var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			//$.each(list, function(index,restoran){
				//$('#restoranMenadzera').append('<option value = "' +restoran.id +'" >' + restoran.name + '</option>');
			
				alert("Preuzmeo restorane"+ data.length);
				var table = document.getElementById("listaRestorana");
				$('#listaRestorana').empty();
				for(var i = 0 ; i < data.length ; i++){
					
					 var item = data[i];
					// var row = table.insertRow(i);
					 
					 
					 $('#listaRestorana').append('<td align="center" valign="center">' +
					 		'<img src="rest1.jpg" alt="description here"  height="200" width="200"/>' +
					 		'<br />' +
					 		'<input type = "button" onclick="OtvoriRestoran()" id ="'+data[i].id+'" value="'+data[i].name+'"> ' +
					 		'<br /><br />'+
					 		'<input type = "button" onclick="PronadjiRestoran()" id ="'+data[i].id+'" value="'+data[i].address+'"> '+
					 		'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' + 
					 		'</td>')
					
				}
				
			
		},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		alert("Problem sa pronalazenjem id-ja");
	}	
	});//kraj ajax poziva za id
};


function OtvoriRestoran(){
	
    
    var idRestorana = OtvoriRestoran.caller.arguments[0].target.id; //njega dodajem
  	alert("Id otvorenog restorana"+ idRestorana);
   

	/*$.ajax({
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
*/


}



