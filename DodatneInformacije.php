<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['datum']) && isset($_POST['doktor']) && isset($_POST['username'])) {
    if ($db->dbConnect()) {
        if ($db->dodatneInfo("patient", $_POST['datum'], $_POST['doktor'],$_POST['username'])) {
            echo "Success";
        } else echo "Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
