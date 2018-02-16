package com.example.hotumit.tommvpstructure.main_activity;


import com.example.hotumit.tommvpstructure.model.Notice;
import com.example.hotumit.tommvpstructure.model.dao.PhotoItemDao;

public interface RecyclerItemClickListener {
    void onItemClick(PhotoItemDao notice);
}