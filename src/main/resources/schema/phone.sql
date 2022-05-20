
 Base de datos: `phone`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bills`
--

DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `id_bill` int NOT NULL AUTO_INCREMENT,
  `id_phone_line` int NOT NULL,
  `amount_calls` int NOT NULL,
  `cost` float NOT NULL,
  `total_price` float NOT NULL,
  `bill_date` date NOT NULL,
  `expiration_date` date NOT NULL,
  `paid` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_bill`),
  KEY `fk_bills_id_phone_line` (`id_phone_line`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calls`
--

DROP TABLE IF EXISTS `calls`;
CREATE TABLE IF NOT EXISTS `calls` (
  `id_call` int NOT NULL AUTO_INCREMENT,
  `id_phone_line_origin` int NOT NULL,
  `id_phone_line_destination` int NOT NULL,
  `id_city_origin` int NOT NULL,
  `id_city_destination` int NOT NULL,
  `price_x_minute` float NOT NULL,
  `duration` datetime NOT NULL,
  `total_price` float NOT NULL,
  `id_bill` int DEFAULT NULL,
  PRIMARY KEY (`id_call`),
  KEY `fk_calls_id_phone_line_origin` (`id_phone_line_origin`),
  KEY `fk_calls_id_phone_line_destination` (`id_phone_line_destination`),
  KEY `fk_calls_id_city_origin` (`id_city_origin`),
  KEY `fk_calls_id_city_destination` (`id_city_destination`),
  KEY `fk_calls_id_bill` (`id_bill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `id_city` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(10) NOT NULL,
  `id_state` int NOT NULL,
  PRIMARY KEY (`id_city`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_cities_id_state` (`id_state`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cities`
--

INSERT INTO `cities` (`id_city`, `name`, `code`, `id_state`) VALUES
(1, 'Buenos Aires', '11', 1),
(2, 'Mar del Plata', '223', 1),
(3, 'La Plata', '221', 1),
(4, 'Miramar', '2291', 1),
(5, 'Villa Gesell', '2255', 1),
(6, 'Balcarce', '2266', 1),
(7, 'Catamarca', '3833', 2),
(8, 'Resistencia', '362', 3),
(9, 'Rawson', '280', 4),
(10, 'Córdoba', '351', 5),
(11, 'Corrientes', '379', 6),
(12, 'Paraná', '343', 7),
(13, 'Formosa', '3717', 8),
(14, 'San Salvador de Jujuy', '388', 9),
(15, 'Santa Rosa', '2954', 10),
(16, 'La Rioja', '3822', 11),
(17, 'Mendoza', '261', 12),
(18, 'Posadas', '376', 13),
(19, 'Neuquén', '299', 14),
(20, 'Viedma', '2920', 15),
(21, 'Salta', '387', 16),
(22, 'San Juan', '264', 17),
(23, 'San Luis', '2652', 18),
(24, 'Río Gallegos', '2966', 19),
(25, 'Santa Fé', '342', 20),
(26, 'Santiago del Estero', '385', 21),
(27, 'Ushuaia', '2901', 22),
(28, 'San Miguel de Tucumán', '381', 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `phone_lines`
--

DROP TABLE IF EXISTS `phone_lines`;
CREATE TABLE IF NOT EXISTS `phone_lines` (
  `id_phone_line` int NOT NULL AUTO_INCREMENT,
  `number` varchar(50) NOT NULL,
  `valid` tinyint NOT NULL DEFAULT '1',
  `type_line` enum('Home','Movile') DEFAULT NULL,
  `.` int DEFAULT NULL,
  PRIMARY KEY (`id_phone_line`),
  UNIQUE KEY `number` (`number`),
  KEY `fk_phone_lines_id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `states`
--

DROP TABLE IF EXISTS `states`;
CREATE TABLE IF NOT EXISTS `states` (
  `id_state` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id_state`),
  UNIQUE KEY `name` (`name`)
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
-- Estructura de tabla para la tabla `tariff`
--

DROP TABLE IF EXISTS `tariff`;
CREATE TABLE IF NOT EXISTS `tariff` (
  `id_tariff` int NOT NULL AUTO_INCREMENT,
  `id_city_origin` int NOT NULL,
  `id_city_destination` int NOT NULL,
  `hour_initial` datetime NOT NULL,
  `hour_finished` datetime NOT NULL,
  `price_x_minute` float NOT NULL,
  PRIMARY KEY (`id_tariff`),
  KEY `fk_tarrifs_id_city_origin` (`id_city_origin`),
  KEY `fk_tarrifs_id_city_destination` (`id_city_destination`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `dni` int NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `valid` tinyint NOT NULL DEFAULT '1',
  `type_user` enum('client','Adimn','Employee') DEFAULT NULL,
  `id_city` int NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `last_name` (`last_name`),
  UNIQUE KEY `dni` (`dni`),
  KEY `fk_user_id_city` (`id_city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
