package com.ml.zszabo.primera;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ml.zszabo.primera.Model.Conversor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Conversor conversor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conversor = new Conversor();

        Button button = findViewById(R.id.button);

        TextInputEditText textInputEditText = findViewById(R.id.textinputedittext);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                updateMiles();
            }
        };

        button.setOnClickListener(onClickListener);

        textInputEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    closeKeyboard();
                    updateMiles();
                    return true;
                }
                return false;
            }
        });
    }

    private void closeKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    private void updateMiles() {
        TextInputEditText textInputEditText = findViewById(R.id.textinputedittext);
        Double miles = 0.0;
        TextView kms = findViewById(R.id.text2);
        try {
            conversor.setMiles(Double.valueOf(textInputEditText.getText().toString()));
            DecimalFormat formatter = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(new Locale("es")));
            formatter.setRoundingMode( RoundingMode.DOWN );
            kms.setText(String.format(getString(R.string._kilometers), formatter.format(conversor.getKilometers())));
        } catch (Exception e) {
            kms.setText("");
        }
    }
}
