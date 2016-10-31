package br.com.gpslogger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Button buttonParar = (Button) findViewById(R.id.button);
        buttonParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(context, GPSLogger.class);
                context.stopService(serviceIntent);
            }
        });
        Button buttonIniciar = (Button) findViewById(R.id.button2);
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(context, GPSLogger.class);
                context.startService(serviceIntent);
            }
        });
    }
}
