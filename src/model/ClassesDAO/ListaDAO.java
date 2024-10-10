/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ClassesDAO;

import conexao.Conexao;
import java.util.List;
import model.Lista;
import java.sql.*;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author Matheus Santana
 */
public class ListaDAO {
    public List<Lista> listarTodosListas() throws SQLException {
        Conexao conexao = new Conexao();
        List<Lista> lista = new ArrayList();
        String sql = "SELECT l.cod_lista, l.data_criacao_lista, c.cod_cliente "
                    + "FROM lista l"
                    + "JOIN cliente c ON e.cod_cliente = c.cod_cliente";
        
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
                Lista listas = new Lista (
                            rs.getInt("cod_lista"),
                            rs.getDate("data_criacao_lista"),
                        cliente
                );
                lista.add(listas);
            }
        }
        return lista;
    }
}
