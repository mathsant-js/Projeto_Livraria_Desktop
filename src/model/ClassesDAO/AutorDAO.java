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
import model.Autor;

/**
 *
 * @author Matheus Santana
 */
public class AutorDAO {
    public List<Autor> listarTodosAutores() throws SQLException {
        Conexao conexao = new Conexao();
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Autor autor = new Autor(
                        rs.getInt("cod_autor"),
                        rs.getString("nome_autor"),
                        rs.getString("biografia_autor"),
                        rs.getString("data_nascimento_autor"),
                        rs.getString("data_falecimento_autor"),
                        rs.getString("nacionalidade_autor")
                );
                autores.add(autor);
            }
        }
        return autores;
    }
}