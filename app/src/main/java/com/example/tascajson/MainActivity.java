package com.example.tascajson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text1 = findViewById(R.id.text1);

        //Cridem al AsyncTask .execute
        DescarregaJson desc = new DescarregaJson();
        desc.execute(text1);

    }
}