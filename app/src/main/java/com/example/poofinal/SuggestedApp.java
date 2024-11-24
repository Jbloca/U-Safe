package com.example.poofinal;

import android.graphics.drawable.Drawable;

public class SuggestedApp {

    private final String name;
    private final String description;
    private final Drawable icon;

    public SuggestedApp(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
        this.description = "Descripción no disponible"; // Puedes poner una descripción predeterminada o nula
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Drawable getIcon() {
        return icon;
    }
}


