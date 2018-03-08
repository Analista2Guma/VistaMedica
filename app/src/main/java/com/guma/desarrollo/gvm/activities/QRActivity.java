package com.guma.desarrollo.gvm.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.zxing.Result;
import com.guma.desarrollo.gvm.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        setTitle("GVM Reader QR");
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)    {
        int id = item.getItemId();
        if (id == 16908332){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void handleResult(Result rawResult) {

        if (rawResult.getText()==""){
           // getIntent().putExtra("myResulte",rawResult.getText());
           // setResult(RESULT_OK,getIntent());
        }else{
            startActivity(new Intent(QRActivity.this,ResultQrActivity.class));
            finish();
        }
    }
}
