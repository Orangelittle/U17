package com.orangelittle.u17.fragment.level1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.SpecialTopicRecyAdapter;
import com.orangelittle.u17.entries.level1.SpecialTopicBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialTopicFragment extends Fragment implements Callback<SpecialTopicBean> {


    private RecyclerView specialTopicRecy;
    private int page = 1;
    private SpecialTopicRecyAdapter adapter;
    private LinearLayoutManager layoutManager;
    private boolean isLoading;

    public SpecialTopicFragment() {
        // Required empty public constructor
    }

    public static SpecialTopicFragment newInstance(String argCon) {

        Bundle args = new Bundle();
        args.putString("argCon", argCon);
        SpecialTopicFragment fragment = new SpecialTopicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_special_topic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        final String argCon = arguments.getString("argCon");
        specialTopicRecy = (RecyclerView) view.findViewById(R.id.special_topic_recy);
        adapter = new SpecialTopicRecyAdapter(getContext());
        specialTopicRecy.setAdapter(adapter);
        NetUtils.getData(ClassType.SpecialTopicBean, this, argCon, page);
        layoutManager = ((LinearLayoutManager) specialTopicRecy.getLayoutManager());
        specialTopicRecy.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int itemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition >= itemCount - 5 && dy > 0) {
                    if (isLoading) {

                    } else {

                        NetUtils.getData(ClassType.SpecialTopicBean, new Callback<SpecialTopicBean>() {
                            @Override
                            public void onResponse(Call<SpecialTopicBean> call, Response<SpecialTopicBean> response) {
                                List<SpecialTopicBean.DataBean.ReturnDataBean.ComicsBean> beanList = response.body().getData().getReturnData().getComics();
                                adapter.addAll(beanList);
                                isLoading = false;
                            }

                            @Override
                            public void onFailure(Call<SpecialTopicBean> call, Throwable t) {
                                Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
                            }
                        }, argCon,++page);
                        isLoading = true;
                    }
                }
            }
        });

        /*
        *
        *
        *  jmprecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 5 && dy > 0) {
                    if (isLoadingMore) {

                    } else {
                        NetUtils.getData(ClassType.Jump_From_SearchBean, new Callback<Jump_From_SearchBean>() {
                            @Override
                            public void onResponse(Call<Jump_From_SearchBean> call, Response<Jump_From_SearchBean> response) {
                                searchBean = response.body();
                                List<Jump_From_SearchBean.DataBean.ReturnDataBean.ComicsBean> comics = searchBean.getData().getReturnData().getComics();
                                adapter.addAll(comics);
                                isLoadingMore = false;
                            }

                            @Override
                            public void onFailure(Call<Jump_From_SearchBean> call, Throwable t) {
                                Toast.makeText(JumpFromSearch.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                            }
                        }, value, argName, con, ++page);
                        isLoadingMore = true;
                    }
                }
            }
        });
        *
        *
        *
        * */


    }

    @Override
    public void onResponse(Call<SpecialTopicBean> call, Response<SpecialTopicBean> response) {
        List<SpecialTopicBean.DataBean.ReturnDataBean.ComicsBean> beanList = response.body().getData().getReturnData().getComics();
        adapter.addAll(beanList);
    }

    @Override
    public void onFailure(Call<SpecialTopicBean> call, Throwable t) {
        Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
    }
}
