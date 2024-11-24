package com.example.poofinal.View;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.R;

public class ConfigurarPinView extends AppCompatActivity {
    private EditText pinInputField;
    private EditText confirmPinField;
    private Button savePinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_pin);

        pinInputField = findViewById(R.id.pinInput); // Campo para ingresar el PIN
        confirmPinField = findViewById(R.id.pinInput2); // Campo para confirmar el PIN
        savePinButton = findViewById(R.id.savePinButton); // Botón para guardar el PIN
    }

    public String getEnteredPin() {
        return pinInputField.getText().toString();
    }

    public String getConfirmedPin() {
        return confirmPinField.getText().toString();
    }

    public Button getSavePinButton() {
        return savePinButton;
    }
}
