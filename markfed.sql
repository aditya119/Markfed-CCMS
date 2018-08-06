-- phpMyAdmin SQL Dump
-- version 4.7.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 06, 2018 at 04:17 PM
-- Server version: 5.6.40
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `markfed`
--

-- --------------------------------------------------------

--
-- Table structure for table `case_info`
--

CREATE TABLE `case_info` (
  `file_number` varchar(50) NOT NULL,
  `case_number` varchar(50) NOT NULL,
  `year` varchar(4) NOT NULL,
  `case_type` varchar(50) NOT NULL,
  `court_name` varchar(50) NOT NULL,
  `lawyer` varchar(50) NOT NULL,
  `location` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `case_info`
--


-- --------------------------------------------------------

--
-- Table structure for table `case_proceeding`
--

CREATE TABLE `case_proceeding` (
  `file_number` varchar(50) NOT NULL,
  `proceeding_number` int(100) DEFAULT '1',
  `proceeding_date` date NOT NULL,
  `decision` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `next_hearing_on` date DEFAULT NULL,
  `order_file` longblob,
  `order_file_type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `case_proceeding`
--

-- --------------------------------------------------------

--
-- Table structure for table `case_type`
--

CREATE TABLE `case_type` (
  `casetype_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `case_type`
--

INSERT INTO `case_type` (`casetype_name`) VALUES
('NONE');

-- --------------------------------------------------------

--
-- Table structure for table `case_update_log`
--

CREATE TABLE `case_update_log` (
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_number` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `user_group` varchar(50) NOT NULL,
  `action` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `case_update_log`
--


-- --------------------------------------------------------

--
-- Table structure for table `court_type`
--

CREATE TABLE `court_type` (
  `court_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `court_type`
--

INSERT INTO `court_type` (`court_name`) VALUES
('NONE');

-- --------------------------------------------------------

--
-- Table structure for table `date_info`
--

CREATE TABLE `date_info` (
  `file_number` varchar(50) NOT NULL,
  `case_filed_on` date DEFAULT NULL,
  `notice_received_on` date DEFAULT NULL,
  `first_hearing_on` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `date_info`
--

-- --------------------------------------------------------

--
-- Table structure for table `decision_type`
--

CREATE TABLE `decision_type` (
  `decision` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `decision_type`
--

INSERT INTO `decision_type` (`decision`) VALUES
('ADJOURNMENT'),
('FINAL JUDGEMENT'),
('INTERIM ORDER'),
('PENDING');

-- --------------------------------------------------------

--
-- Table structure for table `latest_proceeding`
--

CREATE TABLE `latest_proceeding` (
  `file_number` varchar(50) NOT NULL,
  `proceeding_number` int(10) NOT NULL,
  `proceeding_date` date NOT NULL,
  `decision` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `next_hearing_on` date DEFAULT NULL,
  `order_file` longblob,
  `order_file_type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `latest_proceeding`
--


-- --------------------------------------------------------

--
-- Table structure for table `lawyer`
--

CREATE TABLE `lawyer` (
  `lawyer_name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lawyer`
--

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `location_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_name`) VALUES
('NONE');

-- --------------------------------------------------------

--
-- Table structure for table `petitioner_respondent_info`
--

CREATE TABLE `petitioner_respondent_info` (
  `file_number` varchar(50) NOT NULL,
  `petitioner_name` varchar(50) NOT NULL,
  `petitioner_email` varchar(100) DEFAULT NULL,
  `petitioner_address` mediumtext NOT NULL,
  `petitioner_file_type` varchar(100) DEFAULT NULL,
  `petitioner_file` longblob,
  `respondent_name` varchar(50) NOT NULL,
  `respondent_email` varchar(100) DEFAULT NULL,
  `respondent_address` mediumtext NOT NULL,
  `respondent_file_type` varchar(100) DEFAULT NULL,
  `respondent_file` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petitioner_respondent_info`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `contact` varchar(13) DEFAULT NULL,
  `email_add` varchar(50) DEFAULT NULL,
  `email_password` varchar(50) DEFAULT NULL,
  `user_group` varchar(50) NOT NULL,
  `status_app` varchar(50) NOT NULL DEFAULT 'OFFLINE',
  `status_web` varchar(50) NOT NULL DEFAULT 'OFFLINE'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`username`, `password`, `full_name`, `contact`, `email_add`, `email_password`, `user_group`, `status_app`, `status_web`) VALUES
('test', 'test', 'Test User', '+919876543210', 'testuser@gmail.com', '1234', 'Administrator', 'OFFLINE', 'OFFLINE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `case_info`
--
ALTER TABLE `case_info`
  ADD PRIMARY KEY (`file_number`);

--
-- Indexes for table `case_type`
--
ALTER TABLE `case_type`
  ADD PRIMARY KEY (`casetype_name`);

--
-- Indexes for table `case_update_log`
--
ALTER TABLE `case_update_log`
  ADD PRIMARY KEY (`time`);

--
-- Indexes for table `court_type`
--
ALTER TABLE `court_type`
  ADD PRIMARY KEY (`court_name`);

--
-- Indexes for table `date_info`
--
ALTER TABLE `date_info`
  ADD PRIMARY KEY (`file_number`);

--
-- Indexes for table `decision_type`
--
ALTER TABLE `decision_type`
  ADD PRIMARY KEY (`decision`);

--
-- Indexes for table `latest_proceeding`
--
ALTER TABLE `latest_proceeding`
  ADD PRIMARY KEY (`file_number`);

--
-- Indexes for table `lawyer`
--
ALTER TABLE `lawyer`
  ADD PRIMARY KEY (`lawyer_name`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`location_name`);

--
-- Indexes for table `petitioner_respondent_info`
--
ALTER TABLE `petitioner_respondent_info`
  ADD PRIMARY KEY (`file_number`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
