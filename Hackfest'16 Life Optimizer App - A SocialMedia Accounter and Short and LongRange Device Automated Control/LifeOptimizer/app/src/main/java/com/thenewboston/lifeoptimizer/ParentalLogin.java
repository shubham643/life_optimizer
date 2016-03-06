package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shubham on 3/2/2016.
 */
public class ParentalLogin extends Activity implements View.OnClickListener, View.OnLongClickListener {

    TextView t[]=new TextView[10];
    TextView tv[] = new TextView[10];
    TextView top;
    EditText setHour , setMin, setSec;
    Button savebuts;
    String sec,min,hour;
    int second,minute,hours,which;

    String names[]={"com.whatsapp","com.facebook.katani","com.instagram.android","com.android.chrome","com.bsb.hike","com.quora.android","com.supercell.clashofclans","com.snapchat.android","com.twitter.android"};
    String previous="";
    long startingTime=System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_login);
        initialize();

        SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharepf.edit();
        editor.putString("fix1","180000000");
        editor.putString("fix2","180000000");
        editor.putString("fix3","180000000");
        editor.putString("fix4","180000000");
        editor.putString("fix5","180000000");
        editor.putString("fix6","180000000");
        editor.putString("fix7","180000000");
        editor.putString("fix8","180000000");
        editor.putString("fix9","180000000");
        editor.putString("fix10","180000000");
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

        top=(TextView)findViewById(R.id.toplogin);

        setHour = (EditText)findViewById(R.id.tParentalHour);
        setMin = (EditText)findViewById(R.id.tParentalMin);
        setSec = (EditText)findViewById(R.id.tParentalSec);
        savebuts = (Button) findViewById(R.id.bParentalOk);

        tv[0].setOnLongClickListener(this);
        tv[1].setOnLongClickListener(this);
        tv[2].setOnLongClickListener(this);
        tv[3].setOnLongClickListener(this);
        tv[4].setOnLongClickListener(this);
        tv[5].setOnLongClickListener(this);
        tv[6].setOnLongClickListener(this);
        tv[7].setOnLongClickListener(this);
        tv[8].setOnLongClickListener(this);
        tv[9].setOnLongClickListener(this);
        try {
            savebuts.setOnClickListener(this);
        }
        catch (NullPointerException e)
        {
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bParentalOk) {
            setHour.setVisibility(View.INVISIBLE);
            setMin.setVisibility(View.INVISIBLE);
            setSec.setVisibility(View.INVISIBLE);
            savebuts.setVisibility(View.INVISIBLE);
            top.setVisibility(View.VISIBLE);
     //       top.setVisibility(View.VISIBLE);
            sec = setSec.getText().toString();
            min = setMin.getText().toString();
            hour = setHour.getText().toString();

            second = Integer.valueOf(sec);
            minute = Integer.valueOf(min);
            hours = Integer.valueOf(hour);



            tv[which - 1].setText("" + hours + ":" + minute + ":" + second);
            SharedPreferences sharepf = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharepf.edit();
            CounterClass obj=new CounterClass((hours*3600 + minute*60 + second)*1000,1000);
            obj.start();
        //    editor.putString("fix" + which + "", "" + (hours * 3600) + "" + (minute * 60) + "" + second + "");
        }
    }
    @Override
    public boolean onLongClick(View v) {
        setHour.setVisibility(View.VISIBLE);
        setMin.setVisibility(View.VISIBLE);
        setSec.setVisibility(View.VISIBLE);
        savebuts.setVisibility(View.VISIBLE);
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
    public class CounterClass extends CountDownTimer
    {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(millisUntilFinished==0)
            {
                final MediaPlayer ourSong;
                ourSong = MediaPlayer.create(ParentalLogin.this, R.raw.iphone_5_alarm);
                ourSong.start();
            }
        }

        @Override
        public void onFinish() {
            final MediaPlayer ourSong;
            ourSong = MediaPlayer.create(ParentalLogin.this, R.raw.iphone_5_alarm);
            ourSong.start();
        }
    }
}
