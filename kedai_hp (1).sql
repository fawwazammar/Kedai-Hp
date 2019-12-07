-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Des 2019 pada 12.35
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kedai_hp`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `handphone`
--

CREATE TABLE `handphone` (
  `kode_hp` varchar(10) NOT NULL,
  `merk_hp` varchar(20) NOT NULL,
  `tipe` varchar(20) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `tahun` int(10) NOT NULL,
  `harga` int(20) NOT NULL,
  `tgl_data` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `handphone`
--

INSERT INTO `handphone` (`kode_hp`, `merk_hp`, `tipe`, `jumlah`, `tahun`, `harga`, `tgl_data`) VALUES
('APA12', 'Apache', '12', 2, 2001, 1699900, '03/12/2019'),
('hhhuhu', 'njnjn', 'hh7', 987888, 2030, 13, '04/12/2019'),
('IP11', 'Iphong', '23', 9, 2017, 2599000, '04/12/2019'),
('SML21', 'Samsul', '21', 6, 2017, 2699900, '03/12/2019');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id` int(3) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `akses` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id`, `username`, `password`, `akses`, `status`) VALUES
(1, 'admin', '12345', 'admin', 'Aktif'),
(6, 'ammar', 'dfg', 'pegawai', 'Aktif'),
(9, 'budi', '123', 'pegawai', 'Aktif'),
(3, 'fawwaz', 'qwe', 'pegawai', 'Aktif'),
(4, 'haris', 'zxc', 'pegawai', 'Tidak Aktif'),
(5, 'padhisa', 'abc', 'pegawai', 'Tidak Aktif'),
(2, 'pegawai', 'qwerty', 'pegawai', 'Aktif'),
(7, 'umbu', 'mno', 'pegawai', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `notransaksi` varchar(10) NOT NULL,
  `kodeHp` varchar(20) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `merkHp` varchar(20) NOT NULL,
  `tipe` varchar(20) NOT NULL,
  `harga` varchar(20) NOT NULL,
  `jumlah` varchar(25) NOT NULL,
  `subtotal` varchar(25) NOT NULL,
  `total` varchar(25) NOT NULL,
  `bayar` varchar(25) NOT NULL,
  `kembalian` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`notransaksi`, `kodeHp`, `tanggal`, `merkHp`, `tipe`, `harga`, `jumlah`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
('TR001', 'apa12', '04/12/2019', 'Apache', '12', '1999800', '1', '1999800', '4699700', '4700000', '300'),
('TR002', 'apa12', '04/12/2019', 'Apache', '12', '1999800', '1', '1999800', '4699700', '4700000', '300'),
('TR002', 'sml21', '04/12/2019', 'Samsul', '21', '2699900', '1', '2699900', '4699700', '4700000', '300'),
('TR003', 'apa12', '04/12/2019', 'Apache', '12', '1999800', '1', '1999800', '4699700', '4700000', '300'),
('TR003', 'sml21', '04/12/2019', 'Samsul', '21', '2699900', '1', '2699900', '4699700', '4700000', '300'),
('TR004', 'apa12', '04/12/2019', 'Apache', '12', '1999800', '1', '1999800', '7399600', '7400000', '400'),
('TR004', 'sml21', '04/12/2019', 'Samsul', '21', '2699900', '2', '5399800', '7399600', '7400000', '400'),
('TR005', 'apa12', '04/12/2019', 'Apache', '12', '1999800', '1', '1999800', '4699700', '4700000', '300'),
('TR005', 'sml21', '04/12/2019', 'Samsul', '21', '2699900', '1', '2699900', '4699700', '4700000', '300'),
('TR006', 'APA12', '04/12/2019', 'Apache', '12', '1999800', '1', '1999800', '1999800', '2000000', '200'),
('TR007', 'APA12', '04/12/2019', 'Apache', '12', '1999900', '1', '1999900', '4598900', '4600000', '1100'),
('TR007', 'IP11', '04/12/2019', 'Iphong', '23', '2599000', '1', '2599000', '4598900', '4600000', '1100'),
('TR008', 'hhhuhu', '04/12/2019', 'njnjn', 'hh7', '13', '1000', '13000', '13000', '13000', '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksii`
--

CREATE TABLE `transaksii` (
  `notransaksi` varchar(50) NOT NULL,
  `kodeHp` varchar(50) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `merkHp` varchar(50) NOT NULL,
  `tipe` varchar(50) NOT NULL,
  `harga` varchar(50) NOT NULL,
  `jumlah` varchar(50) NOT NULL,
  `subtotal` varchar(50) NOT NULL,
  `total` varchar(50) NOT NULL,
  `bayar` varchar(50) NOT NULL,
  `kembalian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksii`
--

INSERT INTO `transaksii` (`notransaksi`, `kodeHp`, `tanggal`, `merkHp`, `tipe`, `harga`, `jumlah`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
('TR001', 'apa12', '04/12/2019', 'Apache', '12', '1999900', '1', '1999900', '1999900', '2000000', '100'),
('TR002', 'apa12', '04/12/2019', 'Apache', '12', '1999900', '1', '1999900', '4699800', '5000000', '300200');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `handphone`
--
ALTER TABLE `handphone`
  ADD PRIMARY KEY (`kode_hp`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indeks untuk tabel `transaksii`
--
ALTER TABLE `transaksii`
  ADD PRIMARY KEY (`notransaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
