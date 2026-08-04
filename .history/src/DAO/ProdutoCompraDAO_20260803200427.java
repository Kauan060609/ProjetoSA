package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.*;
import connection.ConnectionSA;
import model.ProdutoCompra;

public class ProdutoCompraDAO {

    private Connection conn = ConnectionSA.connect();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private CompraDAO compraDAO = new CompraDAO();

    public void create(ProdutoCompra pc)throws SQLException , ValorInvalidoException{

    String sql = "INSERT INTO produto_compra(quantidade_produto, valor_unitario, id_produto, id_compra) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, pc.getQuantidade_produto());
        ps.setDouble(2, pc.getValor_unitario());
        ps.setInt(3, pc.getProduto().getId_produto());
        ps.setInt(4, pc.getCompra().getId_compra());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        pc.setId_produto_compra(rs.getInt("GENERATED_KEY"));
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM produto_compra";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Carrinho de Compras:\n\tID: %d\n\tQuantidade: %d\n\tValor unitário: R$%.2f\n\tID produto: %d\n\tID compra: %d", 
            rs.getInt("id_produto_compra"), rs.getInt("quantidade_produto"), rs.getDouble("valor_unitario"), rs.getInt("id_produto"), rs.getInt("id_compra"));
        }
    } 

    public void update(ProdutoCompra pc ) throws SQLException{
        String sql = "UPDATE produto_compra SET quantidade_produto = ? , valor_unitario = ? , id_produto = ? , id_compra = ? WHERE id_produto_compra = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pc.getQuantidade_produto());
        ps.setDouble(2, pc.getValor_unitario());
        ps.setInt(3, pc.getProduto().getId_produto());
        ps.setInt(4, pc.getCompra().getId_compra());
        ps.setInt(5 , pc.getId_produto_compra());
        ps.executeUpdate();

    }

    public void delete(int id) throws SQLException{

        String sql = "DELETE FROM categoria WHERE id = ?";
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Carrinho de Compras concluído com sucesso!");
        }catch(SQLException sqlerro){
            System.out.println("Erro na database: "+sqlerro.getMessage());
        }
    }

    public ProdutoCompra getProdutoCompra(int id) throws SQLException , ValorInvalidoException , CepInvalidoException , SenhaInvalidaException , CpfInvalidoException , TelefoneInvalidoException{
        String sql = "SELECT * FROM produto_compra WHERE id_produto_compra = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            return new ProdutoCompra(id, rs.getInt("quantidade_produto"), rs.getDouble("valor_unitario"), produtoDAO.getProduto(rs.getInt("id_produto")), compraDAO.getCompra(rs.getInt("id_compra")));
        }
        return null;
    } 
}
