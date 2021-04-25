<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['status']) && isset($_POST['patientName'])) {
    if ($db->dbConnect()) {
        if ($db->status("termini", $_POST['status'], $_POST['patientName'],$_POST['vrijeme'])) {
            echo "Success";
        } else echo "Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
