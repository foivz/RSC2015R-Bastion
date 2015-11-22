//
//  DrawerController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 22.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import AVFoundation
import QRCodeReader
import MMDrawerController
import SwiftyJSON

class DrawerController: UITableViewController, QRCodeReaderViewControllerDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()

    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        //igra
        if indexPath.row == 1 {
            let nextController = self.storyboard?.instantiateViewControllerWithIdentifier("GameController") as! GameController
            self.mm_drawerController.centerViewController?.showViewController(nextController, sender: nil)
        //qrcode
        } else if indexPath.row == 2 {
            clickQRCodeStartScaning()
        }
        
        self.mm_drawerController.toggleDrawerSide(MMDrawerSide.Left, animated: true, completion: nil)
    }
    
    
    //QRCODE READER
    func clickQRCodeStartScaning() {
        reader.delegate = self
        
        // Or by using the closure pattern
        reader.completionBlock = { (result: String?) in
            APIData.sharedInstance.reportFlag({ (json: JSON) -> Void in
                
                }, withFail: { (error:NSError) -> Void in
                    
            })
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
