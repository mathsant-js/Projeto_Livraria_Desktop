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
import model.EmailCliente;
import model.TelefoneCliente;

/**
 *
 * @author Admin
 */
public class TelefoneClienteDAO {
    public void cadastrarTelefoneCliente (TelefoneCliente telefoneCliente) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO telefonecliente (cod_cliente, telefone_cliente) "
                     + "VALUES (?, ?)";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, telefoneCliente.getCodCli());
            stmt.setString(2, telefoneCliente.getTelefoneCliente());
            stmt.executeUpdate();
        }
    }
    
    public TelefoneCliente obterTelefoneCliente(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM telefonecliente WHERE cod_cliente = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                TelefoneCliente telefonecliente = new TelefoneCliente(
                        rs.getInt("cod_cliente"),
                        rs.getString("telefone_cliente")
                );
                return telefonecliente;
        } catch (SQLException e) {
            TelefoneCliente telefonecliente = new TelefoneCliente(id, null);
            return telefonecliente;
        }
    }
    
    public TelefoneCliente buscarTelefoneClientePorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM telefonecliente WHERE cod_cliente = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                TelefoneCliente telefoneCliente = new TelefoneCliente(
                        rs.getInt("cod_cliente"),
                        rs.getString("telefone_cliente")
                );
                return telefoneCliente;
        }
    }
}
