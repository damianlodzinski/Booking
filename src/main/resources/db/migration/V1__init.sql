CREATE TABLE `addition` (
  `type` varchar(255) NOT NULL,
  `descriptionen` varchar(255) DEFAULT NULL,
  `descriptionpl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type`)
);
CREATE TABLE `car` (
  `car_class_alias` varchar(255) NOT NULL,
  `car_class` varchar(255) DEFAULT NULL,
  `example_car_photo` varchar(255) DEFAULT NULL,
  `fuel_type` int NOT NULL,
  `full_model_name` varchar(255) DEFAULT NULL,
  `max_passengers_amount` int NOT NULL,
  PRIMARY KEY (`car_class_alias`)
);
CREATE TABLE `equipment` (
  `type` varchar(255) NOT NULL,
  `descriptionen` varchar(255) DEFAULT NULL,
  `descriptionpl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type`)
);
CREATE TABLE `location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
