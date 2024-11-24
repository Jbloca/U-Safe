package com.example.poofinal.Controlador;

import android.content.Intent;
import android.content.pm.PackageManager;

import com.example.poofinal.AppItem;
import com.example.poofinal.View.ConfigCodeView;
import com.example.poofinal.Modelo.AppsConfiguracionModelo;
import com.example.poofinal.View.AppsConfiguracionView;

import java.util.ArrayList;
import java.util.List;

public class AppsConfiguracionControlador {

    private AppsConfiguracionModelo modelo;
    private AppsConfiguracionView vista;

    public AppsConfiguracionControlador(AppsConfiguracionModelo modelo, AppsConfiguracionView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    // Cargar aplicaciones desde el modelo y actualizar la vista
    public void cargarAppsList(PackageManager packageManager) {
        modelo.loadAppsList(packageManager);
        vista.actualizarVista(modelo.getAppsList());
    }

    // Manejar la selección de las aplicaciones
    public void manejarSeleccion() {
        List<AppItem> selectedApps = new ArrayList<>();
        for (AppItem app : modelo.getAppsList()) {
            if (app.isSelected()) {
                selectedApps.add(app);
            }
        }

        // Llamar a la vista para mostrar el resultado
        if (!selectedApps.isEmpty()) {
            vista.mostrarToast("Seleccionaste " + selectedApps.size() + " aplicaciones");

            Intent intent= new Intent(vista, ConfigCodeView.class);
            vista.startActivity(intent);
        } else {
            vista.mostrarToast("No seleccionaste ninguna aplicación");
        }

    }
}


