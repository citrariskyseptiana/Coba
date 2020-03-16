<?php

include("config.php");

$isi_tagline = $_POST['isi_tagline'];
 
$sql = "UPDATE tagline SET isi_tagline='$isi_tagline' WHERE id_tagline=2";
$query = mysqli_query($db, $sql);

// apakah query update berhasil?
if( $query ) {

} else {
	die("Gagal menyimpan perubahan . . .");
}

?>