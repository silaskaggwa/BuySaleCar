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
            var firstname = $("#first-name").val();
            var lastname = $("#last-name").val();
            var email = $("#email").val();
            var phoneNumber = $("#phone-number").val();
            var address = $("#address").val();
            var usr = $("#user-name").val();
            var pswd = $("#pswd").val();

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
                    debugger;
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
            var uName = $("#username").val();
            var pwd = $("#passWord").val();
            $.ajax({
                method: 'POST',
                url: 'home',
                data: {
                   "userName": uName,
                    "passWord":pwd
                },
                success: function (response) {
                    debugger;
                    $("#formlogin").hide();
                    $("#login-msg").show();
                    if(response == "true") {
                        $("#login-msg").html("Login success")
                    }
                    else
                    {
                        $("#login-msg").html("Login failed")
                    }
                   setTimeout(function () {
                       $("#loginUser").modal('hide')
                   }, 1000);
                },
                error: function (error) {
                    $("#formlogin").hide();
                    $("#login-msg").show();
                    $("#login-msg").html("Login failed")
                    setTimeout(function () {
                        $("#loginUser").modal('hide')
                    }, 1000);
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


        fetchAllCars();
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

})();