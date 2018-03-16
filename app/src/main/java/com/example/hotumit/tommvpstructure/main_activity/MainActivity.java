package com.example.hotumit.tommvpstructure.main_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hotumit.tommvpstructure.MoreinfoActivity;
import com.example.hotumit.tommvpstructure.R;
import com.example.hotumit.tommvpstructure.adapter.NoticeAdapter;
import com.example.hotumit.tommvpstructure.main_activity.moreinfo_fragment.MoreinfoFragment;
import com.example.hotumit.tommvpstructure.model.Notice;
import com.example.hotumit.tommvpstructure.model.dao.PhotoItemDao;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private MainContract.presenter presenter;
    private ImageView img;
    private TextView user_email,user_name;
    private String jsondata;
    JSONObject response,profile_pic_url,profile_pic_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();
        refresh();
        debugHashKey();
        bindview();

        Intent intent = getIntent();
        jsondata = intent.getStringExtra("userProfile");
        Log.e("Jsondata", jsondata);
        getFacebbokData();
        presenter = new MainPresenterImpl(this, new GetNoticeIntractorImpl());
        presenter.requestDataFromServer();


    }

    private void bindview() {
        user_email = (TextView) findViewById(R.id.tvfacebook);
        user_name = (TextView) findViewById(R.id.userfacebook);
        img = (ImageView) findViewById(R.id.imgnav);
    }

    private void getFacebbokData() {
        try {
            response = new JSONObject(jsondata);
            user_name.setText(response.get("name").toString());
            user_email.setText(response.get("email").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.with(this).load(profile_pic_url.getString("url"))
                    .into(img);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void debugHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.hotumit.tommvpstructure",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    private void refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                presenter.onRefreshButtonClick();
            }
        });
    }


    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipLayoutRefresh);
        recyclerView = findViewById(R.id.recycler_view_employee_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);


    }


    /**
     * Initializing progressbar programmatically
     */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open_drawer, R.string.close_drawer) {


        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * RecyclerItem click event listener
     */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(PhotoItemDao notice) {

          /*  Toast.makeText(MainActivity.this,
                    "List title:  " + notice.getCamera(),
                    Toast.LENGTH_LONG).show();*/
            Intent i = new Intent(MainActivity.this, MoreinfoActivity.class);
            i.putExtra("dao", notice);
            startActivity(i);

        }
    };


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setDataToRecyclerView(ArrayList<PhotoItemDao> noticeArrayList) {

        NoticeAdapter adapter = new NoticeAdapter(noticeArrayList, recyclerItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();

        Log.e("Error", "Error" + throwable.getMessage());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            presenter.onRefreshButtonClick();
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

