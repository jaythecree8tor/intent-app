package com.example.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;

    EditText enterURL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        enterURL = findViewById(R.id.editTextText);

// Adding Text Change listener
        enterURL.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isUpdating) {
                    return;
                }

                isUpdating = true;

                String text = s.toString();
                String newText = text.replace(" ", "+").replace("+", " ");

                // Calculate the position of the cursor
                int cursorPosition = enterURL.getSelectionEnd();

                enterURL.setText(newText);

                // Restore the cursor position
                if (cursorPosition <= newText.length()) {
                    enterURL.setSelection(cursorPosition);
                } else {
                    enterURL.setSelection(newText.length());
                }

                isUpdating = false;
            }
        });


        //     button ActionListner
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = enterURL.getText().toString();
                String url = "https://www.google.com/search?q=" + searchQuery;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);}
        });
//        Button 2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(i);
            }
        });


    }
}