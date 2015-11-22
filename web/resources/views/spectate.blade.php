@extends('default.defaultadmin')

@section('content')
    @include('partials.headeradmin')



    <body>
    <div id="container" style="margin-top:20px;">
        <div class="container">
            <div id="Team1">
                <input type="text" id="team1id">
            </div>

            <div id="Team2">
                <input type="text" id="team2id">
            </div>
        </div>
            <div id="map"></div>





    </div>





    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_VJESZUU2Dqfatb_5YPr1U5H8bv7oY_o&"></script>



    <script src="{{asset('prikazIgre.js')}}"></script>

@endsection