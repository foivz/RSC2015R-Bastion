<?php

use Illuminate\Database\Seeder;

// composer require laracasts/testdummy
use Laracasts\TestDummy\Factory as TestDummy;

class TeamTableSeeder extends Seeder
{
    public function run()
    {
        factory(App\Team::class, 10)->create();
    }
}
