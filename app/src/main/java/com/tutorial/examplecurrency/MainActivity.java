package com.tutorial.examplecurrency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.txt_number);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);

        editText.addTextChangedListener(new MoneyTextWatcher(editText));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cleanString = editText.getText().toString().replaceAll("[.]", "");
                Double parseToDouble = Double.parseDouble(cleanString);
                textView.setText("Uang yang anda masukan sebesar \nRp. " + parseToDouble);
            }
        });
    }
}