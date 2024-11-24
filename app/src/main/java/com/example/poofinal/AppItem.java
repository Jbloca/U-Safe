package com.example.poofinal;

import android.graphics.drawable.Drawable;

    public class AppItem {
        private String appName;
        private Drawable appIcon;
        private boolean isSelected;

        public AppItem(String appName, Drawable appIcon) {
            this.appName = appName;
            this.appIcon = appIcon;
            this.isSelected = false; // Inicialmente no seleccionada
        }

        public String getAppName() {
            return appName;
        }

        public Drawable getAppIcon() {
            return appIcon;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            this.isSelected = selected;
        }
    }


