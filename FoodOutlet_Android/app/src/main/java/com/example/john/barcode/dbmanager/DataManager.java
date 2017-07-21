package com.example.john.barcode.dbmanager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JKH on 2016-03-23.
 */
public class DataManager {
    List<JSONObject> itemList = new ArrayList<JSONObject>();
    public static void getItemList()
    {
        List<JSONObject> list = ConnectionClass.fetchItems();
    }
}
