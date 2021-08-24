package com.example.patineando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.patineando.databinding.ActivityMainBinding;
import com.example.patineando.databinding.ActivityMenuNavigatorDrawerBinding;
import com.example.patineando.databinding.ActivityMenuPrincipalBinding;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MenuPrincipal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;//TODO quitar
    private ActivityMenuPrincipalBinding bindingMP;

    private String tipoUsuario = "Alumno";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);



    }//Fin onCreate

    //-----Función para acceder a InformacionClimatica.java

    public void verTiempo(View view){
        Intent intent = new Intent(this, InformacionClimatica.class);
        startActivity(intent);
    }
    public void irAImagenCursosImpartidos(View view){
        Intent intent = new Intent(this, ImagenCursosImpartidos.class);
        startActivity(intent);
    }
    //-----Función para acceder a MisCursos.java

    public void irAMisCursos(View view){
        Intent intent = new Intent(this, MisCursos.class);
        startActivity(intent);
    }
    //-----Función para acceder a MiPerfil.java

    public void irAMiPerfil(View view){
        Intent intent = new Intent(this, MiPerfil.class);
        startActivity(intent);
    }


}