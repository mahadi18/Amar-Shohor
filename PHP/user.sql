-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2016 at 05:49 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `user`
--

-- --------------------------------------------------------

--
-- Table structure for table `issue`
--

CREATE TABLE IF NOT EXISTS `issue` (
`id` int(11) NOT NULL,
  `latitude` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `longitude` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `posted_time` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(800) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `issue`
--

INSERT INTO `issue` (`id`, `latitude`, `longitude`, `posted_time`, `image`, `name`) VALUES
(1, '', '', '', 'http://192.168.137.1:8080/uploads/0.png', 'tect'),
(2, '', '', '', 'http://192.168.137.1:8080/uploads/1.png', 'tect'),
(3, '', '', '', 'http://192.168.137.1:8080/uploads/2.png', 'tect'),
(4, '', '', '', 'http://192.168.137.1:8080/uploads/3.png', 'tect'),
(5, '', '', '', 'http://192.168.137.1:8080/PhotoUpload/uploads/4.png', 'annual feast'),
(6, '0.0', '0.0', '20160212220954', 'http://192.168.137.1:8080/PhotoUpload/uploads/5.png', 'its me montasir bin shams'),
(7, '0.0', '0.0', '20160212221650', 'http://192.168.137.1:8080/PhotoUpload/uploads/6.png', 'xavier marahall'),
(8, '22.8980586', '89.5005289', '2016-02-12 22:29:57', 'http://192.168.137.1:8080/PhotoUpload/uploads/7.png', 'Eye Sight'),
(9, '22.8980586', '89.5005289', '2016-02-12 22:39:50', 'http://192.168.137.1:8080/PhotoUpload/uploads/8.png', 'programming contest');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE IF NOT EXISTS `user_info` (
`id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`id`, `name`, `username`, `email`, `password`) VALUES
(1, 'montasir', 'dipto', '@gmail.com', 'dipto012'),
(2, 'mahadi mohammad', 'mahadi18', 'mahadi2k12@gmail.com', '1207018');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `issue`
--
ALTER TABLE `issue`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `issue`
--
ALTER TABLE `issue`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
