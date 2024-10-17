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
import model.Autor;
import model.ClassesDAO.AutorDAO;
import model.ClassesDAO.EditoraDAO;
import model.ClassesDAO.GeneroDAO;
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
            livroDAO.deletarLivro(id);
            carregarDadosLivro();
            novo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
