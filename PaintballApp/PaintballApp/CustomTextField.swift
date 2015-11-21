//
//  CustomField.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 21.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

@IBDesignable class CustomTextField: UITextField {
    
    var hasRoundedCorners: Bool = true
    var padding: CGFloat = 15.0
    var signSize: CGFloat = 15.0
    var leftImageStorage: UIImage?
    let leftImageTag = 132
    let rightImageTag = 412
    var rightImageStorage: UIImage?
    
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
    
    @IBInspectable var leftImage: UIImage? {
        get {
            return leftImageStorage
        }
        set(leftImage) {
            leftImageStorage = leftImage
            if leftImage != nil {
                self.removeLeftImageFromView()
                let leftPadding = self.frame.size.height-20 > 38 ? 38 : self.frame.size.height-20
                let imageView = UIImageView(frame: CGRectMake(10, 10, leftPadding, self.frame.size.height-20))
                imageView.tag = leftImageTag
                imageView.contentMode = UIViewContentMode.ScaleAspectFit
                imageView.image = leftImage
                self.addSubview(imageView)
            } else {
                self.removeLeftImageFromView()
            }
        }
    }
    
    @IBInspectable var rightImage: UIImage? {
        get {
            return rightImageStorage
        }
        set(rightImage) {
            self.removeRightImageFromView()
            rightImageStorage = rightImage
            if rightImage != nil {
                let imageView = UIImageView(frame: CGRectMake(self.frame.size.width-signSize-padding, self.frame.size.height/2-padding/2, signSize, signSize))
                imageView.tag = rightImageTag
                imageView.contentMode = UIViewContentMode.ScaleAspectFit
                imageView.image = rightImage
                self.addSubview(imageView)
            } else {
                self.removeRightImageFromView()
            }
        }
    }
    
    func removeRightImageFromView() {
        for view in self.subviews {
            if view.tag == rightImageTag {
                view.removeFromSuperview()
            }
        }
    }
    
    func removeLeftImageFromView() {
        for view in self.subviews {
            if view.tag == leftImageTag {
                view.removeFromSuperview()
            }
        }
    }
    
    override func textRectForBounds(bounds: CGRect) -> CGRect {
        let leftPadding: CGFloat = leftImage != nil ? self.frame.size.height > 50 ? 50 :self.frame.size.height : self.padding
        return CGRectInset(bounds, leftPadding, self.padding)
    }
    
    override func editingRectForBounds(bounds: CGRect) -> CGRect {
        let leftPadding: CGFloat = leftImage != nil ? self.frame.size.height > 50 ? 50 :self.frame.size.height : self.padding
        return CGRectInset(bounds, leftPadding, self.padding)
    }
    
}