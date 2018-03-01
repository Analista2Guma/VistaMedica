package com.guma.desarrollo.gvm.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.guma.desarrollo.gvm.DATABASE.SQLiteHelper;
import com.guma.desarrollo.gvm.R;
import com.guma.desarrollo.gvm.services.ManagerURI;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            new SQLiteHelper(ManagerURI.getDirDb(),this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            }
        });

    }
}
