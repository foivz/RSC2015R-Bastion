<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUsersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {
            $table->increments('id');
            $table->string('name');
            $table->string('email')->unique();
            $table->string('password', 60);
            $table->string('full_name')->nullable();
            $table->text('gcm_id')->nullable();
            $table->string('device')->nullable();
            $table->integer('role')->nullable()->default(1);
            $table->string('long')->nullable();
            $table->string('lat')->nullable();
            $table->integer('status')->default(0);
            $table->integer('team_id')->unsigned();
            $table->rememberToken();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('users');
    }
}
