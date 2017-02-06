$(document).on('click','#dodajRadnika',function(e){
	e.preventDefault();
	var today = new Date();
	var y = today.getFullYear();
	$('#restoranHtml').empty();
	$('#restoranHtml').multiDatesPicker({
		numberOfMonths: [1,2],
		defaultDate: '1/1/'+y
	});
});