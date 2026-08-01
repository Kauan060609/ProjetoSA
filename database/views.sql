-- 1) Estoque abaixo
CREATE VIEW vw_alerta_estoque_abaixo AS 
SELECT p.id_produto,p.nome_produto,p.marca_produto, f.nome_fornecedor, e.quantidade_estoque, e.quantidade_minima, (e.quantidade_minima - e.quantidade_estoque) AS unidades_necessarias
FROM produto AS p 
JOIN estoque AS e ON e.id_estoque = p.id_estoque
JOIN fornecedor AS f on p.id_fornecedor = f.id_fornecedor
WHERE e.quantidade_estoque < quantidade_minima

-- 2) Perfil do Cliente
CREATE VIEW vw_perfil_cliente AS 
SELECT cl.id_cliente, cl.nome_cliente, cl.email_cliente, COUNT(co.id_compra) AS total_compras_realizadas, SUM(co.valor_total) AS total_gasto_historico
FROM cliente AS cl
JOIN compra AS co ON co.id_cliente = cl.id_cliente
GROUP BY cl.id_cliente, cl.nome_cliente, cl.email_cliente

-- 3) Clientes VIP
CREATE VIEW vw_cliente_vip AS 
SELECT cl.id_cliente, cl.nome_cliente, cl.email_cliente, cl.telefone_cliente, en.cidade, en.estado, SUM(co.valor_total) AS total_gasto_acumulado
FROM cliente AS cl 
JOIN endereco AS en ON en.id_endereco = cl.id_endereco
JOIN compra AS co ON co.id_cliente = cl.id_cliente

-- 4) Venda por marca
CREATE VIEW vw_venda_marca AS 
SELECT p.marca_produto, COUNT(DISTINCT p.id_produto) AS total_produtos_no_catalogo, SUM(pc.quantidade_produto) AS total_unidades_vendidas, SUM(pc.quantidade_produto * pc.valor_unitario) AS faturamento_total_marca
FROM produto AS p
JOIN produto_compra AS pc ON pc.id_produto = p.id_produto
GROUP BY p.marca_produto

-- 5) Concentração de Cidades
CREATE VIEW vw_concentracao_clientes_cidade AS
SELECT en.estado, en.cidade, COUNT(c.id_cliente) AS total_clientes_cadastrados, COUNT(DISTINCT co.id_compra) AS total_pedidos_entregues
FROM endereco as en 
JOIN cliente AS c ON c.id_endereco = en.id_endereco
JOIN compra AS co ON co.id_cliente = c.id_cliente
GROUP BY en.estado, en.cidade

-- 16) Perfil público
CREATE VIEW perfil_publico_cliente AS
SELECT id_cliente, nome_cliente, email_cliente, telefone_cliente
FROM cliente;

SELECT * FROM perfil_publico_cliente;

-- 7) Alerta de reposição
CREATE VIEW alerta_reposicao AS
SELECT 
p.id_produto,
p.nome_produto,
e.quantidade_estoque,
e.quantidade_minima,
f.nome_fornecedor
FROM produto as p
JOIN estoque as e on e.id_estoque = p.id_estoque
JOIN fornecedor as f on f.id_fornecedor = p.id_fornecedor
WHERE e.quantidade_estoque <= e.quantidade_minima;

SELECT * FROM alerta_reposicao;
-- 8) Faturamento por estado
CREATE VIEW faturamento_estado AS
SELECT e.estado,
SUM(c.valor_total) as total
FROM compra as c
JOIN cliente as cl on cl.id_cliente = c.id_cliente
JOIN endereco as e on e.id_endereco = cl.id_endereco
GROUP BY e.estado;

SELECT * FROM faturamento_estado;

-- 9) Histórico de pedidos
CREATE VIEW detalhes_pedido AS
SELECT c.id_compra, 
cl.nome_cliente,
c.descricao,
p.nome_produto,
pc.quantidade_produto,
pc.valor_unitario,
c.valor_total
FROM compra as c 
JOIN cliente as cl on cl.id_cliente = c.id_cliente
JOIN produto_compra as pc on pc.id_compra = c.id_compra
JOIN produto as p on p.id_produto = pc.id_produto;

SELECT * FROM detalhes_pedido;

-- 10) Ranking de Fornecedores
CREATE VIEW ranking_fornecedores AS
SELECT f.nome_fornecedor, 
SUM(pc.quantidade_produto * pc.valor_unitario) as total_vendido
FROM compra as c
JOIN produto_compra as pc on pc.id_compra = c.id_compra
JOIN produto as p on p.id_produto = pc.id_produto
JOIN fornecedor as f on f.id_fornecedor = p.id_fornecedor
GROUP BY nome_fornecedor
ORDER BY total_vendido DESC;

SELECT * FROM ranking_fornecedores;