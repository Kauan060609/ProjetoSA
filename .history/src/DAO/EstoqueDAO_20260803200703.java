package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.*;
import connection.ConnectionSA;
import model.Estoque;

public class EstoqueDAO{

     private Connection conn = ConnectionSA.connect();

    public void create(Estoque e) throws SQLException , ValorInvalidoException{
        String sql = "INSERT INTO estoque(quantidade_minima, quantidade_estoque) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, e.getQuantidade_minima());
        ps.setInt(2, e.getQuantidade_estoque());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        e.setId_estoque(rs.getInt("GENERATED_KEY"));
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM estoque";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Estoque:\n\tID: %d\n\tQuantidade mínima: %s\n\tQuantidade Estoque: %s", rs.getInt("id_estoque"),rs.getInt("quantidade_minima"), rs.getInt("quantidade_estoque"));
        }
    }

    public void update(Estoque estoque) throws SQLException{
        String sql = "UPDATE estoque SET quantidade_minima = ?, quantidade_estoque = ? WHERE id_estoque = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, estoque.getQuantidade_minima());
        ps.setInt(2, estoque.getQuantidade_estoque());
        ps.setInt(3, estoque.getId_estoque());

        ps.executeUpdate();
    }

    public void delete(int id)throws SQLException{

        String sql = "DELETE FROM categoria WHERE id = ?";

        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

            System.out.println("Estoque excluído com sucesso!");
        }catch(SQLException sqlerror){
            System.out.println("Erro na database: "+sqlerror.getMessage());
        }
    }

    public Estoque getEstoque(int id) throws SQLException , ValorInvalidoException{
        String sql = "SELECT * FROM estoque WHERE id_estoque = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            return new Estoque(id, rs.getInt("quantidade_minima"), rs.getInt("quantidade_estoque"));
        }
        return null;
    }
}