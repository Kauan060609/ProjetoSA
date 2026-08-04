-- Active: 1782847720360@@mysql-14b97700-estudante-d3ec.g.aivencloud.com@13219@projeto_sa
create table endereco(
    id_endereco int primary key auto_increment not null unique,
    pais varchar(20) not null,
    estado char(2) not null,
    cidade varchar(20) not null,
    rua varchar(20) not null,
    numero int not null,
    complemento text
);

create table cliente(
    id_cliente int primary key auto_increment not null unique,
    nome_cliente varchar(100) not null,
    cpf_cliente char(11) unique not null,
    telefone_cliente varchar(20) unique not null,
    email_cliente varchar(100) unique,
    cep_cliente varchar(9) not null,
    senha varchar(40) not null,
    id_endereco int,
    foreign key (id_endereco) references endereco(id_endereco)
);

create table compra (
    id_compra int primary key auto_increment unique not null,
    descricao text,
    valor_total decimal(10,2) not null,
    id_cliente int,
    foreign key (id_cliente) references cliente (id_cliente)
);

create table categoria(
    id_categoria int primary key auto_increment not null unique,
    nome_categoria varchar(30) not null unique,
    descricao_categoria varchar(100) not null
);

create table estoque(
    id_estoque int primary key auto_increment not null unique,
    quantidade_minima int not null,
    quantidade_estoque int not null
);

create table fornecedor(
    id_fornecedor int primary key auto_increment not null unique ,
    nome_fornecedor varchar(60) not null,
    cnpj_fornecedor char(14) not null unique
);

create table produto(
    id_produto int primary key auto_increment not null unique,
    nome_produto varchar(80) not null,
    marca_produto varchar(100) not null,
    valor_produto decimal(10,2) not null,
    id_categoria int,
    foreign key (id_categoria) references categoria(id_categoria),
    id_estoque int,
    foreign key (id_estoque) references estoque(id_estoque),
    id_fornecedor int,
    foreign key (id_fornecedor) references fornecedor(id_fornecedor )
);

create table produto_compra (
    id_produto_compra int primary key auto_increment unique not null,
    quantidade_produto int not null,
    valor_unitario decimal(10,2) not null,
    id_produto int,
    id_compra int,
    foreign key (id_compra) references compra (id_compra),
    foreign key (id_produto) references produto (id_produto)
);