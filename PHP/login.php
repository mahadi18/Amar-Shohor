<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $username = $_POST['username'];
 $password = $_POST['password'];
 
 require_once('configuration.php');
 
 $sql = "select * from user_info where username='$username' and password='$password'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 if(isset($check)){
 echo "success";
 }else{
 echo "Invalid Username or Password";
 }
 
 }else{
 echo "error try again";
 }