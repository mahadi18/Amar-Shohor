<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$name = $_POST['name'];
		$username = $_POST['username'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		
		if($name == '' || $username == '' || $email == ''|| $password == '' ){
			echo 'please fill all values';
		}else{
			require_once('configuration.php');
			$sql = "SELECT * FROM user_info WHERE username='$username' OR email='$email'";
			
			$check = mysqli_fetch_array(mysqli_query($con,$sql));
			
			if(isset($check)){
				echo 'username or email already exist';
			}else{				
				$sql = "INSERT INTO user_info (name,username,email,password) VALUES('$name','$username','$email','$password')";
				if(mysqli_query($con,$sql)){
					echo 'successfully registered';
				}else{
					echo 'oops! Please try again!';
				}
			}
			mysqli_close($con);
		}
}else{
echo 'error';
}