package com.example.wall.invoicetest.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.wall.invoicetest.R;
import com.example.wall.invoicetest.ui.InvoiceTestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wall on 2018/1/31.
 */
public class Ad_RV_main extends RecyclerView.Adapter<Ad_RV_main.MianViewHolder>{
    private Context context;
    private Typeface typeface;
    private List<String> iconList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    public Ad_RV_main(){}
    public Ad_RV_main(Context context,List<String> iconList,List<String> titleList){
        this.context = context;
        this.iconList = iconList;
        this.titleList = titleList;
        typeface = Typeface.createFromAsset(context.getAssets(),"iconfont/iconfont.ttf");
    }

    @Override
    public MianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_mian,parent,false);
        MianViewHolder vh = new MianViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MianViewHolder holder, final int position) {
        holder.tv_title.setText(titleList.get(position));
        holder.tv_icon.setText(iconList.get(position));
        holder.tv_icon.setTypeface(typeface);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent invoiceInt = new Intent(context, InvoiceTestActivity.class);
                        context.startActivity(invoiceInt);
                        break;
                    case 1:

                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return iconList.size();
    }

    public class MianViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_icon;
        private LinearLayout linearLayout;
        public MianViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_mian_title);
            tv_icon = (TextView) itemView.findViewById(R.id.tv_item_main_icon);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_main);
        }
    }
}
