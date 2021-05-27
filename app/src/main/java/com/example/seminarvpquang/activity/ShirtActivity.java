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
import com.example.seminarvpquang.adapter.ShirtAdapter;
import com.example.seminarvpquang.model.Product;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShirtActivity extends AppCompatActivity {

    Toolbar toolbarPhone;
    ListView listViewShirt;
    ShirtAdapter shirtAdapter;
    ArrayList<Product> arrayListShirt;
    ArrayList<Product> arrayListShirtFilter;
    int idShirt =0;
    int page =1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);


        listViewShirt =  findViewById(R.id.listViewShirt);
        arrayListShirt = new ArrayList<>();
        arrayListShirtFilter=new ArrayList<>();

        GetIdProductType();
//            ActionToolBar();
        GetDataPhone();


        shirtAdapter = new ShirtAdapter(this,R.layout.item_phone,arrayListShirtFilter);
        listViewShirt.setAdapter(shirtAdapter);

        listViewShirt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShirtActivity.this,ProductDetailActivity.class);
                intent.putExtra("information",arrayListShirt.get(position));
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
                        arrayListShirt.add(new Product(idPhone,namePhone,pricePhone,imagePhone,descriptionPhone,idProductPhone
                                ,idthuonghieu,sosanphamdaban,sosanphamtonkho,diemdanhgia));



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<arrayListShirt.size();i++)
                {
                    if(arrayListShirt.get(i).getIdProduct()==idShirt)
                    {
                        arrayListShirtFilter.add(new Product(arrayListShirt.get(i).getId(),arrayListShirt.get(i).getNameProduct()
                                ,arrayListShirt.get(i).getPriceProduct(),arrayListShirt.get(i).getImageProduct()
                                ,arrayListShirt.get(i).getDescriptionProduct(),arrayListShirt.get(i).getIdProduct()));
                    }
                }
                Toast.makeText(ShirtActivity.this, arrayListShirtFilter.size()+"", Toast.LENGTH_SHORT).show();
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