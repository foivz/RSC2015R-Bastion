//
//  JudgeTeamListController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 22.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import SwiftyJSON

class JudgeTeamListController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var buttonUserClickMenu: UIButton!
    @IBOutlet weak var viewUserClickMenu: UIView!
    @IBOutlet weak var tableView: UITableView!
    var data: JSON = nil
    var rowClicked: Int = 0
    var teamIndex: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        
        getData()

    }

    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data["data"][teamIndex]["players"].count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("cellPlayer")
        cell?.textLabel?.text = data["data"][teamIndex]["players"][indexPath.row]["name"].string
        return cell!
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        UIView.animateWithDuration(0.3) { () -> Void in
            self.viewUserClickMenu.hidden = false
            self.buttonUserClickMenu.hidden = false
        }
        rowClicked = indexPath.row
    }

    func getData() {
        APIData.sharedInstance.fetchTeamsAndPlayers({ (json: JSON) -> Void in
            self.data = json
            self.tableView.reloadData()
            }) { (error: NSError) -> Void in
                
        }
    }
    
    @IBAction func clickButtonUserClickMenu(sender: AnyObject) {
        UIView.animateWithDuration(0.3) { () -> Void in
            self.viewUserClickMenu.hidden = true
            self.buttonUserClickMenu.hidden = true
        }
    }
    
    @IBAction func clickEliminatePlayer(sender: AnyObject) {
        let id = Int(data["data"][teamIndex]["players"][rowClicked]["id"].string!)
        APIData.sharedInstance.eliminatePlayer(id!, withSuccess: { (json: JSON) -> Void in
                
            }) { (error: NSError) -> Void in
                
        }
    }
}
