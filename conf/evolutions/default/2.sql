
# --- Sample dataset

# --- !Ups

insert into contato(id, nome, email, telefone) values (1, 'Haroldo', 'haroldo@haroldo.com', '4599813637');
insert into usuario(email, senha, padrao_do_sistema, contato_id) values ('haroldo@haroldo.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', true, 1);
insert into tipo(nome, padrao_do_sistema) values ('Alerta', true);
insert into tipo(nome, padrao_do_sistema) values ('Informativo', true);
insert into tipo(nome, padrao_do_sistema) values ('Inverso', true);
insert into tipo(nome, padrao_do_sistema) values ('Sucesso', true);
insert into tipo(nome, padrao_do_sistema) values ('Especial', true);
insert into tipo(nome, padrao_do_sistema) values ('Importante', true);