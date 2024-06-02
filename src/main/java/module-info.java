module com.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fazecast.jSerialComm;
    requires java.logging;
    requires jssc;


    opens com.example.proyectofinal to javafx.fxml;
    exports com.example.proyectofinal;
}