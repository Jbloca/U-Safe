package com.example.poofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CodigoView extends AppCompatActivity {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String PIN_KEY = "user_pin";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_view);


        EditText editText1 = findViewById(R.id.textView);
        EditText editText2 = findViewById(R.id.textView3);
        EditText editText3 = findViewById(R.id.textView4);
        EditText editText4 = findViewById(R.id.textView5);
        EditText editText5 = findViewById(R.id.textView6);
        EditText editText6 = findViewById(R.id.textView7);


        EditText[] editTexts = {editText1, editText2, editText3, editText4, editText5, editText6};


        for (int i = 0; i < editTexts.length; i++) {
            final int index = i;


            editTexts[i].setOnKeyListener((v, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {

                    if (editTexts[index].getText().toString().isEmpty() && index > 0) {
                        editTexts[index - 1].requestFocus();
                        editTexts[index - 1].setText("");
                    }
                }
                return false;
            });


            editTexts[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.length() == 1 && index < editTexts.length - 1) {
                        editTexts[index + 1].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }


        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {

            StringBuilder enteredPinBuilder = new StringBuilder();
            for (EditText editText : editTexts) {
                enteredPinBuilder.append(editText.getText().toString().trim());
            }

            String enteredPin = enteredPinBuilder.toString();


            SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
            String correctPin = sharedPreferences.getString("user_pin", "");

            if (enteredPin.equals(correctPin)) {
                Toast.makeText(CodigoView.this, "PIN Correcto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CodigoView.this, borrarDataView.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(CodigoView.this, "PIN Incorrecto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


