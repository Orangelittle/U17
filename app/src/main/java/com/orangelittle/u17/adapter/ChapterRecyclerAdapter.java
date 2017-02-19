package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orangelittle.u17.activity.ChapterActivity;
import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.level1.DetailGuessBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class ChapterRecyclerAdapter extends RecyclerView.Adapter<ChapterRecyclerAdapter.ViewHolder> {
    private List<DetailGuessBean.DataBean.ReturnDataBean> list;
    private static Context context;

    public ChapterRecyclerAdapter(List<DetailGuessBean.DataBean.ReturnDataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chapter_guess_sub, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DetailGuessBean.DataBean.ReturnDataBean returnDataBean = list.get(position);
        holder.data = returnDataBean;
        holder.mGuessImg.setImageURI(returnDataBean.getCover());
        holder.mGuessName.setText(returnDataBean.getName());
        holder.mGuessDiscrip.setText(returnDataBean.getShort_description());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private DetailGuessBean.DataBean.ReturnDataBean data;
        private SimpleDraweeView mGuessImg;
        private TextView mGuessName;
        private TextView mGuessDiscrip;

        public ViewHolder(View itemView) {
            super(itemView);
            mGuessImg = ((SimpleDraweeView) itemView.findViewById(R.id.guessImg_img));
            mGuessName = ((TextView) itemView.findViewById(R.id.guessName_tv));
            mGuessDiscrip = ((TextView) itemView.findViewById(R.id.guessDiscrip_tv));
            mGuessImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String comic_id = data.getComic_id();
            Intent intent = new Intent(context, ChapterActivity.class);
            intent.putExtra("comicid", comic_id);
            context.startActivity(intent);
        }
    }

}
