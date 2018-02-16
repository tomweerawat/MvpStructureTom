package com.example.hotumit.tommvpstructure.model.dao;

import android.content.Context;
import android.util.Log;

import com.example.hotumit.tommvpstructure.utils.Contextor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOTUM IT on 12/1/2561.
 */

public class PhotoItemCollectionDao {

    PhotoItemCollectionDao dao;
    Context context;

    public PhotoItemCollectionDao() {
        context = Contextor.getInstance().getContext();
    }

    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    public int getMaximumId() {
        if (dao == null) {
            Log.e("aaaa","aaaa");
            return 0;
        }
        if (dao.getData() == null) {
            Log.e("bbbb","bbbb");
            return 0;
        }
        if (dao.getData().size() == 0) {
            Log.e("cccc","cccc");
            return 0;
        }
        int maxId = dao.getData().get(0).getId();
        for (int i = 0; i < dao.getData().size(); i++)
            maxId = Math.max(maxId, dao.getData().get(0).getId());
        return maxId;
    }

    public int getCount() {
        if (dao == null)
            return 0;

        if (dao.getData() == null)
            return 0;
        return dao.getData().size();

    }

    public void insertDaoTopPosition(PhotoItemCollectionDao newDao){
        if(dao==null)
            dao = new PhotoItemCollectionDao();

        if (dao.getData()==null)
            dao.setData(new ArrayList<PhotoItemDao>());

        dao.getData().addAll(0,newDao.getData());
    }

    @Expose
    @SerializedName("success")
    private boolean success;

    @Expose
    @SerializedName("data")
    private ArrayList<PhotoItemDao> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<PhotoItemDao> getData() {
        return data;
    }

    public void setData(ArrayList<PhotoItemDao> data) {
        this.data = data;
    }


}
