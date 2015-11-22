//
//  WebViewController.swift
//  PaintballApp
//
//  Created by Bojan Jakuš on 22.11.2015..
//  Copyright © 2015. Bastion. All rights reserved.
//

import UIKit

class WebViewController: UIViewController {

    @IBOutlet weak var webview: UIWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.webview.loadRequest(NSURLRequest(URL: NSURL(string: "http://www.bug.hr")!))
        
    }

}
