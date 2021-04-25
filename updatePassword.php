<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['password']) && isset($_POST['username'])) {
    if ($db->dbConnect()) {
       // if ($db->login("patient", $_POST['username'], $_POST['password'])) {
            if($db->updatePassword('patient',$_POST['username'],$_POST['password'])){
                echo "Success";
            }else echo "Ne radi";
        } else echo "Failed";
    //} else echo "Error: Database connection";
} else echo "All fields are required";
