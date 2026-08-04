package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.*;
import connection.ConnectionSA;
import model.Categoria;
import model.Endereco;

public class EnderecoDAO{
    private Connection conn = ConnectionSA.connect();

    public void create(Endereco endereco) throws SQLException , ValorInvalidoException {
        String sql = "INSERT INTO endereco (pais, estado, cidade, rua, numero, complemento) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, endereco.getPais());
        ps.setString(2, endereco.getEstado());
        ps.setString(3, endereco.getCidade());
        ps.setString(4, endereco.getRua());
        ps.setInt(5, endereco.getNumero());
        ps.setString(6, endereco.getComplemento());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            endereco.setId_enderec(rs.getInt(1)); 
        }
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM endereco";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Endereço\n\tID: %d\n\tPaís: %s\n\tEstado: %s\n\tCidade: %s\n\tRua: %s\n\tNúmero: %d\n\tComplemento: %s\n", rs.getInt("id_endereco"), rs.getString("pais"), rs.getString("estado"), rs.getString("cidade"), rs.getString("rua"), rs.getInt("numero"), rs.getString("complemento"));
        }
    }

    public void update(Endereco endereco) throws SQLException{
        String sql = "UPDATE endereco SET pais = ?, estado = ?, cidade = ?, rua = ?, numero = ?, complemento = ? WHERE id_endereco = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, endereco.getPais());
        ps.setString(2, endereco.getEstado());
        ps.setString(3, endereco.getCidade());
        ps.setString(4, endereco.getRua());
        ps.setInt(5, endereco.getNumero());
        ps.setString(6, endereco.getComplemento());
        ps.setInt(7, endereco.getId_enderec());
        ps.executeUpdate();
    }

    public void delete(int id)throws SQLException{

        String sql = "DELETE FROM endereco WHERE id_endereco = ?";

        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Endereço excluído com sucesso!");
        }catch(SQLException sqlerro){
            System.out.println("Erro na database: " + sqlerro.getMessage());
        }
    }

    public Endereco getEndereco(int id) throws SQLException , ValorInvalidoException{
        String sql = "SELECT * FROM endereco WHERE id_endereco = " + id ;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Endereco(id, rs.getString("pais"), rs.getString("estado"), rs.getString("cidade"), rs.getString("rua"), rs.getInt("numero"), rs.getString("complemento"));
        }
        return null;
    }
    
}
