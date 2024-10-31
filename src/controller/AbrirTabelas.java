/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author Matheus Santana
 */
public class AbrirTabelas {
    public void abrirAutores(TableView tabela) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FormularioAutores.fxml"));
        Scene scene = new Scene(root);
        
        newStage.setTitle("Tela Autores");
        newStage.setScene(scene);
        newStage.show();
        
        Stage currentStage = (Stage) tabela.getScene().getWindow();
        currentStage.close();
    }
    
    public void abrirCliente(TableView tabela) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource( "FormularioClientes.fxml" ));
        Scene scene = new Scene(root);

        newStage.setTitle("Tela Clientes");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) tabela.getScene().getWindow();
        currentStage.close();
    }
    
    public void abrirEditoras(TableView tabela) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FormularioEditoras.fxml"));
        Scene scene = new Scene(root);
        
        newStage.setTitle("Tela Editoras");
        newStage.setScene(scene);
        newStage.show();
        
        Stage currentStage = (Stage) tabela.getScene().getWindow();
        currentStage.close();
    }
    
    public void abrirGeneros(TableView tabela) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FormularioGeneros.fxml"));
        Scene scene = new Scene(root);
        
        newStage.setTitle("Tela Gêneros");
        newStage.setScene(scene);
        newStage.show();
        
        Stage currentStage = (Stage) tabela.getScene().getWindow();
        currentStage.close();
    }
    
    public void abrirLivros(TableView tabela) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FormularioLivros.fxml"));
        Scene scene = new Scene(root);
        
        newStage.setTitle("Tela Livros");
        newStage.setScene(scene);
        newStage.show();
        
        Stage currentStage = (Stage) tabela.getScene().getWindow();
        currentStage.close();
    }
}
