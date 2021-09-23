package com.example.reseprestapi.network;

import com.example.reseprestapi.model.ResponseData;
import com.example.reseprestapi.model.ResponseGetAllDataResep;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

//    method menampilkan semua data
    @GET("getdataresep.php")
    Call <ResponseGetAllDataResep> getDataResep();

//    method insert data
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseData> insertData(@Field("nama_resep") String nama,
                                  @Field("detail") String detail,
                                  @Field("gambar") String gambar);

//    method register
    @FormUrlEncoded
    @POST("user_register.php")
    Call<ResponseData> userRegister(@Field("nama") String nama,
                                  @Field("jk") String jk,
                                  @Field("image") String image,
                                  @Field("email") String email,
                                  @Field("username") String username,
                                  @Field("password") String password);

//    method login
    @FormUrlEncoded
    @POST("user_login.php")
    Call<ResponseData> userLogin(@Field("email") String email,
                                 @Field("password") String password);

//    method update data
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseData> updateData( @Field("id_resep") String id,
                                   @Field("nama_resep") String nama,
                                   @Field("detail") String detail,
                                   @Field("gambar") String gambar);

//    delete menggunakan parameter id
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseData> deleteData(@Field("id_resep") String id);

}
