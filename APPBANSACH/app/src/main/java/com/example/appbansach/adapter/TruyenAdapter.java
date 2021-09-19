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

public class TruyenAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraytruyen;

    public TruyenAdapter(Context context, ArrayList<Sanpham> arraytruyen) {
        this.context = context;
        this.arraytruyen = arraytruyen;
    }

    @Override
    public int getCount() {
        return arraytruyen.size();
    }

    @Override
    public Object getItem(int i) {
        return arraytruyen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttentruyen, txtgiatruyen, txtmotatruyen;
        public ImageView imgtruyen;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_truyen,null);
            viewHolder.txttentruyen = (TextView) view.findViewById(R.id.textviewtruyen);
            viewHolder.txtgiatruyen = (TextView) view.findViewById(R.id.textviewgiatruyen);
            viewHolder.txtmotatruyen = (TextView) view.findViewById(R.id.textviewmotatruyen);
            viewHolder.imgtruyen = (ImageView) view.findViewById(R.id.imageviewtruyen);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttentruyen.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiatruyen.setText("Gia : " + decimalFormat.format(sanpham.getGiasanpham()) + "D");
        viewHolder.txtmotatruyen.setMaxLines(2);
        viewHolder.txtmotatruyen.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotatruyen.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.ic_no_img_black_24dp)
                .error(R.drawable.cancel)
                .into(viewHolder.imgtruyen);
        return view;
    }
}
