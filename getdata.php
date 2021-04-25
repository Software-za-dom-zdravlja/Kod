<?php


$servername = "localhost";
$username = "root";
$password = "";
$database = "projekat";



$conn = new mysqli($servername, $username, $password, $database);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


//$stmt = $conn->prepare("SELECT vrijeme FROM termini");

$stmt2 = $conn->prepare("SELECT patientName,vrijeme FROM termini WHERE status=1 ORDER BY termin_id DESC LIMIT 3");


$array = [];
$arraynew = [];

$stmt2->execute();

foreach ($stmt2->get_result() as $row2)
{
    $array[]=$row2['vrijeme'];
    $arraynew[] = $row2['patientName'];
}
if(empty($array[0])){
    $temp=['vrijeme1'=>"Nema termina",'vrijeme2'=>"Nema termina",'ime'=>"Nema termina",'ime2'=>"Nema termina",'vrijeme3'=>"Nema termina",'ime3'=>"Nema termina"];
    $array2=array();

    array_push($array2,$temp);
    echo json_encode($array2);
}elseif(empty($array[1])){
    $temp=['vrijeme1'=>$array[0],'vrijeme2'=>"Nema termina",'ime'=>$arraynew[0],'ime2'=>"Nema termina",'vrijeme3'=>"Nema termina",'ime3'=>"Nema termina"];
    $array2=array();

    array_push($array2,$temp);
    echo json_encode($array2);
}elseif(empty($array[2])){
    $temp=['vrijeme1'=>$array[0],'vrijeme2'=>$array[1],'ime'=>$arraynew[0],'ime2'=>$arraynew[1],'vrijeme3'=>"Nema termina",'ime3'=>"Nema termina"];
    $array2=array();

    array_push($array2,$temp);
    echo json_encode($array2);
}else{
    $temp=['vrijeme1'=>$array[0],'vrijeme2'=>$array[1],'vrijeme3'=>$array[2],'ime'=>$arraynew[0],'ime2'=>$arraynew[1],'ime3'=>$arraynew[2]];
    $array2=array();

    array_push($array2,$temp);
    echo json_encode($array2);
}



