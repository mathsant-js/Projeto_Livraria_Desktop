/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ClassesDAO;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import model.Autor;

/**
 *
 * @author Matheus Santana
 */
public class AutorDAO {
    public void cadastrarAutor(Autor autor) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO autor (nome_autor, biografia_autor, data_nascimento_autor, data_falecimento_autor, nacionalidade_autor) " 
                   + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = conexao.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setString(1, autor.getNomeAutor());
             stmt.setString(2, autor.getBiografiaAutor());
             stmt.setDate(3, autor.getDataNascimentoAutor());
             stmt.setDate(4, autor.getDataFalecimentoAutor());
             stmt.setString(5, autor.getNacionalidadeAutor());
             stmt.executeUpdate();
        }
    }
    
    
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
                rs.getDate("data_nascimento_autor"),
               rs.getDate("data_falecimento_autor"),
                 rs.getString("nacionalidade_autor")
                );
                autores.add(autor);
            }
        }
        return autores;
    }
    
    public Autor buscarAutoresPorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM autor WHERE cod_autor = " + id + ";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                Autor autor = new Autor(
                    rs.getInt("cod_autor"),
                   rs.getString("nome_autor"),
                rs.getString("biografia_autor"),
            rs.getDate("data_nascimento_autor"),
           rs.getDate("data_falecimento_autor"),
             rs.getString("nacionalidade_autor")
                );
                return autor;
        }
    }
    
    public List<Autor> buscarAutoresPorNome(String nome) throws SQLException {
        Conexao conexao = new Conexao();
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor WHERE nome_autor LIKE '%" + nome + "%';";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Autor autor = new Autor(
                 rs.getInt("cod_autor"),
                rs.getString("nome_autor"),
             rs.getString("biografia_autor"),
         rs.getDate("data_nascimento_autor"),
        rs.getDate("data_falecimento_autor"),
          rs.getString("nacionalidade_autor")
                );
                autores.add(autor);
            }
        }
        return autores;
    }
    
    public void atualizarAutor(int codAutor, String nomeAutor, String biografiaAutor, 
                                Date dataNascimentoAutor, Date dataFalecimentoAutor, 
                                String nacionalidadeAutor) throws SQLException 
    {
        Conexao conexao = new Conexao();
        String sql = "UPDATE autor SET nome_autor = '" + nomeAutor + "', biografia_autor = '" + biografiaAutor + "', data_nascimento_autor = '" + dataNascimentoAutor +"', data_falecimento_autor = '" + dataFalecimentoAutor +"', nacionalidade_autor = '" + nacionalidadeAutor + "' "
                   + "WHERE cod_autor = " + codAutor +";";
        
        try (Connection conn = conexao.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.executeUpdate();
        }
    }
    
    public void deletarAutor(int codAutor) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM autor WHERE cod_autor = ?";
        try (Connection conn = conexao.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setInt(1, codAutor);
             stmt.executeUpdate();
        }
    }
}
