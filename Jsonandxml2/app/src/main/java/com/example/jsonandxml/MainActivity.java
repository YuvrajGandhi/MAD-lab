package com.example.jsonandxml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    Button bx,bj;

    TextView tx,tj;

    String KEY_NAME = "name";
    String KEY_LATITUDE = "latitude";
    String KEY_LONGITUDE = "longitude";
    String KEY_TEMPERATURE = "temperature";
    String KEY_HUMIDITY = "humidity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx=findViewById(R.id.xtable);
        tj=findViewById(R.id.jtable);
        tx.setVisibility(View.GONE);
        tj.setVisibility(View.GONE);
        bx=findViewById(R.id.btnxml);
        bj=findViewById(R.id.btnjson);
        bx.setOnClickListener(this::parseXml);
        bj.setOnClickListener(this::parseJson);

    }
    private void parseXml(View v){
        tx.setVisibility(View.VISIBLE);
        bx.setVisibility(View.GONE);

        try{
            InputStream stream = getAssets().open("city_data.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document dom = db.parse(stream);
            NodeList city = dom.getElementsByTagName("city");
            String name = "";
            String latitude = "";
            String longitude = "";
            String humidity = "";
            String temperature = "";
            for(int i=0; i<city.getLength(); i++){

                Element e = (Element) city.item(i);

                if(name.equals("")){

                    name = this.getValue(e,KEY_NAME);
                }
                if(latitude.equals("")){
                    latitude = this.getValue(e,KEY_LATITUDE);
                }
                if(longitude.equals("")){
                    longitude = this.getValue(e,KEY_LONGITUDE);
                }
                if(humidity.equals("")){
                    humidity = this.getValue(e,KEY_HUMIDITY);
                }
                if(temperature.equals("")){
                    temperature = this.getValue(e, KEY_TEMPERATURE  );
                }
            }

            ((TextView)findViewById(R.id.xname)).setText("Name: "+name);
            ((TextView)findViewById(R.id.xlat)).setText("Latitude: "+latitude);
            ((TextView)findViewById(R.id.xlong)).setText("Longitude: "+longitude);
            ((TextView)findViewById(R.id.xhumid)).setText("Humidity: "+humidity);
            ((TextView)findViewById(R.id.xtemp)).setText("Temperature: "+temperature);

        }

        catch(Exception e){
           System.out.println("Something Went Wrong:"+ e.getMessage());
            e.printStackTrace();
        }
    }

    private String getValue(Element item, String str) {

        NodeList n = item.getElementsByTagName(str);
        return  n.item(0).getFirstChild().getNodeValue();
    }
    private void parseJson(View v){
        tj.setVisibility(View.VISIBLE);
        bj.setVisibility(View.GONE);
        JSONObject city;

        try{
            InputStream is = getAssets().open("city.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            city = new JSONObject(new String(buffer, "UTF-8"));
            ((TextView)findViewById(R.id.jname)).setText("Name: "+city.getString("name"));
            ((TextView)findViewById(R.id.jlat)).setText("Latitude: "+city.getString("latitude"));
            ((TextView)findViewById(R.id.jlong)).setText("Longitude: "+city.getString("longitude"));
            ((TextView)findViewById(R.id.jtemp)).setText("Temperature: "+city.getString("temperature"));
            ((TextView)findViewById(R.id.jhumid)).setText("Humidity: "+city.getString("humidity"));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}