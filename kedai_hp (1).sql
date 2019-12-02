-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Des 2019 pada 18.01
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
-- Struktur dari tabel `coba`
--

CREATE TABLE `coba` (
  `id` int(10) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `tipe` varchar(50) NOT NULL,
  `harga` varchar(50) NOT NULL,
  `jumlah` varchar(50) NOT NULL,
  `subtotal` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `tgl_data` varchar(10) NOT NULL,
  `batasgaransi` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `handphone`
--

INSERT INTO `handphone` (`kode_hp`, `merk_hp`, `tipe`, `jumlah`, `tahun`, `harga`, `tgl_data`, `batasgaransi`) VALUES
('1', 'Oppo Y19', 'Oppo', 0, 2019, 2900000, '', '0'),
('10', 'Oppo', 'A39', 2, 2017, 5900000, '28/11/2019', '01/12/2019'),
('2', 'Samsung S10', 'Samsung', 1, 2019, 7500000, '', '0'),
('23', 'Oppo', 'A70', 2, 2017, 12345678, '01/12/2019', '04/12/2019'),
('3', 'www', 'www', 0, 321, 1111, '24/11/2019', '0'),
('4', 'aaaa', 'aaaaa', 1, 2019, 23456, '27/11/2019', '0'),
('5', 'fvds', 'vfz', 0, 2010, 4321, '24/11/2019', '0'),
('7', 'rgfeds', 'rtbsvasd', 1, 2009, 12345, '27/11/2019', '0'),
('SMS10', 'Samsung', 'S10', 1, 2019, 1200000, '26/11/2019', '0');

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
(3, 'fawwaz', 'qwe', 'pegawai', 'Aktif'),
(4, 'haris', 'zxc', 'pegawai', 'Tidak Aktif'),
(5, 'padhisa', 'abc', 'pegawai', 'Aktif'),
(2, 'pegawai', 'qwerty', 'pegawai', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `notransaksi` varchar(50) NOT NULL,
  `kodeHp` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
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
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`notransaksi`, `kodeHp`, `tanggal`, `merkHp`, `tipe`, `harga`, `jumlah`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
('TR002', '1', '2019-11-27', 'Oppo Y19', 'Oppo', '2500000', '2', '5000000', '5000000', '10000000', '5000000'),
('TR003', '2', '2019-11-27', 'Samsung S10', 'Samsung', '7500000', '3', '22500000', '22500000', '0', ''),
('TR006', '3', '2019-11-27', 'www', 'www', '1111', '2', '2222', '2222', '4444', '2222'),
('TR007', 'SMS10', '2019-11-27', 'Samsung', 'S10', '1200000', '2', '2400000', '2400000', '2500000', '100000'),
('TR008', '10', '2019-11-28', 'Oppo', 'A39', '5900000', '2', '11800000', '11800000', '12000000', '200000'),
('TR009', 'SMS10', '2019-12-01', 'Samsung', 'S10', '1200000', '1', '1200000', '1200000', '1300000', '100000'),
('TR010', '7', '2019-12-01', 'rgfeds', 'rtbsvasd', '12345', '1', '12345', '12345', '12345', '0'),
('TR011', 'SMS10', '2019-12-01', 'Samsung', 'S10', '1200000', '1', '1200000', '1200000', '12345678', '11145678'),
('TR012', '2', '2019-12-02', 'Samsung S10', 'Samsung', '7500000', '3', '22500000', '22500000', '', '');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `coba`
--
ALTER TABLE `coba`
  ADD PRIMARY KEY (`id`);

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
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`notransaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `coba`
--
ALTER TABLE `coba`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
