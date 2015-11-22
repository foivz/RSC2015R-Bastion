<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/test', function () {
    return view('test');
});

Route::get('/view', function () {
    return view('prikaz');
});

Route::get('/home', function () {
    return view('welcome');
});


Route::post('/save', 'GameController@store');

Route::post('/view', 'GameController@view');

Route::get('testaona', function() {
    $team = \App\Team::last();
    return $team;
});


/* AUTHENTICATION ROUTES */
Route::get('auth/login', 'Auth\AuthController@getLogin');
Route::post('auth/login', 'Auth\AuthController@postLogin');
Route::get('auth/logout', 'Auth\AuthController@getLogout');

/* REGISTRATION ROUTES */
Route::get('auth/register', 'Auth\AuthController@getRegister');
Route::post('auth/register', 'Auth\AuthController@postRegister');

/* PASSWORD RESET ROUTES */
Route::get('password/email', 'Auth\PasswordController@getEmail');
Route::post('password/email', 'Auth\PasswordController@postEmail');
Route::get('password/reset/{token}', 'Auth\PasswordController@getReset');
Route::post('password/reset', 'Auth\PasswordController@postReset');

/* TESTING ROUTES FOR PUSHING NOTIFICATIONS */
Route::get('/asend', function () {
    PushNotification::app('android')
        ->to('dFN7LCn_JRc:APA91bHuLZh4GPtfwpW0I3-5s62lTOjKpWfkuRUWuY7eLs-EsW-CQLC3wi9a9IvOvMmsHAMeVDW1iPJJMK1BcERqWYjw4F4mWp-BQKK1aM6XY91rDLksAkIQRomkpyV_UopLqwVgThwP')
        ->send('bozo');
});

Route::get('/isend', function () {
    PushNotification::app('iOS')
        ->to('3ad7df4eade61c36b00a4c1bd57bd562ec9f2835276090a63cce9333acc97dfd')
        ->send('iOS Apns testna poruka');
});

/* API ROUTES FOR MOBILE - JSON WEB TOKEN */
Route::group(['prefix' => 'api'], function()
{

    Route::post('login', 'Api\AuthController@login');

    Route::match(['get','post'],'login/{provider?}','Api\AuthController@loginProvider');

    Route::post('register', 'Api\AuthController@register');

    Route::post('logout', 'Api\AuthController@logout');

    Route::group(['middleware' => ['jwt.auth']], function() {

        Route::get('teams', function (){
            $teams = App\Team::all();
            $responseArray = array('status' => 'OK', 'message' => 'Okay','data' => $teams);
            return json_encode($responseArray);
        });

        Route::resource('team','TeamController');

        Route::post('join/{id}', function ($id){
            //$team_leader = Auth::user()->gcm_id;
            $team = \App\Team::where('id',$id)->first();
            $teamLeader = $team->team_leader;
            $user = App\User::find(Auth::user()->id);
            $user->team_id = $id;
            $user->save();

            $user1 = App\User::where('email',$teamLeader)->first();

            if($user1->device == 'android') {
                PushNotification::app('android')
                    ->to($user1->gcm_id)
                    ->send('Novi igrač želi u vaš team!');
            } else {
                PushNotification::app('iOS')
                    ->to($user1->gcm_id)
                    ->send('Novi igrač želi u vaš team!');
            }

            $responseArray = array('status' => 'OK', 'message' => 'Okay','data' => "");
            return json_encode($responseArray);
        });

        Route::get('applications/{id}', function($id) {
            $users = \App\User::where('team_id',$id)->get();
            return $users;
        });


        Route::get('/myplayers', 'TeamController@players');
        Route::get('/lockteam','TeamController@lock');


    });
});




