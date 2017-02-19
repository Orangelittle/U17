package com.orangelittle.u17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.level1.RankListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmc on 16-9-30.
 */

public class RankItemAdapter extends RecyclerView.Adapter<RankItemAdapter.ViewHolder> {

    private Context context;
    private List<RankListBean.DataBean.ReturnDataBean.ComicsBean> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rank_item_recy_item, parent, false);

        return new ViewHolder(view);
    }

    public RankItemAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addAll(List<RankListBean.DataBean.ReturnDataBean.ComicsBean> list) {

        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RankListBean.DataBean.ReturnDataBean.ComicsBean bean = list.get(position);
        holder.itemView.setOnClickListener(bean);
        holder.name.setText(bean.getName());
        StringBuffer buffer = new StringBuffer();
        for (String s : bean.getTags()) {
            buffer.append(s+" ");
        }
        holder.tags.setText(buffer.toString());
        holder.author.setText(bean.getAuthor());
        holder.description.setText(bean.getDescription());
        holder.conTag.setText(bean.getConTag());
        holder.coverImage.setImageURI(bean.getCover());
        holder.numberNo.setText("");
       if (position == 0) {
            holder.numberImage.setImageResource(R.mipmap.icon_comic_first);
        } else if (position == 1) {
            holder.numberImage.setImageResource(R.mipmap.icon_comic_second);
        } else if (position == 2) {
            holder.numberImage.setImageResource(R.mipmap.icon_comic_third);
        } else {
            holder.numberImage.setImageResource(R.mipmap.icon_comic_forth);
            holder.numberNo.setText(position+1+"");
        }

        if (bean.getFlag() == 3) {
            holder.vip.setImageResource(R.mipmap.icon_comic_vip);
        }
    }

    @Override
    public int getItemCount() {
        int count=list==null?0:list.size();
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView coverImage;
        private final TextView name;
        private final TextView tags;
        private final TextView author;
        private final TextView description;
        private final TextView conTag;
        private final ImageView numberImage;
        private final TextView numberNo;
        private final ImageView vip;

        public ViewHolder(View itemView) {
            super(itemView);
            coverImage = ((SimpleDraweeView) itemView.findViewById(R.id.coverImage));
            name = ((TextView) itemView.findViewById(R.id.name));
            tags = ((TextView) itemView.findViewById(R.id.tags));
            author = ((TextView) itemView.findViewById(R.id.author));
            description = ((TextView) itemView.findViewById(R.id.description));
            conTag = ((TextView) itemView.findViewById(R.id.conTag));
            numberImage = ((ImageView) itemView.findViewById(R.id.number_image));
            numberNo = ((TextView) itemView.findViewById(R.id.number_no));
            vip = ((ImageView) itemView.findViewById(R.id.vip));
        }
    }
}

