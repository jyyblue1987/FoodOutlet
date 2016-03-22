package com.example.john.barcode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.john.barcode.R;
import com.example.john.barcode.dbmanager.ConnectionClass;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        TextView mTxtLogin = (TextView)findViewById(R.id.txtLogin);
        mTxtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionClass.login("Admin", "");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                finish();
            }
        });
    }
}
