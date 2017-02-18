package com.cyclic_order.amarshohor.model;

/**
 * Created by cyclic_order on 1/9/2016.
 */
public class User {

    String username,email,password;

    public  User(String lusername,String lemail,String lpassword)
    {
        username=lusername;
        email=lemail;
        password=lpassword;
    }
    public User(String username,String password) {

        this.username=username;
        this.password=password;
        email="";
    }
}
