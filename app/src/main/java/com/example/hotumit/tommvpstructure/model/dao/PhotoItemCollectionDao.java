package com.example.hotumit.tommvpstructure.model.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HOTUM IT on 12/1/2561.
 */

public class PhotoItemCollectionDao {
    @Expose
    @SerializedName("success")
    private boolean success;

    @Expose
    @SerializedName("data")
    private List<PhotoItemDao> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PhotoItemDao> getData() {
        return data;
    }

    public void setData(List<PhotoItemDao> data) {
        this.data = data;
    }
}
