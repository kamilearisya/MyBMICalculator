<?php

$host='localhost';
$username='root';
$pwd='';
$db="nutdb";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
  echo "Failed to Connect to Database ".mysqli_connect_error();
}

$name=$_POST['Query'];
$id=$_POST['Query1'];

$sql="SELECT * FROM type_food WHERE jenis_darah='$name' and categori='$id'";

$query=mysqli_query($con,$sql);

if($query)
{
    while($row=mysqli_fetch_array($query))
  {
    $data[]=$row;
  }

    print(json_encode($data));

}else
{
  echo('Not Found ');
}
mysqli_close($con);

?>