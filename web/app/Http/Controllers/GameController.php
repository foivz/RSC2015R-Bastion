<?php

namespace App\Http\Controllers;

use App\Game;
use App\Map;
use App\Marker;
use App\Team;

use App\User;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Witty\LaravelPushNotification\PushNotification;

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

            for($i = 0;$i < count($teams);$i++){
                $teams[$i]->players = $teams[$i]->users()->get();
            }

            $array = array('markers' => $markers, 'map' =>$map,'teams'=> $teams);
        return json_encode($array);
    }

    public function createGame(Request $request) {
        $game = new Game();
        $game->status = 1;
        $game->map_id = $request->input('map');
        $game->save();
        $idGame = $game->id;

        $idTeam1 = $request->input('team1');
        $idTeam2 = $request->input('team2');
        $team1 = Team::find($idTeam1);
        $team1->game_id = $idGame;
        $team1->save();

        $players = $team1->users()->get();
        foreach($players as $p){
            if($p->gcm_id){
                if($p->device == 'android') {
                    PushNotification::app('android')
                        ->to($p->gcm_id)
                        ->send("Igra pocinje!");
                } else {
                    PushNotification::app('iOS')
                        ->to($p->gcm_id)
                        ->send("Igra pocinje!");
                }
            }
        }

        $team2 = Team::find($idTeam2);
        $team2->game_id = $idGame;
        $team2->save();

        $players = $team2->users()->get();
        foreach($players as $p){
            if($p->gcm_id){
                if($p->device == 'android') {
                    PushNotification::app('android')
                        ->to($p->gcm_id)
                        ->send("Igra pocinje!");
                } else {
                    PushNotification::app('iOS')
                        ->to($p->gcm_id)
                        ->send("Igra pocinje!");
                }
            }
        }

        return redirect('game')->with('message','Succesfully created game!');
    }


    public function viewMy($id){
        $user = User::where('email',$id)->first();
        $game = Game::where('status',1)->first();
        $map = Map::where('id',$game->map_id)->first();
        $teams = $game->teams()->get();
        $markers = $map->markers()->get();

        for($i = 0;$i < count($teams);$i++){
            $users = User::where('team_id',$user->team_id)->get();
            $teams[$i]->players = $teams[$i]->$users;
        }

        $array = array('markers' => $markers, 'map' =>$map,'teams'=> $teams);
        return json_encode($array);

    }

    public function trackloc(Request $request){

        $lat = $request->lati;
        $long = $request->long;

        $user = Auth::user();
        $user->long = $long;
        $user->lat = $lat;
        $user->save();

        $responseArray = array('status' => 'OK', 'message' => 'Success','data' => "");
        return json_encode($responseArray);

    }

    public function flag(){
        //NAPRAVIT DA SE UVECAJU BODOVI kad uzmemo zastavu
        $user = Auth::user();
        $team  = Team::where('id',$user->team_id)->first();
        $team->score = $team->score + 5;
        $team->save();

        $responseArray = array('status' => 'OK', 'message' => 'Success','data' => "");
        return json_encode($responseArray);
    }

    public function eliminationSingle(){
        //GCM
        $user = Auth::user();
        $user->status = 1;
        $user->save();
        $game = Game::where('status',1)->first();
        $teams = $game->teams()->get();
        for($i = 0;$i < count($teams);$i++){
            if($teams[$i]->id != $user->team_id){
                $team = $teams[$i];
                $team->score = $team->score +2;
                if($team->score >= 10){
                    $game->status = 2;
                    $team->status = 1;
                    $game->save();
                }
                $team->save();
            }
        }
        //SLOZIT DIO SA SKORON i NOTIFIKACIJE
        $users = $teams->users()->get();
        for($i = 0;$i < count($teams);$i++){

        }

        $responseArray = array('status' => 'OK', 'message' => 'Success','data' => "");
        return json_encode($responseArray);
    }

    public function eliminationJudge(Request $request, $id){
        //GCM
        $message = $request->message;
        $user = User::where('id',$id)->first();
        $user->status = 1;
        $user->save();
        $game = Game::where('status',1)->first();
        $teams = $game->teams()->get();
        for($i = 0;$i < count($teams);$i++){
            if($teams[$i]->id != $user->team_id){
                $team = $teams[$i];
                $team->score = $team->score + 2;
                $team->save();
            }
        }

        if($user->gcm_id){
            if($user->device == 'android') {
                PushNotification::app('android')
                    ->to($user->gcm_id)
                    ->send($message);
            } else {
                PushNotification::app('iOS')
                    ->to($user->gcm_id)
                    ->send($message);
            }
        }

        $responseArray = array('status' => 'OK', 'message' => 'Success','data' => "");
        return json_encode($responseArray);
    }



}
