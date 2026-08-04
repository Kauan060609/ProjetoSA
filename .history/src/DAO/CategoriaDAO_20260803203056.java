package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionSA;
import exceptions.ValorInvalidoException;
import model.Categoria;

public class CategoriaDAO{
    
    private Connection conn = ConnectionSA.connect();

    public void create(Categoria categoria) throws SQLException, ValorInvalidoException{
        String sql = "INSERT INTO categoria(nome_categoria, descricao_categoria) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, categoria.getNome_categoria());
        ps.setString(2, categoria.getDescricao_categoria());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            categoria.setId_categoria(rs.getInt("GENERATED_KEY"));
        }1
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM categoria";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Categoria:\n\tID: %d\n\tNome: %s\n\tDescrição: %s\n", rs.getInt("id_categoria"), rs.getString("nome_categoria"), rs.getString("descricao_categoria"));
        }
    }

    public void update(Categoria categoria) throws SQLException, ValorInvalidoException{
        String sql = "UPDATE categoria SET nome_categoria = ?, descricao_categoria = ? WHERE id_categoria = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, categoria.getNome_categoria());
        ps.setString(2, categoria.getDescricao_categoria());
        ps.setInt(3, categoria.getId_categoria());

        ps.executeUpdate();
    }

    public void delete(int id) throws SQLException{

        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        PreparedStatement ps = null;
        try{
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Categoria deletada com sucesso");
        }catch (SQLException sqlError){
            System.out.println("Erro na database: " + sqlError.getMessage());
        }
    }

    public Categoria getCategoria(int id) throws SQLException, ValorInvalidoException{
        String sql = "SELECT * FROM categoria WHERE id_categoria = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            return new Categoria(id, rs.getString("nome_categoria"), rs.getString("descricao_categoria"));
        }
        return null;
    }
}