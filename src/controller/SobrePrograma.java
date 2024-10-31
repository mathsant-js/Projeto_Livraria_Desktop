/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Matheus Santana
 */
public class SobrePrograma {
    public void abrirSobre() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Sobre o Programa");
            alert.setContentText("Programa para gereciamento de dados da Open Book\nVersão: 1.0\n\nDesenvolvido por:\nLucas Tino Rosa\nMatheus Jorge Santana");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("custom-alert");
            ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/icons/Information.png"))));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
    }
}
