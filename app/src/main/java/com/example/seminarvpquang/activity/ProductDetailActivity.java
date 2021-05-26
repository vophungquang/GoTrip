package com.example.seminarvpquang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.CommentAdapter;
import com.example.seminarvpquang.model.Product;
import com.example.seminarvpquang.model.Comment;
import com.example.seminarvpquang.model.User;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {

    Toolbar btnToolbar;
    ImageView imageViewProductDetail,imghinhne,imageViewMuiTen,imagegiohang,imgbinhluanUser;
    TextView textViewNameProductDetail,textViewPriceProductDetail,textViewDescriptionProductDetail,txtsoluong,txtdaban,txttonkho,txtdiemdanhgia,textviewChitietsanpham;
    int REQUEST_CODE_USER=123;
    Button buttonAddCartProductDetail,btnTru,btnCong,btndangnhapchitiet,btnShowUser,btnExitUser;
    CommentAdapter commentAdapter;
    ArrayList<Comment> commentArrayList;
    ArrayList<Comment> commentArrayListFilter;
    ArrayList<User> userArrayList;
    ListView listViewComment;
    EditText binhluanUser;
    User user;
    int id = 0;
    String name = "";
    int price = 0;
    String image = "";
    String description = "";
    int idProduct = 0;
    int idthuonghieu=0;
    int sosanphamdaban=0;
    int sosanphamtonkho=0;
    String diemdanhgia="";

    //comment
    int idcm=0;
    String username="";
    String content="";
    String tensanpham="";

    String tensanphambinhluan="";


    //user
    int idUser=0;
    String usernameuser="";
    String emailUser="";
    String addressUser="";


    int idUserDangNhap=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        GetDataUsers();
        GetDataProductDetail();

        imageViewMuiTen.setImageResource(R.drawable.muiten);
        imagegiohang.setImageResource(R.drawable.giohang);
        GetDataComment(tensanphambinhluan);
        Log.e("vpq"," binhluan la:"+tensanphambinhluan+"");



        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soluong=txtsoluong.getText().toString();
                int soluongdadoi=Integer.parseInt(soluong);
                soluongdadoi=soluongdadoi+1;
                txtsoluong.setText(""+soluongdadoi);
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String soluong=txtsoluong.getText().toString();
                int soluongdadoi=Integer.parseInt(soluong);
                if(soluongdadoi!=0) {
                    soluongdadoi = soluongdadoi - 1;
                    txtsoluong.setText("" + soluongdadoi);
                }
            }
        });
        if(btnShowUser.getText().length()!=0) {

            binhluanUser.setEnabled(true);
        }

        btndangnhapchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this,LoginActivity.class);
                intent.putExtra("idProductType",1);

                startActivityForResult(intent,REQUEST_CODE_USER);
            }
        });

        buttonAddCartProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chuoi=    txtsoluong.getText().toString();
                int so=Integer.parseInt(chuoi);
                if(btnShowUser.getText().toString().length()!=0&&btnShowUser.getText().toString()!=null) {
                    if(so==0)
                    {
                        Toast.makeText(ProductDetailActivity.this,"Bạn phải thêm số lượng sản phẩm", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        themGioHang();
                    }

                }
                else{
                    Toast.makeText(ProductDetailActivity.this, "Bạn chưa đăng nhập ,vui lòng đăng nhập", Toast.LENGTH_SHORT).show();

                }
            }
        });
        imagegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnShowUser.getText().toString().length()!=0&&btnShowUser.getText().toString()!=null) {
                    Intent intent = new Intent(ProductDetailActivity.this, CartActicity.class);
                    intent.putExtra("idUser",idUserDangNhap);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ProductDetailActivity.this, "Bạn chưa đăng nhập ,vui lòng đăng nhập", Toast.LENGTH_SHORT).show();

                }
            }
        });

        imageViewMuiTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentUser();
            }
        });

        btnExitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnShowUser.setVisibility(View.GONE);
                btnExitUser.setVisibility(View.GONE);

                binhluanUser.setEnabled(false);
                btndangnhapchitiet.setVisibility(View.VISIBLE);

            }
        });
        btnShowUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductDetailActivity.this,InformationUserActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }

    private void commentUser(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.commentUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(ProductDetailActivity.this, "Comment thành công", Toast.LENGTH_SHORT).show();
                            GetDataComment(tensanphambinhluan);
                            binhluanUser.setText("");
                        }
                        else
                        {
                            Toast.makeText(ProductDetailActivity.this, "Lỗi comment", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductDetailActivity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("id_user",idUserDangNhap+"");
                params.put("idsanpham",id+"");
                params.put("content",binhluanUser.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                params.put("ngaycomment",sdf.format(new Date()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void themGioHang()
    {

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.pathInsertSanPham,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(ProductDetailActivity.this, "Them vào giỏ hang thành công", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(ProductDetailActivity.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductDetailActivity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("id_user",idUserDangNhap+"");
                params.put("idsanpham",id+"");
                params.put("soluong",txtsoluong.getText().toString()+"");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                params.put("ngaymuahang",sdf.format(new Date()));
                params.put("price",price+"");
                params.put("thuonghieu",idthuonghieu+"");


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void GetDataComment(String tensanphamcobinhluan) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathComment, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                commentArrayList.clear();
                commentArrayListFilter.clear();
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            idcm = jsonObject.getInt("id");
                            username = jsonObject.getString("username");
                            tensanpham = jsonObject.getString("tensanpham");
                            content = jsonObject.getString("content");
                            boolean add = commentArrayList.add(new Comment(id, username, tensanpham, content));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    for(int j=0;j<commentArrayList.size();j++)
                    {
                        if(tensanphamcobinhluan.equals(commentArrayList.get(j).getTensanpham())) {
                            int idcme = commentArrayList.get(j).getId();
                            String usernamee = commentArrayList.get(j).getUsername();
                            String tensanphame = commentArrayList.get(j).getTensanpham();
                            String contente = commentArrayList.get(j).getContent();
                            commentArrayListFilter.add(new Comment(idcme, usernamee, tensanphame, contente));
                            commentAdapter.notifyDataSetChanged();
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

    public void GetDataUsers() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathUser,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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

    private void GetDataProductDetail() {

        Product product= (Product) getIntent().getSerializableExtra("information");
        id = product.getId();
        name = product.getNameProduct();
        tensanphambinhluan=name;
        price = product.getPriceProduct();
        image = product.getImageProduct();
        description = product.getDescriptionProduct();
        idProduct = product.getIdProduct();
        sosanphamdaban=product.getSosanphamdaban();
        sosanphamtonkho=product.getSosanphamcontonkho();
        idthuonghieu=product.getId_thuonghieu();
        diemdanhgia=product.getDiemdanhgia();


        textViewNameProductDetail.setText(name);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewPriceProductDetail.setText("Giá : " + decimalFormat.format(price) + " VND ");
        textViewDescriptionProductDetail.setText(description);
        Picasso.get().load(image).into(imageViewProductDetail);

        txtdaban.setText("Số sản phẩm đã bán : "+sosanphamdaban);
        txttonkho.setText("Số sản phẩm tồn kho : "+sosanphamtonkho);
        txtdiemdanhgia.setText(diemdanhgia+"");
        imghinhne.setImageResource(R.drawable.caybut);
        imgbinhluanUser.setImageResource(R.drawable.comment);

    }



    private void init() {
        btnToolbar                  =findViewById(R.id.btnToolbar);
        imageViewProductDetail      =   findViewById(R.id.imageViewProductDetail);
        textViewNameProductDetail =     findViewById(R.id.textViewNameProductDetail);
        textViewPriceProductDetail =    findViewById(R.id.textViewPriceProductDetail);
        textViewDescriptionProductDetail= findViewById(R.id.textViewDescriptionProductDetail);
        buttonAddCartProductDetail  =   findViewById(R.id.buttonAddCart);
        imageViewMuiTen=                findViewById(R.id.imageMuiTen);
        imgbinhluanUser                 =findViewById(R.id.iconbinhluan);
        imagegiohang                    =  findViewById(R.id.imagegiohang);
        btndangnhapchitiet              =findViewById(R.id.btndangnhapchitiet);
        btnShowUser                     =findViewById(R.id.showUserChiTiet);
        binhluanUser                    =findViewById(R.id.binhluanUser);
        txtdaban= findViewById(R.id.textviewSosanphamdaban);
        txttonkho=findViewById(R.id.textviewSosanphamtonkho);
        txtsoluong=findViewById(R.id.textviewSoLuong);
        txtdiemdanhgia=findViewById(R.id.textviewDiemdanhgia);
        imghinhne=findViewById(R.id.imageHinhne);
        btnCong=findViewById(R.id.btnCong);
        btnTru=findViewById(R.id.btnTru);
        listViewComment= findViewById(R.id.listviewComment);
        commentArrayList=new ArrayList<>();
        commentArrayListFilter=new ArrayList<>();
        commentAdapter=new CommentAdapter(this,R.layout.binh_luan,commentArrayListFilter);
        listViewComment.setAdapter(commentAdapter);

        userArrayList=new ArrayList<>();
        btnExitUser=findViewById(R.id.showUserExit);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_USER && resultCode == RESULT_OK && data != null) {
            String email = data.getStringExtra("email");
            Log.e("phong","mang có bao nhieu phan tu"+userArrayList.size());
            for(int i=0;i<userArrayList.size();i++)
            {
                if(email.equals(userArrayList.get(i).getEmail()))
                {
                    user=userArrayList.get(i);
                    idUserDangNhap=userArrayList.get(i).getId();
                    btnShowUser.setText( "Hello: "+userArrayList.get(i).getUsername());
                    btnShowUser.setVisibility(View.VISIBLE);
                    btnExitUser.setVisibility(View.VISIBLE);
                    btnExitUser.setText("Thoát");
                    binhluanUser.setEnabled(true);
                    btndangnhapchitiet.setVisibility(View.GONE);
                    buttonAddCartProductDetail.setEnabled(true);
                    imagegiohang.setEnabled(true);


                }
            }

        }

    }
}
