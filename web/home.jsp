<%--
  Created by IntelliJ IDEA.
  User: bishwa
  Date: 7/13/2018
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BuyAndSale</title>

    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>



    <!-- custom js and css -->
    <link rel="stylesheet" href="resources/css/style.css">
    <script src="resources/js/script.js"></script>
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class=""><a href="#">Home <span class="sr-only">(current)</span></a></li>
                        <li id="AddCar"><a href="#">Add Car</a></li>
                        <li ><a href="#">About Us</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Log In</a></li>
                        <li id="Register"><a href="#">Register</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div id="content-wrapper">
            <div id="aside">
                <div class="filter">
                    <h2>Filter</h2>
                    <form>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="minPrice">Min</label>
                                    <input type="number" class="form-control" id="minPrice" placeholder="0">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="maxPrice">Max</label>
                                    <input type="number" class="form-control" id="maxPrice" placeholder="~">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="brand">Brand</label>
                                    <select id="brand" multiple class="form-control">
                                        <option selected>Any</option>
                                        <c:forEach items="${carBrands}" var="brand">
                                            <option>${brand}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="shape">Shape</label>
                                    <select id="shape" multiple class="form-control">
                                        <option selected>Any</option>
                                        <c:forEach items="${carShapes}" var="shape">
                                            <option>${shape}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <button id="btnReset" type="button" class="btn btn-default">Reset</button>
                        <button id="btnSearch" type="button" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                            Search
                        </button>
                    </form>
                </div>
            </div>
            <div id="contents">
                <div class="progress" id="top-loader">
                    <div class="progress-bar progress-bar-striped active" role="progressbar"
                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
                    </div>
                </div>
                <div id="content"></div>
            </div>
        </div>
        <footer>
            <p>@Buy And Sale</p>
        </footer>

        <!-- Modals -->
        <div class="modal fade" id="addCarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCarModalLabel">Add Car</h5>
                        <button type="button" class="close" data-dismiss="model" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Car Model:</label>
                                <input type="text" class="form-control" id="recipient-name">
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Message:</label>
                                <textarea class="form-control" id="message-text"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="model">Close</button>
                        <button type="button" class="btn btn-primary">Add Car</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="registerUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="first-name" class="col-form-label">First Name:</label>
                                <input type="text" class="form-control" id="first-name">
                            </div>
                            <div class="form-group">
                                <label for="last-name" class="col-form-label">Last Name:</label>
                                <textarea class="form-control" id="last-name"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Send message</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="carInfoModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 img">
                                <img src="resources/images/b.jpg" alt="">
                            </div>
                            <div class="col-md-6">
                                <h2 id="brandModel">Mercedes CLK, 2006</h2>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped">
                                        <tbody>
                                            <tr>
                                                <th>Plate</th>
                                                <td id="licenseNo">UAW 805B</td>
                                            </tr>
                                            <tr>
                                                <th>Color</th>
                                                <td id="carColor">Black</td>
                                            </tr>
                                            <tr>
                                                <th>Shape</th>
                                                <td id="carShape">SUV</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div>
                                    <h3 class="price">
                                        <span class="label label-success">
                                            <span class="dollar">$</span>
                                            <span id="carPrice">30000</span>
                                        </span>
                                    </h3>
                                    <div class="owner-info">
                                        <div class="row">
                                            <span class="glyphicon glyphicon-user color-green" aria-hidden="true"></span>
                                            <span id="ownerName">Silas Kaggwa</span>
                                        </div>
                                        <div class="row">
                                            <span class="glyphicon glyphicon-envelope color-green" aria-hidden="true"></span>
                                            <span id="ownerEmail">silakag@gmail.com</span>
                                        </div>
                                        <div class="row">
                                            <span class="glyphicon glyphicon-earphone color-green" aria-hidden="true"></span>
                                            <span id="ownerPhone">+1 641 954 6999</span>
                                        </div>
                                        <div class="row">
                                            <span class="glyphicon glyphicon-map-marker color-green" aria-hidden="true"></span>
                                            <span id="ownerAddress">Fairfeild, Iowa</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
