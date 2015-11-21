<!DOCTYPE html>

<html lang="en">
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="description" content="">
    <meta name="author" content="Bastion">

    <title>Bastion</title>

    <!-- Bootstrap Core CSS -->
    <link href="{{ asset('css/bootstrap.min.css') }}" rel="stylesheet">

    <!-- Customizable CSS -->
    <link href="{{ asset('css/main.css') }}" rel="stylesheet" data-skrollr-stylesheet>
    <link href="{{ asset('css/green.css') }}" rel="stylesheet" title="Color">
    <link href="{{ asset('css/owl.carousel.css') }}" rel="stylesheet">
    <link href="{{ asset('css/owl.transitions.css') }}" rel="stylesheet">
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