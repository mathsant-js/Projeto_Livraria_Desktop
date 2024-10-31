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
import model.Cliente;
import model.ClassesDAO.ClienteDAO;
import java.sql.*;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ClassesDAO.EmailClienteDAO;
import model.ClassesDAO.EnderecoClienteDAO;
import model.ClassesDAO.TelefoneClienteDAO;
import model.EmailCliente;
import model.EnderecoCliente;
import model.TelefoneCliente;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FormularioClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TableColumn<Cliente, Integer> codCli;
    @FXML
    private TableColumn<Cliente, String> nomeCliente;
    @FXML
    private TableColumn<Cliente, String> dtNascCliente;
    @FXML
    private TableColumn<Cliente, String> clienteCpf;
    @FXML
    private Label tituloPag;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cpfField;
    @FXML
    private DatePicker dtNascField;
    @FXML
    private TextField codField;
    @FXML
    private TextField buscaField;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosCliente();
    }    
    
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
    
    @FXML
    private void exibirClicado() {
        int linha = tabelaClientes.getSelectionModel().getSelectedItem().getCodCli();
        ClienteDAO clienteDAO = new ClienteDAO();
        EmailClienteDAO emailclienteDAO = new EmailClienteDAO();
        TelefoneClienteDAO telefoneclienteDAO = new TelefoneClienteDAO();
        EnderecoClienteDAO enderecoclienteDAO = new EnderecoClienteDAO();
        
        try {
            Cliente cliente = clienteDAO.buscarPorId(linha);
            EmailCliente emailcliente = emailclienteDAO.obterEmailCliente(linha);
            TelefoneCliente telefonecliente = telefoneclienteDAO.obterTelefoneCliente(linha);
            EnderecoCliente enderecocliente = enderecoclienteDAO.obterEnderecoCliente(linha);
            
            codField.setText(Integer.toString(cliente.getCodCli()));
            nomeField.setText(cliente.getNomeCliente());
            nomeField.setText(cliente.getNomeCliente());
            cpfField.setText(cliente.getClienteCpf());
            dtNascField.setValue(cliente.getDtNascCliente().toLocalDate());
            emailField.setText(emailcliente.getEmailCliente());
            telefoneField.setText(telefonecliente.getTelefoneCliente());
            enderecoField.setText(enderecocliente.getEnderecoCliente());
            senhaField.setText(cliente.getSenhaCliente());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buscar() {
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            List<Cliente> clientes = clienteDAO.buscarPorNome(buscaField.getText());
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

            Stage currentStage = (Stage) tabelaClientes.getScene().getWindow();
            currentStage.close();
        }
    }
    
    @FXML
    private void novo() {
        codField.setText("");
        nomeField.setText("");
        nomeField.setText("");
        cpfField.setText("");
        dtNascField.setValue(null);
        emailField.setText("");
        telefoneField.setText("");
        enderecoField.setText("");
        senhaField.setText("");
    }
    
    private boolean verificacaoCampos() {
        if ("".equals(nomeField.getText()) && "".equals(cpfField.getText()) && dtNascField.getValue() == null && "".equals(emailField.getText()) && "".equals(telefoneField.getText()) && "".equals(enderecoField.getText()) && "".equals(senhaField.getText())){
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
        } else if ("".equals(cpfField.getText())) {
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
        } else if ("".equals(emailField.getText())) {
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
        } else if ("".equals(senhaField.getText())) {
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
    private void cadastrarCliente() {
        String nomeCliente = nomeField.getText();
        String cpfCliente = cpfField.getText();
        Date dtNascCliente = java.sql.Date.valueOf(dtNascField.getValue());
        String emailCliente = emailField.getText();
        String telefoneCliente = telefoneField.getText();
        String enderecoCliente = enderecoField.getText();
        String senhaCliente = senhaField.getText();
        
        ClienteDAO clienteDAO = new ClienteDAO();
        EmailClienteDAO emailclienteDAO = new EmailClienteDAO();
        TelefoneClienteDAO telefoneclienteDAO = new TelefoneClienteDAO();
        EnderecoClienteDAO enderecoclienteDAO = new EnderecoClienteDAO();
        
        Cliente cliente = new Cliente(nomeCliente, cpfCliente, dtNascCliente, senhaCliente);
        try {
            clienteDAO.cadastrarCliente(cliente, telefoneCliente, emailCliente, enderecoCliente);
            carregarDadosCliente();
            novo();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e);
        }
    }
    
    @FXML
    private void atualizarCliente() {
        int codCliente = Integer.parseInt(codField.getText());
        String nomeCliente = nomeField.getText();
        String cpfCliente = cpfField.getText();
        Date dtNascCliente = java.sql.Date.valueOf(dtNascField.getValue());
        String emailCliente = emailField.getText();
        String telefoneCliente = telefoneField.getText();
        String enderecoCliente = enderecoField.getText();
        String senhaCliente = senhaField.getText();
        
        ClienteDAO clienteDAO = new ClienteDAO();
        EmailClienteDAO emailclienteDAO = new EmailClienteDAO();
        TelefoneClienteDAO telefoneclienteDAO = new TelefoneClienteDAO();
        EnderecoClienteDAO enderecoclienteDAO = new EnderecoClienteDAO();
        
        try {
            clienteDAO.atualizarCliente(codCliente, nomeCliente, cpfCliente, dtNascCliente, senhaCliente, telefoneCliente,emailCliente, enderecoCliente);
            carregarDadosCliente();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e);
        }
    }
    
    @FXML
    private void excluirCliente() {
        int id = Integer.parseInt(codField.getText());
        ClienteDAO clienteDAO = new ClienteDAO();
        EmailClienteDAO emailclienteDAO = new EmailClienteDAO();
        TelefoneClienteDAO telefoneclienteDAO = new TelefoneClienteDAO();
        EnderecoClienteDAO enderecoclienteDAO = new EnderecoClienteDAO();
        
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
                clienteDAO.deletarCliente(id);
                carregarDadosCliente();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
