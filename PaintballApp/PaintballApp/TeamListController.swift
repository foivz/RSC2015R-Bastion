//
//  TeamListController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import SwiftyJSON

class TeamListController: UIViewController, UITableViewDelegate, UITableViewDataSource, UIAlertViewDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    var alert: UIAlertView? = nil
    var localJson: JSON = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        alert = UIAlertView(title: "KREIRANJE TIMA", message: "Molimo unesite ime tima", delegate: self, cancelButtonTitle: "Kreiraj")
        alert!.alertViewStyle = UIAlertViewStyle.PlainTextInput;
        
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        NSThread.sleepForTimeInterval(1)
        getData()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return localJson["data"].count;
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell: TeamListCell = tableView.dequeueReusableCellWithIdentifier("TeamListCell") as! TeamListCell
        cell.labelName.text = localJson["data"][indexPath.row]["name"].string
        cell.labelDescription.text = localJson["data"][indexPath.row]["team_leader"].string
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let name = self.localJson["data"][indexPath.row]["name"].string!
        APIData.sharedInstance.joinTeam(name, withSuccess: { (json: JSON) -> Void in
            
            }) { (error: NSError) -> Void in
            
        }
    }
    
    
    @IBAction func clickPlusButton(sender: AnyObject) {
        alert?.show()
    }
    
    func getData() {
        APIData.sharedInstance.getListOfTeams({ (json: JSON) -> Void in
            
            self.localJson = json
            self.tableView.reloadData()
            
            }) { (error: NSError) -> Void in
                
        }
    }
    
    //GO TO USERS PICKING
    func alertView(alertView: UIAlertView, clickedButtonAtIndex buttonIndex: Int) {
        APIData.sharedInstance.setTeam((alertView .textFieldAtIndex(0)?.text!)!, withSuccess: { (json: JSON) -> Void in
            
            if let status = json["status"].string {
                if status == "OK" {
                    let nextController = self.storyboard?.instantiateViewControllerWithIdentifier("PlayerListController")
                    self.navigationController?.showViewController(nextController!, sender: nil)
                } else {
                    CustomAlertView.alertCreateTeamFail()
                }
            } else {
                CustomAlertView.alertCreateTeamFail()
            }
            
            }) { (error: NSError) -> Void in
                
        }
    }
}
