//
//  CustomView.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

@IBDesignable class CustomView: UIView {
    
    var hasRoundedCorners: Bool = true
    
    @IBInspectable var roundedCorners: Bool {
        get {
            return hasRoundedCorners
        }
        set(roundedCorners) {
            self.hasRoundedCorners = roundedCorners
            if self.hasRoundedCorners {
                self.addRoundedCorners()
            } else {
                self.removeRoundedCorners()
            }
        }
    }
    
    @IBInspectable var borderWidth: CGFloat {
        get {
            return self.layer.borderWidth
        }
        set(borderWidth) {
            self.layer.borderWidth = borderWidth
        }
    }
    
    @IBInspectable var borderColor: UIColor? {
        get {
            return UIColor(CGColor: self.layer.borderColor!)
        }
        set(borderColor) {
            return self.layer.borderColor = borderColor?.CGColor
        }
    }
    
}
