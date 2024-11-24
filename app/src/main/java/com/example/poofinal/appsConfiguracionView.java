package com.example.poofinal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poofinal.R;

import java.util.ArrayList;
import java.util.List;

public class appsConfiguracionView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AppItem> appsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_configuracion_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appsList = new ArrayList<>();

        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        // Filtrar y mostrar solo aplicaciones no del sistema (es decir, descargadas desde la Play Store)
        for (ApplicationInfo appInfo : installedApplications) {
            // Verificamos si no es una aplicación del sistema
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = appInfo.loadLabel(packageManager).toString();
                Drawable appIcon = appInfo.loadIcon(packageManager);

                // Agregar la aplicación a la lista
                appsList.add(new AppItem(appName, appIcon));
            }
        }

        // Crear un adaptador y configurarlo para el RecyclerView
        recyclerView.setAdapter(new AppsListAdapter());
    }

    // Adaptador para el RecyclerView
    private class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(appsConfiguracionView.this).inflate(R.layout.item_suggested_app, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            AppItem appItem = appsList.get(position);
            holder.appName.setText(appItem.getAppName());
            holder.appIcon.setImageDrawable(appItem.getAppIcon());
        }

        @Override
        public int getItemCount() {
            return appsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView appName;
            ImageView appIcon;

            public ViewHolder(View itemView) {
                super(itemView);
                appName = itemView.findViewById(R.id.app_name);
                appIcon = itemView.findViewById(R.id.app_icon);
            }
        }
    }

    // Modelo de datos para cada aplicación
    private class AppItem {

        private String appName;
        private Drawable appIcon;

        public AppItem(String appName, Drawable appIcon) {
            this.appName = appName;
            this.appIcon = appIcon;
        }

        public String getAppName() {
            return appName;
        }

        public Drawable getAppIcon() {
            return appIcon;
        }
    }
}







