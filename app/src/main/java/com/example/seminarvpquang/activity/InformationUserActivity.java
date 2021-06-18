package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.model.User;
import com.example.seminarvpquang.ultil.GoTrip;

import java.util.HashMap;
import java.util.Map;

public class InformationUserActivity extends AppCompatActivity {

    EditText editTextNameCustomer, editTextdiachiCustomer, editTextEmailCustomer;
    Button buttonConfrim, buttonBack;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);
        init();
        showUser();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonConfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capNhatUser();
            }
        });
    }



    private void init() {
        editTextNameCustomer = (EditText) findViewById(R.id.editTextNameCustomer);
        editTextdiachiCustomer = (EditText) findViewById(R.id.editdiachi);
        editTextEmailCustomer = (EditText) findViewById(R.id.editTextEmailCustomer);
        buttonConfrim = (Button) findViewById(R.id.buttonConfrim);
        buttonBack = (Button) findViewById(R.id.buttonBack);
    }
    private  void showUser(){
        Intent intent=getIntent();

        User user= (User) intent.getSerializableExtra("user");
        id=user.getId();
        editTextNameCustomer.setText(user.getUsername());
        editTextdiachiCustomer.setText(user.getAddress());
        editTextEmailCustomer.setText(user.getEmail());
    }
    private  void capNhatUser(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, GoTrip.updateUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(InformationUserActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else
                        {
                            Toast.makeText(InformationUserActivity.this, "Loi cap nhat", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InformationUserActivity.this, "Da xay ra loi,vui long thu lai", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("idUser",String.valueOf(id));
                params.put("username",editTextNameCustomer.getText().toString().trim());
                params.put("address",editTextdiachiCustomer.getText().toString().trim());
                params.put("email",editTextEmailCustomer.getText().toString().trim());



                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
