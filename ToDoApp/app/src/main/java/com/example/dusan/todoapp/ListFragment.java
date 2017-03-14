package com.example.dusan.todoapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
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
  private Model mModel;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.list_fragment, container, false);
    ButterKnife.bind(this, view);

    mLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLayoutManager);

    mListViewAdapter = new ListViewAdapter();
    mRecyclerView.setAdapter(mListViewAdapter);

    mModel = new Model(getActivity());
    mListViewAdapter.updateAdapterData(mModel.getReminderList());

    setUpItemSwipe();

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

  private void setUpItemSwipe()
  {
    ItemTouchHelper.SimpleCallback simpleCallback = new SimpleCallback(0, ItemTouchHelper.LEFT) {
      @Override
      public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
        return false;
      }

      @Override
      public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        return super.getSwipeDirs(recyclerView, viewHolder);
      }

      @Override
      public void onSwiped(ViewHolder viewHolder, int direction) {
        ListViewAdapter listViewAdapter = (ListViewAdapter) mRecyclerView.getAdapter();
        int swipedPosition = viewHolder.getAdapterPosition();
        boolean removed = listViewAdapter.removeListItem(swipedPosition);
        if(removed)
        {
          mModel.removeItemFromList(swipedPosition);
        }
      }
    };
    ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleCallback);
    mItemTouchHelper.attachToRecyclerView(mRecyclerView);
  }
}
