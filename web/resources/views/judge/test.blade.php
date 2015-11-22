@extends('default.defaultadmin')

@section('content')
    @include('partials.headeradmin')



<div  id="container" style="margin-top:20px;">

    <div id="map" ></div>
    <div id="content">
        <div style="margin: 0 auto; text-align: center">
        <button id="borders" ><img src="images/rsz_border-256x256.png"></button>
        <button id="borders2" ><img src="images/rsz_border-256x256.png"></button>
        <button id="borders3" ><img src="http://maps.google.com/mapfiles/kml/pal3/icon48.png"></button>
        <button id="borders4" ><img src="http://maps.google.com/mapfiles/kml/pal2/icon4.png"></button>
        <button id="borders9" ><img src="http://maps.google.com/mapfiles/kml/pal2/icon13.png"></button>
        <button id="borders5" ><img src="http://maps.google.com/mapfiles/kml/paddle/blu-circle-lv.png"></button>
        <button id="borders6" ><img src="http://maps.google.com/mapfiles/kml/paddle/red-circle-lv.png"></button>

        <button id="borders7" ><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/128/save.png"
                    style="height:20px;width:20px;"></button>
        <button id="borders8" ><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/128/cancel.png"
                                    style="height:20px;width:20px;"></button>
        </div>
    </div>


    <div>

        <h1>Naziv</h1>
        <input type="text" id="naziv">
        <h1>Trajanje (h)</h1>
        <input type="text" id="trajanje">
    </div>

</div>




<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_VJESZUU2Dqfatb_5YPr1U5H8bv7oY_o&"></script>



<script src="{{asset('test.js')}}"></script>

@endsection