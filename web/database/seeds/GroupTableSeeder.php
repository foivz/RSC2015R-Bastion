<?php

use Illuminate\Database\Seeder;

// composer require laracasts/testdummy
use Laracasts\TestDummy\Factory as TestDummy;

class GroupTableSeeder extends Seeder
{
    public function run()
    {
        factory(App\Group::class, 10)->create();
    }
}
