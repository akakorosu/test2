$(function(){
    $(".chosen-select").chosen({
        disable_search : true
    });
    $(".chosen-multiple").multiselect();
    $(".datepicker-input").datepicker({
        autoclose:true,
        format:"yyyy-mm-dd"
    });
})