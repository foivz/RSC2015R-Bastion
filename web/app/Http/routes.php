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

/* API ROUTES FOR MOBILE - JSON WEB TOKEN */
Route::group(['prefix' => 'api'], function()
{

    Route::post('login', 'Api\AuthController@login');

    Route::match(['get','post'],'login/{provider?}','Api\AuthController@loginProvider');

    /*
    Route::get('login/{provider?}','Api\AuthController@loginProvider');

    Route::post('login/{provider?}', 'Api\AuthController@loginProvider');
    */

    Route::post('register', 'Api\AuthController@register');

    Route::post('logout', 'Api\AuthController@logout');

    Route::group(['middleware' => ['jwt.auth', 'jwt.refresh']], function() {

        Route::get('test', function(){
            return App\User::all();
            //return response()->json(['foo'=>'bar']);
        });

    });
});
