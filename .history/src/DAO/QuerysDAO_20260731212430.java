package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.ConnectionSA;

public class QuerysDAO {

    private Connection conn = ConnectionSA.connect();

    public void showQuery(int option, int subOption){
        String sql = "";
        PreparedStatement ps;
        ResultSet rs;

        try {
            switch (option) {
                case 1: 
                    switch(subOption) {
                        case 1: // Quem são os nossos Clientes?
                            sql = "SELECT * FROM cliente;"; 
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("id_cliente"));
                                System.out.println("Nome: "+ rs.getString("nome_cliente"));
                                System.out.println("Cpf: " + rs.getString("cpf_cliente"));
                                System.out.println("Telefone: " + rs.getString("telefone_cliente"));
                                System.out.println("Cep: " + rs.getString("cep_cliente"));
                                System.out.println("Senha: "+ rs.getString("senha"));
                                System.out.println("Id Endereço:  "+ rs.getInt("id_endereco"));
                            }     
                            break;
                        case 2: //Quem são os clientes VIP's (+R$1000 gasto)
                            sql = "SELECT id_cliente, SUM(valor_total) as gasto_total FROM compra group by id_cliente HAVING SUM(valor_total) > 1000;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("id_cliente"));
                                System.out.println("Gasto: : " + rs.getDouble("gasto_total"));
                   
                            }
                            break;
                        case 3: //Quais clientes se cadastraram mas nunca realizaram nenhuma compra?
                            sql = "SELECT id_cliente FROM cliente WHERE id_cliente NOT IN (SELECT id_cliente FROM compra)";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("id_cliente"));
                            }   
                            break;
                        case 4: // Resumo de compras por cliente:
                            sql = "SELECT c.nome_cliente,COUNT(com.id_compra) as qtd_compras FROM cliente as c  JOIN compra as com on com.id_cliente = c.id_cliente GROUP BY c.nome_cliente;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Nome: "+ rs.getString("nome_cliente"));
                                System.out.println("Quantidade de Compras: " + rs.getInt("qtd_compras"));
                            } 
                            break;


                    }
                    break;
                case 2: 
                switch (subOption) {
                    case 1: // // Estoque - Onde moram nossos clientes?
                        sql = "SELECT e.estado, COUNT(c.id_cliente) as qtd_cliente FROM cliente as c JOIN endereco as e on e.id_endereco = c.id_endereco GROUP BY (e.estado) ORDER BY (COUNT(c.id_cliente)) desc;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Estado: "+ rs.getString("estado"));
                                System.out.println("Quantidade de Clientes: " + rs.getInt("qtd_cliente"));
                            } 
                        break;
                
                    case 2: // Quais estados do Brasil (UF) possuem o maior valor médio gasto por pedido? (Mostrar apenas com gastoas maiores que R$ 500)
                        sql = "SELECT e.estado,  SUM(c.valor_total) as total_gasto FROM compra as c JOIN cliente as cl on cl.id_cliente = c.id_cliente JOIN endereco as e on e.id_endereco = cl.id_endereco GROUP BY e.estado HAVING total_gasto > 500;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Estado: "+ rs.getString("estado"));
                                System.out.println("Total Gasto: " + rs.getInt("total_gasto"));
                            } 
                        break;
                
                }
                break;
          
                case 3: //  Estoque e Fornecedores
                    
                    switch(subOption) {
                        case 1: //O que está acabando no estoque? (Alerta de reposição)
                            sql = "SELECT id_estoque FROM estoque WHERE quantidade_estoque <= quantidade_minima;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getString("id_estoque"));
                            }
                            break;
                        case 2: //Qual o valor totalizado do estoque atual?
                            sql = "SELECT SUM(quantidade_estoque * valor_produto) as total FROM estoque as e JOIN produto as p on p.id_estoque = e.id_estoque;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Total: " + rs.getDouble("total"));
                                
                            }
                            break;
                        case 3: //Quais produtos pertencem a qual fornecedor?
                            sql = "SELECT p.nome_produto, f.nome_fornecedor FROM produto as p JOIN fornecedor as f on f.id_fornecedor = p.id_fornecedor;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Nome Produto: " + rs.getString("nome_produto"));
                                System.out.println("Nome Fornecedor" + rs.getString("nome_fornecedor"));  
                            }
                            break;

                        case 4: //Quantos produtos cada fornecedor nos entrega?
                            sql = "SELECT f.nome_fornecedor, COUNT(p.id_produto) as qtd_produtos FROM produto as p  JOIN fornecedor as f on f.id_fornecedor = p.id_fornecedor GROUP BY p.id_fornecedor ORDER BY (COUNT(p.id_produto)) desc;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Nome Fornecedor: " + rs.getString("nome_fornecedor"));
                                System.out.println("Quantidade de Produtos Entregues" + rs.getInt("qtd_produtos"));  
                            }
                            break;
                    }
                    break;
                case 4: // Fornecedores
                    switch(subOption) {
                        case 1:
                            sql = "SELECT SUM(valor_total) as total_ecommerce FROM compra";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Total faturado: R$"+ rs.getDouble("total_ecommerce"));
                            } 
                            break;
                        case 2:
                            sql = "SELECT AVG(valor_total) as media_compra FROM compra;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Valor médio por compra: R$"+ rs.getDouble("media_compra"));
                            } 
                            break;
                        case 3:
                            sql = "SELECT MAX(valor_total) as maior_compra FROM compra;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Maior compra: R$"+ rs.getDouble("maior_compra"));
                            } 
                            break;
                        case 4:
                            sql = "SELECT c.id_compra, p.nome_produto, pc.valor_unitario, p.valor_produto, (p.valor_produto - pc.valor_unitario) as diferenca FROM compra as c JOIN produto_compra as pc on pc.id_compra = c.id_compra JOIN produto as p on p.id_produto = pc.id_produto WHERE pc.valor_unitario <> p.valor_produto;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: "+ rs.getInt("id_compra"));
                                System.out.println("Nome do produto: " + rs.getString("nome_produto"));
                                System.out.println("Valor pago: R$"+ rs.getDouble("valor_unitario"));
                                System.out.println("Valor atual do produto: R$"+ rs.getDouble("valor_produto"));
                                System.out.println("Diferença: R$"+ rs.getDouble("diferenca"));
                            } 
                            break;
                            
                    }
                    break;
                
                case 5:
                    switch (subOption) {
                        case 1: //Quais são as categorias que mais faturaram? Ordenadas do maior faturamento pro meno.
                            sql = "SELECT c.nome_categoria,  SUM(pc.quantidade_produto * pc.valor_unitario) as total_faturamento FROM produto_compra as pc JOIN produto as p on p.id_produto = pc.id_produto JOIN categoria as c on c.id_categoria = p.id_categoria GROUP BY c.id_categoria ORDER BY (SUM(pc.quantidade_produto * pc.valor_unitario)) desc;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Categoria: "+ rs.getString("nome_categoria"));
                                System.out.println("Faturamento: " + rs.getDouble("total_faturamento"));

                            } 
                            break;

                        case 2: // Quantos produtos existem por categoria?
                            sql = "SELECT c.nome_categoria, COUNT(p.id_produto) as qtd_produtos FROM produto as p JOIN categoria as c on c.id_categoria = p.id_categoria GROUP BY c.nome_categoria";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Categoria: "+ rs.getString("nome_categoria"));
                                System.out.println("Quantidade de produtos: "+ rs.getInt("qtd_produtos"));
                            } 
                            break;

                        case 3: // Qual a categoria mais lucrativa?
                            sql = "SELECT C.nome_categoria, SUM(PC.valor_unitario * PC.quantidade_produto) AS total_faturado_categoria FROM produto AS P JOIN produto_compra AS PC ON PC.id_produto = P.id_produto JOIN categoria AS C ON C.id_categoria = P.id_categoria GROUP BY C.id_categoria, C.nome_categoria ORDER BY total_faturado_categoria DESC LIMIT 1;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Categoria: "+ rs.getString("nome_categoria"));
                                System.out.println("Faturamento: " + rs.getDouble("total_faturado_categoria"));
                           
                            } 
                            break;
                    
                        
                    }
                    break;
                case 6:
                    switch(subOption) {
                        case 1:
                            sql = "SELECT AVG(total_itens) as media_produto FROM (SELECT SUM(quantidade_produto) as total_itens FROM produto_compra GROUP BY id_compra) as subquery; ";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Média de produtos: "+ rs.getDouble("media_produto"));
                            } 
                            break;
                        case 2:
                            sql = "SELECT (SELECT nome_produto FROM produto WHERE valor_produto = (SELECT MAX(valor_produto) FROM produto) LIMIT 1) AS mais_caro, (SELECT nome_produto FROM produto WHERE valor_produto = (SELECT MIN(valor_produto) FROM produto) LIMIT 1) AS mais_barato;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Mais caro: "+ rs.getString("mais_caro"));
                                System.out.println("Mais barato: "+ rs.getString("mais_barato"));
                            } 
                            break;
                        case 3:
                            sql = "SELECT P.nome_produto, SUM(quantidade_produto) AS mais_vendidos FROM produto_compra AS PC JOIN produto AS P ON P.id_produto = PC.id_produto GROUP BY nome_produto ORDER BY mais_vendidos DESC LIMIT 5;";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Nome produto: "+ rs.getString("nome_produto"));
                                System.out.println("Mais vendidos: "+ rs.getInt("mais_vendidos"));    
                            } 
                            break;
                        case 4:
                            sql = "SELECT nome_produto, PC.quantidade_produto, valor_unitario FROM produto AS P JOIN produto_compra AS PC ON PC.id_produto = P.id_produto WHERE id_compra = 5; ";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Nome produto: "+ rs.getString("nome_produto"));
                                System.out.println("Quantidade: "+ rs.getInt("quantidade_produto"));  
                                System.out.println("Valor unitário: R$"+ rs.getDouble("valor_unitario"));  
                            } 
                            break;
                        case 5:
                            sql = "SELECT nome_produto, marca_produto FROM produto AS P WHERE marca_produto = 'Samsung'";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Nome produto: "+ rs.getString("nome_produto"));
                                System.out.println("Marca: "+ rs.getString("marca_produto"));  
                            } 
                            break;
                    }
            }
            
        } catch (SQLException sqlError) {
            System.out.println("Erro na database: " + sqlError.getMessage());
        }
    }
    
}
