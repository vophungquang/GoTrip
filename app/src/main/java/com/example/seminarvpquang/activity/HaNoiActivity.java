package com.example.seminarvpquang.activity;

import android.os.Bundle;
import android.util.Log;
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
import com.example.seminarvpquang.adapter.HaNoiAdapter;
import com.example.seminarvpquang.model.Place;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.GoTrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HaNoiActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewHN;
    HaNoiAdapter HNAdapter;
    ArrayList<Place> arrayListHN;
    ArrayList<Place> arrayListHNFilter;
    int idHN =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanoi);


        listViewHN =  findViewById(R.id.listViewHN);
        arrayListHN = new ArrayList<>();
        arrayListHNFilter=new ArrayList<>();

        GetidPlaceType();
//            ActionToolBar();
        GetDataPhone();


        HNAdapter = new HaNoiAdapter(this,R.layout.item_place,arrayListHNFilter);
        listViewHN.setAdapter(HNAdapter);

    }

    private void GetDataPhone() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String pathHN = GoTrip.pathPlace ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, pathHN, new Response.Listener<String>() {
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
                        arrayListHN.add(new Place(Id,namePlace,
                                imagePlace,descriptionPlace,idPlace,doanhthu,diemdanhgia));


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<arrayListHN.size();i++)
                {
                    if(arrayListHN.get(i).getidPlace()==2)
                    {
                        arrayListHNFilter.add(new Place(arrayListHN.get(i).getId(),arrayListHN.get(i).getnamePlace()
                                ,arrayListHN.get(i).getPriceProduct(),arrayListHN.get(i).getimagePlace()
                                ,arrayListHN.get(i).getdescriptionPlace(),arrayListHN.get(i).getidPlace(), arrayListHN.get(i).getdoanhthu(),
                                arrayListHN.get(i).getmota(),arrayListHN.get(i).getDiemdanhgia()));
                    }
                }
                Toast.makeText(HaNoiActivity.this, arrayListHNFilter.size()+"", Toast.LENGTH_SHORT).show();
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
        idHN = getIntent().getIntExtra("idPlaceType",-1);
        Log.d("vitri",idHN +"");
    }

}