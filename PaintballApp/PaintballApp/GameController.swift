//
//  GameController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

class GameController: UIViewController {

    @IBOutlet weak var navButton1: UIButton!
    @IBOutlet weak var navButton2: UIButton!
    @IBOutlet weak var viewScrollViewContent: UIView!
    @IBOutlet weak var scrollViewWidth: NSLayoutConstraint!
    @IBOutlet weak var scrollViewController: UIScrollView!
    var numberOfScreens: CGFloat = 2
    var cutBottomEdge: CGFloat = 100
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.scrollViewWidth.constant = self.view.frame.size.width * numberOfScreens
        
        
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

}
