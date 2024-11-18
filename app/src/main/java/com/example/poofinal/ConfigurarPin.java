package com.example.poofinal;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigurarPin extends AppCompatActivity {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String PIN_KEY = "user_pin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_pin);

        EditText pinInput = findViewById(R.id.pinInput); // EditText donde el usuario ingresará el PIN
        findViewById(R.id.savePinButton).setOnClickListener(v -> {
            String pin = pinInput.getText().toString();
            if (pin.length() == 6) { // Verificar que el PIN tenga 4 dígitos
                savePin(pin);
            } else {
                Toast.makeText(this, "El PIN debe tener 6 dígitos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void savePin(String pin) {
        // Guarda el PIN en SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PIN_KEY, pin);
        editor.apply(); // Guarda el PIN de forma persistente
        Toast.makeText(this, "PIN guardado", Toast.LENGTH_SHORT).show();
    }
}
