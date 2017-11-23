package com.example.android_app_test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // R es un recurso de android, es una clase
    // e.g. acceder a una imagen: R.drawable
    // e.g acceder a icono R.mipmap.ic_launcher

    private View btn;
    private final String GREETER = "Hello from the other side!";

    // Se llama cuando se inicia el activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // esta linea de abajo indica el layout xml que se usa
        setContentView(R.layout.activity_main);

        // Forzar y cargar icono en el Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        btn = (Button) findViewById(R.id.buttonMain);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Button clicked!", Toast.LENGTH_LONG).show();
                // Acceder al segundo activity y mandarle un string
                // Creamos explicity intent, en estos se especifica en donde estamos y a donde vamos
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // Pasamos el valor al intent
                intent.putExtra("greeter", GREETER);
                // Lanzar el nuevo activity
                startActivity(intent);
            }
        });

    }


  /*  @Override
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
*/

}
