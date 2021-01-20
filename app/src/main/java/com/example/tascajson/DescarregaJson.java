package com.example.tascajson;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DescarregaJson extends AsyncTask<TextView, Void, String> {

    TextView textOnEscriura;

    @Override
    protected String doInBackground(TextView... textViews) {


        textOnEscriura = textViews[0];   //Es pot enviar una llista però si només hi ha 1 agafem el primer.
        //Recupera un text del servidor i li retorna al postExecute
        String result="";
        try {
            //URL url = new URL("http://project.phpeducem.dx.am/getAllRestaurants.php");
            //URL url = new URL("http://time.jsontest.com");
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");

            HttpURLConnection urlConnection =  (HttpURLConnection)url.openConnection();
            Log.d("Connexio", "Inici openConnection a la URL: http://project.phpeducem.dx.am/getAllRestaurants.php");

            int status = urlConnection.getResponseCode();
            if (status==200) {

                //Recuperem contingut de la URL, llegim el text "per paquets de bytes"
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedInputStream bis = new BufferedInputStream(in);
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int num = bis.read();
                while(num != -1) {
                    buf.write((byte) num);
                    num = bis.read();
                }
                Log.d("Connexio", "Acaba de llegir el InputStream");
                result= buf.toString("UTF-8");
                //Aquest result és tot el text JSON que pasará al postExecute per tractar-lo
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;




    }

    @Override
    protected void onPostExecute(String text) {  //Arriba el paràmetre que retorna el doInBackground
        super.onPostExecute(text);
        Log.d("Connexio", "Al postExecute " + text);

        textOnEscriura.setText(text);  //Tot el JSON



        try {
            JSONArray json = new JSONArray (text);

            JSONObject restaurant = json.getJSONObject(0);


        } catch (JSONException e) {
            e.printStackTrace();
        }



        //Actualitza el TextView del layout, del UI Thread, amb el text que li passa per paràmetre.

    }


}
