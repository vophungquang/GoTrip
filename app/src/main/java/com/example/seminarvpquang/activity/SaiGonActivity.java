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
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.SaiGonAdapter;
import com.example.seminarvpquang.model.Place;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.GoTrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SaiGonActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewSG;
    SaiGonAdapter SGAdapter;
    ArrayList<Place> arrayListSG;
    ArrayList<Place> arrayListSGFilter;
    int idSG =0;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saigon);


        listViewSG =  findViewById(R.id.listViewSG);
        arrayListSG = new ArrayList<>();
        arrayListSGFilter=new ArrayList<>();

        GetidPlaceType();
//            ActionToolBar();
        GetDataPhone();


        SGAdapter = new SaiGonAdapter(this,R.layout.item_place,arrayListSGFilter);
        listViewSG.setAdapter(SGAdapter);

        listViewSG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SaiGonActivity.this, PlaceDetailActivity.class);
                intent.putExtra("information",arrayListSG.get(position));
                startActivity(intent);
            }
        });


    }

//    private void ActionBart() {
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//    }
    private void GetDataPhone() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String pathSG = GoTrip.pathPlace ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, pathSG,
                new Response.Listener<String>() {
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
                        arrayListSG.add(new Place(Id,namePlace,
                                imagePlace,descriptionPlace,idPlace,doanhthu,diemdanhgia));


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<arrayListSG.size();i++)
                {
                    if(arrayListSG.get(i).getidPlace()==1)
                    {

                        arrayListSGFilter.add(new Place(arrayListSG.get(i).getId(),arrayListSG.get(i).getnamePlace()
                                ,arrayListSG.get(i).getimagePlace()
                                ,arrayListSG.get(i).getdescriptionPlace(),arrayListSG.get(i).getidPlace(),
                                arrayListSG.get(i).getdoanhthu(), arrayListSG.get(i).getDiemdanhgia()));
                    }
                }

                Toast.makeText(SaiGonActivity.this, arrayListSGFilter.size()+"", Toast.LENGTH_SHORT).show();
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
        idSG = getIntent().getIntExtra("idPlaceType",-1);
        Log.d("vitri",idSG +"");
    }
}
