
//region page load
$(document).ready(function(){


    // region add Car
    $("li#AddCar a").click(function(e){
        e.preventDefault();
        $('#addCarModal').modal('show');
    });

    //region register user
    $("#Register a").click(function(event){
        event.preventDefault();
        $("#registerUser").modal('show')
    });
});