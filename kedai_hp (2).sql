-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2019 at 06:32 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kedai_hp`
--

-- --------------------------------------------------------

--
-- Table structure for table `handphone`
--

CREATE TABLE IF NOT EXISTS `handphone` (
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
-- Dumping data for table `handphone`
--

INSERT INTO `handphone` (`kode_hp`, `merk_hp`, `tipe`, `jumlah`, `tahun`, `harga`, `tgl_data`, `batasgaransi`) VALUES
('1', 'Oppo Y19', 'Oppo', 1, 2019, 2900000, '', '0'),
('10', 'Oppo', 'A39', 1, 2017, 5900000, '28/11/2019', '01/12/2019'),
('2', 'Samsung S10', 'Samsung', 1, 2019, 7500000, '', '0'),
('23', 'Oppo', 'A70', 1, 2017, 12345678, '01/12/2019', '04/12/2019'),
('3', 'www', 'www', 0, 321, 1111, '24/11/2019', '0'),
('4', 'aaaa', 'aaaaa', 1, 2019, 23456, '27/11/2019', '0'),
('5', 'fvds', 'vfz', 1, 2010, 4321, '24/11/2019', '0'),
('7', 'rgfeds', 'rtbsvasd', 1, 2009, 12345, '27/11/2019', '0'),
('SMS10', 'Samsung', 'S10', 1, 2019, 1200000, '26/11/2019', '0');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE IF NOT EXISTS `pegawai` (
  `id` int(3) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `akses` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id`, `username`, `password`, `akses`, `status`) VALUES
(1, 'admin', '12345', 'admin', 'Aktif'),
(3, 'fawwaz', 'qwe', 'pegawai', 'Aktif'),
(4, 'haris', 'zxc', 'pegawai', 'Tidak Aktif'),
(5, 'padhisa', 'abc', 'pegawai', 'Aktif'),
(2, 'pegawai', 'qwerty', 'pegawai', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `notransaksi` varchar(50) NOT NULL,
  `kodeHp` varchar(50) NOT NULL,
  `tglTransaksi` varchar(10) NOT NULL,
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
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`notransaksi`, `kodeHp`, `tglTransaksi`, `merkHp`, `tipe`, `harga`, `jumlah`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
('TR001', '23', '2019-12-02', 'Oppo', 'A70', '12345678', '1', '12345678', '12345678', '12345678', '0'),
('TR002', '10', '2019-12-02', 'Oppo', 'A39', '5900000', '1', '5900000', '5900000', '6000000', '100000'),
('TR003', '23', '02/12/2019', 'Oppo', 'A70', '12345678', '1', '12345678', '12345678', '12345678', '0'),
('TR004', '10', '2019-12-03', 'Oppo', 'A39', '5900000', '1', '5900000', '5900000', '098765432', '92865432');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `handphone`
--
ALTER TABLE `handphone`
 ADD PRIMARY KEY (`kode_hp`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
 ADD PRIMARY KEY (`username`), ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
 ADD PRIMARY KEY (`notransaksi`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
