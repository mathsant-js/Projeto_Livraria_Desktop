/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Livro;
import model.ClassesDAO.LivroDAO;
import java.sql.*;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FormularioLivrosController implements Initializable {
    @FXML
    private Label tituloPag;
    @FXML
    private TableView<Livro> tabelaLivros;
    @FXML
    private TableColumn<Livro, Integer> codLivro;
    @FXML
    private TableColumn<Livro, String> nomeLivro;
    @FXML
    private TableColumn<Livro, String> autorLivro;
    @FXML
    private TableColumn<Livro, Date> dtLancLivro;

    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosLivro();
    }    
    
    private void carregarDadosLivro() {
        LivroDAO livroDAO = new LivroDAO();
        try {
            List<Livro> livros = livroDAO.listarTodosLivros();
            ObservableList<Livro> observableList = FXCollections.observableArrayList(livros);
            tabelaLivros.setItems(observableList);
            codLivro.setCellValueFactory(new PropertyValueFactory<>("codLivro"));
            nomeLivro.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
            autorLivro.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
            dtLancLivro.setCellValueFactory(new PropertyValueFactory<>("dataLancamentoLivro"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
