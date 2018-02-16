package com.example.hotumit.tommvpstructure.model.dao;

import android.content.Context;

import com.example.hotumit.tommvpstructure.utils.Contextor;

import java.util.ArrayList;

/**
 * Created by HOTUM IT on 15/2/2561.
 */

public class PhotoListManager {
    private PhotoItemCollectionDao dao;
    private Context context;

    public PhotoListManager() {
        context = Contextor.getInstance().getContext();
    }

    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void insertDaoTopPosition(PhotoItemCollectionDao newDao){
        if(dao==null){
            dao = new PhotoItemCollectionDao();
        }
        if (dao.getData()==null){
            dao.setData(new ArrayList<PhotoItemDao>());
        }
        dao.getData().addAll(0,newDao.getData());
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }
    public int getMaximumId(){
        if (dao==null){
            return 0;
        }
        if (dao.getData()==null) {
            return 0;
        }
        if (dao.getData().size()==0){
            return 0;
        }
        int maxId = dao.getData().get(0).getId();
        for (int i =0; i<dao.getData().size();i++)
            maxId = Math.max(maxId,dao.getData().get(0).getId());
        return maxId;
    }
    public int getCount(){
        if (dao==null)
            return 0;

        if (dao.getData()==null)
            return 0;
        return dao.getData().size();

    }

}
