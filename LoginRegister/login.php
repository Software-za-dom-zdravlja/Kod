<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("patient", $_POST['name'], $_POST['password'])) {
            echo "Login Success";
        } else echo "Name or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>