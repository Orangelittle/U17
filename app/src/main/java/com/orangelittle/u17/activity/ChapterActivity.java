package com.orangelittle.u17.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.ChapterAdapter;
import com.orangelittle.u17.entries.CollectionBean;
import com.orangelittle.u17.entries.LookHistoryBean;
import com.orangelittle.u17.entries.level1.DatailBean;
import com.orangelittle.u17.entries.level1.DetailGuessBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterActivity extends BaseActivity implements Callback<DatailBean>, View.OnTouchListener {

    private ImageView mChapterBack;
    private ImageView mChapterDownload;
    private RecyclerView mRecyclerView;
    private List<Object> list;
    private int ids[];
    private ChapterAdapter mchapterAdapter;
    private LinearLayout preview;
    private LinearLayout mChapterBottom;
    private String comicid;
    private TextView collection;
    private TextView read;
    private TextView page;
    private int read_page;
    private boolean isRead;
    private SharedPreferences read_history;
    private DatailBean detailBean;


    private RealmAsyncTask transaction;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        mRealm=Realm.getDefaultInstance();

        Intent intent = getIntent();
        comicid = intent.getStringExtra("comicid");
        read_history = getSharedPreferences(comicid, MODE_PRIVATE);
        read_page = read_history.getInt("page", -1);
        isRead = read_history.getBoolean(comicid,false);
        mChapterBack = ((ImageView) findViewById(R.id.chapter_back));
        mChapterDownload = ((ImageView) findViewById(R.id.chapter_download));
        collection = ((TextView) findViewById(R.id.collection));
        read = ((TextView) findViewById(R.id.read));
        mRecyclerView = ((RecyclerView) findViewById(R.id.chapter_recycler));
        preview = ((LinearLayout) findViewById(R.id.preview));
        mChapterBottom = ((LinearLayout) findViewById(R.id.chapter_bottom));
        page = ((TextView) findViewById(R.id.page));
        if(read_page == -1){
            page.setText("暂未阅读");
        }else{
            page.setText("上次阅读到"+read_page+"话");
        }
        if (isRead) {
            read.setText("继续阅读");
        }

        collection.setText(hasCollection()?"已收藏":"添加收藏");

        list = new ArrayList<>();
        ids = new int[]{R.layout.chapter_head,R.layout.chapter_list,R.layout.chapter_guess};
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mchapterAdapter = new ChapterAdapter(ChapterActivity.this, list, ids,comicid);
        mRecyclerView.setAdapter(mchapterAdapter);
        mRecyclerView.setOnTouchListener(this);
        NetUtils.getData(ClassType.DatailBean,this,comicid);

    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.chapter_back:
                finish();
                break;
            case R.id.chapter_download:

                break;
            case R.id.collection:
                if (hasCollection()) {
                    collection.setText("添加收藏");
                    cancelCollection();

                }else {
                    collection.setText("已收藏");
                    collection();
                }
//                MyPopupWindow myPopupWindow = new MyPopupWindow(this);
//                myPopupWindow.showAtLocation(findViewById(R.id.activity_chapter), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.read:
                Intent intent = new Intent(this, ReadComicActivity.class);
                if (isRead) {
                    int position = read_history.getInt("position", -1);
                    intent.putExtra("showposition",position);
                }else{
                    intent.putExtra("showposition", 0);
                }
                intent.putExtra("comicid", comicid);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onResponse(Call<DatailBean> call, Response<DatailBean> response) {
        detailBean = response.body();
        mchapterAdapter.add(0, detailBean.getData().getReturnData().getComic());
        mchapterAdapter.add(1, detailBean.getData().getReturnData().getChapter_list());
        if (!hasRead()) {
            markHistory();
        }
        NetUtils.getData(ClassType.DetailGuessBean, new Callback<DetailGuessBean>() {
            @Override
            public void onResponse(Call<DetailGuessBean> call, Response<DetailGuessBean> response) {
                DetailGuessBean detailGuessBean = response.body();
                preview.setVisibility(View.GONE);
                mchapterAdapter.add(2, detailGuessBean.getData().getReturnData());
            }

            @Override
            public void onFailure(Call<DetailGuessBean> call, Throwable t) {
                Toast.makeText(ChapterActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFailure(Call<DatailBean> call, Throwable t) {
        Toast.makeText(this, "数据请求失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Animation out_animation = AnimationUtils.loadAnimation(this, R.anim.chapter_out_anim);
                out_animation.setFillAfter(true);
                mChapterBottom.startAnimation(out_animation);
                break;
            case MotionEvent.ACTION_UP:
                Animation in_animation = AnimationUtils.loadAnimation(this, R.anim.chapter_in_anim);
                in_animation.setFillAfter(true);
                mChapterBottom.startAnimation(in_animation);
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
        list = null;
        if (!mRealm.isClosed()) {
            mRealm.close();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (transaction != null && !transaction.isCancelled()) {
            transaction.cancel();
        }
    }

    /**
     * 是否已经收藏
     */
    private boolean hasCollection() {
        RealmResults<CollectionBean> results = mRealm.where(CollectionBean.class).equalTo("comicid", comicid).findAll();
        if (results.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 取消收藏
     */
    private void cancelCollection() {
        final RealmResults<CollectionBean> results = mRealm.where(CollectionBean.class).equalTo("comicid", comicid).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });

//        mRealm.executeTransaction(realm -> results.deleteAllFromRealm());
    }

    /**
     * 收藏
     */
    private void collection() {
        final    CollectionBean  collect = new CollectionBean();
        DatailBean.DataBean.ReturnDataBean.ComicBean dataBean=  ((DatailBean.DataBean.ReturnDataBean.ComicBean) detailBean.getData().getReturnData().getComic());

        collect.setAuthorName(dataBean.getAuthor().getName());
        collect.setImageUrl(dataBean.getCover());
        collect.setTitleName(dataBean.getName());
        collect.setComicid(comicid);
        transaction= mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(collect);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Snackbar.make(collection,"收藏失败!",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 是否已经浏览过
     */
    private boolean hasRead() {
        RealmResults<LookHistoryBean> results = mRealm.where(LookHistoryBean.class).equalTo("comicid", comicid).findAll();
        if (results.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 记录浏览历史
     */

    private void markHistory() {
        final LookHistoryBean mark = new LookHistoryBean();
        DatailBean.DataBean.ReturnDataBean.ComicBean dataBean=  ((DatailBean.DataBean.ReturnDataBean.ComicBean) detailBean.getData().getReturnData().getComic());

      mark.setHistoyname(dataBean.getName());
        mark.setHistoryurl(dataBean.getCover());
        mark.setHistorytitle(dataBean.getAuthor().getName());
        mark.setComicid(comicid);
        mark.setHistorydescribe(dataBean.getDescription());
        transaction= mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(mark);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Snackbar.make(collection,"记录失败!",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
