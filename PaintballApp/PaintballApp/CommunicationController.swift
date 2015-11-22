//
//  CommunicationController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 22.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import SwiftyJSON

class CommunicationController: UIViewController {

    @IBOutlet weak var viewJudgeLayer: UIView!
    @IBOutlet weak var buttonSwitch: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

    }

    @IBAction func clickSendMessage(sender: UIButton) {
        APIData.sharedInstance.sendTeamMessage(sender.currentTitle!, withSuccess: { (json: JSON) -> Void in
            
            }) { (error: NSError) -> Void in
                
        }
    }

    @IBAction func clickSendJudgeMessage(sender: UIButton) {
        APIData.sharedInstance.sendToJudge(sender.currentTitle!, withSuccess: { (json: JSON) -> Void in
            
            }) { (error: NSError) -> Void in
                
        }
    }
    
    
    @IBAction func clickSwitchMode(sender: AnyObject) {
        viewJudgeLayer.hidden = !viewJudgeLayer.hidden
        if viewJudgeLayer.hidden {
            buttonSwitch.setTitle("PORUKE SUCU", forState: UIControlState.Normal)
        } else {
            buttonSwitch.setTitle("PORUKE TIMU", forState: UIControlState.Normal)
        }
    }
    
}
