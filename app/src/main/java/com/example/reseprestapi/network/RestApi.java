package com.example.reseprestapi.network;

import com.example.reseprestapi.model.ResponseData;
import com.example.reseprestapi.model.ResponseGetAllDataResep;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @GET("getdataresep.php")
    Call <ResponseGetAllDataResep> getDataResep();

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseData> insertData(@Field("nama_resep") String nama,
                                  @Field("detail") String detail,
                                  @Field("gambar") String gambar);

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseData> updateData( @Field("id_resep") String id,
                                   @Field("nama_resep") String nama,
                                   @Field("detail") String detail,
                                   @Field("gambar") String gambar);
    //delete menggunakan parameter id
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseData> deleteData(@Field("id_resep") String id);

}
