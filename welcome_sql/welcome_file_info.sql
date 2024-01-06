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
-- Table structure for table `file_info`
--

DROP TABLE IF EXISTS `file_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_info` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `tourboard_id` int DEFAULT NULL,
  `save_folder` varchar(45) DEFAULT NULL,
  `original_file` varchar(50) DEFAULT NULL,
  `save_file` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `tourboard_id` (`tourboard_id`),
  CONSTRAINT `file_info_ibfk_1` FOREIGN KEY (`tourboard_id`) REFERENCES `tourboard` (`tourboard_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_info`
--

LOCK TABLES `file_info` WRITE;
/*!40000 ALTER TABLE `file_info` DISABLE KEYS */;
INSERT INTO `file_info` VALUES (3,4,'231123','dokyo.jpg','5e323c1e-9ab2-4437-a2eb-b7aa78de178e.jpg'),(5,6,'231123','aurora-1185464_1280.jpg','1392655f-8b54-4e4b-8c48-863614ed4976.jpg'),(6,7,'231123','mountain-477832_1280.jpg','d57c4ef1-891a-4860-ae56-d3ad503c02f9.jpg'),(7,8,'231123','colosseum-4544105_1280.jpg','3a142a69-6c2a-41f6-a3db-17e8e229958e.jpg'),(8,9,'231123','road-4103334_1280.jpg','050f3501-6bf9-4e68-9ee1-87025666408e.jpg'),(9,10,'231123','man-5737858_1280.jpg','4e04ba39-5dd4-4324-b219-6c9edcf329da.jpg'),(10,11,'231123','tottenham.png','2210ecf0-896d-4f1a-9727-b947f4ef9656.png'),(11,12,'231123','sagrada-familia-552084_1280.jpg','9692cdf7-bc06-4977-8769-e52ae8e41b21.jpg'),(12,13,'231123','rio-2352566_1280.jpg','09e73e68-34c8-4709-8f3e-f4bfcb800438.jpg'),(13,14,'231123','penguins-429136_1280.jpg','c675b82e-37c0-4171-b05b-3d6710913c4f.jpg'),(14,15,'231123','santorini-4996846_1280.jpg','6009c30c-1eb3-4da9-8cae-0e6faa2123a9.jpg'),(15,16,'231123','china-6788187_1280.jpg','fce7e3e9-b5fe-4b0d-9996-ec777dd79ea8.jpg'),(16,17,'231123','stadium-323795_1280.jpg','8bbd9104-46e3-45ae-9531-9961446afd06.jpg'),(17,18,'231123','waterfall-5050298_1280.jpg','fea8d3fa-5619-425d-ba88-565cc50f6ba4.jpg'),(18,19,'231123','woman-5628426_1280.jpg','166f3bb0-8cbf-4ba4-aee4-834b5b884a47.jpg'),(19,20,'231123','elephant-3832516_1280.jpg','6451607b-acb5-4535-9000-8374393fb9f4.jpg'),(20,21,'231123','polynesia-3021072_1280.jpg','f639669f-14d9-4b03-b53d-76ce5407bf27.jpg'),(21,22,'231123','disney-1359225_1280.jpg','00ad4d50-2d6d-4891-b236-939793513048.jpg'),(22,23,'231123','plaza-espana-1751442_1280.jpg','f0d8c92a-4b9c-4bc7-96a2-0509f5659dbc.jpg'),(23,24,'231123','rome-7244828_1280.jpg','7b521668-03b4-4a03-983e-80039652aa9a.jpg'),(24,25,'231123','blue-hour-3848856_1280.jpg','b97d4ebb-edbc-4b58-9fab-a56c45d885b6.jpg'),(25,26,'231123','architecture-140785_1280.jpg','2e342915-b5ef-4b6f-b5fd-48a7d721b459.jpg'),(26,27,'231123','munchen.png','22f603a9-4cdc-4198-8a5f-b11d03ff4c00.png'),(27,28,'231123','sunrise-1014712_1280.jpg','58b117d3-6527-44d0-aba6-036b176c0083.jpg'),(28,29,'231123','beach-4405357_1280.jpg','663895d6-d840-4b19-9c7d-8036f11fa3da.jpg'),(29,30,'231123','australia-1281935_1280.jpg','e9e328a4-9031-49dd-a6a6-c755abd4eb4e.jpg'),(30,31,'231123','grand-canyon-1235221_1280.jpg','8ca780ba-4ab9-43a0-acb1-4d76377b8245.jpg'),(31,32,'231123','usa-1183579_1280.jpg','8bc8c5a3-e76c-4299-ad58-9d07b3986fdd.jpg'),(32,33,'231123','koala-3055832_1280.jpg','4c0cb855-8c1f-4d61-a7c2-e203eaf419aa.jpg'),(33,34,'231123','schilthorn-3033448_1280.jpg','594551ee-3a61-4d83-9eb3-5dfc184c4aea.jpg'),(34,35,'231123','mosque-4372296_1280.jpg','84311389-ead9-4008-93cf-3cbb50c5fa9b.jpg'),(35,36,'231123','hamburg-4103406_1280.jpg','885d88bd-bc77-4560-b161-105c3d42fd38.jpg'),(37,38,'231123','artworks-8hUNunJfPf7jLpzY-jYmGvg-t500x500.jpg','3f8bac9e-48be-409f-aacd-08f4b3a6e7df.jpg'),(38,39,'231123','northern-lights-5947608_1280.jpg','a9af52a1-41e0-4a53-8fff-d70216e6dc39.jpg');
/*!40000 ALTER TABLE `file_info` ENABLE KEYS */;
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
