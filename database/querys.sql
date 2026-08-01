-- GESTÃO DE CLIENTES E LOCALIZAÇÃO

-- 1) Quem são nosso clientes?
SELECT * FROM cliente;

-- 2) Onde moram nossos clientes?
SELECT e.estado, COUNT(c.id_cliente) as qtd_cliente
FROM cliente as c
JOIN endereco as e on e.id_endereco = c.id_endereco
GROUP BY (e.estado)
ORDER BY (COUNT(c.id_cliente)) desc;

-- 3) Quem são os clientes VIP's (+R$1000 gasto)
SELECT id_cliente, SUM(valor_total) as gasto_total
FROM compra
group by id_cliente
HAVING SUM(valor_total) > 1000;

-- 4) Quais clientes se cadastraram mas nunca realizaram nenhuma compra?
SELECT id_cliente
FROM cliente
WHERE id_cliente NOT IN (
    SELECT id_cliente
    FROM compra
)

-- ESTOQUE E FORNECEDORES

-- 5) O que está acabando no estoque? (Alerta de reposição)
SELECT id_estoque
FROM estoque
WHERE quantidade_estoque <= quantidade_minima;

-- 6) Qual o valor totalizado do estoque atual?
SELECT SUM(quantidade_estoque * valor_produto) as total
FROM estoque as e 
JOIN produto as p on p.id_estoque = e.id_estoque;

-- 7) Quais produtos pertencem a qual fornecedor?
SELECT p.nome_produto, f.nome_fornecedor
FROM produto as p
JOIN fornecedor as f on f.id_fornecedor = p.id_fornecedor;

-- 8) Quantos produtos cada fornecedor nos entrega?
SELECT f.nome_fornecedor, COUNT(p.id_produto) as qtd_produtos
FROM produto as p 
JOIN fornecedor as f on f.id_fornecedor = p.id_fornecedor
GROUP BY p.id_fornecedor
ORDER BY (COUNT(p.id_produto)) desc;

-- Faturamento e vendas

-- 9) Quanto o e-commerce faturou no total?
SELECT SUM(valor_total) as total_ecommerce
FROM compra;

-- 10) Qual o valor médio das compras (Ticket médio)?
SELECT AVG(valor_total) as media_compra
FROM compra;


-- 11) Qual a maior compra feita no site?
SELECT MAX(valor_total) as maior_compra
from compra;

-- 12) Resumo de compras por cliente:
SELECT c.nome_cliente, 
COUNT(com.id_compra) as qtd_compras
FROM cliente as c 
JOIN compra as com on com.id_cliente = c.id_cliente
GROUP BY c.nome_cliente;

--  Desempenho de Categorias e Lucratividade

-- 13) Quais são as categorias que mais faturaram? Ordenadas do maior faturamento pro meno.
SELECT c.nome_categoria, 
SUM(pc.quantidade_produto * pc.valor_unitario) as total_faturamento
FROM produto_compra as pc
JOIN produto as p on p.id_produto = pc.id_produto
JOIN categoria as c on c.id_categoria = p.id_categoria
GROUP BY c.id_categoria
ORDER BY (SUM(pc.quantidade_produto * pc.valor_unitario)) desc;

-- 14) Qual é a média de itens (quantidade de produtos) que os clientes colocam por pedido no carrinho?
SELECT AVG(total_itens) as media_produto
FROM (
    SELECT SUM(quantidade_produto) as total_itens
    FROM produto_compra
    GROUP BY id_compra
) as subquery; 

-- 15) Quais estados do Brasil (UF) possuem o maior valor médio gasto por pedido? (Mostrar apenas com gastoas maiores que R$ 500)
SELECT e.estado, 
SUM(c.valor_total) as total_gasto
FROM compra as c
JOIN cliente as cl on cl.id_cliente = c.id_cliente
JOIN endereco as e on e.id_endereco = cl.id_endereco
GROUP BY e.estado
HAVING total_gasto > 500;

-- 16) Houve algum produto vendido com o preço unitário diferente do preçoa atual da tabela de produtos? Se sim, quais foram esses produtos, em quais compras isso aconteceu
-- e qual foi a diferença de valor?
SELECT c.id_compra, 
p.nome_produto, 
pc.valor_unitario, 
p.valor_produto, 
(p.valor_produto - pc.valor_unitario) as diferenca
FROM compra as c
JOIN produto_compra as pc on pc.id_compra = c.id_compra
JOIN produto as p on p.id_produto = pc.id_produto
WHERE pc.valor_unitario <> p.valor_produto;

-- Categorias e Produtos

-- 17 Quais são os produtos mais caros e mais baratos?
SELECT
(SELECT nome_produto 
 FROM produto 
 WHERE valor_produto = 
 (SELECT MAX(valor_produto) 
 FROM produto) 
 LIMIT 1) 
 AS mais_caro,
(SELECT nome_produto 
FROM produto 
WHERE valor_produto = 
(SELECT MIN(valor_produto) 
FROM produto) 
LIMIT 1) 
AS mais_barato;

-- 18 Quantos produtos existem por categoria?
SELECT nome_categoria,COUNT(P.id_categoria)
FROM produto AS P
JOIN categoria AS C ON C.id_categoria = P.id_categoria
GROUP BY nome_categoria

-- 19 Qual a categoria mais lucrativa?
SELECT C.nome_categoria, SUM(PC.valor_unitario * PC.quantidade_produto) AS total_faturado_categoria
FROM produto AS P
JOIN produto_compra AS PC ON PC.id_produto = PC.id_produto
JOIN categoria AS C ON C.id_categoria = P.id_categoria
GROUP BY C.id_categoria, C.nome_categoria
ORDER BY total_faturado_categoria DESC
LIMIT 1;

-- Itens do Pedido

-- 20 Quais são os produtos mais vendidos (Top 5)?
SELECT P.nome_produto, SUM(quantidade_produto) AS mais_vendidos
FROM produto_compra AS PC
JOIN produto AS P ON P.id_produto = PC.id_produto
GROUP BY nome_produto
ORDER BY mais_vendidos DESC
LIMIT 5;

-- 21 O que foi comprado em um pedido específico?
SELECT nome_produto, PC.quantidade_produto, valor_unitario
FROM produto AS P
JOIN produto_compra AS PC ON PC.id_produto = P.id_produto
WHERE id_compra = 5; -- ID do pedido desejado

-- 22 Busca de produtos por marca:
SELECT nome_produto 
FROM produto  AS P
WHERE marca_produto = 'Samsung' -- Marca desejada

