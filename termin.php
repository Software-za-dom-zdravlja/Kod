<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['doctor_id']) && isset($_POST['vrijeme'])) {
    if ($db->dbConnect()) {
        if ($db->termini("termini", $_POST['patient_id'], $_POST['doctor_id'],$_POST['vrijeme'],$_POST['dodatneInfo'])) {
            echo "Success";
        } else echo "Error";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>