package com.example.poofinal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhoneNumber());

        // Generar el ícono con la inicial del contacto
        Bitmap initialBitmap = getInitialBitmap(contact.getName());
        holder.iconImageView.setImageBitmap(initialBitmap);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    // Genera un Bitmap con la inicial del nombre del contacto
    private Bitmap getInitialBitmap(String name) {
        if (name == null || name.isEmpty()) {
            return null;  // Si no hay nombre, retorna null
        }

        // Obtener la inicial del nombre
        String initial = name.substring(0, 1).toUpperCase();

        // Crear un bitmap con fondo blanco
        int size = 100;  // Tamaño del ícono
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.LTGRAY);  // Color de fondo

        // Configurar el texto (la inicial)
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);

        // Centrar la inicial
        Rect textBounds = new Rect();
        paint.getTextBounds(initial, 0, initial.length(), textBounds);
        int x = (bitmap.getWidth() - textBounds.width()) / 2;
        int y = (bitmap.getHeight() + textBounds.height()) / 2;

        // Dibujar la inicial en el centro del bitmap
        canvas.drawText(initial, x, y, paint);

        return bitmap;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
        ImageView iconImageView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txt_contact_name);
            phoneTextView = itemView.findViewById(R.id.txt_contact_phone);
            iconImageView = itemView.findViewById(R.id.img_contact_icon);
        }
    }
}
