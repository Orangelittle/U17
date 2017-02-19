package com.orangelittle.u17.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.orangelittle.u17.activity.BaseActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.fragment.level1.SpecialTopicFragment;

public class SpecialTopicActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar specialTopicToolbar;
    private String argCon = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_topic);
        specialTopicToolbar = ((Toolbar) findViewById(R.id.special_topic_toolbar));
        setSupportActionBar(specialTopicToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        specialTopicToolbar.setNavigationIcon(R.mipmap.toolbar_back_normal);
        specialTopicToolbar.setNavigationOnClickListener(this);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().add(R.id.container,SpecialTopicFragment.newInstance(argCon)).commit();

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
