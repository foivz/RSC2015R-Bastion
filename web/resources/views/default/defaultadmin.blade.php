<!DOCTYPE html>

<html lang="en">
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="description" content="">
    <meta name="author" content="Bastion">
    <style>
    html, body {
    height: 100%;
    margin: 0;
    padding: 0px;
    }
    #container{
    height:100%;
    width:80%;
       margin-left: auto;
        margin-right: auto;

    }
    #map {
    height:80%;

        width: 100%;
        margin: 0 auto;
    }
    #content{
    width: 50%;
        margin: 0 auto;
    }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <title>Bastion</title>

    <!-- Bootstrap Core CSS -->
    <link href="{{ asset('css/bootstrap.min.css') }}" rel="stylesheet">

    <!-- Customizable CSS -->
    <link href="{{ asset('css/main.css') }}" rel="stylesheet" data-skrollr-stylesheet>
    <link href="{{ asset('css/green.css') }}" rel="stylesheet" title="Color">
    <link href="{{ asset('css/animate.min.css') }}" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Lato:400,900,300,700" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,400italic,700italic" rel="stylesheet">

    <!-- Icons/Glyphs -->
    <link href="{{ asset('fonts/fontello.css') }}" rel="stylesheet">

    <!-- Favicon -->
    <link rel="shortcut icon" href="{{ asset('images/favicon.ico') }}">

    <!-- HTML5 elements and media queries Support for IE8 : HTML5 shim and Respond.js -->
    <!--[if lt IE 9]>
    <!--<script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script> -->
    <!--[endif]-->

</head>

<body>

@yield('content')

</body>
</html>