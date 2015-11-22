<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Game extends Model
{
    public function map () {
        return $this->belongsTo('App\Map');
    }

    public function teams () {
        return $this->hasMany('App\Team');
    }
}
