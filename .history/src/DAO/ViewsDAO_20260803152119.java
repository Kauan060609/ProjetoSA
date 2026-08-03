package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionSA;

public class ViewsDAO {

    private Connection conn = ConnectionSA.connect();

    public void showView(int option, int subOption){
        String sql = "";
        PreparedStatement ps;
        ResultSet rs;
        try {
            switch (option) {
                case 1: // Cliente
                    switch(subOption) {
                        case 1:
                            sql = "SELECT * FROM vw_perfil_cliente"; 
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("id_cliente"));
                                System.out.println("Nome: "+ rs.getString("nome_cliente"));
                                System.out.println("Email: " + rs.getString("email_cliente"));
                                System.out.println("Quantidade total de compras realizadas: " + rs.getInt("total_compras_realizadas"));
                                System.out.println("Total gasto: R$"+ rs.getDouble("total_gasto_historico"));
                            }     
                            break;
                        case 2:
                            sql = "SELECT * FROM perfil_publico_cliente";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("id_cliente"));
                                System.out.println("Nome: " + rs.getString("nome_cliente"));
                                System.out.println("Email: " + rs.getString("email_cliente"));
                                System.out.println("Telefone: " + rs.getString("telefone_cliente"));                    
                            }
                            break;
                        case 3:
                            sql = "SELECT * FROM vw_cliente_vip";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("id_cliente"));
                                System.out.println("Nome: " + rs.getString("nome_cliente"));
                                System.out.println("Email: " + rs.getString("email_cliente"));
                                System.out.println("Telefone: " + rs.getString("telefone_cliente"));
                                System.out.println("Cidade: " + rs.getString("cidade"));
                                System.out.println("Estado: " + rs.getString("estado"));
                                System.out.println("Total Gasto: R$" + rs.getDouble("total_gasto_acumulado"));

                            }   
                            break;
                        case 4:
                            sql = "SELECT * FROM vw_concentracao_clientes_cidade";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Estado: "+ rs.getString("estado"));
                                System.out.println("Cidade: " + rs.getString("cidade"));
                                System.out.println("Total de clientes cadastrados: " + rs.getInt("total_clientes_cadastrados"));
                                System.out.println("Total de pedidos entregues: "+ rs.getInt("total_pedidos_entregues"));
                            } 
                            break;


                    }
                case 2: // Estoque
                    sql = "SELECT * FROM vw_alerta_estoque_abaixo";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id_produto"));
                        System.out.println("Nome: "+ rs.getString("nome_produto") );
                        System.out.println("Marca: " + rs.getString("marca_produto") );
                        System.out.println("Fornecedor: " + rs.getString("nome_fornecedor") );
                        System.out.println("Quantdade em estoque:" + rs.getInt("quantidade_estoque"));
                        System.out.println("quantidade_minima: " + rs.getInt("quantidade_minima") );
                        System.out.println("Unidades necessárias: " + rs.getInt("unidades_necessarias"));
                    }
                    break;

               

                case 3: // Faturamento
                    
                    switch(subOption) {
                        case 1:
                            sql = "SELECT * FROM faturamento_estado";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Estado: " + rs.getString("estado"));
                                System.out.println("Faturamento: " + rs.getInt("total"));
                            }
                            break;
                        case 2:
                            sql = "SELECT * FROM vw_venda_marca";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("Marca: " + rs.getString("marca_produto"));
                                System.out.println("Total Produtos: " + rs.getInt("total_produtos_no_catalogo"));
                                System.out.println("Total Unidades Vendidas: " + rs.getInt("total_unidades_vendidas"));
                                System.out.println("Faturamento: " + rs.getInt("faturamento_total_marca"));
                            }
                            break;
                        case 3:
                            sql = "SELECT * FROM detalhes_pedido";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("id_compra"));
                                System.out.println("Cliente: " + rs.getString("nome_cliente"));
                                System.out.println("Descrição" + rs.getString("descricao"));
                                System.out.println("Produto" + rs.getString("nome_produto"));
                                System.out.println("Quantidade do Produto" + rs.getInt("quantidade_produto"));
                                System.out.println("Valor Unitário: " + rs.getDouble("valor_unitario"));
                                System.out.println("Valor Total" + rs.getDouble("valor_total"));
                                
                            }
                            break;
                    }
                case 4: // Fornecedores
                    sql = "SELECT * FROM ranking_fornecedores";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        System.out.println("Fornecedor: " + rs.getString("nome_fornecedor"));
                        System.out.println("Total vendido: " + rs.getInt("total_vendido"));
                    }
                    
                default:
                    System.out.println("View não encontrada");
                    return;
            }
            
        } catch (SQLException sqlError) {
            System.out.println("Erro na database: " + sqlError.getMessage());
        }
    }
    
}
