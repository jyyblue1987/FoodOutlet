package com.example.john.barcode.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.john.barcode.R;
import com.example.john.barcode.activity.MainActivity;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.zip.Inflater;

/**
 * Created by john on 1/18/2016.
 */
public class SettingFrag extends Fragment {

    private Switch mSwitch_lang;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_frag, null);
        MainActivity activity = (MainActivity)getActivity();
        activity.mTitle.setText("Setting");
        activity.mLeftArrow.setVisibility(View.GONE);
        activity.mRightArrow.setVisibility(View.GONE);
        activity.mSearchLayout.setVisibility(View.GONE);

        mSwitch_lang = (Switch)view.findViewById(R.id.switch1);
        mSwitch_lang.setTextOn("English");
        mSwitch_lang.setTextOff("中文");

        return view;
    }
}
