package com.orangelittle.u17.other_activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.orangelittle.u17.activity.BaseActivity;
import com.orangelittle.u17.R;

public class WebViewActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String val = intent.getStringExtra("val");
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl(val);
    }
}
