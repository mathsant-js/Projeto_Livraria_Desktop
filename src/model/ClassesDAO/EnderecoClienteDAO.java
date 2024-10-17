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
    public void cadastrarEnderecoCliente (EnderecoCliente enderecoCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO enderecocliente (cod_cliente, endereco_cliente) "
                     + "VALUES (?, ?)";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, enderecoCliente.getCodCli());
            stmt.setString(2, enderecoCliente.getEnderecoCliente());
            stmt.executeUpdate();
        }
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
    
    public EnderecoCliente buscarEnderecoClientePorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM enderecocliente WHERE cod_cliente = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                EnderecoCliente enderecoCliente = new EnderecoCliente(
                        rs.getInt("cod_cliente"),
                        rs.getString("endereco_cliente")
                );
                return enderecoCliente;
        }
    }
}
