# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categoria (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  dono_id                   bigint,
  constraint pk_categoria primary key (id))
;

create table compromisso (
  id                        bigint auto_increment not null,
  titulo                    varchar(255) not null,
  data_inicio               timestamp,
  hora_inicio               timestamp,
  data_fim                  timestamp,
  hora_fim                  timestamp,
  descricao                 varchar(255),
  observacoes               varchar(255),
  local                     varchar(255),
  status                    varchar(7),
  tipo_id                   bigint,
  categoria_id              bigint,
  contato_id                bigint,
  dono_id                   bigint,
  constraint ck_compromisso_status check (status in ('OCUPADO','LIVRE')),
  constraint pk_compromisso primary key (id))
;

create table contato (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  email                     varchar(255),
  telefone                  varchar(255),
  dono_id                   bigint,
  constraint pk_contato primary key (id))
;

create table log (
  id                        bigint auto_increment not null,
  data_do_log               timestamp,
  mensagem                  varchar(255),
  constraint pk_log primary key (id))
;

create table tipo (
  id                        bigint auto_increment not null,
  nome                      varchar(255) not null,
  padrao_do_sistema         boolean,
  constraint pk_tipo primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  email                     varchar(255) not null,
  senha                     varchar(255) not null,
  padrao_do_sistema         boolean,
  privilegio                integer,
  constraint uq_usuario_email unique (email),
  constraint pk_usuario primary key (id))
;


create table compromisso_usuario (
  compromisso_id                 bigint not null,
  usuario_id                     bigint not null,
  constraint pk_compromisso_usuario primary key (compromisso_id, usuario_id))
;
alter table categoria add constraint fk_categoria_dono_1 foreign key (dono_id) references usuario (id) on delete restrict on update restrict;
create index ix_categoria_dono_1 on categoria (dono_id);
alter table compromisso add constraint fk_compromisso_tipo_2 foreign key (tipo_id) references tipo (id) on delete restrict on update restrict;
create index ix_compromisso_tipo_2 on compromisso (tipo_id);
alter table compromisso add constraint fk_compromisso_categoria_3 foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_compromisso_categoria_3 on compromisso (categoria_id);
alter table compromisso add constraint fk_compromisso_contato_4 foreign key (contato_id) references contato (id) on delete restrict on update restrict;
create index ix_compromisso_contato_4 on compromisso (contato_id);
alter table compromisso add constraint fk_compromisso_dono_5 foreign key (dono_id) references usuario (id) on delete restrict on update restrict;
create index ix_compromisso_dono_5 on compromisso (dono_id);
alter table contato add constraint fk_contato_dono_6 foreign key (dono_id) references usuario (id) on delete restrict on update restrict;
create index ix_contato_dono_6 on contato (dono_id);



alter table compromisso_usuario add constraint fk_compromisso_usuario_compro_01 foreign key (compromisso_id) references compromisso (id) on delete restrict on update restrict;

alter table compromisso_usuario add constraint fk_compromisso_usuario_usuari_02 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists categoria;

drop table if exists compromisso;

drop table if exists compromisso_usuario;

drop table if exists contato;

drop table if exists log;

drop table if exists tipo;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

