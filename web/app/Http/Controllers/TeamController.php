<?php

namespace App\Http\Controllers;

use App\User;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Team;
use Illuminate\Support\Facades\Auth;

class TeamController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $teams = Team::all();
        $responseArray = array('status' => 'OK', 'message' => 'Okay','data' => $teams);
        return json_encode($responseArray);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $email = Auth::user()->email;
        $team = new Team();
        $team->name = $request->name;
        $team->team_leader = $email;
        $team->save();
        $id = $team->id;
        $count = \DB::table('users')->count();
        $count++;
        $user = new User();
        $user->name = 'name'.$count;
        $user->email = 'email'.$count.'@gmail.com';
        $user->password = bcrypt('123456');
        $user->full_name = 'Full name'.$count;
        $user->long = '16.3524307';
        $user->lat = '46.3087504';
        $user->team_id = $id;
        $user->save();
        $count++;
        $user = new User();
        $user->name = 'name'.$count;
        $user->email = 'email'.$count.'@gmail.com';
        $user->password = bcrypt('123456');
        $user->full_name = 'Full name'.$count;
        $user->long = '16.3524307';
        $user->lat = '46.3087504';
        $user->team_id = $id;
        $user->save();
        $count++;
        $user = new User();
        $user->name = 'name'.$count;
        $user->email = 'email'.$count.'@gmail.com';
        $user->password = bcrypt('123456');
        $user->full_name = 'Full name'.$count;
        $user->long = '16.3524307';
        $user->lat = '46.3087504';
        $user->team_id = $id;
        $user->save();


        $responseArray = array('status' => 'OK', 'message' => $id,'data' => "");
        return json_encode($responseArray);

    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }

    public function players(){
        $user = Auth::user();
        $team = Team::where('team_leader',$user->email)->orderBy('created_at')->take(1)->get();

        $players = User::where('team_id',$team->id)->get();

        for($i = 0;$i < count($players);$i++){
            $players[$i]->team_name = $team->name;
        }

        $responseArray = array('status' => 'OK', 'message' => 'Success','data' => $players);
        return json_encode($responseArray);



    }
}
