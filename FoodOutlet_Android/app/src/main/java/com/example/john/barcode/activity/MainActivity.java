package com.example.john.barcode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.barcode.R;
import com.example.john.barcode.fragment.AcountFrag;
import com.example.john.barcode.fragment.OrdersFrag;
import com.example.john.barcode.fragment.SettingFrag;
import com.example.john.barcode.fragment.ShoppingFrag;
import com.example.john.barcode.lib.FragmentTab;
import com.example.john.barcode.lib.TabItemImpl;

/**
 * Created by john on 1/17/2016.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    FragmentTab mFragmentTab;
    public TextView mTitle;
    public ImageView mLeftArrow;
    public ImageView mRightArrow;
    public LinearLayout mSearchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mTitle = (TextView)findViewById(R.id.title);
        mLeftArrow = (ImageView)findViewById(R.id.left_arrow);
        mRightArrow = (ImageView)findViewById(R.id.rightArrow);
        mSearchLayout = (LinearLayout)findViewById(R.id.searchLayout);
        TextView mTxtShopping = (TextView)findViewById(R.id.txtShopping);
        TextView mTxtOrders = (TextView)findViewById(R.id.txtOrders);
        TextView mTxtAccount = (TextView)findViewById(R.id.txtAccount);
        TextView mTxtSettings = (TextView)findViewById(R.id.txtSettings);

        mTxtShopping.setOnClickListener(this);
        mTxtOrders.setOnClickListener(this);
        mTxtAccount.setOnClickListener(this);
        mTxtSettings.setOnClickListener(this);

        mFragmentTab = new FragmentTab(getSupportFragmentManager());

        mFragmentTab.addTabItem(new TabItemImpl(this, "shopping", ShoppingFrag.class));
        mFragmentTab.addTabItem(new TabItemImpl(this, "orders", OrdersFrag.class));
        mFragmentTab.addTabItem(new TabItemImpl(this, "account", AcountFrag.class));
        mFragmentTab.addTabItem(new TabItemImpl(this, "setting", SettingFrag.class));

        mFragmentTab.selectTab("shopping");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtShopping:
                mFragmentTab.selectTab("shopping");
                break;
            case R.id.txtOrders:
                mFragmentTab.selectTab("orders");
                break;
            case R.id.txtAccount:
                mFragmentTab.selectTab("account");
                break;
            case R.id.txtSettings:
                mFragmentTab.selectTab("setting");
                break;
        }
    }
}
