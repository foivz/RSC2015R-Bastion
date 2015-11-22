<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\User;

class NotificationController extends Controller
{
    public function judgeToPlayers(Request $request) {
        $player = $request->id;
        $message = $request->message;
        $user1 = User::find($player);

        if($user1->device == 'android') {
            PushNotification::app('android')
                ->to($user1->gcm_id)
                ->send($message);
        } else {
            PushNotification::app('iOS')
                ->to($user1->gcm_id)
                ->send($message);
        }

        $responseArray = array('status' => 'OK', 'message' => 'OK','data' => "");
        return json_encode($responseArray);

    }

    public function judgeToTeams(Request $request) {
        $idTeam1 = $request->team1;
        $idTeam2 = $request->team2;
        $message = $request->message;

        /*
        if($user1->device == 'android') {
            PushNotification::app('android')
                ->to($user1->gcm_id)
                ->send($message);
        } else {
            PushNotification::app('iOS')
                ->to($user1->gcm_id)
                ->send($message);
        }
        */
    }

    public function playersToJudge(Request $request) {
        $player = $request->id;
        $message = $request->message;
        $user1 = User::find($player);

        if($user1->device == 'android') {
            PushNotification::app('android')
                ->to($user1->gcm_id)
                ->send($message);
        } else {
            PushNotification::app('iOS')
                ->to($user1->gcm_id)
                ->send($message);
        }
    }

    public function onlyTeamPlayers(Request $request) {

    }
}
