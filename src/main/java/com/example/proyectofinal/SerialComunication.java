package com.example.proyectofinal;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class SerialComunication extends Thread{

    Label aux_velocidad;
    Label aux_temperatura;
    ImageView aux_derecha;
    ImageView aux_izquierda;
    ImageView aux_aceite;
    ImageView aux_luces;
    ImageView aux_battery;
    Image flecha_izquierda_negra = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/flecha_verde_negra.jpg");
    Image flecha_derecha_negra = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/flecha_negra_derecha.jpg");
    Image flecha_izquierda_verde = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/flecha_verde_izquierda.jpg");
    Image flecha_derecha_verde = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/flecha_verde_derecha.png");

    Image bateria = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/bateria.jpg");
    Image bateria_negra = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/bateria_negra.jpg");
    Image aceite_negro = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/nivel_aceite_negro.jpg");
    Image aceite_rojo = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/nivel_aceite_rojo.jpg");
    Image luces_negro = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/luces_altas_negro.png");
    Image luces_azul = new Image("C:/Users/oscar/IdeaProjects/proyectofinal/target/classes/imagenes/luces_altas_azul.png");

    public SerialComunication(Label label, ImageView izquierda, ImageView derecha, Label temp, ImageView aceite,ImageView luces,ImageView battery){
        aux_velocidad = label;
        aux_izquierda = izquierda;
        aux_derecha = derecha;
        aux_temperatura = temp;
        aux_aceite = aceite;
        aux_luces = luces;
        aux_battery = battery;
    }
    @Override
    public void run() {

        SerialPort serialPort = SerialPort.getCommPort("COM5"); // Reemplaza "COM1" con el nombre de tu puerto serial
        serialPort.setComPortParameters(9600,8,1,0);
        if (serialPort.openPort()) {
            System.out.println("Puerto abierto correctamente.");
        } else {
            System.err.println("Error al abrir el puerto.");
            return;
        }

        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    byte[] newData = new byte[serialPort.bytesAvailable()]; // aqui jala datos de arduino, del serialprint
                    serialPort.readBytes(newData, newData.length);
                    // Procesa los datos recibidos
                    String receivedData = new String(newData);
                    String[] datos = receivedData.split(",");
                    Platform.runLater(() -> {
                        aux_velocidad.setText(datos[0]+" km/h");

                    });
                    if(Objects.equals(datos[1], "0") || Objects.equals(datos[2], "0")){
                        if(Objects.equals(datos[1], "0")){
                            Platform.runLater(() -> {
                                aux_izquierda.setImage(flecha_izquierda_verde);
                            });
                        }
                        else{
                            Platform.runLater(() -> {
                                aux_derecha.setImage(flecha_derecha_verde);
                            });
                        }
                    }
                    else{
                        Platform.runLater(() -> {
                            aux_derecha.setImage(flecha_derecha_negra);
                        });
                        Platform.runLater(() -> {
                            aux_izquierda.setImage(flecha_izquierda_negra);
                        });
                    }
                    Platform.runLater(() -> {
                        aux_temperatura.setText(datos[3]+" °C");
                    });

                    if(Integer.parseInt(datos[4]) <= 300){
                        aux_aceite.setImage(aceite_rojo);
                    }
                    else{
                        aux_aceite.setImage(aceite_negro);
                    }

                    if(Integer.parseInt(datos[5]) <= 200){
                        aux_luces.setImage(luces_azul);
                    }
                    else{
                        aux_luces.setImage(luces_negro);
                    }

                    if(Float.parseFloat(datos[6]) <= 1.38){
                        aux_battery.setImage(bateria);
                    }
                    else{
                        aux_battery.setImage(bateria_negra);
                    }
                }
            }
        });

        System.out.println("Presiona Enter para finalizar.");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
