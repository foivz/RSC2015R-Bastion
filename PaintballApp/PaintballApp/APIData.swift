//
//  APIData.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//


import Foundation
import Alamofire
import SwiftyJSON

private let _sharedInstance = APIData()

class APIData {
    
    private init() {
    }
    
    class var sharedInstance: APIData {
        return _sharedInstance
    }
    
    func getData(apiURL: String, withSuccess: ((JSON) -> Void), andFail withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        Alamofire.request(.GET, apiURL, headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                //let token: String = response!.allHeaderFields["Authorization"] as! String
                
                //APIUser.sharedInstance.setToken(token)
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
    func getListOfTeams(withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        getData(Constants.kTEAMLIST, withSuccess: withSuccess, andFail: withFail)
    }
    
    
    func setTeam(teamName: String, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        Alamofire.request(.POST, Constants.kTEAMLIST, parameters: ["name": teamName], headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
 
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
    func joinTeam(id: Int, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, "\(Constants.kJOINTEAM)/\(id)", headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
    
    func getYourPlayers(id: Int, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.GET, "\(Constants.kGETYOURPLAYERS)/\(id)", headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
    
    
    
    func lockTeam(teamName: String, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, Constants.kLOCKTEAM, parameters: ["teamname" : teamName], headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                
                let json = JSON(data: data!)
                
                withSuccess(json)
                print(json)
                
        }
    }
    
    
    
    func fetchTeamsAndPlayers(withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.GET, Constants.kTEAMSANDPLAYERS, headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
    
    func eliminatePlayer(id: Int, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, "\(Constants.kELIMINATEPLAYER)/\(id)", parameters: ["message" : "izbačen si iz igre"], headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                let json = JSON(data: data!)
                print(json)
                withSuccess(json)
                
        }
    }
    
    
    
    func sendTeamMessage(message: String, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, Constants.kSENDTEAMMESSAGE, parameters: ["message" : message], headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                let json = JSON(data: data!)
                withSuccess(json)
                
                print(json)
                
        }
    }
    
    
    
    
    func sendToJudge(message: String, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, Constants.kSENDTOJUDGE, parameters: ["message" : message], headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                let json = JSON(data: data!)
                withSuccess(json)
                
                print(json)
                
        }
    }
    
    
    
    func reportFlag(withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, Constants.kSCANREQUEST, headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                let json = JSON(data: data!)
                withSuccess(json)
                
                print(json)
                
        }
    }
    
    
    
    func sendGeolocation(lati: String, long: String, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, Constants.kSCANREQUEST, parameters: ["lati" : lati, "long" : long],  headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                let json = JSON(data: data!)
                withSuccess(json)
                
                print(json)
                
        }
    }
    
}