<!DOCTYPE html>
<html>
<head>
    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        #container{
            height:100%;
            width:100%;
        }
        #map {
            height:100%;
            width: 80%;
        }
        #content{
            width: 30%;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>


</head>


<body>
<div id="container">

    <div id="map"></div>



</div>


<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_VJESZUU2Dqfatb_5YPr1U5H8bv7oY_o&"></script>



<script src="{{asset('prikazAdmin.js')}}"></script>
</body>
</html>