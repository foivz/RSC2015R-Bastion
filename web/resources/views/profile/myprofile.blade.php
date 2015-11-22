@extends('default.layout')

@section('content')

@include('partials.header')

        <!-- ============================================================= MAIN ============================================================= -->

<main>
    <div class="container" style="margin-top: 20px;margin-bottom: 20px;">
        <h1 style="text-align: center">P r o f i l e</h1>
        <div class="avatar" style="text-align: center">
            <img alt="" src="{{ $user->avatar }}" style="height:80px;width: 80px;">
        </div>
        <label>Full Name:</label>
        <input type="text" value="{{ $user->full_name }}">
        <label>Email:</label>
        <input type="text" value="{{ $user->email }}">
        <label>Your Team:</label>
        <input type="text" value="{{ $team->name }}">
    </div>




</main>

<!-- ============================================================= MAIN : END ============================================================= -->

@include('partials.bottomfooter')

@include('includes.scripts')

@endsection