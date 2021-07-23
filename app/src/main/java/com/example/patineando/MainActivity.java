package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    //ActionBar actionbar = getSupportActionBar(); //TODO ver si hay alguna forma mejor de hacer esto. Casca
    //Recurso: https://stackoverflow.com/questions/26492522/how-do-i-remove-the-title-bar-from-my-app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //actionbar.hide(); //TODO casca


        //Llamada al método que devuelve el SHA-1 de la aplicación:
        printHashKey(this);
    }//Fin onCreate()------------


    //Método para poder ir al menú principal:

    public  void pruebaMenuPrincipal(View view){
        Intent intent = new Intent(this, MenuPrincipal.class);

        startActivity(intent);
    }//Fin método pruebaMenuPrincipal(View view)


    //Método para obtener el SHA-1:
    public  void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                System.out.println("printHashKey() Hash Key: " + hashKey);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }//Fin método obtención SHA-1
}