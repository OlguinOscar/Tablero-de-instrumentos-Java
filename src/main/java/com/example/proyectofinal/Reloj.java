package com.example.proyectofinal;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.util.Calendar;


public class Reloj extends Thread{

    Label my_label;
    String hour;
    public Reloj(Label text){
        modifier(text);
    }
    @Override
    public void run() {
        while(true){
            modifier(my_label);
        }
    }

    public void modifier(Label label){

        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        if(minutes<10){
            hour = String.valueOf(hours+":"+"0"+minutes+" hrs");
        }
        else{
            hour = String.valueOf(hours+":"+minutes+" hrs");
        }

        Platform.runLater(() -> {
            label.setText(hour);

        });
        my_label = label;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
