package com.example.dusan.todoapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ListViewHolder extends RecyclerView.ViewHolder {

  public TextView mReminderText;

  public ListViewHolder(View itemView) {
    super(itemView);

    this.mReminderText = (TextView) itemView.findViewById(R.id.reminder_text);
  }
}
