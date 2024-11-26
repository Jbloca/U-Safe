package com.example.poofinal;

import android.graphics.drawable.Drawable;

public class AppItem {
    private String appName;
    private Drawable appIcon;
    private String packageName;
    private boolean isSelected;

    public AppItem(String appName, Drawable appIcon, String packageName) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.packageName = packageName;
        this.isSelected = false;
    }

    // Getters y Setters
    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}


