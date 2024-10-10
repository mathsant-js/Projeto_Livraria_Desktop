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
import model.Livro;

/**
 *
 * @author lucas
 */
public class LivroDAO {
    public List<Livro> listarTodosLivros() throws SQLException {
        Conexao conexao = new Conexao();
        List<Livro> livros = new ArrayList<>();
        String sql = ""
                +"SELECT Livro.cod_livro, Livro.nome_livro, Livro.isbn_livro, Livro.data_lancamento, Livro.preco_livro, AutorLivro.cod_autor, Autor.nome_autor, Editora.cod_editora, Editora.nome_editora, Genero.cod_genero, Genero.nome_genero, Livro.descricao_livro\n"
                +"FROM Livro\n"
                +"INNER JOIN AutorLivro ON Livro.cod_livro=AutorLivro.cod_livro\n"
                +"INNER JOIN Autor ON Livro.cod_livro=AutorLivro.cod_livro AND AutorLivro.cod_autor=Autor.cod_autor\n"
                +"INNER JOIN Editora ON Livro.cod_editora=Editora.cod_editora\n"
                +"INNER JOIN Genero ON Livro.cod_genero=Genero.cod_genero;";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("cod_livro"),
                        rs.getString("nome_livro"),
                        rs.getString("isbn_livro"),
                        rs.getDate("data_lancamento"),
                        rs.getFloat("preco_livro"),
                        rs.getInt("cod_autor"),
                        rs.getString("nome_autor"),
                        rs.getInt("cod_editora"),
                        rs.getString("nome_editora"),
                        rs.getInt("cod_genero"),
                        rs.getString("nome_genero"),
                        rs.getString("descricao_livro")
                );
                livros.add(livro);
            }
        }
        return livros;
    }
}
