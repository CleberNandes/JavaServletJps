create database trabalhoBiblioteca;

use trabalhoBiblioteca;

create table tipo_usuario (
	id int auto_increment primary key,
	nome varchar(255) not null
);

insert into tipo_usuario (nome) values ('Aluno'),('Funcionario'),('Professor');

create table usuarios (
	id int auto_increment primary key,
	nome varchar(255) not null,
	email varchar(255) not null,
	tipo_usuario_id int not null,
	senha varchar(255) not null,
	constraint fk_tipo_usuario foreign key tipo_usuario_id references (id) on (tipo_usuario)
);



create table tipo_produto (
	id int auto_increment primary key,
	nome varchar(255) not null
);

create table assuntos (
	in int auto_increment primary key,
	nome varchar(255) not null
);

create table autores (
	id int auto_increment primary key,
	nome varchar(255) not null,
);

create table produtos (
	id int auto_increment primary key,
	tipo_produto_id int not null,
	assunto_id int not null,
	autor_id int not null,
	isbn_issn int null,
	ano int,
	n_chamada varchar(255), not null,
	titulo varchar(255) not null,
	editora varchar(255),
	serie varchar(255),
	descricao varchar(255),
	
);

create table pedidos (
	id int auto_increment primary key,	
);
create table item_pedidos (
	id int auto_increment primary key,	
);
create table pedidos (
	id int auto_increment primary key,	
);
