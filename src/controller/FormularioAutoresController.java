/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
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
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FormularioAutoresController implements Initializable {

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
    @FXML
    private TextField nacionalidadeField;
    @FXML
    private DatePicker dtNascField;
    @FXML
    private DatePicker dtFaleField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextArea biografiaField;
    @FXML
    private TableView<Autor> tabelaAutores;
    @FXML
    private TextField codField;
    @FXML
    private TableColumn<Autor, String> dtFaleAutor;
    @FXML
    private TextField buscaField;

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
            tabelaAutores.setItems(observableList);
            codAutor.setCellValueFactory(new PropertyValueFactory<>("codAutor"));
            nomeAutor.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
            nacionalidadeAutor.setCellValueFactory(new PropertyValueFactory<>("nacionalidadeAutor"));
            dtNascAutor.setCellValueFactory(new PropertyValueFactory<>("dataNascimentoAutor"));
            dtFaleAutor.setCellValueFactory(new PropertyValueFactory<>("dataFalecimentoAutor"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void exibirClicado() {
        int linha = tabelaAutores.getSelectionModel().getSelectedItem().getCodAutor();
        AutorDAO autorDAO = new AutorDAO();
        
        try {
            Autor autor = autorDAO.buscarAutoresPorId(linha);
            
            LocalDate localdateNascimento = autor.getDataNascimentoAutor().toLocalDate();
            LocalDate localdateFalecimento = autor.getDataFalecimentoAutor().toLocalDate();
            
            codField.setText(Integer.toString(autor.getCodAutor()));
            nomeField.setText(autor.getNomeAutor());
            biografiaField.setText(autor.getBiografiaAutor());
            dtNascField.setValue(localdateNascimento);
            dtFaleField.setValue(localdateFalecimento);
            nacionalidadeField.setText(autor.getNacionalidadeAutor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buscar() {
        AutorDAO autorDAO = new AutorDAO();
        try {
            List<Autor> autores = autorDAO.buscarAutoresPorNome(buscaField.getText());
            ObservableList<Autor> observableList = FXCollections.observableArrayList(autores);
            tabelaAutores.setItems(observableList);
            codAutor.setCellValueFactory(new PropertyValueFactory<>("codAutor"));
            nomeAutor.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
            nacionalidadeAutor.setCellValueFactory(new PropertyValueFactory<>("nacionalidadeAutor"));
            dtNascAutor.setCellValueFactory(new PropertyValueFactory<>("dataNascimentoAutor"));
            dtFaleAutor.setCellValueFactory(new PropertyValueFactory<>("dataFalecimentoAutor"));
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

            Stage currentStage = (Stage) tabelaAutores.getScene().getWindow();
            currentStage.close();
        }
    }
    
    @FXML
    private void novo() {
        codField.setText("");
        nomeField.setText("");
        dtNascField.setValue(null);
        dtFaleField.setValue(null);
        nacionalidadeField.setText("");
        biografiaField.setText("");
    }
    
    private boolean verificacaoCampos() {
        if ("".equals(nomeField.getText()) && "".equals(biografiaField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campos vazios!!!");
            alert.setContentText("Todos os campos estão vazios");
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
            alert.setContentText("Algum dos campos está vazio!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        } else if ("".equals(biografiaField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Algum dos campos está vazio!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        } else if ("".equals(dtNascField.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Algum dos campos está vazio!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        } else if (dtNascField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Algum dos campos está vazio!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        } else if (dtFaleField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Algum dos campos está vazio!");
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
    private void cadastrarAutor() {
        if (verificacaoCampos()) {
        } else {
            String nomeAutor = nomeField.getText();
            Date dataNascimentoAutor = Date.valueOf(dtNascField.getValue());
            Date dataFalecimentoAutor = Date.valueOf(dtFaleField.getValue());
            String nacionalidadeAutor = nacionalidadeField.getText();
            String biografiaAutor = biografiaField.getText();
            AutorDAO autorDAO = new AutorDAO();
            Autor autor = new Autor(nomeAutor, biografiaAutor, dataNascimentoAutor, dataFalecimentoAutor, nacionalidadeAutor);
            try {
                autorDAO.cadastrarAutor(autor);
                carregarDadosAutor();
                novo();
            } catch (Exception e) {
                System.out.println("Erro ao adicionar autor: " + e.getMessage());
            }
        }
    }
    
    @FXML
    private void atualizarAutor() {
        int codAutor = Integer.parseInt(codField.getText());
        String nomeAutor = nomeField.getText();
        String nacionalidadeAutor = nacionalidadeField.getText();
        Date dataNascimentoAutor = Date.valueOf(dtNascField.getValue());
        Date dataFalecimentoAutor = Date.valueOf(dtFaleField.getValue());
        String biografiaAutor = biografiaField.getText();
        AutorDAO autorDAO = new AutorDAO();
        
        try {
            autorDAO.atualizarAutor(codAutor, nomeAutor, biografiaAutor, dataNascimentoAutor, dataFalecimentoAutor, nacionalidadeAutor);
            carregarDadosAutor();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar autor: " + e);
        }
    }
    
    @FXML
    private void excluirAutor() {
        int id = Integer.parseInt(codField.getText());
        AutorDAO autorDAO = new AutorDAO();
        
        try {
            autorDAO.deletarAutor(id);
            carregarDadosAutor();
            novo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
