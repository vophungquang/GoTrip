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
import com.example.seminarvpquang.model.Comment;
import com.example.seminarvpquang.model.User;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.GoTrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlaceDetailActivity extends AppCompatActivity {

    Toolbar btnToolbar;
    ImageView imageViewPlaceDetail,imghinhne,imageViewMuiTen,imgbinhluanUser;
    TextView textViewNamePlaceDetail,textViewDescriptionPlaceDetail,txtdiemdanhgia;
    int REQUEST_CODE_USER=123;
    Button buttonShowMap,btndangnhapchitiet,btnShowUser,btnExitUser;
    CommentAdapter commentAdapter;
    ArrayList<Comment> commentArrayList;
    ArrayList<Comment> commentArrayListFilter;
    ArrayList<User> userArrayList;
    ListView listViewComment;
    EditText binhluanUser;
    User user;
    int id = 0;

    //comment
    int idcm=0;
    String username="";
    String content="";
    String tendiadiem="";

    String tendiadiembinhluan="";


    //user
    int idUser=0;
    String usernameuser="";
    String emailUser="";
    String addressUser="";


    int idUserDangNhap=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        init();
        GetDataUsers();

        imageViewMuiTen.setImageResource(R.drawable.muiten);
        imghinhne.setImageResource(R.drawable.caybut);
        imgbinhluanUser.setImageResource(R.drawable.comment);
        GetDataComment(tendiadiembinhluan);
        Log.e("vpq"," binhluan la:"+tendiadiembinhluan+"");
        if(btnShowUser.getText().length()!=0) {
            binhluanUser.setEnabled(true);
        }
        buttonShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceDetailActivity.this,GgMapsActivity.class);
                startActivity(intent);
            }
        });

        btndangnhapchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceDetailActivity.this,SignInUser.class);
                intent.putExtra("idPlaceType",1);
                startActivityForResult(intent,REQUEST_CODE_USER);
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
                Intent intent=new Intent(PlaceDetailActivity.this,InformationUserActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }

    private void commentUser(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, GoTrip.commentUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(PlaceDetailActivity.this, "Comment thành công", Toast.LENGTH_SHORT).show();
                            GetDataComment(tendiadiembinhluan);
                            binhluanUser.setText("");
                        }
                        else
                        {
                            Toast.makeText(PlaceDetailActivity.this, "Lỗi comment", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceDetailActivity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("id_user",idUserDangNhap+"");
                params.put("id_diadiem",id+"");
                params.put("content",binhluanUser.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                params.put("ngaycomment",sdf.format(new Date()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void GetDataComment(String tendiadiemcobinhluan) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(GoTrip.pathComment, new Response.Listener<JSONArray>() {
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
                            tendiadiem = jsonObject.getString("tendiadiem");
                            content = jsonObject.getString("content");
                            boolean add = commentArrayList.add(new Comment(id, username, tendiadiem, content));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    for(int j=0;j<commentArrayList.size();j++)
                    {
                        if(tendiadiemcobinhluan.equals(commentArrayList.get(j).gettendiadiem())) {
                            int idcme = commentArrayList.get(j).getId();
                            String usernamee = commentArrayList.get(j).getUsername();
                            String tendiadieme = commentArrayList.get(j).gettendiadiem();
                            String contente = commentArrayList.get(j).getContent();
                            commentArrayListFilter.add(new Comment(idcme, usernamee, tendiadieme, contente));
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
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(GoTrip.pathUser,
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




    private void init() {
        btnToolbar                  =findViewById(R.id.btnToolbar);
        imageViewPlaceDetail      =   findViewById(R.id.imageViewPlaceDetail);
        textViewNamePlaceDetail =     findViewById(R.id.textViewNamePlaceDetail);
        textViewDescriptionPlaceDetail= findViewById(R.id.textViewDescriptionPlaceDetail);
        buttonShowMap  =   findViewById(R.id.buttonshowMap);
        imageViewMuiTen=                findViewById(R.id.imageMuiTen);
        imgbinhluanUser                 =findViewById(R.id.iconbinhluan);
        btndangnhapchitiet              =findViewById(R.id.btndangnhapchitiet);
        btnShowUser                     =findViewById(R.id.showUserChiTiet);
        binhluanUser                    =findViewById(R.id.binhluanUser);
        txtdiemdanhgia=findViewById(R.id.textviewDiemdanhgia);
        imghinhne=findViewById(R.id.imageHinhne);
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
            Log.e("vpq","mang có bao nhieu phan tu"+userArrayList.size());
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
                    buttonShowMap.setEnabled(true);
                }
            }

        }

    }
}
