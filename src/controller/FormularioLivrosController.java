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
import model.Livro;
import model.ClassesDAO.LivroDAO;
import java.sql.*;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Autor;
import model.ClassesDAO.AutorDAO;
import model.ClassesDAO.EditoraDAO;
import model.ClassesDAO.GeneroDAO;
import model.Editora;
import model.Genero;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FormularioLivrosController implements Initializable {
    @FXML
    private Label tituloPag;
    @FXML
    private TableView<Livro> tabelaLivros;
    @FXML
    private TableColumn<Livro, Integer> codLivro;
    @FXML
    private TableColumn<Livro, String> nomeLivro;
    @FXML
    private TableColumn<Livro, String> autorLivro;
    @FXML
    private TableColumn<Livro, Date> dtLancLivro;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField codField;
    @FXML
    private TextField tituloField;
    @FXML
    private DatePicker dtLancField;
    @FXML
    private TextField precoField;
    @FXML
    private TextArea descField;
    @FXML
    private TextField codAutorField;
    @FXML
    private TextField autorField;
    @FXML
    private TextField codEditoraField;
    @FXML
    private TextField editoraField;
    @FXML
    private TextField codGeneroField;
    @FXML
    private TextField generoField;
    @FXML
    private TextField buscaField;

    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
        carregarDadosLivro();
    }    
    
    private void carregarDadosLivro() {
        LivroDAO livroDAO = new LivroDAO();
        try {
            List<Livro> livros = livroDAO.listarTodosLivros();
            ObservableList<Livro> observableList = FXCollections.observableArrayList(livros);
            tabelaLivros.setItems(observableList);
            codLivro.setCellValueFactory(new PropertyValueFactory<>("codLivro"));
            nomeLivro.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
            autorLivro.setCellValueFactory(new PropertyValueFactory<>("codAutor"));
            dtLancLivro.setCellValueFactory(new PropertyValueFactory<>("dataLancamentoLivro"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void preencherAutor() {
        AutorDAO autorDAO = new AutorDAO();
        try {
            if ((codAutorField.getText()).isEmpty()) {
                autorField.setText("Autor não encontrado.");
            } else {
                Autor autor = autorDAO.buscarAutoresPorId(Integer.parseInt(codAutorField.getText()));
                String nome = autor.getNomeAutor();
                autorField.setText(nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void preencherEditora() {
        EditoraDAO editoraDAO = new EditoraDAO();
        try {
            if ((codEditoraField.getText()).isEmpty()) {
                editoraField.setText("Editora não encontrada.");
            } else {
                Editora editora = editoraDAO.buscarEditoraPorId(Integer.parseInt(codEditoraField.getText()));
                String nome = editora.getNomeEditora();
                editoraField.setText(nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void preencherGenero() {
        GeneroDAO generoDAO = new GeneroDAO();
        try {
            if ((codGeneroField.getText()).isEmpty()) {
                generoField.setText("Gênero não encontrado.");
            } else {
                Genero genero = generoDAO.buscarGeneroPorId(Integer.parseInt(codGeneroField.getText()));
                String nome = genero.getNomeGenero();
                generoField.setText(nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void exibirClicado() {
        int linha = tabelaLivros.getSelectionModel().getSelectedItem().getCodLivro();
        LivroDAO livroDAO = new LivroDAO();
        
        try {
            Livro livro = livroDAO.buscarLivroPorId(linha);
            
            codField.setText(Integer.toString(livro.getCodLivro()));
            tituloField.setText(livro.getNomeLivro());
            isbnField.setText(livro.getIsbnLivro());
            dtLancField.setValue(livro.getDataLancamentoLivro().toLocalDate());
            precoField.setText(Float.toString(livro.getCodLivro()));
            codAutorField.setText(Integer.toString(livro.getCodAutor()));
            codEditoraField.setText(Integer.toString(livro.getCodEditora()));
            codGeneroField.setText(Integer.toString(livro.getCodGenero()));
            descField.setText(livro.getDescricaoLivro());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void buscar() {
        LivroDAO livroDAO = new LivroDAO();
        try {
            List<Livro> livros = livroDAO.buscarLivrosPorNome(buscaField.getText());
            ObservableList<Livro> observableList = FXCollections.observableArrayList(livros);
            tabelaLivros.setItems(observableList);
            codLivro.setCellValueFactory(new PropertyValueFactory<>("codLivro"));
            nomeLivro.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
            autorLivro.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
            dtLancLivro.setCellValueFactory(new PropertyValueFactory<>("dataLancamentoLivro"));
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

            Stage currentStage = (Stage) tabelaLivros.getScene().getWindow();
            currentStage.close();
        }
    }
    
    @FXML
    private void novo() {
        codField.setText("");
        tituloField.setText("");
        isbnField.setText("");
        dtLancField.setValue(null);
        precoField.setText("");
        codAutorField.setText("");
        autorField.setText("");
        codEditoraField.setText("");
        editoraField.setText("");
        codGeneroField.setText("");
        generoField.setText("");
        descField.setText("");
    }
    
    private boolean verificacaoCampos() {
        if ("".equals(tituloField.getText()) && "".equals(isbnField.getText()) && dtLancField.getValue() == null && "".equals(precoField.getText()) && "".equals(descField.getText())){
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
        } else if ("".equals(tituloField.getText())){
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
        } else if ("".equals(isbnField.getText())) {
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
        } else if (dtLancField.getValue() == null) {
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
        } else if ("".equals(precoField.getText())) {
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
        } else if ("".equals(descField.getText())) {
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
    
    private boolean confirmarDelecao() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de deleção de cadastro");
        alert.setContentText("Deseja realmente deletar o cadastro selecionado?");
        
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            
        }
        return true;
    }
    
    @FXML
    private void cadastrarLivro() {
        String nomeLivro = tituloField.getText();
        String isbnLivro = isbnField.getText();
        Date dtLancLivro = java.sql.Date.valueOf(dtLancField.getValue());
        Float precoLivro = Float.parseFloat(precoField.getText());
        int codAutor = Integer.parseInt(codAutorField.getText());
        int codEditora = Integer.parseInt(codEditoraField.getText());
        int codGenero = Integer.parseInt(codGeneroField.getText());
        String descLivro = descField.getText();
        
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = new Livro(nomeLivro, isbnLivro, dtLancLivro, precoLivro, codAutor, codEditora, codGenero, descLivro);
        try {
            livroDAO.cadastrarLivro(livro);
            carregarDadosLivro();
            novo();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar livro: " + e);
        }
    }
    
    @FXML
    private void atualizarLivro() {
        int codLivro = Integer.parseInt(codField.getText());
        String nomeLivro = tituloField.getText();
        String isbnLivro = isbnField.getText();
        Date dtLancLivro = java.sql.Date.valueOf(dtLancField.getValue());
        Float precoLivro = Float.parseFloat(precoField.getText());
        int codAutor = Integer.parseInt(codAutorField.getText());
        int codEditora = Integer.parseInt(codEditoraField.getText());
        int codGenero = Integer.parseInt(codGeneroField.getText());
        String descLivro = descField.getText();
        
        LivroDAO livroDAO = new LivroDAO();
        
        try {
            livroDAO.atualizarLivro(codLivro, nomeLivro, isbnLivro, dtLancLivro, precoLivro, codAutor, codEditora, codGenero, descLivro);
            carregarDadosLivro();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro: " + e);
        }
    }
    
    @FXML
    private void excluirLivro() {
        int id = Integer.parseInt(codField.getText());
        LivroDAO livroDAO = new LivroDAO();
        
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
                livroDAO.deletarLivro(id);
                carregarDadosLivro();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
