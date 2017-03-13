package com.example.dusan.todoapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

  @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;
  @BindView(R.id.btn_add)
  FloatingActionButton mButtonAdd;

  private LinearLayoutManager mLayoutManager;
  private List<String> mItemList = new ArrayList<>();
  private ListViewAdapter mListViewAdapter;

  int i = 0;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.list_fragment, container, false);
    ButterKnife.bind(this, view);

    mLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLayoutManager);

    mListViewAdapter = new ListViewAdapter(mItemList);
    mRecyclerView.setAdapter(mListViewAdapter);

    return view;
  }

  @OnClick(R.id.btn_add)
  public void AddItem()
  {
    mItemList.add("item " + i);
    i++;
    mListViewAdapter.notifyDataSetChanged();
  }
}
