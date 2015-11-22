@extends('default.defaultadmin')

@section('content')

@include('partials.headeradmin')

        <!-- ============================================================= MAIN ============================================================= -->

<main>
<div class="container" style="margin-top: 20px;">
    <h1>Creating a new game</h1>
    <div class="alert-success" style="margin-bottom: 10px;">
        @if(Session::has('message'))
            {{ Session::get('message') }}
            @endif
    </div>
    <form method="post" action="{{ url('game') }}">
        <label>Team 1:</label>
        <select name="team1">
            @foreach($teams as $team)
                <option value="{{ $team->id }}">{{$team->name}}</option>
            @endforeach
        </select>
        <br>
        <label>Team 2:</label>
        <select name="team2">
            @foreach($teams as $team)
                <option value="{{ $team->id }}">{{$team->name}}</option>
            @endforeach
        </select>
        <br>
        <label>Choose map:</label>
        <select name="map">
            @foreach($maps as $map)
            <option value="{{ $map->id }}">{{$map->name}}</option>
                @endforeach
        </select>
        <br>
        <button type="submit" class="btn btn-success">Create a game</button>
    </form>
</div>


</main>

<!-- ============================================================= MAIN : END ============================================================= -->


@include('includes.scripts')

@endsection