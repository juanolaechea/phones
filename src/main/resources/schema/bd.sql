Estructura de tabla para la tabla `bands`
--

DROP TABLE IF EXISTS `bands`;
CREATE TABLE IF NOT EXISTS `bands` (
  `id_band` int NOT NULL AUTO_INCREMENT,
  `hour_finished` int DEFAULT NULL,
  `hour_initial` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `fk_tariff` int NOT NULL,
  PRIMARY KEY (`id_band`),
  KEY `FKr7ngfspjqks1sme4ggnp0f6ni` (`fk_tariff`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `bands`
--

INSERT INTO `bands` (`id_band`, `hour_finished`, `hour_initial`, `price`, `fk_tariff`) VALUES
(1, 12, 8, 0.2, 2),
(2, 16, 12, 0.2, 1),
(3, 16, 20, 0.3, 1),
(4, 20, 24, 0.3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bills`
--

DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `id_bill` int NOT NULL AUTO_INCREMENT,
  `amount_calls` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `total_cost` float DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `paid` bit(1) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `client_id_client` int DEFAULT NULL,
  `phone_line_id_line` int DEFAULT NULL,
  PRIMARY KEY (`id_bill`),
  KEY `FKktdq06n2vq9siy7o2j9g7xtls` (`client_id_client`),
  KEY `FKq03lp0dgyu7g1vjreunsw8o0y` (`phone_line_id_line`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calls`
--

DROP TABLE IF EXISTS `calls`;
CREATE TABLE IF NOT EXISTS `calls` (
  `id_call` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `invoice` bit(1) DEFAULT NULL,
  `price_x_minute` float DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `id_city_destination` int DEFAULT NULL,
  `id_city_origin` int DEFAULT NULL,
  `client_id_client` int DEFAULT NULL,
  `id_line_destination` int DEFAULT NULL,
  `id_line_origin` int DEFAULT NULL,
  PRIMARY KEY (`id_call`),
  KEY `FKo8wr4bsq6fnqwj68u4nxeia4n` (`id_city_destination`),
  KEY `FK5qf3q7sdjb6nbjgwq8e9r4yfx` (`id_city_origin`),
  KEY `FK7pwwgeqvh0d1nrh1t7dy0n4hc` (`client_id_client`),
  KEY `FKlsgfynjsnud5haien5tg97n85` (`id_line_destination`),
  KEY `FKbeodl9vmvh8t6ypvxaytikmf3` (`id_line_origin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `id_city` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fk_state` int NOT NULL,
  PRIMARY KEY (`id_city`),
  KEY `FK9v0kprkhoif3slcykg919dgax` (`fk_state`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cities`
--

INSERT INTO `cities` (`id_city`, `code`, `name`, `fk_state`) VALUES
(1, '11', 'Buenos Aires', 1),
(2, '223', 'Mar del Plata', 1),
(3, '221', 'La Plata', 1),
(4, '2291', 'Miramar', 1),
(5, '2255', 'Villa Gesell', 1),
(6, '2266', 'Balcarce', 1),
(7, '3833', 'Catamarca', 2),
(8, '362', 'Resistencia', 3),
(9, '280', 'Rawson', 4),
(10, '351', 'Córdoba', 5),
(11, '379', 'Corrientes', 6),
(12, '343', 'Paraná', 7),
(13, '3717', 'Formosa', 8),
(14, '388', 'San Salvador de Jujuy', 9),
(15, '2954', 'Santa Rosa', 10),
(16, '3822', 'La Rioja', 11),
(17, '261', 'Mendoza', 12),
(18, '376', 'Posadas', 13),
(19, '299', 'Neuquén', 14),
(20, '2920', 'Viedma', 15),
(21, '387', 'Salta', 16),
(22, '264', 'San Juan', 17),
(23, '2652', 'San Luis', 18),
(24, '2966', 'Río Gallegos', 19),
(25, '342', 'Santa Fé', 20),
(26, '385', 'Santiago del Estero', 21),
(27, '2901', 'Ushuaia', 22),
(28, '381', 'San Miguel de Tucumán', 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `dni` int DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type_user` varchar(255) DEFAULT NULL,
  `id_city` int DEFAULT NULL,
  `phone_line_id_line` int DEFAULT NULL,
  `user_id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_client`),
  KEY `FKcpijpn687a00bn9reqigwg9ng` (`id_city`),
  KEY `FK90ou39ey4enh5m27seswpohdh` (`phone_line_id_line`),
  KEY `FK32uopqv0xij6xon2xo3ei7pjb` (`user_id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `clients`
--

INSERT INTO `clients` (`id_client`, `dni`, `last_name`, `name`, `type_user`, `id_city`, `phone_line_id_line`, `user_id_user`) VALUES
(1, 26107876, 'Rodriguez', 'Martin', 'client', 2, 1, 3),
(2, 36107876, 'Olaechea', 'Juan', 'client', 1, 2, 4),
(3, 37107878, 'Olaechea', 'Imanol', 'client', 9, 3, 5),
(4, 40107878, 'Senra', 'Angie', 'client', 7, 4, 6),
(5, 45107878, 'Bravi', 'Ailu', 'client', 10, 5, 7),
(6, 30107878, 'Smuglia', 'Belen', 'client', 10, 6, 8),
(7, 36177876, 'Olaechea', 'Lucas', 'client', 14, 7, 9),
(8, 48147876, 'Roncoso', 'Tamara', 'client', 17, 8, 10),
(9, 13147876, 'Tuzzo', 'Etefania', 'client', 17, 9, 11),
(10, 16167876, 'Olaechea', 'Miguel', 'client', 21, 10, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `id_employee` int NOT NULL AUTO_INCREMENT,
  `dni` int DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type_user` varchar(255) DEFAULT NULL,
  `user_id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_employee`),
  KEY `FKdp5ycm3xnx7at20htog74dtgp` (`user_id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `employees`
--

INSERT INTO `employees` (`id_employee`, `dni`, `last_name`, `name`, `type_user`, `user_id_user`) VALUES
(1, 1234, 'Veron', 'Jorge', 'Employee', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `phones_line`
--

DROP TABLE IF EXISTS `phones_line`;
CREATE TABLE IF NOT EXISTS `phones_line` (
  `id_line` int NOT NULL AUTO_INCREMENT,
  `type_line` varchar(255) DEFAULT NULL,
  `number_line` varchar(255) DEFAULT NULL,
  `valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_line`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `phones_line`
--

INSERT INTO `phones_line` (`id_line`, `type_line`, `number_line`, `valid`) VALUES
(1, 'movile', '2236688022', b'1'),
(2, 'movile', '115623489', b'1'),
(3, 'movile', '2806563410', b'1'),
(4, 'movile', '38336563419', b'1'),
(5, 'movile', '3516458902', b'1'),
(6, 'movile', '3514743210', b'1'),
(7, 'home', '3884912457', b'1'),
(8, 'home', '2614914545', b'1'),
(9, 'movile', '2615914549', b'1'),
(10, 'movile', '3875914578', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `states`
--

DROP TABLE IF EXISTS `states`;
CREATE TABLE IF NOT EXISTS `states` (
  `id_state` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_state`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `states`
--

INSERT INTO `states` (`id_state`, `name`) VALUES
(1, 'Buenos Aires'),
(2, 'Catamarca'),
(3, 'Chaco'),
(4, 'Chubut'),
(5, 'Córdoba'),
(6, 'Corrientes'),
(7, 'Entre Ríos'),
(8, 'Formosa'),
(9, 'Jujuy'),
(10, 'La Pampa'),
(11, 'La Rioja'),
(12, 'Mendoza'),
(13, 'Misiones'),
(14, 'Neuquén'),
(15, 'Río Negro'),
(16, 'Salta'),
(17, 'San Juan'),
(18, 'San Luis'),
(19, 'Santa Cruz'),
(20, 'Santa Fe'),
(21, 'Santiago del Estero'),
(22, 'Tierra del Fuego'),
(23, 'Tucumán');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tariffs`
--

DROP TABLE IF EXISTS `tariffs`;
CREATE TABLE IF NOT EXISTS `tariffs` (
  `id_tariff` int NOT NULL AUTO_INCREMENT,
  `price_x_minute` float DEFAULT NULL,
  `id_city_destination` int DEFAULT NULL,
  `id_city_origin` int DEFAULT NULL,
  PRIMARY KEY (`id_tariff`),
  KEY `FK18wwiaangweqrxy1hlgp67dfq` (`id_city_destination`),
  KEY `FK3x60aqq9jihvc59ac0bblbgna` (`id_city_origin`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tariffs`
--

INSERT INTO `tariffs` (`id_tariff`, `price_x_minute`, `id_city_destination`, `id_city_origin`) VALUES
(1, 0.3, 1, 1),
(2, 0.4, 2, 2),
(3, 0.4, 9, 9),
(4, 0.4, 7, 7),
(5, 0.4, 10, 10),
(6, 0.4, 14, 14),
(7, 0.4, 17, 17),
(8, 0.4, 21, 21),
(9, 0.7, 2, 1),
(10, 0.8, 9, 1),
(11, 0.9, 7, 1),
(12, 1, 10, 1),
(13, 0.7, 14, 1),
(14, 0.6, 17, 1),
(15, 0.9, 21, 1),
(16, 0.5, 1, 2),
(17, 0.6, 9, 2),
(18, 0.7, 7, 2),
(19, 0.8, 10, 2),
(20, 1.2, 14, 2),
(21, 1.4, 17, 2),
(22, 0.9, 21, 2),
(23, 0.6, 1, 7),
(24, 0.5, 2, 7),
(25, 0.8, 9, 7),
(26, 0.9, 10, 7),
(27, 1.5, 14, 7),
(28, 1, 17, 7),
(29, 0.8, 21, 7),
(30, 1.8, 1, 9),
(31, 0.7, 2, 9),
(32, 0.9, 7, 9),
(33, 0.6, 10, 9),
(34, 1.1, 14, 9),
(35, 1.2, 17, 9),
(36, 0.8, 21, 9),
(37, 0.7, 1, 10),
(38, 0.6, 2, 10),
(39, 0.5, 7, 10),
(40, 0.9, 9, 10),
(41, 1.1, 14, 10),
(42, 1, 17, 10),
(43, 0.3, 21, 10),
(44, 1.4, 1, 14),
(45, 1.6, 9, 14),
(46, 0.3, 7, 14),
(47, 0.8, 10, 14),
(48, 2, 2, 14),
(49, 3.4, 17, 14),
(50, 0.5, 21, 14),
(51, 0.5, 1, 17),
(52, 0.6, 2, 17),
(53, 0.8, 7, 17),
(54, 0.7, 9, 17),
(55, 3.2, 10, 17),
(56, 1, 14, 17),
(57, 0.4, 21, 17),
(58, 0.5, 1, 21),
(59, 0.6, 2, 21),
(60, 0.7, 7, 21),
(61, 0.8, 9, 21),
(62, 1.2, 10, 21),
(63, 1.4, 14, 21),
(64, 0.9, 17, 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `type_user` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id_user`, `password`, `type_user`, `username`) VALUES
(1, 'a', 'Employee', 'admin'),
(2, 'a', 'antenna', 'antenna'),
(3, 'a', 'client', 'martin'),
(4, 'a', 'client', 'juan'),
(5, 'a', 'client', 'imanol'),
(6, 'a', 'client', 'angie'),
(7, 'a', 'client', 'ailu'),
(8, 'a', 'client', 'belen'),
(9, 'a', 'client', 'lucas'),
(10, 'a', 'client', 'tamara'),
(11, 'a', 'client', 'estefania'),
(12, 'a', 'client', 'miguel');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bands`
--
ALTER TABLE `bands`
  ADD CONSTRAINT `FKr7ngfspjqks1sme4ggnp0f6ni` FOREIGN KEY (`fk_tariff`) REFERENCES `tariffs` (`id_tariff`);

--
-- Filtros para la tabla `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `FKktdq06n2vq9siy7o2j9g7xtls` FOREIGN KEY (`client_id_client`) REFERENCES `clients` (`id_client`),
  ADD CONSTRAINT `FKq03lp0dgyu7g1vjreunsw8o0y` FOREIGN KEY (`phone_line_id_line`) REFERENCES `phones_line` (`id_line`);

--
-- Filtros para la tabla `calls`
--
ALTER TABLE `calls`
  ADD CONSTRAINT `FK5qf3q7sdjb6nbjgwq8e9r4yfx` FOREIGN KEY (`id_city_origin`) REFERENCES `cities` (`id_city`),
  ADD CONSTRAINT `FK7pwwgeqvh0d1nrh1t7dy0n4hc` FOREIGN KEY (`client_id_client`) REFERENCES `clients` (`id_client`),
  ADD CONSTRAINT `FKbeodl9vmvh8t6ypvxaytikmf3` FOREIGN KEY (`id_line_origin`) REFERENCES `phones_line` (`id_line`),
  ADD CONSTRAINT `FKlsgfynjsnud5haien5tg97n85` FOREIGN KEY (`id_line_destination`) REFERENCES `phones_line` (`id_line`),
  ADD CONSTRAINT `FKo8wr4bsq6fnqwj68u4nxeia4n` FOREIGN KEY (`id_city_destination`) REFERENCES `cities` (`id_city`);

--
-- Filtros para la tabla `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `FK9v0kprkhoif3slcykg919dgax` FOREIGN KEY (`fk_state`) REFERENCES `states` (`id_state`);

--
-- Filtros para la tabla `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `FK32uopqv0xij6xon2xo3ei7pjb` FOREIGN KEY (`user_id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `FK90ou39ey4enh5m27seswpohdh` FOREIGN KEY (`phone_line_id_line`) REFERENCES `phones_line` (`id_line`),
  ADD CONSTRAINT `FKcpijpn687a00bn9reqigwg9ng` FOREIGN KEY (`id_city`) REFERENCES `cities` (`id_city`);

--
-- Filtros para la tabla `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `FKdp5ycm3xnx7at20htog74dtgp` FOREIGN KEY (`user_id_user`) REFERENCES `users` (`id_user`);

--
-- Filtros para la tabla `tariffs`
--
ALTER TABLE `tariffs`
  ADD CONSTRAINT `FK18wwiaangweqrxy1hlgp67dfq` FOREIGN KEY (`id_city_destination`) REFERENCES `cities` (`id_city`),
  ADD CONSTRAINT `FK3x60aqq9jihvc59ac0bblbgna` FOREIGN KEY (`id_city_origin`) REFERENCES `cities` (`id_city`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
