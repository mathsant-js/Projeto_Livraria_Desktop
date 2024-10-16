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
    public void cadastrarGenero (Genero genero) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO genero (nome_genero, descricao_genero) "
                     + "VALUES (?, ?)";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, genero.getNomeGenero());
            stmt.setString(2, genero.getDescricaoGenero());
            stmt.executeUpdate();
        }
    }
    
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
    
    public Genero buscarGeneroPorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM genero WHERE cod_genero = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                Genero genero = new Genero(
                        rs.getInt("cod_genero"),
                        rs.getString("nome_genero"),
                    rs.getString("descricao_genero")
                );
                return genero;
        }
    }
    
    public List<Genero> buscarGenerosPorNome(String nome) throws SQLException {
        Conexao conexao = new Conexao();
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT * FROM genero WHERE nome_genero LIKE '%" +nome +"%';";
        
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
    
    public void atualizarGenero (int codGenero, String nomeGenero, String descricaoGenero) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE genero SET nome_genero = '" +nomeGenero +"', descricao_genero = '" +descricaoGenero +"' "
                   + "WHERE cod_genero = " +codGenero +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
    
    public void deletarGenero (int codGenero) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM genero WHERE cod_genero = ?";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codGenero);
            stmt.executeUpdate();
        }
    }
}
