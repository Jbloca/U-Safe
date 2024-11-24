package com.example.poofinal.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.Controlador.ConfigCodeControlador;
import com.example.poofinal.R;

public class ConfigCodeView extends AppCompatActivity {

    private ConfigCodeControlador controlador;
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofi_code_view);

        controlador = new ConfigCodeControlador(this);

        // Botón para abrir AppsConfiguracionView
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> controlador.abrirAppsConfiguracionView());

        // Botón para abrir BorraDataView
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> controlador.abrirBorraDataView());

        // Switch para activar o desactivar el modo oculto
        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> controlador.manejarSwitchModoOculto(isChecked));
    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void actualizarEstadoSwitch(boolean estado) {
        switch1.setChecked(estado);
    }
}


