# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categoria (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint pk_categoria primary key (id))
;

create table evento (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint pk_evento primary key (id))
;

create table tipo (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint pk_tipo primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint pk_usuario primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table categoria;

drop table evento;

drop table tipo;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

