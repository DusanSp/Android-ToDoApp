package com.example.dusan.todoapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddItemFragment extends Fragment {

  @BindView(R.id.edit_text)
  EditText mEditText;

  private Model mModel;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.additem_fragment, container, false);
    ButterKnife.bind(this, view);
    mModel = new Model(getActivity());
    return view;
  }

  @OnClick(R.id.btn_additem)
  public void onClickAdd()
  {
    String text = mEditText.getText().toString();
    mModel.addNewReminder(text);

    showListFragment();
  }

  @OnClick(R.id.btn_close)
  public void onClickClose()
  {
    mEditText.setText("");
    mEditText.clearFocus();

    showListFragment();
  }

  private void showListFragment()
  {
    ListFragment listFragment = new ListFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_holder, listFragment);
    fragmentTransaction.commit();
  }
}
