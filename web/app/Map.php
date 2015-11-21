<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Map extends Model
{
    public function markers () {
        return $this->belongsToMany('App\Marker')->withPivot('long','lat');
    }

    public function games () {
        return $this->hasMany('App\Game');
    }
}
