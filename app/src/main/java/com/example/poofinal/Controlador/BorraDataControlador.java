package com.example.poofinal.Controlador;

import com.example.poofinal.Modelo.BorraDataModelo;
import com.example.poofinal.View.ConfigCodeView;
import com.example.poofinal.View.BorraDataView;

import java.util.ArrayList;

public class BorraDataControlador {

    private final BorraDataView borraDataView;
    private final ConfigCodeView configCodeView;
    private final BorraDataModelo borraDataModelo;

    // Constructor que recibe BorraDataView y BorraDataModelo
    public BorraDataControlador(BorraDataView borraDataView, BorraDataModelo borraDataModelo) {
        this.borraDataView = borraDataView;
        this.borraDataModelo = borraDataModelo;
        this.configCodeView = null; // No es necesario en BorraDataView
    }

    // Constructor para ConfigCodeView, que no tiene la vista BorraDataView
    public BorraDataControlador(ConfigCodeView configCodeView, BorraDataModelo borraDataModelo) {
        this.configCodeView = configCodeView;
        this.borraDataModelo = borraDataModelo;
        this.borraDataView = null; // No es necesario en ConfigCodeView
    }

    // Guardar las opciones seleccionadas
    public void guardarOpcionesSeleccionadas(ArrayList<String> opcionesSeleccionadas) {
        // Pasar las opciones seleccionadas al modelo para su procesamiento
        borraDataModelo.setOpcionesSeleccionadas(opcionesSeleccionadas);

        // Llamar al método para borrar los datos basados en las opciones seleccionadas
        borrarDatos();
    }

    // Lógica para borrar los datos seleccionados
    private void borrarDatos() {
        if (borraDataModelo.getOpcionesSeleccionadas().contains("Archivos")) {
            borraDataModelo.borrarArchivos(); // Borrar archivos
        }
        if (borraDataModelo.getOpcionesSeleccionadas().contains("Fotos")) {
            borraDataModelo.borrarFotos();   // Borrar fotos
        }
        if (borraDataModelo.getOpcionesSeleccionadas().contains("Contactos")) {
            borraDataModelo.borrarContactos(); // Borrar contactos
        }
        if (borraDataModelo.getOpcionesSeleccionadas().contains("Mensajes")) {
            borraDataModelo.borrarMensajes(); // Borrar mensajes
        }

        // Mostrar mensaje de éxito
        if (configCodeView != null) {
            configCodeView.mostrarMensaje("Datos eliminados correctamente.");
        }
    }
}
