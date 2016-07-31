$(document).ready(function() {
    $('#botonpruebafade').click(function(evento) {
        $('#divquesemuestra').fadeIn(2000);
        $('#botonpruebafade').hide(1000);
    });
});

$(function(){
	$("#mostrar").click(function(event) {
	event.preventDefault();
	$("#aprende").slideToggle();
});
$("#aprende a").click(function(event) {
	event.preventDefault();
	$("#aprende").slideUp();
});
});