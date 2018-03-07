package com.example.hotumit.tommvpstructure.main_activity.moreinfo_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hotumit.tommvpstructure.R;
import com.example.hotumit.tommvpstructure.model.dao.PhotoItemDao;

import java.util.ArrayList;

/**
 * Created by HOTUM IT on 7/3/2561.
 */

public class MoreinfoFragment extends AppCompatActivity {
    ViewPager viewPager;
    PhotoItemDao dao;
    private ArrayList<PhotoItemDao> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_info);
         dao = getIntent().getParcelableExtra("dao");
        init();


    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
              /*  Button btn = new Button(container.getContext());
                btn.setText(dao.getUsername()+position);
                container.addView(btn);*/
                ImageView btn = new ImageView(container.getContext());
                Glide.with(container.getContext())
                        .load(dao.getImageUrl())
                        .into(btn);
                container.addView(btn);
                return btn;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
              container.removeView((View) object);
            }
        });
    }
}
