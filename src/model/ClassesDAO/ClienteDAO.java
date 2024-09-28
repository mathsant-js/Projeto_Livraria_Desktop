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

/**
 *
 * @author Admin
 */
public class ClienteDAO {
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
                    rs.getString("data_nascimento_cliente")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}