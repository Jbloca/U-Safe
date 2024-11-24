package com.example.poofinal.View;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.R;

public class ConfigurarPinView {
    private final EditText pinInputField;
    private final EditText confirmPinField;
    private final Button savePinButton;

    public ConfigurarPinView(Context context) {
        pinInputField = ((AppCompatActivity) context).findViewById(R.id.pinInput); // Campo para ingresar el PIN
        confirmPinField = ((AppCompatActivity) context).findViewById(R.id.pinInput2); // Campo para confirmar el PIN
        savePinButton = ((AppCompatActivity) context).findViewById(R.id.savePinButton); // Botón para guardar el PIN
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
