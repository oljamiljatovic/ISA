$(document).on('click','#prihodRestorana',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form method="post" id="prihodiRest">'+
		'Prihodi restorana:<br/><br/>'+
		'Pocetni datum:<input type = "date" id = "startDate" class="in-text"/><br/>'+
		'Krajnji datum:<input type = "date" id = "endDate" class="in-text"/><br/>'+
		'<br><br><br>------  Izvestaj: ----------'+
		'Prihod:<input type = "text" id = "ukupniPrihod" class="in-text" readonly="true"/><br/>'+
		'<input type = "submit" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
});


$(document).on('submit','#prihodiRest',function(e){
	e.preventDefault();
	var str1 = $('#startDate').val();
	var date1 = new Date(str1);
	var str3 = $('#endDate').val();
	var date3 = new Date(str3);
	
	if(date1>date3){
		alert('Prvi datum mora biti manji od drugog!');
	}else{
	
		$.ajax({
			type : 'GET',
			url :  '/billController/getBillsOfRestaurant',
			contentType : 'application/json',
			success : function(data){
				var suma = 0;
				var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
				$.each(list, function(index, bill){
					
					var date2 = new Date(bill.dateOfBill);
					if( date1 < date2 && date2 < date3){
						suma = suma+bill.bill;
				    }
				});
				
				$('#ukupniPrihod').val(suma);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) { //(XHR,STATUS, ERROR)
				alert("Da li je ovdje problem");
				alert("AJAX ERROR: " + errorThrown);
			}
		});
	}
	
});


$(document).on('click','#prihodKonobara',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#mica_mapa').empty();
	$('#ubaci_mapu').empty();
	$('#content').append('<div id="wraper"><div class="centered-content-wrap" >'+
		'<div class="login-page wrapper centered centered-block">'+ 
		'<div class = "form-group"><form method="post" id="prihodiKonob">'+
		'Prihodi konobara:<br/><br/>'+
		'Pocetni datum:<input type = "date" id = "startDate" class="in-text"/><br/>'+
		'Krajnji datum:<input type = "date" id = "endDate" class="in-text"/><br/>'+
		'Izaberi konobara:<select id="izborKonobara"></select><br/>'+
		'<br><br><br>------  Izvestaj: ----------'+
		'Prihod:<input type = "text" id = "ukupniPrihod" class="in-text" readonly="true"/><br/>'+
		'<input type = "submit" value="Submit" class="btn orange">'+
		'</form></div></div></div></div>');
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : '/getWaitersOfRestaurant',
		success : function(data){
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index,konobar){
				$('#izborKonobara').append('<option value = "' +konobar.id +'" >' + konobar.name + '</option>');
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("Admin ERROR: " + errorThrown);
		}	
	});
});


$(document).on('submit','#prihodiKonob',function(e){
	e.preventDefault();
	var str1 = $('#startDate').val();
	var date1 = new Date(str1);
	var str3 = $('#endDate').val();
	var date3 = new Date(str3);
	var id_konobara = $('#izborKonobara option:selected').val();
	
	var data2 = JSON.stringify({
		"id" : id_konobara,
		"role" : "waiter",
		"name" : "",
		"surname" : "",
		"dateBirth" : "",
		"confNumber" : "",
		"shoeNumber" : "",
		"restaurant" : "",
		"email" : "",
		"accept" : "",
		"password" : "",
		"firstLog" : "true"
	});
	
	if(date1>date3){
		alert('Prvi datum mora biti manji od drugog!');
	}else{
	
		$.ajax({
			type : 'POST',
			url :  '/billController/getBillsOfWaiter',
			contentType : 'application/json',
			dataType : 'json',
			data : data2,
			success : function(data){
				var suma = 0;
				var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
				$.each(list, function(index, bill){
					
					var date2 = new Date(bill.dateOfBill);
					if( date1 < date2 && date2 < date3){
						suma = suma+bill.bill;
				    }
				});
				
				$('#ukupniPrihod').val(suma);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) { 
				
				alert("AJAX ERROR: " + errorThrown);
			}
		});
	}
	
});



$(document).on('click','#grafikaPosecenosti',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#ubaci_mapu').empty();

	$('#content').append('<div id="wraper"><div id="mica_mapa"></div></div>');
	
	var pon = 0;
	var uto = 0;
	var sre = 0;
	var cet = 0;
	var pet = 0;
	var sub = 0;
	var ned = 0;
	
	$.ajax({
		type : 'GET',
		url :  '/billController/getBillsOfRestaurant',
		contentType : 'application/json',
		success : function(data){
			var suma = 0;
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index, bill){
				
				var date2 = new Date(bill.dateOfBill);
				
				var day = date2.getDay();
				if(day==0)
					ned = ned+1;
				else if(day==1)
					pon = pon+1;
				else if(day==2)
					uto = uto+1;
				else if(day==3)
					sre = sre+1;
				else if(day==4)
					cet = cet+1;
				else if(day==5)
					pet = pet+1;
				else if(day==6)
					sub = sub+1;
			});
			
			

			
			var chart = AmCharts.makeChart("mica_mapa", {
				  "type": "serial",
				  "theme": "light",
				  "columnWidth": 1,
				  "dataProvider": [{
				    "category": "Monday",
				    "count": pon
				  }, {
				    "category": "Tuesday",
				    "count": uto
				  }, {
				    "category": "Wednesday",
				    "count": sre
				  }, {
				    "category": "Thursday",
				    "count": cet
				  }, {
				    "category": "Friday",
				    "count": pet
				  }, {
				    "category": "Saturday",
				    "count": sub
				  }, {
				    "category": "Sunday",
				    "count": ned
				  }],
				  "graphs": [{
				    "fillColors": "#c55",
				    "fillAlphas": 0.9,
				    "lineColor": "#fff",
				    "lineAlpha": 0.7,
				    "type": "column",
				    "valueField": "count"
				  }],
				  "categoryField": "category",
				  "categoryAxis": {
				    "startOnAxis": true,
				    "title": "Days"
				  },
				  "valueAxes": [{
				    "title": "Count"
				  }]
				});
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});




$(document).on('click','#grafikaPosecenostiDan',function(e){
	e.preventDefault();
	
	$('#content').empty();
	$('#ubaci_mapu').empty();

	$('#content').append('<div id="wraper"><div id="mica_mapa"></div></div>');
	
	var jutro = 0;
	var podne = 0;
	var poslepodne = 0;
	var vece = 0;
	
	$.ajax({
		type : 'GET',
		url :  '/billController/getBillsOfRestaurant',
		contentType : 'application/json',
		success : function(data){
			var suma = 0;
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index, bill){
				
				var date2 = new Date(bill.dateOfBill);
				
				var hour = date2.getHours();
				
				if(hour>3 && hour<10)
					jutro = jutro+1;
				else if(hour>9 && hour<15)
					podne = podne +1;
				else if(hour>14 && hour<20)
					poslepodne = poslepodne + 1;
				else if(hour>19 || hour<4)
					vece = vece + 1;
			});
			
			

			
			var chart = AmCharts.makeChart("mica_mapa", {
				  "type": "serial",
				  "theme": "light",
				  "columnWidth": 1,
				  "dataProvider": [{
				    "category": "jutarnji casovi",
				    "count": jutro
				  }, {
				    "category": "podne",
				    "count": podne
				  }, {
				    "category": "poslepodne",
				    "count": poslepodne
				  }, {
				    "category": "vecernji sati",
				    "count": vece
				  }],
				  "graphs": [{
				    "fillColors": "#c55",
				    "fillAlphas": 0.9,
				    "lineColor": "#fff",
				    "lineAlpha": 0.7,
				    "type": "column",
				    "valueField": "count"
				  }],
				  "categoryField": "category",
				  "categoryAxis": {
				    "startOnAxis": true,
				    "title": "Days"
				  },
				  "valueAxes": [{
				    "title": "Count"
				  }]
				});
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});