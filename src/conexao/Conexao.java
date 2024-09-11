
package conexao;

import javax.swing.JOptionPane;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Conexao {
    final private String driver ="com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://127.0.0.1/db_livraria";
    final private String usuario = "root";
    final private String senha = "";
    private Connection connection;
    public Statement statement;
    public ResultSet resultset;
    
    public boolean connect() {
        boolean result = true;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, senha);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Conexão Estabelecida!");
            alert.showAndWait();
        } catch (ClassNotFoundException Driver) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Driver não localizado!");
            alert.setContentText(Driver.toString());
            alert.showAndWait();
            
            result = false;
        } catch (SQLException Fonte) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Fonte de dados não encontrada!");
            alert.setContentText(Fonte.toString());
            alert.showAndWait();

            result = false;
        }
        return result;
    }
    
    public void disconnect() {
        try {
            connection.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Conexão com o banco fechada.");
            alert.showAndWait();
        } catch (SQLException fecha) {
            
        }
    }
    
    public void executeSQL(String sql) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        } catch (SQLException excecao) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensagem do Programa");
            alert.setHeaderText("Erro no comando SQL!");
            alert.setContentText(excecao.toString());
            alert.showAndWait();
        }
    }
}
