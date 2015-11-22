package com.bozidar.labas.microdroid.fragments;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import butterknife.Bind;

/**
 * Created by Bozidar on 22.11.2015..
 */
public class JudgeFragmentMap extends MicroTabFrag {

    private static final String ARG_PARAM1 = "data1";

    @Bind(R.id.webView1)
    WebView webView;

    @Override
    public String setTabTitle() {
        return getArguments().getString(ARG_PARAM1);
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_map;
    }

    @Override
    public void init() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://bozidarlabas.from.hr/adminview");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public static JudgeFragmentMap newInstance(String param1) {
        JudgeFragmentMap fragment = new JudgeFragmentMap();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
}