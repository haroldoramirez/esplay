# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table evento (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint pk_evento primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table evento;

SET FOREIGN_KEY_CHECKS=1;

