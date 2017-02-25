$(document).ready(function() {
	$("#content").empty();
	$.ajax({
		type : 'GET',
		url :  '/reservationController/getReservations',
		contentType : 'application/json',
		dataType :'json',
		success : function(data){
			  var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			  $("#content").append('<p><b>Moje posjete</b></p>');
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
		    			url :  '/restaurantManagerController/getRestaurant/'+reservation.idRestaurant,
		    			contentType : 'application/json',
		    			dataType :'json',
		    			success : function(data){
		    				  var restaurant = data.name;
		    				  var tables ="";
		    				  $.each(reservation.reservedTables, function(index2, reservedTables) {
		    					  tables+=reservedTables.id+",";
		    				  });
		    				  var tables = tables.substring(0,tables.length-1);
								var forma = $('<form method="post" class="reservationForm" action=""></form>');
						        var tr = $('<tr></tr>');
						        tr.append('<td align="center">' + reservation.id + '</td><td align="center">'+restaurant+
						        		'</td><td align="center">' + reservation.date + '</td><td align="center">'+reservation.time+
						        		'</td>');
						        forma.append('<input type="submit" id="rating" name='+ reservation.id+";"+ tables +' value="Ocjeni" class="btn green">');
						        var td = $('<td></td>');
						        td.append(forma);	        
						        tr.append(td);
						        $('#content').append(tr);

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
});
$(document).on('click','#rating',function(e){
	e.preventDefault();
	$("#content").empty();
	$(this).prop('disabled',true);
	$(this).css('color', 'gray');
	var zaSplit = $(this).attr('name');
	var splitovano  = zaSplit.split(";");
	var reservation_id = splitovano[0];
	var tables_ids = splitovano[1].split(",");
	/*$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/waiterController/getWorkSchedules',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,ws){
				
				var partsStart =ws.dateStart.split('-');
				var partsEnd =ws.dateEnd.split('-');
				Command: toastr["info"]("Smjena: "+ws.shift+", od: "+partsStart[2]+"."+partsStart[1]+"."+partsStart[0]+"."
						+" do: "+partsEnd[2]+"."+partsEnd[1]+"."+partsEnd[0]+".", "Raspored rada!")
				message();
				colorDate(partsStart[0],partsStart[1]-1,partsStart[2],partsEnd[0],partsEnd[1]-1,partsEnd[2]);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Calendar ERROR: " + errorThrown);
		}	
	});*/
	
});