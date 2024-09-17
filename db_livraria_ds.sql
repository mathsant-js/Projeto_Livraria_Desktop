-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 12/09/2024 às 00:16
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_livraria`
--
CREATE DATABASE IF NOT EXISTS `db_livraria` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_livraria`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `autor`
--

CREATE TABLE `autor` (
  `cod_autor` int(11) NOT NULL,
  `nome_autor` varchar(60) NOT NULL,
  `biografia_autor` varchar(255) NOT NULL,
  `data_nascimento_autor` date NOT NULL,
  `data_falecimento_autor` date DEFAULT NULL,
  `nacionalidade_autor` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `autorlivro`
--

CREATE TABLE `autorlivro` (
  `cod_autor` int(11) DEFAULT NULL,
  `cod_livro` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `nome_cliente` varchar(60) NOT NULL,
  `cpf_cliente` varchar(15) NOT NULL,
  `data_nascimento_cliente` date NOT NULL,
  `senha_cliente` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `compra`
--

CREATE TABLE `compra` (
  `cod_compra` int(11) NOT NULL,
  `prazo_entrega` date NOT NULL,
  `metodo_pagamento` varchar(20) NOT NULL,
  `cod_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `editora`
--

CREATE TABLE `editora` (
  `cod_editora` int(11) NOT NULL,
  `nome_editora` varchar(50) NOT NULL,
  `endereco_editora` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `emailcliente`
--

CREATE TABLE `emailcliente` (
  `cod_cliente` int(11) DEFAULT NULL,
  `email_cliente` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `enderecocliente`
--

CREATE TABLE `enderecocliente` (
  `cod_cliente` int(11) DEFAULT NULL,
  `endereco_cliente` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `genero`
--

CREATE TABLE `genero` (
  `cod_genero` int(11) NOT NULL,
  `nome_genero` varchar(15) NOT NULL,
  `descricao_genero` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `lista`
--

CREATE TABLE `lista` (
  `cod_lista` int(11) NOT NULL,
  `data_criacao_lista` date NOT NULL,
  `cod_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `livro`
--

CREATE TABLE `livro` (
  `cod_livro` int(11) NOT NULL,
  `nome_livro` varchar(70) NOT NULL,
  `isbn_livro` varchar(18) NOT NULL,
  `data_lancamento` date NOT NULL,
  `preco_livro` float NOT NULL,
  `descricao_livro` varchar(255) DEFAULT NULL,
  `cod_genero` int(11) DEFAULT NULL,
  `cod_editora` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `livroscomprados`
--

CREATE TABLE `livroscomprados` (
  `cod_livro` int(11) DEFAULT NULL,
  `cod_compra` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `livrossalvos`
--

CREATE TABLE `livrossalvos` (
  `cod_livro` int(11) DEFAULT NULL,
  `cod_lista` int(11) DEFAULT NULL,
  `data_salvamento_livro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `telefonecliente`
--

CREATE TABLE `telefonecliente` (
  `cod_cliente` int(11) DEFAULT NULL,
  `telefone_cliente` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `telefoneeditora`
--

CREATE TABLE `telefoneeditora` (
  `cod_editora` int(11) DEFAULT NULL,
  `telefone_editora` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `cod_usuario` int(11) NOT NULL,
  `nome_usuario` varchar(50) NOT NULL,
  `senha_usuario` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`cod_autor`);

--
-- Índices de tabela `autorlivro`
--
ALTER TABLE `autorlivro`
  ADD KEY `cod_autor` (`cod_autor`),
  ADD KEY `cod_livro` (`cod_livro`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cliente`),
  ADD UNIQUE KEY `cpf_cliente` (`cpf_cliente`);

--
-- Índices de tabela `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`cod_compra`),
  ADD KEY `cod_cliente` (`cod_cliente`);

--
-- Índices de tabela `editora`
--
ALTER TABLE `editora`
  ADD PRIMARY KEY (`cod_editora`);

--
-- Índices de tabela `emailcliente`
--
ALTER TABLE `emailcliente`
  ADD UNIQUE KEY `email_cliente` (`email_cliente`),
  ADD KEY `cod_cliente` (`cod_cliente`);

--
-- Índices de tabela `enderecocliente`
--
ALTER TABLE `enderecocliente`
  ADD KEY `cod_cliente` (`cod_cliente`);

--
-- Índices de tabela `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`cod_genero`);

--
-- Índices de tabela `lista`
--
ALTER TABLE `lista`
  ADD PRIMARY KEY (`cod_lista`),
  ADD KEY `cod_cliente` (`cod_cliente`);

--
-- Índices de tabela `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`cod_livro`),
  ADD KEY `cod_genero` (`cod_genero`),
  ADD KEY `cod_editora` (`cod_editora`);

--
-- Índices de tabela `livroscomprados`
--
ALTER TABLE `livroscomprados`
  ADD KEY `cod_livro` (`cod_livro`),
  ADD KEY `cod_compra` (`cod_compra`);

--
-- Índices de tabela `livrossalvos`
--
ALTER TABLE `livrossalvos`
  ADD KEY `cod_livro` (`cod_livro`),
  ADD KEY `cod_lista` (`cod_lista`);

--
-- Índices de tabela `telefonecliente`
--
ALTER TABLE `telefonecliente`
  ADD UNIQUE KEY `telefone_cliente` (`telefone_cliente`),
  ADD KEY `cod_cliente` (`cod_cliente`);

--
-- Índices de tabela `telefoneeditora`
--
ALTER TABLE `telefoneeditora`
  ADD KEY `cod_editora` (`cod_editora`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cod_usuario`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `autor`
--
ALTER TABLE `autor`
  MODIFY `cod_autor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `compra`
--
ALTER TABLE `compra`
  MODIFY `cod_compra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `editora`
--
ALTER TABLE `editora`
  MODIFY `cod_editora` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `genero`
--
ALTER TABLE `genero`
  MODIFY `cod_genero` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `lista`
--
ALTER TABLE `lista`
  MODIFY `cod_lista` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `livro`
--
ALTER TABLE `livro`
  MODIFY `cod_livro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `autorlivro`
--
ALTER TABLE `autorlivro`
  ADD CONSTRAINT `autorlivro_ibfk_1` FOREIGN KEY (`cod_autor`) REFERENCES `autor` (`cod_autor`),
  ADD CONSTRAINT `autorlivro_ibfk_2` FOREIGN KEY (`cod_livro`) REFERENCES `livro` (`cod_livro`);

--
-- Restrições para tabelas `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`);

--
-- Restrições para tabelas `emailcliente`
--
ALTER TABLE `emailcliente`
  ADD CONSTRAINT `emailcliente_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`);

--
-- Restrições para tabelas `enderecocliente`
--
ALTER TABLE `enderecocliente`
  ADD CONSTRAINT `enderecocliente_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`);

--
-- Restrições para tabelas `lista`
--
ALTER TABLE `lista`
  ADD CONSTRAINT `lista_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`);

--
-- Restrições para tabelas `livro`
--
ALTER TABLE `livro`
  ADD CONSTRAINT `livro_ibfk_1` FOREIGN KEY (`cod_genero`) REFERENCES `genero` (`cod_genero`),
  ADD CONSTRAINT `livro_ibfk_2` FOREIGN KEY (`cod_editora`) REFERENCES `editora` (`cod_editora`);

--
-- Restrições para tabelas `livroscomprados`
--
ALTER TABLE `livroscomprados`
  ADD CONSTRAINT `livroscomprados_ibfk_1` FOREIGN KEY (`cod_livro`) REFERENCES `livro` (`cod_livro`),
  ADD CONSTRAINT `livroscomprados_ibfk_2` FOREIGN KEY (`cod_compra`) REFERENCES `compra` (`cod_compra`);

--
-- Restrições para tabelas `livrossalvos`
--
ALTER TABLE `livrossalvos`
  ADD CONSTRAINT `livrossalvos_ibfk_1` FOREIGN KEY (`cod_livro`) REFERENCES `livro` (`cod_livro`),
  ADD CONSTRAINT `livrossalvos_ibfk_2` FOREIGN KEY (`cod_lista`) REFERENCES `lista` (`cod_lista`);

--
-- Restrições para tabelas `telefonecliente`
--
ALTER TABLE `telefonecliente`
  ADD CONSTRAINT `telefonecliente_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`);

--
-- Restrições para tabelas `telefoneeditora`
--
ALTER TABLE `telefoneeditora`
  ADD CONSTRAINT `telefoneeditora_ibfk_1` FOREIGN KEY (`cod_editora`) REFERENCES `editora` (`cod_editora`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
