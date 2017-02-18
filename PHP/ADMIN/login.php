<?php

$user=$_POST['username'];
$pass=$_POST['password'];
if($user=="admin"&&$pass=="admin")
{
	header('Location:data.php');
}
else if($user!="admin"||$pass!="admin")
{
	header('Location:index.php');
}

?>