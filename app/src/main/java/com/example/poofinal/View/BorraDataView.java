package com.example.poofinal.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.poofinal.R;

public class BorraDataView {

    private View view;
    private Button guardarDatosButton;
    private TextView titleTextView;

    public BorraDataView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_borrar_data_view, null); // Aquí se carga el layout

        // Referencias a los componentes de la interfaz
        titleTextView = view.findViewById(R.id.textView6);
        guardarDatosButton = view.findViewById(R.id.btncambios);
    }

    public View getView() {
        return view;
    }

    // Métodos para actualizar la vista
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setGuardarDatosClickListener(View.OnClickListener listener) {
        guardarDatosButton.setOnClickListener(listener);
    }
}

