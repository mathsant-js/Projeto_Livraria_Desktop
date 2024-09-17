/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import conexao.Conexao;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TelaLoginController implements Initializable {

    @FXML
    private Label tituloLogin;
    @FXML
    private TextField campoNome;
    @FXML
    private Label rotuloNome;
    @FXML
    private Label rotuloSenha;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Button botaoEntrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void botaoEntrarAction() throws IOException {
        Conexao clientConnection = new Conexao();
        clientConnection.connect();
        try {
            String pesquisa = "SELECT * FROM usuario WHERE nome_usuario like '" +campoNome.getText() +"' && senha_usuario = '" +campoSenha.getText() +"'";
            clientConnection.executeSQL(pesquisa);
            
            if (clientConnection.resultset.first()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensagem do Programa");
                alert.setHeaderText("Conectado ao sistema com sucesso!");
                alert.setContentText("Seja bem-vindo(a) " +clientConnection.resultset.getString("nome_usuario"));
                alert.showAndWait();

                Stage newStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource( "teste.fxml" ) );
                Scene scene = new Scene(root);

                newStage.setTitle("Tela Inicial");
                newStage.setScene(scene);
                newStage.show();
                
                Stage currentStage = (Stage) botaoEntrar.getScene().getWindow();
                currentStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mensagem do Programa");
                alert.setHeaderText("Usuário não encontrado!");
                alert.setContentText("Verifique se o nome de usuário ou senha está correto.");
                alert.showAndWait();
            }
        } catch (SQLException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Dados digitados não encontrados!");
            alert.setContentText(error.toString());
            alert.showAndWait();
        }
    }
    
}
