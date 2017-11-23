package com.example.android_app_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    // Almacenar la informacion de los campos
    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        // Boton para llamadas
        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    // Comprobar version actual de android del dispositivo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // M de marshmallow o versiones mayores
                        // requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);

                        // Comprobar si ha aceptado, no ha aceptado, o nunca se le ha preguntado
                        if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                            // Ha aceptado
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                            if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                                return;
                            startActivity(i);

                        } else {
                            // No ha aceptado/primera vez que se le pregunta

                            if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                // No se le ha preguntado aun, entonces se le pregunta
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            } else {
                                // Ha denegado
                                Toast.makeText(ThirdActivity.this, "Habilitar permiso", Toast.LENGTH_SHORT).show();
                                // Intent para abrir detalles configuracion aplicacion
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                // pasar info para abrir la config de nuestra aplicacion
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                // FLAG_ACTIVITY_NEW_TASK: If set, this activity will become the start of a new task on this history stack.
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                // Remover del historial y de los recientes (Flujo de navegacion entre activities)
                                // FLAG_ACTIVITY_NO_HISTORY: If set, the new activity is not kept in the history stack.
                                // FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS: If set, the new activity is not kept in the list of recently launched activities.
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }

                    } else {
                        // Versiones menores a marshmallow
                        OlderVersions(phoneNumber);
                    }
                } else {
                    Toast.makeText(ThirdActivity.this, "No ingresaste un numero de teléfono", Toast.LENGTH_SHORT).show();
                }
            }

            private void OlderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "Sin permiso para hacer llamadas", Toast.LENGTH_SHORT).show();
                }

            }

        });

        // Boton para web

        imgBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextWeb.getText().toString();
                String email = "kvnzavalza";

                if (url != null && !url.isEmpty()) {
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://" + url));
                    //startActivity(intentWeb);

                    // Ejemplo intent para abrir contactos
//                    Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
//                    startActivity(intentContacts);

                    // Ejemplo intent Email
//                    Intent intentMailTo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+email));
//                    startActivity(intentMailTo);

                    // Ejemplo email completo
//                    Intent intentEmail = new Intent(Intent.ACTION_VIEW, Uri.parse(email));
//                    intentEmail.setType("plain/text");
//                    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Mail's Title");
//                    intentEmail.putExtra(Intent.EXTRA_TEXT, "Hi there, i lo  my form app");
//                    intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {"jose@jose.com", "antonio@gmail.com"});
//                    startActivity(intentEmail);

                    // Telefono 2, sin permisos requeridos
//                    Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:2221026437"));
//                    startActivity(intentPhone);

                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Caso de telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                // Obtener el permiso en el manifest
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    // Comprobar si ha sido aceptado o denegado el permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // Se tiene el permiso en el dispositivo
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // A pesar de que se comprobo arriba los permisos, startActivity requiere llamar a checkSelfPermission
                            // para comprobar
                            return;
                        }
                        startActivity(intentCall);

                    } else {
                        // No se tiene el permiso en el dispositivo
                        Toast.makeText(ThirdActivity.this, "No se tiene el permiso", Toast.LENGTH_SHORT);
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;

        }

    }

    private boolean CheckPermission(String permission) {
        // Checar si se tiene el permiso
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

}
