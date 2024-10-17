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
import model.Editora;
import model.TelefoneEditora;

/**
 *
 * @author Matheus Santana
 */
public class TelefoneEditoraDAO {
    public List<TelefoneEditora> listarTodosOsEnderecosClientes() throws SQLException {
        Conexao conexao = new Conexao();
        List<TelefoneEditora> telefoneEditora = new ArrayList();
        String sql = "SELECT editora.nome_editora, editora.endereco_editora, telefoneeditora.telefone_editora "
                   + "FROM editora INNER JOIN telefoneeditora"
                   + "ON editora.cod_editora = telefoneeditora.cod_editora";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Editora editora = new Editora (
                         rs.getInt("cod_editora"),
                        rs.getString("nome_editora"),
                     rs.getString("endereco_editora")
                );
                TelefoneEditora telefoneEditoras = new TelefoneEditora (
                        editora,
                            rs.getString("telefone_editora")
                );
                telefoneEditora.add(telefoneEditoras);
            }
        }
        return telefoneEditora;
    }
}
