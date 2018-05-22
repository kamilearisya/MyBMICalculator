-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2018 at 03:59 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nutdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `type_food`
--

CREATE TABLE `type_food` (
  `type_food_id` int(11) NOT NULL,
  `jenis_darah` varchar(255) NOT NULL,
  `categori` varchar(50) NOT NULL,
  `bmi_range` varchar(50) NOT NULL,
  `f_1` varchar(50) NOT NULL,
  `f_2` varchar(50) NOT NULL,
  `f_3` varchar(50) NOT NULL,
  `f_4` varchar(50) NOT NULL,
  `f_5` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type_food`
--

INSERT INTO `type_food` (`type_food_id`, `jenis_darah`, `categori`, `bmi_range`, `f_1`, `f_2`, `f_3`, `f_4`, `f_5`) VALUES
(1, 'O', 'Underweight', '< 18.5', 'Sweet potatoes', 'Corn', 'Beans', 'Cauliflower ', 'Wheat flour'),
(2, 'O', 'Overweight', '25.1 - 30.0', 'Green tea', 'Broccoli', 'Beaf', 'Codfish', 'Saltwater fish'),
(3, 'O', 'Normal', '18.5 - 25.0', 'Beans', 'Nuts ', 'Beef', 'Seafood', 'Vegetables'),
(4, 'O', 'Obese', '> 30.1', 'Green tea', 'Broccoli', 'Beaf', 'Codfish', 'Saltwater fish'),
(5, 'A', 'Underweight', '< 18.5', 'Meat', 'Dairy food', 'Wheat food', 'Lima beans', 'Kidney beans'),
(6, 'A', 'Overweight', '25.1 - 30.0', 'Soya food', 'Vegetable oil', 'Pineapple', 'Vegetable', 'Salmon'),
(7, 'A', 'Normal', '18.5 - 25.0', 'Whole grains', 'Sesame seeds', 'Kelp', 'Broccoli', 'Spinach'),
(8, 'A', 'Obese', '> 30.1', 'Soya food', 'Vegetable oil', 'Pineapple', 'Vegetable', 'White fis'),
(9, 'B', 'Underweight', '< 18.5', 'Sesame seeds', 'Lentils', 'Corn', 'Peanuts', 'Wheat'),
(10, 'B', 'Overweight', '25.1 - 30.0', 'Green vegetable', 'Goat meat', 'Lamb', 'Mutton', 'Eggs'),
(11, 'B', 'Normal', '18.5 - 25.0', 'Beans', 'Eggs', 'Fish', 'Mutton', 'Lamb'),
(12, 'B', 'Obese', '> 30.1', 'Green vegetable', 'Goat meat', 'Lamb', 'Mutton', 'Eggs'),
(13, 'AB', 'Underweight', '< 18.5', 'Red meat', 'Buckwheats', 'Lima/Kidney Beans', 'Corn', 'Wheat'),
(14, 'AB', 'Overweight', '25.1 - 30.0', 'Green vegetable', 'Tofu', 'Seafood', 'Poultry', 'Yogurt'),
(15, 'AB', 'Normal', '18.5 - 25.0', 'Whole grains', 'Fruits', 'Vegetables', 'Meat', 'Dairy food'),
(16, 'AB', 'Obese', '> 30.1', 'Green vegetable', 'Tofu', 'Seafood', 'Seeds and Nuts', 'Kelp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `type_food`
--
ALTER TABLE `type_food`
  ADD KEY `typeFood_id` (`type_food_id`);
ALTER TABLE `type_food` ADD FULLTEXT KEY `my_index` (`jenis_darah`,`categori`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `type_food`
--
ALTER TABLE `type_food`
  MODIFY `type_food_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
