package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.*;
import connection.ConnectionSA;
import model.Produto;

public class ProdutoDAO {

    private Connection conn = ConnectionSA.connect();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private EstoqueDAO estoqueDAO = new EstoqueDAO();
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public void create(Produto produto) throws SQLException , ValorInvalidoException{
        String sql = "INSERT INTO produto(nome_produto, marca_produto, valor_produto, id_categoria, id_estoque, id_fornecedor) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, produto.getNome_produto());
        ps.setString(2, produto.getMarca_produto());
        ps.setDouble(3, produto.getValor_produto());
        ps.setInt(4, produto.getCategoria().getId_categoria());
        ps.setInt(5, produto.getEstoque().getId_estoque());
        ps.setInt(6, produto.getFornecedor().getId_fornecedor());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        produto.setId_produto(rs.getInt("GENERATED_KEY"));
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM produto";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Produto:\n\tID: %d\n\tNome: %s\n\tMarca: %s\n\tValor Produto: %f\n\tID categoria: %d\n\tID Estoque: %d\n\tID Fornecedor: %d", 
            rs.getInt("id_produto"), rs.getString("nome_produto"), rs.getString("marca_produto"), rs.getDouble("valor_produto"), rs.getInt("id_categoria"), rs.getInt("id_estoque"), rs.getInt("id_fornecedor"));
        }
    }

    public void update(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET nome_produto = ? , marca_produto = ? , valor_produto = ? , id_categoria = ? , id_estoque = ? , id_fornecedor = ?  WHERE id_produto = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, produto.getNome_produto());
        ps.setString(2, produto.getMarca_produto());
        ps.setDouble(3, produto.getValor_produto());
        ps.setInt(4, produto.getCategoria().getId_categoria());
        ps.setInt(5, produto.getEstoque().getId_estoque());
        ps.setInt(6, produto.getFornecedor().getId_fornecedor());
        ps.setInt(7 , produto.getId_produto());
        ps.executeUpdate();
    }

    public void delete(int id)throws SQLException{
        String sql = "DELETE FROM categoria WHERE id = ?";
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Produto excluído com sucesso!");
        }catch(SQLException erro){
            System.out.println("Erro na database: "+erro.getMessage());
        }
    }

    public Produto getProduto(int id) throws SQLException ,ValorInvalidoException{
        String sql = "SELECT * FROM produto WHERE id_produto = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            return new Produto(id, rs.getString("nome_produto"), rs.getString("marca_produto"), rs.getDouble("valor_produto"), categoriaDAO.getCategoria(rs.getInt("id_categoria")), estoqueDAO.getEstoque(rs.getInt("id_estoque")), fornecedorDAO.getFornecedor(rs.getInt("id_fornecedor")));
        }
        return null;
    }
}