package com.example.reseprestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.reseprestapi.model.ResponseData;
import com.example.reseprestapi.network.RestApi;
import com.example.reseprestapi.network.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText nama, jk, email, password, image, username;
    Button register;
    ProgressBar pbRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = findViewById(R.id.edt_nama);
        jk = findViewById(R.id.edt_jk);
        username = findViewById(R.id.edt_username);
        email = findViewById(R.id.edt_email);
        image = findViewById(R.id.edt_image);
        password = findViewById(R.id.edt_password);
        register = findViewById(R.id.btn_register);
        pbRegister = findViewById(R.id.pb_register);

        register();

    }

    private void register() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueNama = nama.getText().toString();
                String valueJk = jk.getText().toString();
                String valueUsername = username.getText().toString();
                String valueImage = image.getText().toString();
                String valueEmail = email.getText().toString();
                String valuePassword = password.getText().toString();

//                validasi
                if(valueNama.isEmpty() ||
                        valueEmail.isEmpty() ||
                        valueJk.isEmpty() ||
                        valueUsername.isEmpty() ||
                        valueImage.isEmpty() ||
                        valuePassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Data Tidak Boleh Kosong",
                            Toast.LENGTH_SHORT).show();
                }else{
                    pbRegister.setVisibility(View.VISIBLE);
                    RestApi api = RetroServer.getClient().create(RestApi.class);
                    Call<ResponseData> userRegister = api.userRegister(
                            valueNama, valueJk, valueImage, valueEmail, valueUsername, valuePassword
                    );

                    userRegister.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            int kode = response.body().getKode();

                            if(kode == 1){
                                pbRegister.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "Register Berhasil",
                                        Toast.LENGTH_SHORT).show();
//                                jika berhasil akan diarahkan ke halaman login
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }else{
                                pbRegister.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "Register Gagal",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
                            pbRegister.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Register Gagal",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}