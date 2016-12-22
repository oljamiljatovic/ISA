$(document).on('submit','#registracijaMenadzera',function(e){
	e.preventDefault();
	//var id = $('#idMenadzera').val();
	var name = $('#imeMenadzera').val();
	var dataa = JSON.stringify({
		//"id" : id,
		"name" : name
	});
	
	/*if(id == ""){
		toastr.error("Id je prazan");
	}
	else*/ if(name == ""){
		toastr.error("Ime je prazno");
	}
	else{
		$.ajax({
			type : 'POST',
			url :  '/registerController/registerManager',
			contentType : 'application/json',
			dataType : 'json',
			data : dataa,
			success : function(data){
				alert(data.id);
				window.location.reload();
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		});
	}
});