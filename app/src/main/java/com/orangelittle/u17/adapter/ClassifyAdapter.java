package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orangelittle.u17.activity.JumpFromSearch;
import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.ClassifyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder>{

    private final List<ClassifyBean.DataBean.ReturnDataBean.RankinglistBean> rankinglist;
    private Context context;
    private LayoutInflater inflater;
    public ClassifyAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(this.context);
        rankinglist = new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.classify_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.classify_image.setImageURI(rankinglist.get(position).getCover());
        holder.classify_title.setText(rankinglist.get(position).getSortName());
        Bundle bundle=new Bundle();
        bundle.putString("argCon",rankinglist.get(position).getArgCon());
        bundle.putString("argName",rankinglist.get(position).getArgName());
        bundle.putString("argValue",rankinglist.get(position).getArgValue());
        bundle.putString("title",rankinglist.get(position).getSortName());
        holder.classify_title.setTag(bundle);
    }

    @Override
    public int getItemCount() {
        return rankinglist.size();
    }
    public void addAll(List<ClassifyBean.DataBean.ReturnDataBean.RankinglistBean> rankinglist){
        this.rankinglist.addAll(rankinglist);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final SimpleDraweeView classify_image;
        private final TextView classify_title;

        public ViewHolder(View itemView) {
            super(itemView);
            classify_image = ((SimpleDraweeView) itemView.findViewById(R.id.classify_item_image));
            classify_title = ((TextView) itemView.findViewById(R.id.classify_item_title));
            classify_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle tag = (Bundle) classify_title.getTag();
            Intent intent=new Intent(context, JumpFromSearch.class);
            intent.putExtras(tag);
            context.startActivity(intent);
        }
    }
}
