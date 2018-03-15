package com.example.hotumit.tommvpstructure.main_activity.moreinfo_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hotumit.tommvpstructure.R;
import com.example.hotumit.tommvpstructure.adapter.ViewPagerAdapter;
import com.example.hotumit.tommvpstructure.model.dao.PhotoItemDao;

import java.util.ArrayList;

/**
 * Created by HOTUM IT on 7/3/2561.
 */

public class MoreinfoFragment extends Fragment {
    ViewPager viewPager;
    PhotoItemDao dao;
    private ArrayList<PhotoItemDao> dataList;
    ImageView img;


    public MoreinfoFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_info, container, false);
        dao = getActivity().getIntent().getParcelableExtra("dao");
        showimg(view);
        return view;
    }

    public static Fragment newInstance(PhotoItemDao dao) {
        MoreinfoFragment fragment = new MoreinfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;

    }

    public void showimg(View view) {
        img = (ImageView) view.findViewById(R.id.img);
        Glide.with(getContext())
                .load(dao.getImageUrl())
                .into(img);

    }


    private void init(View view) {
      /*  Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        *//*img = (ImageView)view.findViewById(R.id.img);*//*
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);*/
        /*viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

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

            @Override
            public CharSequence getPageTitle(int position) {
                *//*return mFragmentTitleList.get(position);*//*
                switch (position) {
                    case 0:
                        return "One";
                    case 1:
                        return "Two";
                    case 2:
                        return "Two";
                    default:
                        return "";
                }

            }
        });
       */


    }


}
