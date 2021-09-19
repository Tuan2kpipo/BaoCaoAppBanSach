package com.example.appbansach.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbansach.R;
import com.example.appbansach.modell.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TieuThuyetAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraytieuthuyet;

    public TieuThuyetAdapter(Context context, ArrayList<Sanpham> arraytieuthuyet) {
        this.context = context;
        this.arraytieuthuyet = arraytieuthuyet;
    }

    @Override
    public int getCount() {
        return arraytieuthuyet.size();
    }

    @Override
    public Object getItem(int i) {
        return arraytieuthuyet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttentieuthuyet, txtgiatieuthuyet, txtmotatieuthuyet;
        public ImageView imgtieuthuyet;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TieuThuyetAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_tieuthuyet,null);
            viewHolder.txttentieuthuyet = (TextView) view.findViewById(R.id.textviewtentieuthuyet);
            viewHolder.txtgiatieuthuyet = (TextView) view.findViewById(R.id.textviewgiatieuthuyet);
            viewHolder.txtmotatieuthuyet = (TextView) view.findViewById(R.id.textviewmotatieuthuyet);
            viewHolder.imgtieuthuyet = (ImageView) view.findViewById(R.id.imageviewtieuthuyet);
            view.setTag(viewHolder);
        }else {
            viewHolder = (TieuThuyetAdapter.ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttentieuthuyet.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiatieuthuyet.setText("Gia : " + decimalFormat.format(sanpham.getGiasanpham()) + "D");
        viewHolder.txtmotatieuthuyet.setMaxLines(2);
        viewHolder.txtmotatieuthuyet.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotatieuthuyet.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.ic_no_img_black_24dp)
                .error(R.drawable.cancel)
                .into(viewHolder.imgtieuthuyet);
        return view;
    }
}
