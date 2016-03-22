package com.example.john.barcode.lib;

import android.support.v4.app.FragmentTransaction;

public interface TabListener {
    public void onTabSelected(TabItem tab, FragmentTransaction ft);

    public void onTabUnselected(TabItem tab, FragmentTransaction ft);

    public void onTabReselected(TabItem tab, FragmentTransaction ft);
}