package com.example.wall.invoicetest.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wall.invoicetest.R;

import java.util.List;

/**
 * Created by Wall on 2018/1/31.
 */
public class Ad_List_mainDrawer extends BaseAdapter{
    private Context context;
    private List<String> icon;
    private List<String> titleData;
    private Typeface typeface;

    private Ad_List_mainDrawer(){}
    public Ad_List_mainDrawer(Context context, List<String> icon, List<String> titleData){
        this.context = context;
        this.icon = icon;
        this.titleData = titleData;
        typeface = Typeface.createFromAsset(context.getAssets(),"iconfont/iconfont.ttf");
    }

    @Override
    public int getCount() {
        return titleData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_maindrawer,null);
            myViewHolder = new MyViewHolder();
            myViewHolder.iconTextView = (TextView) convertView.findViewById(R.id.tv_main_drawer_icon);
            myViewHolder.titleTextView = (TextView) convertView.findViewById(R.id.tv_main_drawer_text);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.iconTextView.setText(icon.get(position));
        myViewHolder.titleTextView.setText(titleData.get(position));
        myViewHolder.iconTextView.setTypeface(typeface);
        return convertView;
    }
    public class MyViewHolder{
        private TextView iconTextView;
        private TextView titleTextView;
    }

}
