@extends('default.layout')

@section('content')

@include('partials.header')

        <!-- ============================================================= MAIN ============================================================= -->

<main>

    <!-- ============================================================= SECTION – HERO ============================================================= -->

    <section id="hero">
        <div id="owl-main" class="owl-carousel height-md owl-inner-nav owl-ui-lg">

            <div class="item" style="background-image: url('{{ asset('images/art/paintball3.jpg') }}');">
                <div class="container">
                    <div class="caption vertical-center text-center">

                        <h1 class="fadeInDown-1 light-color" >Paintball is cool</h1>
                        <p class="fadeInDown-2 medium-color" >Be a spectator! Watch your team moves!
                        </p>
                        <div class="fadeInDown-3">
                            <a href="{{url('view')}}" class="btn btn-large">Click to spectate</a>
                        </div>

                    </div>
                </div>
            </div>

            <div class="item" style="background-image: url('{{ asset('images/art/paintball4.jpg') }}');">
                <div class="container">
                    <div class="caption vertical-center text-left">

                        <h1 class="fadeInLeft-1 light-color">See your <br>stats! Admire yourself!</h1>
                        <p class="fadeInLeft-2 light-color"><br>Login and check it out!
                        </p>
                        <div class="fadeInLeft-3">
                            <a href="{{url('auth/login')}}" class="btn btn-large">Login</a>
                        </div>

                    </div>
                </div>
            </div>

            <div class="item" style="background-image: url('{{ asset('images/art/paintball5.jpg') }}');">
                <div class="container">
                    <div class="caption vertical-center text-left">

                        <h1 class="fadeInRight-1 dark-bg light-color"><span>Android<br> and iOS</span></h1>
                        <p class="fadeInRight-2 dark-color">Download mobile application!
                        </p>


                    </div>
                </div>
            </div>

        </div>
    </section>

    <!-- ============================================================= SECTION – HERO : END ============================================================= -->


    <!-- ============================================================= SECTION – GET IN TOUCH ============================================================= -->

    <section id="get-in-touch" class="inner-bottom" style="margin-top: 20px">
        <div class="container inner light-bg">
            <div class="row">
                <div class="col-md-8 col-sm-9 center-block text-center">
                    <header>
                        <h1>Want to watch live battle!</h1>
                        <p>Wanna see your favourite team fighting against others? If yes, press the button below and get popcorns!</p>
                    </header>
                    <a href="{{ url('/view') }}" class="btn btn-large">Spectate</a>
                </div>
            </div>
        </div>
    </section>

    <!-- ============================================================= SECTION – GET IN TOUCH : END ============================================================= -->

</main>

<!-- ============================================================= MAIN : END ============================================================= -->

@include('partials.footer')

@include('includes.scripts')

@endsection