/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private void voltar() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de retorno");
        alert.setContentText("Deseja realmente fechar esta tabela e voltar à página inicial?");
        
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource( "teste.fxml" ) );
            Scene scene = new Scene(root);

            newStage.setTitle("Tela Inicial");
            newStage.setScene(scene);
            newStage.show();

            Stage currentStage = (Stage) tabelaGeneros.getScene().getWindow();
            currentStage.close();
        }
    }
    
    @FXML
    private void novo() {
        codField.setText("");
        nomeField.setText("");
        descricaoField.setText("");
    }
    
    private boolean verificacaoCampos() {
        if ("".equals(nomeField.getText()) && "".equals(descricaoField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campos vazios!!!");
            alert.setContentText("Campo nome vazio!\nCampo descrição vazio!\nDigite o nome e a descrição do gênero.");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        } else if ("".equals(nomeField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!!!");
            alert.setContentText("Campo nome vazio!\nDigite o nome do gênero.");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        } else if ("".equals(descricaoField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Campo descrição vazio!\nDigite a descrição do gênero.");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        }
        return false;
    }
    
    @FXML
    private void cadastrarGenero() {
        if (verificacaoCampos()) {
        } else {
            String nomeGenero = nomeField.getText();
            String descricaoGenero = descricaoField.getText();
            GeneroDAO generoDAO = new GeneroDAO();
            Genero genero = new Genero(nomeGenero, descricaoGenero);
            try {
                generoDAO.cadastrarGenero(genero);
                carregarDadosGenero();
                novo();
            } catch (Exception e) {
                System.out.println("Erro ao cadastrar gênero: " + e);
            }
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleção de Cadastro");
            alert.setContentText("Deseja realmente deletar o cadastro?");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Question.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                generoDAO.deletarGenero(id);
                carregarDadosGenero();
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
