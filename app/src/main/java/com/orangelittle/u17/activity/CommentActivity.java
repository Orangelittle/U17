package com.orangelittle.u17.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.CommentAdapter;
import com.orangelittle.u17.entries.level1.CommentBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView comment_recycler;
    private ImageView comment_back;
    private String object_id;
    private String thread_id;
    private int page = 0;
    private CommentAdapter adapter;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        object_id = intent.getStringExtra("object_id");
        thread_id = intent.getStringExtra("thread_id");
        comment_recycler = ((RecyclerView) findViewById(R.id.comment_recyclerView));
        comment_back = ((ImageView) findViewById(R.id.comment_back));
        comment_back.setOnClickListener(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        comment_recycler.setLayoutManager(layoutManager);
        adapter = new CommentAdapter(new ArrayList<CommentBean.DataBean.ReturnDataBean.CommentListBean>(),this);
        comment_recycler.setAdapter(adapter);
        comment_recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(Color.parseColor("#cabdbd"));
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,2,0,2);
            }
        });
        comment_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                if (lastVisibleItemPosition == adapter.getSize() - 6 && !isLoading) {
                    isLoading = true;
                    netWork();
                }
            }
        });
        netWork();
    }

    private void netWork() {
        NetUtils.getData(ClassType.CommentBean, new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                adapter.addAll(response.body().getData().getReturnData().getCommentList());
                page++;
                isLoading = false;
                Log.e("自定义标签", "类名:==CommentActivity" + "方法名:==onResponse----" + page+"");
            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {
                Toast.makeText(CommentActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }
        },thread_id,object_id,page);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_back:
                finish();
                break;
        }
    }
}
