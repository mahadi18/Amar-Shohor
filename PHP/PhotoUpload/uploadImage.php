<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$image = $_POST['image'];
         $name = $_POST['name'];
         $latitude=$_POST['latitude'];
         $longitude=$_POST['longitude'];
         $posted_time=$_POST['posted_time'];
         $problem_title=$_POST['problem_title'];
         $submitted_by=$_POST['submitted_by'];
         $locationname=$_POST['locationname'];

         

		
		require_once('configuration.php');
		
		$sql ="SELECT * FROM issue ";
		
		$res = mysqli_query($con,$sql);
		
		$id = 0;
		
		while($row = mysqli_fetch_array($res)){
				$id = $row['id'];
		}
		
		$path = "uploads/$id.png";
		
		$actualpath = "http://192.168.137.1:8080/PhotoUpload/$path";
		$phpaccess = "../PhotoUpload/$path";
		
		$sql = "INSERT INTO issue (latitude,longitude,locationname,posted_time,problem_title,image,name,submitted_by,phpimage) VALUES ('$latitude','$longitude','$locationname','$posted_time','$problem_title','$actualpath','$name','$submitted_by','$phpaccess')";
		
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			echo "Successfully Uploaded";
		}
		else
		{
			echo "Sorry Something went wrong";
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}