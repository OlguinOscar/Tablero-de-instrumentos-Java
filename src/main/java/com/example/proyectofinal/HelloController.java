package com.example.proyectofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloController {
    @FXML
    private PasswordField TextContrasena;

    @FXML
    private TextField TextUsuario;

    @FXML
    private Label welcomeText;

    @FXML
    void onIniciarClick(ActionEvent event) throws IOException, InterruptedException {
        if(TextUsuario.getText().equals("oscar") && TextContrasena.getText().equals("123")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("ventana de informacion");
            alert.setContentText("deseas ingresar al sistema?");
            ButtonType buttonTypeOK = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeOK){
                // cierra la ventana activa
                Node source = (Node) event.getSource();     //Recupera el elemnto activo
                Stage stage1 = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
                stage1.close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Tablero.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

            }
            else{
                Node source = (Node) event.getSource();     //Recupera el elemnto activo
                Stage stage1 = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
                stage1.close();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ventana de error");
            alert.setContentText("has ingresado datos incorrectos");
            alert.showAndWait();
        }
    }
}