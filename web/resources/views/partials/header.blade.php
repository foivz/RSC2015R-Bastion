<!-- ============================================================= HEADER ============================================================= -->

<header>
    <div class="navbar">

        <div class="navbar-header">
            <div class="container">

                <ul class="info pull-left">
                    <li><a href="{{ url('/') }}"><i class="icon-mail contact"></i> acredo@gmail.com</a></li>
                    <li><i class="icon-mobile contact"></i> +385 345 233 4344</li>
                </ul>

                <ul class="social pull-right">
                    <li><a href="https://www.facebook.com/"><i class="icon-s-facebook"></i></a></li>
                    <li><a href="https://www.google.hr/"><i class="icon-s-gplus"></i></a></li>
                    <li><a href="https://twitter.com/"><i class="icon-s-twitter"></i></a></li>
                    <li><a href="https://www.pinterest.com/"><i class="icon-s-pinterest"></i></a></li>
                    <li><a href="https://www.behance.net/"><i class="icon-s-behance"></i></a></li>
                    <li><a href="https://dribbble.com/"><i class="icon-s-dribbble"></i></a></li>

                </ul>

                <!-- ============================================================= LOGO MOBILE ============================================================= -->

                <a class="navbar-brand" href="{{ url('/') }}"><img src="{{ asset('images/splat.svg') }}" class="logo" alt=""></a>

                <!-- ============================================================= LOGO MOBILE : END ============================================================= -->

                <a class="btn responsive-menu pull-right" data-toggle="collapse" data-target=".navbar-collapse"><i class='icon-menu-1'></i></a>

            </div>
        </div>

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
                            <a href="{{ url('/view') }}" >Spectate</a>

                        </li>


                        <!-- ============================================================= MEGA MENU : END ============================================================= -->

                        @if(Auth::check())
                            <li >
                                <a href="{{ url('/profile') }}" >Profile</a>
                            </li>
                        @endif

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