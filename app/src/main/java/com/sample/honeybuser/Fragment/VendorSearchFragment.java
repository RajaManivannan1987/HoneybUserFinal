package com.sample.honeybuser.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.honeybuser.Adapter.VendorSearchAdapter;
import com.sample.honeybuser.Models.VendorListModel;
import com.sample.honeybuser.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Im033 on 10/18/2016.
 */

public class VendorSearchFragment extends Fragment {
    private RecyclerView threeKmVendorRecyclerView;
    private ArrayList<Integer> vendorListModel = new ArrayList<>();
    private VendorSearchAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_search, container, false);
        threeKmVendorRecyclerView = (RecyclerView) view.findViewById(R.id.threeKmVendorRecyclerView);
        threeKmVendorRecyclerView.setHasFixedSize(true);
        threeKmVendorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);
        vendorListModel.add(R.drawable.logo);

        adapter = new VendorSearchAdapter(getActivity(), vendorListModel);
        threeKmVendorRecyclerView.setAdapter(adapter);
        return view;
    }

}
