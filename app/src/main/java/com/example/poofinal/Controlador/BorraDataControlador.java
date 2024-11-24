package com.example.poofinal.Controlador;

import android.content.Intent;
import android.view.View;

import com.example.poofinal.BorraDataActivity;
import com.example.poofinal.CofiCodeView;
import com.example.poofinal.Modelo.BorraDataModelo;
import com.example.poofinal.View.BorraDataView;

public class BorraDataControlador {

    private final BorraDataView borraDataView;
    private final BorraDataModelo borraDataModelo;
    private final BorraDataActivity borraDataActivity;

    public BorraDataControlador(BorraDataView borraDataView, BorraDataModelo borraDataModelo, BorraDataActivity borraDataActivity) {
        this.borraDataView = borraDataView;
        this.borraDataModelo = borraDataModelo;
        this.borraDataActivity = borraDataActivity;

        // Configurar el comportamiento del botón "Guardar Datos"
        this.borraDataView.setGuardarDatosClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarDatos();  // Lógica de borrado de datos
                navegarAlMenu(); // Navegar a la siguiente actividad
            }
        });
    }

    // Lógica para borrar los datos
    private void borrarDatos() {
        borraDataModelo.borrarDatos();
    }

    // Navegar a la siguiente actividad
    private void navegarAlMenu() {
        Intent intent = new Intent(borraDataActivity, CofiCodeView.class);
        borraDataActivity.startActivity(intent);
    }

    // Método para inicializar la vista (se puede usar si hay más inicializaciones)
    public void iniciar() {
        borraDataView.setTitle("Borrar Datos");
    }
}

