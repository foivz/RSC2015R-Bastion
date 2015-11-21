//
//  PlayerListController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

class PlayerListController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.delegate = self
        self.tableView.dataSource = self

    }

    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("UserListCell") as! UserListCell
        cell.buttonAdd.tag = indexPath.row
        cell.buttonDelete.tag = indexPath.row
        cell.buttonAdd.addTarget(self, action: "clickedAddButton:", forControlEvents: UIControlEvents.TouchUpInside)
        cell.buttonDelete.addTarget(self, action: "clickedDeleteButton:", forControlEvents: UIControlEvents.TouchUpInside)
        return cell
    }

    func clickedAddButton(sender: UIButton) {
        self.tableView.reloadSections(NSIndexSet(index: 0), withRowAnimation: UITableViewRowAnimation.Automatic)
    }
    
    func clickedDeleteButton(sender: UIButton) {
        self.tableView.reloadSections(NSIndexSet(index: 0), withRowAnimation: UITableViewRowAnimation.Automatic)
    }
}
