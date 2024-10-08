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
    public List<TelefoneCliente> listarTodosOsTelefonesClientes() throws SQLException {
        Conexao conexao = new Conexao();
        List<TelefoneCliente> telefoneCliente = new ArrayList();
        String sql = "SELECT t.id, t.telefone, c.id AS cliente_id, c.nome "
                    + "FROM TelefoneCliente e "
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
                TelefoneCliente telefonesClientes = new TelefoneCliente (
                        cliente,
                            rs.getString("telefone_cliente")
                );
                telefoneCliente.add(telefonesClientes);
            }
        }
        return telefoneCliente;
    }
}
