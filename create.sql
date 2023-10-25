create table tbl_hospede (hotel_reservation_id bigint unique, id bigint not null, id_hotel bigint, documento varchar(255) not null unique, nome varchar(255) not null, telefone varchar(255) not null, primary key (id));
create table tbl_hotel (id bigint not null, name varchar(255), primary key (id));
create table tbl_hotel_reservation (adicional_veiculo boolean, preco_diaria numeric(38,2), valor_garagem numeric(38,2), data_entrada timestamp(6), data_saida timestamp(6), hospede_id bigint, id bigint not null, primary key (id));
alter table if exists tbl_hospede add constraint FKbapcmwvt9umf6g7dcey8yx2um foreign key (id_hotel) references tbl_hotel;
alter table if exists tbl_hospede add constraint FK1gompo74mhrt65255k7461ywy foreign key (hotel_reservation_id) references tbl_hotel_reservation;
alter table if exists tbl_hotel_reservation add constraint FK7cygs1mwtg65wyjtx3gw5yilr foreign key (hospede_id) references tbl_hospede;
insert into tbl_hotel(id, name) values(1, 'hotel transilvania');
create sequence tbl_hospede_seq start with 1 increment by 50;
create sequence tbl_hotel_reservation_seq start with 1 increment by 50;
create sequence tbl_hotel_seq start with 1 increment by 50;

