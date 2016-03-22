package com.example.john.barcode.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.barcode.R;
import com.example.john.barcode.dbmanager.ConnectionClass;
import com.example.john.barcode.fragment.AcountFrag;
import com.example.john.barcode.fragment.OrdersFrag;
import com.example.john.barcode.fragment.SettingFrag;
import com.example.john.barcode.fragment.ShoppingFrag;
import com.example.john.barcode.lib.FragmentTab;
import com.example.john.barcode.lib.TabItemImpl;
import com.example.john.barcode.utils.BackgroundTaskUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 1/17/2016.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    FragmentTab mFragmentTab;
    public TextView mTitle;
    public ImageView mLeftArrow;
    public ImageView mRightArrow;
    public LinearLayout mSearchLayout;

    List<JSONObject> m_categories = new ArrayList<JSONObject>();
    int             m_nSelectedCategory = 0;
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

        initData();
        initEvents();
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

    private void initData()
    {
        getCategories();
    }



    private void getCategories()
    {
        final ProgressDialog progress = ProgressDialog.show(this, "Loading", "Please wait");
        new BackgroundTaskUtils(new BackgroundTaskUtils.OnTaskProgress() {
            List<JSONObject> list = new ArrayList<JSONObject>();
            @Override
            public void onProgress() {
                list = ConnectionClass.fetchCategories();
            }

            @Override
            public void onFinished() {
                progress.hide();
                showCategories(list);
            }
        }).execute();
    }

    private void showCategories(List<JSONObject> list)
    {
        m_nSelectedCategory = 0;
        m_categories = list;

        showItems(m_nSelectedCategory);
    }

    private void showItems(int category)
    {
        if( m_categories.size() < 1 )
            return;

        if( category < 0 )
            category = 0;
        if( category >= m_categories.size()  )
            category = m_categories.size() - 1;
        JSONObject categoryInfo = m_categories.get(category);
        if( categoryInfo == null )
            return;

        int id = categoryInfo.optInt("ID", 0);
        mTitle.setText(categoryInfo.optString("CategoryName"));

    }

    private  void initEvents()
    {
        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( m_categories.size() < 1 )
                    return;

                m_nSelectedCategory = (m_nSelectedCategory - 1) % m_categories.size();
                showItems(m_nSelectedCategory);
            }
        });

        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( m_categories.size() < 1 )
                    return;

                m_nSelectedCategory = (m_nSelectedCategory + 1) % m_categories.size();
                showItems(m_nSelectedCategory);
            }
        });
    }
}
