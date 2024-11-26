package com.example.poofinal.Modelo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.poofinal.AppItem;
import com.example.poofinal.Controlador.AppsConfiguracionControlador;
import com.example.poofinal.View.AppsConfiguracionView;

import java.util.ArrayList;
import java.util.List;

public class AppsConfiguracionModelo {
    private List<AppItem> appsList;

    public AppsConfiguracionModelo() {
        appsList = new ArrayList<>();
    }

    // Obtener la lista de aplicaciones
    public List<AppItem> getAppsList() {
        return appsList;
    }

    // Cargar las aplicaciones installs
    public void loadAppsList(PackageManager packageManager) {
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo appInfo : installedApplications) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = appInfo.loadLabel(packageManager).toString();
                String packageName = appInfo.packageName;
                Drawable appIcon = appInfo.loadIcon(packageManager);
                appsList.add(new AppItem(appName, appIcon, packageName));
            }
        }
    }
}

