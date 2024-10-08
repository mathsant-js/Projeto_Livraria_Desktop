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
    public List<EmailCliente> listarTodosOsEmailsClientes() throws SQLException {
        Conexao conexao = new Conexao();
        List<EmailCliente> emailCliente = new ArrayList();
        String sql = "SELECT e.id, e.email, c.id AS cliente_id, c.nome "
                    + "FROM EmailCliente e "
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
                EmailCliente emailClientes = new EmailCliente (
                        cliente,
                            rs.getString("email_cliente")
                );
                emailCliente.add(emailClientes);
            }
        }
        return emailCliente;
    }
}
