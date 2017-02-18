package com.cyclic_order.amarshohor.SlidingMenu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyclic_order.amarshohor.R;
import com.cyclic_order.amarshohor.model.ItemSlideMenu;

import java.util.List;

/**
 * Created by cyclic_order on 1/14/2016.
 */
public class SlidingMenuAdapter extends BaseAdapter {
    private Context context;
    List<ItemSlideMenu>lstitem;

    public SlidingMenuAdapter(Context context, List<ItemSlideMenu> lstitem) {
        this.context = context;
        this.lstitem = lstitem;
    }

    public SlidingMenuAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return lstitem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context, R.layout.drawer_list_item,null);
        ImageView imageView=(ImageView)v.findViewById(R.id.icon);
        TextView textView=(TextView)v.findViewById(R.id.item_title);
        ItemSlideMenu item=lstitem.get(position);
        imageView.setImageResource(item.getImgid());
        textView.setText(item.getTitle());
        return  v;
    }
}
