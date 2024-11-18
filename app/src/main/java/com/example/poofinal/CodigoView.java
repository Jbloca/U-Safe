package com.example.poofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CodigoView extends AppCompatActivity {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String PIN_KEY = "user_pin";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_view); // Reemplaza con tu layout correcto

        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            String pin = "123456"; // Simula un PIN correcto para pruebas
            SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
            String storedPin = sharedPreferences.getString("user_pin", "");

            if (pin.equals(storedPin)) {
                Toast.makeText(CodigoView.this, "PIN Correcto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CodigoView.this, confiCodeView.class);
                startActivity(intent);
                finish();  // Cierra la actividad actual
            } else {
                Toast.makeText(CodigoView.this, "PIN Incorrecto", Toast.LENGTH_SHORT).show();
            }
        });
    }

}