package com.example.patineando;




import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.patineando.FragmentsND.FragmentGestionarCursos;
import com.example.patineando.FragmentsND.FragmentGestionarNoticias;
import com.example.patineando.FragmentsND.FragmentGestionarPermisos;
import com.example.patineando.FragmentsND.FragmentGestionarRutas;
import com.example.patineando.FragmentsND.FragmentGestionarUsuarios;
import com.example.patineando.FragmentsND.FragmentMenuPrincipal;
import com.example.patineando.FragmentsND.FragmentOpciones;
import com.example.patineando.databinding.ActivityMainBinding;
import com.example.patineando.databinding.ActivityMenuPrincipalBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import android.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.patineando.databinding.ActivityMenuNavigatorDrawerBinding;

public class MenuNavigatorDrawer extends AppCompatActivity {
//TODo Esto creo que lo puedo borrar, ya que el .java del navigator es el menuPrincipal.java

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuNavigatorDrawerBinding binding;
    String tipoUsuario2 = "Administracion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuNavigatorDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       //En teoria esto es lo de la barra, pero yo no tengo barra:

        setSupportActionBar(binding.appBarMenuNavigatorDrawer.toolbar);
        DrawerLayout drawer =findViewById(R.id.menu_drawer_layout);
        NavigationView navigationView = findViewById(R.id.vista_navegacion_ND);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //TODO: NOTA: En teoria esto es lo que va  a hacer que el menú sea dinámico en funcion del tipo de usuario.
        switch (tipoUsuario2){
            case "Profesor":
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.menuAlumnoND, R.id.menuProfesorND)
                        .setDrawerLayout(drawer)
                        .build();

                break;
            case "Administracion":
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.menuAlumnoND, R.id.menuProfesorND, R.id.menuAdministracionND)
                        .setDrawerLayout(drawer)
                        .build();
                break;

            default:
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.menuAlumnoND)
                        .setDrawerLayout(drawer)
                        .build();
                break;
        }
        //Asignación de la visibilidad en función del tipo de usuario: La generiaca es la de los alumnos:
       // https://stackoverflow.com/questions/49850726/how-to-set-the-visibility-of-menu-item-while-respective-fragment-is-open
        Menu menu = navigationView.getMenu();
        if(tipoUsuario2.equals("Profesor")){
            MenuItem menuProfe = menu.findItem(R.id.menuProfesorND);
            menuProfe.setVisible(true);
        }else if(tipoUsuario2.equals("Administracion")){
            MenuItem menuProfe = menu.findItem(R.id.menuProfesorND);
            menuProfe.setVisible(true);
            MenuItem menuAdmin = menu.findItem(R.id.menuAdministracionND);
            menuAdmin.setVisible(true);
        }

        NavController navController = Navigation.findNavController(this, R.id.nav_menu_principal_fragment_content_menu_navigator_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




        //Versionando el tutorial de youtube: https://www.youtube.com/watch?v=3SHLg2isKi4
        //Funcionalidad de los botones del navigator Medrawer:
        NavigationView vistaNavegacion = findViewById(R.id.vista_navegacion_ND);
        vistaNavegacion.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmentoND;
                FragmentManager fragmentManager = getFragmentManager();
                //Se crea una nueva transacción:
                FragmentTransaction transaccion = fragmentManager.beginTransaction();
                switch(item.getItemId()){
                    case (R.id.nav_menu_principal):
                        //FragmentMenuPrincipal fragmentMenuPrincipal = new FragmentMenuPrincipal(); con esto tambien tira
                   //     fragmentoND = new FragmentMenuPrincipal(); //Nota: el fragment tiene que importar import android.app.Fragment;, no la de androidX
                     //   transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND); //TODO nota, no se si es mejor usar transaccion replace o transaccion.add
                        //transaccion.add(R.id.contenedor_fragments_ND,fragmentoND);
                        //Validacion del cambio:
                        transaccion.commit();
                        break;
                    case (R.id.nav_opciones):
                        fragmentoND = new FragmentOpciones();
                        transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
                        transaccion.commit();
                        break;
                    case (R.id.nav_admin_cursos):
                        fragmentoND = new FragmentGestionarCursos();
                        transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
                        transaccion.commit();
                        break;
                    case (R.id.nav_admin_noticias):
                        fragmentoND = new FragmentGestionarNoticias();
                        transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
                        transaccion.commit();
                        break;
                    case (R.id.nav_admin_rutas):
                        fragmentoND = new FragmentGestionarRutas();
                        transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
                        transaccion.commit();
                        break;
                    case (R.id.nav_admin_permisos):
                        fragmentoND = new FragmentGestionarPermisos();
                        transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
                        transaccion.commit();
                        break;
                    case (R.id.nav_admin_usuarios):
                        fragmentoND = new FragmentGestionarUsuarios();
                        transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
                        transaccion.commit();
                        break;
                }

                DrawerLayout drawerLayout = findViewById(R.id.menu_drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });




    }//fin onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigator_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_menu_principal_fragment_content_menu_navigator_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}




