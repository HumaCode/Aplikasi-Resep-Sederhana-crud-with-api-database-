package com.example.reseprestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class AddResepActivity extends AppCompatActivity implements View.OnClickListener{


    ProgressBar pd;
    Button btnInsertdata;
    Button btntampildata;
    Button btnUpdate;
    Button btnhapus;
    EditText edtNama;
    EditText edtGambar;
    EditText edtResep;
    String idData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resep);

        btnhapus = findViewById(R.id.btnhapus);
        btnInsertdata = findViewById(R.id.btn_insertdata);
        btntampildata = findViewById(R.id.btntampildata);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtGambar = findViewById(R.id.edt_gambar);
        edtNama = findViewById(R.id.edt_nama);
        edtResep = findViewById(R.id.edtResep);
        pd = findViewById(R.id.pd);
        Intent getData = getIntent();
        idData = getData.getStringExtra("ID");

        if (idData != null) {
            btnInsertdata.setVisibility(View.GONE);
            btntampildata.setVisibility(View.GONE);
            btnhapus.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);
            edtNama.setText(getData.getStringExtra("NAMA"));
            edtGambar.setText(getData.getStringExtra("GAMBAR"));
            edtResep.setText(getData.getStringExtra("DETAIL"));

        }

        btnUpdate.setOnClickListener(this);
        btntampildata.setOnClickListener(this);
        btnInsertdata.setOnClickListener(this);
        btnhapus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            tombol update
            case R.id.btnUpdate:

                RestApi apiUpdate = RetroServer.getClient().create(RestApi.class);
                Call<ResponseData> updateData =
                        apiUpdate.updateData(idData,
                                edtNama.getText().toString(),
                                edtGambar.getText().toString(),
                                edtResep.getText().toString());

                updateData.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                        startActivity(new Intent(AddResepActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Toast.makeText(AddResepActivity.this, "gagal update"+t.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("", "onFailure: "+t.toString());
                    }
                });


                break;


//            tombol menampilkan data
            case R.id.btntampildata:
                startActivity(new Intent(this, MainActivity.class));
                break;


//            tombol hapus
            case R.id.btnhapus:
                RestApi apidata = RetroServer.getClient().create(RestApi.class);
                Call<ResponseData> del  = apidata.deleteData(idData);
                del.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        Toast.makeText(AddResepActivity.this, "Berhasil Hapus", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(AddResepActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Toast.makeText(AddResepActivity.this, "Gagal Hapus", Toast.LENGTH_SHORT).show();
                    }
                });
                break;


//            tombol insert
            case R.id.btn_insertdata:
                String snama = edtNama.getText().toString();
                String sgambar = edtGambar.getText().toString();
                String sresep = edtResep.getText().toString();

                if (snama.isEmpty() ) {
                    edtNama.setError("Nama Harus Diisi");
                }else if (sgambar.isEmpty()){
                    edtGambar.setError("Gambar Harus Diisi");}
                else if (sresep.isEmpty()){
                    edtResep.setError("Detail Resep Harus Diisi");
                } else {
                    pd.setVisibility(View.VISIBLE);
                    RestApi api = RetroServer.getClient().create(RestApi.class);
                    Call<ResponseData> insertResep = api.insertData(snama, sresep, sgambar);
                    insertResep.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            int kode = response.body().getKode();
                            if (String.valueOf(kode).equals("1")){
                                pd.setVisibility(View.GONE);
                                Toast.makeText(AddResepActivity.this, "berhasil simpan", Toast.LENGTH_SHORT).show();
                                edtGambar.setText("");
                                edtNama.setText("");
                                edtResep.setText("");
                                startActivity(new Intent(AddResepActivity.this, MainActivity.class));
                                finish();
                            }else {
                                pd.setVisibility(View.GONE);
                                Toast.makeText(AddResepActivity.this, "data error, gagal simpan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
                            pd.setVisibility(View.GONE);

                            Toast.makeText(AddResepActivity.this, "data error, gagal simpan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
        }
    }
}