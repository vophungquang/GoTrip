package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.model.PhoneAdapter;
import com.example.seminarvpquang.model.Product;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SmartPhoneActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewPhone;
    PhoneAdapter phoneAdapter;
    ArrayList<Product> arrayListPhone;
    ArrayList<Product> arrayListPhoneFilter;
    int idPhone =0;
    int page =1;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_phone);


        listViewPhone =  findViewById(R.id.listViewPhone);
        arrayListPhone = new ArrayList<>();
        arrayListPhoneFilter=new ArrayList<>();

        GetIdProductType();
//            ActionToolBar();
        GetDataPhone();


        phoneAdapter = new PhoneAdapter(this,R.layout.item_phone,arrayListPhoneFilter);
        listViewPhone.setAdapter(phoneAdapter);

        listViewPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SmartPhoneActivity.this,ProductDetailActivity.class);
                intent.putExtra("information",arrayListPhone.get(position));
                startActivity(intent);
            }
        });


    }

    private void ActionBart() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void GetDataPhone() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String pathPhone = Server.pathPhone ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, pathPhone,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int idPhone = 0;
                String namePhone = "";
                int pricePhone = 0;
                String imagePhone = "";
                String descriptionPhone = "";
                int idProductPhone =0;
                int IdProduct = 0;
                int idthuonghieu=0;
                int sosanphamdaban=0;
                int sosanphamtonkho=0;
                String diemdanhgia="";

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        idPhone = jsonObject.getInt("id");
                        namePhone = jsonObject.getString("tensp");
                        pricePhone = jsonObject.getInt("giasp");
                        imagePhone=jsonObject.getString("hinhanhsp");
                        descriptionPhone = jsonObject.getString("motasp");
                        idProductPhone = jsonObject.getInt("idsanpham");
                        idthuonghieu=jsonObject.getInt("id_thuonghieu");
                        sosanphamtonkho=jsonObject.getInt("sosanphamtonkho");
                        sosanphamdaban=jsonObject.getInt("sosanphamdaban");
                        diemdanhgia=jsonObject.getString("diemdanhgia");
                        arrayListPhone.add(new Product(idPhone,namePhone,pricePhone,imagePhone,descriptionPhone,idProductPhone
                                ,idthuonghieu,sosanphamdaban,sosanphamtonkho,diemdanhgia));



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<arrayListPhone.size();i++)
                {
                    if(arrayListPhone.get(i).getIdProduct()==1)
                    {

                        arrayListPhoneFilter.add(new Product(arrayListPhone.get(i).getId(),arrayListPhone.get(i).getNameProduct()
                                ,arrayListPhone.get(i).getPriceProduct(),arrayListPhone.get(i).getImageProduct()
                                ,arrayListPhone.get(i).getDescriptionProduct(),arrayListPhone.get(i).getIdProduct(),
                                arrayListPhone.get(i).getId_thuonghieu(),arrayListPhone.get(i).getSosanphamdaban(),
                                arrayListPhone.get(i).getSosanphamcontonkho(),arrayListPhone.get(i).getDiemdanhgia()));
                    }
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        }) ;
        requestQueue.add(stringRequest);
    }


    private void GetIdProductType() {
        idPhone = getIntent().getIntExtra("idProductType",-1);
        Log.d("giatrsp",idPhone +"");
    }

    private void anhxa() {



    }


}