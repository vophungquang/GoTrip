package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.CartApdapter;
import com.example.seminarvpquang.adapter.DetailCartAdapter;
import com.example.seminarvpquang.model.DetailCart;
import com.example.seminarvpquang.model.User;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    Button btndangnhap,btnShowUser,btnExit;
    TextView textViewDonhang;
    int REQUEST_CODE_USER=123;
    ArrayList<User> userArrayList;
    User user;
    //user
    int idUser=0;
    String usernameuser="";
    String emailUser="";
    String addressUser="";
    int idUserDangNhap=0;
    ListView listViewCart;
    DetailCartAdapter detailCartAdapter;
    ArrayList<DetailCart> cartArrayList;
    ArrayList<DetailCart> cartArrayListFiler;
    CartApdapter cartApdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        btndangnhap=findViewById(R.id.btnlogin);
        btnShowUser=findViewById(R.id.btnShowUserneem);
        textViewDonhang=findViewById(R.id.texviewThongtindonhang);
        btnExit=findViewById(R.id.showUserExitne);
        userArrayList=new ArrayList<>();
        cartArrayList=new ArrayList<>();
        cartArrayListFiler=new ArrayList<>();
        GetDataUsers();

        listViewCart=findViewById(R.id.listviewDonhangchitiet);
        detailCartAdapter=new DetailCartAdapter(this,R.layout.don_hang_chi_tiet,cartArrayListFiler);
        listViewCart.setAdapter(detailCartAdapter);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this,SignInUser.class);
                intent.putExtra("idProductType",1);

                startActivityForResult(intent,REQUEST_CODE_USER);
            }
        });

        btnShowUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HistoryActivity.this,InformationUserActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnShowUser.setVisibility(View.GONE);
                btnExit.setVisibility(View.GONE);


                btndangnhap.setVisibility(View.VISIBLE);
                textViewDonhang.setText("Bạn chưa đăng nhập");
                listViewCart.setVisibility(View.GONE);
            }
        });
    }
    public void GetDataUsers() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                userArrayList.clear();
                if (response != null){

                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            idUser = jsonObject.getInt("id");
                            usernameuser = jsonObject.getString("username");
                            emailUser = jsonObject.getString("email");
                            addressUser = jsonObject.getString("address");
                            userArrayList.add(new User(idUser,usernameuser,emailUser,addressUser));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

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
    public void GetDataCart() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDonHangChiTiet, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cartArrayList.clear();
                cartArrayListFiler.clear();
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            int id=0;
                            int idUser=0;
                            String tensanpham="";
                            int soluong=0;
                            String ngaymuahang="";
                            int giasanpham=0;
                            String diachigiaohang="";

                            String hinhanhsanpham="";
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            idUser = jsonObject.getInt("id_user");
                            tensanpham = jsonObject.getString("tensanpham");
                            soluong = jsonObject.getInt("soluong");
                            ngaymuahang = jsonObject.getString("ngaymuahang");
                            giasanpham = jsonObject.getInt("giasanpham");
                            diachigiaohang = jsonObject.getString("diachigiaohang");
                            hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            cartArrayList.add(new DetailCart(id,idUser,tensanpham,giasanpham,soluong,ngaymuahang,diachigiaohang,hinhanhsanpham));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(HistoryActivity.this, "cartArrayList co size là :"+cartArrayList.size(), Toast.LENGTH_SHORT).show();

                    for (int j=0;j<cartArrayList.size();j++)
                    {

                        if(idUserDangNhap==cartArrayList.get(j).getIdUser())
                        {
                            DetailCart cart=cartArrayList.get(j);
                            cartArrayListFiler.add(new DetailCart(cart.getId(),cart.getIdUser(),cart.getTensanpham(),cart.getGiasanpham(),cart.getSoluong(),cart.getNgaymuahang(),cart.getDiachigiaohang()
                                    ,cart.getHinhanhsanpham()));

                        }

                    }
                    Toast.makeText(HistoryActivity.this, "vpq have :"+cartArrayListFiler.size(), Toast.LENGTH_SHORT).show();
                    int x=0;
                    for(int i=0;i<cartArrayListFiler.size()-1;i++)
                    {
                        x= cartArrayListFiler.get(i).getSoluong();
                        for(int j=cartArrayListFiler.size()-1;j>i;j--)
                        {
                            if(cartArrayListFiler.get(i).getTensanpham().equals(cartArrayListFiler.get(j).getTensanpham()))
                            {
                                x=x+cartArrayListFiler.get(j).getSoluong();
                                cartArrayListFiler.get(i).setSoluong(x);
                                cartArrayListFiler.remove(j);

                            }
                        }
                    }
                    Toast.makeText(HistoryActivity.this, "vpq have :"+cartArrayListFiler.size(), Toast.LENGTH_SHORT).show();
                    detailCartAdapter.notifyDataSetChanged();

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_USER && resultCode == RESULT_OK && data != null) {
            String email = data.getStringExtra("email");
            Log.e("vpq","mang có bao nhieu phan tu"+userArrayList.size());
            for(int i=0;i<userArrayList.size();i++)
            {
                if(email.equals(userArrayList.get(i).getEmail()))
                {
                    user=userArrayList.get(i);
                    idUserDangNhap=userArrayList.get(i).getId();
                    btnShowUser.setText( "Hello: "+userArrayList.get(i).getUsername());
                    btnShowUser.setVisibility(View.VISIBLE);
                    btnExit.setVisibility(View.VISIBLE);
                    btnExit.setText("Thoát");
                    btndangnhap.setVisibility(View.GONE);
                    textViewDonhang.setText("Đơn hàng đã mua");
                    GetDataCart();
                    listViewCart.setVisibility(View.VISIBLE);
                }
            }

        }

    }
}
