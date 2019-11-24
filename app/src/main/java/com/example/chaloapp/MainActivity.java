package com.example.chaloapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    SearchView sv;
    RecyclerView rv;
    private ArrayList<ListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // top tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chalo App");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        sv= (androidx.appcompat.widget.SearchView) findViewById(R.id.mSearch);


        rv= (RecyclerView) findViewById(R.id.recyclerview);

        //SET ITS PROPETRIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
//        adapter=new MyAdapter(this,getPlayers());
//        rv.setAdapter(adapter);
        listItems = new ArrayList<>();
        String uri = "http://mock.chalo.com:8080/metadata";
        loadRecyclerViewData(uri);
        //SEARCH
        sv.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter.getFilter().filter(query);
                return false;
            }
        });

    }




    private void loadRecyclerViewData(String uri)
    {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        try {
            JsonArrayRequest stringRequest = new JsonArrayRequest(
                    uri,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            progressBar.setVisibility(View.INVISIBLE);

                            Log.d("AK", response.toString());
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Log.e("AK",obj.getString("routeId"));
                                    Log.e("AK",obj.getString("routeName"));
                                    Log.e("AK",obj.getString("stopDataList"));
                                    ArrayList<LatLng> coordList = new ArrayList<LatLng>();
                                    JSONArray lat = obj.getJSONArray("stopDataList");
                                    for (int j=0;j<lat.length();j++)
                                    {
                                        JSONObject object=lat.getJSONObject(j);
                                        Double dlat = Double.valueOf(object.getString("latitute"));
                                        Double dlon = Double.valueOf(object.getString("longitude"));
                                        LatLng p =new LatLng(dlat,dlon);
                                        coordList.add(p);

                                    }
//                                    Log.e("JSON",lat.toString());
                                    ListItem item = new ListItem(
                                            obj.getString("routeId"),
                                            obj.getString("routeName"),
                                            obj.getString("stopDataList"),
                                            coordList
                                    );
                                    listItems.add(item);

                                    // adding movie to movies array
                                    //MessageList.add(msg);
                                    adapter=new MyAdapter(getApplicationContext(),listItems);
                                    rv.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            sv.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.e("AK",error.getMessage());

                            Toast.makeText(getApplicationContext(),"Unable to fetch data for you :(",Toast.LENGTH_LONG).show();
                        }

                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
        catch (Exception e)
        {
            Log.e("AK","exception occured");
            e.printStackTrace();
        }




    }



}
