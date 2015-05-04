
# --- Sample dataset

# --- !Ups

insert into usuario(email, senha, padrao_do_sistema) values ('admin@admin.com', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', true);
insert into usuario(email, senha, padrao_do_sistema) values ('haroldo', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', true);

insert into tipo(nome, padrao_do_sistema) values ('Importante', true);
insert into tipo(nome, padrao_do_sistema) values ('Alerta', true);
insert into tipo(nome, padrao_do_sistema) values ('Informativo', true);
insert into tipo(nome, padrao_do_sistema) values ('Inverso', true);
insert into tipo(nome, padrao_do_sistema) values ('Sucesso', true);
insert into tipo(nome, padrao_do_sistema) values ('Especial', true);