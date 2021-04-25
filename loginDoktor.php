<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['kod'])) {
    if ($db->dbConnect()) {
        if ($db->doktorLogin("doktor", $_POST['username'], $_POST['password'],$_POST['kod'])) {
            echo "Login Success";
        } else echo "Name, Password or Code wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>