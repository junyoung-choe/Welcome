-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: welcome
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `board_title` varchar(50) DEFAULT NULL,
  `board_content` varchar(255) DEFAULT NULL,
  `board_views` int DEFAULT '0',
  `board_regDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,1,'첫번째글입니다','첫번째글입니다',0,'2023-11-23 03:10:38'),(2,2,'잊지 못할 여행 너무 완벽했습니다','이번에 다녀온 여행은 정말 재밌었습니다. \n다음에 이 사이트에서 또 이용해서 패키지 등록하고 싶어요 :) \nwelcome 최고 !!!',0,'2023-11-23 07:46:07'),(3,1,'다시 패키지를 등록한다면...','아 여기 정말 최고네요 마일리지도 주고 여러 사이트에서 직접 비교하며 사용했지만\nwelcome 사이트를 만나고 나서 너무 행복합니다.',0,'2023-11-23 07:47:45'),(4,3,'최고의 여행 다녀와 너무 뿌듯하고 행복합니다','최고입니다 다음에 꼭 다시 이용할게요 welcome 추천합니다 ! ! !',0,'2023-11-23 07:50:51'),(5,3,'여행 후기입니다.','여행 후기...\n여행사 직원분들을 welcome 에서 직접 매칭해주셔서 편했고 welcome 에서 공항 픽업 서비스도 제공해줘서 너무 좋았습니다.',0,'2023-11-23 07:51:42');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24 16:38:37
