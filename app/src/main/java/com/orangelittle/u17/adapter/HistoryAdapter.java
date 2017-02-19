package com.orangelittle.u17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.LookHistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>implements View.OnClickListener {

    private List<LookHistoryBean> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    private RecyclerView mRecyclerView;
    public HistoryAdapter(List<LookHistoryBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.history_item,parent,false);
            view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LookHistoryBean bean=mList.get(position);

        holder.collect_titlename.setText(bean.getHistorytitle());
        holder.collect_authorname.setText(bean.getHistoyname());
        holder.collect_pic.setImageURI(bean.getHistoryurl());
        holder.collect_describe.setText(bean.getHistorydescribe());
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            int position = mRecyclerView.getChildAdapterPosition(v);
            mOnItemClickListener.onItemClick(mRecyclerView, v, position, mList.get(position));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View item, int position, LookHistoryBean bean);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }

    public  void remove(int location) {
        mList.remove(location);
        notifyItemRemoved(location);
        notifyDataSetChanged();
    }


    public void removeAll(List<LookHistoryBean> items) {
        mList.removeAll(items);
        notifyDataSetChanged(); // RecyclerView
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView collect_pic;
        private final TextView collect_authorname;
        private final TextView collect_titlename;
        private final TextView collect_describe;

        public MyViewHolder(View itemView) {
            super(itemView);
            collect_pic = ((SimpleDraweeView) itemView.findViewById(R.id.collection_pic));
            collect_authorname = ((TextView) itemView.findViewById(R.id.history_name));
            collect_titlename = ((TextView) itemView.findViewById(R.id.history_title));
            collect_describe = ((TextView) itemView.findViewById(R.id.history_describe));


        }
    }
}
