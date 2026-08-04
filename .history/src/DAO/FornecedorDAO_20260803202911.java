package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.*;
import connection.ConnectionSA;
import model.Fornecedor;

public class FornecedorDAO {

    private Connection conn = ConnectionSA.connect();

    public void create(Fornecedor f) throws SQLException , ValorInvalidoException{
        String sql = "INSERT INTO fornecedor(nome_fornecedor, cnpj_fornecedor) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, f.getNome_fornecedor());
        ps.setString(2, f.getCnpj_fornecedor());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
    f.setId_fornecedor(rs.getInt("GENERATED_KEY"));
}
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM fornecedor";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Fornecedor:\n\tNome: %s\n\tCnpj: %s", rs.getString("nome_fornecedor"), rs.getString("cnpj_fornecedor"));
        }
    }

    public void update(Fornecedor f)throws SQLException{
        String sql = "UPDATE fornecedor SET nome_fornecedor = ? , cnpj_fornecedor = ?  WHERE id_fornecedor = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, f.getNome_fornecedor());
        ps.setString(2, f.getCnpj_fornecedor());
        ps.setInt(3 , f.getId_fornecedor());
        ps.executeUpdate();
    }

    public void delete(int id)throws SQLException{

        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Fornecedor excluído com sucesso!");
        }catch(SQLException sqlerro){
            System.out.println("Erro na database: "+sqlerro.getMessage());
        }
    }

    public Fornecedor getFornecedor(int id) throws SQLException , ValorInvalidoException{
        String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            return new Fornecedor(id, rs.getString("nome_fornecedor"), rs.getString("cnpj_fornecedor"));
        }
        return null;
    } 
}