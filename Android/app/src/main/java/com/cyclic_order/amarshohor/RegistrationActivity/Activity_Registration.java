package com.cyclic_order.amarshohor.RegistrationActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyclic_order.amarshohor.R;

import java.util.HashMap;

/**
 * Created by cyclic_order on 2/12/2016.
 */
public class Activity_Registration extends ActionBarActivity  implements View.OnClickListener{
    EditText editText_name,editText_username,editText_email,editText_password;
    Button btn_register,btn_exit;
    public  String entered_username;
    Resources resources;
    public final String serverUrl = "http://192.168.137.1:8080/register.php";
   //public final String serverUrl = "http://192.168.43.74:8080/register.php";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText_name = (EditText) findViewById(R.id.et_name);
        editText_username = (EditText) findViewById(R.id.et_username);
        editText_email = (EditText) findViewById(R.id.et_email);
        editText_password = (EditText) findViewById(R.id.et_Password);
        btn_exit = (Button) findViewById(R.id.bt_cancel);
        btn_register = (Button) findViewById(R.id.bt_register);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_register.setOnClickListener(this);
    }

    @Override

    public void onClick(View v) {
        if(v == btn_register){
            registerUser();
        }
    }

    private void registerUser() {
        String name = editText_name.getText().toString().trim().toLowerCase();
        String username = editText_username.getText().toString().trim().toLowerCase();
        String email = editText_email.getText().toString().trim().toLowerCase();
        String password = editText_password.getText().toString().trim().toLowerCase();
        if (name.length()>0 && username.length()>0 && email.contains("@") && password.length()<=12){

            register(name, username, email, password);
        } else if (name.length()==0){
            Toast.makeText(this,"Name is required",Toast.LENGTH_LONG).show();

        } else if (username.length()==0){
            Toast.makeText(this,"username is required",Toast.LENGTH_LONG).show();
        } else if (!email.contains("@")){
            Toast.makeText(this,"invalid email",Toast.LENGTH_LONG).show();
        } else if (password.length()>12){
            Toast.makeText(this,"Password must be less than 13 ",Toast.LENGTH_LONG).show();
        }

    }

    private void register(String name, String username,String email, String password) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterHandler rhc = new RegisterHandler();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Activity_Registration.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();

                    startActivity(new Intent(Activity_Registration.this, Activity_login.class));
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("email",params[2]);
                data.put("password",params[3]);

                String result = rhc.sendPostRequest(serverUrl,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name, username,email, password);
    }
}
