package com.example.reseprestapi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetAllDataResep{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private String sukses;

	@SerializedName("resep")
	private List<ResepItem> resep;

	public String getPesan(){
		return pesan;
	}

	public String getSukses(){
		return sukses;
	}

	public List<ResepItem> getResep(){
		return resep;
	}
}