<%--
  Created by IntelliJ IDEA.
  User: bishwa
  Date: 7/13/2018
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li><a href="#">Register</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
        <div id="content-wrapper">
            <div id="aside"></div>
            <div id="contents">
                <div id="content">
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/a.jpg" alt=""></img>
                        </div>
                       <div class="info-wrapper">
                           <h4>Example heading</h4>
                           <h4>Example heading</h4>
                           <button type="button" class="btn btn-success">View owner</button>
                           <div class="owner-info-wrapper">

                           </div>
                       </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/a.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/b.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/c.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/b.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/a.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/c.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/a.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                    <div class="car-info-wrapper">
                        <div class="image-wrapper">
                            <img src="resources/images/b.jpg" alt=""></img>
                        </div>
                        <div class="info-wrapper">
                            <h4>Example heading</h4>
                            <h4>Example heading</h4>
                            <button type="button" class="btn btn-success">Success</button>
                            <div class="owner-info-wrapper">

                            </div>
                        </div>

                    </div>
                </div>
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
    </div>
</body>
</html>
