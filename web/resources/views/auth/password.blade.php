@extends('default.layout')

@section('content')

        <!-- ============================================================= MAIN ============================================================= -->



    <section style="margin-top: 50px;margin-bottom: 50px">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1>Reset you password</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <p class="text-center">Use the form below to enter your email address. We will send you password reset link.</p>
                    <form method="post" id="passwordForm" action="{{ url('password/email') }}">
                        @if (count($errors) > 0)
                            <div class="alert alert-danger">
                                <ul>
                                    @foreach ($errors->all() as $error)
                                        <li>{{ $error }}</li>
                                    @endforeach
                                </ul>
                            </div>
                        @endif
                        @if (session('status'))
                            <div class="alert alert-success">
                                {{ session('status') }}
                            </div>
                        @endif
                            {!! csrf_field() !!}
                        <input type="email" class="input-lg form-control" name="email" id="email" placeholder="Your email address" autocomplete="off" value="{{ old('email') }}">

                        <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg" value="Send password reset link">
                    </form>
                </div><!--/col-sm-6-->
            </div><!--/row-->
        </div>
    </section>


<!-- ============================================================= MAIN : END ============================================================= -->


@endsection