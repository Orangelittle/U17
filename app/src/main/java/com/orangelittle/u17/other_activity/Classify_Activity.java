package com.orangelittle.u17.other_activity;

import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.orangelittle.u17.activity.BaseActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.ClassifyAdapter;
import com.orangelittle.u17.entries.ClassifyBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Classify_Activity extends BaseActivity implements View.OnClickListener {

    private RecyclerView classify_recycle;
    private ImageView classify_image;
    private ClassifyAdapter classifyAdapter;
    private ClassifyBean classifyBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_);
        classify_recycle = (RecyclerView) findViewById(R.id.classify_recycle);
        classify_image = (ImageView) findViewById(R.id.classify_toolbar_image);
        classify_recycle.setLayoutManager(new GridLayoutManager(this,3));
        classifyAdapter = new ClassifyAdapter(this);
        classify_image.setOnClickListener(this);
        classify_recycle.setAdapter(classifyAdapter);
        NetUtils.getData(ClassType.ClassifyBean, new Callback<ClassifyBean>() {
            @Override
            public void onResponse(Call<ClassifyBean> call, Response<ClassifyBean> response) {
                classifyBean = response.body();
                classifyAdapter.addAll(classifyBean.getData().getReturnData().getRankinglist());
            }

            @Override
            public void onFailure(Call<ClassifyBean> call, Throwable t) {
                Snackbar.make(classify_recycle,"数据获取失败",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
