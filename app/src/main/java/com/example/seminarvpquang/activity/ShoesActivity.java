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
import com.example.seminarvpquang.adapter.ShoesAdapter;
import com.example.seminarvpquang.model.Product;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShoesActivity extends AppCompatActivity {

    Toolbar toolbarPhone;
    ListView listViewShoes;
    ShoesAdapter shoesAdapter;
    ArrayList<Product> arrayShoesShirt;
    ArrayList<Product> arrayListShoesFilter;
    int idShirt =0;
    int page =1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);


        listViewShoes =  findViewById(R.id.listViewShoes);
        arrayShoesShirt = new ArrayList<>();
        arrayListShoesFilter=new ArrayList<>();

        GetIdProductType();
//            ActionToolBar();
        GetDataPhone();


        shoesAdapter = new ShoesAdapter(this,R.layout.item_phone,arrayListShoesFilter);
        listViewShoes.setAdapter(shoesAdapter);

        listViewShoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShoesActivity.this,ProductDetailActivity.class);
                intent.putExtra("information",arrayShoesShirt.get(position));
                startActivity(intent);
            }
        });


    }

    private void GetDataPhone() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String pathPhone = Server.pathPhone ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, pathPhone, new Response.Listener<String>() {
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
                        arrayShoesShirt.add(new Product(idPhone,namePhone,pricePhone,imagePhone,descriptionPhone,idProductPhone
                                ,idthuonghieu,sosanphamdaban,sosanphamtonkho,diemdanhgia));



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<arrayShoesShirt.size();i++)
                {
                    if(arrayShoesShirt.get(i).getIdProduct()==idShirt)
                    {
                        arrayListShoesFilter.add(new Product(arrayShoesShirt.get(i).getId(),arrayShoesShirt.get(i).getNameProduct()
                                ,arrayShoesShirt.get(i).getPriceProduct(),arrayShoesShirt.get(i).getImageProduct()
                                ,arrayShoesShirt.get(i).getDescriptionProduct(),arrayShoesShirt.get(i).getIdProduct()));
                    }
                }
                Toast.makeText(ShoesActivity.this, arrayListShoesFilter.size()+"", Toast.LENGTH_SHORT).show();
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
        idShirt = getIntent().getIntExtra("idProductType",-1);
        Log.d("giatrsp",idShirt +"");
    }

    private void anhxa() {



    }

}
