package com.example.hotumit.tommvpstructure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.hotumit.tommvpstructure.main_activity.MainActivity;
import com.example.hotumit.tommvpstructure.model.UserInfo;
import com.example.hotumit.tommvpstructure.utils.Constants;
import com.facebook.AccessToken;
import com.facebook.login.widget.LoginButton;


public class LoginActivity  {


   /* private FBSignInPresenter signInFBPresenter;
    private Button nextButton;
    private Button goToProfile;
    private UserInfo userModelSingelton;
    private Button vkButton;
    //private String tokenToFaceBook;
    //public static boolean activityLive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FB
        signInFBPresenter = new FBSignIn(this);
        signInFBPresenter.initSignInFB();

        userModelSingelton = UserInfo.getInstance();
        Log.e("startProfileActivity()","startProfileActivity()"+UserInfo.getInstance());
        userModelSingelton.readCash(this);
         switch (userModelSingelton.getLogin_status()){
             case Constants.LOGIN_IN:

                 startProfileActivity();
                 break;
             case Constants.IN_GALLERY:

         }
        setContentView(R.layout.activity_login);

        LoginButton buttonFB = (LoginButton) findViewById(R.id.facebook_button);
        buttonFB.setReadPermissions("email", "public_profile","user_birthday", "user_photos");
       // buttonFB.sr
        buttonFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInFBPresenter.logIn ();
            }
        });



      *//*  //FB photo gallery
        nextButton = (Button) findViewById( R.id.nextButton );
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInFBPresenter.logIn (FBSignIn.GO.GALLERY);
            }
        });*//*
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        Log.e("accessToken","accessToken"+accessToken);
        return accessToken != null;
    }

    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        signInFBPresenter.onActivityResult( requestCode, resultCode, data);

    }




    @Override
    public void startProfileActivity() {
        Intent goToProfile = new Intent(LoginActivity.this, MainActivity.class);
               *//* if (tokenToFaceBook != null)
                 goToGallery.putExtra("token_face_book", tokenToFaceBook);*//*
        startActivity(goToProfile);
        finish();
    }




    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }


    @Override
    public void showToast(String mssg) {
        Toast.makeText(this, mssg ,
                Toast.LENGTH_SHORT).show();
    }





    @Override
    public void onBackPressed(){
       // moveTaskToBack(true);
       // super.onBackPressed();
       // finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activityLive = false;
        signInFBPresenter.onDestroy();
    }*/
}
