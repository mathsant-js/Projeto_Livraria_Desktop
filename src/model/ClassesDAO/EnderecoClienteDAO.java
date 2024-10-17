/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ClassesDAO;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.EnderecoCliente;

/**
 *
 * @author Admin
 */
public class EnderecoClienteDAO {
    public List<EnderecoCliente> listarTodosOsEnderecosClientes() throws SQLException {
        Conexao conexao = new Conexao();
        List<EnderecoCliente> enderecoCliente = new ArrayList();
        String sql = "SELECT e.id, e.endereco, c.id AS cliente_id, c.nome "
                    + "FROM EnderecoCliente e "
                    + "JOIN Cliente c ON e.cliente_id = c_id";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente (
                        rs.getInt("cod_cliente"),
                     rs.getString("nome_cliente"),
                      rs.getString("cpf_cliente"),
                   rs.getDate("data_nascimento_cliente"),
                    rs.getString("senha_cliente")
                );
                EnderecoCliente enderecoClientes = new EnderecoCliente (
                            rs.getInt("cod_cliente"),
                      rs.getString("endereco_cliente")
                );
                enderecoCliente.add(enderecoClientes);
            }
        }
        return enderecoCliente;
    }
    
    public EnderecoCliente obterEnderecoCliente(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM enderecocliente WHERE cod_cliente = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                EnderecoCliente enderecocliente = new EnderecoCliente(
                        rs.getInt("cod_cliente"),
                        rs.getString("endereco_cliente")
                );
                return enderecocliente;
        } catch (SQLException e) {
            EnderecoCliente enderecocliente = new EnderecoCliente(id, null);
            return enderecocliente;
        }
    }
    
    public void cadastrarEnderecoCliente(EnderecoCliente enderecoCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO enderecocliente (cod_cliente, endereco_cliente) VALUES (?, ?);";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, enderecoCliente.getCodCli());
            stmt.setString(2, enderecoCliente.getEnderecoCliente());
            stmt.executeUpdate();
        }
    }
    
    public void atualizarEnderecoCliente(int codCliente, String enderecoCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE enderecocliente SET cod_cliente = " +codCliente +", endereco_cliente = '" +enderecoCliente +"';";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.executeUpdate();
        }
    }
    
    public void deletarEnderecoCliente(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM enderecocliente WHERE cod_cliente = ?";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
