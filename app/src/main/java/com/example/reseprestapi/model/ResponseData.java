package com.example.reseprestapi.model;

import com.google.gson.annotations.SerializedName;

public class ResponseData{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("kode")
	private int kode;

	public String getPesan(){
		return pesan;
	}

	public int getKode(){
		return kode;
	}
}