package com.example.seminarvpquang.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.seminarvpquang.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInUser extends AppCompatActivity {

    private ProgressDialog loading;
    private FirebaseAuth mauth;
    private Button signin;
    private EditText uuser, ppaswrd;
    private TextView newuser, reset;
    private ImageView close;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = findViewById(R.id.signin_btn);
        uuser = findViewById(R.id.email_input_log);
        ppaswrd = findViewById(R.id.pass_input_log);
        newuser = findViewById(R.id.need_account);
        mauth = FirebaseAuth.getInstance();
        reset = findViewById(R.id.reset);
        close = findViewById(R.id.close);

        Toolbar tlbr = findViewById(R.id.toolbar_log);
        setSupportActionBar(tlbr);


        message = getIntent().getStringExtra("name");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInUser.this, ResetPassword.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInUser.this, RegisterActivity.class));
            }
        });
    }

    private void signin() {

        String Email = uuser.getText().toString();
        String pass = ppaswrd.getText().toString();

        loading = new ProgressDialog(this);
        loading.setTitle("Logging In...");
        loading.setMessage("Please Wait .....");


        if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(SignInUser.this, "Thiếu thông tin đăng nhập...", Toast.LENGTH_SHORT).show();
        } else {
            loading.show();

            mauth.signInWithEmailAndPassword(Email, pass).
        addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            Log.d("vpq", "signInWithEmail:success");
            Toast.makeText(SignInUser.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intent.putExtra("namee", message);
//            startActivity(intent);
            intent.putExtra("email", Email);
            loading.dismiss();
            setResult(RESULT_OK,intent);
            finish();

        } else {
            Toast.makeText(SignInUser.this, "Lỗi ĐĂNG NHẬP", Toast.LENGTH_SHORT).show();

        }

        // ...
    }
});
        }
    }
}
