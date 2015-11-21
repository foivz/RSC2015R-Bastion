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
                
                var token: String = response!.allHeaderFields["Set-Cookie"] as! String
                token = token.stringByReplacingOccurrencesOfString("laravel_session=", withString: "")
                token = token.substringToIndex((token.rangeOfString(";")?.first)!)
                
                APIUser.sharedInstance.setToken("Bearer \(token)")
                let json = JSON(data: data!)
                
                withSuccess(json)
                
        }
    }
    
    func getListOfTeams(withSuccess: ((JSON) -> Void), withFail: ((NSError) -> Void)) {
        getData(Constants.kTEAMLIST, withSuccess: withSuccess, andFail: withFail)
    }
    
}