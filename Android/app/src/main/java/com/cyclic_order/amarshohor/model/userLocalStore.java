package com.cyclic_order.amarshohor.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cyclic_order on 1/9/2016.
 */
public class userLocalStore {
    public  static  final String SP_name="userdetails";
    SharedPreferences userdatastore;
    public  void storeUserdata(Context context)
    {
        userdatastore=context.getSharedPreferences("Sp_name",0);
    }
    public  void storeUserData(User user)
    {
        SharedPreferences.Editor SP_edit=userdatastore.edit();
        SP_edit.putString("username",user.username);
        SP_edit.putString("password",user.password);
        SP_edit.commit();
    }
    public User getLoggedinUser()
    {
        String username=userdatastore.getString("username","");
        String password =userdatastore.getString("password","");
        User storeduser=new User(username,password);
        return  storeduser;
    }
    public void setUserLoggedIn(boolean loggedIn)
    {
        SharedPreferences.Editor SP_edit=userdatastore.edit();
        SP_edit.putBoolean("loggedIn",loggedIn);
        SP_edit.commit();

    }
//    public  boolean getUserLoggedIn()
//    {
//        SharedPreferences.Editor SP_edit=userdatastore.edit();
//        if(userdatastore.getBoolean("loggedIn",false)==true)
//        {
//            return  true;
//
//        }
//        else
//        {
//            return  false;
//        }

    //}
    public void clearUserDetails()
    {
        SharedPreferences.Editor SP_edit=userdatastore.edit();
        SP_edit.clear();
        SP_edit.commit();
    }


}
