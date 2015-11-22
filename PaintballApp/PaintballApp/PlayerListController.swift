//
//  PlayerListController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import QRCodeReader
import AVFoundation
import SwiftyJSON

class PlayerListController: UIViewController, UITableViewDelegate, UITableViewDataSource, QRCodeReaderViewControllerDelegate {

    var indexOfTeam: Int = 0
    var teamName: String = ""
    var data: JSON = nil
    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
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
        
        APIData.sharedInstance.getYourPlayers(indexOfTeam, withSuccess: { (json: JSON) -> Void in

            if json != nil && json.count != 0{
                self.data = json
                self.tableView.reloadData()
            }
            
            }) { (error: NSError) -> Void in
                
        }
    }
    
    
    
    //QRCODE READER
    func clickQRCodeStartScaning() {
        reader.delegate = self
        
        // Or by using the closure pattern
        reader.completionBlock = { (result: String?) in
            print(result)
        }
        
        // Presents the reader as modal form sheet
        reader.modalPresentationStyle = .FormSheet
        presentViewController(reader, animated: true, completion: nil)
    }
    
    lazy var reader = QRCodeReaderViewController(metadataObjectTypes: [AVMetadataObjectTypeQRCode])
    
    // MARK: - QRCodeReader Delegate Methods
    func readerDidCancel(reader: QRCodeReaderViewController) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
    func reader(reader: QRCodeReaderViewController, didScanResult result: String) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }
}
