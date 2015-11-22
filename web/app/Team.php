<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Team extends Model
{
    public function users()
    {
        return $this->hasMany('App\User');
    }

    public function groups()
    {
        return $this->hasMany('App\Group');
    }

    public function game() {
        return $this->hasOne('App\Game');
    }
}
