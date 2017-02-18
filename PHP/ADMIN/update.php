
<?php
define("HOST", "localhost");
define("USER", "root");
define("PASS", "");
define("DB", "user");
$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$id = $_POST['issolve'];
$sql="update issue set issolve='1' where id='$id'";
mysqli_query($con,$sql);
header('Location: data.php');

?>