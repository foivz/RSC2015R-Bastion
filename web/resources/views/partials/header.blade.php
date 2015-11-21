<!-- ============================================================= HEADER ============================================================= -->

<header>
    <div class="navbar">

        <div class="navbar-header">
            <div class="container">

                <ul class="info pull-left">
                    <li><a href="{{ url('/') }}"><i class="icon-mail contact"></i> bastion@gmail.com</a></li>
                    <li><i class="icon-mobile contact"></i> +385 097 615 5988</li>
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

                <a class="navbar-brand" href="{{ url('/') }}"><img src="{{ asset('images/logo.svg') }}" class="logo" alt=""></a>

                <!-- ============================================================= LOGO MOBILE : END ============================================================= -->

                <a class="btn responsive-menu pull-right" data-toggle="collapse" data-target=".navbar-collapse"><i class='icon-menu-1'></i></a>

            </div>
        </div>

        <div class="yamm">
            <div class="navbar-collapse collapse">
                <div class="container">

                    <!-- ============================================================= LOGO ============================================================= -->

                    <a class="navbar-brand" href="{{ url('/') }}"><img src="{{ asset('images/logo.svg') }}" class="logo" alt=""></a>

                    <!-- ============================================================= LOGO : END ============================================================= -->


                    <!-- ============================================================= MAIN NAVIGATION ============================================================= -->

                    <ul class="nav navbar-nav">

                        <li >
                            <a href="{{ url('/') }}" >Home</a>

                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle js-activated">Pages</a>

                            <ul class="dropdown-menu">
                                <li><a href="{{ url('/page1') }}">Page1</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle js-activated">Blog</a>

                            <ul class="dropdown-menu">
                                <li><a href="{{ url('/') }}">Sidebar right</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle js-activated">Pages</a>

                            <ul class="dropdown-menu">

                                <li class="dropdown-submenu">
                                    <a href="#">One Page</a>

                                    <ul class="dropdown-menu">
                                        <li><a href="{{ url('/') }}">Product Style</a></li>
                                    </ul>
                                </li>

                                <li><a href="{{ url('/') }}">About I</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle js-activated">Features</a>

                            <ul class="dropdown-menu">

                                <li><a href="{{ url('/') }}">Slider/Carousel</a></li>

                                <li class="dropdown-submenu">
                                    <a href="#">Colors</a>

                                    <ul class="dropdown-menu">
                                        <li><a class="changecolor green" title="Green color">Green</a></li>
                                    </ul>
                                </li>

                            </ul>
                        </li>

                        <!-- ============================================================= MEGA MENU ============================================================= -->

                        <li class="dropdown yamm-fullwidth">
                            <a href="#" class="dropdown-toggle js-activated">Mega Menu</a>

                            <ul class="dropdown-menu yamm-dropdown-menu">
                                <li>
                                    <div class="yamm-content row">

                                        <div class="col-sm-3 inner">
                                            <h4>Focus on</h4>
                                            <figure>
                                                <div class="icon-overlay icn-link">
                                                    <a href="{{ url('/') }}"><img src="{{ asset('images/art/work01.jpg') }}" alt=""></a>
                                                </div>
                                                <figcaption>
                                                    <p>Consed quodips ameniat empernam que apid cust quas molor eatis numa estio.</p>
                                                    <a href="{{ url('/') }}" class="btn">View Project</a>
                                                </figcaption>
                                            </figure>
                                        </div>

                                        <div class="col-sm-3 inner">
                                            <h4>Special Pages</h4>

                                            <ul class="circled">
                                                <li><a href="{{ url('/') }}">3 Columns Details Grid Portfolio</a></li>
                                                <li><a href="{{ url('/') }}">Fullscreen Grid Portfolio</a></li>
                                            </ul>
                                        </div>

                                        <div class="col-sm-3 inner">
                                            <h4>Latest Works</h4>

                                            <div class="row thumbs gap-xs">

                                                <div class="col-xs-6 thumb">
                                                    <figure class="icon-overlay icn-link">
                                                        <a href="{{ url('/') }}"><img src="{{ asset('images/art/work02.jpg') }}" alt=""></a>
                                                    </figure>
                                                </div>

                                                <div class="col-xs-6 thumb">
                                                    <figure class="icon-overlay icn-link">
                                                        <a href="{{ url('/') }}"><img src="{{ asset('images/art/work03.jpg') }}" alt=""></a>
                                                    </figure>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="col-sm-3 inner">
                                            <h4>About Us</h4>
                                            <p>Voluptat ibusaped molorporro consequ idustibus. Reressi morum ut
                                                dolessiti tem nihicid ernatum, coria volore non pro officat ut autem
                                                accaborem conet. Omnis peribus qui dolent praeperrum coria.</p>
                                            <p>Equam conesti occum dolorest, quae venderes quistius, comnitatur sae
                                                dinam nonseculpa cum fugit is verciam.</p>
                                            <a href="{{ url('/') }}" class="btn">Read More</a>
                                        </div>

                                    </div>
                                </li>
                            </ul>
                        </li>

                        <!-- ============================================================= MEGA MENU : END ============================================================= -->
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