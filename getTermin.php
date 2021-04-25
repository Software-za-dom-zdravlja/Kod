<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "projekat";
$conn = new mysqli($servername, $username, $password, $database);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$stmt = $conn->prepare("SELECT * FROM termini ORDER BY termin_id DESC LIMIT 1");
$array = [];
$stmt->execute();
foreach ($stmt->get_result() as $row)
{
    $array[]=$row['status'];
}


    if(empty($array[0])){
        $temp=['termin'=>"22"];
        $array2=array();
        array_push($array2,$temp);
        echo json_encode($array2);
    }else {
        $temp=['termin'=>$array[0]];
        $array2=array();
        array_push($array2, $temp);
        echo json_encode($array2);
    }



