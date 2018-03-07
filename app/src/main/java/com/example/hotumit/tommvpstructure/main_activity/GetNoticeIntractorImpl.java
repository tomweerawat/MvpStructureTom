package com.example.hotumit.tommvpstructure.main_activity;

import android.util.Log;


import com.example.hotumit.tommvpstructure.model.NoticeList;
import com.example.hotumit.tommvpstructure.model.dao.PhotoItemCollectionDao;
import com.example.hotumit.tommvpstructure.model.dao.PhotoListManager;
import com.example.hotumit.tommvpstructure.my_interface.GetNoticeDataService;
import com.example.hotumit.tommvpstructure.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bpn on 12/7/17.
 */

public class GetNoticeIntractorImpl implements MainContract.GetNoticeIntractor {
    PhotoItemCollectionDao photoItemCollectionDao;
    int maxId;
    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<PhotoItemCollectionDao> call = service.loadPhotoList();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<PhotoItemCollectionDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {
                onFinishedListener.onFinished(response.body().getData());




            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

    @Override
    public void getNoticeArrayListNewer(final OnFinishedListener onFinishedListener) {

        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);
        photoItemCollectionDao = new PhotoItemCollectionDao();

        maxId = photoItemCollectionDao.getMaximumId();
        Call<PhotoItemCollectionDao> call = service.loadPhotoListAfterId(maxId);


        Log.e("Hello Called", call.request().url() + "");

        call.enqueue(new Callback<PhotoItemCollectionDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {
                PhotoItemCollectionDao dao = response.body().getDao();
                /*photoItemCollectionDao.setDao(dao);*/
               /* photoItemCollectionDao.setDao(dao);
                photoItemCollectionDao.insertDaoTopPosition(dao);*/
                onFinishedListener.onFinishedLoadNewer(response.body().getData());




            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {
                Log.e("MyError",""+t.getMessage())  ;
                onFinishedListener.onFailure(t);
            }
        });

    }


    private void reLoadDataNewer(){
        if(photoItemCollectionDao.getCount()==0){

        }
    }

    private void reLoadData(){

    }

}
