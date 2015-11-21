//
//  TeamListController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

class TeamListController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10;
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell: TeamListCell = tableView.dequeueReusableCellWithIdentifier("TeamListCell") as! TeamListCell
        return cell
    }
    
    
    @IBAction func clickPlusButton(sender: AnyObject) {
        var nextController = self.storyboard?.instantiateViewControllerWithIdentifier("UserListController")
        self.navigationController?.showViewController(nextController!, sender: nil)
    }
}
