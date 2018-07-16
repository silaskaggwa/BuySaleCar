(function () {
    "use strict";
    let isLoggedIn = false;
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
            $("#addCarModal").find("form").show();
            $("#addCarModal").find("label#car-add-msg").hide();
        });

        //region register user
        $("#Register a").click(function(event){
            event.preventDefault();
            $("#register-form").show();
            $("#register-msg").hide();
            $("#register-form").find("input").val("");
            $("#registerUser").modal('show')
        });

        $("#registerbtn").click(function (ev) {

            if (validateRegisterModal()) {
                const firstname = $("#first-name").val();
                const lastname = $("#last-name").val();
                const email = $("#email").val();
                const phoneNumber = $("#phone-number").val();
                const address = $("#address").val();
                const usr = $("#user-name").val();
                const pswd = $("#pswd").val();

                $.ajax({
                    method: 'POST',
                    url: 'register',
                    data: {
                        "id": Math.floor(Math.random() * 1000),
                        "firstName": firstname,
                        "lastName": lastname,
                        "email": email,
                        "phoneNumber": phoneNumber,
                        "address": address,
                        "usr": usr,
                        "pswd": pswd
                    },
                    success: function (response) {

                        $("#register-form").hide();
                        $("#register-msg").show();
                        if (response == "true") {
                            $("#register-msg").html("register success")
                        }
                        else {
                            $("#register-msg").html("registration failed")
                        }
                        setTimeout(function () {
                            $("#registerUser").modal('hide')
                        }, 1000);
                    },
                    error: function (error) {
                        $("#register-form").hide();
                        $("#register-msg").show();
                        $("#register-msg").html("registration failed")
                        setTimeout(function () {
                            $("#registerUser").modal('hide')
                        }, 1000);
                    }
                });
            }else{
                console.log("Registration inputs invalid!");
            }
        });
        //region login user

        $("#loginMe a").click(function (evnt) {
            evnt.preventDefault();
            $("#loginUser").modal('show')
            $("#formlogin").show();
            $("#login-msg").hide();

        });



        /*submit login
        $("#btnSubmit").click(function (ev) {
            $("#formlogin").submit();

        }); */

        $("#btnSubmit").click(function (ev) {
            var uName = $("#username").val().trim();
            var pwd = $("#passWord").val().trim();
            $.ajax({
                method: 'POST',
                url: 'login',
                data: {
                   "userName": uName,
                    "passWord":pwd,
                    "rememberMe": $("#rememberMe").prop("checked"),
                },
                success: function (response) {

                   performLoginSuccess(response);
                },
                error: function (error) {
                    alert("invalid login credentials");
                }
            });

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
                            carsDisplayed.push(response);
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
        if (HasCookie("persistentlogintoken") == true ) {
            $.ajax({
                method: 'GET',
                url: 'persistentlogin',
                success: function (response) {
                    performLoginSuccess(response);
                },
                error: function (error) {

                }
            });
        }

        $("#logout").click(function(e){
            e.preventDefault();
            $.ajax({
                method: 'GET',
                url: 'logout',
                success: function (response) {
                    $("#loginName").html("");
                    $(".log-in").removeClass("none");
                    $("#AddCar").addClass("none");
                    $(".log-out").addClass("none");
                    $
                },
                error: function (error) {
                }
            });
        });
    });

    function loadCarInfoModal(car){
        $('#carImage').attr('src',car.image);
        $('#brandModel').text(car.brand +", "+car.model);
        $('#licenseNo').text(car.license);
        $('#carColor').text(car.color);
        $('#carShape').text(car.shape);
        $('#carPrice').text(car.price);
        $('#ownerName').text(car.owner.firstname+" "+car.owner.lastName);
        $('#ownerEmail').text(car.owner.email);
        $('#ownerPhone').text(car.owner.phoneNumber);
        $('#ownerAddress').text(car.owner.address);
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
                       <h4>$ ${car.price}</h4>
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

    function validateRegisterModal(){
        var isValid = true;
        $("#registerUser :input").not(":button").each(function(index, value){
            var itemValue = $(this).val().trim();
            if(typeof itemValue == "undefined" || itemValue.length <=0){
                isValid = false;
                $(this).addClass("error");
            }else {
                $(this).removeClass("error");
            }
        });
        return isValid;
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

    function performLoginSuccess(response){


        if(response == "" || response == "false") {
           alert("invalid login credentials");
        }
        else
        {
            $("#loginName").html(`<a>Hello ${response}</a>`);
            $(".log-in").addClass("none");
            $("#AddCar").removeClass("none");
            $(".log-out").removeClass("none");
            $("#formlogin").find(":input").val("");
            $("#loginUser").modal('hide')
        }

    }

    $(function(){
        $("#addCarModal").find(":input").keyup(function(){

            if($(this).val().trim().length >0 && $(this).hasClass("error")){
                $(this).removeClass("error");
            }
        });
    });

})();
