//
//  TeamListController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import SwiftyJSON

class TeamListController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var tableView: UITableView!
    var localJson: JSON = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        
        getData()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return localJson["data"].count;
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell: TeamListCell = tableView.dequeueReusableCellWithIdentifier("TeamListCell") as! TeamListCell
        cell.labelName.text = localJson["data"][indexPath.row]["name"].string
        cell.labelDescription.text = localJson["data"][indexPath.row]["team_leader"].string
        return cell
    }
    
    
    @IBAction func clickPlusButton(sender: AnyObject) {
        var nextController = self.storyboard?.instantiateViewControllerWithIdentifier("UserListController")
        self.navigationController?.showViewController(nextController!, sender: nil)
    }
    
    func getData() {
        APIData.sharedInstance.getListOfTeams({ (json: JSON) -> Void in
            
            //print(json)
            self.localJson = json
            self.tableView.reloadData()
            
            }) { (error: NSError) -> Void in
                
        }
    }
}
