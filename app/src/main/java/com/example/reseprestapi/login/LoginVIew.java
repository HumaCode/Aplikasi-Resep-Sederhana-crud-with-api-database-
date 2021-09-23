package com.example.reseprestapi.login;


import com.example.reseprestapi.base.View;

public interface LoginVIew  extends View {
    void showLoading();
    void hideLoading();
    void showSukses(String email);
    void showMessage(String message);


}
