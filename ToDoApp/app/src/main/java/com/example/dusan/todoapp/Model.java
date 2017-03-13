package com.example.dusan.todoapp;


import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Model {

  private final String STORE_NAME = "ToDoApp";
  private List<String> mToDoList = new ArrayList<>();
  private Context mContext;


  public Model(Context context)
  {
    this.mContext = context;
  }

  public List<String> getToDoList() {
    return mToDoList;
  }

  public void setToDoList(List<String> mToDoList) {
    this.mToDoList = mToDoList;
  }

  public void addItemToList(String item) {
    mToDoList.add(item);
  }

  public void saveToSharedPreferences()
  {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    Gson gson = new Gson();
    String json = gson.toJson(mToDoList);

    editor.putString(STORE_NAME, json);
    editor.apply();
  }

  public List<String> loadFromSharedPreferences()
  {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
    String json = sharedPreferences.getString(STORE_NAME, "");
    Gson gson = new Gson();
    Type type = new TypeToken<List<String>>() {}.getType();
    mToDoList = gson.fromJson(json, type);
    if(mToDoList == null)
      mToDoList = new ArrayList<>();
    return mToDoList;
  }
}
