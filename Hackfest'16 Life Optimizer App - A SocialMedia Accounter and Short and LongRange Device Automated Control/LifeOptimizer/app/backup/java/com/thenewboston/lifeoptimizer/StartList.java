package com.thenewboston.lifeoptimizer;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shubham on 3/2/2016.
 */
public class StartList extends ActionBarActivity implements View.OnClickListener, View.OnLongClickListener {

    Intent i;
    TextView farmer,self,shortRange,longRange,timeConsumption,parentalControl,top;
    ImageView ifarmer,iself,ishortRange,ilongRange,itimeConsumption,iparentalControl;
    EditText farmer1,myEditText;
    Button savebut;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_list);
        initialize();
        farmer1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
    /* When focus is lost check that the text field
    * has valid values.
    */
                if (!hasFocus) {
                    farmer1.setVisibility(View.INVISIBLE);
                    farmer.setVisibility(View.VISIBLE);
                    String farm = farmer1.getText().toString();
                    farmer.setText(farm);
                }
            }
        });
    }

    private void initialize() {
        farmer = (TextView) findViewById(R.id.tvFarmer);
        farmer1 = (EditText) findViewById(R.id.etfarmer);
        self = (TextView) findViewById(R.id.tvSelf);
        shortRange = (TextView) findViewById(R.id.tvShortRange);
        longRange = (TextView) findViewById(R.id.tvLongRange);
        top = (TextView) findViewById(R.id.tvStartTop);
        timeConsumption = (TextView) findViewById(R.id.tvTimeConsumption);
       parentalControl = (TextView) findViewById(R.id.tvParentalControl);
        ifarmer = (ImageView) findViewById(R.id.ivFarmer);
        iself = (ImageView) findViewById(R.id.ivSelf);
        ishortRange = (ImageView) findViewById(R.id.ivShortRange);
        ilongRange = (ImageView) findViewById(R.id.ivLongRange);
        savebut = (Button) findViewById(R.id.bFarmerSave);
        farmer.setOnClickListener(this);
        self.setOnClickListener(this);
        shortRange.setOnClickListener(this);
        longRange.setOnClickListener(this);
        timeConsumption.setOnClickListener(this);
        parentalControl.setOnClickListener(this);
        ifarmer.setOnClickListener(this);
        iself.setOnClickListener(this);
        ishortRange.setOnClickListener(this);
        ilongRange.setOnClickListener(this);
        savebut.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvFarmer:
            case R.id.ivFarmer:
                    i=new Intent("android.intent.action.FARMER");
                    startActivity(i);
                break;
            case R.id.tvSelf:
            case R.id.ivSelf:
                    i=new Intent("android.intent.action.SELF");
                    startActivity(i);
                break;
            case R.id.tvShortRange:
            case R.id.ivShortRange:
                    i=new Intent(this, ShortRange.class);
                    startActivity(i);
                break;
            case R.id.tvLongRange:
            case R.id.ivLongRange:
                    i=new Intent("android.intent.action.LONGRANGE");
                    startActivity(i);
                break;
            case R.id.tvTimeConsumption:
                    i=new Intent("android.intent.action.TIMECONSUMPTION");
                    startActivity(i);
                break;
            case R.id.tvParentalControl:
                    i=new Intent("android.intent.action.PARENTALCONTROL");
                    startActivity(i);
                break;
            case R.id.bFarmerSave :
                farmer1.setVisibility(View.INVISIBLE);
                farmer.setVisibility(View.VISIBLE);
                String farm = farmer1.getText().toString();
                farmer.setText(farm);
                savebut.setVisibility(View.INVISIBLE);
                top.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        farmer.setVisibility(View.INVISIBLE);
        farmer1.setVisibility(View.VISIBLE);
        savebut.setVisibility(View.VISIBLE);
        top.setVisibility(View.INVISIBLE);
        return true;
    }


}
