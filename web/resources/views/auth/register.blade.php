@extends('default.layout')

<link href="{{ asset('css/login.css') }}" rel="stylesheet">

@section('content')

@include('partials.header')

        <!-- ============================================================= MAIN ============================================================= -->

<main>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

    <section style="margin-top: 50px;margin-bottom: 50px">
        <div class="container" >


            <div class="omb_login">
                <h3 class="omb_authTitle">Haven't an account already? Sign up then!</h3>

                <div class="row omb_row-sm-offset-3">
                    <div class="col-xs-12 col-sm-6">

                        <form class="omb_loginForm" method="post" action="{{ url('/auth/register') }}" >
                            @if (count($errors) > 0)
                                <div class="alert alert-danger">
                                    <ul>
                                        @foreach ($errors->all() as $error)
                                            <li>{{ $error }}</li>
                                        @endforeach
                                    </ul>
                                </div>
                            @endif
                            {!! csrf_field() !!}
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" class="form-control" name="email" placeholder="Email address" value="{{ old('email') }}">
                            </div>
                            <span class="help-block"></span>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input  type="password" class="form-control" name="password" placeholder="Password">
                            </div>
                            <span class="help-block"></span>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input  type="password" class="form-control" name="password_confirmation" placeholder="Password confirmation">
                            </div>
                            <span class="help-block"></span>
                            <!--
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" class="form-control" name="first_name" placeholder="First name">
                            </div>
                            <span class="help-block"></span>

                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" class="form-control" name="last_name" placeholder="Last name">
                            </div>
                            <span class="help-block"></span>
                            -->

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
                        </form>
                    </div>
                </div>

            </div>



        </div>
    </section>
</main>

<!-- ============================================================= MAIN : END ============================================================= -->

@include('partials.footer')

@include('includes.scripts')


@endsection