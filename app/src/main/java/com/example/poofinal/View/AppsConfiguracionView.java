package com.example.poofinal.View;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poofinal.AppItem;
import com.example.poofinal.Controlador.AppsConfiguracionControlador;
import com.example.poofinal.Modelo.AppsConfiguracionModelo;
import com.example.poofinal.R;

import java.util.ArrayList;
import java.util.List;

public class AppsConfiguracionView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button confirmButton;
    private AppsConfiguracionControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_configuracion_view);

        recyclerView = findViewById(R.id.recyclerView);
        confirmButton = findViewById(R.id.confirm_buttonapp);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar modelo y controlador
        AppsConfiguracionModelo modelo = new AppsConfiguracionModelo();
        controlador = new AppsConfiguracionControlador(modelo, this);

        // Cargar las aplicaciones instaladas
        controlador.cargarAppsList(getPackageManager());

        // Configurar el botón
        confirmButton.setOnClickListener(v -> controlador.manejarSeleccion());
    }

    // Actualiza la vista con la lista de aplicaciones
    public void actualizarVista(List<AppItem> appsList) {
        AppsListAdapter adapter = new AppsListAdapter(appsList);
        recyclerView.setAdapter(adapter);
    }

    // Mostrar un mensaje (Toast)
    public void mostrarToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Adaptador para el RecyclerView
    private class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.ViewHolder> {

        private List<AppItem> appsList;

        public AppsListAdapter(List<AppItem> appsList) {
            this.appsList = appsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(AppsConfiguracionView.this)
                    .inflate(R.layout.item_suggested_app, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            AppItem app = appsList.get(position);
            holder.appName.setText(app.getAppName());
            holder.appIcon.setImageDrawable(app.getAppIcon());
            holder.checkBox.setChecked(app.isSelected());

            // Cambiar el estado de selección
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> app.setSelected(isChecked));
        }

        @Override
        public int getItemCount() {
            return appsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView appName;
            ImageView appIcon;
            CheckBox checkBox;

            public ViewHolder(View itemView) {
                super(itemView);
                appName = itemView.findViewById(R.id.app_name);
                appIcon = itemView.findViewById(R.id.app_icon);
                checkBox = itemView.findViewById(R.id.app_checkboxapp);
            }
        }
    }
}








