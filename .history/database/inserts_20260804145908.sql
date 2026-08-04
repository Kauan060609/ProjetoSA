St

-- ESTOQUE
INSERT INTO estoque (id_estoque, quantidade_minima, quantidade_estoque) VALUES
(1, 10, 50),
(2, 5, 20),
(3, 15, 100),
(4, 8, 35),
(5, 20, 150),
(6, 2, 15),
(7, 5, 40),
(8, 12, 60),
(9, 3, 8),
(10, 25, 200);

-- CATEGORIA
INSERT INTO categoria (id_categoria, nome_categoria, descricao_categoria) VALUES
(1, 'Eletrônicos', 'Dispositivos eletrônicos e gadgets'),
(2, 'Vestuário', 'Roupas e acessórios de moda'),
(3, 'Alimentos', 'Produtos alimentícios e bebidas'),
(4, 'Livros', 'Livros físicos, e-books e HQs'),
(5, 'Beleza e Saúde', 'Maquiagens, perfumes e cuidados pessoais'),
(6, 'Casa e Decoração', 'Móveis, utensílios domésticos e decoração');

-- FORNECEDOR
INSERT INTO fornecedor (id_fornecedor, nome_fornecedor, cnpj_fornecedor) VALUES
(1, 'Tech Distribuidora Ltda', '12345678000199'),
(2, 'Moda Brasil Confecções', '98765432000188'),
(3, 'Global Alimentos S.A.', '55566677000122'),
(4, 'Distribuidora Alta Books', '44455566000133'),
(5, 'Loreal Cosméticos Brasil', '22233344000155'),
(6, 'Móveis Marabraz S.A.', '88899900000144');

-- ENDERECO
INSERT INTO endereco (id_endereco, pais, estado, cidade, rua, numero, complemento) VALUES
(1, 'Brasil', 'SP', 'São Paulo', 'Av. Paulista', 1000, 'Apto 42'),
(2, 'Brasil', 'RJ', 'Rio de Janeiro', 'Rua Copacabana', 250, 'Bloco B'),
(3, 'Brasil', 'MG', 'Belo Horizonte', 'Rua da Bahia', 500, NULL),
(4, 'Brasil', 'PR', 'Curitiba', 'Rua XV de Novembro', 1200, 'Conjunto 4'),
(5, 'Brasil', 'RS', 'Porto Alegre', 'Av. Ipiranga', 3000, NULL),
(6, 'Brasil', 'BA', 'Salvador', 'Av. Sete de Setembro', 85, 'Sala 102'),
(7, 'Brasil', 'SP', 'Campinas', 'Rua Barão de Jaguara', 1420, 'Casa');

-- CLIENTE
-- CLIENTE
INSERT INTO cliente (id_cliente, nome_cliente, cpf_cliente, telefone_cliente, email_cliente, cep_cliente, senha, id_endereco) VALUES
(1, 'Carlos Silva', '11122233344', '11999998888', 'carlos@email.com', '01310-100', left(sha2('senha123', 256) , 30 ) , 1),
(2, 'Ana Souza', '55566677788', '21988887777', 'ana@email.com', '22020-001', left(sha2('ana@2026', 256) , 30 ) , 2),
(3, 'Marcos Oliveira', '99988877766', '31977776666', 'marcos@email.com', '30160-010', left(sha2('marcos_pass' , 256 ) , 30 ) , 3),
(4, 'Mariana Costa', '44455566622', '41966665555', 'mariana@email.com', '80020-000', left(sha2('mari_2026' , 256 ) , 30 ) , 4),
(5, 'Lucas Almeida', '22288899911', '51955554444', 'lucas@email.com', '90160-090', left(sha2('lucas_pass!' , 256 ) , 30 ) , 5),
(6, 'Beatriz Santos', '77733311155', '71944443333', 'beatriz@email.com', '40060-001', left(sha2('biasecret' , 256 ) , 30 ) , 6),
(7, 'Ricardo Rezende', '88844422200', '19933332222', 'ricardo@email.com', '13015-002', left(sha2('ric_9988', 256 ) , 30 ) , 7);

-- PRODUTO
INSERT INTO produto (id_produto, nome_produto, marca_produto, valor_produto, id_categoria, id_estoque, id_fornecedor) VALUES
(1, 'Smartphone Galaxy S24', 'Samsung', 4500.00, 1, 1, 1),
(2, 'Camiseta Algodão Premium', 'Hering', 89.90, 2, 2, 2),
(3, 'Café Gourmet 500g', 'Três Corações', 24.50, 3, 3, 3),
(4, 'Notebook Ultra Slim', 'Asus', 3899.00, 1, 4, 1),
(5, 'Fone de Ouvido Bluetooth', 'JBL', 299.90, 1, 5, 1),
(6, 'Livro: O Poder do Hábito', 'Objetiva', 49.90, 4, 6, 4),
(7, 'Protetor Solar FPS 60', 'La Roche', 89.90, 5, 7, 5),
(8, 'Perfume Bleu de Chanel', 'Chanel', 750.00, 5, 8, 5),
(9, 'Cadeira de Escritório Ergonômica', 'Flexform', 640.00, 6, 9, 6),
(10, 'Jogo de Panelas Antiaderente', 'Tramontina', 320.00, 6, 10, 6);

-- COMPRA
INSERT INTO compra (id_compra, descricao, valor_total, id_cliente) VALUES
(1, 'Compra de eletrônico e vestuário', 4589.90, 1),
(2, 'Compra de supermercado mensal', 49.00, 2),
(3, 'Presente de aniversário e livros', 1100.00, 3),
(4, 'Upgrade para home office', 4539.00, 4),       
(5, 'Renovação de cosméticos', 179.80, 5),       
(6, 'Compra rápida de fone', 299.90, 6),            
(7, 'Presente de luxo e leitura', 799.90, 1);     

INSERT INTO produto_compra (id_produto_compra, quantidade_produto, valor_unitario, id_produto, id_compra) VALUES
(1, 1, 4500.00, 1, 1),
(2, 1, 89.90, 2, 1),
(3, 1, 49.00, 3, 2), 
(4, 1, 750.00, 8, 3),   
(5, 1, 320.00, 10, 3),  
(6, 2, 15.00, 3, 3),   
(7, 1, 3899.00, 4, 4),  
(8, 1, 640.00, 9, 4),  
(9, 2, 89.90, 7, 5),   
(10, 1, 299.90, 5, 6), 
(11, 1, 750.00, 8, 7), 
(12, 1, 49.90, 6, 7);

