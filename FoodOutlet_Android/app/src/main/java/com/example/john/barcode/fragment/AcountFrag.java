package com.example.john.barcode.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.john.barcode.R;
import com.example.john.barcode.activity.MainActivity;

/**
 * Created by john on 1/18/2016.
 */
public class AcountFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_frg,null);
        MainActivity activity = (MainActivity)getActivity();
        activity.mTitle.setText("Account");
        activity.mLeftArrow.setVisibility(View.GONE);
        activity.mRightArrow.setVisibility(View.GONE);
        activity.mSearchLayout.setVisibility(View.GONE);

        return view;
    }
}
