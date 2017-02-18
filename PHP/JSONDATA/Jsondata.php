<?php
define("HOST", "localhost");
define("USER", "root");
define("PASS", "");
define("DB", "user");
$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
$sql="Select * from issue order by id desc";
$res=mysqli_query($con,$sql);
$result=array();

while($row=mysqli_fetch_array($res))
{
	array_push($result,array('id'=>$row[0],'locationname'=>$row[3],'posted_time'=>$row[4],'problem_title'=>$row[5],'name'=>$row[7],'submitted_by'=>$row[8],'issolve'=>$row[10]));

}
echo json_encode(array("Issues"=>$result));
mysqli_close($con);
?>
