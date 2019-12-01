-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2019 at 06:23 PM
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
-- Table structure for table `coba`
--

CREATE TABLE IF NOT EXISTS `coba` (
`id` int(10) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `tipe` varchar(50) NOT NULL,
  `harga` varchar(50) NOT NULL,
  `jumlah` varchar(50) NOT NULL,
  `subtotal` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
('1', 'Oppo Y19', 'Oppo', 0, 2019, 2900000, '', '0'),
('10', 'Oppo', 'A39', 5, 2017, 5900000, '28/11/2019', '01/12/2019'),
('2', 'Samsung S10', 'Samsung', 0, 2019, 7500000, '', '0'),
('23', 'Oppo', 'A70', 5, 2017, 12345678, '01/12/2019', '04/12/2019'),
('3', 'www', 'www', 0, 321, 1111, '24/11/2019', '0'),
('4', 'aaaa', 'aaaaa', 1, 2019, 23456, '27/11/2019', '0'),
('5', 'fvds', 'vfz', 0, 2010, 4321, '24/11/2019', '0'),
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
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id`, `username`, `password`, `status`) VALUES
(1, 'admin', '12345', 'admin'),
(2, 'pegawai', 'qwerty', 'pegawai');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
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
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`notransaksi`, `kodeHp`, `tanggal`, `merkHp`, `tipe`, `harga`, `jumlah`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
('TR002', '1', '2019-11-27', 'Oppo Y19', 'Oppo', '2500000', '2', '5000000', '5000000', '10000000', '5000000'),
('TR003', '2', '2019-11-27', 'Samsung S10', 'Samsung', '7500000', '3', '22500000', '22500000', '0', ''),
('TR006', '3', '2019-11-27', 'www', 'www', '1111', '2', '2222', '2222', '4444', '2222'),
('TR007', 'SMS10', '2019-11-27', 'Samsung', 'S10', '1200000', '2', '2400000', '2400000', '2500000', '100000'),
('TR008', '10', '2019-11-28', 'Oppo', 'A39', '5900000', '2', '11800000', '11800000', '12000000', '200000'),
('TR009', 'SMS10', '2019-12-01', 'Samsung', 'S10', '1200000', '1', '1200000', '1200000', '1300000', '100000'),
('TR010', '7', '2019-12-01', 'rgfeds', 'rtbsvasd', '12345', '1', '12345', '12345', '12345', '0'),
('TR011', 'SMS10', '2019-12-01', 'Samsung', 'S10', '1200000', '1', '1200000', '1200000', '12345678', '11145678');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coba`
--
ALTER TABLE `coba`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `handphone`
--
ALTER TABLE `handphone`
 ADD PRIMARY KEY (`kode_hp`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
 ADD PRIMARY KEY (`notransaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coba`
--
ALTER TABLE `coba`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
