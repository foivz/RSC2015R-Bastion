//
//  CoreLocationController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 22.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import CoreLocation
import SwiftyJSON

class CoreLocationController: NSObject, CLLocationManagerDelegate {
    
    var locationManager:CLLocationManager = CLLocationManager()
    
    override init() {
        super.init()
        self.locationManager.delegate = self
        self.locationManager.requestAlwaysAuthorization()
    }
    
    
    
    func locationManager(manager: CLLocationManager!, didChangeAuthorizationStatus status: CLAuthorizationStatus) {
        print("didChangeAuthorizationStatus")
        
        switch status {
        case .NotDetermined:
            print(".NotDetermined")
            break
            
        case .Authorized:
            print(".Authorized")
            //self.locationManager.startUpdatingLocation()
            break
            
        case .Denied:
            print(".Denied")
            break
            
        default:
            print("Unhandled authorization status")
            break
            
        }
    }
    
    func locationManager(manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location = locations.last
        
        APIData.sharedInstance.sendGeolocation("\(location!.coordinate.latitude)", long: "\(location!.coordinate.longitude)", withSuccess: { (json: JSON) -> Void in
            
            }) { (error: NSError) -> Void in
                
        }
        print("didUpdateLocations:  \(location!.coordinate.latitude), \(location!.coordinate.longitude)")
    }
    
    
}
