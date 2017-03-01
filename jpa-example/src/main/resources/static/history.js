$(document).ready(function() {
	showHistory();
});


$(document).on('click','#restorani',function(e){
	window.location.href= "restaurants.html";
	
});

$(document).on('click','#prijatelji',function(e){
	window.location.href= "friendsOfGuest.html";
	
});

$(document).on('click','#mojNalog',function(e){
	window.location.href= "userProfile.html";
	
});




function showHistory(){
	$("#content").empty();
	$.ajax({
		type : 'GET',
		url :  '/reservationController/getReservations',
		contentType : 'application/json',
		dataType :'json',
		success : function(data){
			  var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			  $("#content").append('<br/><br/><p><b>Moje posjete</b></p><br/>');
			  $("#content").append("<table id='reservation'>");
		      $("#content").append("<thead>");
		      $("#content").append("<tr>");
		      $("#content").append("<th>Broj</th>");
		      $("#content").append("<th>Restorana</th>");
		      $("#content").append("<th>Datum</th>");
		      $("#content").append("<th>Vrijeme</th>");
		      $("#content").append("<th>&nbsp;</th>");
		      $("#content").append("</tr>");
		      $("#content").append("</thead>");
		      $("#content").append("<tbody>");
		      
		      $.each(list, function(index, reservation) {
		    		$.ajax({
		    			type : 'GET',
		    			url :  '/restaurantManagerController/getRestaurant/'+reservation.idRestaurant.id,
		    			contentType : 'application/json',
		    			dataType :'json',
		    			success : function(data){
		    				  var restaurant = data.name;
								var forma = $('<form method="post" class="reservationForm" action=""></form>');
						        var tr = $('<tr></tr>');
						        tr.append('<td align="center">' + reservation.id + '</td><td align="center">'+restaurant+
						        		'</td><td align="center">' + reservation.date + '</td><td align="center">'+reservation.time+
						        		'</td>');
						        forma.append('<input type="submit" id="rating" name='+ reservation.id+";"+ reservation.idRestaurant.id +' value="Ocjeni" class="btn green">');
						        var td = $('<td></td>');
						        td.append(forma);	        
						        tr.append(td);
						        $('#content').append(tr);
					    		$.ajax({
					    			type : 'GET',
					    			url :  '/ratingAllController/checkRating/'+reservation.id,
					    			dataType :'json',
					    			success : function(data){
					    				  if(data!=null){
					    					  	$('input[id="rating"][name="'+ reservation.id+";"+ reservation.idRestaurant.id +'"]').attr('disabled','disabled');
					  			        		$('input[id="rating"][name="'+ reservation.id+";"+ reservation.idRestaurant.id +'"]').css('color','gray'); 
					    				  }
					    			},

					    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
					    				alert("checkRating ERROR: " + errorThrown);
					    			}
					    		});

		    			},

		    			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
		    				alert("Reservations restaurant ERROR: " + errorThrown);
		    			}
		    		});
						
		      });
			  $("#content").append("</tbody>");
			  $("#content").append("</table>");

		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("Reservations ERROR: " + errorThrown);
		}
	});
}
$(document).on('click','#rating',function(e){
	e.preventDefault();
	$("#content").empty();
	$(this).prop('disabled',true);
	$(this).css('color', 'gray');
	var reservationAndRestId = $(this).attr('name');

	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
			'<div class="login-page wrapper centered centered-block">'+ 
			'<div class = "form-group"><form id="ratingForm" method="post">'+
			'Ocjenjivanje posjete:<br/>'+
			'Ocjena restorana:<select id="comboRest">'+
			'<option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select><br/><br/>'+
			'Ocjena usluge:   <select id="comboService">'+
			'<option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select><br/><br/>'+
			'Ocjena jela:     <select id="comboMeal">'+
			'<option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select><br/><br/>'+
			'<input type = "submit" value="Potvrdi" name="'+reservationAndRestId+'" id="ratingSubmit" style="width:100px" class="btn orange">'+
			'</form></div></div></div></div>');	
});
$(document).on('click','#ratingSubmit',function(e){
	e.preventDefault();
	var rest = $('#comboRest option:selected').val();
	var service = $('#comboService option:selected').val();
	var meal = $('#comboMeal option:selected').val();
	var reservationAndRestId = $(this).attr('name');
	var reservationId = reservationAndRestId.split(";")[0];
	var restaurantId = reservationAndRestId.split(";")[1];
	var restaurant = JSON.stringify({
		"id" : restaurantId,
		"name" : "",
		"type" : "",
		"address" : "",
		"contact" : ""
	});
	var objRestaurant = JSON.parse(restaurant);
	
	var guest = JSON.stringify({
		"id" : "1",
		"name" : "",
		"surname" : "",
	});
	var objGuest = JSON.parse(guest);
	
	var reservation = JSON.stringify({
		"id" : reservationId,
		"idGuest" : null,
		"idRestaurant" : null,
		"date" : "",
		"time" : "",
		"duration" : "0"
	});
	var objReservation = JSON.parse(reservation);
	
	$.ajax({
		type : 'POST',
		url :  '/ratingAllController/addRating',
		contentType : 'application/json',
		dataType : 'json',
		data : JSON.stringify({
			"guest" : objGuest,
			"reservation" : objReservation,
			"restaurant" : objRestaurant,
			"waiter" : null,
			"meals" : null,
 			"restaurantRating" : rest,
			"serviceRating" : service,
			"mealRating" : meal
		}),
		success : function(data){
			if(data==null){
				Command: toastr["error"]("Nemate pravo ocjenjivanja jer još ništa niste naručili.", "Greška!")
				//message();
				showHistory();
			}else{
				Command: toastr["success"]("Uspješno ste ocjenili.", "Odlično!")
				//message();
				showHistory();
			}
		},
	
		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("addRating ERROR: " + errorThrown);
		}
	});

});

$(document).on('click', '#logout', function(e) {
	e.preventDefault();
	$("#content").empty();
	$.ajax({
		type: 'GET',
		dataType: 'text',
		url : '/userController/logout',
		success : function(data){
			window.location.href= "index.html";
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("billOrders: " + errorThrown);
		}	
	});
});