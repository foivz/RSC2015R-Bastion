@extends('default.layout')

<link href="{{ asset('css/login.css') }}" rel="stylesheet">

@section('content')

    @include('partials.header')

        <!-- ============================================================= MAIN ============================================================= -->
    <main>
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

        <section style="margin-top: 50px;margin-bottom: 50px">
            <div class="container" >

                <div class="alert-danger">

                </div>
                <div class="omb_login">
                    <h3 class="omb_authTitle">Login or <a href="{{ url('auth/register') }}">Sign up</a></h3>
                    <div class="row omb_row-sm-offset-3 omb_socialButtons">
                        <div class="col-xs-6 col-sm-3">
                            <a href="{{ url('api/login/facebook') }}" class="btn btn-lg btn-block omb_btn-facebook">
                                <i class="fa fa-facebook visible-xs"></i>
                                <span class="hidden-xs">Facebook</span>
                            </a>
                        </div>
                        <div class="col-xs-6 col-sm-3">
                            <a href="{{ url('api/login/twitter') }}" class="btn btn-lg btn-block omb_btn-twitter">
                                <i class="fa fa-twitter visible-xs"></i>
                                <span class="hidden-xs">Twitter</span>
                            </a>
                        </div>

                    </div>

                    <div class="row omb_row-sm-offset-3 omb_loginOr">
                        <div class="col-xs-12 col-sm-6">
                            <hr class="omb_hrOr">
                            <span class="omb_spanOr">or</span>

                        </div>

                    </div>

                    <div class="row omb_row-sm-offset-3">
                        <div class="col-xs-12 col-sm-6">
                            <form class="omb_loginForm" action="{{ url('/auth/login') }}" method="post" >
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
                                    <label>
                                        <input type="checkbox" name="remember" value="true">Remember me
                                    </label>
                                <button class="btn btn-lg btn-primary btn-block" type="submit" id="login">Login</button>
                            </form>
                        </div>
                    </div>
                    <div class="row omb_row-sm-offset-3">
                        <div class="col-xs-12 col-sm-3">

                        </div>
                        <div class="col-xs-12 col-sm-3">
                            <p class="omb_forgotPwd">
                                <a href="{{ url('password/email') }}">Forgot password?</a>
                            </p>
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