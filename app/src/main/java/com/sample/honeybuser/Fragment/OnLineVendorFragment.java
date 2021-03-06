package com.sample.honeybuser.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sample.honeybuser.Adapter.OnLineVendorListAdapter;
import com.sample.honeybuser.Application.MyApplication;
import com.sample.honeybuser.InterFaceClass.ActionCompleted;
import com.sample.honeybuser.InterFaceClass.SaveCompletedInterface;
import com.sample.honeybuser.InterFaceClass.VolleyResponseListerner;
import com.sample.honeybuser.Models.OnLineVendorListModel;
import com.sample.honeybuser.R;
import com.sample.honeybuser.Singleton.ActionCompletedSingleton;
import com.sample.honeybuser.Singleton.Complete;
import com.sample.honeybuser.Utility.Fonts.CommonUtilityClass.CommonMethods;
import com.sample.honeybuser.Utility.Fonts.Sharedpreferences.Session;
import com.sample.honeybuser.Utility.Fonts.WebServices.GetResponseFromServer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IM0033 on 10/6/2016.
 */
public class OnLineVendorFragment extends Fragment {
    private String TAG = "OnLineVendorFragment";
    private RecyclerView recyclerView;
    private OnLineVendorListAdapter adapter;
    private Gson gson = new Gson();
    private Session session;
    private List<OnLineVendorListModel> list = new ArrayList<OnLineVendorListModel>();
    private String lat, lang;
    private Bundle bundle;
    private TextView txtView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_map, container, false);
        bundle = this.getArguments();
        if (bundle != null) {
            lat = bundle.getString("lat");
            lang = bundle.getString("lang");
        }
        txtView = (TextView) view.findViewById(R.id.noRecordTextView);
        recyclerView = (RecyclerView) view.findViewById(R.id.onLineVendorList);
        session = new Session(getActivity(), TAG);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OnLineVendorListAdapter(getActivity(), list, "1");
        recyclerView.setAdapter(adapter);
        Complete.offerDialogInstance().setListener(new SaveCompletedInterface() {
            @Override
            public void completed() {
                getData();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        getData();
        super.onResume();

    }

    private void getData() {
        if (MyApplication.locationInstance().getLocation() != null) {
            if (bundle != null) {
                lat = bundle.getString("lat");
                lang = bundle.getString("lang");
            } else {
                lat = String.valueOf(MyApplication.locationInstance().getLocation().getLatitude());
                lang = String.valueOf(MyApplication.locationInstance().getLocation().getLongitude());
            }

            GetResponseFromServer.getWebService(getActivity(), TAG).getOnlineVendor(getActivity(), lat, lang, "1", new VolleyResponseListerner() {
                @Override
                public void onResponse(JSONObject response) throws JSONException {
                    list.clear();
                    if (response.getString("status").equalsIgnoreCase("1")) {
                        JSONObject jsonObject = response.getJSONObject("data");
                        Log.d(TAG, jsonObject.toString());
                        for (int i = 0; i < jsonObject.getJSONArray("vendors").length(); i++) {
                            list.add(gson.fromJson(jsonObject.getJSONArray("vendors").getJSONObject(i).toString(), OnLineVendorListModel.class));
                        }
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        txtView.setVisibility(View.VISIBLE);
                        txtView.setText(response.getString("message"));
                    }
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onError(String message, String title) {

                }
            });
        } else {
            CommonMethods.showLocationAlert(getActivity());
        }

    }
}
