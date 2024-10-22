/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ClassesDAO;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.EmailCliente;
import model.EnderecoCliente;
import model.TelefoneCliente;

/**
 *
 * @author Admin
 */
public class ClienteDAO {
    public void cadastrarCliente (Cliente cliente, String telCliente, String emaCliente, String endCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO cliente (nome_cliente, cpf_cliente, data_nascimento_cliente, senha_cliente) "
                   + "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getClienteCpf());
            stmt.setDate(3, cliente.getDtNascCliente());
            stmt.setString(4, cliente.getSenhaCliente());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                int idCliente = rs.getInt(1);
                
                TelefoneCliente telefoneCliente = new TelefoneCliente(idCliente, telCliente);
                TelefoneClienteDAO telefoneClienteDAO = new TelefoneClienteDAO();
                telefoneClienteDAO.cadastrarTelefoneCliente(telefoneCliente);
                
                EmailCliente emailCliente = new EmailCliente(idCliente, emaCliente);
                EmailClienteDAO emailClienteDAO = new EmailClienteDAO();
                emailClienteDAO.cadastrarEmailCliente(emailCliente);
                
                EnderecoCliente enderecoCliente = new EnderecoCliente(idCliente, endCliente);
                EnderecoClienteDAO enderecoClienteDAO = new EnderecoClienteDAO();
                enderecoClienteDAO.cadastrarEnderecoCliente(enderecoCliente);
            }
        }
    }
    
    
    public List<Cliente> listarTodos() throws SQLException {
        Conexao conexao = new Conexao();
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        
        try (Connection conn = conexao.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cod_cliente"),
                     rs.getString("nome_cliente"),
                      rs.getString("cpf_cliente"),
                   rs.getDate("data_nascimento_cliente"),
                    rs.getString("senha_cliente")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    
    public Cliente buscarPorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM cliente WHERE cod_cliente = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                Cliente cliente = new Cliente(
                        rs.getInt("cod_cliente"),
                     rs.getString("nome_cliente"),
                      rs.getString("cpf_cliente"),
                   rs.getDate("data_nascimento_cliente"),
                    rs.getString("senha_cliente")
                );
                return cliente;
        }
    }
    
    public List<Cliente> buscarPorNome(String nome) throws SQLException {
        Conexao conexao = new Conexao();
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome_cliente LIKE '%" +nome +"%';";
        
        try (Connection conn = conexao.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cod_cliente"),
                     rs.getString("nome_cliente"),
                      rs.getString("cpf_cliente"),
                   rs.getDate("data_nascimento_cliente"),
                    rs.getString("senha_cliente")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    
    public void atualizarCliente(int codCli, String nomeCliente, String clienteCpf, Date dtNascCliente, String senhaCliente, String telCliente, String emaCliente, String endCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE cliente SET nome_cliente = '" +nomeCliente +"', cpf_cliente = '" +clienteCpf +"', data_nascimento_cliente = '" +dtNascCliente +"', senha_cliente = '" +senhaCliente +"' "
                   + "WHERE cod_cliente = " +codCli +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        
        sql = "UPDATE telefonecliente SET telefone_cliente = '" +telCliente +"' "
            + "WHERE cod_cliente = " +codCli +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        
        sql = "UPDATE emailcliente SET email_cliente = '" +emaCliente +"' "
            + "WHERE cod_cliente = " +codCli +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        
        sql = "UPDATE enderecocliente SET endereco_cliente = '" +endCliente +"' "
            + "WHERE cod_cliente = " +codCli +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
    
    
    public void deletarCliente(int codCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM cliente WHERE cod_cliente = ?";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codCliente);
            
            TelefoneClienteDAO telefoneClienteDAO = new TelefoneClienteDAO();
            telefoneClienteDAO.deletarTelefoneCliente(codCliente);
            
            EnderecoClienteDAO enderecoClienteDAO = new EnderecoClienteDAO();
            enderecoClienteDAO.deletarEnderecoCliente(codCliente);
            
            EmailClienteDAO emailClienteDAO = new EmailClienteDAO();
            emailClienteDAO.deletarEmailCliente(codCliente);
            
            stmt.executeUpdate();
        }
    }
}
