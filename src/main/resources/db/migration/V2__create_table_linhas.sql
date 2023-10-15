create table linhas(
                       id bigint not null auto_increment,
                       codigo varchar(4) not null,
                       nome varchar(50) not null,
                       tarifa double not null,
                       empresa_id int,
                       ativo tinyint,
                       criacao DATETIME,
                       FOREIGN KEY(empresa_id) references empresas(id),
                       primary key(id)
)