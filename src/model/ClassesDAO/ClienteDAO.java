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

/**
 *
 * @author Admin
 */
public class ClienteDAO {
    public void cadastrarCliente (Cliente cliente) throws SQLException {
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
    
    public void atualizarCliente(int codCli, String nomeCliente, String clienteCpf, Date dtNascCliente, String senhaCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE cliente SET nome_cliente = '" +nomeCliente +"', cpf_cliente = '" +clienteCpf +"', data_nascimento_cliente = '" +dtNascCliente +"', senha_cliente = '" +senhaCliente +"' "
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
            stmt.executeUpdate();
        }
    }
}
