package com.example.poofinal.Controlador;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.poofinal.Modelo.ContactosModelo;
import com.example.poofinal.View.ShareContactosView;

public class ShareContactos {

    private final ContactosModelo contactModel;
    private final ShareContactosView shareContactosView;

    public ShareContactos(ShareContactosView shareContactosView) {
        this.contactModel = new ContactosModelo();
        this.shareContactosView = shareContactosView;
    }

    public void openContactsApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        shareContactosView.startActivityForResult(intent, ShareContactosView.REQUEST_CODE_CONTACTS);
    }

    public void handleContactSelection(ContentResolver contentResolver, String contactId) {
        contactModel.retrieveContactDetails(contentResolver, contactId);
        shareContactosView.updateSelectedContacts(contactModel.getSelectedContacts());
    }

    public void checkLocationPermission(Context context) {
        if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            // Cambié el método a 'initializeMap()', que es público
            shareContactosView.setupMap();
        } else {
            Toast.makeText(context, "Location permission required", Toast.LENGTH_SHORT).show();
        }
    }
}

