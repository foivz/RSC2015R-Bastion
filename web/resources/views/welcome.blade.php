@extends('default.layout')

@section('content')

@include('partials.header')

        <!-- ============================================================= MAIN ============================================================= -->

<main>

    <!-- ============================================================= SECTION – HERO ============================================================= -->

    <section id="hero">
        <div id="owl-main" class="owl-carousel height-md owl-inner-nav owl-ui-lg">

            <div class="item" style="background-image: url('{{ asset('images/art/slider01.jpg') }}');">
                <div class="container">
                    <div class="caption vertical-center text-center">

                        <h1 class="fadeInDown-1 light-color">Paintball is cool</h1>
                        <p class="fadeInDown-2 medium-color">Watch your favourite team!
                        </p>
                        <div class="fadeInDown-3">
                            <a href="#" class="btn btn-large">text</a>
                        </div>

                    </div>
                </div>
            </div>

            <div class="item" style="background-image: url('{{ asset('images/art/slider02.jpg') }}');">
                <div class="container">
                    <div class="caption vertical-center text-right">

                        <h1 class="fadeInLeft-1 light-color">text <br>text</h1>
                        <p class="fadeInLeft-2 light-color">text<br>tekst tekst
                        </p>
                        <div class="fadeInLeft-3">
                            <a href="#" class="btn btn-large">text</a>
                        </div>

                    </div>
                </div>
            </div>

            <div class="item" style="background-image: url('{{ asset('images/art/slider03.jpg') }}');">
                <div class="container">
                    <div class="caption vertical-center text-left">

                        <h1 class="fadeInRight-1 dark-bg light-color"><span>text<br>text</span></h1>
                        <p class="fadeInRight-2 dark-color">Some text
                        </p>
                        <div class="fadeInRight-3">
                            <a href="#" class="btn btn-large">Text</a>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </section>

    <!-- ============================================================= SECTION – HERO : END ============================================================= -->


    <!-- ============================================================= SECTION – GET IN TOUCH ============================================================= -->

    <section id="get-in-touch" class="inner-bottom">
        <div class="container inner light-bg">
            <div class="row">
                <div class="col-md-8 col-sm-9 center-block text-center">
                    <header>
                        <h1>Want to work with us?</h1>
                        <p>Magnis modipsae que voloratati andigen daepeditem quiate re porem aut labor.
                            Laceaque quiae sitiorem rest non restibusaes maio es dem tumquam.</p>
                    </header>
                    <a href="{{ url('/contact') }}" class="btn btn-large">Get in touch</a>
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