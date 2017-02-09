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
	//$("#content").append("<img src='jpa-example/src/main/resources/static/seating.jpg' alt='Graficki prikaz stolova'>");
	$("#content").append("<button class='table' id='1' style='height:50px;width:80px'>1</button><button class='table' id='2' style='height:50px;width:80px'>2</button><button class='table' id='3' style='height:50px;width:80px'>3</button>");
	$("#content").append("<button class='table' id='4' style='height:50px;width:80px'>4</button><button class='table' id='5' style='height:50px;width:80px'>5</button><button class='table' id='6' style='height:50px;width:80px'>6</button></br>");
	$("#content").append("<button class='table' id='7' style='height:50px;width:80px'>7</button><button class='table' id='8' style='height:50px;width:80px'>8</button><button class='table' id='9' style='height:50px;width:80px'>9</button>");
	$("#content").append("<button class='table' id='10' style='height:50px;width:80px'>10</button><button class='table' id='11' style='height:50px;width:80px'>11</button><button class='table' id='12' style='height:50px;width:80px'>12</button></br>");
	$("#content").append("<button class='table' id='13' style='height:50px;width:80px'>13</button><button class='table' id='14' style='height:50px;width:80px'>14</button><button class='table' id='15' style='height:50px;width:80px'>15</button>");
	$("#content").append("<button class='table' id='16' style='height:50px;width:80px'>16</button><button class='table' id='17' style='height:50px;width:80px'>17</button><button class='table' id='18' style='height:50px;width:80px'>18</button></br>");
	
	$.ajax({
		type : 'GET',
		url :  '/workingAreaController/getWorkingArea',
		contentType : 'application/json',
		dataType :'json',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index, workingArea) {
				if(workingArea.username == "Desa"){
					$("#content").append("</br><button style='background-color:red;height:50px;width:80px'></button>Moj reon");
					var areas = [];
					var areaString = workingArea.area;
					areas = areaString.split(',');
					//alert(workingArea.area);
					$.each(areas, function(index2, area) {
						var buttons = $(document).find('button[class="table"]');
						$.each(buttons, function(index3, button){
							if(button.id == area){
								$("#"+area).attr("disabled","disabled");
								$("#"+area).css("background-color", "red");
							}
						});
					});
				}	
			});
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});

});

function showOrders(){
	$("#content").empty();
	
	$.ajax({
		type : 'GET',
		url :  '/orderController/getOrder',
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
		      $("#content").append("<th>Jela</th>");
		      $("#content").append("</tr>");
		      $("#content").append("</thead>");
		      $("#content").append("<tbody>");
		      $.each(list, function(index, order) {
					if(order.username == "Desa"){
						var drinks = order.drinks;
						var meals = order.meals;
						var desk = order.desk;
						var forma = $('<form method="post" class="orderForm" action=""></form>');
						var formaBill = $('<form method="post" class="orderBill" action=""></form>');
				        var tr = $('<tr></tr>');
				        tr.append('<td align="center">' + desk + '</td><td align="center">'+drinks+'</td><td align="center">' + meals + '</td>');
				        forma.append('<input type="hidden" name="edit" id='+index+' value="'+ desk+";"+drinks+";"+meals +'">' +
				                '<input type="submit" id="submitEdit" name='+index+' value="Izmjeni" class="btn green">');
				        var td = $('<td></td>');
				        td.append(forma);
				        formaBill.append('<input type="hidden" name="bill" id='+index+' value="'+ desk+";"+drinks+";"+meals +'">' +
				                '<input type="submit" id="bill" name='+index+' value="Kreiraj račun" class="btn green">');
				        var tdBill = $('<td></td>');
				        tdBill.append(formaBill);
				        tr.append(td);
				        tr.append(tdBill);
				        $('#content').append(tr);
					}	
				});
	
			  $("#content").append("</tbody>");
			  $("#content").append("</table>");
			  //var table = document.getElementById("tableOrder");
			  //table.style.border = "thick solid red";
			  //$("#tableOrder").css("align","center");

		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}

$(document).on('click','#order',function(e){
	showOrders();
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

	$("#content").empty();
	$("#content").append('</br><p><b>Izmjena porudžbine</b></p>');
	$("#content").append('<p>*potrebno je popuniti sva polja</p>');
	$("#content").append('<table class="edit"></table>');
	$("table.edit").append('<tr><td>Sto: &nbsp;</td><td><input type="text" id="deskId" value="'+desk+'"></td></tr>');
	$("#deskId").attr('readonly','readonly');
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$("table.edit").append('<tr><td>Pića: &nbsp;</td><td><select id="comboDrinks" multiple="multiple" size="5" style="width:170px;">');
			$.each(list, function(index,pice){
				$('#comboDrinks').append('<option>'+pice.name+'</option>');
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/mealAndDrinkController/uzmiObroke',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$("table.edit").append('<tr><td>Jela: &nbsp;</td><td><select id="comboMeals" multiple="multiple" size="5" style="width:170px;">');
					$.each(list, function(index,obrok){
						$('#comboMeals').append('<option>'+obrok[1]+'</option>');
					});
					$('#comboDrinks').val(drinks);
					$('#comboMeals').val(meals);
					$("table.edit").append('<tr><td>&nbsp;</td><td><input type="submit" id="update" name="'+name+'" value="Potvrdi" class="btn green"></td>');
				
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
							if(obrok[1]==meal){
								$("table.bill ").append('<tr><td>'+obrok[1]+' = '+obrok[2]+'</td></tr>');
								billSum=billSum + obrok[2];
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
							"username" :"Desa",
							"bill" : billSum,
							"dateOfBill" : dateOfBill
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
$(document).on('click', '#update', function(e) {
	var drinks = $('#comboDrinks').val();
	var meals = $('#comboMeals').val();
	var desk =$(document).find('#deskId').val();
	var name = $(this).attr('name');
	var id = parseInt(name) + 1;//jer su indeksi od 0, a indeksi u bazi od 1
	$.ajax({
		type : 'PUT',
		url :  '/orderController/change/'+id,
		contentType : 'application/json',
		dataType :'json',
		data : JSON.stringify({
			"username" : "Desa",
			"desk" : desk,
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

$(document).on('click','#addOrder',function(e){
	$("#content").empty();
	$("#content").append('</br><p><b>Dodavanje porudžbine</b></p>');
	$("#content").append('<p>*potrebno je popuniti sva polja</p>');
	$("#content").append('<table id="tableAddOrder"></table>');
	$("#tableAddOrder").append('<tr><td>Broj stola</td><td><input type="text" id="desk"></td></tr>');
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/mealAndDrinkController/uzmiPica',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$("#tableAddOrder").append('<tr><td>Pića: &nbsp; </td><td><select id="comboDrinks" multiple="multiple" size="5" style="width:170px;">');
			$.each(list, function(index,pice){
				$('#comboDrinks').append('<option>'+pice.name+'</option>');
			});
			
			$.ajax({
				type: 'GET',
				dataType: 'json',
				url : '/mealAndDrinkController/uzmiObroke',
				success : function(data){
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$("#tableAddOrder").append('<tr><td>Jela: &nbsp;</td><td><select id="comboMeals" multiple="multiple" size="5" style="width:170px;">');
					$.each(list, function(index,obrok){
						$('#comboMeals').append('<option>'+obrok[1]+'</option>');
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
	$("#content").append('<tr><td>&nbsp;</td><td><input type="submit" id="submitOrder" class="btn orange"></td></tr>');
});

$(document).on('click','#submitOrder',function(e){
	var desk = $(document).find('#desk').val();
	var drinks = $('#comboDrinks').val();
	var meals = $('#comboMeals').val();
	$.ajax({
		type : 'POST',
		url :  '/orderController/addOrder',
		contentType : 'application/json',
		dataType :'json',
		data : JSON.stringify({
			"username" :"Desa",
			"desk" : desk,
			"drinks" : drinks,
			"meals" : meals
		}),
		success : function(data){
			Command: toastr["success"]("Uspješno dodata porudžbina.", "Odlično!")
			message();
			showOrders();
		},

		error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
			alert("AJAX ERROR: " + errorThrown);
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