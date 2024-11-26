package com.example.poofinal.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.Controlador.BorraDataControlador;
import com.example.poofinal.Modelo.BorraDataModelo;
import com.example.poofinal.R;

import java.util.ArrayList;

public class BorraDataView extends AppCompatActivity {

    private CheckBox chkContactos, chkArchivos, chkFotos, chkMensajes;
    private Button btnGuardarCambios;
    private BorraDataControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_data_view);

        // Crear una instancia del modelo BorraDataModelo
        BorraDataModelo modelo = new BorraDataModelo();

        // Ahora podemos pasar tanto la vista como el modelo al controlador
        controlador = new BorraDataControlador(this, modelo);

        // Iniciar los CheckBox
        chkContactos = findViewById(R.id.chk_contactos);
        chkArchivos = findViewById(R.id.chk_archivos);
        chkFotos = findViewById(R.id.chk_fotos);
        chkMensajes = findViewById(R.id.chk_mensajes);

        // Botón para guardar cambios
        btnGuardarCambios = findViewById(R.id.btncambios);
        btnGuardarCambios.setOnClickListener(v -> {
            ArrayList<String> selectedData = new ArrayList<>();
            if (chkContactos.isChecked()) {
                selectedData.add("Contactos");
            }
            if (chkArchivos.isChecked()) {
                selectedData.add("Archivos");
            }
            if (chkFotos.isChecked()) {
                selectedData.add("Fotos");
            }
            if (chkMensajes.isChecked()) {
                selectedData.add("Mensajes");
            }

            // Usar el controlador para guardar las opciones seleccionadas
            controlador.guardarOpcionesSeleccionadas(selectedData);

            // Pasar la lista de datos seleccionados a ConfigCodeView
            Intent intent = new Intent(BorraDataView.this, ConfigCodeView.class);
            intent.putStringArrayListExtra("SELECTED_DATA", selectedData);
            startActivity(intent);
        });
    }
}






