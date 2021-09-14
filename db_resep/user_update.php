	<?php 
 require_once './config/koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	
 //parameter post
    $id = $_POST['id'];
    $nama = $_POST['nama'];
    $jk = $_POST['jk'];
    $image = $_POST['image'];
    $email = $_POST['email'];
    $username = $_POST['username'];
    $password = $_POST['password'];


// update tabel user by id
 	 
 	$query = "UPDATE  tb_user 
 	SET nama = '$nama',
 	jk = '$jk', 
 	image = '$image',
    email = '$email',
    username = '$username'
    password = '$password'
 	 WHERE id ='$id'";

 	$exeQuery = mysqli_query($con, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'data berhasil update')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>
