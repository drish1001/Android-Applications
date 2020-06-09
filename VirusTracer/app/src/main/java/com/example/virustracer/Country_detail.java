package com.example.virustracer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class Country_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        String TAG = Country_detail.class.getSimpleName();
        HashMap<String, Integer> data = new HashMap<>();

        Intent intent = getIntent();

        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("hashMap");
        String id = hashMap.get("slug");
        String title = hashMap.get("title");
        Integer ourid = Integer.parseInt(String.valueOf(hashMap.get("ourid")));
        Integer total_cases = Integer.parseInt(String.valueOf(hashMap.get("total_confirmed")));
        Integer total_recovered = Integer.parseInt(String.valueOf(hashMap.get("total_recovered")));
        Integer total_unresolved = Integer.parseInt(String.valueOf(hashMap.get("total_deaths")));
        Integer newcases = Integer.parseInt(String.valueOf(hashMap.get("total_new_cases_today")));
        Integer newdeaths = Integer.parseInt(String.valueOf(hashMap.get("total_new_deaths_today")));
        Integer newrecover = Integer.parseInt(String.valueOf(hashMap.get("total_new_recovered_today")));

        TextView country_title = findViewById(R.id.country_title);
        country_title.setText(title);


        PieChart pieChart = findViewById(R.id.piechart);
        BarChart chart = findViewById(R.id.chart);

        ArrayList NoOfEmp = new ArrayList();


        NoOfEmp.add(new BarEntry(total_cases, 0));
        NoOfEmp.add(new BarEntry(total_unresolved, 1));
        NoOfEmp.add(new BarEntry(total_recovered, 2));




        ArrayList year = new ArrayList();

        year.add("Total Cases");
        year.add("Total Deaths");
        year.add("Total Recovered");


        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Covid'19 Data");
        PieData ta = new PieData(year, dataSet);
        pieChart.setData(ta);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);

        //String country_url = "https://api.covid19api.com/live/country/" + id;

        ArrayList count = new ArrayList();
        ArrayList Date = new ArrayList();

        count.add(new BarEntry(newcases, 0));
        count.add(new BarEntry(newrecover, 1));
        count.add(new BarEntry(newdeaths, 2));

        Date.add("New Cases");
        Date.add("New Recovered");
        Date.add("New Deaths");

//        HttpHandler cmon = new HttpHandler();
//        String jsonStr = cmon.makeServiceCall(country_url);
//        country_title.setText("The fuck");
//        if (jsonStr != null) {
//            country_title.setText("What");
//
//            try {
//                country_title.setText("YES");
//                JSONArray a = new JSONArray(jsonStr);
//                for (int i = 0; i<a.length(); i++) {
//                    JSONObject b = a.getJSONObject(i);
//                    String confirmed = b.getString("Confirmed");
//                    Integer cnt = Integer.parseInt(confirmed);
//                    count.add(new BarEntry(cnt, i));
//                    String date = b.getString("Date");
//                    Date.add(date);
//
//                }
//            } catch (final JSONException e) {
//                country_title.setText("no");
//
//                Log.e(TAG, "Json parsing error: " + e.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Json parsing error: " + e.getMessage(),
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//            }
//
//        }
        BarDataSet bardataset = new BarDataSet(count, "Cases added today");
        chart.animateY(5000);
        BarData la = new BarData(Date, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(la);
    }
}
