package com.example.koki.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerMenuAdapter extends BaseAdapter {

    private Context context;
    private int [] iconInts;
    private String [] titleString;

    public DrawerMenuAdapter(Context context,
                             int[] iconInts,
                             String[] titleString) {
        this.context = context;
        this.iconInts = iconInts;
        this.titleString = titleString;
    }

    @Override
    public int getCount() {
        return titleString.length;
    }//นับว่ามีกี่อัน

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_meny, parent, false);

        ImageView imageView = view.findViewById(R.id.imvIcon);
        imageView.setImageResource(iconInts[position]);

        TextView textView=view.findViewById(R.id.txtTitle);
        textView.setText(titleString[position]);


        return view;
    }
}
