package com.example.hotumit.tommvpstructure;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hotumit.tommvpstructure.adapter.ViewPagerAdapter;
import com.example.hotumit.tommvpstructure.main_activity.moreinfo_fragment.MoreinfoFragment;
import com.example.hotumit.tommvpstructure.model.dao.PhotoItemDao;

public class MoreinfoActivity extends AppCompatActivity {

    PhotoItemDao dao;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    final int[] ICONS = new int[]{
            R.drawable.ic_trending_up_black_24dp,
            R.drawable.ic_shopping_cart_black_24dp,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo);
        dao = getIntent().getParcelableExtra("dao");
        initview();
     /*   if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, MoreinfoFragment.newInstance(dao))
                    .commit();
        }*/
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(ICONS[0]);
                tabLayout.getTabAt(1).setIcon(ICONS[1]);
              /*  tabLayout.getTabAt(2).setIcon(tabIcons[2]);*/

            }
        });

    }

    private void initview() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MoreinfoFragment(), "");
        adapter.addFragment(new MoreinfoFragment(), "");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }*/
}
