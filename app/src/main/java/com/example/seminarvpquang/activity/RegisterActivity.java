package com.example.seminarvpquang.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.seminarvpquang.R;
import com.example.seminarvpquang.ultil.Server;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    DatabaseReference root_reference;
    private FirebaseUser user;
    private Button register;
    private EditText passwordd, emaill, username, phone, organisation;
    private TextView redirecttosignin;
    private FirebaseAuth auth;
    private ProgressDialog dialog;
    private Toolbar tlbr;
    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register_btn);
        emaill = findViewById(R.id.email_input_reg);
        passwordd = findViewById(R.id.pass_input_reg);
        username = findViewById(R.id.name_input_reg);
        redirecttosignin = findViewById(R.id.already_account);
        phone = findViewById(R.id.phone_input_reg);
        close = findViewById(R.id.close);
        organisation = findViewById(R.id.organization_input);

        tlbr = findViewById(R.id.reg_toolbar);
        setSupportActionBar(tlbr);

        dialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        root_reference = FirebaseDatabase.getInstance().getReference();
        root_reference.keepSynced(true);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        redirecttosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createnewacount();
            }
        });
    }

    private void createnewacount() {
        final String Eemail = emaill.getText().toString();
        String ppass = passwordd.getText().toString();
        final String phhone = phone.getText().toString();
        final String usernaaam = username.getText().toString();
        final String organisationn = organisation.getText().toString();

        if (TextUtils.isEmpty(Eemail) || TextUtils.isEmpty(ppass) || TextUtils.isEmpty(usernaaam) || TextUtils.isEmpty(phhone)) {
            Toast.makeText(RegisterActivity.this, "Th", Toast.LENGTH_SHORT).show();
        } else if (Eemail.contains("@")) {
            dialog.setTitle("Tạo tài khoản mới");
            dialog.setMessage("Đợi trong giây lát");
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();

            auth.createUserWithEmailAndPassword(Eemail, ppass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dangkiBangUser();
                                // making some database for id and password
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                String userid = Objects.requireNonNull(auth.getCurrentUser()).getUid();

                                root_reference = FirebaseDatabase.getInstance().getReference("USERS").child(userid);
                                root_reference.keepSynced(true);
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("id", userid);
                                hashMap.put("username_item", usernaaam);
                                hashMap.put("imageURL", "noimage");
                                hashMap.put("status", "offline");
                                hashMap.put("email", Eemail);
                                hashMap.put("phone", phhone);
                                hashMap.put("organisation", organisationn);


                                root_reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    intent.putExtra("name", usernaaam);

                                                    startActivity(intent);
                                                    Toast.makeText(RegisterActivity.this, "Please check your email for verification ...", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                } else {
                                                    Toast.makeText(RegisterActivity.this, "" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            }
                                        });
                                    }
                                });

                            } else {
                                Toast.makeText(RegisterActivity.this, "" + Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });
        } else {
            Toast.makeText(RegisterActivity.this, "Please Enter Correct Email-ID", Toast.LENGTH_LONG).show();
        }
    }

    public void dangkiBangUser()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.insertUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(RegisterActivity.this, "Thêm User thành công", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "lỗi thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
                        Log.d("aaa","Loi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();

                params.put("email",emaill.getText().toString());
                params.put("diachi",organisation.getText().toString());
                params.put("username",username.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}