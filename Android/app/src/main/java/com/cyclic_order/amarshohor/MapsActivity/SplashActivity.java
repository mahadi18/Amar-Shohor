package com.cyclic_order.amarshohor.MapsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cyclic_order.amarshohor.RegistrationActivity.Activity_login;

/**
 * Created by cyclic_order on 2/1/2016.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(SplashActivity.this,Activity_login.class);
        startActivity(intent);
        finish();
    }
}
