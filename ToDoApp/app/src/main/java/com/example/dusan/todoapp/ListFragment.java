package com.example.dusan.todoapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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


public class ListFragment extends Fragment {

  @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;
  @BindView(R.id.btn_add)
  FloatingActionButton mButtonAdd;

  private LinearLayoutManager mLayoutManager;
  private ListViewAdapter mListViewAdapter;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.list_fragment, container, false);
    ButterKnife.bind(this, view);

    mLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLayoutManager);

    mListViewAdapter = new ListViewAdapter(getActivity());
    mRecyclerView.setAdapter(mListViewAdapter);

    Model mModel = new Model(getActivity());
    mListViewAdapter.updateAdapterData(mModel.loadFromSharedPreferences());

    return view;
  }

  @OnClick(R.id.btn_add)
  public void AddItem()
  {
    AddItemFragment addItemFragment = new AddItemFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_holder, addItemFragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  public void updateListView()
  {
    // mListViewAdapter.updateAdapterData();
  }
}
