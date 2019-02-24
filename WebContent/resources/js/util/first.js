var langEnable=false;
var noRecord = "No record found";
$(document).ready(function(){
    $('.credit-nav-li').click(function(){
    	$('.credit-nav-li').find('li ul').slideDown();
    });
    $(".btn-group > .btn").click(function(){
        $(".btn-group > .btn").removeClass("active");
        $(this).addClass("active");
    });
    $(".btn-search").click(function(){
        $(".btn-group > .btn").removeClass("active");
        $(".btn-group > .btn:first-child").addClass("active");
    });
    $('.btn-group > .btn-filter').on('click', function () {
    	noRecord = $('#noRecordVal').val();
        var $target = $(this).data('target');
        var $input = $(this),
        $panel = $input.parents('.x_panel'),
        $table = $panel.find('.table');
        if ($target != '2') {
            $table.find('tbody .no-result').remove();
            $filteredRows = $('.table tr[data-status="' + $target + '"]');
            $('.table tbody tr').css('display', 'none');
            $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
            if ($filteredRows.length === 0) {
                $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">'+noRecord+'</td></tr>'));
            }
        } else {
        	$table.find('tbody .no-result').remove();
            $('.table tbody tr').css('display', 'none').fadeIn('slow');
        }
    });    
});