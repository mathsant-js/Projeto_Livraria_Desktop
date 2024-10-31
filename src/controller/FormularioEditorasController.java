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
import model.Editora;
import model.ClassesDAO.EditoraDAO;
import java.sql.*;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ClassesDAO.TelefoneEditoraDAO;
import model.TelefoneEditora;

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
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField codField;
    @FXML
    private TextField buscaField;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosEditora();
    }    
    
    @FXML
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Não foi possível atualizar a tabela");
            alert.setContentText("Erro no banco de dados do sistema!\nContate o suporte!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
        }
    }
    
    @FXML
    private void exibirClicado() {
        int linha = tabelaEditoras.getSelectionModel().getSelectedItem().getCodEditora();
        EditoraDAO editoraDAO = new EditoraDAO();
        TelefoneEditoraDAO telefoneEditoraDAO = new TelefoneEditoraDAO();
        
        try {
            Editora editora = editoraDAO.buscarEditoraPorId(linha);
            TelefoneEditora telefoneEditora = telefoneEditoraDAO.obterTelefoneEditora(linha);
            
            codField.setText(Integer.toString(editora.getCodEditora()));
            nomeField.setText(editora.getNomeEditora());
            enderecoField.setText(editora.getEnderecoEditora());
            telefoneField.setText(telefoneEditora.getTelefoneEditora());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buscar() {
        EditoraDAO editoraDAO = new EditoraDAO();
        try {
            List<Editora> editoras = editoraDAO.buscarEditorasPorNome(buscaField.getText());
            ObservableList<Editora> observableList = FXCollections.observableArrayList(editoras);
            tabelaEditoras.setItems(observableList);
            codEditora.setCellValueFactory(new PropertyValueFactory<>("codEditora"));
            nomeEditora.setCellValueFactory(new PropertyValueFactory<>("nomeEditora"));
            enderecoEditora.setCellValueFactory(new PropertyValueFactory<>("enderecoEditora"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void sair() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de saída");
        alert.setContentText("Deseja realmente sair do programa?");
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");
        ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Question.png"))));
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.getDialogPane().setGraphic(icon);
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
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");
        ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Question.png"))));
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.getDialogPane().setGraphic(icon);
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource( "teste.fxml" ) );
            Scene scene = new Scene(root);

            newStage.setTitle("Tela Inicial");
            newStage.setScene(scene);
            newStage.show();

            Stage currentStage = (Stage) tabelaEditoras.getScene().getWindow();
            currentStage.close();
        }
    }
    
    @FXML
    private void novo() {
        codField.setText("");
        nomeField.setText("");
        enderecoField.setText("");
        telefoneField.setText("");
    }
    
    private boolean verificacaoCampos() {
        if ("".equals(nomeField.getText()) && "".equals(enderecoField.getText()) && "".equals(telefoneField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campos vazios!!!");
            alert.setContentText("Todos os campos vazios!");
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
        } else if ("".equals(enderecoField.getText())) {
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
        } else if ("".equals(telefoneField.getText())) {
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
    private void cadastrarEditora() {
        String nomeEditora = nomeField.getText();
        String enderecoEditora = enderecoField.getText();
        String telefoneEditora = telefoneField.getText();
        
        EditoraDAO editoraDAO = new EditoraDAO();
        Editora editora = new Editora(nomeEditora, enderecoEditora);
        try {
            editoraDAO.cadastrarEditora(editora, telefoneEditora);
            carregarDadosEditora();
            novo();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar editora: " + e);
        }
    }
    
    @FXML
    private void atualizarEditora() {
        int codEditora = Integer.parseInt(codField.getText());
        String nomeEditora = nomeField.getText();
        String enderecoEditora = enderecoField.getText();
        String telefoneEditora = telefoneField.getText();
        EditoraDAO editoraDAO = new EditoraDAO();
        
        try {
            editoraDAO.atualizarEditora(codEditora, nomeEditora, enderecoEditora, telefoneEditora);
            carregarDadosEditora();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar gênero: " + e);
        }
    }
    
    @FXML
    private void excluirEditora() {
        int id = Integer.parseInt(codField.getText());
        EditoraDAO editoraDAO = new EditoraDAO();
        TelefoneEditoraDAO telefoneEditoraDAO = new TelefoneEditoraDAO();
        
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
                editoraDAO.deletarEditora(id);
                carregarDadosEditora();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
