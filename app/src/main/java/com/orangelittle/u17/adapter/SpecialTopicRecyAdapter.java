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
import com.orangelittle.u17.entries.level1.SpecialTopicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmc on 16-9-30.
 */

public class SpecialTopicRecyAdapter extends RecyclerView.Adapter<SpecialTopicRecyAdapter.ViewHolder> {

    private Context context;
    private List<SpecialTopicBean.DataBean.ReturnDataBean.ComicsBean> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.special_topic_recy_item, parent, false);
        return new ViewHolder(view);
    }

    public SpecialTopicRecyAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addAll(List<SpecialTopicBean.DataBean.ReturnDataBean.ComicsBean> list) {

        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SpecialTopicBean.DataBean.ReturnDataBean.ComicsBean bean = list.get(position);
        holder.itemView.setOnClickListener(bean);
        holder.converImage.setImageURI(bean.getCover());
        holder.title.setText(bean.getTitle());
        holder.subTitle.setText(bean.getSubTitle());
        switch (bean.getSpecialType()) {
            case 1:
                holder.itemTypePic.setImageResource(R.mipmap.boutique_special_tag_comic);
                break;

            case 2:
                holder.itemTypePic.setImageResource(R.mipmap.boutique_special_tag_space);
                break;
        }
    }

    @Override
    public int getItemCount() {
        int count=list==null?0:list.size();
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView subTitle;
        private final SimpleDraweeView converImage;
        private final ImageView itemTypePic;

        public ViewHolder(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.title));
            subTitle = ((TextView) itemView.findViewById(R.id.sub_title));
            converImage = ((SimpleDraweeView) itemView.findViewById(R.id.coverImage));
            itemTypePic = ((ImageView) itemView.findViewById(R.id.item_type_pic));
        }
    }
}

