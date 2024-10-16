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
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    @FXML
    private TextField nomeField;
    @FXML
    private TextField descricaoField;
    @FXML
    private TextField buscaField;
    @FXML
    private TextField codField;

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
    
    @FXML
    private void exibirClicado() {
        int linha = tabelaGeneros.getSelectionModel().getSelectedItem().getCodGenero();
        GeneroDAO generoDAO = new GeneroDAO();
        
        try {
            Genero genero = generoDAO.buscarGeneroPorId(linha);
            
            codField.setText(Integer.toString(genero.getCodGenero()));
            nomeField.setText(genero.getNomeGenero());
            descricaoField.setText(genero.getDescricaoGenero());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buscar() {
        GeneroDAO generoDAO = new GeneroDAO();
        try {
            List<Genero> generos = generoDAO.buscarGenerosPorNome(buscaField.getText());
            ObservableList<Genero> observableList = FXCollections.observableArrayList(generos);
            tabelaGeneros.setItems(observableList);
            codGenero.setCellValueFactory(new PropertyValueFactory<>("codGenero"));
            nomeGenero.setCellValueFactory(new PropertyValueFactory<>("nomeGenero"));
            descricaoGenero.setCellValueFactory(new PropertyValueFactory<>("descricaoGenero"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void sair() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de saída");
        alert.setContentText("Deseja realmente sair do programa?");
        
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            System.exit(0);
        }
    }
    
    @FXML
    private void novo() {
        codField.setText("");
        nomeField.setText("");
        descricaoField.setText("");
    }
    
    @FXML
    private void cadastrarGenero() {
        String nomeGenero = nomeField.getText();
        String descricaoGenero = descricaoField.getText();
        GeneroDAO generoDAO = new GeneroDAO();
        Genero genero = new Genero(nomeGenero, descricaoGenero);
        try {
            generoDAO.cadastrarGenero(genero);
            carregarDadosGenero();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar gênero: " + e);
        }
    }
    
    @FXML
    private void atualizarGenero() {
        int codGenero = Integer.parseInt(codField.getText());
        String nomeGenero = nomeField.getText();
        String descricaoGenero = descricaoField.getText();
        GeneroDAO generoDAO = new GeneroDAO();
        
        try {
            generoDAO.atualizarGenero(codGenero, nomeGenero, descricaoGenero);
            carregarDadosGenero();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar gênero: " + e);
        }
    }
    
    @FXML
    private void excluirGenero() {
        int id = Integer.parseInt(codField.getText());
        GeneroDAO generoDAO = new GeneroDAO();
        
        try {
            generoDAO.deletarGenero(id);
            carregarDadosGenero();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
