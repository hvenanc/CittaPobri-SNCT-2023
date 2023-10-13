create table empresas(
                         id bigint not null auto_increment,
                         cnpj varchar(100) not null,
                         nome varchar(100) not null,
                         ativo tinyint,
                         criacao DATETIME,

                         primary key(id)

)