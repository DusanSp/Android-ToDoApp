package com.example.dusan.todoapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


public class ListViewAdapter extends RecyclerView.Adapter<ListViewHolder> {

  private List<String> mItemList;


  public ListViewAdapter(List<String> list)
  {
    this.mItemList = list;
  }

  @Override
  public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_row, parent, false);

    return new ListViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(ListViewHolder holder, int position) {
    Log.d("onBindViewHolder", "position: " + position);
    String item = mItemList.get(position);
    holder.mReminderText.setText(item);
  }

  @Override
  public int getItemCount() {

    return mItemList.size();
  }
}
