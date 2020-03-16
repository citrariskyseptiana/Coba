<?php

include("config.php");



$sql = "SELECT * FROM mahasiswa";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('nama_mhs' => $row['nama_mhs'],
    'no_mhs' => $row['no_mhs'], 
    'alamat_mhs' => $row['alamat_mhs']
));
}
echo json_encode(array("result" => $result));
?>