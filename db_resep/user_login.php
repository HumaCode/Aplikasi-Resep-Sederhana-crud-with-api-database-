<?php 
 include './config/koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
   $email = $_POST['email'];
   $password = $_POST['password'];
  
   //ambil data dari database
    $data = mysqli_query($con, "select * from tb_user where email = '$email' and password = '$password'");

    $cek = mysqli_num_rows($data);

    $response=[];

    if($cek > 0 ){
      $response['pesan']="Berhasil Login";
      $response['success']= "true";
      echo json_encode($response);
    }else{
      $response['pesan']="Gagal Login";
      $response['success']= "false";
      echo json_encode($response);
    }
  }

 ?>
