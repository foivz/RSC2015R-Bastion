//
//  Constants.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import Foundation

struct Constants {
    
    static let kENDPOINT: String = "https://bozidarlabas.from.hr"
    static let kLOGIN: String = "\(kENDPOINT)/api/login"
    static let kREGISTER: String = "\(kENDPOINT)/api/register"
    static let kTEAMLIST: String = "\(kENDPOINT)/api/team"
    static let kJOINTEAM: String = "\(kENDPOINT)/api/join"
    static let kGETYOURPLAYERS: String = "\(kENDPOINT)/api/applications"
    static let kLOCKTEAM: String = "\(kENDPOINT)/api/lockteam"
    static let kTEAMSANDPLAYERS: String = "\(kENDPOINT)/api/listatimova"
    
    static let kELIMINATEPLAYER: String = "\(kENDPOINT)/api/judgeeliminate"
    static let kSENDTEAMMESSAGE: String = "\(kENDPOINT)/api/notify/teamplayers"
    
    static let kSENDTOJUDGE: String = "\(kENDPOINT)/api/notify/judge"
    
    static let kSCANREQUEST: String = "\(kENDPOINT)/api/getflag"
    
    static let kGEOLOCATION: String = "\(kENDPOINT)/api/trackloc"
    
    static let kMAPTEAM: String = "https://bozidarlabas.from.hr/viewmyteam"
    static let kMAPADMIN: String = "https://bozidarlabas.from.hr/adminview"
    
}