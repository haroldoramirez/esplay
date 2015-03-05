# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table agenda (
  id                        bigint auto_increment not null,
  constraint pk_agenda primary key (id))
;

create table categoria (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  constraint pk_categoria primary key (id))
;

create table compromisso (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  constraint pk_compromisso primary key (id))
;

create table contato (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  constraint pk_contato primary key (id))
;

create table tipo (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  constraint pk_tipo primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  constraint pk_usuario primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table agenda;

drop table categoria;

drop table compromisso;

drop table contato;

drop table tipo;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

