package com.example.poofinal.Modelo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class ContactosModelo {

    private final ArrayList<String> selectedContactos = new ArrayList<>();

    public ArrayList<String> getSelectedContacts() {
        return selectedContactos;
    }

    public void addContact(String contactName) {
        selectedContactos.add(contactName);
    }

    public void retrieveContactDetails(ContentResolver contentResolver, String contactId) {
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, ContactsContract.Contacts._ID + " = ?", new String[]{contactId}, null);

        if (cursor != null && cursor.moveToFirst()) {
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            addContact(contactName);
            cursor.close();
        }
    }
}

