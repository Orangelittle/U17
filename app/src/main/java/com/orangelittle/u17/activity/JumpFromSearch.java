package com.orangelittle.u17.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.Jump_From_SearchAdapter;
import com.orangelittle.u17.entries.level1.Jump_From_SearchBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JumpFromSearch extends BaseActivity implements View.OnClickListener {

    private Toolbar jmptoolbar;
    private TextView jmptext_title;
    private ImageView jmpimage;
    private int page = 1;
    private RecyclerView jmprecycle;
    private Jump_From_SearchAdapter adapter;
    private Jump_From_SearchBean searchBean;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoadingMore = false;
    private String argCon;
    private int con;
    private String argName;
    private String argValue;
    private int value;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_from_search);
        jmprecycle = (RecyclerView) findViewById(R.id.activity_jump_from_search_recycle);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        argCon = extras.getString("argCon");
        con = Integer.parseInt(argCon);
        argName = extras.getString("argName");
        argValue = extras.getString("argValue");
        value = Integer.parseInt(argValue);
        title = extras.getString("title");
        jmptoolbar = (Toolbar) findViewById(R.id.activity_jump_from_search_toolbar);
        jmpimage = (ImageView) findViewById(R.id.activity_jump_from_search_image);
        jmpimage.setOnClickListener(this);
        jmptext_title = (TextView) findViewById(R.id.activity_jump_from_search_title);
        jmptext_title.setText(title);
        adapter = new Jump_From_SearchAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        jmprecycle.setLayoutManager(mLayoutManager);
        jmprecycle.setAdapter(adapter);
        NetUtils.getData(ClassType.Jump_From_SearchBean, new Callback<Jump_From_SearchBean>() {
            @Override
            public void onResponse(Call<Jump_From_SearchBean> call, Response<Jump_From_SearchBean> response) {
                searchBean = response.body();
                List<Jump_From_SearchBean.DataBean.ReturnDataBean.ComicsBean> comics = searchBean.getData().getReturnData().getComics();
                adapter.addAll(comics);

            }

            @Override
            public void onFailure(Call<Jump_From_SearchBean> call, Throwable t) {
                Toast.makeText(JumpFromSearch.this, "数据加载失败", Toast.LENGTH_SHORT).show();
            }
        }, value, argName, con, page);
        jmprecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 5 && dy > 0) {
                    if (isLoadingMore) {

                    } else {
                        NetUtils.getData(ClassType.Jump_From_SearchBean, new Callback<Jump_From_SearchBean>() {
                            @Override
                            public void onResponse(Call<Jump_From_SearchBean> call, Response<Jump_From_SearchBean> response) {
                                searchBean = response.body();
                                List<Jump_From_SearchBean.DataBean.ReturnDataBean.ComicsBean> comics = searchBean.getData().getReturnData().getComics();
                                adapter.addAll(comics);
                                isLoadingMore = false;
                            }

                            @Override
                            public void onFailure(Call<Jump_From_SearchBean> call, Throwable t) {
                                Toast.makeText(JumpFromSearch.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                            }
                        }, value, argName, con, ++page);
                        isLoadingMore = true;
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
