package com.example.praktikum1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextNamee;
    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNamee = findViewById(R.id.editTextNamee);
        textViewDisplay = findViewById(R.id.textViewDisplay);
    }

    public void onBtnClick(View view ) {
        String inputText = editTextNamee.getText().toString();
        textViewDisplay.append(inputText + "/n");
        editTextNamee.setText(""); // Clear EditText after appending text
    }
}