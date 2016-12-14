package com.barranquero.offering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Activity in which we will choose the offers we want to see
 * @author José Antonio Barranquero Fernández
 */
public class MainActivity extends AppCompatActivity {
    CheckBox mCbxHome, mCbxElectro, mCbxSport, mCbxImportance;
    Button mBtnOk;
    public static final String OPTIONS = "myOptions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnOk = (Button) findViewById(R.id.btnOk);
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCbxHome.isChecked() || mCbxSport.isChecked() || mCbxElectro.isChecked()) {
                    Intent intent = new Intent(MainActivity.this, OfferListActivity.class);
                    intent.putExtra(OPTIONS, new boolean[]{mCbxHome.isChecked(), mCbxElectro.isChecked(), mCbxSport.isChecked(), mCbxImportance.isChecked()});
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.must_select, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCbxHome = (CheckBox) findViewById(R.id.cbxHome);
        mCbxElectro = (CheckBox) findViewById(R.id.cbxElectro);
        mCbxSport = (CheckBox) findViewById(R.id.cbxSport);
        mCbxImportance = (CheckBox) findViewById(R.id.cbxImportance);
    }
}
