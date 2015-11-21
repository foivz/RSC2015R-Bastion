<?php

/*
|--------------------------------------------------------------------------
| Model Factories
|--------------------------------------------------------------------------
|
| Here you may define all of your model factories. Model factories give
| you a convenient way to create models for testing and seeding your
| database. Just tell the factory how a default model should look.
|
*/

/* USERS factory  */
$factory->define(App\User::class, function (Faker\Generator $faker) {
    return [
        'name' => $faker->userName,
        'email' => $faker->email,
        'password' => bcrypt('123456'),
        'full_name' => $faker->name,
        'remember_token' => str_random(10),
    ];
});

/* Teams factory  */
$factory->define(App\Team::class, function (Faker\Generator $faker) {
    return [
        'name' => $faker->name,
    ];
});

/* Groups factory  */
$factory->define(App\Group::class, function (Faker\Generator $faker) {
    return [
        'name' => $faker->name,
    ];
});
