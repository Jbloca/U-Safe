package com.example.poofinal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class ContactUtils {

    // Método para generar un Bitmap con la inicial del contacto
    public static Bitmap getInitialBitmap(String name) {
        // Obtener la primera letra del nombre del contacto
        String initial = name.length() > 0 ? String.valueOf(name.charAt(0)).toUpperCase() : "";

        // Definir tamaño de la imagen
        int width = 100;
        int height = 100;

        // Crear un Bitmap vacío
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Configurar el fondo y el texto
        canvas.drawColor(Color.GRAY);  // Fondo gris
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(48);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        // Calcular la posición del texto
        Rect bounds = new Rect();
        paint.getTextBounds(initial, 0, initial.length(), bounds);
        float x = (canvas.getWidth() / 2);
        float y = (canvas.getHeight() / 2) - bounds.exactCenterY();

        // Dibujar el texto (la inicial)
        canvas.drawText(initial, x, y, paint);

        return bitmap;
    }
}
