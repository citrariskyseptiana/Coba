<?php

include("config.php");

$nama_mhs = $_POST['nama_mhs'];
$no_mhs = $_POST['no_mhs'];
$alamat_mhs = $_POST['alamat_mhs'];
 
$sql = "INSERT INTO mahasiswa(nama_mhs, no_mhs, alamat_mhs) values('$nama_mhs', '$no_mhs', '$alamat_mhs')";
$query = mysqli_query($db, $sql);

// apakah query update berhasil?
if( $query ) {

} else {
	die("Gagal menyimpan perubahan . . .");
}

?>