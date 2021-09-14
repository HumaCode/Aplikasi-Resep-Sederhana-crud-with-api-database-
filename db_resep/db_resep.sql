-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 14 Sep 2021 pada 02.33
-- Versi server: 10.4.19-MariaDB
-- Versi PHP: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_resep`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_resep`
--

CREATE TABLE `tb_resep` (
  `id_resep` int(11) NOT NULL,
  `nama_resep` varchar(125) NOT NULL,
  `detail` text NOT NULL,
  `gambar` varchar(125) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_resep`
--

INSERT INTO `tb_resep` (`id_resep`, `nama_resep`, `detail`, `gambar`) VALUES
(1, 'Kechup', 'kechup', 'https://asset-a.grid.id//crop/0x0:0x0/700x465/photo/2019/06/21/4052165102.jpg'),
(4, 'Nasi Padang Asli', 'Nasi padang asli dan enak', 'https://awsimages.detik.net.id/community/media/visual/2019/04/24/de2758a6-ea38-4ae9-8c4b-f2b395a81a22_43.png'),
(5, 'pisang goreng', 'pisang goreng keju', 'pisang. jpg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `nama` varchar(125) NOT NULL,
  `jk` varchar(50) NOT NULL,
  `image` text NOT NULL,
  `email` varchar(150) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id`, `nama`, `jk`, `image`, `email`, `username`, `password`) VALUES
(1, 'Humaidi Zakaria', 'Laki-laki', 'saya.jpg', 'huma@gmail.com', 'Huma', '1234'),
(5, 'Amir', 'Laki-laki', 'saya.jpg', 'amir@gmail.com', 'amir', '1234');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_resep`
--
ALTER TABLE `tb_resep`
  ADD PRIMARY KEY (`id_resep`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_resep`
--
ALTER TABLE `tb_resep`
  MODIFY `id_resep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
