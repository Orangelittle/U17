package com.orangelittle.u17.fragment.level1;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangelittle.u17.activity.ChapterActivity;
import com.orangelittle.u17.R;
import com.orangelittle.u17.adapter.CollectAdapter;
import com.orangelittle.u17.entries.CollectionBean;
import com.orangelittle.u17.widget.EmptyView;
import com.orangelittle.u17.widget.ItemDecoration;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rb1Fragment extends Fragment {


    public Rb1Fragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerView;
    private EmptyView mEmptyView;
    private Realm mRealm;
    private CollectAdapter mCollectAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rb1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = ((RecyclerView) view.findViewById(R.id.listview));

        mEmptyView = (EmptyView) view.findViewById(R.id.empty_view);

        mRealm = Realm.getDefaultInstance();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final RealmResults<CollectionBean> results = mRealm.where(CollectionBean.class).findAll();
        mCollectAdapter = new CollectAdapter(results,getActivity());
        GridLayoutManager manager=new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new ItemDecoration(25));
        mRecyclerView.setAdapter(mCollectAdapter);
        if (results.size() == 0) {
            mEmptyView.setEmptyText("还没有收藏哦！");
            mEmptyView.setEmptyImage(R.mipmap.user_list_feedback);
        }
        mCollectAdapter.setOnItemClickListener(new CollectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View item, int position, CollectionBean bean) {
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
