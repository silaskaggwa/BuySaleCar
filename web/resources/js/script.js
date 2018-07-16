(function () {
    "use strict";

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
            $("#addCarModal").find("form").show();
            $("#addCarModal").find("label#car-add-msg").hide();
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
        
        //region add car
        $("#btnAddCar").click(function (e) {
            if(validateAddCarModal()){

                let data = new FormData(document.getElementById("addCarForm"));
                var car ={
                    license: $("#car-number").val().trim(),
                    model:  $("#car-model").val().trim(),
                    brand:  $("#car-brand").val().trim(),
                    price : $("#car-price").val().trim(),
                    color: $("#car-color").val().trim(),
                    date: new Date().getMonth()+"/"+new Date().getDate()+"/"+new Date().getFullYear(),
                    discount: $("#car-discount").val().trim(),
                    image:  $("#car-image")[0].files[0]
                }
                data.append("number", $("#car-number").val().trim());
                data.append("model", $("#car-model").val().trim());
                data.append("brand", $("#car-brand").val().trim());
                data.append("price", $("#car-price").val().trim());
                data.append("color", $("#car-color").val().trim());
                data.append("date", new Date().getMonth()+"/"+new Date().getDate()+"/"+new Date().getFullYear());
                data.append("discount", $("#car-discount").val().trim());
                data.append("shape", $("#car-shape").val().trim());
                data.append("image", $("#car-image")[0].files[0]);

                $.ajax({
                    method: 'POST',
                    url: 'get-all-cars',
                    data: data,
                    enctype: 'multipart/form-data',
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        if(response.length <=0){
                            alert("problem in adding car");
                        }
                        else{
                            $("#content").prepend(generateCarHtml(response));
                            $("#car-add-msg").html("Car added Successfully");
                            $("#addCarModal").find("form").hide();
                            $("#addCarModal").find("label#car-add-msg").show();
                            setTimeout(function () {
                                $('#addCarModal').modal('hide');
                                $("#addCarModal").find(":input").not(":button").val("");
                            },1000);
                        }

                    },
                    error: function (error) {
                       alert("problem in adding car");
                    }
                });
            }

        });

        fetchAllCars();
        if (HasCookie("persistentlogintoken") == true ) {
            $.ajax({
                method: 'GET',
                url: 'persistentlogin',
                success: function (response) {
                    if(typeof response != "undefined" && response.length >0 && response != "false"){
                        alert("Login with username "+response+" found");
                    }
                    else{
                        alert("persistent login failed");
                    }
                },
                error: function (error) {
                    alert("persistent login failed");
                }
            });
        }
    });

    function generateCarHtml(car) {
        return `<div class="car-info-wrapper">
                    <div class="image-wrapper">
                        <img src="${car.image}" alt="${car.brand + car.model}"/>
                    </div>
                   <div class="info-wrapper">
                       <h4>${car.license}</h4>
                       <h4>${car.price}</h4>
                       <button type="button" class="btn btn-success">View owner</button>
                       <div class="owner-info-wrapper">

                       </div>
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
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

    function validateAddCarModal(){
        var isValidate = true;
        $("#addCarModal :input").not(":button").each(function(index, value){
            var itemValue = $(this).val().trim();
            if(typeof itemValue == "undefined" || itemValue.length <=0){
                isValidate = false;
                $(this).addClass("error");
            }
        });
        return isValidate;
    }

    function PerformAddCarOnSuccess(data){

    }
    function PerformAddCarOnError(){

    }
    //#region HasCookie
    function HasCookie(name) {
        var pairs = document.cookie.split("; "),
            count = pairs.length, parts;
        while (count--) {
            parts = pairs[count].split("=");
            if (parts[0] === name)
                return true;
        }
        return false;
    }
//#endregion

})();