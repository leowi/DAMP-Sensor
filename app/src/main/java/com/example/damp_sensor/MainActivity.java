package com.example.damp_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView senGra, senGraX, senGraY, senGraZ;
    TextView magnet, magnetX, magnetY, magnetZ;

    private SensorManager sensorManager1, sensorManager2;
    private Sensor gravitySensor, magnetometro;
    private SensorEventListener sensorEventListener1, sensorEventListener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senGra = (TextView) findViewById(R.id.textView16);
        senGraX = (TextView) findViewById(R.id.textView9);
        senGraY = (TextView) findViewById(R.id.textView10);
        senGraZ = (TextView) findViewById(R.id.textView11);
        magnet = (TextView) findViewById(R.id.textView15);
        magnetX = (TextView) findViewById(R.id.textView12);
        magnetY = (TextView) findViewById(R.id.textView13);
        magnetZ = (TextView) findViewById(R.id.textView14);

        sensorManager1 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gravitySensor = sensorManager1.getDefaultSensor(Sensor.TYPE_GRAVITY);

        sensorManager2 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometro = sensorManager1.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(gravitySensor ==null){
            Toast.makeText(this, "No se dispone del sensor de gravedad", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sensor de gravedad disponible", Toast.LENGTH_SHORT).show();
        }
        if(magnetometro ==null){
            Toast.makeText(this, "No se dispone de magnetometro", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Magnetometro disponible", Toast.LENGTH_SHORT).show();
        }

        sensorEventListener1 =new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x=event.values[0];
                senGraX.setText(Float.toString(x));
                float y=event.values[1];
                senGraY.setText(Float.toString(y));
                float z=event.values[2];
                senGraZ.setText(Float.toString(z));

                if(y>6&&x<3&&x>-3) senGra.setText("Vertical 1");
                else if(y<-6&&x<3&&x>-3) senGra.setText("Vertical 2");
                else if(y<3&&y>-3&&x<-6) senGra.setText("Horizontal 1");
                else if(y<3&&y>-3&&x>6) senGra.setText("Horizontal 2");

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorEventListener2 =new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x=event.values[0];
                magnetX.setText(Float.toString(x));
                float y=event.values[1];
                magnetY.setText(Float.toString(y));
                float z=event.values[2];
                magnetZ.setText(Float.toString(z));

                if(y>-1&&y<5&&x>15) magnet.setText("Vertical 1");
                else if(y>-1&&y<5&&x<-15) magnet.setText("Vertical 2");
                else if(y>16&&x<3&&x>-3) magnet.setText("Horizontal 1");
                else if(y<-16&&x<3&&x>-3) magnet.setText("Horizontal 2");


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        start();
    }
    private void start(){
        sensorManager1.registerListener(sensorEventListener1, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager1.registerListener(sensorEventListener2, magnetometro, SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void stop(){
        sensorManager1.unregisterListener(sensorEventListener1);
        sensorManager1.unregisterListener(sensorEventListener2);
    }
}