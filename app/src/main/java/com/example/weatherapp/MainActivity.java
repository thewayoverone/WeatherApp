package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected EditText editText;
    protected Button button;
    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        EditText editText = findViewById(R.id.city);
        TextView textView = findViewById(R.id.weather);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<City> list = init();
                        String city = editText.getText().toString().toLowerCase(Locale.ROOT);
                        City city1 = null;
                        for (City c: list){
                            if (c.getName().equals(city)||c.getrName().equals(city)){
                                city1 = c;
                            }
                        }
                        if (city1==null){
                            textView.setText("Некоретные данные");
                        }else {
                        String urlText
                                = "https://api.openweathermap.org/data/2.5/weather?lat="+city1.getLat()+"&lon="+city1.getLon()+"&appid=8214b5994813fe7379c043e827584008";
                        try {
                            URL url = new URL(urlText);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                            String str = bufferedReader.readLine();
                            JSONObject jsonObject = new JSONObject(str);
                            JSONObject weather = (JSONObject) jsonObject.get("main");
                            String actualTemp = weather.get("temp").toString();
                            String feelsTemp = weather.get("feels_like").toString();
                            String minTemp = weather.get("temp_min").toString();
                            String maxTemp = weather.get("temp_max").toString();

                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Сейчас на улице " + actualTemp +"\n");
                            stringBuilder.append("Ощущается как "+feelsTemp+"\n");
                            stringBuilder.append("Минимальная температура" + minTemp +"\n");
                            stringBuilder.append("Максимальная температура "+ minTemp+"\n");

                            editText.setText("");
                            textView.setText(stringBuilder);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }}
                });
                thread.start();
            }
        });
    }
    public List<City> init(){
        List<City> list = new ArrayList<>();
        list.add(new City("minsk","минск", "53.893009", "27.567444"));
        list.add(new City("grodno","гродно","53.669353","23.813131"));
        list.add(new City("brest","брест", "52.097622", "23.734051"));
        list.add(new City("gomel", "гомель","52.4345000", "30.9754000"));
        list.add(new City("mogilev","могилев", "53.9007159","30.3313598"));
        list.add(new City("vitebsk", "витебск","55.187222", "30.205116"));

        return list;
    }
}
/*
   */
