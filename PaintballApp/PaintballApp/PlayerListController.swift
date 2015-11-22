//
//  PlayerListController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import AVFoundation
import SwiftyJSON

class PlayerListController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    var indexOfTeam: Int = 0
    var teamName: String = ""
    var data: JSON = nil
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var viewWaitingForBegining: UIView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.viewWaitingForBegining.hidden = true
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        
        self.refreshList(NSNotification(name: "fake", object: nil))
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "refreshList:", name: "refreshUserList", object: nil)

    }

    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("UserListCell") as! UserListCell
        cell.buttonAdd.tag = indexPath.row
        cell.buttonDelete.tag = indexPath.row
        cell.buttonAdd.addTarget(self, action: "clickedAddButton:", forControlEvents: UIControlEvents.TouchUpInside)
        cell.buttonDelete.addTarget(self, action: "clickedDeleteButton:", forControlEvents: UIControlEvents.TouchUpInside)
        
        cell.labelName.text = data[indexPath.row]["name"].string
        
        return cell
    }
    
    @IBAction func clickedLockButton(sender: AnyObject) {
        APIData.sharedInstance.lockTeam(teamName, withSuccess: { (json: JSON) -> Void in
            
            self.viewWaitingForBegining.hidden = false
            
            }) { (error: NSError) -> Void in
                
        }
    }

    func clickedAddButton(sender: UIButton) {

        self.tableView.reloadSections(NSIndexSet(index: 0), withRowAnimation: UITableViewRowAnimation.Automatic)
    }
    
    func clickedDeleteButton(sender: UIButton) {
        self.tableView.reloadSections(NSIndexSet(index: 0), withRowAnimation: UITableViewRowAnimation.Automatic)
    }
    
    func refreshList(notification: NSNotification) {
        if notification.userInfo != nil {
            let string: String = (notification.userInfo!["aps"]!["alert"]) as! String
            if string == "Igra pocinje!" {
                self.viewWaitingForBegining.hidden = true
                let nextController = self.storyboard?.instantiateViewControllerWithIdentifier("GameController") as! GameController
                self.navigationController?.showViewController(nextController, sender: nil)
            }
        }
        
        
        APIData.sharedInstance.getYourPlayers(indexOfTeam, withSuccess: { (json: JSON) -> Void in

            if json != nil && json.count != 0{
                self.data = json
                self.tableView.reloadData()
            }
            
            }) { (error: NSError) -> Void in
                
        }
    }
    
}
