package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.patineando.databinding.ActivityMainBinding;//TODO QUITAR
import com.example.patineando.databinding.ActivityMenuNavigatorDrawerBinding;
import com.example.patineando.databinding.ActivityMenuPrincipalBinding;
import com.google.android.material.navigation.NavigationView;

public class MenuPrincipal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;//TODO quitar
    private ActivityMenuPrincipalBinding bindingMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        bindingMP = ActivityMenuPrincipalBinding.inflate(getLayoutInflater()); //EN el ejemplo creado de cero usan ActivityMainBinding, pero yo no estoy en el main,
                                                                                //He descubirto que puedo crear variables Activity*Nombre de mi actividad*Binding
        setContentView(binding.getRoot());

        DrawerLayout drawer = bindingMP.; //La cosa es, mi aplicación no tiene toolbar, por lo que nose puede referenciar:

        NavigationView navigationView = bindingMP.;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


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