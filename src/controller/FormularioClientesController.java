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
import model.Cliente;
import model.ClienteDAO;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FormularioClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private TableColumn<Cliente, Integer> codCli;
    @FXML
    private TableColumn<Cliente, String> nomeCliente;
    @FXML
    private TableColumn<Cliente, String> dtNascCliente;
    @FXML
    private TableColumn<Cliente, String> clienteCpf;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosCliente();
    }    
    
    @FXML
    private void carregarDadosCliente() {
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            List<Cliente> clientes = clienteDAO.listarTodos();
            ObservableList<Cliente> observableList = FXCollections.observableArrayList(clientes);
            tabelaClientes.setItems(observableList);
            codCli.setCellValueFactory(new PropertyValueFactory<>("codCli"));
            nomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
            clienteCpf.setCellValueFactory(new PropertyValueFactory<>("clienteCpf"));
            dtNascCliente.setCellValueFactory(new PropertyValueFactory<>("dtNascCliente"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
