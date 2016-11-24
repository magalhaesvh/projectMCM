-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 24-Nov-2016 às 03:51
-- Versão do servidor: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `locaveiculos`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agencia`
--

CREATE TABLE `agencia` (
  `id_agencia` int(11) NOT NULL,
  `nome` varchar(250) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  `telefone` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `agencia`
--

INSERT INTO `agencia` (`id_agencia`, `nome`, `cnpj`, `telefone`) VALUES
(1, 'Agência 1', '51.601.063/0001-34', ''),
(4, 'Agência 3', '82.476.168/0001-32', ''),
(6, 'Agência 2', '14.658.896/0001-05', NULL),
(7, 'Agência 4', '54.759.811/0001-08', NULL),
(8, 'Agência 6', '48.258.056/0001-77', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nome` varchar(250) NOT NULL,
  `cpf` int(11) NOT NULL,
  `rg` varchar(20) NOT NULL,
  `cnh` varchar(12) NOT NULL,
  `data_nascimento` date NOT NULL,
  `data_vinculo` date NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nome`, `cpf`, `rg`, `cnh`, `data_nascimento`, `data_vinculo`, `email`) VALUES
(2, 'teste', 123, '321', '123', '2016-11-20', '2016-11-28', 'teste@email.com'),
(3, 'teste123', 123123, '32132132', '12313213', '2016-11-23', '2016-11-23', '1231@email.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `id_funcionario` int(11) NOT NULL,
  `id_agencia` int(11) NOT NULL,
  `nome` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `senha` varchar(500) NOT NULL,
  `cpf` varchar(250) NOT NULL,
  `rg` varchar(20) NOT NULL,
  `data_contratacao` date DEFAULT NULL,
  `tipo` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`id_funcionario`, `id_agencia`, `nome`, `email`, `senha`, `cpf`, `rg`, `data_contratacao`, `tipo`) VALUES
(3, 0, 'Raphael Corujo Moura', 'admin@email.com', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', '125.287.246-16', 'MG-18.733.967', '2016-10-09', 1),
(4, 4, 'Gerente12', 'gerente@email.com', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', '125.287.246-16', '321', '2016-10-17', 2),
(7, 4, 'Victor', 'victor@email.com', '5B4EF47F73C7458B4375EBEE468E5EFB2EF67F388368B2454D0D5D8D67979A87', '123', '3321', '2016-11-22', 3),
(10, 4, 'abab', 'sadasd@email.com', '0A264E84B66457A4047FE0C0178E9C340947DBD51B34DA86C498398D5F0E59D8', '852.726.144-88', 'adsa', '2016-11-23', 3),
(11, 4, 'teste', 'teste@email.com', 'E3F3691C175C4A02D85F1B66D3CC9928D97CFCE334636503A3B9A64BC43B462D', '852.726.144-88', '123', '2016-11-23', 3),
(12, 4, 'Carla Floriano Silva', 'carflosi@email.com', '20F3765880A5C269B747E1E906054A4B4A3A991259F1E16B5DDE4742CEC2319A', '112.490.986-96', 'MG-18.456.879', '2016-11-23', 2),
(13, 8, 'Teste', '12312@email.com', '836F4513C7085DF9CC3B3C065F67DAD95FD3FB8EF892B4A1BDB2D815D66A34A7', '12528724616', 'dsamdsadam', '2016-11-25', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `locacao`
--

CREATE TABLE `locacao` (
  `id_locacao` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_plano` int(11) NOT NULL,
  `id_locador` int(11) NOT NULL,
  `data_inicio` datetime NOT NULL,
  `data_final` datetime NOT NULL,
  `km_inicial` decimal(10,0) NOT NULL,
  `id_veiculo` int(11) NOT NULL,
  `id_agencia_devolucao` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `locacao`
--

INSERT INTO `locacao` (`id_locacao`, `id_cliente`, `id_plano`, `id_locador`, `data_inicio`, `data_final`, `km_inicial`, `id_veiculo`, `id_agencia_devolucao`) VALUES
(1, 2, 2, 3, '2016-11-23 00:00:00', '2016-11-24 00:00:00', '10000', 3, NULL),
(3, 3, 0, 4, '2016-11-23 00:00:00', '2016-11-24 00:00:00', '1500', 6, NULL),
(4, 2, 2, 4, '2016-11-23 00:00:00', '2016-11-25 00:00:00', '1509', 1, NULL),
(5, 2, 2, 4, '2016-11-23 00:00:00', '2016-11-24 00:00:00', '15000', 5, NULL),
(6, 3, 2, 4, '2016-11-23 00:00:00', '2016-11-23 00:00:00', '123321', 5, NULL),
(7, 3, 2, 4, '2016-11-23 00:00:00', '2016-11-25 00:00:00', '12312', 3, NULL),
(8, 2, 2, 4, '2016-11-23 00:00:00', '2016-11-25 00:00:00', '123123', 3, NULL),
(9, 2, 2, 4, '2016-11-23 00:00:00', '2016-12-03 00:00:00', '123213', 3, NULL),
(10, 2, 2, 4, '2016-11-23 00:00:00', '2016-11-25 00:00:00', '12312', 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `plano`
--

CREATE TABLE `plano` (
  `id_plano` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `regulamento` text NOT NULL,
  `descricao` text NOT NULL,
  `calculo_quilometragem` tinyint(1) NOT NULL,
  `valor_quilometragem` decimal(10,0) NOT NULL,
  `custo_fixo` tinyint(1) NOT NULL,
  `valor_custo` decimal(10,0) NOT NULL,
  `diaria` tinyint(1) NOT NULL,
  `valor_diaria` decimal(11,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `plano`
--

INSERT INTO `plano` (`id_plano`, `nome`, `regulamento`, `descricao`, `calculo_quilometragem`, `valor_quilometragem`, `custo_fixo`, `valor_custo`, `diaria`, `valor_diaria`) VALUES
(1, 'teste', 'teste', 'teste', 0, '4', 0, '0', 0, '30'),
(2, 'teste1', 'teste1\nteste1\nteste1', 'teste1\nteste1\nteste1', 1, '8', 1, '150', 0, '0');

-- --------------------------------------------------------

--
-- Estrutura da tabela `status`
--

CREATE TABLE `status` (
  `id_status` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `status`
--

INSERT INTO `status` (`id_status`, `nome`) VALUES
(5, 'Disponível'),
(6, 'Alugado'),
(7, 'Manutenção');

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `id_veiculo` int(11) NOT NULL,
  `id_agencia` int(11) NOT NULL,
  `cor` varchar(100) NOT NULL,
  `valor` float NOT NULL,
  `placa` varchar(8) NOT NULL,
  `chassi` varchar(30) NOT NULL,
  `ano_modelo` int(11) NOT NULL,
  `ano_fabricacao` int(11) NOT NULL,
  `observacoes` text NOT NULL,
  `id_status` int(11) NOT NULL,
  `ar_condicionado` tinyint(1) NOT NULL DEFAULT '0',
  `vidro_eletrico` tinyint(1) NOT NULL DEFAULT '0',
  `trava_eletrica` tinyint(1) NOT NULL DEFAULT '0',
  `direcao_eletrica` tinyint(1) NOT NULL DEFAULT '0',
  `cambio_automatico` tinyint(1) NOT NULL DEFAULT '0',
  `motor` float NOT NULL,
  `observações` text,
  `abs` tinyint(1) NOT NULL DEFAULT '0',
  `air_bag` tinyint(1) NOT NULL DEFAULT '0',
  `4x4` tinyint(1) NOT NULL DEFAULT '0',
  `marca` varchar(250) NOT NULL,
  `modelo` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `veiculo`
--

INSERT INTO `veiculo` (`id_veiculo`, `id_agencia`, `cor`, `valor`, `placa`, `chassi`, `ano_modelo`, `ano_fabricacao`, `observacoes`, `id_status`, `ar_condicionado`, `vidro_eletrico`, `trava_eletrica`, `direcao_eletrica`, `cambio_automatico`, `motor`, `observações`, `abs`, `air_bag`, `4x4`, `marca`, `modelo`) VALUES
(1, 1, 'azul', 14000, 'mda-1231', 'sadsade2233ewqswe', 0, 0, 'teste', 6, 0, 1, 0, 0, 0, 3, 'dsadsd', 0, 0, 0, 'Ford', 'Ka'),
(3, 4, 'preto', 25654, 'abc-1234', 'daksdjaksjdask', 2016, 2015, '', 6, 1, 1, 0, 0, 0, 3, NULL, 0, 0, 0, 'Ford', 'Fiesta'),
(4, 4, 'preto', 14550, 'asd-1231', 'daksldhsahdsadlh', 2016, 2015, 'dhsajsadsajkdnsadasd\nadsadasd', 6, 1, 1, 0, 1, 0, 1, NULL, 0, 0, 0, 'Fiat', 'Uno'),
(5, 4, 'Preto', 123123000, 'ads-1233', 'sadasdsadasdsa', 2015, 2016, 'dsadasdasd', 6, 0, 1, 1, 0, 0, 2, NULL, 0, 1, 0, 'Teste', 'Palio'),
(6, 4, '2133213', 2133210, '2133213', '2133213', 2016, 2015, 'asdasdsadsad', 6, 1, 0, 1, 0, 0, 2133210, NULL, 0, 0, 0, '123123', '2133213');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vistoria`
--

CREATE TABLE `vistoria` (
  `id_vistoria` int(11) NOT NULL,
  `id_veiculo` int(11) NOT NULL,
  `id_funcionario` int(11) NOT NULL,
  `id_status` int(11) NOT NULL,
  `observacoes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agencia`
--
ALTER TABLE `agencia`
  ADD PRIMARY KEY (`id_agencia`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id_funcionario`),
  ADD KEY `id_agencia` (`id_agencia`);

--
-- Indexes for table `locacao`
--
ALTER TABLE `locacao`
  ADD PRIMARY KEY (`id_locacao`),
  ADD KEY `id_cliente` (`id_cliente`,`id_plano`,`id_locador`),
  ADD KEY `id_plano` (`id_plano`),
  ADD KEY `id_locador` (`id_locador`);

--
-- Indexes for table `plano`
--
ALTER TABLE `plano`
  ADD PRIMARY KEY (`id_plano`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id_status`);

--
-- Indexes for table `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`id_veiculo`),
  ADD KEY `id_status` (`id_status`),
  ADD KEY `id_agencia` (`id_agencia`);

--
-- Indexes for table `vistoria`
--
ALTER TABLE `vistoria`
  ADD PRIMARY KEY (`id_vistoria`),
  ADD KEY `id_veiculo` (`id_veiculo`),
  ADD KEY `id_funcionario` (`id_funcionario`,`id_status`),
  ADD KEY `id_status` (`id_status`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agencia`
--
ALTER TABLE `agencia`
  MODIFY `id_agencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id_funcionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `locacao`
--
ALTER TABLE `locacao`
  MODIFY `id_locacao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `plano`
--
ALTER TABLE `plano`
  MODIFY `id_plano` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id_status` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `id_veiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `vistoria`
--
ALTER TABLE `vistoria`
  MODIFY `id_vistoria` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `locacao`
--
ALTER TABLE `locacao`
  ADD CONSTRAINT `locacao_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `locacao_ibfk_3` FOREIGN KEY (`id_locador`) REFERENCES `funcionario` (`id_funcionario`);

--
-- Limitadores para a tabela `veiculo`
--
ALTER TABLE `veiculo`
  ADD CONSTRAINT `veiculo_ibfk_1` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `veiculo_ibfk_2` FOREIGN KEY (`id_agencia`) REFERENCES `agencia` (`id_agencia`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `vistoria`
--
ALTER TABLE `vistoria`
  ADD CONSTRAINT `vistoria_ibfk_1` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id_veiculo`),
  ADD CONSTRAINT `vistoria_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`),
  ADD CONSTRAINT `vistoria_ibfk_3` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
