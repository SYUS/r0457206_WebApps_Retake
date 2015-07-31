/**
 *  After page ready Bootstrap optional plugins initialisers
 *  
 */
$(document).ready(function(){
	/*
	$('[data-toggle="popover"]').popover({
		placement : 'top'
    });
    */
	$('[data-toggle="tooltip"]').tooltip({
	    'placement': 'top'
	});
	$('[data-toggle="popover"]').popover();
});

/*
<a id="pop" 
        href="#" 
        class="btn btn-lg btn-danger" 
        data-toggle="popover" 
        data-content="And here's some amazing content. It's very engaging. right?"
    >Hover to toggle popover</a>
    
    <button type="button" class="btn btn-default" data-container="body" data-trigger="hover" data-toggle="popover" data-placement="bottom" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus." title="Dismissible popover">
  		Popover on bottom
	</button>

<a tabindex="0" class="btn btn-lg btn-danger" role="button" data-toggle="popover" data-trigger="hover focus" title="Dismissible popover" data-content="And here's some amazing content. It's very engaging. Right?">Dismissible popover</a>
*/