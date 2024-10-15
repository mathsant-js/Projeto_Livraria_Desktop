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
}
