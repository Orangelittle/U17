package com.orangelittle.u17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.GameBean;
import com.orangelittle.u17.widget.FlickerProgressBar;

import java.util.List;
import java.util.Locale;

import static com.orangelittle.u17.R.id.downUrl;

public class GameHeaderAdapter extends RecyclerView.Adapter<GameHeaderAdapter.ViewHolder> {
    private final LayoutInflater mInflater;
    private Context mContext;
    private List<GameBean.DataBean.ReturnDataBean.GameheaderBean.RecommandsBean> mList;

    public GameHeaderAdapter(Context context, List<GameBean.DataBean.ReturnDataBean.GameheaderBean.RecommandsBean> mList) {
        this.mContext = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.game_item_v, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GameBean.DataBean.ReturnDataBean.GameheaderBean.RecommandsBean recommandsBean = mList.get(position);
        holder.coverUrl.setImageURI(recommandsBean.getCoverUrl());
        holder.title.setText(recommandsBean.getTitle());
        holder.gameType.setText(recommandsBean.getGameType());
        holder.size.setText(String.format(Locale.CHINA, "%.2fMB", ((float) recommandsBean.getSize())/1024/1024));
        holder.coverUrl.setOnClickListener(recommandsBean);
        holder.downUrl.setOnClickListener(recommandsBean);
        holder.downUrl.initState(recommandsBean.getDownUrl());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView coverUrl;
        private TextView title;
        private TextView gameType;
        private TextView size;
        private FlickerProgressBar downUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            coverUrl = (SimpleDraweeView) itemView.findViewById(R.id.coverUrl);
            title = (TextView) itemView.findViewById(R.id.title);
            gameType = (TextView) itemView.findViewById(R.id.gameType);
            size = (TextView) itemView.findViewById(R.id.size);
            downUrl = (FlickerProgressBar) itemView.findViewById(R.id.downUrl);
        }
    }
}
