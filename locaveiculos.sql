-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 19-Nov-2016 às 13:18
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
  `telefone` varchar(40) NOT NULL,
  `id_endereco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `agencia`
--

INSERT INTO `agencia` (`id_agencia`, `nome`, `cnpj`, `telefone`, `id_endereco`) VALUES
(1, 'Agencia 1', '123456789', '', 0),
(4, 'teste', '123456', '', 0);

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
  `data_vinculo` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  `id_endereco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `id_endereco` int(11) NOT NULL,
  `logradouro` text NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` text NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(200) NOT NULL,
  `estado` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(3, 0, 'Raphael Corujo Moura', 'admin@email.com', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4', '125.287.246-16', 'MG-18.733.967', '2016-10-09', 1),
(4, 4, '123', 'gerente@email.com', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', '123', '321', '2016-10-17', 2);

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
  `km_final` decimal(10,0) NOT NULL,
  `id_ status` int(11) NOT NULL,
  `id_agencia_devolucao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `plano`
--

CREATE TABLE `plano` (
  `id_plano` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `regulamento` text NOT NULL,
  `descricao` text NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `calculo_quilometragem` tinyint(1) NOT NULL,
  `valor_quilometragem` decimal(10,0) NOT NULL,
  `custo_fixo` tinyint(1) NOT NULL,
  `valor_custo` decimal(10,0) NOT NULL,
  `diaria` tinyint(1) NOT NULL,
  `valor_diaria` decimal(11,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `status`
--

CREATE TABLE `status` (
  `id_status` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `tipo` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `status`
--

INSERT INTO `status` (`id_status`, `nome`, `tipo`) VALUES
(1, 'ativo', 1),
(2, 'desativo', 1),
(3, 'Em funcionamento', 2),
(4, 'Fechada', 2),
(5, 'Disponível', 3),
(6, 'Alugado', 3),
(7, 'Manutenção', 3);

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
  `ano_modelo` date NOT NULL,
  `ano_fabricacao` date NOT NULL,
  `data_compra` datetime NOT NULL,
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

INSERT INTO `veiculo` (`id_veiculo`, `id_agencia`, `cor`, `valor`, `placa`, `chassi`, `ano_modelo`, `ano_fabricacao`, `data_compra`, `observacoes`, `id_status`, `ar_condicionado`, `vidro_eletrico`, `trava_eletrica`, `direcao_eletrica`, `cambio_automatico`, `motor`, `observações`, `abs`, `air_bag`, `4x4`, `marca`, `modelo`) VALUES
(1, 1, 'azul', 14000, 'mda-1231', 'sadsade2233ewqswe', '0000-00-00', '0000-00-00', '2016-10-16 00:00:00', 'teste', 1, 0, 0, 0, 0, 0, 3, 'dsadsd', 0, 0, 0, '', ''),
(2, 1, 'xxxxxxxx', 123, '123', '123', '0000-00-00', '0000-00-00', '2016-10-27 00:00:00', '123', 2, 0, 0, 0, 0, 0, 3, '123', 0, 0, 0, '', '');

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
  ADD PRIMARY KEY (`id_agencia`),
  ADD KEY `id_endereco` (`id_endereco`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `id_endereco` (`id_endereco`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id_endereco`);

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
  ADD KEY `id_cliente` (`id_cliente`,`id_plano`,`id_locador`,`id_agencia_devolucao`),
  ADD KEY `id_plano` (`id_plano`),
  ADD KEY `id_locador` (`id_locador`),
  ADD KEY `id_agencia_devolucao` (`id_agencia_devolucao`),
  ADD KEY `id_ status` (`id_ status`);

--
-- Indexes for table `plano`
--
ALTER TABLE `plano`
  ADD PRIMARY KEY (`id_plano`),
  ADD KEY `id_tipo` (`id_tipo`);

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
  MODIFY `id_agencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id_endereco` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id_funcionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `locacao`
--
ALTER TABLE `locacao`
  MODIFY `id_locacao` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `plano`
--
ALTER TABLE `plano`
  MODIFY `id_plano` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id_status` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `id_veiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `vistoria`
--
ALTER TABLE `vistoria`
  MODIFY `id_vistoria` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `locacao`
--
ALTER TABLE `locacao`
  ADD CONSTRAINT `locacao_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `locacao_ibfk_2` FOREIGN KEY (`id_plano`) REFERENCES `plano` (`id_plano`),
  ADD CONSTRAINT `locacao_ibfk_3` FOREIGN KEY (`id_locador`) REFERENCES `funcionario` (`id_funcionario`),
  ADD CONSTRAINT `locacao_ibfk_4` FOREIGN KEY (`id_agencia_devolucao`) REFERENCES `agencia` (`id_agencia`),
  ADD CONSTRAINT `locacao_ibfk_5` FOREIGN KEY (`id_ status`) REFERENCES `status` (`id_status`);

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
