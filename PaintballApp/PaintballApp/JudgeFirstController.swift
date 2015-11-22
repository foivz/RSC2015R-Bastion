//
//  JudgeFirstController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 22.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

class JudgeFirstController: UIViewController {
    
    @IBOutlet weak var navButton1: UIButton!
    @IBOutlet weak var navButton2: UIButton!
    @IBOutlet weak var navButton3: UIButton!
    @IBOutlet weak var viewScrollViewContent: UIView!
    @IBOutlet weak var scrollViewWidth: NSLayoutConstraint!
    @IBOutlet weak var scrollViewController: UIScrollView!
    var numberOfScreens: CGFloat = 3
    var cutBottomEdge: CGFloat = 58
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.scrollViewWidth.constant = self.view.frame.size.width * numberOfScreens
        
        
    }
    
    func putControllers() {
        let width = self.view.frame.size.width
        let mapsController: WebViewController = self.storyboard?.instantiateViewControllerWithIdentifier("WebViewJudgeController") as! WebViewController
        mapsController.webViewURL = Constants.kMAPADMIN
        let judgeTeamListController = self.storyboard?.instantiateViewControllerWithIdentifier("JudgeTeamListController") as! JudgeTeamListController
        let judgeTeamListController2 = self.storyboard?.instantiateViewControllerWithIdentifier("JudgeTeamListController") as! JudgeTeamListController
        judgeTeamListController.teamIndex = 0
        judgeTeamListController2.teamIndex = 1
        mapsController.view.frame = CGRect(x: 0, y: 0, width: width, height: self.view.frame.size.height-cutBottomEdge)
        judgeTeamListController.view.frame = CGRect(x: width, y: 0, width: width, height: self.view.frame.size.height-cutBottomEdge)
        judgeTeamListController2.view.frame = CGRect(x: width*2, y: 0, width: width, height: self.view.frame.size.height-cutBottomEdge)
        self.addChildViewController(mapsController)
        self.addChildViewController(judgeTeamListController)
        self.addChildViewController(judgeTeamListController2)
        self.viewScrollViewContent.addSubview((mapsController.view)!)
        self.viewScrollViewContent.addSubview((judgeTeamListController.view)!)
        self.viewScrollViewContent.addSubview((judgeTeamListController2.view)!)
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

    @IBAction func clickButton3(sender: AnyObject) {
        scrollViewController.setContentOffset(CGPoint(x: self.view.frame.size.width*2, y: 0), animated: true)
        
    }
}
