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
import model.Editora;
import model.ClassesDAO.EditoraDAO;
import java.sql.*;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FormularioEditorasController implements Initializable {

    @FXML
    private Label tituloPag;
    @FXML
    private TableView<Editora> tabelaEditoras;
    @FXML
    private TableColumn<Editora, Integer> codEditora;
    @FXML
    private TableColumn<Editora, String> nomeEditora;
    @FXML
    private TableColumn<Editora, String> enderecoEditora;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosEditora();
    }    
    
    private void carregarDadosEditora() {
        EditoraDAO editoraDAO = new EditoraDAO();
        try {
            List<Editora> editoras = editoraDAO.listarTodosEditoras();
            ObservableList<Editora> observableList = FXCollections.observableArrayList(editoras);
            tabelaEditoras.setItems(observableList);
            codEditora.setCellValueFactory(new PropertyValueFactory<>("codEditora"));
            nomeEditora.setCellValueFactory(new PropertyValueFactory<>("nomeEditora"));
            enderecoEditora.setCellValueFactory(new PropertyValueFactory<>("enderecoEditora"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
