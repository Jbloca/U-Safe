package com.example.poofinal.Modelo;

import java.util.ArrayList;

public class BorraDataModelo {

    private ArrayList<String> opcionesSeleccionadas;

    // Getter y setter para las opciones seleccionadas
    public ArrayList<String> getOpcionesSeleccionadas() {
        return opcionesSeleccionadas;
    }

    public void setOpcionesSeleccionadas(ArrayList<String> opcionesSeleccionadas) {
        this.opcionesSeleccionadas = opcionesSeleccionadas;
    }

    // Método para borrar los archivos
    public void borrarArchivos() {
        // Lógica para borrar archivos (por ejemplo, eliminar archivos almacenados en el dispositivo)
        System.out.println("Archivos eliminados");
    }

    // Método para borrar fotos
    public void borrarFotos() {
        // Lógica para borrar fotos (por ejemplo, eliminar imágenes de la galería)
        System.out.println("Fotos eliminadas");
    }

    // Método para borrar contactos
    public void borrarContactos() {
        // Lógica para borrar contactos (por ejemplo, eliminar contactos de la libreta de direcciones)
        System.out.println("Contactos eliminados");
    }

    // Método para borrar mensajes
    public void borrarMensajes() {
        // Lógica para borrar mensajes (por ejemplo, eliminar mensajes de texto o mensajes en apps de mensajería)
        System.out.println("Mensajes eliminados");
    }

    // Método para borrar los datos seleccionados
    public void borrarDatosSeleccionados() {
        if (opcionesSeleccionadas != null) {
            if (opcionesSeleccionadas.contains("Archivos")) {
                borrarArchivos();
            }
            if (opcionesSeleccionadas.contains("Fotos")) {
                borrarFotos();
            }
            if (opcionesSeleccionadas.contains("Contactos")) {
                borrarContactos();
            }
            if (opcionesSeleccionadas.contains("Mensajes")) {
                borrarMensajes();
            }
        }
    }
}


