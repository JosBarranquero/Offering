package com.barranquero.offering;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.barranquero.offering.adapter.OfferAdapter;

/**
 * Activity in which we will show the offers list
 * @author José Antonio Barranquero Fernández
 */

public class OfferListActivity extends AppCompatActivity {
    ListView mLstOffers;
    FloatingActionButton mFabAdd;
    OfferAdapter mAdapter;
    boolean[] mOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerlist);

        mOptions = getIntent().getBooleanArrayExtra(MainActivity.OPTIONS);

        mLstOffers = (ListView) findViewById(R.id.lstOffer);
        mAdapter = new OfferAdapter(this, OfferAdapter.filter(Repository.getInstance(this).getList(), mOptions), mOptions);
        registerForContextMenu(mLstOffers);
        mLstOffers.setAdapter(mAdapter);

        mFabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OfferListActivity.this, OfferAddActivity.class);
                startActivityForResult(intent, RESULT_OK);
                mLstOffers.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_app, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_offer:
                mAdapter.orderList(OfferAdapter.ORDER_TYPE);
                break;
            case R.id.action_asc:
                mAdapter.orderList(OfferAdapter.ORDER_ASC);
                break;
            case R.id.action_desc:
                mAdapter.orderList(OfferAdapter.ORDER_DESC);
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.info);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

        return true;
    }
}
