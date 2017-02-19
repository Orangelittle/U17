package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orangelittle.u17.activity.CommentActivity;
import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.level1.DatailBean;
import com.orangelittle.u17.entries.level1.DetailGuessBean;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/9/29.
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder>{
    private static Context context;
    private int ids[];
    private List<Object> list;
    private static ChapterViewPagerAdapter chapterViewPagerAdapter;
    private static int size;
    private static List<String> titleList;
    private static boolean isDesc = true;
    private static String comicid;
    private static String thread_id;
    private static List<DatailBean.DataBean.ReturnDataBean.ChapterListBean> chapterListBean;

    public ChapterAdapter(Context context,List<Object> list,int ids[],String comicid) {
        this.context = context;
        this.list = list;
        this.ids = ids;
        this.comicid = comicid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object o = list.get(position);
        if(o!=null) {
            if (position == 0) {
                DatailBean.DataBean.ReturnDataBean.ComicBean comicBean = (DatailBean.DataBean.ReturnDataBean.ComicBean) o;
                holder.mTopicImg.setImageURI(comicBean.getCover());
                holder.mBackgroundImg.setImageURI(comicBean.getCover());
                holder.mTitle.setText(comicBean.getName());
                thread_id = comicBean.getThread_id();
                for (String theme : comicBean.getTheme_ids()) {
                    TextView textView = new TextView(context);
                    textView.setText(theme);
                    textView.setPadding(2,2,2,2);
                    textView.setTextColor(context.getResources().getColor(R.color.theme));
                    textView.setBackgroundResource(R.drawable.chapter_theme_shape);
                    holder.mTheme.addView(textView);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                    layoutParams.setMargins(5,0,0,0);
                    textView.setLayoutParams(layoutParams);
                }
                holder.mAuthor.setText(comicBean.getAuthor().getName());
                holder.mDescription.setText(comicBean.getShort_description());
                holder.mDiscriptionButton.setBackgroundResource(R.mipmap.icon_comic_detail_button);
            } else if (position == 1) {
                SharedPreferences sort = context.getSharedPreferences("sort", Context.MODE_PRIVATE);
                isDesc = sort.getBoolean("sort", true);
                titleList = new ArrayList<>();
                chapterListBean = (List<DatailBean.DataBean.ReturnDataBean.ChapterListBean>) o;
                size = chapterListBean.size();
                if(isDesc){
                    addDescList(titleList, size);
                    holder.mSortImg.setImageResource(R.mipmap.comic_detail_chapters_order_desc);
                    holder.mSort.setText("倒序");
                }else{
                    addAscList(titleList,size);
                    holder.mSortImg.setImageResource(R.mipmap.comic_detail_chapters_order);
                    holder.mSort.setText("正序");
                }
                chapterViewPagerAdapter = new ChapterViewPagerAdapter(titleList, context, chapterListBean,isDesc,comicid);
                holder.mChapterVP.setAdapter(chapterViewPagerAdapter);
                holder.mChapterTab.setupWithViewPager(holder.mChapterVP);
            } else if (position == 2) {
                List<DetailGuessBean.DataBean.ReturnDataBean> returnDataBeanList = (List<DetailGuessBean.DataBean.ReturnDataBean>) o;
                holder.mGuessRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                holder.mGuessRecycler.setAdapter(new ChapterRecyclerAdapter(returnDataBeanList, context));
            }
        }
    }

    public static void addDescList(List<String> list,int size) {
        list.clear();
        if (size % 15 > 0) {
            for (int i = 0; i < size / 15 + 1; i++) {
                if (i != size / 15) {
                    list.add((i * 15 + 1) + "-" + (i + 1) * 15);
                } else {
                    list.add((i * 15 + 1) + "-" + size);
                }
            }
        } else {
            for (int i = 0; i < size / 15; i++) {
                list.add((i * 15 + 1) + "-" + (i + 1) * 15);
            }
        }
    }
    public static void addAscList(List<String>list,int size){
        list.clear();
        if (size % 15 > 0) {
            for (int i = 0; i < size / 15 + 1; i++) {
                if (i != size / 15) {
                    list.add((size-15*i) + "-" + (size-15*(i+1)+1));
                } else {
                    list.add((size-15*i) + "-" + 1);
                }
            }
        } else {
            for (int i = 0; i < size / 15; i++) {
                list.add((size-15*i) + "-" + (size-15*(i+1)+1));
            }
        }
    }
    public void add(int index,Object o) {
        list.add(index,o);
        notifyItemInserted(index);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ids[position];
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout mTheme;

       // private boolean isAsc;
        private ImageView mSortImg;
        private LinearLayout mSortLayout;
        private RecyclerView mGuessRecycler;
        private TextView mMore;
        private SimpleDraweeView mBackgroundImg;
        private ImageView mDiscriptionButton;
        private TabLayout mChapterTab;
        private ViewPager mChapterVP;
        private TextView mComment;
        private TextView mTicket;
        private TextView mShare;
        private TextView mSort;
        private SimpleDraweeView mTopicImg;
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mBackgroundImg = ((SimpleDraweeView) itemView.findViewById(R.id.background_img));
            mTopicImg = ((SimpleDraweeView) itemView.findViewById(R.id.topic_img));
            mTitle = ((TextView) itemView.findViewById(R.id.title_tv));
            mAuthor = ((TextView) itemView.findViewById(R.id.author_tv));
            mTheme = ((LinearLayout) itemView.findViewById(R.id.theme));
            mDescription = ((TextView) itemView.findViewById(R.id.description_tv));
            mDiscriptionButton = ((ImageView) itemView.findViewById(R.id.comic_button));

            mChapterTab = ((TabLayout) itemView.findViewById(R.id.chapter_list_tab));
            mChapterVP = ((ViewPager) itemView.findViewById(R.id.chapter_list_vp));
            mComment = ((TextView) itemView.findViewById(R.id.comment_tv));
            mTicket = ((TextView) itemView.findViewById(R.id.ticket_tv));
            mShare = ((TextView) itemView.findViewById(R.id.share_tv));
            mSort = ((TextView) itemView.findViewById(R.id.sort_tv));
            mSortImg = ((ImageView) itemView.findViewById(R.id.sort_img));
            mSortLayout = ((LinearLayout) itemView.findViewById(R.id.sort_layout));
            if(mSortLayout!=null) {
                mSortLayout.setOnClickListener(this);
            }
            if (mShare != null) {
                mShare.setOnClickListener(this);
            }
            if (mComment != null) {
                mComment.setOnClickListener(this);
            }
            mGuessRecycler = ((RecyclerView) itemView.findViewById(R.id.guess_recycler));
            mMore = ((TextView) itemView.findViewById(R.id.more_tv));

        }
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.sort_layout:
                    if(chapterViewPagerAdapter!=null) {
                        if (isDesc) {
                            mSortImg.setImageResource(R.mipmap.comic_detail_chapters_order);
                            mSort.setText("正序");
                            addAscList(titleList, size);
                            reverse();

                        } else {
                            mSortImg.setImageResource(R.mipmap.comic_detail_chapters_order_desc);
                            mSort.setText("倒序");
                            addDescList(titleList, size);
                            reverse();
                        }
                        isDesc = !isDesc;
                        SharedPreferences sort = context.getSharedPreferences("sort", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sort.edit();
                        edit.putBoolean("sort",isDesc);
                        edit.apply();
                    }
                    break;
                case R.id.share_tv:
                    // TODO: 2017/2/15 0015 分享功能
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                    intent.putExtra(Intent.EXTRA_TEXT, "来自【有妖气漫画】的分享：" + "http://m.u17.com/c/"+comicid+".html");
                    context.startActivity(Intent.createChooser(intent, "请选择分享渠道"));
                    break;
                case R.id.comment_tv:
                     intent = new Intent(context, CommentActivity.class);
                    intent.putExtra("object_id", comicid);
                    intent.putExtra("thread_id", thread_id);
                    context.startActivity(intent);
                    break;
            }

        }

        private void reverse() {
            int currentItem = mChapterVP.getCurrentItem();
            chapterViewPagerAdapter = new ChapterViewPagerAdapter(titleList, context,chapterListBean,!isDesc,comicid);
            mChapterVP.setAdapter(chapterViewPagerAdapter);
            mChapterVP.setCurrentItem(currentItem);
            mChapterTab.setupWithViewPager(mChapterVP);
        }

    }
}
