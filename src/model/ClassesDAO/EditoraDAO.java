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

/**
 *
 * @author Matheus Santana
 */
public class EditoraDAO {
    public List<Editora> listarTodosEditoras() throws SQLException {
        Conexao conexao = new Conexao();
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Editora editora = new Editora(
                        rs.getInt("cod_editora"),
                        rs.getString("nome_editora"),
                        rs.getString("endereco_editora")
                );
                editoras.add(editora);
            }
        }
        return editoras;
    }
}
