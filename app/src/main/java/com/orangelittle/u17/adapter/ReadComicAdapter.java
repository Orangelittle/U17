package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orangelittle.u17.entries.level1.ReadComicBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */

public class ReadComicAdapter extends RecyclerView.Adapter<ReadComicAdapter.ViewHolder>{
    private List<ReadComicBean.DataBean.ReturnDataBean.ImageListBean> list;
    private Context context;
    private List<Integer> positionList;
    private String comicid;
    public ReadComicAdapter(List<ReadComicBean.DataBean.ReturnDataBean.ImageListBean> list, Context context, List<Integer> positionList,String comicid) {
        this.list = list;
        this.context = context;
        this.positionList = positionList;
        this.comicid = comicid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.readcomic_sub, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SharedPreferences.Editor edit = context.getSharedPreferences(comicid, Context.MODE_PRIVATE).edit();
        edit.putInt("position", position);
        edit.apply();
        ReadComicBean.DataBean.ReturnDataBean.ImageListBean imageListBean = list.get(position);
        float aspectRatio =  (Integer.parseInt(imageListBean.getWidth())*1.0f / Integer.parseInt(imageListBean.getHeight()));
        holder.ImgPreview.setVisibility(View.VISIBLE);
        holder.mReadComicImg.setAspectRatio(aspectRatio);
        holder.position.setText(positionList.get(position)+"");
        ControllerListener listener = new BaseControllerListener(){
            @Override
            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                holder.ImgPreview.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Toast.makeText(context, "加载失败，请检查你的网络", Toast.LENGTH_SHORT).show();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageListBean.getImg05())
                .setAutoPlayAnimations(true)
                .setControllerListener(listener)
                .build();
        holder.mReadComicImg.setController(controller);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView position;
        private final FrameLayout ImgPreview;
        private  SimpleDraweeView mReadComicImg;
        public ViewHolder(View itemView) {
            super(itemView);
            mReadComicImg = ((SimpleDraweeView) itemView.findViewById(R.id.readcomic_img));
            position = ((TextView) itemView.findViewById(R.id.position));
            ImgPreview = ((FrameLayout) itemView.findViewById(R.id.ImgPreview));
        }
    }

}
