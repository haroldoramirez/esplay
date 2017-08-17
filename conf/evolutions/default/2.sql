-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile


# --- Sample dataset

# --- !Ups

insert into contato(id, nome, email, telefone) values (1, 'Contato 1', 'contato1@esplay.com', '');
insert into contato(id, nome, email, telefone) values (2, 'Contato 2', 'contato2@esplay.com', '');
insert into contato(id, nome, email, telefone) values (3, 'Contato 3', 'contato3@esplay.com', '');
insert into contato(id, nome, email, telefone) values (4, 'Contato 4', 'contato4@esplay.com', '');
insert into contato(id, nome, email, telefone) values (5, 'Contato 5', 'contato5@esplay.com', '');
insert into contato(id, nome, email, telefone) values (6, 'Contato 6', 'contato6@esplay.com', '');
insert into contato(id, nome, email, telefone) values (7, 'Contato 7', 'contato7@esplay.com', '');
insert into contato(id, nome, email, telefone) values (8, 'Contato 8', 'contato8@esplay.com', '');
insert into usuario(id, email, senha, padrao_do_sistema, privilegio) values (1, 'admin@esplay.com', '123', true, 2);
insert into usuario(id, email, senha, padrao_do_sistema, privilegio) values (2, 'admin2@esplay.com', '123', true, 2);
insert into usuario(id, email, senha, padrao_do_sistema, privilegio) values (3, 'admin3@esplay.com', '123', true, 2);
insert into usuario(id, email, senha, padrao_do_sistema, privilegio) values (4, 'admin4@esplay.com', '123', true, 2);
insert into usuario(id, email, senha, padrao_do_sistema, privilegio) values (5, 'admin5@esplay.com', '123', true, 2);
insert into usuario(id, email, senha, padrao_do_sistema, privilegio) values (6, 'admin6@esplay.com', '123', true, 2);
insert into tipo(nome, padrao_do_sistema) values ('warning', true);
insert into tipo(nome, padrao_do_sistema) values ('info', true);
insert into tipo(nome, padrao_do_sistema) values ('inverse', true);
insert into tipo(nome, padrao_do_sistema) values ('success', true);
insert into tipo(nome, padrao_do_sistema) values ('special', true);
insert into tipo(nome, padrao_do_sistema) values ('important', true);