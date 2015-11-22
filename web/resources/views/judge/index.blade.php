@extends('default.layout')

@section('content')

@include('partials.headeradmin')

        <!-- ============================================================= MAIN ============================================================= -->

<div class="container" style="margin-top: 20px;">
        <h1>Dashboard</h1>
        <div class="row">
                <div class="col-md-6">
                        <h3>Total number of created games: {{$countGames}}</h3>
                </div>
                <div class="col-md-6">
                        <h3>Total number of registered players: {{$countUsers}}</h3>
                </div>

        </div>

        <div id="chart1"> </div>
</div>








<!-- ============================================================= MAIN : END ============================================================= -->



@include('includes.scripts')
<script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>

@endsection