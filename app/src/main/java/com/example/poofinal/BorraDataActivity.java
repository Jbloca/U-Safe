package com.example.poofinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.View.MenuView;

public class BorraDataActivity extends AppCompatActivity {

    private Button guardarDatosButton;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_data_view); // Aquí se carga el layout

        // Referencias a los componentes de la interfaz
        titleTextView = findViewById(R.id.textView6); // Corregido para coincidir con el ID en el XML
        guardarDatosButton = findViewById(R.id.btncambios);

        // Configurar el listener del botón para borrar datos y navegar
        guardarDatosButton.setOnClickListener(v -> {
            borrarDatos();  // Lógica para borrar los datos
            navegarAlMenu(); // Navegar a la siguiente actividad
        });

        // Set de título (puedes cambiarlo si lo necesitas)
        setTitle("Borrar Datos");
    }

    // Método para borrar los datos (Lógica del modelo aquí)
    private void borrarDatos() {
        // Aquí puedes agregar la lógica para borrar datos (como borrar preferencias o datos locales)
        // Ejemplo de borrado:
        // SharedPreferences prefs = getSharedPreferences("MiApp", MODE_PRIVATE);
        // prefs.edit().clear().apply();
    }

    // Método para navegar a la actividad MenuView
    private void navegarAlMenu() {
        Intent intent = new Intent(BorraDataActivity.this, CofiCodeView.class);
        startActivity(intent);
    }

    // Métodos para interactuar con la vista
    public void setTitle(String title) {
        titleTextView.setText(title);
    }
}
