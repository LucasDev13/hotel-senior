create sequence tbl_checkin_hotel_seq start with 1 increment by 50;
create sequence tbl_hospede_seq start with 1 increment by 50;
create table tbl_checkin_hotel (adicional_veiculo boolean not null, parking_fee numeric(38,2), price_hotel numeric(38,2), data_entrada timestamp(6) not null, data_saida timestamp(6) not null, guest_id bigint unique, id bigint not null, primary key (id));
create table tbl_hospede (id bigint not null, documento varchar(255) not null, nome varchar(255) not null, telefone varchar(255) not null, primary key (id));
alter table if exists tbl_checkin_hotel add constraint FKib46flifiuv8dq6efmhves9qt foreign key (guest_id) references tbl_hospede;
