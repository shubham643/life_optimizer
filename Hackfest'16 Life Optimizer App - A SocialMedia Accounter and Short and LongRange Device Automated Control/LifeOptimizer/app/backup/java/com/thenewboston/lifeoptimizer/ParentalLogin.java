package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Shubham on 3/2/2016.
 */
public class ParentalLogin extends Activity implements View.OnClickListener, View.OnLongClickListener {

    TextView t[]=new TextView[10];
    TextView tv[] = new TextView[10];
    EditText setHour , setMin, setSec;
    TextView top;
    Button savebut;
    String sec,min,hour;
    int second,minute,hours,which;

    String names[]={"com.whatsapp","com.facebook.katani","com.instagram.android","com.android.chrome","com.bsb.hike","com.quora.android","com.supercell.clashofclans","com.snapchat.android","com.twitter.android"};
    String previous="";
    long startingTime=System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_consumption);
        initialize();

        SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharepf.edit();
        editor.putString("app1","180000000");
        editor.putString("app2","180000000");
        editor.putString("app3","180000000");
        editor.putString("app4","180000000");
        editor.putString("app5","180000000");
        editor.putString("app6","180000000");
        editor.putString("app7","180000000");
        editor.putString("app8","180000000");
        editor.putString("app9","180000000");
        editor.putString("app10","180000000");
        editor.commit();

    }
    private void initialize() {
        t[0]=(TextView)findViewById(R.id.tv1);
        t[1]=(TextView)findViewById(R.id.tv2);
        t[2]=(TextView)findViewById(R.id.tv3);
        t[3]=(TextView)findViewById(R.id.tv4);
        t[4]=(TextView)findViewById(R.id.tv5);
        t[5]=(TextView)findViewById(R.id.tv6);
        t[6]=(TextView)findViewById(R.id.tv7);
        t[7]=(TextView)findViewById(R.id.tv8);
        t[8]=(TextView)findViewById(R.id.tv9);
        t[9]=(TextView)findViewById(R.id.tv10);

        tv[0]=(TextView)findViewById(R.id.tvv1);
        tv[1]=(TextView)findViewById(R.id.tvv2);
        tv[2]=(TextView)findViewById(R.id.tvv3);
        tv[3]=(TextView)findViewById(R.id.tvv4);
        tv[4]=(TextView)findViewById(R.id.tvv5);
        tv[5]=(TextView)findViewById(R.id.tvv6);
        tv[6]=(TextView)findViewById(R.id.tvv7);
        tv[7]=(TextView)findViewById(R.id.tvv8);
        tv[8]=(TextView)findViewById(R.id.tvv9);
        tv[9]=(TextView)findViewById(R.id.tvv10);

        top = (TextView) findViewById(R.id.toplogin);

        setHour = (EditText)findViewById(R.id.tParentalHour);
        setMin = (EditText)findViewById(R.id.tParentalMin);
        setSec = (EditText)findViewById(R.id.tParentalSec);
        savebut = (Button) findViewById(R.id.bParentalOK);

        t[0].setOnLongClickListener(this);
        t[1].setOnLongClickListener(this);
        t[2].setOnLongClickListener(this);
        t[3].setOnLongClickListener(this);
        t[4].setOnLongClickListener(this);
        t[5].setOnLongClickListener(this);
        t[6].setOnLongClickListener(this);
        t[7].setOnLongClickListener(this);
        t[8].setOnLongClickListener(this);
        t[9].setOnLongClickListener(this);

        savebut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            setHour.setVisibility(View.INVISIBLE);
            setMin.setVisibility(View.INVISIBLE);
            setSec.setVisibility(View.INVISIBLE);
            savebut.setVisibility(View.INVISIBLE);
            top.setVisibility(View.VISIBLE);
        sec = setSec.getText().toString();
        min = setMin.getText().toString();
        hour = setHour.getText().toString();

        second = Integer.valueOf(sec);
        minute = Integer.valueOf(min);
        hours = Integer.valueOf(hour);

        tv[which].setText("" + hours + ":" + minute + ":" + second);
        SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharepf.edit();
        editor.putString("" + which + "","" + (hours*3600) + "" + (minute*60) + "" + second + "");

    }

    @Override
    public boolean onLongClick(View v) {
        setHour.setVisibility(View.VISIBLE);
        setMin.setVisibility(View.VISIBLE);
        setSec.setVisibility(View.VISIBLE);
        savebut.setVisibility(View.VISIBLE);
        top.setVisibility(View.INVISIBLE);
        switch(v.getId()){
            case R.id.tvv1 :
                which =1;
                break;
            case R.id.tvv2 :
                which =2;
                break;
            case R.id.tvv3 :
                which =3;
                break;
            case R.id.tvv4 :
                which =4;
                break;
            case R.id.tvv5 :
                which =5;
                break;
            case R.id.tvv6 :
                which =6;
                break;
            case R.id.tvv7 :
                which =7;
                break;
            case R.id.tvv8 :
                which =8;
                break;
            case R.id.tvv9 :
                which =9;
                break;
            case R.id.tvv10 :
                which =10;
                break;

        }

        return true;
    }
}
