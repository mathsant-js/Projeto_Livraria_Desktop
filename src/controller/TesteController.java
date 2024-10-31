/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class TesteController implements Initializable {

    @FXML
    private Button btnClientes;
    @FXML
    private Button btnAutores;
    @FXML
    private Button btnEditoras;
    @FXML
    private Button btnGeneros;
    @FXML
    private Button btnLivros;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private Text nomeAdministrador;
    @FXML
    private Button btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void abrirJanelaSobre() throws IOException {
        SobrePrograma sobre = new SobrePrograma();
        sobre.abrirSobre();
    }
    
    @FXML
    public void btnClientes() throws IOException{
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource( "FormularioClientes.fxml" ) );
        Scene scene = new Scene(root);

        newStage.setTitle("Tela Clientes");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) btnClientes.getScene().getWindow();
        currentStage.close();
    }
    
    @FXML
    public void btnAutores() throws IOException{
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource( "FormularioAutores.fxml" ) );
        Scene scene = new Scene(root);

        newStage.setTitle("Tela Autores");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) btnAutores.getScene().getWindow();
        currentStage.close();
    }
    
    @FXML
    public void btnEditoras() throws IOException{
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource( "FormularioEditoras.fxml" ) );
        Scene scene = new Scene(root);

        newStage.setTitle("Tela Editoras");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) btnEditoras.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void btnGeneros() throws IOException{
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource( "FormularioGeneros.fxml" ) );
        Scene scene = new Scene(root);

        newStage.setTitle("Tela Gêneros");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) btnGeneros.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void btnLivros() throws IOException{
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource( "FormularioLivros.fxml" ) );
        Scene scene = new Scene(root);

        newStage.setTitle("Tela Livros");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) btnLivros.getScene().getWindow();
        currentStage.close();
    }
    
    @FXML
    private void voltarParaLogin() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de saída");
        alert.setContentText("Deseja realmente sair?\nVocê voltará para a tela de login");
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
            Parent root = FXMLLoader.load(getClass().getResource( "TelaLogin.fxml" ) );
            Scene scene = new Scene(root);

            newStage.setTitle("Tela Inicial");
            newStage.setScene(scene);
            newStage.show();

            Stage currentStage = (Stage) btnSair.getScene().getWindow();
            currentStage.close();
        }
    }
}
