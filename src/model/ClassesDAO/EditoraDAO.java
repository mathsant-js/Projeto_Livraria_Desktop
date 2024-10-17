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
public class EditoraDAO {
    public void cadastrarEditora (Editora editora, String telEditora) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO editora (nome_editora, endereco_editora) "
                     + "VALUES (?, ?)";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, editora.getNomeEditora());
            stmt.setString(2, editora.getEnderecoEditora());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                int idEditora = rs.getInt(1);
                
                TelefoneEditora telefoneEditora = new TelefoneEditora(idEditora, telEditora);
                TelefoneEditoraDAO telefoneEditoraDAO = new TelefoneEditoraDAO();
                
                telefoneEditoraDAO.cadastrarTelefoneEditora(telefoneEditora);
            }
        }
    }
    
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
    
    public Editora buscarEditoraPorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM editora WHERE cod_editora = " +id +";";
        
        try (Connection conn = conexao.connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                Editora editora = new Editora(
                        rs.getInt("cod_editora"),
                       rs.getString("nome_editora"),
                    rs.getString("endereco_editora")
                );
                return editora;
        }
    }
    
    public List<Editora> buscarEditorasPorNome(String nome) throws SQLException {
        Conexao conexao = new Conexao();
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora WHERE nome_editora LIKE '%" +nome +"%';";
        
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
    
    public void atualizarEditora (int codEditora, String nomeEditora, String enderecoEditora, String telefoneEditora) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "UPDATE editora SET nome_editora = '" +nomeEditora +"', endereco_editora = '" +enderecoEditora +"' "
                   + "WHERE cod_editora = " +codEditora +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        
        sql = "UPDATE telefoneeditora SET telefone_editora = '" +telefoneEditora +"' "
            + "WHERE cod_editora = " +codEditora +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
    
    public void deletarEditora (int codEditora) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM editora WHERE cod_editora = " +codEditora +"; \n"
                    +"DELETE FROM telefoneeditora WHERE cod_editora = " +codEditora +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
}
