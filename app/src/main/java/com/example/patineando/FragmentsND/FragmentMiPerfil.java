package com.example.patineando.FragmentsND;

import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.patineando.Acceso;
import com.example.patineando.MenuNavigatorDrawer;
import com.example.patineando.MiPerfil;
import com.example.patineando.ModificarMiPerfil;
import com.example.patineando.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMiPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMiPerfil extends Fragment {

    private ImageView imgMiPerfil;
    private TextView txtNombre, txtApodo, txtPatines, txtDescripcion;
    private Button btnModificarPerfil, btnCerrarSesion;
    private FirebaseAuth mAuth;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMiPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMiPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMiPerfil newInstance(String param1, String param2) {
        FragmentMiPerfil fragment = new FragmentMiPerfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_mi_perfil, container, false);

        imgMiPerfil = (ImageView) vista.findViewById(R.id.imvImagenPerfilND);
        txtNombre = (TextView) vista.findViewById(R.id.lblNombreUsuarioResultadoND);
        txtApodo = (TextView) vista.findViewById(R.id.lblApodoUsuarioResultadoND);
        txtPatines = (TextView) vista.findViewById(R.id.lblPatinesUsuarioResultadoND);
        txtDescripcion = (TextView) vista.findViewById(R.id.lblDescripcionUsuarioResultadoND);

        btnModificarPerfil = (Button) vista.findViewById(R.id.btnModificarMiPerfilND);
        btnModificarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarMiPerfil();
            }
        });





        btnCerrarSesion = (Button) vista.findViewById(R.id.btnCerrarSesionMiPerfilND);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion(v);
            }
        });

        mAuth = FirebaseAuth.getInstance(); //Inicialización de la instancia de FirebaseAuth
        cargarDatosUsuario();




        return vista;
    }//Fin onCreateView


    //Método que carga los datos del alumno alojados en la base de datos, en la primera carga, los datos estarán vacios.
    public void cargarDatosUsuario(){
        DatabaseReference mDatabase; //TODO debería ser private, pero no lo permite ya que la clase original es pública. Debo cambiar esto?
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser usuario = mAuth.getCurrentUser(); //TODO igual lo mejor es hacer esta declaracion a nivel de clase



        mDatabase.child("Usuarios").child(usuario.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    //TODO PONER IMAGEN
                    String imagenRecuperada = String.valueOf(task.getResult().child("imagenUsuario").getValue());
                    Picasso.get().load(imagenRecuperada).into(imgMiPerfil);
                    imgMiPerfil.setBackgroundDrawable(ponerBordeMorado());
                    String nombreRecuperado = String.valueOf(task.getResult().child("nombreUsuario").getValue());
                    String apellidosRecuperados = String.valueOf(task.getResult().child("apellidosUsuario").getValue());
                    txtNombre.setText(nombreRecuperado + " "+ apellidosRecuperados);

                    String apodoRecuperado = String.valueOf(task.getResult().child("apodoUsuario").getValue());
                    txtApodo.setText(apodoRecuperado);

                    String patinesRecuperado = String.valueOf(task.getResult().child("patinesUsuario").getValue());
                    txtPatines.setText(patinesRecuperado);

                    String descripcionRecuperado = String.valueOf(task.getResult().child("descripcionUsuario").getValue());
                    txtDescripcion.setText(descripcionRecuperado);

                }else{
                    Log.e(getResources().getString(R.string.logFirebase),getResources().getString(R.string.logErrorObtencionDatos),task.getException());
                }
            }
        });
    }//Fin metodo cerrrar sesion

    public void modificarMiPerfil(){
        Fragment fragmento = new FragmentModificarMiPerfil();
        FragmentTransaction transaccion = getFragmentManager().beginTransaction();
        transaccion.replace(R.id.contenedor_fragments_ND,fragmento);
        transaccion.addToBackStack(null);
        transaccion.commit();
    }

    public void cerrarSesion(View vista){

        //Cerrar la sesión de Firebase. //TODO no se si esto no funciona, o si es que el código del onStart de la actividad Acceso es lo que fastidia todo:
        //  FirebaseAuth.getInstance().signOut();
        //Confirmado que lo anterior no funciona, el siguiente fragmento se ha sacado de la siguiente guia y funciona fenomenal:
        //https://stackoverflow.com/questions/38707133/google-firebase-sign-out-and-forget-user-in-android-app
        GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(),gso);
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(vista.getContext(),Acceso.class);
                    Toast.makeText(vista.getContext(),getResources().getString(R.string.sesionCerradaMiPerfil),Toast.LENGTH_SHORT).show();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Todo investigar que hace esto
                    startActivity(intent);
                }
        });

         //TODO no tiene mucho sentido, no s esupone que una vez se accede con Google, deberia estar el propio FirebaseAuth???

    }//Fin metodo cerrarSesion;

    //Metodo para poner borde a los botones:
    public GradientDrawable ponerBordeMorado(){
        GradientDrawable borde = new GradientDrawable();
        borde.setColor(Color.argb(0,255,255,255));
        borde.setStroke(15,Color.argb(255,41,19,42));
        return borde;
    }


}