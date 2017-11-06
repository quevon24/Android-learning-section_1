package com.example.android_app_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv_hw);
        tv.setText("Cambio de texto");
    }

    // R es un recurso de android, es una clase
    // e.g. acceder a una imagen: R.drawable
    // e.g acceder a icono R.mipmap.ic_launcher
}
