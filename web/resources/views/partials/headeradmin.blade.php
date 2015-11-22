<!-- ============================================================= HEADER ============================================================= -->

<header>
    <div class="navbar">



        <div class="yamm">
            <div class="navbar-collapse collapse">
                <div class="container">

                    <!-- ============================================================= LOGO ============================================================= -->

                    <a class="navbar-brand" href="{{ url('/') }}"><img src="{{ asset('images/splat.svg') }}" class="logo" alt=""></a>

                    <!-- ============================================================= LOGO : END ============================================================= -->


                    <!-- ============================================================= MAIN NAVIGATION ============================================================= -->

                    <ul class="nav navbar-nav">

                        <li >
                            <a href="{{ url('/') }}" >Home</a>

                        </li>

                        <li >
                            <a href="{{ url('/test') }}" >Create map</a>

                        </li>

                        <li >
                            <a href="{{ url('/game') }}" >Create game</a>

                        </li>






                        @if(!Auth::check())
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle js-activated">Login</a>

                            <ul class="dropdown-menu">
                                <li><a href="{{ url('auth/login') }}">Sign in</a></li>
                                <li><a href="{{ url('auth/register') }}">Sign up</a></li>
                            </ul><!-- /.dropdown-menu -->
                        </li><!-- /.dropdown -->
                            @else
                            <li >
                                <a href="{{ url('/auth/logout') }}" >Logout</a>

                            </li>
                        @endif

                        <li class="dropdown pull-right searchbox">
                            <a href="#" class="dropdown-toggle js-activated"><i class="icon-search"></i></a>

                            <div class="dropdown-menu">
                                <form id="search" class="navbar-form search" role="search">
                                    <input type="search" class="form-control" placeholder="Type to search">
                                    <button type="submit" class="btn btn-default btn-submit icon-right-open"></button>
                                </form>
                            </div>
                        </li>

                    </ul>

                    <!-- ============================================================= MAIN NAVIGATION : END ============================================================= -->

                </div>
            </div>
        </div>
    </div>
</header>

<!-- ============================================================= HEADER : END ============================================================= -->