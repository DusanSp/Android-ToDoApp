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
  private Context mContext;


  public Model(Context context)
  {
    this.mContext = context;
  }

  public void addNewReminder(String newReminder)
  {
    List<String> savedList = loadFromSharedPreferences();
    savedList.add(newReminder);
    saveToSharedPreferences(savedList);
  }

  public List<String> getReminderList()
  {
    return loadFromSharedPreferences();
  }

  public void removeItemFromList(int position)
  {
    List<String> savedList = loadFromSharedPreferences();
    String item = savedList.get(position);
    if(savedList.contains(item))
    {
      savedList.remove(position);
      saveToSharedPreferences(savedList);
    }
  }

  private void saveToSharedPreferences(List<String> saveList)
  {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    Gson gson = new Gson();
    String json = gson.toJson(saveList);

    editor.putString(STORE_NAME, json);
    editor.apply();
  }

  private List<String> loadFromSharedPreferences()
  {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
    String json = sharedPreferences.getString(STORE_NAME, "");
    Gson gson = new Gson();
    Type type = new TypeToken<List<String>>() {}.getType();

    List<String> list;
    list = gson.fromJson(json, type);
    if(list == null)
      list = new ArrayList<>();
    return list;
  }
}
