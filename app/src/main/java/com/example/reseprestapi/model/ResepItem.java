package com.example.reseprestapi.model;

import com.google.gson.annotations.SerializedName;

public class ResepItem{

	@SerializedName("nama_resep")
	private String namaResep;

	@SerializedName("id_resep")
	private String idResep;

	@SerializedName("detail")
	private String detail;

	@SerializedName("gambar")
	private String gambar;

	public String getNamaResep(){
		return namaResep;
	}

	public String getIdResep(){
		return idResep;
	}

	public String getDetail(){
		return detail;
	}

	public String getGambar(){
		return gambar;
	}
}