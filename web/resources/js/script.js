
//region page load
$(document).ready(function(){


    // region add Car
    $("li#AddCar a").click(function(e){
        debugger;
        e.preventDefault();
        $('#addCarModal').modal('show');
    });
});