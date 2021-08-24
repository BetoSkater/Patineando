package com.example.patineando;




import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.patineando.FragmentsND.FragmentGestionarCursos;
import com.example.patineando.FragmentsND.FragmentGestionarEscuela;
import com.example.patineando.FragmentsND.FragmentGestionarNoticias;
import com.example.patineando.FragmentsND.FragmentGestionarPermisos;
import com.example.patineando.FragmentsND.FragmentGestionarRutas;
import com.example.patineando.FragmentsND.FragmentMenuPrincipal;
import com.example.patineando.FragmentsND.FragmentOpciones;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.patineando.databinding.ActivityMenuNavigatorDrawerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

/*public enum TipoUsurio
{
    Administracion, Profesorado, Alumnado
}*/

public class MenuNavigatorDrawer extends AppCompatActivity {
//TODo Esto creo que lo puedo borrar, ya que el .java del navigator es el menuPrincipal.java

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuNavigatorDrawerBinding binding;
    private FirebaseAuth mAuth;


    String tipoUsuario2 =  "Alumnado"; //TODO cambiar a Alumno cuando funcione el hacer dinámico el menú.

    String tipoUsuarioApp = "Alumnado"; //TODO lo mismo aqui


    private ImageView imagenNavigatorDrawer;
    private TextView nombreNavigatorDrawer, correoNavigatorDrawer;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        //Instancia de autenticacion:
        mAuth = FirebaseAuth.getInstance();
        tipoUsuario2 = obtencionTipoUsuarioND();


        binding = ActivityMenuNavigatorDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       //En teoria esto es lo de la barra, pero yo no tengo barra:

        setSupportActionBar(binding.appBarMenuNavigatorDrawer.toolbar);
        DrawerLayout drawer =findViewById(R.id.menu_drawer_layout);
         navigationView = findViewById(R.id.vista_navegacion_ND);

        //La asignacion de variables-controladores del ND se tienen que poner despues de la definicion del navigator:

        //https://stackoverflow.com/questions/33560219/in-android-how-to-set-navigation-drawer-header-image-and-name-programmatically-i
        imagenNavigatorDrawer = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imgUsuarioND);
        nombreNavigatorDrawer = (TextView) navigationView.getHeaderView(0).findViewById(R.id.lblNombreND);
        correoNavigatorDrawer = (TextView) navigationView.getHeaderView(0).findViewById(R.id.lblCorreoND);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //TODO: NOTA: En teoria esto es lo que va  a hacer que el menú sea dinámico en funcion del tipo de usuario.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menuAlumnoND, R.id.menuProfesorND, R.id.menuAdministracionND)
                .setDrawerLayout(drawer)
                .build();

        switch (tipoUsuario2){
            case "Profesorado":
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.menuAlumnoND, R.id.menuProfesorND)
                        .setDrawerLayout(drawer)
                        .build();

                break;
            case "Administración":
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
        if(tipoUsuario2.equals("Profesorado")){
            MenuItem menuProfe = menu.findItem(R.id.menuProfesorND);
            menuProfe.setVisible(true);
        }else if(tipoUsuario2.equals("Administración")){
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
                Fragment fragmentoND = new FragmentOpciones();;
                //Se crea una nueva transacción:
                FragmentTransaction transaccion = getFragmentManager().beginTransaction();
                switch(item.getItemId()){
                    case (R.id.nav_menu_principal):
                        //FragmentMenuPrincipal fragmentMenuPrincipal = new FragmentMenuPrincipal(); con esto tambien tira
                   //     fragmentoND = new FragmentMenuPrincipal(); //Nota: el fragment tiene que importar import android.app.Fragment;, no la de androidX
                     //   transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND); //TODO nota, no se si es mejor usar transaccion replace o transaccion.add
                        //transaccion.add(R.id.contenedor_fragments_ND,fragmentoND);
                        //Validacion del cambio:
                        //transaccion.commit();
                        fragmentoND = new FragmentMenuPrincipal();
                        break;
                    case (R.id.nav_opciones):
                        fragmentoND = new FragmentOpciones();
                        break;
                    case (R.id.nav_admin_cursos):
                        fragmentoND = new FragmentGestionarCursos();
                        break;
                    case (R.id.nav_admin_noticias):
                        fragmentoND = new FragmentGestionarNoticias();
                        break;
                    case (R.id.nav_admin_rutas):
                        fragmentoND = new FragmentGestionarRutas();
                        break;
                    case (R.id.nav_admin_permisos):
                        fragmentoND = new FragmentGestionarPermisos();
                        break;
                    case (R.id.nav_admin_escuela):
                        fragmentoND = new FragmentGestionarEscuela();
                        break;
                }
                transaccion.replace(R.id.contenedor_fragments_ND,fragmentoND);
                transaccion.addToBackStack(null);

                transaccion.commit();
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

    //Método para obtener el tipo de usuario para poder generar el tipo del navigator drawer en funcion del usuario
    private String obtencionTipoUsuarioND(){


        //Creación de la referencia a la base de datos:
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtencion del usuario actual:
        FirebaseUser usuario = mAuth.getCurrentUser();

        //Query:


        mDatabase.child("Usuarios").child(usuario.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                   tipoUsuarioApp = snapshot.child("tipoUsuario").getValue().toString();

                //---------------------

                   Menu menu = navigationView.getMenu();
                if(tipoUsuarioApp.equals("Profesorado")){
                    MenuItem menuProfe = menu.findItem(R.id.menuProfesorND);
                    menuProfe.setVisible(true);
                }else if(tipoUsuarioApp.equals("Administración")){
                    MenuItem menuProfe = menu.findItem(R.id.menuProfesorND);
                    menuProfe.setVisible(true);
                    MenuItem menuAdmin = menu.findItem(R.id.menuAdministracionND);
                    menuAdmin.setVisible(true);
                }

                //-------------------
        //TODO comprobar que funciona
                   String imagenObtenida = snapshot.child("imagenUsuario").getValue().toString();
               Picasso.get().load(imagenObtenida).into(imagenNavigatorDrawer);

                String nombre = snapshot.child("nombreUsuario").getValue().toString();
                String apellido = snapshot.child("apellidosUsuario").getValue().toString();
                String nombreCompleto = nombre + " " + apellido;
                nombreNavigatorDrawer.setText(nombreCompleto);

                String correo = snapshot.child("correoUsuario").getValue().toString();
                correoNavigatorDrawer.setText(correo);
            }


            //TODO no funciona, no entra en onDataChange
            //ESta respuesta parece interesante : https://stackoverflow.com/questions/47847694/how-to-return-datasnapshot-value-as-a-result-of-a-method/47853774#47853774
            //De momento se deja como administrador apra todo el mundo.

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return tipoUsuarioApp;
    }


    //Metodo para pasar del fragment principal al fragment menu principal:
    public void transicionHomeMenuPrincipal(){


    }


}




