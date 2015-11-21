<?php

namespace App\Http\Controllers;

use App\Game;
use App\Map;
use App\Marker;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\DB;

class GameController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request){
        //$user->providers()->attach(Provider::where('name', $provider)->pluck('id'), array('avatar' => $userData->avatar, 'providerUID' => $userData->id));
        $map = new Map();
        $map->name = $request->input('name');
        $map->duration = $request->input('duration');
        $map->border_north = $request->input('bound_north');
        $map->border_south = $request->input('bound_south');
        $map->border_east = $request->input('bound_east');
        $map->border_west = $request->input('bound_west');
        $markers = json_decode($request->input('markers'),true);
        $map->save();
        //$b = json_decode($markers[0],true);
        /*
        $a = "";
        foreach($markers[0] as $key=>$value){
            $a .= "$value";
        }
    $user->providers()->sync(array($target->id => array('avatar' => $userData->avatar)));
        return $a;
        */
        for($i = 0;$i < count($markers);$i++){

            $a = array();
            foreach ($markers[$i] as $key => $value) {
                $a[$key] = $value;
            }
            $map->markers()->attach(Marker::where('type', $a['type'])->pluck('id'), array('long' => $a['long'], 'lat' => $a['lat']));
        }
    /*
        foreach($markers as $marker) {

            foreach ($marker as $key => $value) {
                $a[$key] = $value;
            }
            //return $a['type'];
            $map->markers()->attach(Marker::where('type', $a['type'])->pluck('id'), array('long' => $a['long'], 'lat' => $a['lat']));
        }
*/


    }

    public function view(){

            $game = Game::where('status',1)->first();
            $map = Map::where('id',$game->map_id)->first();
            $teams = $game->teams()->get();
            $markers = $map->markers()->get();
            $array = array('markers' => $markers, 'map' =>$map,'teams'=> $teams);
        return $array;
    }

}
