package com.example.john.barcode.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.barcode.R;
import com.example.john.barcode.dbmanager.ConnectionClass;
import com.example.john.barcode.utils.BackgroundTaskUtils;
import com.example.john.barcode.utils.ProgressUtils;

public class LoginActivity extends AppCompatActivity {
    EditText    m_editUserName = null;
    EditText    m_editPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        m_editUserName = (EditText)findViewById(R.id.editText);
        m_editPassword = (EditText)findViewById(R.id.edtPwd);
        TextView mTxtLogin = (TextView) findViewById(R.id.txtLogin);

        mTxtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();

            }
        });
    }

    private void onClickLogin()
    {
        final String username = m_editUserName.getText().toString();
        final String password = m_editPassword.getText().toString();

        final ProgressDialog progdialog = ProgressUtils.showProgress(this, "Loading", "Please wait");
        new BackgroundTaskUtils(new BackgroundTaskUtils.OnTaskProgress() {
            boolean m_bLoginOK = false;
            @Override
            public void onProgress() {
                m_bLoginOK = ConnectionClass.login(username, password);
            }

            @Override
            public void onFinished() {
                progdialog.hide();
                if( m_bLoginOK == false )
                {
                    Toast.makeText(LoginActivity.this, "Username and password does not match", Toast.LENGTH_LONG).show();
                    return;
                }

                gotoMainPage();
            }
        }).execute();
    }

    private void gotoMainPage()
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
