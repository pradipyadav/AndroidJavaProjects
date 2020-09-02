package com.sony.androidnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "NotificationID";
    private static final String CHANNEL_NAME = "NotificationName";
    private static final String CHANNEL_DESC = "NotificationDesc";

    EditText email, password;
    ProgressBar progressBar;

    private FirebaseAuth mFireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFireBaseAuth = FirebaseAuth.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);

        findViewById(R.id.btnSignUp).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createUser();
                    }
                }
        );



    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mFireBaseAuth.getCurrentUser()!=null) {
          startProfileActivity();
        }
    }

    private void createUser() {

        final String strEmail = email.getText().toString().trim();
        final String strPassword = password.getText().toString().trim();

        if (strEmail.isEmpty()) {
            email.setError("Email Required");
            email.requestFocus();
            return;
        }
        if (strPassword.isEmpty()) {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }
        if (strPassword.length() < 6) {
            password.setError("Password length should be 6 char long");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mFireBaseAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    startProfileActivity();
                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        userLogin(strEmail, strPassword);
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                );
    }

    private void userLogin(String strEmail, String strPassword) {
        mFireBaseAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    startProfileActivity();
                                }  else {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                            }
                        }
                );
    }

    private void startProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
