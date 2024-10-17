/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ClassesDAO;

import conexao.Conexao;
import java.util.List;
import model.EmailCliente;
import java.sql.*;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author Admin
 */
public class EmailClienteDAO {
    public void cadastrarEmailCliente (EmailCliente emailCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO emailcliente (cod_cliente, email_cliente) "
                     + "VALUES (?, ?)";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emailCliente.getCodCli());
            stmt.setString(2, emailCliente.getEmailCliente());
            stmt.executeUpdate();
        }
    }
    
    public EmailCliente obterEmailCliente(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM emailcliente WHERE cod_cliente = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                EmailCliente emailcliente = new EmailCliente(
                        rs.getInt("cod_cliente"),
                        rs.getString("email_cliente")
                );
                return emailcliente;
        } catch (SQLException e) {
            EmailCliente emailcliente = new EmailCliente(id, null);
            return emailcliente;
        }
    }
    
    public EmailCliente buscarEmailClientePorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM emailcliente WHERE cod_cliente = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                EmailCliente emailCliente = new EmailCliente(
                        rs.getInt("cod_cliente"),
                        rs.getString("email_cliente")
                );
                return emailCliente;
        }
    }
}
