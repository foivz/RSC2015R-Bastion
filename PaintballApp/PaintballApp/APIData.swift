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
 
                //let token: String = response!.allHeaderFields["Authorization"] as! String
                
                //APIUser.sharedInstance.setToken(token)
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
    func joinTeam(id: Int, withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        let getToken = APIUser.sharedInstance.getToken()
        
        Alamofire.request(.POST, "\(Constants.kJOINTEAM)/\(id)", headers: ["Authorization": getToken])
            .response { (request: NSURLRequest?, response: NSHTTPURLResponse?, data: NSData?, error: NSError?) -> Void in
                
                print(response)
                print(data)
                print(response)
                
                //let token: String = response!.allHeaderFields["Authorization"] as! String
                
                //APIUser.sharedInstance.setToken(token)
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
}