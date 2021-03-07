<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("patient", $_POST['name'],$_POST['surname'], $_POST['password'],$_POST['kontakt_broj'],$_POST["email"])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
