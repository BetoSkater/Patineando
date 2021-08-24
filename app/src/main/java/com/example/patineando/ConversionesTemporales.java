package com.example.patineando;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ConversionesTemporales {


    public long tiempoAMPMalong(String horaAMPM){ //N funciona
        //https://stackoverflow.com/questions/6531632/conversion-from-12-hours-time-to-24-hours-time-in-java
        long resultado = 0;
        DateFormat formatoUno = new SimpleDateFormat("hh:mm:ss aa");
        Date date = null;
        try{
            date = formatoUno.parse(horaAMPM);

        DateFormat formatoDos = new SimpleDateFormat("HH:mm");
        String hora24 = formatoDos.format(date);

        //Ahora se pasa la hora en formato ej 23:00 a long
        //https://stackoverflow.com/questions/15561580/android-string-variable-hhmm-to-long-variable-miliseconds
        SimpleDateFormat formatoTres = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatoTres.setTimeZone(TimeZone.getTimeZone("UTC"));

        String cadenaPasar = hora24 + ":00.000";

        date = formatoTres.parse("1970-01-01 "+ cadenaPasar);
        resultado = date.getTime();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return resultado;
    }//Fin tiempoAMPMlong

    public String pasarDeAMPM (String horaPasar, boolean esPM){
        String resultado ="";
        String auxiliarHora = "";
        String auxiliarResto ="";
        int auxCorte = horaPasar.length();
        if (esPM){

            auxiliarHora = horaPasar.substring(0,2);
            auxiliarResto =horaPasar.substring(2,auxCorte);
            switch (auxiliarHora){
                case "00":
                    resultado = "12"+auxiliarResto;
                    break;
                case "01":
                    resultado = "13"+auxiliarResto;
                    break;
                case "02":
                    resultado = "14"+auxiliarResto;
                    break;
                case "03":
                    resultado = "15"+auxiliarResto;
                    break;
                case "04":
                    resultado = "16"+auxiliarResto;
                    break;
                case "05":
                    resultado = "17"+auxiliarResto;
                    break;
                case "06":
                    resultado = "18"+auxiliarResto;
                    break;
                case "07":
                    resultado = "19"+auxiliarResto;
                    break;
                case "08":
                    resultado = "20"+auxiliarResto;
                    break;
                case "09":
                    resultado = "21"+auxiliarResto;
                    break;
                case "10":
                    resultado = "22"+auxiliarResto;
                    break;
                case "11":
                    resultado = "23"+auxiliarResto;
                    break;
            }
        }else{

            resultado = horaPasar;
        }


        return resultado;

    }
}
