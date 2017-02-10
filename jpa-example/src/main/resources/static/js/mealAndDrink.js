$(document).on('click','#pica',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<table id="tabelaPrikaz" ><tr><th>ID</th><th>NAZIV</th><th>OPIS</th>'+
			'<th>CENA</th><th></th><th></th>/tr></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){
				$('#tabelaPrikaz').append('<tr><td>'+pice.id+'</td><td>'+pice.name+'</td><td>'+pice.description+'</td><td>'+pice.price+'</td>'+
						'<td><form id="formIzbrisiPice" method="get" action=""><input type="submit" id="submitIzbrisiPice" value="Izbrisi"><input type="hidden" value='
							+pice.id+'></form></td><td><form id="formIzmeniPice" method="get" action="">'+
							'<input type="submit" value="Izmeni">' +
							'<input type="hidden" id="idIzmenaId" value='+pice.id+'>'+
							'<input type="hidden" id="idIzmenaName" value='+pice.name+'>'+
							'<input type="hidden" id="idIzmenaOpis" value='+pice.description+'>'+
							'<input type="hidden" id="idIzmenaPrice" value='+pice.price+'>'+
							'<input type="hidden" id="idIzmenaRestoran" value='+pice.restaurant+'>'+
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
	var restaurant = "";
	var description = "";
	var data2 = JSON.stringify({
		"id" : id,
		"name" : name,
		"price" : price,
		"restaurant" : restaurant,
		"description" : description
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
	var opis = $(this).find("input[id='idIzmenaOpis']").val();
	var restoran = $(this).find("input[id='idIzmenaRestoran']").val();
	
	$('#content').empty();
	
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form id="submitIzmenaPica" method="post">'+
		'Podaci o picu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imePica"  class="in-text"/><br/>'+
		'Opis:<input type = "text" id = "opisPica"  class="in-text"/><br/>'+
		'Cena:<input type = "text" id = "cenaPica" class="in-text"/><br/>'+
		'<input type = "submit" value="Submit" class="btn orange">'+
		'<input type="hidden" id="idIzmenaId" value='+id+'>'+
		'<input type="hidden" id="idIzmenaName" value='+name+'>'+
		'<input type="hidden" id="idIzmenaPrice" value='+price+'>'+
		'<input type="hidden" id="idIzmenaOpis" value='+opis+'>'+
		'<input type="hidden" id="idIzmenaRestoran" value='+restoran+'>'+
		'</form></div></div></div></div>');
	
	
	$('#imePica').val(name);
	$('#cenaPica').val(price);
	$('#opisPica').val(opis);
});

$(document).on('submit','#submitIzmenaPica',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='idIzmenaId']").val();
	var name = $('#imePica').val();
	var price = $('#cenaPica').val();
	var restaurant = $(this).find("input[id='idIzmenaRestoran']").val();
	var description = $('#opisPica').val();
	
	if(name == ""){
		alert("Ime je prazno");
	}else if(price == ""){
		alert("Cena je prazna");
	}else{
		var data2 = JSON.stringify({
			"id" : id,
			"name" : name,
			"price" : price,
			"restaurant" : restaurant,
			"description" : description
		});
		$.ajax({
			type : 'POST',
			url :  '/mealAndDrinkController/updateDrink',
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


$(document).on('click','#novoPice',function(e){
	e.preventDefault();
	
	$('#content').empty();
	
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form id="submitDodajPice" method="post">'+
		'Podaci o picu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imePica"  class="in-text"/><br/>'+
		'Opis:<input type = "text" id = "opisPica" class="in-text"/><br/>'+
		'Cena:<input type = "text" id = "cenaPica" class="in-text"/><br/>'+
		'<input type = "submit" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
});

$(document).on('submit','#submitDodajPice',function(e){
	e.preventDefault();
	
	var name = $('#imePica').val();
	var price = $('#cenaPica').val();
	var opis = $('#opisPica').val();
	var restoran = 0;
	if(name == ""){
		alert("Ime je prazno");
	}else if(price == ""){
		alert("Cena je prazna");
	}else{
		var data2 = JSON.stringify({
			"name" : name,
			"price" : price,
			"restaurant" : restoran,
			"description" : opis
		});
		$.ajax({
			type : 'POST',
			url :  '/mealAndDrinkController/addDrink',
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






$(document).on('click','#jelovnik',function(e){
	e.preventDefault();
	$('#content').empty();
	$('#content').append('<table id="tabelaPrikaz" ><tr><th>ID</th><th>NAZIV</th><th>OPIS</th>'+
			'<th>CENA</th><th></th><th></th>/tr></table>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiObroke',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,jelo){
				$('#tabelaPrikaz').append('<tr><td>'+jelo.id+'</td><td>'+jelo.name+'</td><td>'+jelo.description+'</td><td>'+jelo.price+'</td>'+
						'<td><form id="formIzbrisiJelo" method="get" action=""><input type="submit" id="submitIzbrisiJelo" value="Izbrisi"><input type="hidden" value='
							+jelo.id+'></form></td><td><form id="formIzmeniJelo" method="get" action="">'+
							'<input type="submit" value="Izmeni">' +
							'<input type="hidden" id="idIzmenaId" value='+jelo.id+'>'+
							'<input type="hidden" id="idIzmenaName" value='+jelo.name+'>'+
							'<input type="hidden" id="idIzmenaOpis" value='+jelo.description+'>'+
							'<input type="hidden" id="idIzmenaPrice" value='+jelo.price+'>'+
							'<input type="hidden" id="idIzmenaRestoran" value='+jelo.restaurant+'>'+
							'</form></td></tr>');
				
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
	
});

$(document).on('submit','#formIzbrisiJelo',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[type=hidden]").val();
	var name = "";
	var price = 0;
	var restaurant = "";
	var description = "";
	var data2 = JSON.stringify({
		"id" : id,
		"name" : name,
		"price" : price,
		"restaurant" : restaurant,
		"description" : description
	});
	$.ajax({
		type : 'POST',
		url :  '/mealAndDrinkController/deleteMeal',
		contentType : 'application/json',
		data : data2,
		success : function(){
			window.location.reload();
			
			alert('Uspesno brisanje jela!');
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});

$(document).on('submit','#formIzmeniJelo',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='idIzmenaId']").val();
	var name = $(this).find("input[id='idIzmenaName']").val();
	var price = $(this).find("input[id='idIzmenaPrice']").val();
	var opis = $(this).find("input[id='idIzmenaOpis']").val();
	var restoran = $(this).find("input[id='idIzmenaRestoran']").val();
	
	$('#content').empty();
	
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form id="submitIzmenaJelo" method="post">'+
		'Podaci o picu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imeJela"  class="in-text"/><br/>'+
		'Opis:<input type = "text" id = "opisJela"  class="in-text"/><br/>'+
		'Cena:<input type = "text" id = "cenaJela" class="in-text"/><br/>'+
		'<input type = "submit" value="Submit" class="btn orange">'+
		'<input type="hidden" id="idIzmenaId" value='+id+'>'+
		'<input type="hidden" id="idIzmenaName" value='+name+'>'+
		'<input type="hidden" id="idIzmenaPrice" value='+price+'>'+
		'<input type="hidden" id="idIzmenaOpis" value='+opis+'>'+
		'<input type="hidden" id="idIzmenaRestoran" value='+restoran+'>'+
		'</form></div></div></div></div>');
	
	
	$('#imeJela').val(name);
	$('#cenaJela').val(price);
	$('#opisJela').val(opis);
});

$(document).on('submit','#submitIzmenaJelo',function(e){
	e.preventDefault();
	
	var id = $(this).find("input[id='idIzmenaId']").val();
	var name = $('#imeJela').val();
	var price = $('#cenaJela').val();
	var restaurant = $(this).find("input[id='idIzmenaRestoran']").val();
	var description = $('#opisJela').val();
	
	if(name == ""){
		alert("Ime je prazno");
	}else if(price == ""){
		alert("Cena je prazna");
	}else{
		var data2 = JSON.stringify({
			"id" : id,
			"name" : name,
			"price" : price,
			"restaurant" : restaurant,
			"description" : description
		});
		$.ajax({
			type : 'POST',
			url :  '/mealAndDrinkController/updateMeal',
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


$(document).on('click','#novoJelo',function(e){
	e.preventDefault();
	
	$('#content').empty();
	
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form id="submitDodajJelo" method="post">'+
		'Podaci o picu:<br/><br/>'+
		'Naziv:<input type = "text" id = "imeJela"  class="in-text"/><br/>'+
		'Opis:<input type = "text" id = "opisJela" class="in-text"/><br/>'+
		'Cena:<input type = "text" id = "cenaJela" class="in-text"/><br/>'+
		'<input type = "submit" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
});

$(document).on('submit','#submitDodajJelo',function(e){
	e.preventDefault();
	
	var name = $('#imeJela').val();
	var price = $('#cenaJela').val();
	var opis = $('#opisJela').val();
	var restoran = 0;
	if(name == ""){
		alert("Ime je prazno");
	}else if(price == ""){
		alert("Cena je prazna");
	}else{
		var data2 = JSON.stringify({
			"name" : name,
			"price" : price,
			"restaurant" : restoran,
			"description" : opis
		});
		$.ajax({
			type : 'POST',
			url :  '/mealAndDrinkController/addMeal',
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