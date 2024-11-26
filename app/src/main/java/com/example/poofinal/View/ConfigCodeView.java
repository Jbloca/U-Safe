package com.example.poofinal.View;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.Controlador.ConfigCodeControlador;
import com.example.poofinal.R;

import java.io.File;
import java.util.ArrayList;

public class ConfigCodeView extends AppCompatActivity {

    private ConfigCodeControlador controlador;
    private Switch switch1;
    private ArrayList<String> selectedData;
    private ArrayList<String> selectedApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofi_code_view);

        controlador = new ConfigCodeControlador(this);

        selectedData = getIntent().getStringArrayListExtra("SELECTED_DATA");
        selectedApps = getIntent().getStringArrayListExtra("SELECTED_APPS");

        // Botón para abrir AppsConfiguracionView
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> controlador.abrirAppsConfiguracionView());

        // Botón para abrir BorraDataView
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> controlador.abrirBorraDataView());

        // Botón para retroceder a MenuView
        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> controlador.abrirMenuView());

        // Switch para activar o desactivar el modo oculto
        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> controlador.manejarSwitchModoOculto(isChecked));

        // Botón de emergencia
        Button emergencyButton = findViewById(R.id.btn_panico);
        emergencyButton.setOnClickListener(v -> {
            borrarDatos();
            desinstalarApps();
        });
    }

    // Lógica para borrar los datos seleccionados (archivos, fotos, contactos, mensajes, etc.)
    private void borrarDatos() {
        if (selectedData != null && !selectedData.isEmpty()) {
            if (selectedData.contains("Archivos")) {
                borrarArchivos();
            }
            if (selectedData.contains("Fotos")) {
                borrarFotos();
            }
            if (selectedData.contains("Contactos")) {
                borrarContactos();
            }
            if (selectedData.contains("Mensajes")) {
                borrarMensajes();
            }
        } else {
            mostrarMensaje("No se seleccionaron datos para eliminar.");
        }
    }

    private void borrarArchivos() {
        try {
            File file = new File(this.getFilesDir(), "data.txt");
            if (file.exists()) {
                file.delete();
                mostrarMensaje("Archivos eliminados correctamente.");
            } else {
                mostrarMensaje("No se encontraron archivos para eliminar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al borrar archivos: " + e.getMessage());
        }
    }

    private void borrarFotos() {
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String selection = null;

            int deletedCount = contentResolver.delete(collection, selection, null);
            if (deletedCount > 0) {
                mostrarMensaje("Fotos eliminadas correctamente.");
            } else {
                mostrarMensaje("No se encontraron fotos para eliminar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al borrar fotos: " + e.getMessage());
        }
    }

    private void borrarContactos() {
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            String selection = null;

            int deletedCount = contentResolver.delete(uri, selection, null);
            if (deletedCount > 0) {
                mostrarMensaje("Contactos eliminados correctamente.");
            } else {
                mostrarMensaje("No se encontraron contactos para eliminar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al borrar contactos: " + e.getMessage());
        }
    }

    private void borrarMensajes() {
        try {
            SharedPreferences prefs = getSharedPreferences("messages", MODE_PRIVATE);
            prefs.edit().clear().apply();
            mostrarMensaje("Mensajes eliminados correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al borrar mensajes: " + e.getMessage());
        }
    }

    // Mostrar mensaje en la interfaz
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    // Actualizar el estado del switch
    public void actualizarEstadoSwitch(boolean estado) {
        switch1.setChecked(estado);
    }

    // Método para desinstalar aplicaciones seleccionadas
    private void desinstalarApps() {
        if (selectedApps != null && !selectedApps.isEmpty()) {
            for (String packageName : selectedApps) {
                desinstalarApp(packageName);
            }
        } else {
            mostrarMensaje("No se seleccionaron aplicaciones para desinstalar.");
        }
    }

    private void desinstalarApp(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        startActivity(intent);
    }
}
