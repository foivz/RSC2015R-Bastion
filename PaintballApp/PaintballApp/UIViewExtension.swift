//
//  UIViewExtension.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

extension UIView {
    
    func addRoundedCorners() {
        self.layer.cornerRadius = self.frame.size.height/2
        self.layer.masksToBounds = true
    }
    
    func removeRoundedCorners() {
        self.layer.cornerRadius = 0
        self.layer.masksToBounds = true
    }
    
    func addRoundedCornersWithOutlineAndClearBackground() {
        self.addRoundedCorners()
        self.layer.borderWidth = 1
        self.layer.borderColor = UIColor(white: 1, alpha: 0.5).CGColor
        self.backgroundColor = UIColor.clearColor()
    }
    
    
    func addOutline() {
        self.layer.borderWidth = 1
        self.layer.borderColor = UIColor(white: 1, alpha: 0.5).CGColor
    }
    
    func removeOutline() {
        self.layer.borderWidth = 0
    }
    
}
