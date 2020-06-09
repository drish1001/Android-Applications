package com.example.virustracer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;
    ArrayList<HashMap<String, String>> countryList = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> data = new HashMap<>();

    TextView total_cases, total_recovered,
            total_deaths, new_confirmed, new_recovered, new_deaths;


    private static String url = "https://api.covid19api.com/summary";
    private static String country_url = "https://api.thevirustracker.com/free-api?countryTotals=ALL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.list);
        new GetCountry().execute();

    }

    private class GetCountry extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading, please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject object = jsonObj.getJSONObject("Global");
                    String confirmed, deaths, recovered, newconfirmed, newdeaths, newrecovered;

                    confirmed = object.getString("TotalConfirmed");
                    deaths = object.getString("TotalDeaths");
                    recovered = object.getString("TotalRecovered");
                    newconfirmed = object.getString("NewConfirmed");
                    newdeaths = object.getString("NewDeaths");
                    newrecovered = object.getString("NewRecovered");

                    data.put("confirmed", confirmed);
                    data.put("deaths", deaths);
                    data.put("recovered", recovered);
                    data.put("newconfirmed", newconfirmed);
                    data.put("newdeaths", newdeaths);
                    data.put("newrecovered", newrecovered);


                    JSONArray countryitems = jsonObj.getJSONArray("Countries");
                    for(int i=0;i<countryitems.length();i++)
                    {
                        JSONObject c = countryitems.getJSONObject(i);

                        String ourid = Integer.toString(i+1);
                        String title = c.getString("Country");
                        String slug = c.getString("Slug");
                        String total_confirmed = c.getString("TotalConfirmed");
                        String total_recovered = c.getString("TotalRecovered");
                        String total_deaths = c.getString("TotalDeaths");
                        String total_new_cases_today = c.getString("NewConfirmed");
                        String total_new_deaths_today = c.getString("NewDeaths");
                        String total_new_recovered_today = c.getString("NewRecovered");


                        HashMap<String, String> country_data = new HashMap<>();

                        country_data.put("ourid", ourid);
                        country_data.put("title", title);
                        country_data.put("slug", slug);
                        country_data.put("total_confirmed", total_confirmed);
                        country_data.put("total_recovered", total_recovered);
                        country_data.put("total_deaths", total_deaths);
                        country_data.put("total_new_cases_today", total_new_cases_today);
                        country_data.put("total_new_deaths_today", total_new_deaths_today);
                        country_data.put("total_new_recovered_today", total_new_recovered_today);

                        countryList.add(country_data);

                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            for (String name : data.keySet())
            {
                String key = data.get(name);
                if(name== "confirmed") {
                    total_cases = findViewById(R.id.total_cases);
                    total_cases.setText(key);
                }
                if(name == "recovered")
                {
                    total_recovered = findViewById(R.id.total_recovered) ;
                    total_recovered.setText(key);
                }
                if(name == "deaths" )
                {
                    total_deaths = findViewById(R.id.total_deaths);
                    total_deaths.setText(key);
                }
                if(name== "newconfirmed") {
                    new_confirmed =  findViewById(R.id.new_confirmed);
                    new_confirmed.setText(key);
                }
                if(name == "newrecovered")
                {
                    new_recovered  =  findViewById(R.id.new_recovered);
                    new_recovered.setText(key);
                }
                if(name == "newdeaths")
                {
                    new_deaths  =  findViewById(R.id.new_deaths) ;
                    new_deaths.setText(key);
                }


            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, countryList,
                    R.layout.list_item, new String[]{"ourid", "title", "total_confirmed"}, new int[]{R.id.outrid, R.id.title, R.id.total_cases});

            lv.setAdapter(adapter);



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // selected item
                    String selected = ((TextView) view.findViewById(R.id.title)).getText().toString();
                    HashMap<String, String> hashMap= countryList.get(position);
                    Intent intent = new Intent(MainActivity.this, Country_detail.class);
                    intent.putExtra("hashMap", hashMap);
                    startActivity(intent);

                }
            });


        }
    }
}
