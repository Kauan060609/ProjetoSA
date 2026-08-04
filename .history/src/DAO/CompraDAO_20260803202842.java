package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionSA;
import exceptions.*;
import model.Compra;

public class CompraDAO{
    private Connection conn = ConnectionSA.connect();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public void create(Compra compra) throws SQLException , ValorInvalidoException  {
        String sql = "INSERT INTO compra (descricao, valor_total, id_cliente) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, compra.getDescricao());
        ps.setDouble(2, compra.getValor_total());
        ps.setInt(3, compra.getCliente().getId_cliente());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            compra.setId_compra(rs.getInt("GENERATED_KEY"));
        }
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM compra";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Compra:\n\tID: %d\n\tDescrição: %s\n\tValor Total: R$%.2f\n\tID Cliente: %d",rs.getInt("id_compra"), rs.getString("descricao"), rs.getDouble("valor_total"), rs.getInt("id_cliente"));
        }
    }

    public void update(Compra compra) throws SQLException{
        String sql = "UPDATE compra SET descricao = ?, valor_total = ?, id_cliente = ? WHERE id_compra = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, compra.getDescricao());
        ps.setDouble(2, compra.getValor_total());
        ps.setInt(3, compra.getCliente().getId_cliente());
        ps.setInt(4, compra.getId_compra());

        ps.executeUpdate();
    }

    public void delete(int id)throws SQLException{
        String sql = "DELETE FROM compra WHERE id_compra = ?";
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Compra deletada com sucesso!");

        }catch(SQLException sqlerror){
            System.out.println("Erro na database: "+sqlerror.getMessage());
        }
    }

    public Compra getCompra(int id) throws SQLException , ValorInvalidoException , SenhaInvalidaException, CepInvalidoException , CpfInvalidoException , TelefoneInvalidoException {
        String sql = "SELECT * FROM compra WHERE id_compra = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            return new Compra(id, rs.getString("descricao"), rs.getDouble("valor_total"), clienteDAO.getCliente(rs.getInt("id_cliente")));
        }
        return null;
    }
}
