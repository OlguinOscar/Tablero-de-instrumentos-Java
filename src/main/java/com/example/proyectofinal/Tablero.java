package com.example.proyectofinal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tablero {


    @FXML
    private ImageView aceite;

    @FXML
    private ImageView direccional_derecha;

    @FXML
    private ImageView direccional_izquierda;

    @FXML
    private Label la_hora;

    @FXML
    private Label la_velocidad;

    @FXML
    private ImageView batery;

    @FXML
    private Label la_temperatura;

    @FXML
    private ImageView luces_altas;

    Image flecha_izquierda_negra = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/flecha_verde_negra.jpg");
    Image flecha_derecha_negra = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/flecha_negra_derecha.jpg");
    Image bateria_negra = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/bateria_negra.jpg");
    Image aceite_negro = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/nivel_aceite_negro.jpg");
    Image luces_negro = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/luces_altas_negro.png");
    @FXML
    public void initialize() {

        direccional_izquierda.setImage(flecha_izquierda_negra);
        direccional_derecha.setImage(flecha_derecha_negra);
        batery.setImage(bateria_negra);
        aceite.setImage(aceite_negro);
        luces_altas.setImage(luces_negro);

        SerialComunication serial = new SerialComunication(la_velocidad,direccional_izquierda,direccional_derecha,la_temperatura,aceite,luces_altas,batery); // hilo en java
        serial.start();
        Reloj reloj = new Reloj(la_hora); // hilo en java
        reloj.start();
    }


}
