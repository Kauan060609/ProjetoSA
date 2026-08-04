package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.*;
import connection.ConnectionSA;
import model.Categoria;
import model.Cliente;

public class ClienteDAO{
    private Connection conn = ConnectionSA.connect();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    
    
    public void create(Cliente cliente) throws SQLException , ValorInvalidoException{
        String sql = "INSERT INTO cliente(nome_cliente, cpf_cliente, telefone_cliente, email_cliente, cep_cliente, senha, id_endereco) VALUES (?, ?, ?, ?, ?, left(sha2(?, 256), 30), ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, cliente.getNome_cliente());
        ps.setString(2, cliente.getCpf_cliente());
        ps.setString(3, cliente.getTelefone_cliente());
        ps.setString(4, cliente.getEmail_cleinte());
        ps.setString(5, cliente.getCep_cliente());
        ps.setString(6, cliente.getSenha());
        ps.setInt(7, cliente.getEndereco().getId_enderec());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        cliente.setId_cliente(rs.getInt("GENERATED_KEY"));
    }

    public void read() throws SQLException{
        String sql = "SELECT * FROM cliente";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("Cliente:\n\tID: %d\n\tNome: %s\n\tCPF: %s\n\tTelefone: %s\n\tEmail: %s\n\tCEP: %s\n\tSenha: %s\n\tID Endereço: %d", rs.getInt("id_cliente"), rs.getString("nome_cliente"), rs.getString("cpf_cleinte"), rs.getString("telefone_cliente"), rs.getString("email_cliente"), rs.getString("cep_cliente"), rs.getString("senha"), rs.getInt("id_endereco"));
        }
    }

    public void update(Cliente cliente) throws SQLException{
        String sql = "UPDATE cliente SET nome_cliente = ?, cpf_cliente = ?, telefone_cliente = ?, email_cliente = ?, cep_cliente = ?, senha = left(sha2(?, 256), 30), id_endereco = ? WHERE id_cliente = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, cliente.getNome_cliente());
        ps.setString(2, cliente.getCpf_cliente());
        ps.setString(3, cliente.getTelefone_cliente());
        ps.setString(4, cliente.getEmail_cleinte());
        ps.setString(5, cliente.getCep_cliente());
        ps.setString(6, cliente.getSenha());
        ps.setInt(7, cliente.getEndereco().getId_enderec());
        ps.setInt(8, cliente.getId_cliente());

        ps.executeUpdate();
    } 

    public void delete(int id)throws SQLException{
        String sql = "DELETE FROM categoria WHERE id = ?";
        
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Cliente deletado com sucesso");

        }catch(SQLException s){
            System.out.println("Erro na database: "+s.getMessage());
        }
    }
    
    public Cliente getCliente(int id) throws SQLException, CpfInvalidoException, TelefoneInvalidoException , SenhaInvalidaException , CepInvalidoException , ValorInvalidoException {
        String sql = "SELECT * FROM cliente WHERE id_cliente = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            
            return new Cliente(id, rs.getString("nome_cliente"), rs.getString("cliente"), rs.getString("telefone_cliente"), rs.getString("email_cliente"), rs.getString("cep_cliente"), rs.getString("senha"), enderecoDAO.getEndereco(rs.getInt("id_endereco")));
        }
        return null;
    }
}
