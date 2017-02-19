package com.orangelittle.u17.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.orangelittle.u17.activity.BaseActivity;
import com.orangelittle.u17.R;

public class SpecialTopicDetailActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private Toolbar specialTopicToolbar;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_topic_detail);
        specialTopicToolbar = ((Toolbar) findViewById(R.id.special_topic_toolbar));
        setSupportActionBar(specialTopicToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        specialTopicToolbar.setNavigationIcon(R.mipmap.toolbar_back_normal);
        specialTopicToolbar.setNavigationOnClickListener(this);
        actionBar.setDisplayHomeAsUpEnabled(true);
        titleText = (TextView) findViewById(R.id.title);
        webView = (WebView) findViewById(R.id.web_view);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        titleText.setText(title);
        String url = intent.getStringExtra("url");
        if (url == null||url.length()==0) {
            String specialId = intent.getStringExtra("specialId");
            String isComment = intent.getStringExtra("isComment");
            url = "http://www.u17.com/z/zt/appspecial/special_comic_list_v3.html?is_comment=" + isComment + "&special_id=" + specialId;
        }


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
