package com.orangelittle.u17.fragment.level1;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangelittle.u17.activity.ChapterActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.HistoryAdapter;
import com.orangelittle.u17.entries.LookHistoryBean;
import com.orangelittle.u17.widget.EmptyView;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rb2Fragment extends Fragment {


    public Rb2Fragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerView;
    private EmptyView mEmptyView;
    private Realm mRealm;
    private HistoryAdapter mHistoryAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rb2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = ((RecyclerView) view.findViewById(R.id.listview));

        mEmptyView = (EmptyView) view.findViewById(R.id.empty_view);

        mRealm = Realm.getDefaultInstance();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final RealmResults<LookHistoryBean> results = mRealm.where(LookHistoryBean.class).findAll();
        mHistoryAdapter = new HistoryAdapter(results,getActivity());
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 10;
                outRect.top = 10;
                outRect.left = 10;
                outRect.right = 10;
            }
        });
        mRecyclerView.setAdapter(mHistoryAdapter);
        if (results.size() == 0) {
            mEmptyView.setEmptyText("什么都没有哭晕在厕所哦！");
            mEmptyView.setEmptyImage(R.mipmap.user_list_feedback);
        }
        mHistoryAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View item, int position,LookHistoryBean bean) {
                Intent intent = new Intent(getActivity(), ChapterActivity.class);
                intent.putExtra("comicid",results.get(position).getComicid());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mRealm.isClosed()) {
            mRealm.close();
        }
    }

}
