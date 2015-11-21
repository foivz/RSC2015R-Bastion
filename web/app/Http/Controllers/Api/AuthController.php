<?php

namespace App\Http\Controllers\Api;

use App\AuthenticateUser;
use App\User;
use Illuminate\Support\Facades\Validator;
use Tymon\JWTAuth\Facades\JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class AuthController extends Controller {

    /**
     * API Login, on success return JWT Auth token
     *
     * @param Request $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function login(Request $request) {
        $credentials = $request->only('email', 'password');

        //$user = User::where(['email' => $credentials['email'],'password' => bcrypt($credentials['password'])])->firstOrFail();
        try{
            $user = User::where('email', $credentials['email'])->firstOrFail();
        }
        catch(\Exception $e){
            $responseArray = array('status' => 'ERROR', 'message' => 'invalid_credentials','data' => array('token'=>''));
            return response()->json($responseArray,410);
        }

        //$user = User::where('email', $credentials['email'])->where('name','bozo')->firstOrFail();
        if(\Hash::check($credentials['password'],$user->password)){
            try {



                //$roles = ['foo' => 'bar', 'baz' => 'bob'];
                //return $token = JWTAuth::fromUser($user,$roles);
                // attempt to verify the credentials and create a token for the user
                if (! $token = JWTAuth::fromUser($user)) {
                    $responseArray = array('status' => 'ERROR', 'message' => 'invalid_credentials!','data' => array('token'=>'invalid'));
                    return response()->json($responseArray,410);
                }
            } catch (JWTException $e) {
                // something went wrong whilst attempting to encode the token
                $responseArray = array('status' => 'ERROR', 'message' => 'Could not create token!','data' => array('token'=>'token'));
                return response()->json($responseArray,500);
            }

            // all good so return the token
            $dataArray = array(compact('token'));

            //$responseArray = array('status' => 'OK', 'message' => 'Successfull login!','data' => compact('token'));

            //return response()->json($responseArray);

            $dataArray = array('token' => $token,'avatar' => "",'email' => $user->email,'name' => $user->name);
            $responseArray = array('status' => 'OK', 'message' => 'Successfull login!','data' => $dataArray);

            return json_encode($responseArray);
        }


        //return response()->json(compact('token'));
    }


    /**
     * Log out
     * Invalidate the token, so user cannot use it anymore
     * They have to relogin to get a new token
     *
     * @param Request $request
     */
    public function logout(Request $request) {

        $this->validate($request, [
                'token' => 'required'
        ]);

        JWTAuth::invalidate($request->input('token'));

        return json_encode("success");
    }

    /**
     * @param Request $request
     */
    public function register(Request $request) {
        $input = $request->all();

        $rules = [
            'full_name' => 'required|max:50',
            'email' => 'required|unique:users,email|email|max:40',
            'password' => 'required|max:20',
        ];

        $messages = [
            'required' => 'The :attribute field is required.',
            'email.email' => 'Wrong email format',
            'full_name.unique' => 'Given name is not unique',
        ];

        $validator = Validator::make($input, $rules, $messages);

        if($validator->fails()){
            $messages = $validator->errors();
            $return_msg = array();
            foreach ($messages->all() as $message) {
                array_push($return_msg,$message);
            }
            return $return_msg;
        }

        $user = new User();

        $user->name = $request->input('full_name');
        $user->email = $request->input('email');
        $user->password = bcrypt($request->input('password'));
        $user->role = 1;
        $user->device = $request->input('device');
        $user->save();


        return json_encode(array('status' => 'OK', 'message' => 'Registration successfull!', 'data' => array('response'=>'ok')));
        //return "ok";
    }

    public function loginProvider(AuthenticateUser $authenticateUser, Request $request, $provider = null){
        //$type = $request->input('type',1);

        if($request->isMethod('get')){
            return $authenticateUser->execute($request->all(),$this,$provider);
        }
        else if($request->isMethod('post')){
            return $authenticateUser->executeMobile($request->all(),$this,$provider);
        }
    }

    public function userHasLoggedIn($user,$provider) {
        //$credentials = array('email' => $user->email,'password' => '123456');

        try {
            // attempt to verify the credentials and create a token for the user
            $roles = array();
            foreach($user->roles as $role){
                $roles['role']= $role->name;
            }
            if (! $token = JWTAuth::fromUser($user, $roles)) {
                $responseArray = array('status' => 'ERROR', 'message' => 'invalid_credentials!','data' => array('token'=>'invalid'));
                return response()->json($responseArray,410);
            }
        } catch (JWTException $e) {
            // something went wrong whilst attempting to encode the token
            $responseArray = array('status' => 'ERROR', 'message' => 'Could not create token!','data' => array('token'=>'token'));
            return response()->json($responseArray,500);
        }

        // all good so return the token
        if(is_numeric($user->email)){
            $user->email = null;
        }

        $userAvatar = $user->getProviderByName($provider);

        $dataArray = array('token' => $token,'avatar' => $userAvatar->pivot->avatar,'email' => $user->email,'name' => $user->name);
        $responseArray = array('status' => 'OK', 'message' => 'Successfull login!','data' => $dataArray);

        return json_encode($responseArray);

        // \Session::flash('message', 'Welcome, ' . $user->username);
        //return redirect('/');
    }

    public function userHasLoggedInWeb($user,$provider){

        if(is_numeric($user->email)){
            $user->email = null;
        }

        $userAvatar = $user->getProviderByName($provider);

        return view('welcome', ['avatar' => $userAvatar->pivot->avatar,'email' => $user->email
        ,'name' => $user->name,'status' => 'OK','message' => 'Successfull login!']);
    }


}

