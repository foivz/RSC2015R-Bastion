@extends('default.defaultadmin')

@section('content')

@include('partials.headeradmin')

        <!-- ============================================================= MAIN ============================================================= -->

<main>
<div class="container" style="margin-top: 20px;">
    <h1>Creating a new game</h1>
    <form>
        <label>Team 1:</label>
        <input type="text">
        <label>Team 2:</label>
        <input type="text">
        <label>Choose map:</label>
        <select>
            <option>Map1</option>
            <option>Map2</option>
        </select>
    </form>
</div>


</main>

<!-- ============================================================= MAIN : END ============================================================= -->


@include('includes.scripts')

@endsection