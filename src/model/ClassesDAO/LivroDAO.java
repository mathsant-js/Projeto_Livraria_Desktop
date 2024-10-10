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
import model.Genero;
import model.Livro;

/**
 *
 * @author Matheus Santana
 */
public class LivroDAO {
    public List<Livro> listarTodosLivros() throws SQLException {
        Conexao conexao = new Conexao();
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT l.cod_livro, l.nome_livro, l.isbn_livro, l.data_lancamento, " +
                     "l.preco_livro, l.descricao_livro, " +
                     "g.cod_genero, g.nome_genero, " +
                     "e.cod_editora, e.nome_editora " +
                     "FROM livro l " +
                     "JOIN genero g ON l.cod_genero = g.cod_genero " +
                     "JOIN editora e ON l.cod_editora = e.cod_editora";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Genero genero = new Genero(rs.getInt("cod_genero"),
                        rs.getString("nome_genero"),
                    rs.getString("descricao_genero"));
                
                Editora editora = new Editora(rs.getInt("cod_editora"),
                        rs.getString("nome_editora"),
                     rs.getString("endereco_editora"));
                
                Livro livro = new Livro(
                        rs.getInt("cod_livro"),
                       rs.getString("nome_livro"),
                       rs.getString("isbn_livro"),
                    rs.getDate("data_lancamento"),
                       rs.getFloat("preco_livro"),
                    rs.getString("descricao_livro"),
                       genero,
                       editora
                );
                livros.add(livro);
            }
        }
        return livros;
    }
}
