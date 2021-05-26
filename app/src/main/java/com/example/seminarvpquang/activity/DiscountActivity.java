package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.DiscountAdapter;
import com.example.seminarvpquang.model.Discount;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DiscountActivity extends AppCompatActivity {

    ArrayList<Discount> discountArrayList;
    DiscountAdapter discountAdapter;
    ListView listViewDiscount;
    Button btnXacnhangiamgia,btnhuyxacnhan;
    int discounts=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        discountArrayList = new ArrayList<>();

        btnXacnhangiamgia = findViewById(R.id.btnxacnhangiamgia);
        btnhuyxacnhan = findViewById(R.id.btnhuyxacnhangiamgia);
        listViewDiscount = findViewById(R.id.listviewgiamgia);
        discountAdapter = new DiscountAdapter(this, R.layout.dong_discount, discountArrayList);
        listViewDiscount.setAdapter(discountAdapter);
        GetDataDiscount();

        btnXacnhangiamgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("discount", discounts);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
    public void GetDataDiscount() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.getDiscount, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            int id=0;
                            String ten="";
                            int tiengiamgia=0;

                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");

                            ten = jsonObject.getString("ten");
                            tiengiamgia = jsonObject.getInt("giagiam");
                            discountArrayList.add(new Discount(id,ten,tiengiamgia));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                    discountAdapter.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void discountSanpham(int discount){
        discounts=discount;

    }
}
