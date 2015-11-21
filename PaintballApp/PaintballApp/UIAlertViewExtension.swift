//
//  UIAlertViewExtension.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

class CustomAlertView: UIAlertView {
    
    static func alertCreateTeamFail() {
        let alert = UIAlertView()
        alert.title = "Ups!"
        alert.message = "Kreiranje tima nije uspjelo :("
        alert.addButtonWithTitle("OK")
        alert.show()
    }
    
    static func alertFailWithText(tekst: String) {
        let alert = UIAlertView()
        alert.title = "Ups!"
        alert.message = tekst
        alert.addButtonWithTitle("OK")
        alert.show()
    }
    
    static func alertFailWithTextAndTitle(title: String, tekst: String) {
        let alert = UIAlertView()
        alert.title = "Ups!"
        alert.message = tekst
        alert.addButtonWithTitle("OK")
        alert.show()
    }
    
}
