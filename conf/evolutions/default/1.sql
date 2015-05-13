# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table agenda (
  id                        bigint auto_increment not null,
  data_inicio               datetime,
  data_fim                  datetime,
  constraint pk_agenda primary key (id))
;

create table categoria (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  constraint uq_categoria_nome unique (nome),
  constraint pk_categoria primary key (id))
;

create table compromisso (
  id                        bigint auto_increment not null,
  titulo                    varchar(255) not null,
  data_inicio               datetime,
  hora_inicio               datetime,
  data_fim                  datetime,
  hora_fim                  datetime,
  descricao                 varchar(255),
  observacoes               varchar(255),
  local                     varchar(255),
  status                    varchar(7),
  tipo_id                   bigint,
  contato_id                bigint,
  categoria_id              bigint,
  constraint ck_compromisso_status check (status in ('OCUPADO','LIVRE')),
  constraint uq_compromisso_titulo unique (titulo),
  constraint pk_compromisso primary key (id))
;

create table contato (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  email                     varchar(255) not null,
  telefone                  varchar(255),
  constraint pk_contato primary key (id))
;

create table entidade_pai (
  id                        bigint auto_increment not null,
  padrao_do_sistema         tinyint(1) default 0,
  constraint pk_entidade_pai primary key (id))
;

create table log (
  id                        bigint auto_increment not null,
  data_do_log               datetime,
  mensagem                  varchar(255),
  constraint pk_log primary key (id))
;

create table tipo (
  id                        bigint auto_increment not null,
  padrao_do_sistema         tinyint(1) default 0,
  nome                      varchar(255) not null,
  constraint uq_tipo_nome unique (nome),
  constraint pk_tipo primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  padrao_do_sistema         tinyint(1) default 0,
  email                     varchar(255) not null,
  senha                     varchar(255) not null,
  constraint uq_usuario_email unique (email),
  constraint pk_usuario primary key (id))
;

alter table compromisso add constraint fk_compromisso_tipo_1 foreign key (tipo_id) references tipo (id) on delete restrict on update restrict;
create index ix_compromisso_tipo_1 on compromisso (tipo_id);
alter table compromisso add constraint fk_compromisso_contato_2 foreign key (contato_id) references contato (id) on delete restrict on update restrict;
create index ix_compromisso_contato_2 on compromisso (contato_id);
alter table compromisso add constraint fk_compromisso_categoria_3 foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_compromisso_categoria_3 on compromisso (categoria_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table agenda;

drop table categoria;

drop table compromisso;

drop table contato;

drop table entidade_pai;

drop table log;

drop table tipo;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

