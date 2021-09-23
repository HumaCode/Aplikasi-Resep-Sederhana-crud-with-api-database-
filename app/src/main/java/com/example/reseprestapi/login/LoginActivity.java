package com.example.reseprestapi.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reseprestapi.MainActivity;
import com.example.reseprestapi.R;
import com.example.reseprestapi.RegisterActivity;
import com.example.reseprestapi.model.ResponseData;
import com.example.reseprestapi.network.RestApi;
import com.example.reseprestapi.network.RetroServer;
import com.example.reseprestapi.pref.SessionPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginVIew{


    EditText edtEmail, edtPass;
    Button btnLogin;
    TextView tvRegister;
    ProgressBar pbLogin;

//    session adapter / preff
    SessionPref sessionPref;

//    buat object loginPresenter
    LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        panggil constructornya
        sessionPref = new SessionPref(LoginActivity.this);
        sessionPref.checkLogin();

//        inisialisasi loginPresenter
        loginPresenter = new LoginPresenter();

//        inisialisasi
        edtEmail = findViewById(R.id.edt_email_login);
        edtPass = findViewById(R.id.edt_pass_login);
        btnLogin = findViewById(R.id.login);
        tvRegister = findViewById(R.id.tv_register_now);
        pbLogin = findViewById(R.id.pb_login);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        onAttachView();
    }



    @Override
    public void onAttachView() {
        loginPresenter.onAttach(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        ambil data dari inputan
                final String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();

                loginPresenter.Login(
                    email, pass
                );
            }
        });
    }

    @Override
    public void onDetachView() {
        loginPresenter.onDetach();
    }

    @Override
    public void showLoading() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void showSukses(String email) {
        pbLogin.setVisibility(View.GONE);
        finish();

//      jika berhasil login buat sessionya
        sessionPref.createLoginSession(email);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

    }
}