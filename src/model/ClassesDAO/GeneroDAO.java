/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ClassesDAO;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Genero;

/**
 *
 * @author Matheus Santana
 */
public class GeneroDAO {
    public List<Genero> listarTodosGeneros() throws SQLException {
        Conexao conexao = new Conexao();
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT * FROM genero";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Genero genero = new Genero(
                        rs.getInt("cod_genero"),
                        rs.getString("nome_genero"),
                        rs.getString("descricao_genero")
                );
                generos.add(genero);
            }
        }
        return generos;
    }
}