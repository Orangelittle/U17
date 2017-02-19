package com.orangelittle.u17.activity;

import android.os.Bundle;

import com.orangelittle.u17.activity.BaseActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.fragment.level1.RankFragment;

public class RankActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new RankFragment()).commit();
    }
}
