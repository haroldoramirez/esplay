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
  padrao_do_sistema         tinyint(1) default 0 not null,
  nome                      varchar(255) not null,
  dono_id                   bigint,
  constraint uq_categoria_nome unique (nome),
  constraint pk_categoria primary key (id))
;

create table compromisso (
  id                        bigint auto_increment not null,
  titulo                    varchar(255) not null,
  titulo2                   varchar(255),
  data_inicio               datetime,
  hora_inicio               datetime,
  data_fim                  datetime,
  hora_fim                  datetime,
  descricao                 varchar(255),
  observacoes               varchar(255),
  local                     varchar(255),
  status                    varchar(7),
  agenda_id                 bigint,
  usuario_id                bigint,
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
  dono_id                   bigint,
  usuario_id                bigint,
  constraint pk_contato primary key (id))
;

create table entidade_pai (
  padrao_do_sistema         tinyint(1) default 0 not null)
;

create table tipo (
  id                        bigint auto_increment not null,
  padrao_do_sistema         tinyint(1) default 0 not null,
  nome                      varchar(255) not null,
  constraint uq_tipo_nome unique (nome),
  constraint pk_tipo primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  padrao_do_sistema         tinyint(1) default 0 not null,
  email                     varchar(255) not null,
  senha                     varchar(255) not null,
  agenda_id                 bigint,
  constraint uq_usuario_email unique (email),
  constraint pk_usuario primary key (id))
;

alter table categoria add constraint fk_categoria_dono_1 foreign key (dono_id) references usuario (id) on delete restrict on update restrict;
create index ix_categoria_dono_1 on categoria (dono_id);
alter table compromisso add constraint fk_compromisso_agenda_2 foreign key (agenda_id) references agenda (id) on delete restrict on update restrict;
create index ix_compromisso_agenda_2 on compromisso (agenda_id);
alter table compromisso add constraint fk_compromisso_usuario_3 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_compromisso_usuario_3 on compromisso (usuario_id);
alter table compromisso add constraint fk_compromisso_tipo_4 foreign key (tipo_id) references tipo (id) on delete restrict on update restrict;
create index ix_compromisso_tipo_4 on compromisso (tipo_id);
alter table compromisso add constraint fk_compromisso_contato_5 foreign key (contato_id) references contato (id) on delete restrict on update restrict;
create index ix_compromisso_contato_5 on compromisso (contato_id);
alter table compromisso add constraint fk_compromisso_categoria_6 foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_compromisso_categoria_6 on compromisso (categoria_id);
alter table contato add constraint fk_contato_dono_7 foreign key (dono_id) references usuario (id) on delete restrict on update restrict;
create index ix_contato_dono_7 on contato (dono_id);
alter table contato add constraint fk_contato_usuario_8 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_contato_usuario_8 on contato (usuario_id);
alter table usuario add constraint fk_usuario_agenda_9 foreign key (agenda_id) references agenda (id) on delete restrict on update restrict;
create index ix_usuario_agenda_9 on usuario (agenda_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table agenda;

drop table categoria;

drop table compromisso;

drop table contato;

drop table entidade_pai;

drop table tipo;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

