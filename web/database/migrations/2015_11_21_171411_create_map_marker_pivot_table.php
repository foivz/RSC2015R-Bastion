<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateMapMarkerPivotTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('map_marker', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('map_id')->unsigned()->index();
            $table->string('long');
            $table->string('lat');
            $table->foreign('map_id')->references('id')->on('maps')->onDelete('cascade');
            $table->integer('marker_id')->unsigned()->index();
            $table->foreign('marker_id')->references('id')->on('markers')->onDelete('cascade');

        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('map_marker');
    }
}
