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

class PlayerListController: UIViewController, UITableViewDelegate, UITableViewDataSource, QRCodeReaderViewControllerDelegate {

    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "refreshList:",
            name: "refreshUserList:", object: nil)

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
    
    func refreshList(notification: NSNotification) {
        print(notification)
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
