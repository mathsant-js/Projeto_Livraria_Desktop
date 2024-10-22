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
    public void cadastrarTelefoneEditora (TelefoneEditora telefoneEditora) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO telefoneeditora (cod_editora, telefone_editora) "
                     + "VALUES (?, ?)";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, telefoneEditora.getCodEditora());
            stmt.setString(2, telefoneEditora.getTelefoneEditora());
            stmt.executeUpdate();
        }
    }
    
    public TelefoneEditora obterTelefoneEditora(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM telefoneeditora WHERE cod_editora = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                TelefoneEditora telefoneeditora = new TelefoneEditora(
                        rs.getInt("cod_editora"),
                        rs.getString("telefone_editora")
                );
                return telefoneeditora;
        } catch (SQLException e) {
            TelefoneEditora telefoneeditora = new TelefoneEditora(id, null);
            return telefoneeditora;
        }
    }
    
    public TelefoneEditora buscarTelefoneEditoraPorId(int id) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM telefoneeditora WHERE cod_editora = " +id +";";
        
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                rs.next();
                TelefoneEditora telefoneEditora = new TelefoneEditora(
                        rs.getInt("cod_editora"),
                        rs.getString("telefone_editora")
                );
                return telefoneEditora;
        }
    }
    
    public void deletarTelefoneEditora(int codEditora) throws SQLException {
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM telefoneeditora WHERE cod_editora = " +codEditora +";";
        try (Connection conn = conexao.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
}
