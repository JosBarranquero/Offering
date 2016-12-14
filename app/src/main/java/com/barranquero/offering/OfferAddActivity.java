package com.barranquero.offering;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.barranquero.offering.model.Offer;

/**
 * Activity which allows to create new offers
 *
 * @author José Antonio Barranquero Fernández
 */
public class OfferAddActivity extends AppCompatActivity {
    EditText edtName, edtShop, edtDate;
    Spinner spnType, spnImportance;
    Button btnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offeradd);

        edtName = (EditText) findViewById(R.id.edtNewName);
        edtShop = (EditText) findViewById(R.id.edtNewShop);
        edtDate = (EditText) findViewById(R.id.edtNewDate);
        spnType = (Spinner) findViewById(R.id.spnNewType);
        spnImportance = (Spinner) findViewById(R.id.spnNewImportance);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().isEmpty() ||
                        edtShop.getText().toString().isEmpty() ||
                        edtDate.getText().toString().isEmpty()) {
                    Toast.makeText(OfferAddActivity.this, R.string.must_input, Toast.LENGTH_SHORT).show();
                } else {
                    Offer newOffer = new Offer(edtName.getText().toString(),
                            edtShop.getText().toString(),
                            edtDate.getText().toString(),
                            spnType.getSelectedItemPosition(),
                            spnImportance.getSelectedItemPosition());
                    if (Repository.getInstance(OfferAddActivity.this).getList().contains(newOffer)) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(OfferAddActivity.this);
                        builder.setTitle(R.string.app_name);
                        builder.setMessage(R.string.error);
                        builder.setCancelable(true);
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                    } else {
                        Repository.getInstance(OfferAddActivity.this).addOffer(newOffer);
                        finish();
                    }
                }
            }
        });
    }
}
