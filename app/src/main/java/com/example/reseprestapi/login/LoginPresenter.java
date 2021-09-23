package com.example.reseprestapi.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.reseprestapi.base.Presenter;
import com.example.reseprestapi.model.ResponseData;
import com.example.reseprestapi.network.RestApi;
import com.example.reseprestapi.network.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements Presenter<LoginVIew> {

    LoginVIew loginVIew;

    @Override
    public void onAttach(LoginVIew view) {
        loginVIew = view;
    }

    @Override
    public void onDetach() {
        loginVIew = null;
    }

    //method login
    void Login(final String email, String pass) {
        if (email.isEmpty() || pass.isEmpty()) {
            loginVIew.showMessage("Email atau Password tidak Boleh kosong");

        } else {
            loginVIew.showLoading();
            RestApi api = RetroServer.getClient().create(RestApi.class);
            Call<ResponseData> userRegister = api.userLogin(email, pass);
            userRegister.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    Log.e("TAG", "response " + response.body().getPesan());

                    int kode = response.body().getKode();
                    if (kode == 1) {
                        loginVIew.hideLoading();
                        loginVIew.showSukses(email);
                    } else {
                        Log.e("TAG", "response " + response.body().getPesan());
                        loginVIew.hideLoading();
                        loginVIew.showMessage("Gagal Login");
                    }
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    loginVIew.showMessage("Gagal Login");
                    loginVIew.hideLoading();

                }
            });
        }
    }


}
