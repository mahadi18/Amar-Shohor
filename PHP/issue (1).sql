-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2016 at 07:45 AM
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
  `locationname` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `posted_time` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `problem_title` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(800) COLLATE utf8_unicode_ci NOT NULL,
  `submitted_by` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phpimage` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `issolve` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `issue`
--

INSERT INTO `issue` (`id`, `latitude`, `longitude`, `locationname`, `posted_time`, `problem_title`, `image`, `name`, `submitted_by`, `phpimage`, `issolve`) VALUES
(12, '22.8978792', '89.500861', '', '2016-02-13 12:50:38', 'PothHoles', 'http://192.168.137.1:8080/PhotoUpload/uploads/11.png', 'we are the friends', '', '/PhotoUpload/uploads/11.png', 1),
(13, '22.8978792', '89.500861', '', '2016-02-13 12:51:26', 'PothHoles', 'http://192.168.137.1:8080/PhotoUpload/uploads/12.png', 'on the way to saint martins', '', '/PhotoUpload/uploads/12.png', 1),
(14, '22.8978792', '89.500861', '', '2016-02-13 12:53:22', 'illegal advertising', 'http://192.168.137.1:8080/PhotoUpload/uploads/13.png', 'burhan', '', '/PhotoUpload/uploads/13.png', 1),
(15, '22.8978792', '89.500861', '', '2016-02-13 12:53:35', 'illegal advertising', 'http://192.168.137.1:8080/PhotoUpload/uploads/14.png', 'burhan', '', '/PhotoUpload/uploads/14.png', 1),
(16, '22.8980586', '89.5004917', '', '2016-02-14 15:10:31', 'eve teasing', 'http://192.168.137.1:8080/PhotoUpload/uploads/15.png', '', '', '/PhotoUpload/uploads/15.png', 1),
(17, '22.8980586', '89.5004917', '', '2016-02-14 15:26:41', 'faulty street light', 'http://192.168.137.1:8080/PhotoUpload/uploads/16.png', '', '', '/PhotoUpload/uploads/16.png', 0),
(35, '22.8981', '89.5005083', 'KUET Rd, Khulna, Bangladesh', '2016-02-17 02:35:24', 'PothHoles', 'http://192.168.137.1:8080/PhotoUpload/uploads/21.png', 'path holes due to over rain', 'mahadi18', '../PhotoUpload/uploads/21.png', 1),
(36, '22.8977616', '89.5006666', 'KUET Rd, Khulna, Bangladesh', '2016-02-17 02:39:36', 'faulty street light', 'http://192.168.137.1:8080/PhotoUpload/uploads/35.png', 'light on always', 'mahadi18', '../PhotoUpload/uploads/35.png', 0),
(37, '22.8980799', '89.5004766', 'KUET Rd, Khulna, Bangladesh', '2016-02-17 02:40:23', 'trash dumping', 'http://192.168.137.1:8080/PhotoUpload/uploads/36.png', 'animals in dustbin', 'mahadi18', '../PhotoUpload/uploads/36.png', 1),
(38, '22.8980799', '89.5004766', 'KUET Rd, Khulna, Bangladesh', '2016-02-17 02:40:58', 'Road Accident', 'http://192.168.137.1:8080/PhotoUpload/uploads/37.png', 'clash of cars', 'mahadi18', '../PhotoUpload/uploads/37.png', 0),
(41, '22.8981335', '89.5004103', 'KUET Rd, Khulna, Bangladesh', '2016-02-17 11:27:41', 'trash dumping', 'http://192.168.137.1:8080/PhotoUpload/uploads/38.png', 'Trash dumping', 'dipto', '../PhotoUpload/uploads/38.png', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `issue`
--
ALTER TABLE `issue`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `issue`
--
ALTER TABLE `issue`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=42;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
