package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orangelittle.u17.activity.ChapterActivity;
import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.level1.Jump_From_SearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 姚川 on 2016/10/4.
 */

public class Jump_From_SearchAdapter extends RecyclerView.Adapter<Jump_From_SearchAdapter.ViewHolder> {
    private Context context;
    List<Jump_From_SearchBean.DataBean.ReturnDataBean.ComicsBean> comicsBeanList;
    LayoutInflater inflater;
    public Jump_From_SearchAdapter(Context context) {
        this.context = context;
        comicsBeanList =new ArrayList<>();
        inflater=LayoutInflater.from(this.context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.jump_from_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Jump_From_SearchBean.DataBean.ReturnDataBean.ComicsBean bean = comicsBeanList.get(position);
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
        holder.conTag.setTag(bean.getComicId());
        if (bean.getFlag() == 3) {
            holder.vip.setImageResource(R.mipmap.icon_comic_vip);
        }
    }

    @Override
    public int getItemCount() {
        return comicsBeanList ==null?0: comicsBeanList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final SimpleDraweeView coverImage;
        private final TextView name;
        private final TextView tags;
        private final TextView author;
        private final TextView description;
        private final TextView conTag;
        private final ImageView vip;
        private final LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            coverImage = ((SimpleDraweeView) itemView.findViewById(R.id.from_search_coverImage));
            name = ((TextView) itemView.findViewById(R.id.from_search_name));
            tags = ((TextView) itemView.findViewById(R.id.from_search_tags));
            author = ((TextView) itemView.findViewById(R.id.from_search_author));
            description = ((TextView) itemView.findViewById(R.id.from_search_description));
            conTag = ((TextView) itemView.findViewById(R.id.from_search_conTag));
            vip = ((ImageView) itemView.findViewById(R.id.from_search_vip));
            layout = ((LinearLayout) itemView.findViewById(R.id.from_search_layout));
            layout.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,ChapterActivity.class);
            String tag = (String)conTag.getTag();
            intent.putExtra("comicid",tag);
            context.startActivity(intent);
        }
    }

    public void addAll(List<Jump_From_SearchBean.DataBean.ReturnDataBean.ComicsBean> list) {
        this.comicsBeanList.addAll(list);
        notifyDataSetChanged();
    }

}
