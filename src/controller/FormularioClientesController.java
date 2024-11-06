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
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
        setarFormatacoes();
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
    private void abrirJanelaSobre() throws IOException {
        SobrePrograma sobre = new SobrePrograma();
        sobre.abrirSobre();
    }
    
    @FXML
    private void abritAutorTabela() throws IOException {
        AbrirTabelas autor = new AbrirTabelas();
        autor.abrirAutores(tabelaClientes);
    }
    
    @FXML
    private void abrirEditoraTabela() throws IOException {
        AbrirTabelas autor = new AbrirTabelas();
        autor.abrirEditoras(tabelaClientes);
    }
    
    @FXML
    private void abrirGeneroTabela() throws IOException {
        AbrirTabelas cliente = new AbrirTabelas();
        cliente.abrirGeneros(tabelaClientes);
    }
    
    @FXML
    private void abrirLivroTabela() throws IOException {
        AbrirTabelas cliente = new AbrirTabelas();
        cliente.abrirLivros(tabelaClientes);
    }
    
    @FXML
    private void novo() {
        codField.setText("");
        nomeField.setText("");
        cpfField.setText("");
        dtNascField.setValue(null);
        emailField.setText("");
        telefoneField.setText("");
        enderecoField.setText("");
        senhaField.setText("");
    }
    
    private boolean verificacaoCampos() {
        LocalDate hoje = LocalDate.now();
        if ("".equals(nomeField.getText()) && "".equals(cpfField.getText()) && dtNascField.getValue() == null && "".equals(emailField.getText()) && "".equals(telefoneField.getText()) && "".equals(enderecoField.getText()) && "".equals(senhaField.getText())){
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
        } else if ("".equals(cpfField.getText()) || cpfField.getText().length() < 14 || cpfField.getText().length() > 14) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Algum dos campos está vazio ou incorreto!");
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
            alert.setContentText("Algum dos campos está vazio ou está incorreto!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Warning.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            return true;
        } if (dtNascField.getValue().isAfter(hoje)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Data de nascimento a frente da data atual!");
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
        } else if ("".equals(telefoneField.getText()) || telefoneField.getText().length() < 14 || telefoneField.getText().length() > 14) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Campo vazio!");
            alert.setContentText("Algum dos campos está vazio ou incorreto!");
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
    
    private void setarFormatacoes() {
        setarFormatacaoNome(nomeField);
        applyCpfMask(cpfField);
        applyPhoneMask(telefoneField);
        dtNascField.setPromptText("XX/XX/XXXX");
        cpfField.setPromptText("Digite o CPF");
        emailField.setPromptText("Digite o email");
        telefoneField.setPromptText("Digite o telefone");
        enderecoField.setPromptText("Digite o endereço");
        senhaField.setPromptText("Digite a senha");
    }
    
    private void setarFormatacaoNome(TextField textField) {
        nomeField.setPromptText("Digite o nome");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            formatacaoNome(textField, newValue);
        });
    }
    
    private void formatacaoNome(TextField textField, String newValue) {
        if (!newValue.matches("[a-zA-Z]*")) {
            textField.setText(newValue.replaceAll("[^a-zA-Z]", ""));
        }
    }
    
    public static void applyCpfMask(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String value = newValue.replaceAll("[^0-9]", "");
            StringBuilder formattedValue = new StringBuilder(value);

            if (value.length() > 3) formattedValue.insert(3, '.');
            if (value.length() > 6) formattedValue.insert(7, '.');
            if (value.length() > 9) formattedValue.insert(11, '-');
            if (value.length() > 11) formattedValue.setLength(14); // limita ao tamanho de um CPF
            
            textField.setText(formattedValue.toString());
            textField.positionCaret(formattedValue.length());
        });
    }

    public static void applyPhoneMask(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String value = newValue.replaceAll("[^0-9]", "");
            StringBuilder formattedValue = new StringBuilder(value);

            if (value.length() > 0) formattedValue.insert(0, '(');
            if (value.length() > 2) formattedValue.insert(3, ')');
            if (value.length() > 6) formattedValue.insert(9, '-');
            if (value.length() > 10) formattedValue.setLength(14); // limite para (XX) XXXX-XXXX
            
            textField.setText(formattedValue.toString());
            textField.positionCaret(formattedValue.length());
        });
    }
    
    @FXML
    private void cadastrarCliente() {
        if (verificacaoCampos()) {
        } else {
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
