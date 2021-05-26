package com.example.seminarvpquang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.ProductAdapter;
import com.example.seminarvpquang.adapter.ProductTypeAdapter;
import com.example.seminarvpquang.adapter.ProductTypeAdapter1;
import com.example.seminarvpquang.model.Product;
import com.example.seminarvpquang.model.ProductType;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ListView listViewProduct;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<ProductType> arrayListProductType;
    ProductTypeAdapter productTypeAdapter;

    ArrayList<ProductType> arrayListProductType1;
    ProductTypeAdapter1 productTypeAdapter1;

    int id = 0;
    String namePT = "";
    String imagePT = "";

    ArrayList<Product> arrayListProduct;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AnhXa();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBart();
            ActionViewFliper();
            GetDataProductType();
            GetDataProductNew();
            OnClickItemListView();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
            finish();
        }



    }
    private void OnClickItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,SmartPhoneActivity.class);
                            intent.putExtra("idProductType",1);

                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,LaptopActivity.class);
                            intent.putExtra("idProductType",arrayListProductType.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,ShirtActivity.class);
                            intent.putExtra("idProductType",arrayListProductType.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,TrousersActivity.class);
                            intent.putExtra("idProductType",arrayListProductType.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,ShoesActivity.class);
                            intent.putExtra("idProductType",arrayListProductType.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,WatchActivity.class);
                            intent.putExtra("idProductType",arrayListProductType.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 7:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,InformationTeam.class);
                            intent.putExtra("idProductType",arrayListProductType.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 8:
                        if (CheckConnection.haveNetworkConnection(getApplication())){
                            Intent intent = new Intent(HomeActivity.this,HistoryActivity.class);

                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    public void GetDataProductNew() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathNew,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int Id = 0;
                    String nameProduct = "";
                    Integer priceProduct = 0;
                    String imageProduct = "";
                    String descriptionProduct = "";
                    int IdProduct = 0;
                    int idthuonghieu=0;
                    int sosanphamdaban=0;
                    int sosanphamtonkho=0;
                    String diemdanhgia="";
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("id");
                            nameProduct = jsonObject.getString("tensp");
                            priceProduct = jsonObject.getInt("giasp");
                            imageProduct = jsonObject.getString("hinhanhsp");
                            descriptionProduct = jsonObject.getString("motasp");
                            IdProduct = jsonObject.getInt("idsanpham");
                            idthuonghieu=jsonObject.getInt("id_thuonghieu");
                            sosanphamtonkho=jsonObject.getInt("sosanphamtonkho");
                            sosanphamdaban=jsonObject.getInt("sosanphamdaban");
                            diemdanhgia=jsonObject.getString("diemdanhgia");
                            arrayListProduct.add(new Product(Id,nameProduct,priceProduct,
                                    imageProduct,descriptionProduct,IdProduct,idthuonghieu,sosanphamdaban,sosanphamtonkho,diemdanhgia));
                            productAdapter.notifyDataSetChanged();
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

    public void GetDataProductType() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.path,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            namePT = jsonObject.getString("tenloaisp");
                            imagePT = jsonObject.getString("hinhanhloaisanpham");
                            arrayListProductType.add(new ProductType(id,namePT,imagePT));
                            productTypeAdapter.notifyDataSetChanged();

                            arrayListProductType1.add(new ProductType(id,namePT,imagePT));
                            productTypeAdapter1.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    arrayListProductType.add(new ProductType(7,"Liên Hệ Shop","https://cdn1.iconfinder.com/data/icons/mix-color-3/502/Untitled-12-512.png"));
                    arrayListProductType.add(new ProductType(8,"Lịch Sử Mua Hàng","http://icons.iconarchive.com/icons/graphicloads/colorful-long-shadow/128/User-group-icon.png"));
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

    private void ActionViewFliper() {
        ArrayList<String> mangquangcao=new ArrayList<>();
        mangquangcao.add("https://huesmiletravel.com.vn/wp-content/uploads/2019/11/vinh-ha-long-1.jpg");
        mangquangcao.add("http://divui.com/blog/wp-content/uploads/2018/10/111111.jpg");
        mangquangcao.add("https://huesmiletravel.com.vn/wp-content/uploads/2019/11/cau-the-huc-1.jpg");
        mangquangcao.add("https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/07/1.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/thanh-pho-du-lich-dep-nhat-viet-nam-ma-ban-nen-den-mot-lan-trong-doi-304636.jpg");
        mangquangcao.add("https://h3jd9zjnmsobj.vcdn.cloud/public/mytravelmap/images/2018/6/1/knigh.gody/2b70d1e0e5f1dc83ebb17b6c5c49e6d0.jpg");

        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView=new ImageView((getApplicationContext()));
//            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
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

    private  void AnhXa(){
        recyclerView=findViewById(R.id.listviewTheLoai);
        toolbar=findViewById(R.id.btnShowUser);
        viewFlipper=findViewById(R.id.viewlipper);
        listViewProduct=findViewById(R.id.ListviewProduct);
        navigationView=findViewById(R.id.navigationview);
        listViewmanhinhchinh=findViewById(R.id.listviewManhinhchinh);
        drawerLayout=findViewById(R.id.drawerlayout);
        arrayListProductType=new ArrayList<>();
        arrayListProductType.add(0, new ProductType(0, "Trang Chủ","https://tse4.mm.bing.net/th?id=OIP.JCCq1sFqS_2xfar05oek_gHaHa&pid=Api&P=0&w=300&h=300"));
        productTypeAdapter=new ProductTypeAdapter(arrayListProductType,getApplicationContext());
        listViewmanhinhchinh.setAdapter(productTypeAdapter);
        arrayListProduct = new ArrayList<Product>();
        productAdapter = new ProductAdapter(this,R.layout.item_product_new,arrayListProduct);

        listViewProduct.setAdapter(productAdapter);

        arrayListProductType1=new ArrayList<>();

        productTypeAdapter1=new ProductTypeAdapter1(arrayListProductType1,this);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.HORIZONTAL);

        DividerItemDecoration dividerItemDecoration1=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);

        Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_mau);


        dividerItemDecoration.setDrawable(drawable);


        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productTypeAdapter1);
    }
}
