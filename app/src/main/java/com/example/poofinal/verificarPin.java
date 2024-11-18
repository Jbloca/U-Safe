package com.example.poofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class verificarPin extends AppCompatActivity {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String PIN_KEY = "user_pin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_pin);

        EditText pinInput = findViewById(R.id.pinInput); // EditText donde el usuario ingresa el PIN
        findViewById(R.id.submitPinButton).setOnClickListener(v -> {
            String enteredPin = pinInput.getText().toString();
            if (verifyPin(enteredPin)) {
                // PIN correcto, accede a la aplicación
                Toast.makeText(this, "Acceso permitido", Toast.LENGTH_SHORT).show();
                // Redirige a la actividad principal o la siguiente actividad
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                // PIN incorrecto
                Toast.makeText(this, "PIN incorrecto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean verifyPin(String enteredPin) {
        // Recupera el PIN almacenado de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String storedPin = sharedPreferences.getString(PIN_KEY, null);
        return enteredPin.equals(storedPin); // Compara el PIN ingresado con el almacenado
    }
}
