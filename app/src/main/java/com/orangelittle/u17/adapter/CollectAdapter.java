package com.orangelittle.u17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.CollectionBean;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyViewHolder>implements View.OnClickListener {

    private List<CollectionBean> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private RecyclerView mRecyclerView;
    public CollectAdapter(List<CollectionBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.collect_item,parent,false);
            view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CollectionBean bean=mList.get(position);

        holder.collect_titlename.setText(bean.getAuthorName());
        holder.collect_authorname.setText(bean.getTitleName());
        holder.collect_pic.setImageURI(bean.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            int position = mRecyclerView.getChildAdapterPosition(v);
            mOnItemClickListener.onItemClick(mRecyclerView, v, position, mList.get(position));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent,View item, int position,CollectionBean bean);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView itemView, int position);
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


    public void add(int location, CollectionBean item) {
        mList.add(location, item);
        notifyItemInserted(location);
        notifyDataSetChanged();
    }

    public  void add(CollectionBean item) {
        mList.add(item);
        int location = mList.size() - 1;
        notifyItemInserted(location);
        notifyDataSetChanged();
    }


    public final void addAll(List<CollectionBean> items) {
        if (items == null || items.isEmpty()) {
            Log.w(TAG, "addAll: The list you passed contains no elements.");
            return;
        }
        int location =getItemCount();
        mList.addAll(items);
        notifyItemRangeInserted(location, items.size());
        notifyDataSetChanged();
    }

    public void addAll(int location, List<CollectionBean> items) {
        if (items == null || items.isEmpty()) {
            Log.w(TAG, "addAll: The list you passed contains no elements.");
            return;
        }
        if (location < 0 || location >getItemCount()) {
            Log.w(TAG, "addAll: IndexOutOfBoundsException");
            return;
        }
        mList.addAll(location, items);
        notifyItemRangeInserted(location, items.size());
        notifyDataSetChanged();
    }


    public  void remove(int location) {
        mList.remove(location);
        notifyItemRemoved(location);
        notifyDataSetChanged();
    }


    public void removeAll(List<CollectionBean> items) {
        mList.removeAll(items);
        notifyDataSetChanged(); // RecyclerView
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView collect_pic;
        private final TextView collect_authorname;
        private final TextView collect_titlename;

        public MyViewHolder(View itemView) {
            super(itemView);
            collect_pic = ((SimpleDraweeView) itemView.findViewById(R.id.collection_pic));
            collect_authorname = ((TextView) itemView.findViewById(R.id.collection_author_name));
            collect_titlename = ((TextView) itemView.findViewById(R.id.collection_title));


        }
    }
}
