package com.orangelittle.u17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.level1.CommentBean;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private List<CommentBean.DataBean.ReturnDataBean.CommentListBean> commentListBeen;
    private Context context;

    public CommentAdapter(List<CommentBean.DataBean.ReturnDataBean.CommentListBean> commentListBeen, Context context) {
        this.commentListBeen = commentListBeen;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_sub, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentBean.DataBean.ReturnDataBean.CommentListBean commentListBean = commentListBeen.get(position);
        holder.comment_content.setText(commentListBean.getContent());
        holder.comment_likecount.setText(commentListBean.getLikeCount()+"");
        holder.comment_name.setText(commentListBean.getNickname());
        holder.comment_touxiang.setImageURI(commentListBean.getFace());
    }

    @Override
    public int getItemCount() {
        return commentListBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView comment_touxiang;
        private final TextView comment_content;
        private final TextView comment_name;
        private final TextView comment_likecount;

        public ViewHolder(View itemView) {
            super(itemView);
            comment_touxiang = ((SimpleDraweeView) itemView.findViewById(R.id.comment_touxiang));
            comment_content = ((TextView) itemView.findViewById(R.id.comment_content));
            comment_name = ((TextView) itemView.findViewById(R.id.comment_name));
            comment_likecount = ((TextView) itemView.findViewById(R.id.comment_likecount));
        }
    }

    public void addAll(Collection collection) {
        commentListBeen.addAll(collection);
        notifyItemRangeInserted(commentListBeen.size(),collection.size());
    }

    public int getSize() {
        return commentListBeen.size();
    }
}
