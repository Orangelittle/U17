package com.orangelittle.u17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangelittle.u17.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ice on 2016/10/5.
 */

public class GameDetailAdapter extends RecyclerView.Adapter<GameDetailAdapter.VH> {

    private Context context;
    private List<String> smallPictureUrls;
    private final LayoutInflater inflater;

    public GameDetailAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        smallPictureUrls = new ArrayList<>();
    }

    public void addAll(List<String> smallPictureUrls) {
        this.smallPictureUrls.addAll(smallPictureUrls);
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(inflater.inflate(R.layout.game_detail_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.mImageView.setImageURI(smallPictureUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return smallPictureUrls == null ? 0 : smallPictureUrls.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mImageView;

        public VH(View itemView) {
            super(itemView);
            mImageView = ((SimpleDraweeView) itemView.findViewById(R.id.smallPictureUrl));
        }
    }
}
