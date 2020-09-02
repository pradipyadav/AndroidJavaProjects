package com.sony.facebookloginex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    TextView info;
    ImageView profile;
    LoginButton login;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = findViewById(R.id.info);
        profile = findViewById(R.id.profile);
        login = findViewById(R.id.login);

        callbackManager = CallbackManager.Factory.create();

        login.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        info.setText("User ID: " + loginResult.getAccessToken().getUserId());
                        String imageUrl="https://graph.facebook.com/"+ loginResult.getAccessToken().getUserId()+"/picture?return_ssl_resources=1";
                        Glide
                                .with(MainActivity.this)
                                .load(imageUrl)
                                .centerInside()
                                .into(profile);
                    }

                    @Override
                    public void onCancel() {
                        info.setText("Login attempt canceled.");
                    }

                    @Override
                    public void onError(FacebookException e) {
                        info.setText("Login attempt failed.");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
