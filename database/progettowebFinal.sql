-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Lug 25, 2017 alle 17:48
-- Versione del server: 5.5.55-0+deb8u1
-- PHP Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `phpmyadmin`
--
DROP DATABASE `phpmyadmin`;
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Struttura della tabella `pma__bookmark`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__bookmark`;
CREATE TABLE IF NOT EXISTS `pma__bookmark` (
`id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

--
-- Svuota la tabella prima dell'inserimento `pma__bookmark`
--

TRUNCATE TABLE `pma__bookmark`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__column_info`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__column_info`;
CREATE TABLE IF NOT EXISTS `pma__column_info` (
`id` int(5) unsigned NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

--
-- Svuota la tabella prima dell'inserimento `pma__column_info`
--

TRUNCATE TABLE `pma__column_info`;
--
-- Dump dei dati per la tabella `pma__column_info`
--

INSERT INTO `pma__column_info` (`id`, `db_name`, `table_name`, `column_name`, `comment`, `mimetype`, `transformation`, `transformation_options`) VALUES
(6, 'progettoweb', 'Assistenza', 'dataApertura', '', '', '_', ''),
(7, 'progettoweb', 'Assistenza', 'dataChiusura', '', '', '_', ''),
(8, 'progettoweb', 'Oggetto', 'statoDisponibilita', '', '', '_', ''),
(10, 'progettoweb', 'imageNegozio', 'src', '', '', '_', ''),
(11, 'progettoweb', 'imageOggetto', 'src', '', '', '_', ''),
(12, 'progettoweb', 'imageRecensione', 'src', '', '', '_', ''),
(13, 'progettoweb', 'imageUtente', 'src', '', '', '_', ''),
(14, 'progettoweb', 'Negozio', 'dataApertura', '', '', '_', ''),
(16, 'progettoweb', 'Oggetto', 'id', '', '', '_', ''),
(17, 'progettoweb', 'Oggetto', 'nomeDownCase', '', '', '_', ''),
(18, 'progettoweb', 'Oggetto', 'dataFineSconto', '', '', '_', ''),
(19, 'progettoweb', 'RecensioneVenditore', 'id', '', '', '_', ''),
(20, 'progettoweb', 'RecensioneNegozio', 'id', '', '', '_', '');

-- --------------------------------------------------------

--
-- Struttura della tabella `pma__designer_coords`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__designer_coords`;
CREATE TABLE IF NOT EXISTS `pma__designer_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `v` tinyint(4) DEFAULT NULL,
  `h` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for Designer';

--
-- Svuota la tabella prima dell'inserimento `pma__designer_coords`
--

TRUNCATE TABLE `pma__designer_coords`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__favorite`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__favorite`;
CREATE TABLE IF NOT EXISTS `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

--
-- Svuota la tabella prima dell'inserimento `pma__favorite`
--

TRUNCATE TABLE `pma__favorite`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__history`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__history`;
CREATE TABLE IF NOT EXISTS `pma__history` (
`id` bigint(20) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

--
-- Svuota la tabella prima dell'inserimento `pma__history`
--

TRUNCATE TABLE `pma__history`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__navigationhiding`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__navigationhiding`;
CREATE TABLE IF NOT EXISTS `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

--
-- Svuota la tabella prima dell'inserimento `pma__navigationhiding`
--

TRUNCATE TABLE `pma__navigationhiding`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__pdf_pages`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__pdf_pages`;
CREATE TABLE IF NOT EXISTS `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
`page_nr` int(10) unsigned NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

--
-- Svuota la tabella prima dell'inserimento `pma__pdf_pages`
--

TRUNCATE TABLE `pma__pdf_pages`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__recent`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__recent`;
CREATE TABLE IF NOT EXISTS `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Svuota la tabella prima dell'inserimento `pma__recent`
--

TRUNCATE TABLE `pma__recent`;
--
-- Dump dei dati per la tabella `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('progettoweb', '[{"db":"progettoweb","table":"Categoria"},{"db":"progettoweb","table":"Indirizzo"},{"db":"progettoweb","table":"Negozio"},{"db":"progettoweb","table":"Utente"},{"db":"progettoweb","table":"Assistenza"},{"db":"progettoweb","table":"Ordine"},{"db":"progettoweb","table":"IndirizzoUtente"},{"db":"progettoweb","table":"RecensioneVenditore"},{"db":"progettoweb","table":"RecensioneOggetto"},{"db":"progettoweb","table":"RecensioneNegozio"}]'),
('root', '[{"db":"progettoweb","table":"Utente"}]');

-- --------------------------------------------------------

--
-- Struttura della tabella `pma__relation`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__relation`;
CREATE TABLE IF NOT EXISTS `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

--
-- Svuota la tabella prima dell'inserimento `pma__relation`
--

TRUNCATE TABLE `pma__relation`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__savedsearches`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__savedsearches`;
CREATE TABLE IF NOT EXISTS `pma__savedsearches` (
`id` int(5) unsigned NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

--
-- Svuota la tabella prima dell'inserimento `pma__savedsearches`
--

TRUNCATE TABLE `pma__savedsearches`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__table_coords`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__table_coords`;
CREATE TABLE IF NOT EXISTS `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float unsigned NOT NULL DEFAULT '0',
  `y` float unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

--
-- Svuota la tabella prima dell'inserimento `pma__table_coords`
--

TRUNCATE TABLE `pma__table_coords`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__table_info`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__table_info`;
CREATE TABLE IF NOT EXISTS `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

--
-- Svuota la tabella prima dell'inserimento `pma__table_info`
--

TRUNCATE TABLE `pma__table_info`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__table_uiprefs`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__table_uiprefs`;
CREATE TABLE IF NOT EXISTS `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

--
-- Svuota la tabella prima dell'inserimento `pma__table_uiprefs`
--

TRUNCATE TABLE `pma__table_uiprefs`;
--
-- Dump dei dati per la tabella `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('progettoweb', 'progettoweb', 'Oggetto', '{"CREATE_TIME":"2017-07-23 12:04:51","col_visib":["1","1","1","1","1","1","1","1","1","1","1","1"]}', '2017-07-24 14:30:58'),
('progettoweb', 'progettoweb', 'Ordine', '{"sorted_col":"Ordine.dataOrdine DESC"}', '2017-07-19 08:53:10'),
('progettoweb', 'progettoweb', 'RecensioneOggetto', '{"sorted_col":"RecensioneOggetto.utilita DESC"}', '2017-07-25 07:48:19');

-- --------------------------------------------------------

--
-- Struttura della tabella `pma__tracking`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__tracking`;
CREATE TABLE IF NOT EXISTS `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) unsigned NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

--
-- Svuota la tabella prima dell'inserimento `pma__tracking`
--

TRUNCATE TABLE `pma__tracking`;
--
-- Dump dei dati per la tabella `pma__tracking`
--

INSERT INTO `pma__tracking` (`db_name`, `table_name`, `version`, `date_created`, `date_updated`, `schema_snapshot`, `schema_sql`, `data_sql`, `tracking`, `tracking_active`) VALUES
('ProgettoWebSegmentationFault', 'Amministratore', 1, '2017-07-04 15:24:56', '2017-07-04 15:24:56', 'a:2:{s:7:"COLUMNS";a:6:{i:0;a:8:{s:5:"Field";s:2:"id";s:4:"Type";s:7:"int(11)";s:9:"Collation";N;s:4:"Null";s:2:"NO";s:3:"Key";s:3:"PRI";s:7:"Default";N;s:5:"Extra";s:14:"auto_increment";s:7:"Comment";s:0:"";}i:1;a:8:{s:5:"Field";s:4:"nome";s:4:"Type";s:12:"varchar(255)";s:9:"Collation";s:17:"latin1_swedish_ci";s:4:"Null";s:2:"NO";s:3:"Key";s:0:"";s:7:"Default";N;s:5:"Extra";s:0:"";s:7:"Comment";s:0:"";}i:2;a:8:{s:5:"Field";s:7:"cognome";s:4:"Type";s:12:"varchar(255)";s:9:"Collation";s:17:"latin1_swedish_ci";s:4:"Null";s:2:"NO";s:3:"Key";s:0:"";s:7:"Default";N;s:5:"Extra";s:0:"";s:7:"Comment";s:0:"";}i:3;a:8:{s:5:"Field";s:4:"mail";s:4:"Type";s:12:"varchar(255)";s:9:"Collation";s:17:"latin1_swedish_ci";s:4:"Null";s:2:"NO";s:3:"Key";s:0:"";s:7:"Default";N;s:5:"Extra";s:0:"";s:7:"Comment";s:0:"";}i:4;a:8:{s:5:"Field";s:8:"password";s:4:"Type";s:12:"varchar(255)";s:9:"Collation";s:17:"latin1_swedish_ci";s:4:"Null";s:2:"NO";s:3:"Key";s:0:"";s:7:"Default";N;s:5:"Extra";s:0:"";s:7:"Comment";s:0:"";}i:5;a:8:{s:5:"Field";s:6:"avatar";s:4:"Type";s:12:"varchar(255)";s:9:"Collation";s:17:"latin1_swedish_ci";s:4:"Null";s:3:"YES";s:3:"Key";s:0:"";s:7:"Default";N;s:5:"Extra";s:0:"";s:7:"Comment";s:0:"";}}s:7:"INDEXES";a:2:{i:0;a:13:{s:5:"Table";s:14:"Amministratore";s:10:"Non_unique";s:1:"0";s:8:"Key_name";s:7:"PRIMARY";s:12:"Seq_in_index";s:1:"1";s:11:"Column_name";s:2:"id";s:9:"Collation";s:1:"A";s:11:"Cardinality";s:1:"0";s:8:"Sub_part";N;s:6:"Packed";N;s:4:"Null";s:0:"";s:10:"Index_type";s:5:"BTREE";s:7:"Comment";s:0:"";s:13:"Index_comment";s:0:"";}i:1;a:13:{s:5:"Table";s:14:"Amministratore";s:10:"Non_unique";s:1:"1";s:8:"Key_name";s:21:"idAmministratoreIndex";s:12:"Seq_in_index";s:1:"1";s:11:"Column_name";s:2:"id";s:9:"Collation";s:1:"A";s:11:"Cardinality";s:1:"0";s:8:"Sub_part";N;s:6:"Packed";N;s:4:"Null";s:0:"";s:10:"Index_type";s:5:"BTREE";s:7:"Comment";s:0:"";s:13:"Index_comment";s:0:"";}}}', '# log 2017-07-04 15:24:56 root\nDROP TABLE IF EXISTS `Amministratore`;\n# log 2017-07-04 15:24:56 root\n\nCREATE TABLE `Amministratore` (\n`id` int(11) NOT NULL,\n  `nome` varchar(255) NOT NULL,\n  `cognome` varchar(255) NOT NULL,\n  `mail` varchar(255) NOT NULL,\n  `password` varchar(255) NOT NULL,\n  `avatar` varchar(255) DEFAULT NULL\n) ENGINE=InnoDB DEFAULT CHARSET=latin1;\n', '\n', 'UPDATE,INSERT,DELETE,TRUNCATE,CREATE TABLE,ALTER TABLE,RENAME TABLE,DROP TABLE,CREATE INDEX,DROP INDEX', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `pma__userconfig`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__userconfig`;
CREATE TABLE IF NOT EXISTS `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Svuota la tabella prima dell'inserimento `pma__userconfig`
--

TRUNCATE TABLE `pma__userconfig`;
--
-- Dump dei dati per la tabella `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('progettoweb', '2017-07-25 13:08:45', '{"lang":"it","collation_connection":"utf8mb4_general_ci","fontsize":"67%"}'),
('root', '2017-06-29 12:49:51', '{"collation_connection":"utf8mb4_general_ci","lang":"it"}');

-- --------------------------------------------------------

--
-- Struttura della tabella `pma__usergroups`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__usergroups`;
CREATE TABLE IF NOT EXISTS `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

--
-- Svuota la tabella prima dell'inserimento `pma__usergroups`
--

TRUNCATE TABLE `pma__usergroups`;
-- --------------------------------------------------------

--
-- Struttura della tabella `pma__users`
--
-- Creazione: Giu 23, 2017 alle 11:47
--

DROP TABLE IF EXISTS `pma__users`;
CREATE TABLE IF NOT EXISTS `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Svuota la tabella prima dell'inserimento `pma__users`
--

TRUNCATE TABLE `pma__users`;
--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma__designer_coords`
--
ALTER TABLE `pma__designer_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma__favorite`
--
ALTER TABLE `pma__favorite`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__history`
--
ALTER TABLE `pma__history`
 ADD PRIMARY KEY (`id`), ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
 ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
 ADD PRIMARY KEY (`page_nr`), ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma__recent`
--
ALTER TABLE `pma__recent`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__relation`
--
ALTER TABLE `pma__relation`
 ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`), ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
 ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma__table_info`
--
ALTER TABLE `pma__table_info`
 ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
 ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma__tracking`
--
ALTER TABLE `pma__tracking`
 ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
 ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma__users`
--
ALTER TABLE `pma__users`
 ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `pma__history`
--
ALTER TABLE `pma__history`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
MODIFY `page_nr` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
MODIFY `id` int(5) unsigned NOT NULL AUTO_INCREMENT;--
-- Database: `progettoweb`
--
DROP DATABASE `progettoweb`;
CREATE DATABASE IF NOT EXISTS `progettoweb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `progettoweb`;

-- --------------------------------------------------------

--
-- Struttura della tabella `Assistenza`
--
-- Creazione: Lug 19, 2017 alle 13:23
--

DROP TABLE IF EXISTS `Assistenza`;
CREATE TABLE IF NOT EXISTS `Assistenza` (
`id` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `idVenditore` int(11) DEFAULT NULL,
  `idAmministratore` int(11) NOT NULL,
  `idOrdine` int(11) DEFAULT NULL,
  `idOggetto` varchar(32) DEFAULT NULL,
  `stato` int(11) DEFAULT '0',
  `soluzione` varchar(2500) DEFAULT NULL,
  `dataApertura` datetime NOT NULL,
  `dataChiusura` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `Assistenza`:
--   `idUtente`
--       `Utente` -> `id`
--   `idVenditore`
--       `Utente` -> `id`
--   `idAmministratore`
--       `Utente` -> `id`
--   `idOrdine`
--       `Ordine` -> `idOrdine`
--   `idOggetto`
--       `Oggetto` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `Assistenza`
--

TRUNCATE TABLE `Assistenza`;
--
-- Dump dei dati per la tabella `Assistenza`
--

INSERT INTO `Assistenza` (`id`, `idUtente`, `idVenditore`, `idAmministratore`, `idOrdine`, `idOggetto`, `stato`, `soluzione`, `dataApertura`, `dataChiusura`) VALUES
(1, 1, 6, 1, NULL, NULL, 0, NULL, '0000-00-00 00:00:00', NULL),
(2, 1, NULL, 2, NULL, NULL, 0, NULL, '0000-00-00 00:00:00', NULL),
(3, 1, NULL, 3, NULL, NULL, 0, NULL, '0000-00-00 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `Carrello`
--
-- Creazione: Lug 19, 2017 alle 13:38
--

DROP TABLE IF EXISTS `Carrello`;
CREATE TABLE IF NOT EXISTS `Carrello` (
  `idUtente` int(11) NOT NULL,
  `idOrdine` int(11) NOT NULL,
  `subTotal` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `Carrello`:
--   `idUtente`
--       `Utente` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `Carrello`
--

TRUNCATE TABLE `Carrello`;
--
-- Dump dei dati per la tabella `Carrello`
--

INSERT INTO `Carrello` (`idUtente`, `idOrdine`, `subTotal`) VALUES
(1, 1, 850),
(2, 3, 100),
(3, 3, 300);

-- --------------------------------------------------------

--
-- Struttura della tabella `Categoria`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `Categoria`;
CREATE TABLE IF NOT EXISTS `Categoria` (
`id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `oggettiPresenti` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Categoria`
--

TRUNCATE TABLE `Categoria`;
--
-- Dump dei dati per la tabella `Categoria`
--

INSERT INTO `Categoria` (`id`, `nome`, `descrizione`, `oggettiPresenti`) VALUES
(1, 'Elettronica', 'Materiale Elettronico', 3),
(2, 'Casa', 'Materiale per Casa e Cucina', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `Indirizzo`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `Indirizzo`;
CREATE TABLE IF NOT EXISTS `Indirizzo` (
`idI` int(11) NOT NULL,
  `stato` varchar(255) NOT NULL,
  `regione` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `citta` varchar(255) NOT NULL,
  `via` varchar(255) NOT NULL,
  `nCivico` int(11) NOT NULL,
  `interno` int(11) DEFAULT NULL,
  `latitudine` double DEFAULT NULL,
  `longitudine` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Indirizzo`
--

TRUNCATE TABLE `Indirizzo`;
--
-- Dump dei dati per la tabella `Indirizzo`
--

INSERT INTO `Indirizzo` (`idI`, `stato`, `regione`, `provincia`, `citta`, `via`, `nCivico`, `interno`, `latitudine`, `longitudine`) VALUES
(1, 'Italia', 'Veneto', 'Treviso', 'Paese', 'Via Paese', 4, 1, NULL, NULL),
(2, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via roma', 4, 2, NULL, NULL),
(3, 'Italia', 'Veneto', 'Padova', 'Cittadella', 'Via einaudi', 4, 3, NULL, NULL),
(4, 'Italia', 'Lombardia', 'Milano', 'Milano', 'Via vittorio emanuele', 4, 4, NULL, NULL),
(5, 'Italia', 'Lombardia', 'Brescia', 'Brescia', 'Via cristo', 4, 1, NULL, NULL),
(6, 'Italia', 'Lazio', 'Roma', 'Roma', 'Via garibaldi', 4, 2, NULL, NULL),
(7, 'Italia', 'Piemonte', 'Torino', 'Torino', 'Via Matteo', 4, 3, NULL, NULL),
(8, 'Italia', 'Trentino Alto Adige', 'Trento', 'Trento', 'Via Roma', 4, 4, NULL, NULL),
(9, 'Italia', 'Trentino Alto Adige', 'Trento', 'Povo', 'Via Sommarive', 4, 5, NULL, NULL),
(10, 'Italia', 'Lombardia', 'Bergamo', 'Bergamo', 'Via Rose', 4, 6, NULL, NULL),
(11, 'Italia', 'Veneto', 'Treviso', 'Paese', 'Via Paese', 5, 1, NULL, NULL),
(12, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via roma', 5, 2, NULL, NULL),
(13, 'Italia', 'Veneto', 'Padova', 'Cittadella', 'Via einaudi', 5, 3, NULL, NULL),
(14, 'Italia', 'Lombardia', 'Milano', 'Milano', 'Via vittorio emanuele', 5, 4, 45.4642035, 9.189982),
(15, 'Italia', 'Lombardia', 'Brescia', 'Brescia', 'Via cristo', 5, 5, NULL, NULL),
(16, 'Italia', 'Lazio', 'Roma', 'Roma', 'Via garibaldi', 5, 6, NULL, NULL),
(17, 'Italia', 'Piemonte', 'Torino', 'Torino', 'Via Matteo', 5, 1, NULL, NULL),
(18, 'Italia', 'Trentino Alto Adige', 'Trento', 'Trento', 'Via Roma', 5, 3, NULL, NULL),
(19, 'Italia', 'Trentino Alto Adige', 'Trento', 'Povo', 'Via Sommarive', 5, 4, NULL, NULL),
(20, 'Italia', 'Lombardia', 'Bergamo', 'Bergamo', 'Via Rose', 5, 5, NULL, NULL),
(21, 'italia', 'veneto', 'vicenza', 'bassano del grappa', 'via roma', 26, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `IndirizzoUtente`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `IndirizzoUtente`;
CREATE TABLE IF NOT EXISTS `IndirizzoUtente` (
  `idI` int(11) NOT NULL,
  `idU` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `IndirizzoUtente`:
--   `idU`
--       `Utente` -> `id`
--   `idI`
--       `Indirizzo` -> `idI`
--

--
-- Svuota la tabella prima dell'inserimento `IndirizzoUtente`
--

TRUNCATE TABLE `IndirizzoUtente`;
--
-- Dump dei dati per la tabella `IndirizzoUtente`
--

INSERT INTO `IndirizzoUtente` (`idI`, `idU`) VALUES
(1, 1),
(21, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `Interessi1`
--
DROP VIEW IF EXISTS `Interessi1`;
CREATE TABLE IF NOT EXISTS `Interessi1` (
`categoria` int(11)
,`prezzoMassimo` double
,`prezzoMinimo` double
);
-- --------------------------------------------------------

--
-- Struttura della tabella `Negozio`
--
-- Creazione: Lug 25, 2017 alle 15:39
--

DROP TABLE IF EXISTS `Negozio`;
CREATE TABLE IF NOT EXISTS `Negozio` (
`id` int(11) NOT NULL,
  `idVenditore` int(11) NOT NULL,
  `nomeNegozio` varchar(255) NOT NULL,
  `valutazione` double DEFAULT NULL,
  `attivo` tinyint(1) NOT NULL DEFAULT '1',
  `idI` int(11) NOT NULL,
  `dataApertura` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `Negozio`:
--   `idVenditore`
--       `Utente` -> `id`
--   `idI`
--       `Indirizzo` -> `idI`
--

--
-- Svuota la tabella prima dell'inserimento `Negozio`
--

TRUNCATE TABLE `Negozio`;
--
-- Dump dei dati per la tabella `Negozio`
--

INSERT INTO `Negozio` (`id`, `idVenditore`, `nomeNegozio`, `valutazione`, `attivo`, `idI`, `dataApertura`) VALUES
(1, 6, 'Mediaworld', NULL, 1, 11, '0000-00-00 00:00:00'),
(2, 7, 'Trony', NULL, 1, 14, '0000-00-00 00:00:00'),
(3, 8, 'Unieuro', NULL, 1, 12, '0000-00-00 00:00:00'),
(4, 9, 'Kasanova', NULL, 1, 13, '0000-00-00 00:00:00'),
(5, 6, 'aliexpress', NULL, 1, 18, '2017-07-18 14:24:07'),
(6, 7, 'Ikea', 8, 0, 15, '2017-07-20 14:02:41');

-- --------------------------------------------------------

--
-- Struttura della tabella `Oggetto`
--
-- Creazione: Lug 24, 2017 alle 14:30
--

DROP TABLE IF EXISTS `Oggetto`;
CREATE TABLE IF NOT EXISTS `Oggetto` (
  `id` varchar(32) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `nome` varchar(500) NOT NULL,
  `nomeDownCase` varchar(500) NOT NULL,
  `prezzo` double NOT NULL,
  `descrizione` varchar(2500) NOT NULL,
  `ritiroInNegozio` tinyint(1) DEFAULT '0',
  `disponibilita` int(11) NOT NULL,
  `statoDisponibilita` int(11) NOT NULL DEFAULT '0',
  `sconto` double DEFAULT '0',
  `dataFineSconto` date DEFAULT NULL,
  `categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `Oggetto`:
--   `idNegozio`
--       `Negozio` -> `id`
--   `categoria`
--       `Categoria` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `Oggetto`
--

TRUNCATE TABLE `Oggetto`;
--
-- Dump dei dati per la tabella `Oggetto`
--

INSERT INTO `Oggetto` (`id`, `idNegozio`, `nome`, `nomeDownCase`, `prezzo`, `descrizione`, `ritiroInNegozio`, `disponibilita`, `statoDisponibilita`, `sconto`, `dataFineSconto`, `categoria`) VALUES
('1', 1, 'iPhone SE', 'iphone se', 400, 'iPhone SE 16GB Bianco', 0, 5, 0, 0, NULL, 1),
('2', 2, 'HTC U11', 'htc u11', 450, 'HTC U11 32GB Bianco', 0, 6, 0, 0, NULL, 1),
('3', 3, 'UAUEI XYZ', 'uauei xyz', 100, 'UAUEI XYZ Telefono inutile', 0, 1, 0, 0, NULL, 1),
('4', 4, 'Pentola Acciaio Inossidabile', 'pentola acciaio inossidabile', 50, 'Pentola Acciaio Inossidabile, insdistrutibile!', 1, 45, 0, 0, '2017-07-27', 2),
('5', 5, 'Earpods', 'earpods', 29, 'Auricolari wire apple', 1, 65, 1, 0, NULL, 1);

--
-- Trigger `Oggetto`
--
DROP TRIGGER IF EXISTS `AggiornamentoOrdiniSeVieneModificatoIlPrezzo`;
DELIMITER //
CREATE TRIGGER `AggiornamentoOrdiniSeVieneModificatoIlPrezzo` AFTER UPDATE ON `Oggetto`
 FOR EACH ROW BEGIN

DECLARE curDate DATETIME DEFAULT NOW(); 


IF(curDate <= new.dataFineSconto)
	THEN
    	UPDATE Ordine
        	SET Ordine.prezzoDiAcquisto = new.prezzo - ((new.prezzo * new.sconto) / 100)
            WHERE Ordine.stato = 0 AND Ordine.idOggetto = new.id AND Ordine.idNegozio = new.idNegozio;
    ELSE
    UPDATE Ordine
        	SET Ordine.prezzoDiAcquisto = new.prezzo
            WHERE Ordine.stato = 0 AND Ordine.idOggetto = new.id AND Ordine.idNegozio = new.idNegozio;

END IF;

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `NomeSenzaLettereMaiuscole`;
DELIMITER //
CREATE TRIGGER `NomeSenzaLettereMaiuscole` AFTER INSERT ON `Oggetto`
 FOR EACH ROW UPDATE Oggetto
	SET nomeDownCase = LOWER(nome)
    WHERE Oggetto.id = new.id
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Ordine`
--
-- Creazione: Lug 19, 2017 alle 09:28
--

DROP TABLE IF EXISTS `Ordine`;
CREATE TABLE IF NOT EXISTS `Ordine` (
`idOrdine` int(11) NOT NULL,
  `idOggetto` varchar(32) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `stato` int(11) NOT NULL DEFAULT '0',
  `quantita` int(11) NOT NULL DEFAULT '1',
  `codiceTracking` varchar(40) DEFAULT NULL,
  `dataArrivoPresunta` date DEFAULT NULL,
  `dataOrdine` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `prezzoDiAcquisto` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `Ordine`:
--   `idOggetto`
--       `Oggetto` -> `id`
--   `idNegozio`
--       `Negozio` -> `id`
--   `idUtente`
--       `Utente` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `Ordine`
--

TRUNCATE TABLE `Ordine`;
--
-- Dump dei dati per la tabella `Ordine`
--

INSERT INTO `Ordine` (`idOrdine`, `idOggetto`, `idNegozio`, `idUtente`, `stato`, `quantita`, `codiceTracking`, `dataArrivoPresunta`, `dataOrdine`, `prezzoDiAcquisto`) VALUES
(1, '1', 1, 1, 0, 1, NULL, NULL, '0000-00-00 00:00:00', 400),
(1, '1', 1, 3, 1, 2, NULL, NULL, '2017-07-21 13:30:46', 200),
(1, '2', 2, 1, 0, 1, NULL, NULL, '0000-00-00 00:00:00', 450),
(2, '2', 2, 3, 1, 1, NULL, NULL, '2017-07-23 09:27:52', 200),
(3, '3', 3, 2, 0, 1, NULL, NULL, '0000-00-00 00:00:00', 100),
(3, '3', 3, 3, 0, 1, NULL, NULL, '2017-07-25 07:53:01', 300),
(3, '4', 4, 4, 5, 1, NULL, NULL, '2017-07-25 07:58:55', 50),
(4, '1', 1, 1, 2, 1, NULL, NULL, '2017-07-19 08:50:45', 500),
(4, '3', 1, 1, 2, 1, NULL, NULL, '2017-07-19 08:50:45', 450),
(4, '4', 2, 1, 2, 1, NULL, NULL, '2017-07-19 08:50:45', 150),
(5, '2', 2, 1, 3, 1, NULL, NULL, '2017-07-19 08:50:45', 320),
(5, '3', 3, 1, 3, 1, NULL, NULL, '2017-07-19 08:50:45', 350),
(6, '1', 1, 1, 3, 1, NULL, NULL, '2017-07-19 08:50:45', 300),
(7, '1', 3, 1, 4, 1, NULL, NULL, '2017-07-19 08:50:45', 250),
(7, '3', 3, 1, 4, 1, NULL, NULL, '2017-07-19 08:50:45', 200),
(8, '1', 1, 1, 4, 1, NULL, NULL, '2017-07-19 08:50:45', 150),
(9, '4', 3, 1, 5, 1, NULL, NULL, '2017-07-19 08:50:45', 100);

--
-- Trigger `Ordine`
--
DROP TRIGGER IF EXISTS `AggiornamentoCarrelloAggiuntaElemento`;
DELIMITER //
CREATE TRIGGER `AggiornamentoCarrelloAggiuntaElemento` AFTER INSERT ON `Ordine`
 FOR EACH ROW UPDATE Carrello c
	SET c.subTotal = c.subTotal + (new.prezzoDiAcquisto * new.quantita)
    WHERE c.idUtente = new.idUtente AND new.stato = 0
//
DELIMITER ;
DROP TRIGGER IF EXISTS `AggiornamentoCarrelloModificaPrezzoElemento`;
DELIMITER //
CREATE TRIGGER `AggiornamentoCarrelloModificaPrezzoElemento` AFTER UPDATE ON `Ordine`
 FOR EACH ROW BEGIN
IF new.stato = 0
THEN
    UPDATE Carrello c
        SET c.subTotal = c.subTotal - (old.prezzoDiAcquisto * old.quantita) + (new.prezzoDiAcquisto * new.quantita)
        WHERE c.idUtente = old.idUtente AND old.stato = 0;
ELSE
    UPDATE Carrello c
        SET c.subTotal = c.subTotal - (old.prezzoDiAcquisto * old.quantita)
        WHERE c.idUtente = old.idUtente AND old.stato = 0;
    
    IF (SELECT subTotal
       	FROM Carrello
       	WHERE idUtente = old.idUtente) = 0
    THEN
    	DELETE FROM Carrello
        WHERE idUtente = old.idUtente;
    END IF;
END IF;
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `AggiornamentoCarrelloRimozioneElemento`;
DELIMITER //
CREATE TRIGGER `AggiornamentoCarrelloRimozioneElemento` AFTER DELETE ON `Ordine`
 FOR EACH ROW UPDATE Carrello c
	SET c.subTotal = c.subTotal - (old.prezzoDiAcquisto * old.quantita)
    WHERE c.idUtente = old.idUtente AND old.stato = 0
//
DELIMITER ;
DROP TRIGGER IF EXISTS `CreoElementoCarrello`;
DELIMITER //
CREATE TRIGGER `CreoElementoCarrello` BEFORE INSERT ON `Ordine`
 FOR EACH ROW BEGIN
declare x int default 0;
SET x = (SELECT COUNT(idUtente) 
         FROM Carrello 
         WHERE Carrello.idUtente = new.idUtente
         LIMIT 1
        );
        
IF(x < 1) THEN
	INSERT INTO Carrello
		(idOrdine,idUtente,subTotal)
    VALUES (new.idOrdine, new.idUtente, 0);
END IF;
end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `RecensioneNegozio`
--
-- Creazione: Lug 25, 2017 alle 08:59
--

DROP TABLE IF EXISTS `RecensioneNegozio`;
CREATE TABLE IF NOT EXISTS `RecensioneNegozio` (
`id` int(11) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `RecensioneNegozio`:
--   `idNegozio`
--       `Negozio` -> `id`
--   `idUtente`
--       `Utente` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `RecensioneNegozio`
--

TRUNCATE TABLE `RecensioneNegozio`;
--
-- Dump dei dati per la tabella `RecensioneNegozio`
--

INSERT INTO `RecensioneNegozio` (`id`, `idNegozio`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, 1, 1, 'Bel negozio, personale qualificato', 5, '2017-07-14 00:00:00', NULL),
(2, 4, 2, 'Bel negozio, alla moda, consigliato!', 5, '2017-07-11 00:00:00', NULL),
(3, 3, 1, 'tutto molto bello', 4, '2017-07-19 00:00:00', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `RecensioneOggetto`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `RecensioneOggetto`;
CREATE TABLE IF NOT EXISTS `RecensioneOggetto` (
`id` int(11) NOT NULL,
  `idOggetto` varchar(32) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `RecensioneOggetto`:
--   `idOggetto`
--       `Oggetto` -> `id`
--   `idUtente`
--       `Utente` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `RecensioneOggetto`
--

TRUNCATE TABLE `RecensioneOggetto`;
--
-- Dump dei dati per la tabella `RecensioneOggetto`
--

INSERT INTO `RecensioneOggetto` (`id`, `idOggetto`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, '1', 1, 'Bene ma non benissimo, si vede che è una cinesata', 2, '2017-07-14 00:00:00', 4),
(2, '2', 1, 'CAZZO FRA STA ROBA SPACCA, LA POLVERINA BIANCA MMMMM IL NASO FA FESTA', 5, '2017-07-25 08:21:56', 12),
(3, '1', 2, 'la droga.', 1, '2017-07-25 11:18:35', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `RecensioneVenditore`
--
-- Creazione: Lug 25, 2017 alle 08:48
--

DROP TABLE IF EXISTS `RecensioneVenditore`;
CREATE TABLE IF NOT EXISTS `RecensioneVenditore` (
`id` int(11) NOT NULL,
  `idVenditore` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `RecensioneVenditore`:
--   `idVenditore`
--       `Utente` -> `id`
--   `idUtente`
--       `Utente` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `RecensioneVenditore`
--

TRUNCATE TABLE `RecensioneVenditore`;
--
-- Dump dei dati per la tabella `RecensioneVenditore`
--

INSERT INTO `RecensioneVenditore` (`id`, `idVenditore`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, 6, 1, 'tutto arrivato in tempo, imballo fatto con cazzi di gomma', 4, '2017-07-19 00:00:00', 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `RispostaNegozio`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `RispostaNegozio`;
CREATE TABLE IF NOT EXISTS `RispostaNegozio` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `RispostaNegozio`:
--   `idRecensione`
--       `RecensioneNegozio` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `RispostaNegozio`
--

TRUNCATE TABLE `RispostaNegozio`;
-- --------------------------------------------------------

--
-- Struttura della tabella `RispostaOggetto`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `RispostaOggetto`;
CREATE TABLE IF NOT EXISTS `RispostaOggetto` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `RispostaOggetto`:
--   `idRecensione`
--       `RecensioneOggetto` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `RispostaOggetto`
--

TRUNCATE TABLE `RispostaOggetto`;
--
-- Dump dei dati per la tabella `RispostaOggetto`
--

INSERT INTO `RispostaOggetto` (`idRecensione`, `testo`, `data`) VALUES
(1, 'Ci scusiamo, stiamo lavorando per assumere cinesi a basso costo che facciano cinesate che non sembrino cinesate, cordiali saluti, ToMare', '2017-07-30 00:00:00');

-- --------------------------------------------------------

--
-- Struttura della tabella `RispostaVenditore`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `RispostaVenditore`;
CREATE TABLE IF NOT EXISTS `RispostaVenditore` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `RispostaVenditore`:
--   `idRecensione`
--       `RecensioneVenditore` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `RispostaVenditore`
--

TRUNCATE TABLE `RispostaVenditore`;
-- --------------------------------------------------------

--
-- Struttura della tabella `Utente`
--
-- Creazione: Lug 17, 2017 alle 12:12
--

DROP TABLE IF EXISTS `Utente`;
CREATE TABLE IF NOT EXISTS `Utente` (
`id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `valutazione` double DEFAULT NULL,
  `UtenteType` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Utente`
--

TRUNCATE TABLE `Utente`;
--
-- Dump dei dati per la tabella `Utente`
--

INSERT INTO `Utente` (`id`, `nome`, `cognome`, `mail`, `password`, `avatar`, `valutazione`, `UtenteType`) VALUES
(1, 'Andrea', 'Fadi', 'abc@def.ghi', 'ciao', NULL, NULL, 2),
(2, 'Federico', 'Brugiolo', 'def@def.ghi', 'ciao', NULL, NULL, 2),
(3, 'Mattia', 'Milani', 'ghi@def.ghi', 'ciao', NULL, NULL, 2),
(4, 'Damiano', 'Sartori', 'lmn@def.ghi', 'ciao', NULL, NULL, 2),
(5, 'Ciuffo', 'Rosso', 'NewMail', 'newPassword', NULL, NULL, 1),
(6, 'Carlo', 'Cracco', 'a@def.ghi', 'ciao', NULL, NULL, 1),
(7, 'Coso', 'Canavacciulo', 'b@def.ghi', 'ciao', NULL, NULL, 1),
(8, 'Bill', 'Gate', 'c@def.ghi', 'ciao', NULL, NULL, 1),
(9, 'Open', 'Close', 'd@def.ghi', 'ciao', NULL, NULL, 1),
(11, 'Mario', 'Rossi', 'marioRossi@gmail.com', 'tobedefined', NULL, NULL, 1),
(12, 'franco', 'baglioni', 'francobg@gmail.com', 'tobedefined', NULL, NULL, 1),
(13, 'piero', 'pelù', 'pelu@mail.com', 'password', NULL, NULL, 1),
(14, 'Antonio', 'Tognazzi', 'togno@gmail.com', 'password', NULL, NULL, 1),
(15, 'Andrea', 'Andrelli', 'andrelli.andrea@gmail.com', 'password', NULL, NULL, 1),
(16, 'Giulio', 'Andreotti', 'andrelio@gmail.com', 'password', NULL, NULL, 1),
(17, 'Pietro', 'Pegoraro', 'pegoPietro@gmail.com', 'password', NULL, NULL, 1),
(18, 'Marco', 'Neri', 'neri.marco96@gmail.com', 'password', NULL, NULL, 1),
(19, 'Franco', 'Franchetti', 'lollo96happy@gmail.com', 'password', NULL, NULL, 1),
(20, 'Laura', 'Palmer', 'palme.laura23@gmail.com', 'password', NULL, NULL, 1),
(21, 'Federico', 'Spantelo', 'lollolollosorilollo02@maildivertente.it', 'passwod', NULL, NULL, 1),
(22, 'Mario', 'Grigi', 'grigio.mario@gmail.com', 'password', NULL, NULL, 1),
(23, 'Paolo', 'Bitta', 'uomochiamatocontratto@caffe.it', 'odioladigitex', NULL, NULL, 1),
(24, 'Luca', 'Nervi', 'sindacatoforpresident@gmail.com', 'password', NULL, NULL, 1),
(25, 'luigina', 'Brentola', 'brentolamail@gmail.com', 'password', NULL, NULL, 1),
(26, 'Marzia', 'Longhi', 'marzia.longhi@gmail.com', 'password', NULL, NULL, 0),
(27, 'Andrea', 'Rossi', 'andrea.rossi@gmail.com', 'password', NULL, NULL, 0),
(28, 'Marco', 'Furlan', 'furla.marco@gmail.com', 'password', NULL, NULL, 0),
(29, 'Lucia', 'Canticchi', 'cant.lucia@gmail.com', 'password', NULL, NULL, 0),
(30, 'Federico', 'Costenaro', 'coste.fede16@gmail.com', 'password', NULL, NULL, 0),
(31, 'Poldo', 'posturo', 'post.poldo@gmail.com', 'password', NULL, NULL, 0),
(32, 'Margherita', 'Torrevalle', 'marghelatorre@gmail.com', 'password', NULL, NULL, 0),
(33, 'Fiorella', 'Verduzzi', 'verd.fiore97@gmail.com', 'password', NULL, NULL, 0),
(34, 'Pietro', 'Di Stefano', 'distepietruzzo@gmail.com', 'password', NULL, NULL, 0),
(35, 'Pablo', 'Escobar', 'esco.nomaria@gmail.com', 'password', NULL, NULL, 0),
(36, 'Obama', 'baracche', 'barack.obama87@gmail.com', 'password', NULL, NULL, 0),
(37, 'Leonardo', 'diCapricorno', 'cpario.leo1@gmail.com', 'password', NULL, NULL, 0),
(38, 'Fiero', 'Becco', 'becco.fiero@gmail.com', 'password', NULL, NULL, 0),
(39, 'Gandalf', 'rossi', 'gandalf.ilrosso@gmail.com', 'password', NULL, NULL, 0),
(40, 'Filomena', 'menuzzi', 'filomenamenofila@gmail.com', 'password', NULL, NULL, 0),
(41, 'Willy', 'Belair', 'ilprincipe@gmail.com', 'password', NULL, NULL, 0),
(42, 'Thom', 'Hanks', 'tommy57@gmail.com', 'password', NULL, NULL, 0),
(43, 'Guglielmo', 'Marconi', 'marco.guglie@gmail.com', 'password', NULL, NULL, 0),
(44, 'Henrry', 'Wasley', 'potteriani@gmail.com', 'password', NULL, NULL, 0),
(45, 'Francesca', 'Lecresta', 'lecre.sca99@gmail.com', 'password', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageNegozio`
--
-- Creazione: Lug 18, 2017 alle 13:00
--

DROP TABLE IF EXISTS `imageNegozio`;
CREATE TABLE IF NOT EXISTS `imageNegozio` (
  `src` varchar(150) NOT NULL,
  `idN` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `imageNegozio`:
--   `idN`
--       `Negozio` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `imageNegozio`
--

TRUNCATE TABLE `imageNegozio`;
--
-- Dump dei dati per la tabella `imageNegozio`
--

INSERT INTO `imageNegozio` (`src`, `idN`) VALUES
('imgNeg1', 1),
('mediaworld2', 1),
('imgNeg2', 2),
('aliexpressimg1', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageOggetto`
--
-- Creazione: Lug 18, 2017 alle 13:03
--

DROP TABLE IF EXISTS `imageOggetto`;
CREATE TABLE IF NOT EXISTS `imageOggetto` (
  `src` varchar(150) NOT NULL,
  `idO` varchar(32) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `imageOggetto`:
--   `idO`
--       `Oggetto` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `imageOggetto`
--

TRUNCATE TABLE `imageOggetto`;
--
-- Dump dei dati per la tabella `imageOggetto`
--

INSERT INTO `imageOggetto` (`src`, `idO`) VALUES
('imgOgg1', '1'),
('imgOgg2', '2');

-- --------------------------------------------------------

--
-- Struttura della tabella `imageRecensione`
--
-- Creazione: Lug 18, 2017 alle 13:05
--

DROP TABLE IF EXISTS `imageRecensione`;
CREATE TABLE IF NOT EXISTS `imageRecensione` (
  `src` varchar(150) NOT NULL,
  `idR` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `imageRecensione`:
--   `idR`
--       `RecensioneOggetto` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `imageRecensione`
--

TRUNCATE TABLE `imageRecensione`;
--
-- Dump dei dati per la tabella `imageRecensione`
--

INSERT INTO `imageRecensione` (`src`, `idR`) VALUES
('imgRec1', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageUtente`
--
-- Creazione: Lug 18, 2017 alle 13:07
--

DROP TABLE IF EXISTS `imageUtente`;
CREATE TABLE IF NOT EXISTS `imageUtente` (
  `src` varchar(150) NOT NULL DEFAULT '',
  `idU` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `imageUtente`:
--   `idU`
--       `Utente` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `imageUtente`
--

TRUNCATE TABLE `imageUtente`;
--
-- Dump dei dati per la tabella `imageUtente`
--

INSERT INTO `imageUtente` (`src`, `idU`) VALUES
('newSrc', 1),
('imgUsr1', 2),
('imgUsr2', 3),
('imgUsr3', 7);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordiniRicevuti`
--
-- Creazione: Lug 17, 2017 alle 14:03
--

DROP TABLE IF EXISTS `ordiniRicevuti`;
CREATE TABLE IF NOT EXISTS `ordiniRicevuti` (
  `idO` int(11) NOT NULL,
  `idV` int(11) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `ordiniRicevuti`:
--   `idO`
--       `Ordine` -> `idOrdine`
--   `idV`
--       `Utente` -> `id`
--

--
-- Svuota la tabella prima dell'inserimento `ordiniRicevuti`
--

TRUNCATE TABLE `ordiniRicevuti`;
-- --------------------------------------------------------

--
-- Struttura per la vista `Interessi1`
--
DROP TABLE IF EXISTS `Interessi1`;

CREATE ALGORITHM=UNDEFINED DEFINER=`progettoweb`@`%` SQL SECURITY DEFINER VIEW `Interessi1` AS select `Oggetto`.`categoria` AS `categoria`,max(`Oggetto`.`prezzo`) AS `prezzoMassimo`,min(`Oggetto`.`prezzo`) AS `prezzoMinimo` from (`Oggetto` join `Ordine` on((`Ordine`.`idOggetto` = `Oggetto`.`id`))) where ((`Ordine`.`idUtente` = 1) and (`Ordine`.`stato` <> 0)) group by `Oggetto`.`categoria`;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Assistenza`
--
ALTER TABLE `Assistenza`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idVenditore` (`idVenditore`), ADD KEY `idAmministratore` (`idAmministratore`), ADD KEY `idOrdine` (`idOrdine`), ADD KEY `idOggetto` (`idOggetto`);

--
-- Indexes for table `Carrello`
--
ALTER TABLE `Carrello`
 ADD PRIMARY KEY (`idUtente`), ADD KEY `idOrdine` (`idOrdine`);

--
-- Indexes for table `Categoria`
--
ALTER TABLE `Categoria`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Indirizzo`
--
ALTER TABLE `Indirizzo`
 ADD PRIMARY KEY (`idI`);

--
-- Indexes for table `IndirizzoUtente`
--
ALTER TABLE `IndirizzoUtente`
 ADD PRIMARY KEY (`idI`,`idU`), ADD KEY `idU` (`idU`);

--
-- Indexes for table `Negozio`
--
ALTER TABLE `Negozio`
 ADD PRIMARY KEY (`id`,`idI`), ADD KEY `idI` (`idI`), ADD KEY `idNegozioIndex` (`idVenditore`) USING HASH;

--
-- Indexes for table `Oggetto`
--
ALTER TABLE `Oggetto`
 ADD PRIMARY KEY (`id`), ADD KEY `idNegozio` (`idNegozio`), ADD KEY `idOggettoIndex` (`categoria`) USING HASH;

--
-- Indexes for table `Ordine`
--
ALTER TABLE `Ordine`
 ADD PRIMARY KEY (`idOrdine`,`idOggetto`,`idUtente`), ADD KEY `idOggetto` (`idOggetto`), ADD KEY `idNegozio` (`idNegozio`), ADD KEY `idUtente` (`idUtente`);

--
-- Indexes for table `RecensioneNegozio`
--
ALTER TABLE `RecensioneNegozio`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idRecNegIndex` (`idNegozio`) USING HASH;

--
-- Indexes for table `RecensioneOggetto`
--
ALTER TABLE `RecensioneOggetto`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idRecOggIndex` (`idOggetto`) USING HASH;

--
-- Indexes for table `RecensioneVenditore`
--
ALTER TABLE `RecensioneVenditore`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idRecVenIndex` (`idVenditore`) USING HASH;

--
-- Indexes for table `RispostaNegozio`
--
ALTER TABLE `RispostaNegozio`
 ADD PRIMARY KEY (`idRecensione`);

--
-- Indexes for table `RispostaOggetto`
--
ALTER TABLE `RispostaOggetto`
 ADD PRIMARY KEY (`idRecensione`);

--
-- Indexes for table `RispostaVenditore`
--
ALTER TABLE `RispostaVenditore`
 ADD PRIMARY KEY (`idRecensione`);

--
-- Indexes for table `Utente`
--
ALTER TABLE `Utente`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtenteIndex` (`id`,`UtenteType`) USING HASH;

--
-- Indexes for table `imageNegozio`
--
ALTER TABLE `imageNegozio`
 ADD PRIMARY KEY (`src`,`idN`), ADD KEY `idN` (`idN`);

--
-- Indexes for table `imageOggetto`
--
ALTER TABLE `imageOggetto`
 ADD PRIMARY KEY (`src`,`idO`), ADD KEY `idO` (`idO`);

--
-- Indexes for table `imageRecensione`
--
ALTER TABLE `imageRecensione`
 ADD PRIMARY KEY (`src`,`idR`), ADD KEY `idR` (`idR`);

--
-- Indexes for table `imageUtente`
--
ALTER TABLE `imageUtente`
 ADD PRIMARY KEY (`src`,`idU`), ADD KEY `idI` (`src`), ADD KEY `idU` (`idU`);

--
-- Indexes for table `ordiniRicevuti`
--
ALTER TABLE `ordiniRicevuti`
 ADD PRIMARY KEY (`idO`,`idV`), ADD KEY `idV` (`idV`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Assistenza`
--
ALTER TABLE `Assistenza`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `Categoria`
--
ALTER TABLE `Categoria`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Indirizzo`
--
ALTER TABLE `Indirizzo`
MODIFY `idI` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `Negozio`
--
ALTER TABLE `Negozio`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `Ordine`
--
ALTER TABLE `Ordine`
MODIFY `idOrdine` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `RecensioneNegozio`
--
ALTER TABLE `RecensioneNegozio`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `RecensioneOggetto`
--
ALTER TABLE `RecensioneOggetto`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `RecensioneVenditore`
--
ALTER TABLE `RecensioneVenditore`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Utente`
--
ALTER TABLE `Utente`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Assistenza`
--
ALTER TABLE `Assistenza`
ADD CONSTRAINT `Assistenza_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_2` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_3` FOREIGN KEY (`idAmministratore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_4` FOREIGN KEY (`idOrdine`) REFERENCES `Ordine` (`idOrdine`) ON DELETE SET NULL,
ADD CONSTRAINT `Assistenza_ibfk_5` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `Carrello`
--
ALTER TABLE `Carrello`
ADD CONSTRAINT `Carrello_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `IndirizzoUtente`
--
ALTER TABLE `IndirizzoUtente`
ADD CONSTRAINT `IndirizzoUtente_ibfk_1` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `IndirizzoUtente_ibfk_2` FOREIGN KEY (`idI`) REFERENCES `Indirizzo` (`idI`) ON DELETE CASCADE;

--
-- Limiti per la tabella `Negozio`
--
ALTER TABLE `Negozio`
ADD CONSTRAINT `Negozio_ibfk_1` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Negozio_ibfk_2` FOREIGN KEY (`idI`) REFERENCES `Indirizzo` (`idI`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `Oggetto`
--
ALTER TABLE `Oggetto`
ADD CONSTRAINT `Oggetto_ibfk_1` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Oggetto_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `Categoria` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `Ordine`
--
ALTER TABLE `Ordine`
ADD CONSTRAINT `Ordine_ibfk_1` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Ordine_ibfk_2` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Ordine_ibfk_3` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `RecensioneNegozio`
--
ALTER TABLE `RecensioneNegozio`
ADD CONSTRAINT `RecensioneNegozio_ibfk_1` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `RecensioneNegozio_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `RecensioneOggetto`
--
ALTER TABLE `RecensioneOggetto`
ADD CONSTRAINT `RecensioneOggetto_ibfk_1` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `RecensioneOggetto_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `RecensioneVenditore`
--
ALTER TABLE `RecensioneVenditore`
ADD CONSTRAINT `RecensioneVenditore_ibfk_1` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `RecensioneVenditore_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `RispostaNegozio`
--
ALTER TABLE `RispostaNegozio`
ADD CONSTRAINT `RispostaNegozio_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneNegozio` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `RispostaOggetto`
--
ALTER TABLE `RispostaOggetto`
ADD CONSTRAINT `RispostaOggetto_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneOggetto` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `RispostaVenditore`
--
ALTER TABLE `RispostaVenditore`
ADD CONSTRAINT `RispostaVenditore_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneVenditore` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `imageNegozio`
--
ALTER TABLE `imageNegozio`
ADD CONSTRAINT `imageNegozio_ibfk_2` FOREIGN KEY (`idN`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageOggetto`
--
ALTER TABLE `imageOggetto`
ADD CONSTRAINT `imageOggetto_ibfk_2` FOREIGN KEY (`idO`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageRecensione`
--
ALTER TABLE `imageRecensione`
ADD CONSTRAINT `imageRecensione_ibfk_2` FOREIGN KEY (`idR`) REFERENCES `RecensioneOggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageUtente`
--
ALTER TABLE `imageUtente`
ADD CONSTRAINT `imageUtente_ibfk_2` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ordiniRicevuti`
--
ALTER TABLE `ordiniRicevuti`
ADD CONSTRAINT `ordiniRicevuti_ibfk_1` FOREIGN KEY (`idO`) REFERENCES `Ordine` (`idOrdine`),
ADD CONSTRAINT `ordiniRicevuti_ibfk_2` FOREIGN KEY (`idV`) REFERENCES `Utente` (`id`);

DELIMITER $$
--
-- Eventi
--
DROP EVENT `ControlloScontiAttivi`$$
CREATE DEFINER=`progettoweb`@`%` EVENT `ControlloScontiAttivi` ON SCHEDULE EVERY 1 DAY STARTS '2017-07-22 00:01:00' ENDS '2027-07-22 00:01:00' ON COMPLETION PRESERVE ENABLE COMMENT 'Evento utilizzato per eliminare gli sconti terminati' DO UPDATE Oggetto SET Oggetto.`sconto` = 0, Oggetto.`dataFineSconto` = IF(Oggetto.`dataFineSconto`<CURDATE(), NULL,Oggetto.`dataFineSconto`)$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
