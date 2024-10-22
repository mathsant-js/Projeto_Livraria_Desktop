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
import java.util.ArrayList;
import java.util.List;
import model.Livro;

/**
 *
 * @author lucas
 */
public class LivroDAO {
    public void cadastrarLivro (Livro livro) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO livro (nome_livro, isbn_livro, data_lancamento, preco_livro, descricao_livro, cod_genero, cod_editora) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, livro.getNomeLivro());
            stmt.setString(2, livro.getIsbnLivro());
            stmt.setDate(3, livro.getDataLancamentoLivro());
            stmt.setFloat(4, livro.getPrecoLivro());
            stmt.setString(5, livro.getDescricaoLivro());
            stmt.setInt(6, livro.getCodGenero());
            stmt.setInt(7, livro.getCodEditora());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                int idLivro = rs.getInt(1);
                String sqlAutor = "INSERT INTO autorlivro (cod_autor, cod_livro) "
                                + "VALUES (?, ?);";
                try (Connection connAutor = conexao.connect();
                    PreparedStatement stmtAutor = conn.prepareStatement(sqlAutor);) {
                    stmtAutor.setInt(1, livro.getCodAutor());
                    stmtAutor.setInt(2, idLivro);
                    stmtAutor.executeUpdate();
                }
            }
        }
    }
    
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
                        rs.getInt("cod_editora"),
                        rs.getInt("cod_genero"),
                        rs.getString("descricao_livro")
                );
                livros.add(livro);
            }
        }
        return livros;
    }
    
    public Livro buscarLivroPorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT Livro.cod_livro, Livro.nome_livro, Livro.isbn_livro, Livro.data_lancamento, Livro.preco_livro, AutorLivro.cod_autor, Autor.nome_autor, Editora.cod_editora, Editora.nome_editora, Genero.cod_genero, Genero.nome_genero, Livro.descricao_livro\n"
                    +"FROM Livro \n"
                    +"INNER JOIN AutorLivro ON Livro.cod_livro=AutorLivro.cod_livro \n"
                    +"INNER JOIN Autor ON Livro.cod_livro=AutorLivro.cod_livro AND AutorLivro.cod_autor=Autor.cod_autor \n"
                    +"INNER JOIN Editora ON Livro.cod_editora=Editora.cod_editora \n"
                    +"INNER JOIN Genero ON Livro.cod_genero=Genero.cod_genero \n"
                    +"WHERE Livro.cod_livro = " +id +";";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                Livro livro = new Livro(
                        rs.getInt("cod_livro"),
                        rs.getString("nome_livro"),
                        rs.getString("isbn_livro"),
                        rs.getDate("data_lancamento"),
                        rs.getFloat("preco_livro"),
                        rs.getInt("cod_autor"),
                        rs.getInt("cod_editora"),
                        rs.getInt("cod_genero"),
                        rs.getString("descricao_livro")
                );
                return livro;
        }
    }
    
    public List<Livro> buscarLivrosPorNome(String nome) throws SQLException {
        Conexao conexao = new Conexao();
        List<Livro> livros = new ArrayList<>();
        String sql = ""
                +"SELECT Livro.cod_livro, Livro.nome_livro, Livro.isbn_livro, Livro.data_lancamento, Livro.preco_livro, AutorLivro.cod_autor, Autor.nome_autor, Editora.cod_editora, Editora.nome_editora, Genero.cod_genero, Genero.nome_genero, Livro.descricao_livro\n"
                +"FROM Livro \n"
                +"INNER JOIN AutorLivro ON Livro.cod_livro=AutorLivro.cod_livro\n"
                +"INNER JOIN Autor ON Livro.cod_livro=AutorLivro.cod_livro AND AutorLivro.cod_autor=Autor.cod_autor\n"
                +"INNER JOIN Editora ON Livro.cod_editora=Editora.cod_editora\n"
                +"INNER JOIN Genero ON Livro.cod_genero=Genero.cod_genero\n"
                +"WHERE Livro.nome_livro LIKE '%" +nome +"%';";
        
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
                        rs.getInt("cod_editora"),
                        rs.getInt("cod_genero"),
                        rs.getString("descricao_livro")
                );
                livros.add(livro);
            }
        }
        return livros;
    }
    
    public void atualizarLivro(int codLivro, String nomeLivro, String isbnLivro, Date dataLancamentoLivro, float precoLivro, int codAutor, int codEditora, int codGenero, String descricaoLivro) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE livro SET nome_livro = '" +nomeLivro +"', isbn_livro = '" +isbnLivro +"', data_lancamento = '" +dataLancamentoLivro +"', preco_livro = '" +precoLivro +"', descricao_livro = '" +descricaoLivro +"', cod_genero = '" +codGenero +"', cod_editora = '" +codEditora +"' "
                   + "WHERE cod_livro = " +codLivro +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        
        sql = "UPDATE autorlivro SET cod_autor = '" +codAutor +"' "
            + "WHERE cod_livro = " +codLivro +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
    
    public void deletarLivro (int codLivro) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM autorlivro WHERE cod_livro = ?";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codLivro);
            stmt.executeUpdate();
        }
        
        sql = "DELETE FROM livro WHERE cod_livro = ?";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codLivro);
            stmt.executeUpdate();
        }
    }
}
