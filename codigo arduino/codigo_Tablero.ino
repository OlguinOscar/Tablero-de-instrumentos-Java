#include <DHT.h>
#define DHTTYPE DHT11
DHT dht(A2, DHTTYPE);
// Variables
int analogValor = 0;
float voltaje = 0;
void setup() {
Serial.begin(9600);
pinMode(2, INPUT_PULLUP);
pinMode(3, INPUT_PULLUP);
pinMode(8, OUTPUT);
pinMode(9, OUTPUT);
pinMode(10, OUTPUT);
}
void loop() {
int velocidad = analogRead(A1);
velocidad = map(velocidad,0,1023,0,220);
int direccional_izquierda = digitalRead(2);
int direccional_derecha = digitalRead(3);
float temperature = dht.readTemperature();
int nivel_aceite = analogRead(A3);
int ldrValue = analogRead(A4);
nivel_aceite = map(nivel_aceite,1020,0,0,1020);
analogValor = analogRead(A0);
voltaje = 0.0048 * analogValor;
// Dependiendo del voltaje mostramos un LED u otro
if (voltaje >= 1.6)
{
digitalWrite(8, HIGH);
digitalWrite(9, LOW);
digitalWrite(10, LOW);
}
else if (voltaje < 1.6 && voltaje > 1.4)
{
digitalWrite(9, HIGH);
digitalWrite(8, LOW);
digitalWrite(10, LOW);
}
else if (voltaje < 1.4 && voltaje > 0.3)
{
digitalWrite(10, HIGH);
digitalWrite(8, LOW);
digitalWrite(9, LOW);
}
Serial.print(velocidad);
Serial.print(",");
Serial.print(direccional_izquierda);
Serial.print(",");
Serial.print(direccional_derecha);
Serial.print(",");
Serial.print(temperature);
Serial.print(",");
Serial.print(nivel_aceite);
Serial.print(",");
Serial.print(ldrValue);
Serial.print(",");
Serial.print(voltaje);
Serial.println(",");
delay(1000);
}
