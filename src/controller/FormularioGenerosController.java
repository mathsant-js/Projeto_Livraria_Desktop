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
import model.Genero;
import model.ClassesDAO.GeneroDAO;
import java.sql.*;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FormularioGenerosController implements Initializable {
    @FXML
    private Label tituloPag;
    @FXML
    private TableView<Genero> tabelaGeneros;
    @FXML
    private TableColumn<Genero, Integer> codGenero;
    @FXML
    private TableColumn<Genero, String> nomeGenero;
    @FXML
    private TableColumn<Genero, String> descricaoGenero;

    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosGenero();
    }

    private void carregarDadosGenero() {
        GeneroDAO generoDAO = new GeneroDAO();
        try {
            List<Genero> generos = generoDAO.listarTodosGeneros();
            ObservableList<Genero> observableList = FXCollections.observableArrayList(generos);
            tabelaGeneros.setItems(observableList);
            codGenero.setCellValueFactory(new PropertyValueFactory<>("codGenero"));
            nomeGenero.setCellValueFactory(new PropertyValueFactory<>("nomeGenero"));
            descricaoGenero.setCellValueFactory(new PropertyValueFactory<>("descricaoGenero"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
