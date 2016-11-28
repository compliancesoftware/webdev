// JavaScript Document
window.setTimeout(function(){
	jQuery('#alert-closeable').fadeTo(500, 0).slideUp(500, function(){
		jQuery(this).remove();
		});
		}, 4000);