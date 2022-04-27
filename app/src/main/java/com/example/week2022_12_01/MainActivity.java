package com.example.week2022_12_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Getir(View v){

        Toast.makeText(this,"GELİYOR GELMEKTE OLAN",Toast.LENGTH_LONG).show();

        GetJson getir = new GetJson();

        try {
            String myJson=getir.execute("https://api.openweathermap.org/data/2.5/weather?q=Trabzon&appid=d2715d5a7fce807196916e1ff6e43afb&lang=tr&units=metric").get();
            Log.d("MyJSON",myJson);
            JSONObject obj= new JSONObject(myJson);
            String w=obj.getString("weather");
            String m= obj.getString("main");
            JSONObject mw= new JSONObject(m);
            JSONArray  wa= new JSONArray(w);
              for(int i=0;i<wa.length();i++){
                  JSONObject el= wa.getJSONObject(i);
                  Log.d("Main", el.getString("main"));
                  Log.d("Desc", el.getString("description"));
              }

            Log.d("Temp",mw.getString("temp"));
            Log.d("Hissedilen Sıcaklık",mw.getString("feels_like"));
            Log.d("Hava Basıncı",mw.getString("pressure"));
            Log.d("Nem",mw.getString("humidity"));




        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}