<?php

namespace App\Http\Controllers;

use App\Game;
use App\Team;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\User;
use Illuminate\Support\Facades\Auth;
use PushNotification;

class NotificationController extends Controller
{
    public function judgeToPlayers(Request $request) {
        $sender = Auth::user()->full_name;
        $player = $request->id;
        $message = $request->message;
        if($message == 'Izbacen si iz igre'){
            $user = User::where('id',$player)->first();
            $user->status = 1;
            $user->save();
            $game = Game::where('status',1)->first();
            $teams = $game->teams()->get();
            $winner = false;
            $winnerID = 1;
            for($i = 0;$i < count($teams);$i++){
                if($teams[$i]->id != $user->team_id){
                    $team = $teams[$i];
                    $team->score = $team->score + 2;
                    $team->save();
                    if($team->score >= $game->score){
                        $winner = true;
                        $winnerID = $team->id;
                        //kraj foreacha
                    }//if ako je doslo do promjene rezultata
                }
            }

            if($winner){
                $teams = $game->teams()->get();
                //$users = $team->users()->get();

                for($i=0;$i<count($teams);$i++){
                    $users = $teams[$i]->users()->get();
                    $team = $teams[$i];
                    $team->status = 1;
                    $team->save();
                    foreach($users as $u){
                        if($u->gcm_id){
                            if($winnerID == $u->team_id){
                                if($u->device == 'android') {
                                    PushNotification::app('android')
                                        ->to($u->gcm_id)
                                        ->send('Pobjednik');
                                } else {
                                    PushNotification::app('iOS')
                                        ->to($u->gcm_id)
                                        ->send('Pobjednik');
                                }
                            }
                            else{
                                if($u->device == 'android') {
                                    PushNotification::app('android')
                                        ->to($u->gcm_id)
                                        ->send('Gubitnik');
                                } else {
                                    PushNotification::app('iOS')
                                        ->to($u->gcm_id)
                                        ->send('Gubitnik');
                                }
                            }

                        }
                    }
                }
                $game->status = 2;
                $game->save();
                $responseArray = array('status' => 'OK', 'message' => 'OK','data' => "");
                return json_encode($responseArray);

            }//ako je pobjednik

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
        }
        $user1 = User::find($player);

        if($user1->device == 'android') {
            PushNotification::app('android')
                ->to($user1->gcm_id)
                ->send($sender . ":" . $message);
        } else {
            PushNotification::app('iOS')
                ->to($user1->gcm_id)
                ->send($sender . ":" . $message);
        }

        $responseArray = array('status' => 'OK', 'message' => 'OK','data' => "");
        return json_encode($responseArray);

    }

    public function judgeToTeams(Request $request) {
        $sender = Auth::user()->full_name;
        $game = Game::where('status',1)->first();
        $teams = $game->teams()->get();
        $message = $request->message;

        //$playersTeam1 = User::where('team_id',$idTeam1)->get();

        for($i=0; $i<count($teams);$i++){
            $players = User::where('team_id',$teams[$i]->id)->get();
            foreach($players as $p){
                if($p->gcm_id) {
                    if ($p->device == 'android') {
                        PushNotification::app('android')
                            ->to($p->gcm_id)
                            ->send($message);
                    } else {
                        PushNotification::app('iOS')
                            ->to($p->gcm_id)
                            ->send($message);
                    }
                }
            }
        }
        $responseArray = array('status' => 'OK', 'message' => 'OK','data' => "");
        return json_encode($responseArray);

    }

    public function playersToJudge(Request $request) {
        $message = $request->message;
        $judge = User::where('role',2)->first();
        if($judge->gcm_id){
            if($judge->device == 'android') {
                PushNotification::app('android')
                    ->to($judge->gcm_id)
                    ->send($message);
            } else {
                PushNotification::app('iOS')
                    ->to($judge->gcm_id)
                    ->send($message);
            }
        }
        $responseArray = array('status' => 'OK', 'message' => 'OK','data' => "");
        return json_encode($responseArray);
    }

    public function onlyTeamPlayers(Request $request) {
        $user = Auth::user();
        $team = Team::where('id',$user->team_id)->first();
        $players = $team->users()->get();
        $message = $request->message;
        $winner = false;
        $winnerID = 1;
        if($message == 'Pogoden sam'){
            $user = Auth::user();
            $user->status = 1;
            $user->save();
            $game = Game::where('status',1)->first();
            $teams = $game->teams()->get();
            for($i = 0;$i < count($teams);$i++){
                if($teams[$i]->id != $user->team_id){
                    $team = $teams[$i];
                    $team->score = $team->score +2;
                    if($team->score >= $game->score){
                        $winner = true;
                        $winnerID = $team->id;
                        //kraj foreacha
                    }
                    $team->save();
                }
            }
        }

        if($winner){
            $teams = $game->teams()->get();
            //$users = $team->users()->get();

            for($i=0;$i<count($teams);$i++){
                $users = $teams[$i]->users()->get();
                $team = $teams[$i];
                $team->status = 1;
                $team->save();
                foreach($users as $u){
                    if($u->gcm_id){
                        if($winnerID == $u->team_id){
                            if($u->device == 'android') {
                                PushNotification::app('android')
                                    ->to($u->gcm_id)
                                    ->send('Pobjednik');
                            } else {
                                PushNotification::app('iOS')
                                    ->to($u->gcm_id)
                                    ->send('Pobjednik');
                            }
                        }
                        else{
                            if($u->device == 'android') {
                                PushNotification::app('android')
                                    ->to($u->gcm_id)
                                    ->send('Gubitnik');
                            } else {
                                PushNotification::app('iOS')
                                    ->to($u->gcm_id)
                                    ->send('Gubitnik');
                            }
                        }

                    }
                }
            }
            $game->status = 2;
            $game->save();
            $responseArray = array('status' => 'OK', 'message' => 'OK','data' => "");
            return json_encode($responseArray);

        }//ako je pobjednik

        foreach($players as $p){
            if($p->id == $user->id) {

            } else {
                if($p->gcm_id){
                    if($p->device == 'android') {
                        PushNotification::app('android')
                            ->to($p->gcm_id)
                            ->send($message);
                    } else {
                        PushNotification::app('iOS')
                            ->to($p->gcm_id)
                            ->send($message);
                    }
                }
            }
        }
        $responseArray = array('status' => 'OK', 'message' => 'OK','data' => "");
        return json_encode($responseArray);
    }
}
