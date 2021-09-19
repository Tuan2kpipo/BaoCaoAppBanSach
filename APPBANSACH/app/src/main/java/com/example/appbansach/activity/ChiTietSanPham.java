package com.example.appbansach.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appbansach.R;
import com.example.appbansach.modell.Giohang;
import com.example.appbansach.modell.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btndatmua;
    int ID = 0;
    String TenChitiet = "";
    int GiaChitiet = 0;
    String HinhanhChitiet = "";
    String MotaChitiet = "";
    int IDsanpham = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CacthEventSpinner();
        EventButton();
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.appbansach.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() > 0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.manggiohang.size(); i++){
                        if (MainActivity.manggiohang.get(i).getIDsp() == ID){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluongsp() >= 10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if (exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * GiaChitiet;
                        MainActivity.manggiohang.add(new Giohang(ID,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                    }

                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * GiaChitiet;
                    MainActivity.manggiohang.add(new Giohang(ID,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), com.example.appbansach.activity.Giohang.class);
                startActivity(intent);
            }
        });
    }

    private void CacthEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayadapter);
    }

    private void GetInformation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        ID = sanpham.getID();
        TenChitiet = sanpham.getTensanpham();
        GiaChitiet = sanpham.getGiasanpham();
        HinhanhChitiet = sanpham.getHinhanhsanpham();
        MotaChitiet = sanpham.getMotasanpham();
        IDsanpham = sanpham.getIDSanpham();
        txtten.setText(TenChitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("gia: " + decimalFormat.format(GiaChitiet) + "D");
        txtmota.setText(MotaChitiet);
        Picasso.get().load(HinhanhChitiet)
                .placeholder(R.drawable.ic_no_img_black_24dp)
                .error(R.drawable.cancel)
                .into(imgChitiet);

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarChitiet = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imgChitiet = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        txtten = (TextView) findViewById(R.id.textviewtenchitietsanpham);
        txtgia = (TextView)findViewById(R.id.textviewgiachitietsanpham);
        txtmota = (TextView)findViewById(R.id.textviewmotachitietsanpham);
        spinner = (Spinner) findViewById(R.id.spinner);
        btndatmua = (Button) findViewById(R.id.buttondatmua);
    }
}