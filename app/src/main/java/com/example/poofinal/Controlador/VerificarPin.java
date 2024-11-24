package com.example.poofinal.Controlador;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.Modelo.ConfigurarPinModelo;
import com.example.poofinal.R;

public class VerificarPin extends AppCompatActivity {

    private ConfigurarPinModelo pinManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_pin);

        // Crear instancia del modelo pasando el contexto actual
        pinManager = new ConfigurarPinModelo(this);  // Aquí se pasa el contexto solo al constructor.

       /* EditText pinInput = findViewById(R.id.pinInput); // EditText donde el usuario ingresa el PIN
        findViewById(R.id.submitPinButton).setOnClickListener(v -> {
            String enteredPin = pinInput.getText().toString().trim();

            // Verificar el PIN usando el modelo directamente
            if (pinManager.verificarPin(enteredPin)) {  // Sin pasar contexto
                mostrarMensaje("Acceso permitido");

                // Redirige a la vista principal
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                mostrarMensaje("PIN incorrecto");
            }
        });*/
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}


