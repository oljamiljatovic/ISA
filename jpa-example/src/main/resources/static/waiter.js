//http://www.bootstrap-year-calendar.com/#Download
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
		url :  '/orderController/getOrders',
		contentType : 'application/json',
		dataType :'json',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$("#content").append("</br><button id='addOrder' class='btn orange'>Dodaj porudžbinu</button>");
			$("#content").append('<p><b>Lista porudžbina</b></p>');
			$("#content").append("<table id='tableOrder'>");
		      $("#content").append("<thead>");
		      $("#content").append("<tr>");
		      $("#content").append("<th>Sto</th>");
		      $("#content").append("<th>Pića</th>");
		      $("#content").append("<th>Priprema pića</th>");
		      $("#content").append("<th>Jela</th>");
		      $("#content").append("<th>Priprema jela</th>");
		      $("#content").append("<th>&nbsp;</th>");
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
						var drinkStatus = "nema";
						if(drinks == ""){
							drinks = "nema";
						}else{
							if(order.barman_state=="preuzeo_sanker"){
								drinkStatus = "u toku";
							}else if(order.barman_state=="gotovo_pice"){
								drinkStatus = "gotovo";
							}else{
								drinkStatus = "nepreuzeto";
							}
						}
						var meals = "";
						$.each(order.meals, function(index, meal) {
							meals +=meal.name+",";
		    	  		});
						var meals = meals.substring(0,meals.length-1);
						var mealStatus = "nema";
						if(meals == ""){
							meals = "nema";
						}else{
							if(order.cook_state=="preuzeo_kuvar"){
								mealStatus = "u toku";
							}else if(order.cook_state=="gotovo_jelo"){
								mealStatus = "gotovo";
							}else{
								mealStatus = "nepreuzeto";
							}
						}
						
						var desk = order.table_id;
						var forma = $('<form method="post" class="orderForm" action=""></form>');
						var formaBill = $('<form method="post" class="orderBill" action=""></form>');
						var formaAdd = $('<form method="post" class="orderAdd" action=""></form>');
				        var tr = $('<tr></tr>');
				        tr.append('<td align="center">' + desk + '</td><td align="center">'+drinks+'</td><td align="center">'+drinkStatus+
				        		'</td><td align="center">' + meals + '</td><td align="center">'+mealStatus+
				        		'</td>');
				        forma.append('<input type="hidden" name="edit" id='+index+' value="'+ desk+";"+drinks+";"+meals +";"+order.id +'">' +
				                '<input type="submit" id="submitEdit" name='+index+' value="Izmjeni" class="btn green">');
				        var td = $('<td></td>');
				        td.append(forma);
				        formaAdd.append('<input type="hidden" name="add" id='+index+' value="'+ desk+";"+drinks+";"+meals +";"+order.id +'">' +
				                '<input type="submit" id="add" name='+index+' value="Dodaj na porudžbinu" class="btn green">');
				        var tdAdd = $('<td></td>');
				        tdAdd.append(formaAdd);
				        formaBill.append('<input type="hidden" name="bill" id='+index+' value="'+ desk+";"+drinks+";"+meals +";"+order.id +'">' +
				                '<input type="submit" id="bill" name='+index+' value="Kreiraj račun" class="btn green">');
				        var tdBill = $('<td></td>');
				        tdBill.append(formaBill);
				        tr.append(td);
				        tr.append(tdAdd);
				        tr.append(tdBill);
				        $('#content').append(tr);
				        $('input[id="add"][name='+index+']').attr('disabled','disabled');
			        	$('input[id="add"][name='+index+']').css('color','gray');
			        	$('input[id="bill"][name='+index+']').attr('disabled','disabled');
				        $('input[id="bill"][name='+index+']').css('color','gray');
				        if(mealStatus=="nema" && drinkStatus=="nema"){
				        }else if(mealStatus=="nepreuzeto" && drinkStatus=="nema"){
				        	
				        }else if(mealStatus=="nema" && drinkStatus=="nepreuzeto"){
				        	
				        }else if(mealStatus!="nepreuzeto" || drinkStatus!="nepreuzeto"){
				        	$('input[id="submitEdit"][name='+index+']').attr('disabled','disabled');
				        	$('input[id="submitEdit"][name='+index+']').css('color','gray');
				        	$('input[id="add"][name='+index+']').attr('disabled',false);
					        $('input[id="add"][name='+index+']').css('color','black');
				        }
				        if(mealStatus=="gotovo" && drinkStatus=="gotovo"){
				        	$('input[id="bill"][name='+index+']').attr('disabled',false);
					        $('input[id="bill"][name='+index+']').css('color','black');
				        }else if(mealStatus=="gotovo" && drinkStatus=="nema"){
				        	$('input[id="bill"][name='+index+']').attr('disabled',false);
					        $('input[id="bill"][name='+index+']').css('color','black');
				        }else if(mealStatus=="nema" && drinkStatus=="gotovo"){
				        	$('input[id="bill"][name='+index+']').attr('disabled',false);
					        $('input[id="bill"][name='+index+']').css('color','black');
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
		stompClient.subscribe("/topic/acceptMeal", function(data) {
			var mess = data.body;
			//messageList.append("<li>" + mess + "</li>");
			Command: toastr["info"](mess, "Informacija!")
			message();
			if( $('#tableOrder').length ){
				showOrders();
			}
		});
		stompClient.subscribe("/topic/signalMeal", function(data) {
			var mess = data.body;
			Command: toastr["info"](mess, "Informacija!")
			if( $('#tableOrder').length ){
				showOrders();
			}
			
		});
		stompClient.subscribe("/topic/signalDrink", function(data) {
			var mess = data.body;
			Command: toastr["info"](mess, "Informacija!")
			message();
			if( $('#tableOrder').length ){
				
				showOrders();
			}
		});
		stompClient.subscribe("/topic/acceptDrink", function(data) {
			var mess = data.body;
			Command: toastr["info"](mess, "Informacija!")
			message();
			if( $('#tableOrder').length ){
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
			"restaurant" : "1",
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
	billValues=[];
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
$(document).on('click','#tables',function(e){
	e.preventDefault();
	$("#content").empty();
	$("#content").append("<br/>");
	$("#content").append("<br/>");
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/waiterController/getTablesForRestaurant',
		success : function(data){
			var tables = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/waiterController/getReons',
				success : function(data){
					var reons = data == null ? [] : (data instanceof Array ? data : [ data ]);
					
					$.each(reons, function(index,reon){
						//if(reon.name=="North" ){
							var numberTable = reon.numberTable;
							var  x= numberTable/5;
							var iter = 0;
							var iterNum = parseInt(x, 10);
							var btnNum = 0;
							while (iter < iterNum) {
								var i=0;
								while (i < 5) {
									$("#content").append("<button class='"+reon.name+"' id='"+reon.name+btnNum+"' style='height:50px;width:90px'></button>");
									i++;
									btnNum++;
								}
								$("#content").append("<br/>");
								iter++;
							}
							if(x-iterNum!=0){
								var i=0;
								while (i < 5) {
									$("#content").append("<button class='"+reon.name+"' id='"+reon.name+btnNum+"' style='height:50px;width:90px'></button>");
									i++;
									btnNum++;
								}
								$("#content").append("<br/>");
							}
						//}
					});
					$.each(reons, function(index1,reon){
						var btnIndex = 0;
						$.each(tables, function(index2,table){
							if(reon.id==table.reon){
								var string = "#"+reon.name+btnIndex;
								$("#"+reon.name+btnIndex).text(table.id);
								btnIndex++;
							}
						});
					});
					$.ajax({
						type: 'GET',
						dataType: 'json',
						url : '/waiterController/getAssignReons',
						success : function(data){
							var assignReons = data == null ? [] : (data instanceof Array ? data : [ data ]);
							$.each(assignReons, function(index1,assignReon){
								$.each(reons, function(index2,reon){
									if(reon.id==assignReon.reon_id){
										Command: toastr["info"]("Reon: "+reon.name, "Dodjeljeni reon!")
										message();
										$("button."+reon.name).css("background-color", "blue");
									}
								});
							});
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("AssignReon ERROR: " + errorThrown);
						}	
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("REON ERROR: " + errorThrown);
				}	
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Tables ERROR: " + errorThrown);
		}	
	});
	
});

$(document).on('click','#order',function(e){
	showOrders();
});
$(document).on('click', '#add', function(e) {
	e.preventDefault();
	var name = $(this).attr('name');
	var zaSplit;
	$(document).find('input[name="add"]').each(function(e){	
		  var id = this.id;
		 if(name == id ){
			 zaSplit = this.value;
		 }
	});
	var splitovano  = zaSplit.split(";");
	var desk = splitovano[0];
	var drinks = splitovano[1].split(",");
	var meals = splitovano[2].split(",");
	var order_id = splitovano[3];
	var allDrinks = [];
	var allMeals = [];
	$("#content").empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,pice){
				allDrinks.push(pice.name);
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/mealAndDrinkController/uzmiObroke',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(list, function(index,obrok){
						allMeals.push(obrok.name);
					});
					$('#content').empty();
					$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
							'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
								'<form method="post" id="updateForm">'+
									'Dodavanje na postojeću porudžbinu:<br/>'+
									'<br/>Sto:<input type="text" id="deskId" style="width:270px" value="'+desk+'">'+
									'<br/>Pića:'+
									'<select id="comboDrinks" multiple="multiple" size="5" style="width:300px"></select>'+
									'<br/>Jela:'+
									'<select id="comboMeals" multiple="multiple" size="5" style="width:300px"></select>'+
									'<br/><input type = "submit" id = "addOnOrder" name="'+order_id+'" value="Potvrdi" class="btn orange">'+
									'</form></div></div></div></div>');
					$.each(allDrinks, function(index,drink){
						$('#comboDrinks').append('<option>'+drink+'</option>');
					});
					$.each(allMeals, function(index,meal){
						$('#comboMeals').append('<option>'+meal+'</option>');
					});
					$("#deskId").attr('readonly','readonly');
				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Meal ERROR: " + errorThrown);
				}	
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Drink ERROR: " + errorThrown);
		}	
	});
});

$(document).on('click', '#addOnOrder', function(e) {
	e.preventDefault();
	var drinks = $('#comboDrinks').val();
	var meals = $('#comboMeals').val();
	var desk =$(document).find('#deskId').val();
	var id = $(this).attr('name');
	var timeOfOrder = new Date().getTime();
	$.ajax({
		type : 'PUT',
		url :  '/orderController/addOnOrder/'+id,
		contentType : 'application/json',
		dataType :'json',
		data : JSON.stringify({
			"waiter_id" : "1",
			"table_id" : desk,
			"restaurant" : "1",
			"barman_state" : "kreirana",
			"cook_state" : "kreirana",
			"timeOfOrder" : timeOfOrder,
			"drinks" : drinks,
			"meals" : meals
		}),
		success : function(data){	
			Command: toastr["success"]("Uspješno izvršeno dodavanje na postojeću porudžbinu.", "Odlično!")
			message();
			showOrders();
			$.ajax({
				type : 'POST',
				url :  '/send/newOrder',
				data : {
					"newOrder" : "Dodato je na porudžbinu za sto "+desk+"!"
				},
				success : function(data){	
					//Command: toastr["success"]("Uspjela je notifikacija.", "Odlično!")
					//message();
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
});

$(document).on('click', '#submitEdit', function(e) {
	e.preventDefault();
	var name = $(this).attr('name');
	var zaSplit;
	$(document).find('input[name="edit"]').each(function(e){	
		  var id = this.id;
		 if(name == id ){
			 zaSplit = this.value;
		 }
	});
	var splitovano  = zaSplit.split(";");
	var desk = splitovano[0];
	var drinks = splitovano[1].split(",");
	var meals = splitovano[2].split(",");
	var order_id = splitovano[3];
	var allDrinks = [];
	var allMeals = [];
	$("#content").empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			//$("table.edit").append('<tr><td>Pića: &nbsp;</td><td><select id="comboDrinks" multiple="multiple" size="5" style="width:170px;">');
			$.each(list, function(index,pice){
				//$('#comboDrinks').append('<option>'+pice.name+'</option>');
				allDrinks.push(pice.name);
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/mealAndDrinkController/uzmiObroke',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					//$("table.edit").append('<tr><td>Jela: &nbsp;</td><td><select id="comboMeals" multiple="multiple" size="5" style="width:170px;">');
					$.each(list, function(index,obrok){
						//$('#comboMeals').append('<option>'+obrok.name+'</option>');
						allMeals.push(obrok.name);
					});
					$('#content').empty();
					$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
							'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
								'<form method="post" id="updateForm">'+
									'Izmjena porudžbine:<br/>'+
									'<br/>Sto:<input type="text" id="deskId" style="width:270px" value="'+desk+'">'+
									'<br/>Pića:'+
									'<select id="comboDrinks" multiple="multiple" size="5" style="width:300px"></select>'+
									'<br/>Jela:'+
									'<select id="comboMeals" multiple="multiple" size="5" style="width:300px"></select>'+
									'<br/><input type = "submit" id = "update" name="'+order_id+'" value="Potvrdi" class="btn orange">'+
									'</form></div></div></div></div>');
					$.each(allDrinks, function(index,drink){
						$('#comboDrinks').append('<option>'+drink+'</option>');
					});
					$.each(allMeals, function(index,meal){
						$('#comboMeals').append('<option>'+meal+'</option>');
					});
					$("#deskId").attr('readonly','readonly');
					$('#comboDrinks').val(drinks);
					$('#comboMeals').val(meals);
					//$("table.edit").append('<tr><td>&nbsp;</td><td><input type="submit" id="update" name="'+name+'" value="Potvrdi" class="btn green"></td>');
				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Meal ERROR: " + errorThrown);
				}	
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Drink ERROR: " + errorThrown);
		}	
	});
});

$(document).on('click', '#update', function(e) {
	e.preventDefault();
	var drinks = $('#comboDrinks').val();
	var meals = $('#comboMeals').val();
	var desk =$(document).find('#deskId').val();
	var id = $(this).attr('name');
	var timeOfOrder = new Date().getTime();
	$.ajax({
		type : 'PUT',
		url :  '/orderController/change/'+id,
		contentType : 'application/json',
		dataType :'json',
		data : JSON.stringify({
			"waiter_id" : "1",
			"table_id" : desk,
			"restaurant" : "1",
			"barman_state" : "kreirana",
			"cook_state" : "kreirana",
			"timeOfOrder" : timeOfOrder,
			"drinks" : drinks,
			"meals" : meals
		}),
		success : function(data){	
			Command: toastr["success"]("Uspješno izvršena izmjena porudžbine.", "Odlično!")
			message();
			showOrders();
			
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});

$(document).on('click', '#bill', function(e) {
	e.preventDefault();
	var name = $(this).attr('name');
	var zaSplit;
	$(document).find('input[name="bill"]').each(function(e){	
		  var id = this.id;
		 if(name == id ){
			 zaSplit = this.value;
		 }
	});
	var splitovano  = zaSplit.split(";");
	var desk = splitovano[0];
	var drinks = splitovano[1].split(",");
	var meals = splitovano[2].split(",");
	var order_id = splitovano[3];
	var billSum = 0;
	$("#content").empty();
	$("#content").append('</br><p><b>Račun</b></p>');
	$("#content").append('<table class="bill"></table>');
	$("table.bill").append('<tr><td>Sto: &nbsp;</td><td>'+desk+'</td></tr>');
	$("#deskId").attr('readonly','readonly');
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$("table.bill ").append('<tr><td>Pića: &nbsp;</td></tr>');
			$.each(list, function(index1,pice){
				$.each(drinks, function(index2,drink){
					if(pice.name==drink){
						$("table.bill ").append('<tr><td>'+pice.name+' = '+pice.price+'</td></tr>');
						billSum=billSum + pice.price;
					}
				});
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/mealAndDrinkController/uzmiObroke',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$("table.bill").append('<tr><td>Jela: &nbsp;</td></tr>');
					$.each(list, function(index1,obrok){
						$.each(meals, function(index2,meal){
							if(obrok.name==meal){
								$("table.bill ").append('<tr><td>'+obrok.name+' = '+obrok.price+'</td></tr>');
								billSum=billSum + obrok.price;
							}
						});
					});
					$("table.bill").append('<tr><td>Ukupno: </td><td>'+billSum+'</td></tr>');
					var dateOfBill = new Date().getTime();
					$.ajax({
						type : 'POST',
						url :  '/billController/addBill',
						contentType : 'application/json',
						dataType :'json',
						data : JSON.stringify({
							"waiter_id" :"1",
							"bill" : billSum,
							"dateOfBill" : dateOfBill,
							"order_id" : order_id
						}),
						success : function(data){
							Command: toastr["success"]("Uspješno kreiran račun.", "Odlično!")
							message();
						},

						error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
							alert("Bill ERROR: " + errorThrown);
						}
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Meal ERROR: " + errorThrown);
				}	
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Drink ERROR: " + errorThrown);
		}	
	});

});

function todayDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = mm+'/'+dd+'/'+yyyy;
	return today;
}

$(document).on('click','#addOrder',function(e){
	$("#content").empty();
	/*$("#content").append('</br><p><b>Dodavanje porudžbine</b></p>');
	$("#content").append('<p>*potrebno je popuniti sva polja</p>');
	$("#content").append('<table id="tableAddOrder"></table>');*/
	var allDrinks = [];
	var allMeals = [];
	var allTables= [];
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/waiterController/getTables',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			//$("#tableAddOrder").append('<tr><td>Sto: &nbsp; </td><td><select id="comboTables" style="width:170px;">');
			$.each(list, function(index,table){
				//$('#comboTables').append('<option>'+table.id+'</option>');
				allTables.push(table.id);
			});
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/mealAndDrinkController/uzmiPica',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					//$("#tableAddOrder").append('<tr><td>Pića: &nbsp; </td><td><select id="comboDrinks" multiple="multiple" size="5" style="width:170px;">');
					$.each(list, function(index,pice){
						//$('#comboDrinks').append('<option>'+pice.name+'</option>');
						allDrinks.push(pice.name);
					});
					$.ajax({
						type: 'GET',
						dataType: 'json',
						url : '/mealAndDrinkController/uzmiObroke',
						success : function(data){
							var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
							//$("#tableAddOrder").append('<tr><td>Jela: &nbsp;</td><td><select id="comboMeals" multiple="multiple" size="5" style="width:170px;">');
							$.each(list, function(index,obrok){
								//$('#comboMeals').append('<option>'+obrok.name+'</option>');
								allMeals.push(obrok.name);
							});
							$('#content').empty();
							$('#content').append('<div id="wraper"><div class="centered-content-wrap" id="first">'+
									'<div class="login-page wrapper centered centered-block"> <div class = "form-group">'+
										'<form method="post" id="updateForm">'+
											'Dodavanje porudžbine:<br/>'+
											'<br/>Sto:<br/>'+
											'<select id="comboTables" style="width:300px"></select>'+
											'<br/>Pića:'+
											'<select id="comboDrinks" multiple="multiple" size="5" style="width:300px"></select>'+
											'<br/>Jela:'+
											'<select id="comboMeals" multiple="multiple" size="5" style="width:300px"></select>'+
											'<br/><input type = "submit" id = "submitOrder" value="Potvrdi" class="btn orange">'+
											'</form></div></div></div></div>');
							$.each(allTables, function(index,table){
								$('#comboTables').append('<option>'+table+'</option>');
							});
							$.each(allDrinks, function(index,drink){
								$('#comboDrinks').append('<option>'+drink+'</option>');
							});
							$.each(allMeals, function(index,meal){
								$('#comboMeals').append('<option>'+meal+'</option>');
							});
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("Meal ERROR: " + errorThrown);
						}	
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Drink ERROR: " + errorThrown);
				}	
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Tablee ERROR: " + errorThrown);
		}	
	});
	
});

$(document).on('click','#submitOrder',function(e){
	e.preventDefault();
	var table = $('#comboTables').val();
	var drinks = $('#comboDrinks').val();
	var meals = $('#comboMeals').val();
	var listOfDrinkss = [];
	var timeOfOrder = new Date().getTime();
	$.ajax({
		type : 'POST',
		url :  '/orderController/addOrder',
		contentType : 'application/json',
		dataType :'json',
		data : JSON.stringify({
			"waiter_id" :"1",
			"table_id" : table,
			"restaurant" : "1",
			"barman_state" : "kreirana",
			"cook_state" : "kreirana",
			"timeOfOrder" : timeOfOrder,
			"drinks" : drinks,
			"meals" : meals
		}),
		success : function(data){
			Command: toastr["success"]("Uspješno dodata porudžbina.", "Odlično!")
			message();
			showOrders();
			$.ajax({
				type : 'POST',
				url :  '/send/newOrder',
				data : {
					"newOrder" : "Stigla je nova porudžbina!"
				},
				success : function(data){	
					//Command: toastr["success"]("Uspjela je notifikacija.", "Odlično!")
					//message();
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
});
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
								'<br/><input type = "submit" id = "submitUpdateProfile" value="Potvrdi" class="btn orange">'+
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
		"restaurant" : "1",
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
var billValues = [];
$(document).on('click','#myBills',function(e){
	e.preventDefault();
	$("#content").empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		contentType : 'application/json',
		url : '/billController/getMyBills',
		success : function(data){
			var bills = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$("#content").append('<p><b>Moji računi</b></p>');
			$("#content").append("<table id='billTable'>");
		    $("#content").append("<thead>");
		    $("#content").append("<tr>");
		    $("#content").append("<th>Šifra</th>");
		    $("#content").append("<th>Datum računa</th>");
		    $("#content").append("<th>Iznos</th>");
		    $("#content").append("<th>&nbsp;</th>");
		    $("#content").append("</tr>");
		    $("#content").append("</thead>");
		    $("#content").append("<tbody>");
			$.each(bills, function(index,bill){
				var forma = $('<form method="post" class="billOrdersForm" action=""></form>');
		        var tr = $('<tr></tr>');
		        var billValue=  bill.id+";"+bill.order_id;
		        billValues.push(billValue);
		        var d = new Date(bill.dateOfBill);
		        tr.append('<td align="center">' + bill.id + '</td><td align="center">'+d+'</td><td align="center">'+bill.bill+'</td>');
		        forma.append('<input type="hidden" name="billOrders" id='+index+' value="'+ bill.id+";"+bill.order_id +'">' +
		                '<input type="submit" id="billOrders" name='+index+' value="Vidi stavke" class="btn green">');
		        var td = $('<td></td>');
		        td.append(forma);     
		        tr.append(td);
		        $('#content').append(tr);
			});
		  $("#content").append("</tbody>");
		  $("#content").append("</table>");

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("MyBills ERROR: " + errorThrown);
		}	
	});
});
$(document).on('click', '#billOrders', function(e) {
	e.preventDefault();
	$("#content").empty();
	var name = $(this).attr('name');
	var zaSplit;
	var id;
	/*$(document).find('input[name="billOrders"]').each(function(e){	
		  id = this.id;
		 if(name == id ){
			 zaSplit = this.value;
		 }
	});
	var splitovano  = zaSplit.split(";");*/
	var billValue = billValues[name];
	var splitovano = billValue.split(";");
	var billId = splitovano[0];
	var order_id = splitovano[1];
	$("#content").empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/orderController/getOrder/'+order_id,
		success : function(data){
			$("#content").append('<p><b>Stavke računa</b></p>');
			$("#content").append("<table>");
		    $("#content").append("<thead>");
		    $("#content").append("<tr>");
		    $("#content").append("<th>Šifra računa</th>");
		    $("#content").append("<th>Jela</th>");
		    $("#content").append("<th>Pića</th>");
		    $("#content").append("</tr>");
		    $("#content").append("</thead>");
		    $("#content").append("<tbody>");
		    var tr = $('<tr></tr>');
		    var meals = data.meals;
		    if(meals == null){
		    	meals = "nema";
		    }
		    var drinks = data.drinks;
		    if(drinks == null){
		    	drinks = "nema";
		    }
	        tr.append('<td align="center">' + billId + '</td><td align="center">'+
	        		meals+'</td><td align="center">'+drinks+'</td>');
	        var td = $('<td></td>');
	        tr.append(td);
	        $('#content').append(tr);
	        $("#content").append("</tbody>");
	        $("#content").append("</table>");
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("billOrders: " + errorThrown);
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
/*
 <html>
<head>
<meta charset="utf-8">
<style type="text/css">
.uzay{ background-image:url('seating.jpg');
width:537px;
height:262px;
<!--border:1px solid turquoise;-->
}
.rect{ fill:transparent; cursor:pointer;}
.circle {fill:transparent; cursor:pointer}
</style>
<script type="text/javascript">
var A = ["red","white"];
var i=0;
function renkli(ad){   // alert(ad);
var el= document.getElementById(ad);
//el.setAttribute('fill',A[i]);
el.style.fill=A[i];
i++;
if(i>1){i=0;}
}
</script>
</head>
<body>

<img src="seating.jpg" alt="" usemap="#Map" />
<map name="Map" id="Map">
    <area alt="" title="" href="#" shape="poly" coords="13,12,56,13,56,67,13,65" />
    <area alt="" title="" href="#" shape="poly" coords="67,15,107,15,107,68,65,68" />
    <area alt="" title="" href="#" shape="poly" coords="119,13,159,14,160,67,117,67" />
    <area alt="" title="" href="#" shape="poly" coords="171,14,211,13,210,66,170,66" />
    <area alt="" title="" href="#" shape="poly" coords="223,15,263,15,262,67,222,65" />
    <area alt="" title="" href="#" shape="poly" coords="275,14,313,15,314,66,274,65" />
    <area alt="" title="" href="#" shape="poly" coords="327,14,366,15,365,67,325,66" />
    <area alt="" title="" href="#" shape="poly" coords="380,14,420,13,420,67,377,67" />
    <area alt="" title="" href="#" shape="poly" coords="430,14,470,16,471,67,428,67" />
    <area alt="" title="" href="#" shape="poly" coords="483,15,523,16,522,67,482,68" />
    <area alt="" title="" href="#" shape="poly" coords="14,74,54,77,54,126,16,126" />
    <area alt="" title="" href="#" shape="poly" coords="67,75,107,76,106,127,66,128" />
    <area alt="" title="" href="#" shape="poly" coords="118,74,159,75,158,127,118,127" />
    <area alt="" title="" href="#" shape="poly" coords="170,73,210,76,209,127,170,128" />
    <area alt="" title="" href="#" shape="poly" coords="222,74,264,76,263,127,222,127" />
    <area alt="" title="" href="#" shape="poly" coords="274,73,316,77,316,127,273,127" />
    <area alt="" title="" href="#" shape="poly" coords="327,76,366,78,366,126,327,128" />
    <area alt="" title="" href="#" shape="poly" coords="379,75,419,75,419,126,377,126" />
    <area alt="" title="" href="#" shape="poly" coords="434,75,470,76,471,128,432,125" />
    <area alt="" title="" href="#" shape="poly" coords="483,77,521,74,521,126,482,126" />
    <area alt="" title="" href="#" shape="poly" coords="15,140,54,140,53,188,14,185" />
    <area alt="" title="" href="#" shape="poly" coords="66,139,105,139,105,187,66,187" />
    <area alt="" title="" href="#" shape="poly" coords="119,139,158,139,158,187,119,186" />
    <area alt="" title="" href="#" shape="poly" coords="171,139,211,140,208,186,170,187" />
    <area alt="" title="" href="#" shape="poly" coords="223,139,261,139,260,185,223,186" />
    <area alt="" title="" href="#" shape="poly" coords="276,138,313,139,313,186,274,184" />
    <area alt="" title="" href="#" shape="poly" coords="327,138,364,140,364,185,327,184" />
    <area alt="" title="" href="#" shape="poly" coords="379,140,416,140,417,185,379,185" />
    <area alt="" title="" href="#" shape="poly" coords="431,139,469,138,469,186,431,184" />
    <area alt="" title="" href="#" shape="poly" coords="485,139,520,140,521,185,482,185" />
    <area alt="" title="" href="#" shape="poly" coords="15,197,54,199,54,245,13,246" />
    <area alt="" title="" href="#" shape="poly" coords="68,199,106,201,105,246,66,245" />
    <area alt="" title="" href="#" shape="poly" coords="119,201,157,200,156,245,120,244" />
    <area alt="" title="" href="#" shape="poly" coords="172,201,208,200,209,246,171,244" />
    <area alt="" title="" href="#" shape="poly" coords="223,198,262,198,262,244,222,244" />
    <area alt="" title="" href="#" shape="poly" coords="276,199,315,199,313,246,274,245" />
    <area alt="" title="" href="#" shape="poly" coords="327,199,365,199,366,246,324,245" />
    <area alt="" title="" href="#" shape="poly" coords="380,200,418,200,416,245,379,244" />
    <area alt="" title="" href="#" shape="poly" coords="431,200,468,200,469,245,432,244" />
    <area alt="" title="" href="#" shape="poly" coords="484,199,521,199,521,244,483,245" />
</map>
<script type="text/javascript">
function toSvg(ad){
var m, a, t,s,b, c, cc;
m = document.getElementById(ad);
 a = m.getElementsByTagName('area');
 t='<svg class="uzay">';
// alert(a.length);
//alert(a[0].getAttribute('shape'));
for(var i=0; i<a.length; i++) {
s = a[i].getAttribute('shape'); 
c = a[i].getAttribute('coords'); 
b= s+i;
if(s == 'poly') {  
t+=  '<polyline points="'+c+'" fill="red" fill-opacity="0.4" id="'+b+'" onclick="renkli(this.id)"/>'; 
}
}
t+='</svg>';
var el= document.getElementById('mekan');
el.innerHTML = t + 'click  circle and rect';
alert(el.innerHTML);
}
</script><br>
<input type="button" value="to svg" onclick="toSvg('Map')">
<div id="mekan"></div> 
</body>
</html>*/

function formToJSON(username,password){
	return JSON.stringify({
		"username" : username,
		"password" : password
	});
}