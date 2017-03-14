package com.example.dusan.todoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public class ListViewAdapter extends RecyclerView.Adapter<ListViewHolder> {

  private List<String> mItemList;

  public ListViewAdapter()
  {
    mItemList = new ArrayList<>();
  }

  @Override
  public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_row, parent, false);

    return new ListViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(ListViewHolder holder, int position) {

    String item = mItemList.get(position);
    holder.mReminderText.setText(item);
  }

  @Override
  public int getItemCount() {
    return mItemList.size();
  }

  public void updateAdapterData(List<String> list)
  {
    if(!list.isEmpty())
    {
      this.mItemList = list;
      notifyDataSetChanged();
    }
  }

  public boolean removeListItem(int position)
  {
    String item = mItemList.get(position);
    if(mItemList.contains(item))
    {
      mItemList.remove(position);
      notifyItemRemoved(position);
      return true;
    }
    else
    {
      return false;
    }
  }
}
