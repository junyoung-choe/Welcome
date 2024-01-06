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
-- Table structure for table `tourboard`
--

DROP TABLE IF EXISTS `tourboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tourboard` (
  `tourboard_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `tourboard_tourName` varchar(50) DEFAULT NULL,
  `tourboard_keyword` varchar(50) DEFAULT NULL,
  `tourboard_regDate` timestamp NULL DEFAULT NULL,
  `tourboard_startDate` timestamp NULL DEFAULT NULL,
  `tourboard_endDate` timestamp NULL DEFAULT NULL,
  `tourboard_deadLineDate` timestamp NULL DEFAULT NULL,
  `tourboard_price` int DEFAULT NULL,
  `tourboard_salePrice` int DEFAULT NULL,
  `tourboard_stock` int DEFAULT NULL,
  `tourboard_stockCnt` int DEFAULT NULL,
  `tourboard_fnishedYn` tinyint(1) DEFAULT NULL,
  `tourboard_departure` varchar(50) DEFAULT NULL,
  `tourboard_destination` varchar(50) DEFAULT NULL,
  `tourboard_views` int DEFAULT NULL,
  `tourboard_content` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`tourboard_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tourboard_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourboard`
--

LOCK TABLES `tourboard` WRITE;
/*!40000 ALTER TABLE `tourboard` DISABLE KEYS */;
INSERT INTO `tourboard` VALUES (4,4,'재밌는 일본 여행','일본, 도쿄, 후지산, 도톤보리','2023-11-23 04:03:11','2023-12-01 03:00:00','2023-12-04 03:00:00','2023-11-27 14:59:00',600000,550000,30,7,0,'인천','도쿄',0,'재밌는 일본 여행을 즐기고 싶은 사람!'),(6,4,'스웨덴 키루나로, 오라오라 오로라 보러~','스웨덴, 오로라, 키루나','2023-11-23 04:25:07','2023-12-13 06:00:00','2023-12-17 16:00:00','2023-11-23 14:59:00',7000000,6000000,20,2,0,'인천','키루나',0,'스웨덴에서 즐기는 오로라 투어!'),(7,5,'도쿄/야마나시 3일#후지산 인생샷 포인트 #도쿄전망대 #도쿄시내숙박','일본, 도쿄, 후지산','2023-11-23 04:29:00','2023-12-08 01:00:00','2023-12-11 03:00:00','2023-11-30 14:59:00',880000,750000,20,0,0,'대구','도쿄',0,'도쿄 시내에서 숙박으로 편안한 일정을 즐길 수 있고, 후지산을 조망하며 인생샷을 남길 수 있는 포인트를 관광하는 일정입니다.'),(8,6,'이탈리아 핵심 일주 9일 #남부투어','이탈리아, 콜로세움, 예술','2023-11-23 05:17:03','2024-01-23 14:59:00','2024-03-02 14:59:00','2030-12-31 14:59:00',3192000,2999000,27,0,0,'인천','이탈리아',0,'예술이 가득한 이탈리아를 관광하는 여행입니다. 1개국을 중점으로 여행합니다'),(9,6,'중국 3일 여행 #1일자유 #내맘대로_일정','중국, 역사, 인문학','2023-11-23 05:20:03','2023-12-01 14:59:00','2023-12-05 14:59:00','2030-12-31 14:59:00',456000,396000,35,0,0,'인천','중국',0,'중국속의 유럽 하얼빈 ! 1일 상품으로 더 자유롭게 하얼빈을 여행해보세요'),(10,6,'베트남 하노이/하롱베이/닌빈 5일 여행','베트남, 강, 뱃놀이','2023-11-23 05:22:13','2020-12-31 14:59:00','2030-12-31 14:59:00','2030-12-31 14:59:00',589000,550000,37,0,0,'인천','베트남',0,'하노이를 중심으로 하노이 하롱베이 및 근교 지역인 난빈을 구석구석 제대로 보는 하노이 근교 여행 초특가 상품입니다.'),(11,6,'영국 런던 토트넘 투어','영국, 런던, 토트넘, 손흥민','2023-11-23 05:27:51','2020-12-31 14:59:00','2030-12-31 14:59:00','2030-12-31 14:59:00',4000000,3500000,30,3,0,'인천','런던',0,'손흥민 선수의 구단 토트넘의 경기와 구장을 투어할 수 있습니다!'),(12,6,'스페인 일주 #바르셀로나자유 #사라고사 #세고비아','스페인, 바르셀로나, 사라고사, 몬세라트케이블카','2023-11-23 05:30:44','2023-12-26 14:59:00','2024-01-02 14:59:00','2023-11-29 14:59:00',3000000,2800000,20,0,0,'인천','바르셀로나',0,'바르셀로나, 사라고사, 몬세라트케이블카 등 스페인여행'),(13,5,'남미 여행 #브라질 #마추픽추 #이과수폭포','브라질, 남미, 마추픽추, 크루즈, 리마','2023-11-23 05:35:55','2023-12-18 03:00:00','2023-12-27 04:00:00','2023-11-23 14:59:00',20000000,18000000,20,0,0,'인천','리마',0,'남미를 크루즈로 항해하는 잊지 못할 감동의 여정'),(14,5,'남극 투어 최남단 투어','남극, 펭귄, 빙하','2023-11-23 05:39:31','2023-11-23 14:59:00','2023-12-06 14:59:00','2023-11-17 14:59:00',18000000,15000000,25,0,0,'인천','남극',0,'남극 투어, 남극 생태 전문가인 가이드가 여러분과 함께합니다!'),(15,5,'그리스 일주 #산토리니 #메테오라 수도원','그리스, 아테네, 산토리니','2023-11-23 05:42:44','2023-12-06 14:59:00','2023-12-16 14:59:00','2023-11-23 14:59:00',3800000,3500000,15,0,0,'인천','그리스',0,'아테네 도착 항공편 코스로 최적의 동선을 제공합니다'),(16,5,'상해/항주/주가각 #준5성급호텔 #서호유람 #상해옛거리','중국, 상해, 항주, 주가각','2023-11-23 05:48:12','2023-11-24 14:59:00','2023-11-27 14:59:00','2023-11-23 14:59:00',699000,499000,30,0,0,'김포','상해',0,'상해,항주,주가각을 관광하는 베스트셀러 실속 여행 상품'),(17,5,'영국 맨체스터 투어 #맨유 #EPL','영국, 맨체스터, 맨유, 올드트래포드, EPL','2023-11-23 05:50:23','2024-01-08 14:59:00','2024-01-13 14:59:00','2023-12-05 14:59:00',2000000,1700000,20,0,0,'인천','맨체스터',0,'EPL 빅클럽 맨체스터 유나이티드 홈구장 투어!'),(18,5,'미동부 투어 #윈월드전망대 #나이아가라폭포','미국, 미동부, 전망대, 워싱턴','2023-11-23 05:53:45','2023-12-14 14:59:00','2023-12-20 14:59:00','2031-01-08 14:59:00',4700000,4300000,15,0,0,'인천','워싱턴',0,'가격 부담은 덜고 보다 실속있게 미주지역의 대표 일정을 즐기실 수 있습니다!'),(19,4,'튀르키예 항공 일주 9~10일','튀르키예, 국내선이동, 항공일주, 풍선, 여행','2023-11-23 05:54:41','2024-03-14 14:59:00','2024-04-03 14:59:00','2030-12-31 14:59:00',1499000,1399000,14,0,0,'인천','튀르키예',0,'오랜 기다림끝에 떠나는 가장 특별한 여행'),(20,4,'아프리카 완전일주 6개국+도하12일','아프리카, 동물, 강','2023-11-23 05:59:06','2024-02-01 14:59:00','2024-02-16 14:59:00','2030-12-31 14:59:00',11290000,11000000,12,0,0,'인천','아프리카',0,'동물의 왕국 아프리카의 진정한 사파리 체험과 세계3대폭포로 유명한 빅토리아 폭포를 감상하실수 있습니다.'),(21,4,'미국, 하와이 6일 #시티뷰 #1일관광','미국, 하와이, 아름다운풍경','2023-11-23 06:02:59','2024-01-24 14:59:00','2024-01-30 14:59:00','2030-12-31 14:59:00',222000,211000,20,0,0,'인천','하와이',0,'홀린듯 떠나온 하와이, 아름답고 기억에 남을 장소를 다녀오세요'),(22,5,'일본 테마파크 투어 ','일본, 도쿄, 디즈니, 테마파크','2023-11-23 06:10:19','2023-12-01 03:00:00','2023-12-05 14:59:00','2023-11-23 14:59:00',1100000,900000,25,2,0,'대구','도쿄',0,'도쿄 시내에서 숙박으로 편안한 일정을 즐길 수 있고, 도쿄 관광 및 테마파크를 둘러볼 수 있습니다'),(23,4,'스페인 일주 9일 #바르셀로나 자유시간','스페인, 관광, 자유, 축구, 토레스','2023-11-23 06:10:33','2023-12-17 14:59:00','2023-12-28 14:59:00','2030-12-31 14:59:00',3034000,2890000,6,0,0,'인천','스페인',0,'스페인 대표 관광지를 실속있는 합리적인 가격으로 돌아보는 일정입니다. 스페인에서 먹어봐야 할 4가지 스페인 음식과 함께하는 일정입니다'),(24,5,'서유럽 3국(프랑스/스위스/이탈리아)','이탈리아, 로마, 스위스, 프랑스, 서유럽','2023-11-23 06:14:59','2024-01-08 14:59:00','2024-01-18 14:59:00','2023-11-30 14:59:00',3699000,3499000,25,0,0,'인천','로마',0,'짧은 기간 동안 서유럽 3국 프랑스/스위스/이탈리아 인기 관광지를 여행하는 만족도 높은 상품입니다.'),(25,4,'독일 품격 일주9일 #베를린 #자유시간','독일, 다이닝, 미식, 베를린','2023-11-23 06:15:53','2024-04-23 14:59:00','2024-04-30 14:59:00','2030-12-31 14:59:00',5799000,4990000,25,0,0,'인천','베를린',0,'유럽 여행의 정점 ! 품격있는 호텔 숙박 및 특별한 다이닝으로 완벽한 여행을 만들어 보세요'),(26,4,'런던 일주 7일 떠나봅시다 ! ','영국, 관광, 아름다움, 런던','2023-11-23 06:21:09','2023-11-29 14:59:00','2023-12-04 14:59:00','2030-12-31 14:59:00',4780000,4600000,15,0,0,'김포','런던',0,'낭만이 가득한 한나라 깊이보기! 잉글랜드/스코틀랜드/아일랜드가 포함된 영국일주입니다.'),(27,5,'바이에른 뮌헨 구단 투어 #독일 #뮌헨','독일, 뮌헨, 김민재','2023-11-23 06:24:08','2023-12-01 14:59:00','2023-12-07 14:59:00','2023-11-23 14:59:00',2100000,1800000,10,0,0,'인천','뮌헨',0,'김민재의 바이에른 뮌헨의 경기 관람 및 구단 투어 패키지'),(28,4,'다낭 5일 #5성호텔 #시티투어','베트남, 뱃사공, 비행기, 맛있는음식, 다낭','2023-11-23 06:24:31','2024-03-22 14:59:00','2024-03-29 14:59:00','2030-12-31 14:59:00',389000,357000,21,1,0,'부산','다낭',0,'바나힐 테마파크를 포함한 다낭과 호이안의 핵심 관광지를 둘러보는 특별한 가격의 상품입니다. '),(29,5,'보라카이 5일 #헤난팜비치 #호핑투어 #세일링보트 #라운지','필리핀, 보라카이, 바다','2023-11-23 06:26:23','2023-12-13 14:59:00','2023-12-18 14:59:00','2023-11-23 14:59:00',1100000,990000,22,3,0,'김포','보라카이',0,'보라카이 바다로 떠나는 소풍~ 호핑투어 #맛집 #마사지 #자유일정까지 알차게 즐기기!'),(30,4,'시드니여행 인기투어 중심 #사막체험','호주, 시드니, 울릉공, 크르주, 오페라하우스','2023-11-23 06:27:41','2024-02-21 14:59:00','2024-02-28 14:59:00','2030-12-31 14:59:00',1899000,1790000,12,0,0,'인천','시드니',0,'호주 대표 도시♥시드니 집중 여행!'),(31,5,'미서부 3대 캐년 + 태평양 연안 일주 #LAS VEGAS 중심가 호텔 #호스슈벤드','미국, 미서부, 그랜드캐년, 로스앤젤레스','2023-11-23 06:28:55','2021-02-25 14:59:00','2024-03-09 14:59:00','2023-11-30 14:59:00',3990000,3790000,25,2,0,'인천','로스앤젤레스',0,'17 마일 드라이브를 달려 캘리포니아, 네바다, 애리조나, 유타까지 4개 주 곳곳에 숨어있는 미서부의 보석같은 관광명소들을 둘러보는 베스트 셀러 상품입니다.'),(32,5,'미국 서부 자연 속의 모험','여행, 자연, 미서부, 하이킹, 미국, 캘리포니아','2023-11-23 06:33:09','2020-12-31 14:59:00','2030-12-31 14:59:00','2030-12-31 14:59:00',1700000,1500000,15,0,0,'인천','캘리포니아',0,'캘리포니아, 네바다, 아리조나 등 미국 서부의 자연 경치를 탐험하며 캠핑과 하이킹 체험'),(33,4,'브리즈번/탕갈루마 7일 #모튼섬 ','호주, 코알라, 캥거루, 브리즈번','2023-11-23 06:33:15','2024-02-29 14:59:00','2031-03-08 14:59:00','2030-12-31 14:59:00',3200000,2990000,12,0,0,'인천','브리즈번',0,'자연친화적인 리조트인 탕갈루마 2박이 포함된 호주 여행입니다.'),(34,5,'알프스 설산 트레킹 탐험','스위스, 알프스, 설산','2023-11-23 06:34:38','2023-12-01 14:59:00','2023-12-07 14:59:00','2030-12-31 14:59:00',1900000,1800000,10,0,0,'인천','스위스',0,'스위스 설산 트레킹 여행, 아름다운 풍경 감상'),(35,5,'이슬람 문화의 아름다움 - 터키 모스크 투어','터키, 튀르키예, 모스크, 이슬람, 이스탄불','2023-11-23 06:35:59','2023-12-04 14:59:00','2023-12-08 14:59:00','2023-11-23 14:59:00',1800000,1600000,22,0,0,'김포','이스탄불',0,'이스탄불, 안탈리아 지역에서의 터키의 아름다운 모스크들을 탐방하며 이슬람 문화 체험'),(36,5,'독일의 섬 도시 - 함부르크 탐방','여행, 함부르크, 독일, 역사, 문화','2023-11-23 06:37:44','2024-01-01 14:59:00','2024-01-08 14:59:00','2023-12-07 14:59:00',1500000,1300000,30,0,0,'인천','함부르크',0,'함부르크의 역사적인 건축물, 열린 시장, 열정적인 예술과 문화를 체험하는 여행'),(38,6,'화성여행 갈끄니까~','화성, NASA, 우주여행','2023-11-23 07:32:38','2024-01-23 14:59:00','2024-01-25 14:59:00','2030-12-31 14:59:00',200000000,200000000,3,0,0,'NASA','화성',0,'화성여행 함께합시다 이제는 때가 됬습니다 우주여행'),(39,4,'오로라 여행','오로라, 여행, 아름다움','2023-11-23 08:39:41','2023-11-30 14:59:00','2030-12-31 14:59:00','2030-12-31 14:59:00',1000000,800000,5,0,0,'인천','오로라',0,'오로라를 보러갑시다');
/*!40000 ALTER TABLE `tourboard` ENABLE KEYS */;
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