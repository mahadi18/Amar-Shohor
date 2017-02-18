package com.cyclic_order.amarshohor.RegistrationActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cyclic_order.amarshohor.MapsActivity.MapsActivity;
import com.cyclic_order.amarshohor.R;

import java.util.HashMap;

/**
 * Created by cyclic_order on 1/12/2016.
 */
public class Activity_login extends ActionBarActivity implements View.OnClickListener {
    EditText editText_username, editText_password;
    public final String serverUrl = "http://192.168.137.1:8080/login.php";
    //public final String serverUrl = "http://192.168.43.74:8080/login.php";
    TextView tv_registration;
    TextView tv_reg_suggst;
    Button btn_login;
    public String entered_username;
    Resources resources;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String entered_password;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String KEY_USERNAME="username";
    public static  final String KEY_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_username = (EditText) findViewById(R.id.et_loginusername);
        editText_password = (EditText) findViewById(R.id.et_loginPassword);
        tv_registration = (TextView) findViewById(R.id.registration);
        btn_login = (Button) findViewById(R.id.bt_login);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        tv_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_login.this, Activity_Registration.class));
            }
        });
        btn_login.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {

        if(v == btn_login){
//           editText_username.setText("");
//           editText_password.setText("");
            login();
        }
    }
    private void login(){
        String username = editText_username.getText().toString().trim();
        String password = editText_password.getText().toString().trim();
        InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        userLogin(username, password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activity_login.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USERNAME, username);
                    editor.putString(KEY_PASSWORD, password);
                    editor.commit();
                    Intent intent = new Intent(Activity_login.this,MapsActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Activity_login.this,s,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterHandler rhc = new RegisterHandler();

                String result = rhc.sendPostRequest(serverUrl,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }


        }



