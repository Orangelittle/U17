package com.orangelittle.u17.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.entries.level1.RankListBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/29.
 */

public class Comic_RecycleAdapter<T> extends RecyclerView.Adapter<Comic_RecycleAdapter.BindingViewHolder> {

    private final LayoutInflater inflate;
    private Context context;

    private List<T> list;

    private Map<Class<?>, Pair<Integer, Integer>> map;
    private RecyclerView recyclerView;


    private Map<Class<?>, Integer> indexMap = new HashMap<>();  //类 和 comicsBeanList-i 的对应

    private List<Pair<Integer, Integer>> lvList =new ArrayList<>(); // id 和 br
    private BindingViewHolder myholder;
    private View view;
    private TextView type_authorname;


    public Comic_RecycleAdapter(Context context, List<T> list, Map<Class<?>, Pair<Integer, Integer>> map) {
        this.context = context;
        this.list = list;
        this.map = map;
        int i = 0;

        for (Map.Entry<Class<?>, Pair<Integer, Integer>> pairEntry : map.entrySet()) {
            indexMap.put(pairEntry.getKey(),i++);
            lvList.add(pairEntry.getValue());
        }
        inflate = LayoutInflater.from(context);

    }

    public void add(int index ,T o){
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
    public int getSize(){
        return list.size();
    }




    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewDataBinding binding = DataBindingUtil.inflate(this.inflate, lvList.get(viewType).first, parent, false);
        return new BindingViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        T o = list.get(position);
        if (o instanceof RankListBean.DataBean.ReturnDataBean.ComicsBean) {
            RankListBean.DataBean.ReturnDataBean.ComicsBean bean = (RankListBean.DataBean.ReturnDataBean.ComicsBean) o;

            type_authorname = ((TextView) holder.binding.getRoot().findViewById(R.id.more_recycle_item_type_authorname));
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : bean.getTags()) {
                stringBuilder.append(s+" ");
            }
           stringBuilder.append("| ").append(bean.getAuthor());
            type_authorname.setText(stringBuilder.toString());
        }
        holder.binding.setVariable(lvList.get(getItemViewType( position)).second, ((T) o));
    }


    @Override
    public int getItemCount() {
    return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = list.get(position);
        return indexMap.get(o.getClass());


    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
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




    public static class BindingViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding ;

        public BindingViewHolder( ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
