//
//  APIUser.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//


import Foundation
import Alamofire
import SwiftyJSON

private let _sharedInstance = APIUser()

class APIUser {
    
    private init() {
    }
    
    class var sharedInstance: APIUser {
        return _sharedInstance
    }
    
    func login(email: String, password: String, withSuccess: ((JSON) -> Void), andFail withFail: ((NSError) -> Void)) {
        Alamofire.request(.POST, Constants.kLOGIN, parameters: ["email": email, "password": password])
            .responseJSON { response in
                switch response.result {
                case .Success:
                    if let value = response.result.value {
                        let json = JSON(value)
                        withSuccess(json)
                    }
                case .Failure(let error):
                    withFail(error)
                }
        }
    }
    
    func register(email: String, password: String, name: String, withSuccess: ((JSON) -> Void), andFail withFail: ((NSError) -> Void)) {
        
        let parameters = [
            "email": email,
            "password": password,
            "full_name": name,
            "device": "ios",
            "pushid" : APIUser.sharedInstance.getPushID()
        ]
        
        Alamofire.request(.POST, Constants.kREGISTER, parameters: parameters)
            .responseJSON { response in
                switch response.result {
                case .Success:
                    if let value = response.result.value {
                        let json = JSON(value)
                        withSuccess(json)
                    }
                case .Failure(let error):
                    withFail(error)
                }
        }
    }
    
    
    func setToken(token: String) {
        
        let preferences = NSUserDefaults.standardUserDefaults()
        preferences.setObject(token, forKey: "token")
        preferences.synchronize()
        
    }
    
    func getToken() -> String {
        
        let preferences = NSUserDefaults.standardUserDefaults()
        return preferences.objectForKey("token") as! String
        
    }
    
    
    func setPushID(token: String) {
        
        let preferences = NSUserDefaults.standardUserDefaults()
        preferences.setObject(token, forKey: "pushID")
        preferences.synchronize()
        
    }
    
    func getPushID() -> String {
        
        let preferences = NSUserDefaults.standardUserDefaults()
        return preferences.objectForKey("pushID") as! String
        
    }
    
    
    
    func setUserID(token: String) {
        
        let preferences = NSUserDefaults.standardUserDefaults()
        preferences.setObject(token, forKey: "userID")
        preferences.synchronize()
        
    }
    
    func getUserID() -> String {
        
        let preferences = NSUserDefaults.standardUserDefaults()
        return preferences.objectForKey("userID") as! String
        
    }
    
}