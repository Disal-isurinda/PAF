-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3311
-- Generation Time: Apr 25, 2021 at 06:42 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `assistant`
--

-- --------------------------------------------------------

--
-- Table structure for table `tipstable`
--

CREATE TABLE `tipstable` (
  `tipId` varchar(60) NOT NULL,
  `relatedArea` varchar(60) NOT NULL,
  `tipDetail` varchar(500) NOT NULL,
  `date` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipstable`
--

INSERT INTO `tipstable` (`tipId`, `relatedArea`, `tipDetail`, `date`) VALUES
('1', 'System Polices', 'We collect no personal information about you unless you choose to provide that information to us. We do not give, share, sell, or transfer any personal information to a third party.', '25.01.2020'),
('2', 'System Updates(Deleting Software)', 'There may now be software on the site that you no longer need. Select the unwanted software, right-click and select delete. You can also rename software by right-clicking in a similar way.', '18.02.2020'),
('3', 'Help Tip', 'Make sure your passwords are complex, at least 9 characters long and are not easy to guess.And please change them every 90 days. Any password can be cracked if it is never changed.', '10.03.2020');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
