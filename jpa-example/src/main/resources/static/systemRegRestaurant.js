$(document).ready(function(){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/registerController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){
				$('#izborPica').append('<input type="checkbox" id = "' +pice[0] +'" value="' + pice[1] + '">' + pice[1]);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
});

$(document).on('submit','#registracijaRestorana',function(e){
	e.preventDefault();
	var name = $('#imeRestorana').val();
	if(name == ""){
		alert("Ime je prazno");
	}else if($("input[type=checkbox]:checked").length==0){
		alert("Morate uneti pice");
	}else{
		var data2 = JSON.stringify({
			"name" : name,
		});
		$.ajax({
			type : 'POST',
			url :  '/registerController/registerRestaurant',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
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