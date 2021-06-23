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

import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.adapter.PlaceAdapter;
import com.example.seminarvpquang.adapter.PlaceTypeAdapter;
import com.example.seminarvpquang.adapter.PlaceTypeAdapter1;
import com.example.seminarvpquang.model.Place;
import com.example.seminarvpquang.model.PlaceType;
import com.example.seminarvpquang.ultil.CheckConnection;
import com.example.seminarvpquang.ultil.GoTrip;
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
    ListView listViewPlace;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<PlaceType> arrayListPlaceType;
    PlaceTypeAdapter placeTypeAdapter;

    ArrayList<PlaceType> arrayListPlaceType1;
    PlaceTypeAdapter1 placeTypeAdapter1;

    int id = 0;
    String namePlace = "";
    String imagePlace = "";

    ArrayList<Place> arrayListPlace;
    PlaceAdapter placeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AnhXa();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBart();
            ActionViewFliper();
            GetDatapPlaceType();
            GetDataPlaceNew();
            OnClickItemListView();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
            finish();
        }



    }
    private void OnClickItemListView() {
        listViewmanhinhchinh.setOnItemClickListener((parent, view, position, id) -> {
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
                        Intent intent = new Intent(HomeActivity.this, SaiGonActivity.class);
                        intent.putExtra("idPlaceType",1);

                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 2:
                    if (CheckConnection.haveNetworkConnection(getApplication())){
                        Intent intent = new Intent(HomeActivity.this, HaNoiActivity.class);
                        intent.putExtra("idPlaceType", 2);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 3:
                    if (CheckConnection.haveNetworkConnection(getApplication())){
                        Intent intent = new Intent(HomeActivity.this, DaLatActivity.class);
                        intent.putExtra("idPlaceType", 3);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 4:
                    if (CheckConnection.haveNetworkConnection(getApplication())){
                        Intent intent = new Intent(HomeActivity.this, HueActivity.class);
                        intent.putExtra("idPlaceType", 4);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 5:
                    if (CheckConnection.haveNetworkConnection(getApplication())){
                        Intent intent = new Intent(HomeActivity.this, SaPaActivity.class);
                        intent.putExtra("idPlaceType", 5);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 6:
                    if (CheckConnection.haveNetworkConnection(getApplication())){
                        Intent intent = new Intent(HomeActivity.this, DaNangActivity.class);
                        intent.putExtra("idPlaceType", 6);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case 7:
                    if (CheckConnection.haveNetworkConnection(getApplication())){
                        Intent intent = new Intent(HomeActivity.this, AboutUs.class);
                        intent.putExtra("idPlaceType", 7);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 8:
                    if (CheckConnection.haveNetworkConnection(getApplication())){
                        Intent intent = new Intent(HomeActivity.this, FavouriteActivity.class);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Please check the connection again!");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
        });
    }

    public void GetDataPlaceNew() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(GoTrip.pathNew,
                response -> {
                    if (response != null){
                        int Id = 0;
                        String namePlace = "";
                        String imagePlace = "";
                        String descriptionPlace = "";
                        int idPlace = 0;
                        int doanhthu=0;
                        String diemdanhgia="";
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Id = jsonObject.getInt("id");
                                namePlace = jsonObject.getString("tendiadiem");
                                imagePlace = jsonObject.getString("hinhanhdiadiem");
                                descriptionPlace = jsonObject.getString("motadiadiem");
                                idPlace = jsonObject.getInt("iddiadiem");
                                doanhthu=jsonObject.getInt("doanhthu");
                                diemdanhgia=jsonObject.getString("diemdanhgia");
                                arrayListPlace.add(new Place(Id,namePlace,imagePlace,descriptionPlace,idPlace,doanhthu,diemdanhgia));
                                placeAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, error -> CheckConnection.ShowToast_Short(getApplicationContext(), error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    public void GetDatapPlaceType() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(GoTrip.path,
                response -> {
                    if (response != null){
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                id = jsonObject.getInt("id");
                                namePlace = jsonObject.getString("tenloaidiadiem");
                                imagePlace = jsonObject.getString("hinhloaidiadiem");
                                arrayListPlaceType.add(new PlaceType(id,namePlace,imagePlace));
                                placeTypeAdapter.notifyDataSetChanged();
                                arrayListPlaceType1.add(new PlaceType(id,namePlace,imagePlace));
                                placeTypeAdapter1.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        arrayListPlaceType.add(new PlaceType(7,"About Us","https://icon-library.com/images/about-us-icon-png/about-us-icon-png-14.jpg"));
                        arrayListPlaceType.add(new PlaceType(8,"Favourite Choice","https://png.pngtree.com/png-clipart/20190924/original/pngtree-favorite--icon-in-trendy-style-isolated-background-png-image_4859837.jpg"));
                    }
                }, error -> CheckConnection.ShowToast_Short(getApplicationContext(), error.toString()));
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
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
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
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private  void AnhXa(){
        recyclerView=findViewById(R.id.listviewTheLoai);
        toolbar=findViewById(R.id.btnShowUser);
        viewFlipper=findViewById(R.id.viewlipper);
        listViewPlace=findViewById(R.id.listViewPlace);
        navigationView=findViewById(R.id.navigationview);
        listViewmanhinhchinh=findViewById(R.id.listviewManhinhchinh);
        drawerLayout=findViewById(R.id.drawerlayout);
        arrayListPlaceType =new ArrayList<>();
        arrayListPlaceType.add(0, new PlaceType(0, "Trang Chủ","http://ksit.com.vn/wp-content/uploads/2017/07/Home-icon.png"));
        placeTypeAdapter =new PlaceTypeAdapter(arrayListPlaceType,getApplicationContext());
        listViewmanhinhchinh.setAdapter(placeTypeAdapter);
        arrayListPlace = new ArrayList<Place>();
        placeAdapter = new PlaceAdapter(this,R.layout.item_place_new, arrayListPlace);

        listViewPlace.setAdapter(placeAdapter);

        arrayListPlaceType1 =new ArrayList<>();

        placeTypeAdapter1 =new PlaceTypeAdapter1(arrayListPlaceType1,this);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.HORIZONTAL);
//        DividerItemDecoration dividerItemDecoration1=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_mau);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(placeTypeAdapter1);
    }
}
