package com.example.poofinal.Controlador;

import android.content.Context;
import android.widget.Toast;

import com.example.poofinal.Modelo.GestionPinModelo;
import com.example.poofinal.View.ConfigurarPinView;

public class ConfigurarPin {
    private final ConfigurarPinView configurarPinView;
    private final GestionPinModelo gestionPinModelo;
    private final Context context;

    public ConfigurarPin(ConfigurarPinView view, GestionPinModelo model, Context context) {
        this.configurarPinView = view;
        this.gestionPinModelo = model;
        this.context = context;

        setupListeners();
    }

    private void setupListeners() {
        configurarPinView.getSavePinButton().setOnClickListener(v -> guardarPin());
    }

    private void guardarPin() {
        String enteredPin = configurarPinView.getEnteredPin();
        String confirmedPin = configurarPinView.getConfirmedPin();

        if (enteredPin.isEmpty() || confirmedPin.isEmpty()) {
            Toast.makeText(context, "Por favor, ingresa y confirma tu PIN", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!enteredPin.equals(confirmedPin)) {
            Toast.makeText(context, "Los PINs no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        gestionPinModelo.guardarPin(enteredPin);
        Toast.makeText(context, "PIN guardado exitosamente", Toast.LENGTH_SHORT).show();
    }
}

