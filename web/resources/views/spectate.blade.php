@extends('default.defaultadmin')

@section('content')


    <body>
    <div id="container" style="margin-top:20px;">
        <div class="container">
            <h1 style="text-align: center ">Current active game <a href="{{url('/home')}}" style="color: green"> (Go back)</a> </h1>
            <h1>Game started at: {{ $active->created_at }}</h1>
            <div id="Team1" >
                <label>Team 1:</label>
                <input type="text" id="team1id" style="width:80%;" >
            </div>

            <div id="Team2" >
                <label>Team 2:</label>
                <input type="text" id="team2id" style="width:80%;" >
            </div>
        </div>
            <div id="map"></div>

    </div>





    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_VJESZUU2Dqfatb_5YPr1U5H8bv7oY_o&"></script>


    <script src="{{asset('prikazIgre.js')}}"></script>

@endsection