package com.example.adrian.equipmentlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Equipment> equipmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViewAndAdapter();


    }

    private void initializeViewAndAdapter() {
        equipmentList = new ArrayList<Equipment>();

        getEquipmentListFromHin();
    }

    private void getEquipmentListFromHin(){
        Runnable run = new Runnable() {
            @Override
            public void run() {


                String myURL  = "http://kark.hin.no:8088/d3330log_backend/getTestEquipment";
                HttpURLConnection connection;

                try{
                    URL url = new URL(myURL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "text/plain; charset-utf-8");
                    int responseCode = connection.getResponseCode();

                    if(responseCode == HttpURLConnection.HTTP_OK){
                        String jsonResponseString = readServerResponse(connection.getInputStream());
                        equipmentList = createArrayList(jsonResponseString);
                        TextView view = (TextView) findViewById(R.id.test);
                        view.setText(equipmentList.toString());
                    }else{
                        Toast.makeText(MainActivity.this,"ERROR", Toast.LENGTH_SHORT);
                    }
                }catch (Exception ex){
                    throw new Exception(ex.printStackTrace());
                }
            }
        };
        run.run();
    }

    private ArrayList<Equipment> createArrayList(String jsonString) {
        Gson gson = new Gson();
        ArrayList<Equipment> equipmentlist = gson.fromJson(jsonString, ArrayList.class);
        return equipmentlist;
    }


    private String readServerResponse(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder totalString = new StringBuilder();
        String line;
        try {
            while((line = reader.readLine()) != null){
                totalString.append(line);
            }
            return totalString.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "error happened in reading server respone";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
