package com.sample.honeybuser.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sample.honeybuser.Models.VendorListModel;
import com.sample.honeybuser.R;

import java.util.ArrayList;

/**
 * Created by Raja M on 10/19/2016.
 */

public class VendorSearchAdapter extends RecyclerView.Adapter<VendorSearchAdapter.CustomHolder> {
    private Context context;
    private ArrayList<Integer> list;

    public VendorSearchAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.list = arrayList;

    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomHolder(LayoutInflater.from(context).inflate(R.layout.vendor_search_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.vendorSearchImageView.setImageResource(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        ImageView vendorSearchImageView;

        public CustomHolder(View itemView) {
            super(itemView);
            vendorSearchImageView = (ImageView) itemView.findViewById(R.id.vendorSearchImageView);
        }
    }
}
