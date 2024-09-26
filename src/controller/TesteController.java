/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class TesteController implements Initializable {

    @FXML
    private Button btnClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
}
