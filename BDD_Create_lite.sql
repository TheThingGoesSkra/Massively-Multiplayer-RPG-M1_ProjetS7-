PRAGMA foreign_key_checks = 0;

CREATE TABLE `bonus` (
  `idbonus` varchar(42) NOT NULL,
  `name` varchar(42) DEFAULT NULL,
  `life` varchar(42) DEFAULT NULL,
  `attack` varchar(42) DEFAULT NULL,
  `resilience` varchar(42) DEFAULT NULL,
  `chance` varchar(42) DEFAULT NULL,
  `maxlife` varchar(42) DEFAULT NULL,
  `prbsur10` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`idbonus`)
);

INSERT INTO `bonus` (`idbonus`, `name`, `life`, `attack`, `resilience`, `chance`, `maxlife`, `prbsur10`) VALUES
('0',	'The phylosophalic stone',	'0',	'5',	'5',	'2',	'10',	'1'),
('1',	'Food and drink',	'20',	'0',	'0',	'0',	'0',	'9'),
('2',	'Energizer',	'0',	'1',	'0',	'0',	'0',	'9'),
('3',	'four-leaf clover ',	'0',	'0',	'0',	'2',	'0',	'3'),
('4',	'gold armor',	'0',	'0',	'5',	'0',	'5',	'4'),
('5',	'metal armor',	'0',	'0',	'3',	'0',	'3',	'6'),
('6',	'copper armor',	'0',	'0',	'2',	'0',	'2',	'7'),
('7',	'fire sword ',	'0',	'3',	'0',	'0',	'0',	'6'),
('8',	'diamond sword',	'0',	'5',	'0',	'0',	'0',	'4'),
('9',	'iron sword',	'0',	'2',	'0',	'0',	'0',	'8');

CREATE TABLE `bonusestdanslabyrinth` (
  `idbonus` varchar(42) NOT NULL,
  `idlabyrinth` varchar(42) NOT NULL,
  PRIMARY KEY (`idbonus`,`idlabyrinth`),
  FOREIGN KEY (`idlabyrinth`) REFERENCES `labyrinth` (`idlabyrinth`),
  FOREIGN KEY (`idbonus`) REFERENCES `bonus` (`idbonus`)
) ;

INSERT INTO `bonusestdanslabyrinth` (`idbonus`, `idlabyrinth`) VALUES
('0',	'0'),
('1',	'0'),
('2',	'0'),
('3',	'0'),
('4',	'0'),
('5',	'0'),
('6',	'0'),
('7',	'0'),
('8',	'0'),
('9',	'0');

CREATE TABLE `classe` (
  `idclasse` varchar(42) NOT NULL,
  `name` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`idclasse`)
) ;

INSERT INTO `classe` (`idclasse`, `name`) VALUES
('0',	'guerrier');

CREATE TABLE `hall` (
  `idhall` varchar(42) NOT NULL,
  `name` varchar(42) DEFAULT NULL,
  `idtypehall` varchar(42) DEFAULT NULL,
  `idlabyrinth` varchar(42) DEFAULT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  PRIMARY KEY (`idhall`),
  FOREIGN KEY (`idtypehall`) REFERENCES `typehall` (`idtypehall`),
  FOREIGN KEY (`idlabyrinth`) REFERENCES `labyrinth` (`idlabyrinth`)
) ;

INSERT INTO `hall` (`idhall`, `name`, `idtypehall`, `idlabyrinth`, `x`, `y`) VALUES
('0',	'Forest entrance ',	'0',	'0',	2,	1),
('1',	'Forest entrance',	'0',	'0',	2,	2),
('10',	'Wormhole ',	'2',	'0',	1,	5),
('11',	'Wormhole',	'2',	'0',	1,	6),
('12',	'Wormhole',	'2',	'0',	2,	6),
('13',	'Wormhole',	'2',	'0',	3,	6),
('14',	'Wormhole',	'3',	'0',	4,	6),
('15',	'Wormhole',	'3',	'0',	5,	6),
('16',	'Wormhole',	'3',	'0',	6,	6),
('17',	'Wormhole',	'3',	'0',	6,	5),
('18',	'Tresor Room',	'1',	'0',	4,	2),
('19',	'Tresor Room',	'2',	'0',	4,	1),
('2',	'enchanted path',	'0',	'0',	2,	3),
('20',	'Tresor Room',	'2',	'0',	5,	2),
('21',	'Tresor Room',	'2',	'0',	6,	2),
('22',	'Tresor Room',	'3',	'0',	6,	3),
('23',	'Tresor Room',	'3',	'0',	6,	1),
('3',	'enchanted path',	'0',	'0',	3,	3),
('4',	'enchanted path',	'1',	'0',	1,	3),
('5',	'enchanted path',	'0',	'0',	4,	3),
('6',	'enchanted path',	'1',	'0',	1,	4),
('7',	'enchanted path',	'1',	'0',	2,	4),
('8',	'enchanted path',	'1',	'0',	3,	4),
('9',	'enchanted path',	'0',	'0',	4,	4);

CREATE TABLE `hallestcompose` (
  `idhall` varchar(42) NOT NULL,
  `idporte` varchar(42) NOT NULL,
  `direction` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`idhall`,`idporte`),
  FOREIGN KEY (`idporte`) REFERENCES `porte` (`idporte`),
  FOREIGN KEY (`idhall`) REFERENCES `hall` (`idhall`)
) ;

INSERT INTO `hallestcompose` (`idhall`, `idporte`, `direction`) VALUES
('0',	'0',	'South'),
('1',	'0',	'North'),
('1',	'1',	'South'),
('10',	'21',	'South'),
('10',	'6',	'North'),
('11',	'21',	'North'),
('11',	'22',	'West'),
('12',	'10',	'West'),
('12',	'22',	'East'),
('13',	'10',	'East'),
('13',	'11',	'West'),
('14',	'11',	'East'),
('14',	'12',	'West'),
('15',	'12',	'East'),
('15',	'13',	'West'),
('16',	'13',	'East'),
('16',	'14',	'North'),
('17',	'14',	'South'),
('18',	'15',	'South'),
('18',	'16',	'West'),
('18',	'17',	'North'),
('19',	'17',	'South'),
('2',	'1',	'North'),
('2',	'3',	'West'),
('2',	'4',	'East'),
('20',	'16',	'East'),
('20',	'18',	'West'),
('21',	'18',	'East'),
('21',	'19',	'North'),
('21',	'20',	'South'),
('22',	'20',	'North'),
('23',	'19',	'South'),
('3',	'2',	'West'),
('3',	'3',	'East'),
('4',	'4',	'West'),
('4',	'5',	'South'),
('5',	'15',	'North'),
('5',	'2',	'East'),
('5',	'23',	'South'),
('6',	'5',	'North'),
('6',	'6',	'South'),
('6',	'7',	'West'),
('7',	'7',	'East'),
('7',	'8',	'West'),
('8',	'8',	'East'),
('8',	'9',	'West'),
('9',	'23',	'North'),
('9',	'9',	'East');

CREATE TABLE `labyrinth` (
  `idlabyrinth` varchar(42) NOT NULL,
  `name` varchar(42) DEFAULT NULL,
  `idmonstre` varchar(42) DEFAULT NULL,
  `idhall` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`idlabyrinth`),
  FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idmonstre`),
  FOREIGN KEY (`idhall`) REFERENCES `hall` (`idhall`)
) ;

INSERT INTO `labyrinth` (`idlabyrinth`, `name`, `idmonstre`, `idhall`) VALUES
('0',	'The Nebias labyrinth',	'3',	'0');

CREATE TABLE `monstre` (
  `idmonstre` varchar(42) NOT NULL,
  `name` varchar(42) DEFAULT NULL,
  `life` varchar(42) DEFAULT NULL,
  `attaque` varchar(42) DEFAULT NULL,
  `resistance` varchar(42) DEFAULT NULL,
  `chance` varchar(42) DEFAULT NULL,
  `boss` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`idmonstre`)
) ;

INSERT INTO `monstre` (`idmonstre`, `name`, `life`, `attaque`, `resistance`, `chance`, `boss`) VALUES
('0',	'Bat',	'5',	'1',	'1',	'1',	'0'),
('1',	'Worm',	'10',	'2',	'1',	'1',	'0'),
('2',	'Boar',	'15',	'2',	'2',	'1',	'0'),
('3',	'Evil tree',	'30',	'4',	'3',	'2',	'1'),
('4',	'Warrior gargoyle',	'60',	'10',	'10',	'1',	'1');

CREATE TABLE `monstredistribue` (
  `idmonstre` varchar(42) NOT NULL,
  `idbonus` varchar(42) NOT NULL,
  PRIMARY KEY (`idmonstre`,`idbonus`),
  FOREIGN KEY (`idbonus`) REFERENCES `bonus` (`idbonus`),
  FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idmonstre`)
) ;

INSERT INTO `monstredistribue` (`idmonstre`, `idbonus`) VALUES
('4',	'0'),
('0',	'1'),
('1',	'1'),
('2',	'1'),
('1',	'2'),
('3',	'3'),
('4',	'4'),
('2',	'5'),
('0',	'6'),
('0',	'7'),
('3',	'8'),
('2',	'9');

CREATE TABLE `monstreestdans` (
  `idmonstre` varchar(42) NOT NULL,
  `idtypehall` varchar(42) NOT NULL,
  PRIMARY KEY (`idmonstre`,`idtypehall`),
  FOREIGN KEY (`idtypehall`) REFERENCES `typehall` (`idtypehall`),
  FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idmonstre`)
) ;

INSERT INTO `monstreestdans` (`idmonstre`, `idtypehall`) VALUES
('0',	'0'),
('1',	'1'),
('2',	'1'),
('2',	'2'),
('3',	'3'),
('4',	'3');

CREATE TABLE `monstreestdanslabyrinth` (
  `idmonstre` varchar(42) NOT NULL,
  `idlabyrinth` varchar(42) NOT NULL,
  PRIMARY KEY (`idmonstre`,`idlabyrinth`),
  FOREIGN KEY (`idlabyrinth`) REFERENCES `labyrinth` (`idlabyrinth`),
  FOREIGN KEY (`idmonstre`) REFERENCES `monstre` (`idmonstre`)
) ;

INSERT INTO `monstreestdanslabyrinth` (`idmonstre`, `idlabyrinth`) VALUES
('0',	'0'),
('1',	'0'),
('2',	'0'),
('3',	'0'),
('4',	'0');

CREATE TABLE `player` (
  `idplayer` varchar(42) NOT NULL,
  `life` varchar(42) DEFAULT NULL,
  `attaque` varchar(42) DEFAULT NULL,
  `resistance` varchar(42) DEFAULT NULL,
  `chance` varchar(42) DEFAULT NULL,
  `idclasse` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`idplayer`),
  FOREIGN KEY (`idclasse`) REFERENCES `classe` (`idclasse`)
) ;

INSERT INTO `player` (`idplayer`, `life`, `attaque`, `resistance`, `chance`, `idclasse`) VALUES
('Antonio',	'40',	'1',	'1',	'1',	'0'),
('Chupacabra',	'40',	'1',	'1',	'1',	'0'),
('Ramirez',	'40',	'1',	'1',	'1',	'0'),
('Will le barge',	'10',	'1',	'1',	'1',	'0');

CREATE TABLE `playerdispose` (
  `idplayer` varchar(42) NOT NULL,
  `idbonus` varchar(42) NOT NULL,
  PRIMARY KEY (`idplayer`,`idbonus`),
  FOREIGN KEY (`idbonus`) REFERENCES `bonus` (`idbonus`),
  FOREIGN KEY (`idplayer`) REFERENCES `player` (`idplayer`)
) ;

INSERT INTO `playerdispose` (`idplayer`, `idbonus`) VALUES
('Chupacabra',	'0'),
('Chupacabra',	'1'),
('Chupacabra',	'2'),
('Chupacabra',	'3'),
('Chupacabra',	'4'),
('Chupacabra',	'5'),
('Chupacabra',	'6'),
('Chupacabra',	'7'),
('Chupacabra',	'8'),
('Chupacabra',	'9');

CREATE TABLE `porte` (
  `idporte` varchar(42) NOT NULL,
  `idlabyrinth` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`idporte`),
  FOREIGN KEY (`idlabyrinth`) REFERENCES `labyrinth` (`idlabyrinth`)
) ;

INSERT INTO `porte` (`idporte`, `idlabyrinth`) VALUES
('0',	'0'),
('1',	'0'),
('10',	'0'),
('11',	'0'),
('12',	'0'),
('13',	'0'),
('14',	'0'),
('15',	'0'),
('16',	'0'),
('17',	'0'),
('18',	'0'),
('19',	'0'),
('2',	'0'),
('20',	'0'),
('21',	'0'),
('22',	'0'),
('23',	'0'),
('3',	'0'),
('4',	'0'),
('5',	'0'),
('6',	'0'),
('7',	'0'),
('8',	'0'),
('9',	'0');

CREATE TABLE `session` (
  `idplayer` varchar(42) NOT NULL,
  `idlabyrinth` varchar(42) DEFAULT NULL,
  `idhall` varchar(42) DEFAULT NULL,
  `life` int(11) DEFAULT NULL,
  PRIMARY KEY (`idplayer`),
  FOREIGN KEY (`idlabyrinth`) REFERENCES `labyrinth` (`idlabyrinth`),
  FOREIGN KEY (`idplayer`) REFERENCES `player` (`idplayer`),
  FOREIGN KEY (`idhall`) REFERENCES `hall` (`idhall`)
) ;

INSERT INTO `session` (`idplayer`, `idlabyrinth`, `idhall`, `life`) VALUES
('Chupacabra',	'0',	'0',	40),
('Ramirez',	'0',	'1',	40);

CREATE TABLE `typehall` (
  `idtypehall` varchar(42) NOT NULL,
  PRIMARY KEY (`idtypehall`)
) ;

INSERT INTO `typehall` (`idtypehall`) VALUES
('0'),
('1'),
('2'),
('3');