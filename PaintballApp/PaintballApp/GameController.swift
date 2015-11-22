//
//  GameController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import AVFoundation
import QRCodeReader
import MMDrawerController
import SwiftyJSON

class GameController: UIViewController, QRCodeReaderViewControllerDelegate {

    @IBOutlet weak var navButton1: UIButton!
    @IBOutlet weak var navButton2: UIButton!
    @IBOutlet weak var viewScrollViewContent: UIView!
    @IBOutlet weak var scrollViewWidth: NSLayoutConstraint!
    @IBOutlet weak var scrollViewController: UIScrollView!
    var numberOfScreens: CGFloat = 2
    var cutBottomEdge: CGFloat = 100
    
    @IBOutlet weak var viewAlert: UIView!
    @IBOutlet weak var labelAlert: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.scrollViewWidth.constant = self.view.frame.size.width * numberOfScreens
        viewAlert.hidden = true
        
        navigationItem.rightBarButtonItem = UIBarButtonItem(title: "📷", style: .Plain, target: self, action: "clickQR")
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "displayAlert:", name: "refreshUserList", object: nil)
    }
    
    
    func putControllers() {
        let width = self.view.frame.size.width
        let mapsController: WebViewController = self.storyboard?.instantiateViewControllerWithIdentifier("WebViewController") as! WebViewController
        mapsController.webViewURL = "\(Constants.kMAPTEAM)/\(APIUser.sharedInstance.getUserID())"
        let communicationController = self.storyboard?.instantiateViewControllerWithIdentifier("ComunicattionsController")
        mapsController.view.frame = CGRect(x: 0, y: 0, width: width, height: self.view.frame.size.height-cutBottomEdge)
        communicationController!.view.frame = CGRect(x: width, y: 0, width: width, height: self.view.frame.size.height-cutBottomEdge)
        self.addChildViewController(mapsController)
        self.addChildViewController(communicationController!)
        self.viewScrollViewContent.addSubview((mapsController.view)!)
        self.viewScrollViewContent.addSubview((communicationController?.view)!)
        
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        putControllers()
    }
    
    @IBAction func clickButton1(sender: AnyObject) {
        scrollViewController.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
    }
    
    @IBAction func clickButton2(sender: AnyObject) {
        scrollViewController.setContentOffset(CGPoint(x: self.view.frame.size.width, y: 0), animated: true)
    }
    
    func displayAlert(notification: NSNotification) {
        if notification.userInfo != nil {
            let string: String = (notification.userInfo!["aps"]!["alert"]) as! String
            labelAlert.text = string
            self.viewAlert.hidden = false
            
            let qualityOfServiceClass = QOS_CLASS_BACKGROUND
            let backgroundQueue = dispatch_get_global_queue(qualityOfServiceClass, 0)
            dispatch_async(backgroundQueue, {
                
                NSThread.sleepForTimeInterval(3)
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    UIView.animateWithDuration(0.3, animations: { () -> Void in
                        self.viewAlert.hidden = true
                        if string == "Pobjednik" || string == "Gubitnik" {
                            self.navigationController?.popViewControllerAnimated(true)
                        }
                    })
                })
            })
            
            
        }
    }

    func clickQR() {
        clickQRCodeStartScaning()
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
