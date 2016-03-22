package com.example.john.barcode.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.example.john.barcode.R;

/**
 * Created by john on 1/18/2016.
 */
public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        NumberPicker picker_1 = (NumberPicker)findViewById(R.id.numberPicker1);
        NumberPicker picker_2 = (NumberPicker)findViewById(R.id.numberPicker2);
        NumberPicker picker_3 = (NumberPicker)findViewById(R.id.numberPicker3);

        picker_1.setMinValue(0);
        picker_1.setMaxValue(9);

        picker_2.setMinValue(0);
        picker_2.setMaxValue(9);

        picker_3.setMinValue(0);
        picker_3.setMaxValue(1);
        picker_3.setDisplayedValues(new String[]{"CS", "UNIT"});

        ImageView imgBack = (ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
