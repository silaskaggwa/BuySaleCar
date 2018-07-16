(function () {
    "use strict";

    let carsDisplayed = [];

    $(function () {

        $(document).ajaxStart(function() {
            $("#top-loader").show();
        });

        $(document).ajaxStop(function() {
            $("#top-loader").hide();
        });

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

        $("#btnSearch").click(function (e) {
            fetchAllCars(
                $("#minPrice").val(),
                $("#maxPrice").val(),
                $("#brand").val(),
                $("#shape").val()
            )
            return false;
        });
        $("#btnReset").click(function (e) {
            $("#minPrice").val(undefined);
            $("#maxPrice").val(undefined);
            $("#brand").val("Any");
            $("#shape").val("Any");
            fetchAllCars();
            return false;
        });

        $(document).on('click', '.car-info', function () {
             let carId = $(this).attr('id');
             for (let car of carsDisplayed){
                 if (car.id == carId){
                     loadCarInfoModal(car);
                     displayCarInfoModal();
                 }
             }
             return false;
        });

        fetchAllCars();
    });

    function loadCarInfoModal(car){
        $('#brandModel').text(car.brand +", "+car.model);
    }

    function displayCarInfoModal(){
        $('#carInfoModal').modal('show');
    }

    function generateCarHtml(car) {
        return `<div class="car-info-wrapper">
                    <div class="image-wrapper">
                        <img src="${car.image}" alt="${car.brand + car.model}"/>
                    </div>
                   <div class="info-wrapper">
                       <h4>${car.brand +', '+ car.model}</h4>
                       <h4>${car.price}</h4>
                       <button id="${car.id}" type="button" class="btn btn-success car-info">View</button>
                   </div>
                </div>`;
    }

    function fetchAllCars(minPrice, maxPrice, brand, shape) {
        $.ajax({
            method: 'GET',
            url: 'get-all-cars',
            data: {
                minPrice: minPrice,
                maxPrice: maxPrice,
                brand: brand,
                shape:shape
            },
            success: function (response) {
                let carsHtml = '';
                for(let car of response){
                    carsHtml += generateCarHtml(car); 
                }
                $('#content').empty();
                setTimeout(()=>$('#content').append(carsHtml), 0);

                carsDisplayed = response;
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

})();