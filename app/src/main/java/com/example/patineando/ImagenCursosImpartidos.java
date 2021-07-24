package com.example.patineando;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImagenCursosImpartidos extends AppCompatActivity implements OnClickableAreaClickedListener  {

    ImageView imagenCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_cursos_impartidos);

        imagenCursos = (ImageView) findViewById(R.id.imgSeleccionCursos);
        //Hacer algo de lo que he puesto en el apartado de enlacves, hay varias formas de hacer esto, puede que la mejor se ala que utiliza la libreria externa.


        //Se sigue el ejemplo de uso de la documentacion de la libreria: https://github.com/LukasLechnerDev/ClickableAreasImages

        imagenCursos.setImageResource(R.drawable.imagen_escuelas); //La imagen no se pone directamente en el xml, se tiene que declarar aqui ya que la clas PhotoViewAttacher la necesita.

        //Se crea una imagen
        ClickableAreasImage areaImagen = new ClickableAreasImage(new PhotoViewAttacher(imagenCursos),this);

        //Inicializacion de la lista de areas seleccionables:

        List<ClickableArea> areasSeleccionables = new ArrayList<>();

        //Definicion de las áreas seleccionables. mediante el método add de la clase ClickableAreas. Este metodo necesita una nueva instancia de CLickableArea con lo sparametros de inicio (ccordenada x, coordenada y, ancho altura y la asignacion de un objeto
        areasSeleccionables.add(new ClickableArea(60,105,340,521, new Character('1'))); //Bien
        areasSeleccionables.add(new ClickableArea(401,105,340,521, new Character('2'))); //En teoria bien, asegurar ek tamaño de las cajas,
        areasSeleccionables.add(new ClickableArea(742,105,340,521, new Character('3')));//en teoria bien
        areasSeleccionables.add(new ClickableArea(199,699,340,521, new Character('4')));
        areasSeleccionables.add(new ClickableArea(540,699,340,521, new Character('5')));
        areasSeleccionables.add(new ClickableArea(60,1238,340,521, new Character('6')));
        areasSeleccionables.add(new ClickableArea(401,1238,340,521, new Character('7')));
        areasSeleccionables.add(new ClickableArea(742,1238,340,521, new Character('8')));


        //Ahora se añaden las areas seleccionables a la imagen:
        areaImagen.setClickableAreas(areasSeleccionables); //EL onClick va fuera del onCreate?? TODO COmprobar, suena raro. Si que va fuera, es un metodo de la clase que se tiene que sobreescribir ya que la clase está implementando onClickableAreaClickedListener

        //TODO ME QUEDO AQUI; LO SILENCIO PARA OBTENER LAS COORDENADAS CON UN ONCLICK





        //Metodo para conocer las coordendas de la imagen, esto se tiene que dejar silenciado ya que no es necesario //TODO quitar
        //Nota: solo se utiliza para conocer las coordendas en las que se tienen que hacer los recuagros:
        //https://stackoverflow.com/questions/8909835/android-how-do-i-get-the-x-y-coordinates-within-an-image-imageview
        //TODO NO FUNCIONA, siempre devuelve X = = e Y = 210;
        /*
        imagenCursos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int [] values = new int[2];
                view.getLocationOnScreen(values);
                Log.d("X AND Y", values [0] + " "+ values[1]);
                Toast toast = Toast.makeText(getApplicationContext(),"X = " + values[0] + " Y = " + values[1],Toast.LENGTH_LONG);
                toast.show();
            }
        });

         */
        //------------------------------
        /*
        //Segundo intento para conocer las coordenadaS:
        imagenCursos.setOnTouchListener( new View.OnTouchListener(){ //TODO este si que funciona
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    int x = (int) event.getX(); //Mantiene valor vertical
                    int y = (int) event.getY(); //Mantiene valor horizontal
                    Toast toast = Toast.makeText(getApplicationContext(),"X = " + x + " Y = " + y,Toast.LENGTH_LONG);
                    toast.show();
                }
                return false;
            }
        });

         */
        //----------------
    }//fin onCreate

    //Sobreescritura del método onClickableAreaTouched(object item)
    @Override
    public void onClickableAreaTouched(Object item){
        if (item instanceof Character){
            String text = "Curso nº: "+ item + " funciona";
            Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show ();
        }
    }
}