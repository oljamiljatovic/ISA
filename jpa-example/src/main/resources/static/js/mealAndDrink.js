$(document).on('click','#pica',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<table id="tabelaPrikaz" ><tr><th>ID</th><th>NAZIV</th><th>CENA</th></tr>'+
			'<th></th><th></th></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){
				$('#tabelaPrikaz').append('<tr><td>'+pice.id+'</td><td>'+pice.name+'</td><td>'+pice.price+'</td>'+
						'<td><form id="formIzbrisiPice" method="get" action=""><input type="submit" id="submitIzbrisiPice" value="Izbrisi"><input type="hidden" value='
							+pice.id+'></form></td><td><form id="formIzmeniPice" method="get" action="">'+
							'<input type="submit" value="Izmeni">' +
							'<input type="hidden" id="idIzmenaId" value='+pice.id+'>'+
							'<input type="hidden" id="idIzmenaName" value='+pice.name+'>'+
							'<input type="hidden" id="idIzmenaPrice" value='+pice.price+'>'+
							'</form></td></tr>');
				
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});

$(document).on('submit','#formIzbrisiPice',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[type=hidden]").val();
	var name = "";
	var price = 0;
	var data2 = JSON.stringify({
		"id" : id,
		"name" : name,
		"price" : price
	});
	$.ajax({
		type : 'POST',
		url :  '/mealAndDrinkController/deleteDrink',
		contentType : 'application/json',
		data : data2,
		success : function(){
			window.location.reload();
			
			alert('Uspesno brisanje pica!');
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});

$(document).on('submit','#formIzmeniPice',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='idIzmenaId']").val();
	var name = $(this).find("input[id='idIzmenaName']").val();
	var price = $(this).find("input[id='idIzmenaPrice']").val();
	
	$('#content').empty();
	
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form method="post">'+
		'Podaci o picu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imePica"  class="in-text"/><br/>'+
		'Cena:<input type = "text" id = "cenaPica" class="in-text"/><br/>'+
		'<input type = "submit" id = "submitIzmeniPice" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
	
	$('#imePica').val(name);
	$('#cenaPica').val(price);
	var data2 = JSON.stringify({
		"id" : id,
		"name" : name,
		"price" : price
	});
});
