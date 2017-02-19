package com.orangelittle.u17.fragment.level1;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.RankItemAdapter;
import com.orangelittle.u17.entries.level1.RankListBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankItemFragment extends Fragment implements Callback<RankListBean> {


    private RecyclerView rank_item_recy;
    private RankItemAdapter adapter;
    private int page=1;
    private boolean isLoading;
    private LinearLayoutManager layoutManager;

    public RankItemFragment() {
        // Required empty public constructor
    }

    public static RankItemFragment newInstance(String argValue,String argName) {

        Bundle args = new Bundle();
        args.putString("argValue", argValue);
        args.putString("argName", argName);
        RankItemFragment fragment = new RankItemFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_rank_item, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        final String argValue = arguments.getString("argValue");
        final String argName = arguments.getString("argName");
        rank_item_recy = ((RecyclerView) view.findViewById(R.id.rank_item_recy));
        adapter = new RankItemAdapter(getContext());
        rank_item_recy.setAdapter(adapter);
        layoutManager = ((LinearLayoutManager) rank_item_recy.getLayoutManager());

        rank_item_recy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int itemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition >= itemCount - 5 && dy > 0) {
                    if (isLoading) {

                    } else {

                        NetUtils.getData(ClassType.RankListBean, new Callback<RankListBean>() {
                            @Override
                            public void onResponse(Call<RankListBean> call, Response<RankListBean> response) {
                                List<RankListBean.DataBean.ReturnDataBean.ComicsBean> list = response.body().getData().getReturnData().getComics();
                                Toast.makeText(getContext(), "数据加载中", Toast.LENGTH_SHORT).show();
                                adapter.addAll(list);
                                isLoading = false;
                            }

                            @Override
                            public void onFailure(Call<RankListBean> call, Throwable t) {
                                Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
                            }
                        }, Integer.parseInt(argValue),argName,++page);
                        isLoading = true;
                    }
                }
            }
        });
        NetUtils.getData(ClassType.RankListBean,this,Integer.parseInt(argValue),argName,page);

    }


    @Override
    public void onResponse(Call<RankListBean> call, Response<RankListBean> response) {
        List<RankListBean.DataBean.ReturnDataBean.ComicsBean> list = response.body().getData().getReturnData().getComics();
        adapter.addAll(list);


    }

    @Override
    public void onFailure(Call<RankListBean> call, Throwable t) {
        Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}
