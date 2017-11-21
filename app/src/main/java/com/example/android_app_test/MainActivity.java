package com.example.android_app_test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // R es un recurso de android, es una clase
    // e.g. acceder a una imagen: R.drawable
    // e.g acceder a icono R.mipmap.ic_launcher

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv_hw);
        tv.setText("Cambio de texto");

        // Actividades https://developer.android.com/training/basics/activity-lifecycle/starting.html
        // Ver diagrama para ver como es el ciclo de vida y los onCreate, onStart, etc
        // Un activity solo puede tener un contexto
        Context context = this;
        Test.myToast(context, "onCreate");

    }

    @Override
    protected void onStart() {
        // super llamar al constructor padre para que haga por defecto su comportamiento
        super.onStart();
        Test.myToast(this, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Test.myToast(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Test.myToast(this, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Test.myToast(this, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Test.myToast(this, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Test.myToast(this, "onDestroy");
    }


}
