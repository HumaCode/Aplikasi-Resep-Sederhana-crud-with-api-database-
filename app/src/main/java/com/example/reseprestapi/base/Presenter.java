package com.example.reseprestapi.base;

public interface Presenter <T extends View> {
    void onAttach(T view);
    void onDetach();
}
