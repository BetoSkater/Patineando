package com.example.patineando.FragmentsND;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.patineando.AuxAdaptadorGestionPermisos;
import com.example.patineando.R;
import com.example.patineando.Tusuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
//TODO Fragment al que se accede desde el fragment FragmentGestionarPermisos
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUsuarioPermisos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUsuarioPermisos extends Fragment  {

    private Spinner spinnerPermisos;
    private TextView txtNombreUsuarioPermisos, txtCorreoUsuarioPermisos, txtPermisosUsuarioPermisos;
    private ImageView imgUsuarioPermisos;
    private Button botonActualizarPermisos,botonGestionarCuentaMatricula, botonGestionarPerfilUsuario;

    //

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final AuxAdaptadorGestionPermisos ARG_PARAM1 = new AuxAdaptadorGestionPermisos();
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Tusuario mParam1;
    //private String mParam2;

    public FragmentUsuarioPermisos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.onCreate

     * @return A new instance of fragment FragmentUsuarioPermisos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUsuarioPermisos newInstance(Tusuario param1) {
        FragmentUsuarioPermisos fragment = new FragmentUsuarioPermisos();
        Bundle args = new Bundle();
        args.putSerializable("DatosUsuario", param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = (Tusuario) getArguments().getSerializable("DatosUsuario");
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_usuario_permisos, container, false);
        //Asignación de las variables con los controladores:
        spinnerPermisos = (Spinner) vista.findViewById(R.id.sprDesplegablePermisos);
        imgUsuarioPermisos = (ImageView) vista.findViewById(R.id.imgImagenFraUsuarioPermisos);
        txtNombreUsuarioPermisos = (TextView) vista.findViewById(R.id.txtNombreFraUsuarioPermisos);
        txtCorreoUsuarioPermisos = (TextView) vista.findViewById(R.id.txtCorreoFraUsuarioPermisos);
        txtPermisosUsuarioPermisos = (TextView) vista.findViewById(R.id.txtTipoUsuarioFraUsuarioPermisos);
        botonActualizarPermisos = (Button) vista.findViewById(R.id.btnGuardarCambiosUsuarioPermisos);
        botonGestionarCuentaMatricula = (Button) vista.findViewById(R.id.btnModificarInfoEscuelaUsuarioPermisos);
        botonGestionarPerfilUsuario = (Button) vista.findViewById(R.id.btnModificarPerfilUsuarioUsuarioPermisos);

        //Extracción de los valores recibidos:

         String idUsuarioObtenido = mParam1.getIdUsuario();

         String correoUsuarioObtenido = mParam1.getCorreoUsuario();
         String imagenUsuarioObtenido = mParam1.getImagenUsuario();
        Uri uriImagen = Uri.parse(imagenUsuarioObtenido);
                String tipoUsuarioObtenido = mParam1.getTipoUsuario();
         String nombreUsuarioObtenido = mParam1.getNombreUsuario();
         String apellidosUsuarioObtenido = mParam1.getApellidosUsuario();

         //Picasso.get().load(imagenUsuarioObtenido).into(imgUsuarioPermisos); //TODO no funciona lo de Picasso
        Glide.with(getContext()).load(uriImagen).into(imgUsuarioPermisos);
         txtNombreUsuarioPermisos.setText(nombreUsuarioObtenido+" "+apellidosUsuarioObtenido);
         txtCorreoUsuarioPermisos.setText(correoUsuarioObtenido);
         txtPermisosUsuarioPermisos.setText(tipoUsuarioObtenido);

         botonActualizarPermisos.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 actualizacionDePermisos();
             }
         });

         botonGestionarCuentaMatricula.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // irAGestionarCuentaMatricula(mParam1);
                 Fragment fragmento = new FragmentModificarCuentaMatriculaUsuario();
                 //Fragment fragmento = FragmentUsuarioPermisos.newInstance(auxiliarModeloDatos.getIdUsuario());
                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
                 //transaction.hide(getActivity().getFragmentManager().findFragmentByTag("fragment_gestionar_permisos")); //TODO igual el tag está mal, ya que po lo que parece no es el id, entiendo que es el nobre del xml
                 // transaction.add(R.id.fragmento_gestionar_permisos_usuario_permisos,fragmento);
                 transaction.replace(R.id.contenedor_fragments_ND,fragmento);
                 transaction.addToBackStack(null); //Esto no entiendo que hace
                 transaction.commit();
             }
         });

         botonGestionarPerfilUsuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });

        //Array con los valores de las opciones  que se van a mostrar en el Spinner:
        String [] opcionesPermisos = {"Alumnado","Profesorado","Administración"};

        //Creación del objeto arrayAdapter en el que se pasa como argumento el tipo de lista y el array con los valores:
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, opcionesPermisos);
                //Solucion al error del contexto: error: no suitable constructor found for ArrayAdapter(FragmentUsuarioPermisos,int,String[])
                //        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opcionesPermisos);
                        // * https://stackoverflow.com/questions/38633460/error-no-suitable-constructor-found-for-arrayadapter

        //Asociacion del ArrayAdapter al Spinner
        spinnerPermisos.setAdapter(adaptador);
        return vista;

    }//Fin onCreateView

    //Metodo que se llama en el botón para actualizar los permisos:
    public void actualizacionDePermisos(){
       //VAtiables necesarias para la correcta realización de la edición de un campo.
        //ID del usuario al que se le quieren cambiar los permisos:
        String identificadorUsuario = mParam1.getIdUsuario();
        //Campo de la tabla en el que se va a realizar la modificacion:
        String campoAModificar = "tipoUsuario";

        //Creación de la referencia a la base de datos:
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtención del tipo de parámetro que se debe introducir en el campo tipoUsuario:
        String palabraSpinner = spinnerPermisos.getSelectedItem().toString();
        String palabraInsertar;
        if(palabraSpinner.equals("Alumnado")){
            palabraInsertar = palabraSpinner;
        }else if(palabraSpinner.equals("Profesorado")){
            palabraInsertar = palabraSpinner;
        }else if (palabraSpinner.equals("Administración")){
            palabraInsertar = palabraSpinner;
        }else{
            palabraInsertar = "Alumnado"; //Si por algún casual falla y no se obtiene ningún valor, se pone que por defecto sea de tipo Alumnado, ya que es el permiso de más bajo nivel.
        }

        mDatabase.child("Usuarios").child(identificadorUsuario).child(campoAModificar).setValue(palabraInsertar);
        Toast.makeText(getContext(),"Permisos actualizados con exito",Toast.LENGTH_SHORT).show();

    }//Fin método public void actualizacionDePermisos(){
/*
    //Sobreescritura del interfaz onClick para poder mostrar el fragmentModificarCuentaMatricula ppasando la informaciónd el usuario alque se quiere modificar la matricula:
    @Override
    public void onClick(Tusuario auxModeloEnviar){
        Fragment fragmento2 = FragmentModificarCuentaMatriculaUsuario.newInstance(auxModeloEnviar);
        FragmentTransaction transaccion2 = getFragmentManager().beginTransaction();
        transaccion2.replace(R.id.contenedor_fragments_ND,fragmento2);
        transaccion2.addToBackStack(null); //Esto no entiendo que hace
        transaccion2.commit();

    }//Fin Sobreescritura onClick


 */

    public void irAGestionarCuentaMatricula(Tusuario datosUsuario){
       //https://stackoverflow.com/questions/16036572/how-to-pass-values-between-fragments
        Bundle bundle = new Bundle();
        bundle.putSerializable("DatosUsuario2", datosUsuario);

        FragmentModificarCuentaMatriculaUsuario fragmentoModificarCuentaMatricula = new FragmentModificarCuentaMatriculaUsuario();
        fragmentoModificarCuentaMatricula.setArguments(bundle);
         getFragmentManager().beginTransaction().replace(R.id.contenedor_fragments_ND, fragmentoModificarCuentaMatricula).commit();
    }


    //Se crea la interfaz para el OnClickListener
    public interface OnClickListenerMio{
        public void onClick(Tusuario modeloEnviar);
    }

}