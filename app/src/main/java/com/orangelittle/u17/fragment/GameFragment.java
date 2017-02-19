package com.orangelittle.u17.fragment;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.GameAdapter;
import com.orangelittle.u17.entries.GameBean;
import com.orangelittle.u17.util.ClassType;
import com.orangelittle.u17.util.NetUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameFragment extends Fragment implements Callback<GameBean> {


    private RecyclerView mRecyclerView;
    public static GameAdapter mGameAdapter;
    private int pager;

    public GameFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.game_rv);
        mGameAdapter = new GameAdapter(getContext(), this);
        mRecyclerView.setAdapter(mGameAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pager = 1;
        NetUtils.getData(ClassType.GameBean, this, pager++);
    }

    @Override
    public void onResponse(Call<GameBean> call, Response<GameBean> response) {
        GameBean.DataBean.ReturnDataBean returnData = response.body().getData().getReturnData();
        List<Object> list = new ArrayList<>();
        if (pager == 2) {
            list.add(returnData.getGameheader().getBanner());
            list.add(returnData.getGameheader().getRecommands());
            list.addAll(returnData.getItemList());
            mGameAdapter.setList(list);
        }else {
            list.addAll(returnData.getItemList());
            mGameAdapter.addAll(list);
        }
        if (!returnData.isHasMore()) {
            pager = -1;
        }

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration()

                                        {
                                            @Override
                                            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                                            }

                                            @Override
                                            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                                                c.drawColor(getResources().getColor(R.color.gray));
                                            }

                                            @Override
                                            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
                                                    state) {
                                                if (parent.getChildAdapterPosition(view) != 0) {
                                                    outRect.set(0, 1, 0, 0);
                                                }
                                            }
                                        }

        );
    }

    @Override
    public void onFailure(Call<GameBean> call, Throwable t) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mGameAdapter.stopHandler();
    }

    public void getData() {
        if (pager == -1) {
            //没有更多
        } else {
            NetUtils.getData(ClassType.GameBean, this, pager++);
        }
    }
}
