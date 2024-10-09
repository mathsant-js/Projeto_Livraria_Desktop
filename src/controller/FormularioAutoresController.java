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
import model.Autor;
import model.ClassesDAO.AutorDAO;
import java.sql.*;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FormularioAutoresController implements Initializable {

    @FXML
    private TableView<Autor> tabelaClientes;
    @FXML
    private Label tituloPag;
    @FXML
    private TableColumn<Autor, Integer> codAutor;
    @FXML
    private TableColumn<Autor, String> nomeAutor;
    @FXML
    private TableColumn<Autor, String> nacionalidadeAutor;
    @FXML
    private TableColumn<Autor, String> dtNascAutor;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosAutor();
    }

    private void carregarDadosAutor() {
        AutorDAO autorDAO = new AutorDAO();
        try {
            List<Autor> autores = autorDAO.listarTodosAutores();
            ObservableList<Autor> observableList = FXCollections.observableArrayList(autores);
            tabelaClientes.setItems(observableList);
            codAutor.setCellValueFactory(new PropertyValueFactory<>("codAutor"));
            nomeAutor.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
            nacionalidadeAutor.setCellValueFactory(new PropertyValueFactory<>("nacionalidadeAutor"));
            dtNascAutor.setCellValueFactory(new PropertyValueFactory<>("dataNascimentoAutor"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
}
