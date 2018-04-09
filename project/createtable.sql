
create database if not exists moviedb;

use moviedb;


create table movies (
	id varchar(10) not null,
	title varchar(100) not null, 
	year int not null, 
	director varchar(100) not null,
    FULLTEXT (title),
	primary key(id));

create table stars (
	id varchar(10) not null, 
	name varchar(100) not null, 
	birthYear int,
    FULLTEXT (name),
	primary key(id));

create table stars_in_movies (
	starId varchar(10) not null,
	movieId varchar(10) not null,
	foreign key(starId) references stars(id),
    foreign key(movieId) references movies(id));

create table genres (
id int not null auto_increment, 
name varchar(32),
primary key(id));

create table genres_in_movies (
	genreId int not null,
	movieId varchar(10) not null,
	foreign key(genreId) references genres(id),
	foreign key(movieId) references movies(id));

create table creditcards (
	id varchar(20) not null, 
	firstName varchar(50),
	lastName varchar(50),
	expiration date,
	primary key(id));

create table customers (
	id int not null auto_increment, 
	firstName varchar(50),
	lastName varchar(50),
	ccId varchar(20),
	address varchar(200),
	email varchar(50),
	password varchar(20),
	primary key(id),
	foreign key(ccId) references creditcards(id));

create table sales (
	id int not null auto_increment, 
	customerId int, 
	movieId varchar(10), 
	saleDate date,
	primary key(id),
	foreign key(customerId) references customers(id),
	foreign key(movieId) references movies(id));



create table ratings (
	movieId varchar(10),
	rating float,
	numVotes int,
	foreign key(movieId) references movies(id));
