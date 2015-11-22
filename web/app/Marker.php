<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Marker extends Model
{
    public function maps() {
        return $this->belongsToMany('App\Map')->withPivot('long','lat');
    }
}
