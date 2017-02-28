function message(){
	toastr.options = {
			  "closeButton": false,
			  "debug": false,
			  "newestOnTop": false,
			  "progressBar": false,
			  "positionClass": "toast-top-right",
			  "preventDuplicates": false,
			  "onclick": null,
			  "showDuration": "100",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
}
function showOrders(){
	$("#content").empty();
	$.ajax({
		type : 'GET',
		url :  '/orderController/getOrdersForRestaurant',
		contentType : 'application/json',
		dataType :'json',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$("#content").append('<br/><br/><p><b>Poručena pića</b></p><br/>');
			$("#content").append("<table id='tableOrder'>");
		      $("#content").append("<thead>");
		      $("#content").append("<tr>");
		      $("#content").append("<th>Sto</th>");
		      $("#content").append("<th>Pića</th>");
		      $("#content").append("<th>&nbsp;</th>");
		      $("#content").append("<th>&nbsp;</th>");
		      $("#content").append("</tr>");
		      $("#content").append("</thead>");
		      $("#content").append("<tbody>");
		      $.each(list, function(index, order) {
			    	  	var drinks = "";
		    	  		$.each(order.drinks, function(index, drink) {
		    	  			drinks +=drink.name+",";
		    	  		});
		    	  		var drinks = drinks.substring(0,drinks.length-1);
		    	  		if(drinks != ""){
							var desk = order.tablee.id;
					        var forma = $('<form method="post" class="orderForm" action=""></form>');
							var formaSignal = $('<form method="post" class="signalDrink" action=""></form>');
					        var tr = $('<tr></tr>');
					        tr.append('<td align="center">' + desk + '</td><td align="center">' + drinks + '</td>');
					        forma.append('<input type="hidden" name="acceptDrink" id='+index+' value="'+ desk+";"+drinks+";"+order.id+'">' +
					                '<input type="submit" id="acceptDrink" name='+index+' value="Prihvati za spremanje" class="btn green">');
					        var td = $('<td></td>');
					        td.append(forma);
					        formaSignal.append('<input type="hidden" name="signalDrink" id='+index+' value="'+ desk+";"+drinks+";"+order.id+'">' +
					                '<input type="submit" id="signalDrink" name='+index+' value="Gotovo piće" class="btn green">');
					        var tdSignal = $('<td></td>');
					        tdSignal.append(formaSignal);
					        tr.append(td);
					        tr.append(tdSignal);
					        $('#content').append(tr);
					        if(order.barman_state=="preuzeo_sanker"){
					        	$('input[id="acceptDrink"][name='+index+']').attr('disabled','disabled');
					        	$('input[id="acceptDrink"][name='+index+']').css('color','gray');
					        	$('input[id="signalDrink"][name='+index+']').attr('disabled',false);
					        	$('input[id="signalDrink"][name='+index+']').css('color','black');
					        }else if(order.barman_state=="gotovo_pice"){
					        	$('input[id="acceptDrink"][name='+index+']').attr('disabled','disabled');
					        	$('input[id="acceptDrink"][name='+index+']').css('color','gray');
					        	$('input[id="signalDrink"][name='+index+']').attr('disabled','disabled');
					        	$('input[id="signalDrink"][name='+index+']').css('color','gray');
					        }else if(order.barman_state=="kreirana"){
					        	$('input[id="signalDrink"][name='+index+']').attr('disabled','disabled');
					        	$('input[id="signalDrink"][name='+index+']').css('color','gray');
					        }
		    	  		}
				});
	
			  $("#content").append("</tbody>");
			  $("#content").append("</table>");
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}
$(document).ready(function() {
	var messageList = $("#messages");
	var socket = new SockJS('/stomp');
	var stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		stompClient.subscribe("/topic/newOrder", function(data) {
			var mess = data.body;
			//messageList.append("<li>" + mess + "</li>");
			Command: toastr["info"](mess, "Informacija!")
			message();
			if( $('#tableOrder').length ){
				$('#content').empty();
				showOrders();
			}
		});
	});
	$.ajax({
		type: 'GET',
		dataType: 'text',
		url : '/userController/checkSession',
		success : function(data){
			if(data=="notOk"){
				window.location.href= "index.html";
			}else{
				$.ajax({
					type: 'GET',
					dataType: 'json',
					url : '/waiterController/getEmployee',
					success : function(employee){
							if(employee.firstLog=="true"){
								$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
										'<div class="login-page wrapper centered centered-block">'+ 
										'<div class = "form-group"><form id="submitFirstLog" method="post">'+
										'Postavite lozinku:<br/><br/>'+
										'Nova lozinka:<br/><input type = "password" id = "newPassword"  class="in-text"/><br/>'+
										'Ponovite lozinku:<br/><input type = "password" id = "repeatPassword"  class="in-text"/><br/>'+
										'<input type = "submit" value="Submit" class="btn orange">'+
										'<input type="hidden" id="employeeId" value='+employee.id+'>'+
										'</form></div></div></div></div>');
							}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("First log ERROR: " + errorThrown);
					}	
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("checkSession: " + errorThrown);
		}	
	});
});
$(document).on('submit','#submitFirstLog',function(e){
	e.preventDefault();
	var id = $(this).find("input[id='employeeId']").val();
	var password = $('#newPassword').val();
	var checkPassword = $('#repeatPassword').val();
	if(password.trim() == ""){
		Command: toastr["error"]("Morate unijeti lozinku.", "Greška!")
		message();
	}else if(checkPassword.trim() == ""){
		Command: toastr["error"]("Morate ponoviti lozinku.", "Greška!")
		message();
	}else if(password!=checkPassword){
			Command: toastr["error"]("Lozinke nisu iste.", "Greška!")
			message();
	}else{
		
		var employeeData = JSON.stringify({
			"id" : id,
			"name" : "",
			"surname" : "",
			"dateBirth" : "",
			"confNumber" : "",
			"shoeNumber" : "",
			"restaurant" : null,
			"firstLog" : "false",
			"password" : password,
			"email" : "",
			"role" : "",
			"accept" : ""
		});
		
		$.ajax({
			type : 'PUT',
			url :  '/waiterController/updateFirstLog',
			contentType : 'application/json',
			dataType : 'json',
			data : employeeData,
			success : function(data){
				Command: toastr["success"]("Uspješno su ažurirani podaci.", "Odlično!")
				message();
				$('#content').empty();
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		});
	}
});
$(document).on('click','#calendar',function(e){
	e.preventDefault();
	$("#content").empty();
	$.ajax({
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
	});
	
});
function colorDate(yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd){
	$('#content').append('</br>');
	$('#content').calendar({ 
		/*selectRange: function(e) {
            editEvent({ startDate: e.startDate, endDate: e.endDate });
        },
		mouseOnDay: function(e) {
            if(e.events.length > 0) {
                var content = '';
                
                for(var i in e.events) {
                    content += '<div class="event-tooltip-content">'
                                    + '<div class="event-name" style="color:' + e.events[i].color + '">' + e.events[i].name + '</div>'
                                    + '<div class="event-location">' + e.events[i].location + '</div>'
                                + '</div>';
                }
            
                $(e.element).popover({ 
                    trigger: 'manual',
                    container: 'body',
                    html:true,
                    content: content
                });
                
                $(e.element).popover('show');
            }
        },
        mouseOutDay: function(e) {
            if(e.events.length > 0) {
                $(e.element).popover('hide');
            }
        },*/
        dataSource: [
                     {
                         id: 0,
                         name: 'Google I/O',
                         location: 'San Francisco, CA',
                         startDate: new Date(yearStart, monthStart, dayStart),
                         endDate: new Date(yearEnd, monthEnd, dayEnd)
                     }
                 ]
    });
}
$(document).on('click','#updateProfile',function(e){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/waiterController/getEmployee',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,employee){
				$('#content').empty();
				$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
						'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
							'<form method="post" id="updateForm">'+
								'Moji podaci:<br/><br/>Ime:<input type = "text" id = "name" value="'+employee.name+'" class="in-text"/>'+
								'<br/>Prezime:<input type = "text" id = "surname" value="'+employee.surname+'" class="in-text"/>'+
								'<br/>Email:<input type = "text" id = "email" value="'+employee.email+'" class="in-text"/>'+
								'<br/>Datum rodjenja:<input type = "date" id = "date" value="'+employee.dateBirth+'" class="in-text"/>'+
								'<br/>Konfekcijski broj:'+
								'<select id="conf"><option value="XS">XS</option>'+
								'<option value="S">S</option><option value="M">M</option>'+
								'<option value="L">L</option><option value="XL">XL</option>'+
								'<option value="XXL">XXL</option></select>'+
								'<br/>Veličina obuće:<input type = "text" id = "shoes" value="'+employee.shoeNumber+'"  class="in-text"/>'+
								'<br/>Nova lozinka:<br/><input type = "password" id = "newPass"  value="'+employee.password+'" class="in-text"/>'+
								'<br/>Ponovi lozinku:<br/><input type = "password" id = "repeatPass" value="'+employee.password+'" class="in-text"/>'+
								'<br/><br/><input type = "submit" id = "submitUpdateProfile" value="Potvrdi" class="btn orange">'+
								'</form></div></div></div></div>');
				$('#name').attr('disabled','disabled');
				$('#surname').attr('disabled','disabled');
				$('#conf').val(employee.confNumber);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Profile ERROR: " + errorThrown);
		}	
	});
	
});

$(document).on('click','#submitUpdateProfile',function(e){
	e.preventDefault();
	var name = $('#name').val();
	var surname = $('#surname').val();
	var dateBirth = $('#date').val();
	var email = $('#email').val();
	var confNumber = $('#conf option:selected').val();
	var password = $('#newPass').val();
	var repeatPassword = $('#repeatPass').val();
	var shoeNumber = $('#shoes').val();
	var employeeData = JSON.stringify({
		"name" : name,
		"surname" : surname,
		"dateBirth" : dateBirth,
		"confNumber" : confNumber,
		"shoeNumber" : shoeNumber,
		"restaurant" : null,
		"firstLog" : "false",
		"password" : password,
		"email" : email,
		"role" : "waiter",
		"accept" : "true"
	});
	
	if(name == ""){
		Command: toastr["error"]("Ime je prazno.", "Greška!")
		message();
	}else if(surname == ""){
		Command: toastr["error"]("Prezime je prazno.", "Greška!")
		message();
	}else if(shoeNumber == ""){
		Command: toastr["error"]("Veličine obuće je prazno.", "Greška!")
		message();
	}else if(email == ""){
		Command: toastr["error"]("Email je prazno.", "Greška!")
		message();
	}else if(repeatPassword != password){
		Command: toastr["error"]("Lozinke nisu iste.", "Greška!")
		message();
	}else{
		$.ajax({
			type : 'PUT',
			url :  '/waiterController/update',
			contentType : 'application/json',
			dataType : 'json',
			data : employeeData,
			success : function(data){
				Command: toastr["success"]("Uspješno su ažurirani podaci.", "Odlično!")
				message();
		$('#content').empty();
			},

			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("AJAX ERROR: " + errorThrown);
			}
		});
	}
});
$(document).on('click','#orderedDrinks',function(e){
	showOrders();
});

$(document).on('click', '#acceptDrink', function(e) {
	e.preventDefault();
	$(this).prop('disabled',true);
	$(this).css('color', 'gray');
	var name = $(this).attr('name');
	var zaSplit;
	var id;
	$(document).find('input[name="acceptDrink"]').each(function(e){	
		  id = this.id;
		 if(name == id ){
			 zaSplit = this.value;
		 }
	});
	var splitovano  = zaSplit.split(";");
	var desk = splitovano[0];
	var drinks = splitovano[1].split(",");
	var order_id = splitovano[2];
	var meals = [];
	$.ajax({
		type : 'POST',
		url :  '/acceptDrink',
		data : {
			"acceptDrink" : " prihvatio je porudžbinu za sto "+desk+"."
		},
		success : function(data){	
			$.ajax({
				type : 'PUT',
				url :  '/orderController/change/'+order_id,
				contentType : 'application/json',
				dataType :'json',
				data : JSON.stringify({
					"waiter_id" : "1",
					"table_id" : desk,
					"restaurant" : "1",
					"barman_state" : "preuzeo_sanker",
					"cook_state" : "kreirana",
					"drinks" : drinks,
					"meals" : meals
				}),
				success : function(data){	
					//Command: toastr["success"]("preuzeo_sanker.", "Odlično!")
					//message();
					showOrders();
				},

				error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
					alert("preuzeo_sanker ERROR: " + errorThrown);
				}
			});
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("acceptMeal ERROR: " + errorThrown);
		}
	});
});
$(document).on('click', '#signalDrink', function(e) {
	e.preventDefault();
	$(this).prop('disabled',true);
	$(this).css('color', 'gray');
	var name = $(this).attr('name');
	var zaSplit;
	$(document).find('input[name="signalDrink"]').each(function(e){	
		  var id = this.id;
		 if(name == id ){
			 zaSplit = this.value;
		 }
	});
	var splitovano  = zaSplit.split(";");
	var desk = splitovano[0];
	var drinks = splitovano[1].split(",");
	var order_id = splitovano[2];
	var meals = [];
	$.ajax({
		type : 'POST',
		url :  '/signalDrink',
		data : {
			"signalDrink" : "Gotovo je piće za sto "+desk+"!"
		},
		success : function(data){	
			$.ajax({
				type : 'PUT',
				url :  '/orderController/change/'+order_id,
				contentType : 'application/json',
				dataType :'json',
				data : JSON.stringify({
					"waiter_id" : "1",
					"table_id" : desk,
					"restaurant" : "1",
					"barman_state" : "gotovo_pice",
					"cook_state" : "kreirana",
					"drinks" : drinks,
					"meals" : meals
				}),
				success : function(data){	
					//Command: toastr["success"]("gotovo_pice", "Odlično!")
					//message();
				},

				error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
					alert("AJAX ERROR: " + errorThrown);
				}
			});
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("signalDrink ERROR: " + errorThrown);
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

