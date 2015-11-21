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
        ->to('flTgVB-VFQY:APA91bG7QF6CwNZOt-KQ6q2dJxtAM_y-yaf_dwiIwR07I_mD3b760qlE5XHrnMbJbCPb2xoVkksJhhb45mEe7IzAmImfDzDxvbyB3bBsNMQaXwR_r4NyoVNmnDpryxzeeEm8ybnPn-bJ')
        ->send('Android GCM testna poruka');
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

    Route::group(['middleware' => ['jwt.auth', 'jwt.refresh']], function() {

        Route::get('teams', function (){
            $teams = App\Team::all();
            $responseArray = array('status' => 'OK', 'message' => 'Okay','data' => $teams);
            return json_encode($responseArray);
        });

        Route::resource('team','TeamController');


    });
});




