package com.example.poofinal.Controlador;

import android.content.Intent;

import com.example.poofinal.BorraDataActivity;
import com.example.poofinal.Modelo.ConfigCodeModelo;
import com.example.poofinal.View.AppsConfiguracionView;
import com.example.poofinal.View.ConfigCodeView;
import com.example.poofinal.View.MenuView;

public class ConfigCodeControlador {

    private final ConfigCodeView vista;
    private final ConfigCodeModelo modelo;

    public ConfigCodeControlador(ConfigCodeView vista) {
        this.vista = vista;
        this.modelo = new ConfigCodeModelo(vista);
    }

    public void abrirAppsConfiguracionView() {
        Intent intent = new Intent(vista, AppsConfiguracionView.class);
        vista.startActivity(intent);
    }

    public void abrirBorraDataView() {
        Intent intent = new Intent(vista, BorraDataActivity.class);
        vista.startActivity(intent);
    }

    public void abrirMenuView() {
        Intent intent = new Intent(vista, MenuView.class);
        vista.startActivity(intent);
    }

    public void manejarSwitchModoOculto(boolean activar) {
        if (activar) {
            if (modelo.tienePermisoNotificaciones()) {
                modelo.cambiarModoSonido(true);
            } else {
                modelo.solicitarPermisoNotificaciones();
                vista.actualizarEstadoSwitch(false);
            }
        } else {
            modelo.cambiarModoSonido(false);
        }
    }
}
