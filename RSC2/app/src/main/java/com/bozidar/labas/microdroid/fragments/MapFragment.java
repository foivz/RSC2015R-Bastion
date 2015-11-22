package com.bozidar.labas.microdroid.fragments;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import butterknife.Bind;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class MapFragment extends MicroTabFrag {

    private static final String ARG_PARAM1 = "data1";

    @Bind(R.id.webView1)
    WebView webView;

    SharedPrefs prefs = SharedPrefs.getInstance();
    private User user;

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
        user = prefs.loadObject(getResources().getString(R.string.user_data), getMicroActivity());
        Log.d("ididid", user.getId());
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setJavaScriptEnabled(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setLoadWithOverviewMode(false);
        ws.setSupportMultipleWindows(true);
        ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webView.loadUrl("http://bozidarlabas.from.hr/viewmyteam/" + user.getId());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public static MapFragment newInstance(String param1) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
}
