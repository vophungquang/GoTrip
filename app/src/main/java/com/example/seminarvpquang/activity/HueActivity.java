package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.HueAdapter;
import com.example.seminarvpquang.model.Place;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.GoTrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HueActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewHue;
    HueAdapter hueAdapter;
    ArrayList<Place> arrayListHue;
    ArrayList<Place> getarrayListHueFilter;
    int idHue =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hue);


        listViewHue =  findViewById(R.id.listViewHue);
        arrayListHue = new ArrayList<>();
        getarrayListHueFilter=new ArrayList<>();

        GetidPlaceType();
//            ActionToolBar();
        GetDataPhone();


        hueAdapter = new HueAdapter(this,R.layout.item_place,getarrayListHueFilter);
        listViewHue.setAdapter(hueAdapter);

        listViewHue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HueActivity.this, PlaceDetailActivity.class);
                intent.putExtra("information",arrayListHue.get(position));
                startActivity(intent);
            }
        });


    }

    private void GetDataPhone() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String pathHue = GoTrip.pathPlace ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, pathHue, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int Id = 0;
                String namePlace = "";
                String imagePlace = "";
                String descriptionPlace = "";
                int idPlace = 0;
                int doanhthu=0;
                String diemdanhgia="";

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        Id = jsonObject.getInt("id");
                        namePlace = jsonObject.getString("tendiadiem");
                        imagePlace = jsonObject.getString("hinhanhdiadiem");
                        descriptionPlace = jsonObject.getString("motadiadiem");
                        idPlace = jsonObject.getInt("iddiadiem");
                        doanhthu=jsonObject.getInt("doanhthu");
                        diemdanhgia=jsonObject.getString("diemdanhgia");
                        arrayListHue.add(new Place(Id,namePlace,
                                imagePlace,descriptionPlace,idPlace,doanhthu,diemdanhgia));



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<arrayListHue.size();i++)
                {
                    if(arrayListHue.get(i).getidPlace()==4)
                    {
                        getarrayListHueFilter.add(new Place(arrayListHue.get(i).getId(),arrayListHue.get(i).getnamePlace()
                                ,arrayListHue.get(i).getimagePlace()
                                ,arrayListHue.get(i).getdescriptionPlace(),arrayListHue.get(i).getidPlace(),
                                arrayListHue.get(i).getdoanhthu(), arrayListHue.get(i).getDiemdanhgia()));
                    }
                }
                Toast.makeText(HueActivity.this, getarrayListHueFilter.size()+"", Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        }) ;
        requestQueue.add(stringRequest);
    }


    private void GetidPlaceType() {
        idHue = getIntent().getIntExtra("idPlaceType",-1);
        Log.d("vitri",idHue +"");
    }

}
