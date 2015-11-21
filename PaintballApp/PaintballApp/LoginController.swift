//
//  ViewController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit
import SimpleAuth
import SwiftyJSON

class LoginController: UIViewController, UITextFieldDelegate {
    
    var isLogin: Bool = true
    var topImageMoveAnimation: CGFloat = 300
    var bottomFieldsMoveAnimation: CGFloat = 58
    @IBOutlet weak var constraintLogoTop: NSLayoutConstraint!
    @IBOutlet weak var constraintMoveFields: NSLayoutConstraint!
    @IBOutlet weak var constraintBottomPush: NSLayoutConstraint!
    @IBOutlet weak var fieldFullName: CustomTextField!
    @IBOutlet weak var fieldEmail: CustomTextField!
    @IBOutlet weak var fieldPassword: CustomTextField!
    @IBOutlet weak var fieldConfirmPassword: CustomTextField!
    @IBOutlet weak var buttonConfirm: CustomButton!
    @IBOutlet weak var buttonRegister: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "onKeyboardHide", name: UIKeyboardWillHideNotification, object: nil)
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "onKeyboardShow", name: UIKeyboardWillShowNotification, object: nil)
        self.fieldFullName.delegate = self;
        self.fieldPassword.delegate = self;
        self.fieldConfirmPassword.delegate = self;
        self.fieldEmail.delegate = self;
    }
    
    @IBAction func clickConfirm(sender: AnyObject) {
        if isLogin {
            login()
        } else {
            register()
        }
    }
    
    @IBAction func clickChangeRegisterLogin(sender: AnyObject) {
        isLogin = !isLogin
        if isLogin {
            UIView.animateWithDuration(0.3, animations: { () -> Void in
                self.constraintLogoTop.constant = self.constraintLogoTop.constant + self.topImageMoveAnimation
                self.constraintMoveFields.constant = self.constraintMoveFields.constant - self.bottomFieldsMoveAnimation
                self.fieldConfirmPassword.alpha = 0
                self.fieldFullName.alpha = 0
                self.buttonConfirm.setTitle("Login", forState: UIControlState.Normal)
                self.buttonRegister.setTitle("Register", forState: UIControlState.Normal)
                self.view.layoutIfNeeded()
            })
        } else {
            UIView.animateWithDuration(0.3, animations: { () -> Void in
                self.constraintLogoTop.constant = self.constraintLogoTop.constant - self.topImageMoveAnimation
                self.constraintMoveFields.constant = self.constraintMoveFields.constant + self.bottomFieldsMoveAnimation
                self.fieldConfirmPassword.alpha = 1
                self.fieldFullName.alpha = 1
                self.buttonConfirm.setTitle("Register", forState: UIControlState.Normal)
                self.buttonRegister.setTitle("Login", forState: UIControlState.Normal)
                self.view.layoutIfNeeded()
            })
        }
    }
    
    @IBAction func clickTwitterLogin(sender: AnyObject) {
    }
    
    
    @IBAction func clickFacebookLogin(sender: AnyObject) {
    }
    
    
    func login() {
        APIUser.sharedInstance.login(self.fieldEmail.text!, password: self.fieldPassword.text!,
            withSuccess: { (json: JSON) -> Void in
                
                if let token = json["data"]["token"].string {
                    APIUser.sharedInstance.setToken("Bearer \(token)")
                    
                    print(json)
                    var teamListNavigationController = self.storyboard?.instantiateViewControllerWithIdentifier("UserListController")
                    self.presentViewController(teamListNavigationController!, animated: true, completion: nil)
                }
            }) { (error: NSError) -> Void in
        }
        
    }
    
    func register() {
        APIUser.sharedInstance.register(self.fieldEmail.text!, password: self.fieldPassword.text!, name: self.fieldFullName.text!, withSuccess: { (json: JSON) -> Void in
                print(json)
            }) { (error: NSError) -> Void in
        }
    }
    
    func getData() {
        APIData.sharedInstance.getListOfTeams({ (json: JSON) -> Void in
            
            for var i = 0; i < json["data"].count; i++ {
                print(json["data"][i]["description"].string)
            }
            
            }) { (error: NSError) -> Void in
                
        }
    }
    
    func onKeyboardHide() {
        UIView.animateWithDuration(0.3) { () -> Void in
            self.constraintBottomPush.constant = 18
            self.view.layoutIfNeeded()
        }
    }
    
    func onKeyboardShow() {
        UIView.animateWithDuration(0.3) { () -> Void in
            self.constraintBottomPush.constant = 220
            self.view.layoutIfNeeded()
        }
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true;
    }
}

