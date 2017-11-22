package com.example.android_app_test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // R es un recurso de android, es una clase
    // e.g. acceder a una imagen: R.drawable
    // e.g acceder a icono R.mipmap.ic_launcher

    private TextView tv;

    private Button btn;

    // Se llama cuando se inicia el activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // esta linea de abajo indica el layout xml que se usa
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv_hw);
        tv.setText("Cambio de texto");

        // Actividades https://developer.android.com/training/basics/activity-lifecycle/starting.html
        // Un activity es la vista de la aplicacion
        // Ver diagrama para ver como es el ciclo de vida y los onCreate, onStart, etc
        // Un activity solo puede tener un contexto
        Context context = this;
        Test.myToast(context, "onCreate");

        // Metodo 1
        // opcion mas recomendable que usar android:onClick="miMetodo" en el xml
/*        btn = (Button) findViewById(R.id.buttonMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button clicked!", Toast.LENGTH_LONG).show();
            }
        });*/

        // parte de metodo 3
        btn = (Button) findViewById(R.id.buttonMain);
        // this es el que se esta implementando en implements View.OnClickListener
        btn.setOnClickListener(this);


    }

    // para metodo 3 para boton
    @Override
    public void onClick(View view){
        Toast.makeText(MainActivity.this, "Button clicked!", Toast.LENGTH_LONG).show();
    }

    // Metodo 2 para boton para llamar desde el xml
    // MainActivity.this es el contexto de mainactivity
    public void miMetodo(View v) {
        int a = 4;
        Toast.makeText(MainActivity.this, "valor: " + a, Toast.LENGTH_LONG).show();
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
