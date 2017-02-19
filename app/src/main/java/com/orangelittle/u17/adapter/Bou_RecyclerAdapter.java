package com.orangelittle.u17.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.orangelittle.u17.BR;
import com.orangelittle.u17.activity.ChapterActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.entry.BouItemSecond;
import com.orangelittle.u17.entry.Bou_Comic;
import com.orangelittle.u17.entry.Bou_ComicList_Item;
import com.orangelittle.u17.entry.Bou_ReturnData;
import com.orangelittle.u17.entry.Bout_GalleryItem;
import com.orangelittle.u17.other_activity.WebViewActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/30.
 */

public class Bou_RecyclerAdapter extends RecyclerView.Adapter<Bou_RecyclerAdapter.BindingViewHolder> implements ViewPager.OnPageChangeListener , Handler.Callback, View.OnClickListener {

    private Context mContext ;
    private List<Object> list ;
    private Map<Class<?> , Pair<Integer,Integer>> map;

    private Map<Class<?> ,Integer> indexMap = new HashMap<>(); //class 和 comicsBeanList 的 i
    private List<Pair<Integer,Integer>> pList = new ArrayList<>();// id 和 BR
    private final LayoutInflater inflater;
    private ViewDataBinding binding;
    private ViewPager pager;
    private View img;
    private LinearLayout layout;
    private int size;
    private Handler handler = new Handler(this);
    private int currentPosition;
    private RecyclerView recyclerView;
    private List<Bout_GalleryItem> galleryItems;

    public Bou_RecyclerAdapter(Context mContext, List<Object> list, Map<Class<?>, Pair<Integer, Integer>> map) {
        this.mContext = mContext;
        this.list = list;
        this.map = map;
        int i = 0;
        for (Map.Entry<Class<?>, Pair<Integer, Integer>> classPairEntry : map.entrySet()) {
            pList.add(classPairEntry.getValue());
            indexMap.put(classPairEntry.getKey(),i++);
        }
        pList.add(new Pair<Integer, Integer>(R.layout.bou_item_comic_free, BR.bou_free));
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        Object o = list.get(position);
        if (o instanceof Bou_ComicList_Item) {
            int type = ((Bou_ComicList_Item) o).getComicType();
            if (type==2) {
                return pList.size()-1;
            }
        }

        int integer = indexMap.get(o.getClass());
        return integer;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, pList.get(viewType).first, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder holder, int position) {
        View view = holder.binding.getRoot();
        Object o = list.get(position);
        int type = getItemViewType(position);

        if (o instanceof Bou_ComicList_Item) {
            int mType = ((Bou_ComicList_Item) o).getComicType();
            if (mType==1) {
                List<Bou_Comic> comics = ((Bou_ComicList_Item) o).getComics();
                Map<Class<?>, Pair<Integer, Integer>> map = new HashMap<>();
                map.put(Bou_Comic.class,new Pair<Integer, Integer>(R.layout.bou_item_comic_item, BR.bouComic));
                Comic_RecycleAdapter adapter = new Comic_RecycleAdapter<Bou_Comic>(mContext, comics, map);
                holder.binding.setVariable(BR.comicsItemAdapter,adapter);
            }
            if(o ==null){
                ViewGroup.LayoutParams layoutParams = holder.binding.getRoot().getLayoutParams();

            }
            holder.binding.setVariable(pList.get(type).second, ((Bou_ComicList_Item) o));
        } else if(o instanceof BouItemSecond){
            holder.binding.setVariable(pList.get(type).second, ((BouItemSecond) o));
        } else if (o instanceof Bou_ReturnData) {

            pager = ((ViewPager) view.findViewById(R.id.bou_gallery_pager));
            if (pager.getAdapter() == null) {
                 galleryItems = ((Bou_ReturnData) o).getGalleryItems();
                GalleryPagerAdapter galleryPagerAdapter = new GalleryPagerAdapter(galleryItems, mContext);
                holder.binding.setVariable(pList.get(type).second, galleryPagerAdapter);

                img = ((ImageView) view.findViewById(R.id.bou_gallery_img));
                layout = (LinearLayout) view.findViewById(R.id.bou_gallery_lin);
                 size = galleryItems.size();
                for (int i = 0; i < size; i++) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setImageResource(R.drawable.gray_point);
                    int dimension = (int) mContext.getResources().getDimension(R.dimen.my_margin);
                    imageView.setPadding(dimension,dimension,dimension,dimension);
                    layout.addView(imageView);
                }
                img.post(new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.setTranslationX(img,layout.getLeft());
                    }
                });
                handler.removeMessages(0);
                pager.addOnPageChangeListener(this);
                handler.sendEmptyMessage(0);
                pager.setCurrentItem(1);
                pager.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                handler.removeMessages(0);
                                break;
                            case MotionEvent.ACTION_UP:
                                handler.sendEmptyMessageDelayed(0,2000);
                                break;
                        }
                        return false;
                    }
                });
                pager.setOnClickListener(this);

            }
        }
    }

    public void add(int index , Object o){
        list.add(index,o);
        notifyDataSetChanged();

    }
    public void addAll(Collection collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }
    public void removeAll(){
        notifyItemRangeRemoved(0,list.size());
        list.clear();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        float value = layout.getLeft() + (position - 1 + positionOffset) * img.getWidth();
         value = Math.max(layout.getLeft(), value);
        value =Math.min(layout.getRight()-img.getWidth(),value);
        ViewCompat.setTranslationX(img,value);
        currentPosition = position;
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            pager.setCurrentItem(size, false);
        } else if (position == size+1) {
            pager.setCurrentItem(1, false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        int arg1 = msg.arg1;
        switch (msg.what) {
            case 0:
                if (arg1 == 0) {
                    arg1= currentPosition;
                }
                if (arg1>size) {
                    arg1=1 ;
                }
                pager.setCurrentItem(arg1,arg1!=1);
                Message message = handler.obtainMessage(0, ++arg1, 0);
                handler.sendMessageDelayed(message,2000);
                break;
        }
        return true;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bou_gallery_pager:
                int currentItem = ((ViewPager) v).getCurrentItem();
                Bout_GalleryItem bout_galleryItem = galleryItems.get(currentItem - 1);
                Class<?> clazz = null;
                Intent intent = null;
                Map<String,String> map = new HashMap<>();
                switch (bout_galleryItem.getLinkType()) {
                    case 1:
                    case 2:
                        clazz = WebViewActivity.class;
                        map.clear();
                        map.put("val",bout_galleryItem.getExt().get(0).getVal());
                        break;

                    case 3:
                        clazz = ChapterActivity.class;
                        map.clear();
                        map.put("comicid",bout_galleryItem.getExt().get(0).getVal());
                        break;
                    case 5:
                        jumpOtherActivity(v.getContext(),clazz,map);
                        break;
                }

            }
        }



    public static class BindingViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding binding ;

        public BindingViewHolder( ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public void click(View v){
//        switch (v.getId()) {
//            case R.id.fg_boutique_search:
//                Intent intent = new Intent(v.getContext(), Classify_Activity.class);
//                 v.getContext().startActivity(intent);
//                break;
//
//        }
    }
    public void jumpOtherActivity(Context context ,Class clazz ,Map<String,String> map){
        Intent intent = new Intent(context, ChapterActivity.class);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            intent.putExtra(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        context.startActivity(intent);

    }
}
