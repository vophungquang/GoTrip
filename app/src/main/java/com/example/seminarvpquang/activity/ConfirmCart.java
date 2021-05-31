package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.model.Cart;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfirmCart extends AppCompatActivity {

    Button btnConfirm,btnHuy;
    TextView textViewTongtien;
    int idUser=0;
    int tongtien=0;
    ArrayList<Cart> cartArrayList;
    EditText edtDiaChi;
    ArrayList<Cart> cartArrayListFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cart);
        textViewTongtien=findViewById(R.id.textviewTongtien);
        btnConfirm=findViewById(R.id.btnConfirm);
        btnHuy=findViewById(R.id.btnHuy);
        edtDiaChi=findViewById(R.id.textDiachi);
        cartArrayList=new ArrayList<>();
        cartArrayListFilter=new ArrayList<>();
        Intent intent=getIntent();
        idUser=intent.getIntExtra("idUser",123);
        tongtien=intent.getIntExtra("tongtienphaitra",123);
        textViewTongtien.setText(tongtien+"");
        Toast.makeText(this, "id usser"+idUser, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "tongtien : "+tongtien, Toast.LENGTH_SHORT).show();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmCart.this, "Mua thanh cong", Toast.LENGTH_SHORT).show();
                GetDataCart();
                Intent intent = new Intent(ConfirmCart.this,HomeActivity.class);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void themdonhangchitiet(Cart cart ){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.insertDonHangChiTiet,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(ConfirmCart.this, "Them vào đơn hàng chi tiết thành công", Toast.LENGTH_SHORT).show();
                            deleteDonHang(cart.getId());

                        }
                        else
                        {
                            Toast.makeText(ConfirmCart.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConfirmCart.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();


                params.put("id_user",cart.getIdUser()+"");
                params.put("tensanpham",cart.getTensanpham()+"");
                params.put("soluong",cart.getSoluong()+"");
                params.put("ngaymuahang",cart.getNgaymuahang());
                params.put("diachigiaohang",edtDiaChi.getText().toString());
                params.put("giasanpham",cart.getGiasanpham()+"");
                params.put("hinhanhsanpham",cart.getImageSanPham());



                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void GetDataCart() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDonHang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cartArrayList.clear();
                cartArrayListFilter.clear();
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            int id=0;
                            int idUser=0;
                            String tensanpham="";
                            int soluong=0;
                            String ngaymuahang="";
                            int giasanpham=0;

                            String hinhanhsanpham="";
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            idUser = jsonObject.getInt("idUser");
                            tensanpham = jsonObject.getString("tensanpham");
                            soluong = jsonObject.getInt("soluong");
                            ngaymuahang = jsonObject.getString("ngaymuahang");
                            giasanpham = jsonObject.getInt("giasanpham");

                            hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            cartArrayList.add(new Cart(id,idUser,tensanpham,soluong,ngaymuahang,giasanpham,hinhanhsanpham));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Toast.makeText(ConfirmCart.this, "carArraylist là: "+cartArrayList.size(), Toast.LENGTH_SHORT).show();
                    for (int j=0;j<cartArrayList.size();j++)
                    {

                        if(idUser==cartArrayList.get(j).getIdUser())
                        {
                            Cart cart=cartArrayList.get(j);
                            cartArrayListFilter.add(new Cart(cart.getId(),cart.getIdUser(),cart.getTensanpham(),cart.getSoluong(),cart.getNgaymuahang(),cart.getGiasanpham(),
                                    cart.getImageSanPham()));

                        }

                    }
                    for (int i=0;i<cartArrayListFilter.size();i++)
                    {
                        Cart cart=cartArrayListFilter.get(i);
                        themdonhangchitiet(cart);

                    }
                    Toast.makeText(ConfirmCart.this, "carArraylistFilter là: "+cartArrayListFilter.size(), Toast.LENGTH_SHORT).show();


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


    private  void deleteDonHang(int idDonhangne){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.pathXoaDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(ConfirmCart.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else
                        {
                            Toast.makeText(ConfirmCart.this, "Loi Xoa", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConfirmCart.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("iddonhang",String.valueOf(idDonhangne));


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
