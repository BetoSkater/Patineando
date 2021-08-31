package com.example.patineando.FragmentsND;

import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.patineando.R;
import com.example.patineando.TRutas;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMapaRuta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMapaRuta extends Fragment {


    ImageView imagenMapa;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
 // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
   // private String mParam2;

    public FragmentMapaRuta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMapaRuta.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMapaRuta newInstance(String param1, String param2) {
        FragmentMapaRuta fragment = new FragmentMapaRuta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
      //  args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
          //  mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_mapa_ruta, container, false);
        imagenMapa = (ImageView)vista.findViewById(R.id.imgDetalleMapa);

       // TRutas auxRuta = (TRutas) getArguments().getSerializable("RutaSeleccionada");
        String imagenUrl = getArguments().getString("urlMapa");
        Uri uriImagen = Uri.parse(imagenUrl);

        Glide.with(getContext()).load(uriImagen).into(imagenMapa);
        return vista;
    }
}