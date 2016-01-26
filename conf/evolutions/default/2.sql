
# --- Sample dataset

# --- !Ups

insert into contato(id, nome, email, telefone) values (1, 'Administrador', 'admin@esplay.com', '');
insert into usuario(email, senha, padrao_do_sistema, privilegio) values ('admin@esplay.com', '123', true, 2);
insert into tipo(nome, padrao_do_sistema) values ('warning', true);
insert into tipo(nome, padrao_do_sistema) values ('info', true);
insert into tipo(nome, padrao_do_sistema) values ('inverse', true);
insert into tipo(nome, padrao_do_sistema) values ('success', true);
insert into tipo(nome, padrao_do_sistema) values ('special', true);
insert into tipo(nome, padrao_do_sistema) values ('important', true);