<?php
define("HOST", "localhost");
define("USER", "root");
define("PASS", "");
define("DB", "user");
$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$sql="Select * from issue order by id DESC";
$res=mysqli_query($con,$sql);
$result=array();
?>

<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Admin site</title>
    
    
    <link rel="stylesheet" href="css/reset.css">

    <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="css/style.css">

    
    
    
  </head>

  <body>

    
<!-- Mixins-->
<!-- Pen Title-->
<div class="pen-title">
  <h1>Problems</h1>
</div>
<?php
    while($row=mysqli_fetch_array($res))
	{
	?>
<div class="container" >
  <div class="card"style="width:700px;"></div>
  <div class="card"style="width:700px;">
  	
	<fieldset style="width:700px; border-radius:5px;background-color:#FFA500;border-bottom:1px solid;">
	<div class="full">
	<div class="pen-title">
		<h1><?php echo $row[5];?></h1>
	</div>
	<p style="margin-left:100px"><?php echo $row[0];?></p>
  <p style="margin-left:100px"><?php echo $row[8];?></p>
    <p style="margin-left:100px"><?php echo $row[4];?></p>

	<img src="<?php echo $row[9];?>" style="width:160px;height:200px;margin-left:100px"/>
      <p style="margin-left:100px"><?php echo $row[3];?></p>

	<p style="margin-left:100px"><?php echo $row[7];?></p>
	

	<?php if($row[10]==0){?>

<form action="update.php" method="post">
	<input type="hidden" value="<?php echo $row[0];?>" name="issolve">
  <div class="button-container">
        <button><span>Solve</span></button>
      </div>
</form>



<?php
}

else
{
	?>
	
	<div class="pen-title">
        <h2>The problem has solved</h2>
      </div>

	<?php
}

?>
</div>
</div>
</div>

</fieldset>
<?php

}
?>
  
  



    
    
    
  </body>
</html>


