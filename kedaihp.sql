-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2019 at 02:16 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kedaihp`
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
  `tgl_data` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `handphone`
--

INSERT INTO `handphone` (`kode_hp`, `merk_hp`, `tipe`, `jumlah`, `tahun`, `harga`, `tgl_data`) VALUES
('1', 'Oppo Y19', 'Oppo', 0, 2019, 2500000, ''),
('2', 'Samsung S10', 'Samsung', 0, 2019, 7500000, ''),
('3', 'www', 'www', 0, 321, 1111, '24/11/2019'),
('5', 'fvds', 'vfz', 0, 2010, 12345, '24/11/2019'),
('SMS10', 'Samsung', 'S10', 2, 2019, 1200000, '26/11/2019');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `handphone`
--
ALTER TABLE `handphone`
 ADD PRIMARY KEY (`kode_hp`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
