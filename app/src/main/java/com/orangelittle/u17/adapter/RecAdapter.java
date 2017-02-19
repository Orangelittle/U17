package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.activity.ChapterActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.RecommendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecHolder> {
    private Context context;
    private LayoutInflater minflater;
    private List<RecommendBean.DataBean.ReturnDataBean.DataListBean> mDataList;
    private RecyclerView mRecyclerView;

    public RecAdapter(Context context,RecyclerView mRecyclerView) {
        this.context = context;
        this.minflater = LayoutInflater.from(context);
        this.mRecyclerView=mRecyclerView;
        mDataList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
       this.mRecyclerView=recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        recyclerView=null;
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.rec_level_1, parent, false);
        return new RecHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecHolder holder, int position) {
        if(mDataList.get(position).getTitle().equals("测试")){
            holder.rec_title.setTextSize(35);
//            holder.rec_title.setGravity(View.TEXT_ALIGNMENT_CENTER);
            holder.rec_title.setText("队长！到底啦！！!");
            holder.rec_image.setImageResource(R.mipmap.main_reminder_bottom);
            holder.rec_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //// TODO: 2017/1/20 0020  底部最后条空数据弹窗
                    Snackbar.make(holder.rec_image,"甭点了，木有数据啦！",Snackbar.LENGTH_SHORT).setAction("·点击回到顶部哦·", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //// TODO: 2017/1/20 0020 点击回到Recyclerview第一条数据
                                mRecyclerView.smoothScrollToPosition(0);
                        }
                    }).show();
                }
            });
        }else {
            holder.rec_title.setTextSize(15);
            holder.rec_title.setText(mDataList.get(position).getTitle());
            holder.rec_image.setImageURI(mDataList.get(position).getCover());
        }
        String subTitle = mDataList.get(position).getSubTitle();
        String rightFlag = mDataList.get(position).getRightFlag();
        if (subTitle != null) {
            holder.rec_subtitle.setText(subTitle);
        } else {
            holder.rec_subtitle.setText("专题");
        }

        if (rightFlag.equals("new")) {
            holder.rec_jian.setImageResource(R.mipmap.main_recommend_rank_new);
        } else if (rightFlag.equals("recommend")) {
            holder.rec_jian.setImageResource(R.mipmap.main_recommend_rank_jian);
        }
        holder.rec_subtitle.setTag(mDataList.get(position).getExt().get(0).getVal());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addAll(List<RecommendBean.DataBean.ReturnDataBean.DataListBean> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
//        mDataList.addAll(dataList);
//        notifyItemRangeInserted(mDataList.size() - dataList.size(), dataList.size());
    }

    public void removeAll() {
        notifyItemRangeRemoved(0, mDataList.size());
        mDataList.clear();
    }

    public class RecHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView rec_title;
        private final TextView rec_subtitle;
        private final SimpleDraweeView rec_image;
        private final ImageView rec_jian;

        public RecHolder(View itemView) {
            super(itemView);
            rec_title = ((TextView) itemView.findViewById(R.id.rec_title));
            rec_subtitle = ((TextView) itemView.findViewById(R.id.rec_subtitle));
            rec_image = ((SimpleDraweeView) itemView.findViewById(R.id.rec_image));
            rec_jian = ((ImageView) itemView.findViewById(R.id.rec_jian));
            rec_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                Intent intent = new Intent(context, ChapterActivity.class);
                intent.putExtra("comicid", ((String) rec_subtitle.getTag()));
                intent.putExtra("comicid", (String) rec_subtitle.getTag());
                context.startActivity(intent);

        }
    }
}
