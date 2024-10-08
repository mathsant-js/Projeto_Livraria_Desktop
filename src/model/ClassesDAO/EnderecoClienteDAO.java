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
                        cliente,
                            rs.getString("endereco_cliente")
                );
                enderecoCliente.add(enderecoClientes);
            }
        }
        return enderecoCliente;
    }
}
