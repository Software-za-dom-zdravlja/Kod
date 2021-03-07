<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $name, $password)
    {
        $name = $this->prepareData($name);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where ime = '" . $name . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['ime'];
            $dbpassword = $row['password'];
            if ($dbusername == $name || password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $name,$surname,$password,$kontakt_broj,$email)
    {
        $name = $this->prepareData($name);
        $surname = $this->prepareData($surname);
        $password = $this->prepareData($password);
        $kontakt_broj=$this->prepareData($kontakt_broj);
        $email=$this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (ime,prezime,kontakt_broj,password,email) VALUES ('" . $name . "','".$surname."','" . $kontakt_broj ."','". $password. "','".$email ."')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
    function dodatneInfo($table, $datum, $doktor)
    {
        $datum = $this->prepareData($datum);
        $doktor = $this->prepareData($doktor);
        $this->sql =
            "INSERT INTO " . $table . " (datum,doktor) VALUES ('" . $datum . "','" . $doktor ."')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>