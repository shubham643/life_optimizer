package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Shubham on 3/2/2016.
 */
public class Farmer extends Activity implements View.OnClickListener,View.OnLongClickListener {

    TextView name1,name2,name3,name4,name5,timeset1,timeset2,timeset3,timeset4,timeset5,startTop,timeConsumption;
    CheckBox setOnLockScreen1,setOnLockScreen2,setOnLockScreen3,setOnLockScreen4,setOnLockScreen5;
    CheckBox notificationPanel1,notificationPanel2,notificationPanel3,notificationPanel4,notificationPanel5,alertTone1,alertTone2,alertTone3,alertTone4,alertTone5;
    EditText etname1,etname2,etname3,etname4,etname5,setAlarm11,setAlarm12,setAlarm13,setAlarm21,setAlarm22,setAlarm23
            ,setAlarm31,setAlarm32,setAlarm33,setAlarm41,setAlarm42,setAlarm43,setAlarm51,setAlarm52,setAlarm53;
    Button savebut,savebut1;
    DigitalClock clock;

    String setTime1,setTime2,setTime3,setTime4,setTime5;
    int sec1 = 0,min1 = 0,hour1 = 0,sec2 = 0,min2 = 0,hour2 = 0,sec3 = 0,min3 = 0,hour3 = 0,
            sec4 = 0,min4 = 0,hour4 = 0,sec5 = 0,min5 = 0,hour5 = 0,tsec=0,tmin=0,thour=0;
    int time1=0,time2=0,time3=0,time4=0,time5=0,a=0,b=0,d=0,rang = 0 ;
    int a1=0,a2=0,a3=0,a4=0,a5=0;
    long start,stop,netmil,netsec,totalmils =0,test1,test2;
    private Timer myTimer;
    Calendar c;
    NotificationCompat.Builder notify;
    int uniqueid = 65161;
    long tota1,tota2,tota3,tota4,tota5;

    static int k1,k2,k3,k4,k5;
    static long st1,st2,st3,st4,st5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        initializeVars();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        c = Calendar.getInstance();
        notify = new NotificationCompat.Builder(this);
        notify.setAutoCancel(true);

        Bundle bn1=getIntent().getExtras();
        if(bn1!=null)
        {
            long cur=System.currentTimeMillis();
            cur/=1000;
            printothers(k1,k2,k3,k4,k5,st1,st2,st3,st4,st5,cur);
            if(bn1.getInt("key")==1) {
                if (k1 == 0) {
                    k1 = 1;
                    st1 = cur;
                }
            }
            else if(bn1.getInt("key")==2) {
                if (k2 == 0) {
                    k2 = 1;
                    st2 = cur;
                }
            }
            if(bn1.getInt("key")==3) {
                if (k3 == 0) {
                    k3 = 1;
                    st3 = cur;
                }
            }
            if(bn1.getInt("key")==4) {
                if (k4 == 0) {
                    k4 = 1;
                    st4 = cur;
                }
            }
            if(bn1.getInt("key")==5) {
                if (k5 == 0) {
                    k5 = 1;
                    st5 = cur;
                }
            }
        }

    }

    private void printothers(int k1,int k2,int k3,int k4,int k5,long st1,long st2,long st3,long st4,long st5,long cur){
        if(k1!=0) {
            long v=cur-st1;
            timeset1.setText("" + v/3600 + ":" + (v%3600)/60 + ":" + v%60 );
        }
        if(k2!=0) {
            long v=cur-st2;
            timeset2.setText("" + v/3600 + ":" + (v%3600)/60 + ":" + v%60 );
        }
        if(k3!=0) {
            long v=cur-st3;
            timeset3.setText("" + v/3600 + ":" + (v%3600)/60 + ":" + v%60 );
        }
        if(k4!=0) {
            long v=cur-st4;
            timeset4.setText("" + v/3600 + ":" + (v%3600)/60 + ":" + v%60 );
        }
        if(k5!=0) {
            long v=cur-st5;
            timeset5.setText("" + v/3600 + ":" + (v%3600)/60 + ":" + v%60 );
        }
    }


    private class TickCounter5 extends  CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TickCounter5(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (tota5 >= 0) {
                tota5 = tota5 - 1;
                timeset5.setText(Long.toString(tota5 / 3600) + ":" + Long.toString((tota5 % 3600) / 60) + ":" + Long.toString(tota5 % 60));
            }
            if (tota5 == 0) {
                a5 = 0;
                if (notificationPanel5.isChecked()) {
                    notify.setSmallIcon(R.mipmap.oldclock);
                    notify.setTicker(" Time Has Been Achieved : " + name5.getText().toString());
                    notify.setContentTitle(name5.getText().toString());
                    notify.setContentText("Click to set new Alarm");
                    notify.setWhen(System.currentTimeMillis());

                    Intent comeback = new Intent(Farmer.this, Farmer.class);
                    PendingIntent pcomeback = PendingIntent.getActivity(Farmer.this, 0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                    notify.setContentIntent(pcomeback);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueid, notify.build());
                }
                if (alertTone1.isChecked()) {
                    final MediaPlayer ourSong;
                    ourSong = MediaPlayer.create(Farmer.this, R.raw.iphone_5_alarm);
                    ourSong.start();
                }
            }

        }

        @Override
        public void onFinish() {

        }
    }

    private class TickCounter4 extends  CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TickCounter4(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (tota4 >= 0) {
                tota4 = tota4 - 1;
                timeset4.setText(Long.toString(tota4 / 3600) + ":" + Long.toString((tota4 % 3600) / 60) + ":" + Long.toString(tota4 % 60));
            }
            if (tota4 == 0) {
                a4 = 0;
                if (notificationPanel4.isChecked()) {
                    notify.setSmallIcon(R.mipmap.oldclock);
                    notify.setTicker(" Time Has Been Achieved : " + name4.getText().toString());
                    notify.setContentTitle(name4.getText().toString());
                    notify.setContentText("Click to set new Alarm");
                    notify.setWhen(System.currentTimeMillis());

                    Intent comeback = new Intent(Farmer.this, Farmer.class);
                    PendingIntent pcomeback = PendingIntent.getActivity(Farmer.this, 0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                    notify.setContentIntent(pcomeback);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueid, notify.build());
                }
                if (alertTone1.isChecked()) {
                    final MediaPlayer ourSong;
                    ourSong = MediaPlayer.create(Farmer.this, R.raw.iphone_5_alarm);
                    ourSong.start();
                }
            }

        }

        @Override
        public void onFinish() {

        }
    }

    private class TickCounter3 extends  CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TickCounter3(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (tota3 >= 0) {
                tota3 = tota3 - 1;
                timeset3.setText(Long.toString(tota3 / 3600) + ":" + Long.toString((tota3 % 3600) / 60) + ":" + Long.toString(tota3 % 60));
            }
            if (tota3 == 0) {
                a3 = 0;
                if (notificationPanel3.isChecked()) {
                    notify.setSmallIcon(R.mipmap.oldclock);
                    notify.setTicker(" Time Has Been Achieved : " + name3.getText().toString());
                    notify.setContentTitle(name3.getText().toString());
                    notify.setContentText("Click to set new Alarm");
                    notify.setWhen(System.currentTimeMillis());

                    Intent comeback = new Intent(Farmer.this, Farmer.class);
                    PendingIntent pcomeback = PendingIntent.getActivity(Farmer.this, 0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                    notify.setContentIntent(pcomeback);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueid, notify.build());
                }
                if (alertTone1.isChecked()) {
                    final MediaPlayer ourSong;
                    ourSong = MediaPlayer.create(Farmer.this, R.raw.iphone_5_alarm);
                    ourSong.start();
                }
            }

        }

        @Override
        public void onFinish() {

        }
    }

    private class TickCounter2 extends  CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TickCounter2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (tota2 >= 0) {
                tota2 = tota2 - 1;
                timeset2.setText(Long.toString(tota2 / 3600) + ":" + Long.toString((tota2 % 3600) / 60) + ":" + Long.toString(tota2 % 60));
            }
            if (tota2 == 0) {
                a2 = 0;
                if (notificationPanel2.isChecked()) {
                    notify.setSmallIcon(R.mipmap.oldclock);
                    notify.setTicker(" Time Has Been Achieved : " + name2.getText().toString());
                    notify.setContentTitle(name2.getText().toString());
                    notify.setContentText("Click to set new Alarm");
                    notify.setWhen(System.currentTimeMillis());

                    Intent comeback = new Intent(Farmer.this, Farmer.class);
                    PendingIntent pcomeback = PendingIntent.getActivity(Farmer.this, 0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                    notify.setContentIntent(pcomeback);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueid, notify.build());
                }
                if (alertTone1.isChecked()) {
                    final MediaPlayer ourSong;
                    ourSong = MediaPlayer.create(Farmer.this, R.raw.iphone_5_alarm);
                    ourSong.start();
                }
            }

        }

        @Override
        public void onFinish() {

        }
    }

    private class TickCounter extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TickCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (a1 == 1) {
                if (tota1 >= 0) {
                    tota1 = tota1 - 1;
                    timeset1.setText(Long.toString(tota1 / 3600) + ":" + Long.toString((tota1 % 3600) / 60) + ":" + Long.toString(tota1 % 60));
                }
                if (tota1 == 0) {
                    a1 = 0;
                    if (notificationPanel1.isChecked()) {
                        notify.setSmallIcon(R.mipmap.oldclock);
                        notify.setTicker(" Time Has Been Achieved : " + name1.getText().toString());
                        notify.setContentTitle(name1.getText().toString());
                        notify.setContentText("Click to set new Alarm");
                        notify.setWhen(System.currentTimeMillis());

                        Intent comeback = new Intent(Farmer.this, Farmer.class);
                        PendingIntent pcomeback = PendingIntent.getActivity(Farmer.this, 0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                        notify.setContentIntent(pcomeback);

                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(uniqueid, notify.build());
                    }
                    if (alertTone1.isChecked()) {
                        final MediaPlayer ourSong;
                        ourSong = MediaPlayer.create(Farmer.this, R.raw.iphone_5_alarm);
                        ourSong.start();
                    }
                }
            }
        }






        @Override
        public void onFinish() {

        }
    }

    private void initializeVars() {
        clock = (DigitalClock) findViewById(R.id.dcFarmer);

        name1=(TextView)findViewById(R.id.tvFarmerName1);
        name2=(TextView)findViewById(R.id.tvFarmerName2);
        name3=(TextView)findViewById(R.id.tvFarmerName3);
        name4=(TextView)findViewById(R.id.tvFarmerName4);
        name5=(TextView)findViewById(R.id.tvFarmerName5);

        savebut = (Button)findViewById(R.id.bFarmerSave);
        savebut1 = (Button)findViewById(R.id.bFarmerSaveTime);

        timeset1=(TextView)findViewById(R.id.tvFarmerTimeSet1);
        timeset2=(TextView)findViewById(R.id.tvFarmerTimeSet2);
        timeset3=(TextView)findViewById(R.id.tvFarmerTimeSet3);
        timeset4=(TextView)findViewById(R.id.tvFarmerTimeSet4);
        timeset5=(TextView)findViewById(R.id.tvFarmerTimeSet5);

        startTop =(TextView) findViewById(R.id.tvStartTop);
        timeConsumption = (TextView) findViewById((R.id.tvFarmerArchieve));

        setOnLockScreen1=(CheckBox)findViewById(R.id.cbFarmerSetOnLockScreen1);
        setOnLockScreen2=(CheckBox)findViewById(R.id.cbFarmerSetOnLockScreen2);
        setOnLockScreen3=(CheckBox)findViewById(R.id.cbFarmerSetOnLockScreen3);
        setOnLockScreen4=(CheckBox)findViewById(R.id.cbFarmerSetOnLockScreen4);
        setOnLockScreen5=(CheckBox)findViewById(R.id.cbFarmerSetOnLockScreen5);

        notificationPanel1=(CheckBox)findViewById(R.id.cbFarmerNotificationPanel1);
        notificationPanel2=(CheckBox)findViewById(R.id.cbFarmerNotificationPanel2);
        notificationPanel3=(CheckBox)findViewById(R.id.cbFarmerNotificationPanel3);
        notificationPanel4=(CheckBox)findViewById(R.id.cbFarmerNotificationPanel4);
        notificationPanel5=(CheckBox)findViewById(R.id.cbFarmerNotificationPanel5);

        alertTone1=(CheckBox)findViewById(R.id.cbFarmerAlertTone1);
        alertTone2=(CheckBox)findViewById(R.id.cbFarmerAlertTone2);
        alertTone3=(CheckBox)findViewById(R.id.cbFarmerAlertTone3);
        alertTone4=(CheckBox)findViewById(R.id.cbFarmerAlertTone4);
        alertTone5=(CheckBox)findViewById(R.id.cbFarmerAlertTone5);

        setAlarm11=(EditText)findViewById(R.id.etFarmerSetAlarm11);
        setAlarm12=(EditText)findViewById(R.id.etFarmerSetAlarm12);
        setAlarm13=(EditText)findViewById(R.id.etFarmerSetAlarm13);
        setAlarm21=(EditText)findViewById(R.id.etFarmerSetAlarm21);
        setAlarm22=(EditText)findViewById(R.id.etFarmerSetAlarm22);
        setAlarm23=(EditText)findViewById(R.id.etFarmerSetAlarm23);
        setAlarm31=(EditText)findViewById(R.id.etFarmerSetAlarm31);
        setAlarm32=(EditText)findViewById(R.id.etFarmerSetAlarm32);
        setAlarm33=(EditText)findViewById(R.id.etFarmerSetAlarm33);
        setAlarm41=(EditText)findViewById(R.id.etFarmerSetAlarm41);
        setAlarm42=(EditText)findViewById(R.id.etFarmerSetAlarm42);
        setAlarm43=(EditText)findViewById(R.id.etFarmerSetAlarm43);
        setAlarm51=(EditText)findViewById(R.id.etFarmerSetAlarm51);
        setAlarm52=(EditText)findViewById(R.id.etFarmerSetAlarm52);
        setAlarm53=(EditText)findViewById(R.id.etFarmerSetAlarm53);

        etname1 = (EditText) findViewById((R.id.etFarmerName1));
        etname2 = (EditText) findViewById((R.id.etFarmerName2));
        etname3 = (EditText) findViewById((R.id.etFarmerName3));
        etname4 = (EditText) findViewById((R.id.etFarmerName4));
        etname5 = (EditText) findViewById((R.id.etFarmerName5));

        name1.setOnLongClickListener(this);
        name2.setOnLongClickListener(this);
        name3.setOnLongClickListener(this);
        name4.setOnLongClickListener(this);
        name5.setOnLongClickListener(this);
        etname1.setOnLongClickListener(this);
        etname2.setOnLongClickListener(this);
        etname3.setOnLongClickListener(this);
        etname4.setOnLongClickListener(this);
        etname5.setOnLongClickListener(this);


        timeset1.setOnClickListener(this);
        timeset2.setOnClickListener(this);
        timeset3.setOnClickListener(this);
        timeset4.setOnClickListener(this);
        timeset5.setOnClickListener(this);

        setAlarm11.setOnClickListener(this);
        setAlarm12.setOnClickListener(this);
        setAlarm13.setOnClickListener(this);
        setAlarm21.setOnClickListener(this);
        setAlarm22.setOnClickListener(this);
        setAlarm23.setOnClickListener(this);
        setAlarm31.setOnClickListener(this);
        setAlarm32.setOnClickListener(this);
        setAlarm33.setOnClickListener(this);
        setAlarm41.setOnClickListener(this);
        setAlarm42.setOnClickListener(this);
        setAlarm43.setOnClickListener(this);
        setAlarm51.setOnClickListener(this);
        setAlarm52.setOnClickListener(this);
        setAlarm53.setOnClickListener(this);


        savebut.setOnClickListener(this);
        savebut1.setOnClickListener(this);

    }

    @Override

    public void onClick(View v) {
    String sec = null, min = null, hour = null;

        c = Calendar.getInstance();
        switch (v.getId()){

            case R.id.bFarmerSave:

                switch(a){
                    case 1 :
                        name1.setVisibility(View.VISIBLE);
                        etname1.setVisibility(View.INVISIBLE);
                        String farm1 = etname1.getText().toString();
                        name1.setText(farm1);
                        a=0 ;
                        break;
                    case 2 :
                        name2.setVisibility(View.VISIBLE);
                        etname2.setVisibility(View.INVISIBLE);
                        String farm2 = etname2.getText().toString();
                        name2.setText(farm2);
                        a=0 ;
                        break;
                    case 3 :
                        name3.setVisibility(View.VISIBLE);
                        etname3.setVisibility(View.INVISIBLE);
                        String farm3 = etname3.getText().toString();
                        name3.setText(farm3);
                        a=0 ;
                        break;
                    case 4 :
                        name4.setVisibility(View.VISIBLE);
                        etname4.setVisibility(View.INVISIBLE);
                        String farm4 = etname4.getText().toString();
                        name4.setText(farm4);
                        a=0 ;
                        break;
                    case 5 :
                        name5.setVisibility(View.VISIBLE);
                        etname5.setVisibility(View.INVISIBLE);
                        String farm5 = etname5.getText().toString();
                        name5.setText(farm5);
                        a=0 ;
                        break;
                }

                savebut.setVisibility(View.INVISIBLE);
                startTop.setVisibility(View.VISIBLE);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etname1.getWindowToken(),0);
                break;

            case R.id.etFarmerSetAlarm11:
            case R.id.etFarmerSetAlarm12:
            case R.id.etFarmerSetAlarm13:
                savebut1.setVisibility(View.VISIBLE);
                startTop.setVisibility(View.INVISIBLE);
                b=1;
                break;
            case R.id.etFarmerSetAlarm21:
            case R.id.etFarmerSetAlarm22:
            case R.id.etFarmerSetAlarm23:
                savebut1.setVisibility(View.VISIBLE);
                startTop.setVisibility(View.INVISIBLE);
                b=2;
                break;
            case R.id.etFarmerSetAlarm31:
            case R.id.etFarmerSetAlarm32:
            case R.id.etFarmerSetAlarm33:
                savebut1.setVisibility(View.VISIBLE);
                startTop.setVisibility(View.INVISIBLE);
                b=3;
                break;
            case R.id.etFarmerSetAlarm41:
            case R.id.etFarmerSetAlarm42:
            case R.id.etFarmerSetAlarm43:
                savebut1.setVisibility(View.VISIBLE);
                startTop.setVisibility(View.INVISIBLE);
                b=4;
                break;
            case R.id.etFarmerSetAlarm51:
            case R.id.etFarmerSetAlarm52:
            case R.id.etFarmerSetAlarm53:
                savebut1.setVisibility(View.VISIBLE);
                startTop.setVisibility(View.INVISIBLE);
                b=5;
                break;

            case R.id.bFarmerSaveTime:
                int cursec = c.get(Calendar.SECOND);
                int curmin = c.get(Calendar.MINUTE);
                int curhour = c.get(Calendar.HOUR_OF_DAY);
                switch(b){
                    case 1 :
                         sec = setAlarm13.getText().toString();
                         min = setAlarm12.getText().toString();
                         hour = setAlarm11.getText().toString();

                        b=0;
                        a1=1;
                        sec1 = Integer.valueOf(sec);
                        min1 = Integer.valueOf(min);
                        hour1= Integer.valueOf(hour);

                        if(sec1 > 59 || min1 > 59 || hour1 >23){
                            hour1 = hour1 % 24 + min1/60;
                            min1 = min1%60 + sec1/60;
                            sec1 = sec1 % 60;

                        }

                        tota1 = (hour1*3600 + min1*60 + sec1);
                        TickCounter obj1 = new TickCounter(tota1*1000 + 1000 , 1000);
                        obj1.start();


                        break;
                    case 2 :
                        sec = setAlarm23.getText().toString();
                        min = setAlarm22.getText().toString();
                        hour = setAlarm21.getText().toString();

                        b=0;
                        a2=1;
                        sec2 = Integer.valueOf(sec);
                        min2 = Integer.valueOf(min);
                        hour2= Integer.valueOf(hour);

                        if(sec2 > 59 || min2 > 59 || hour2 >23){
                            hour2 = hour2 % 24 + min2/60;
                            min2 = min2%60 + sec2/60;
                            sec2 = sec2 % 60;

                        }
                        tota2 = (hour2*3600 + min2*60 + sec2);
                        TickCounter2 obj2= new TickCounter2(tota2*1000 + 1000 , 1000);
                        obj2.start();

                        break;
                    case 3 :
                        sec = setAlarm33.getText().toString();
                        min = setAlarm32.getText().toString();
                        hour = setAlarm31.getText().toString();

                        b=0;
                        a3=1;
                        sec3 = Integer.valueOf(sec);
                        min3 = Integer.valueOf(min);
                        hour3= Integer.valueOf(hour);

                        if(sec3 > 59 || min3 > 59 || hour3 >23){
                            hour3 = hour3 % 24 + min3/60;
                            min3 = min3%60 + sec3/60;
                            sec3 = sec3 % 60;

                        }
                        tota3 = (hour3*3600 + min3*60 + sec3);

                        TickCounter3 obj3 = new TickCounter3(tota3*1000 + 1000 , 1000);
                        obj3.start();
                        break;
                    case 4 :
                        sec = setAlarm43.getText().toString();
                        min = setAlarm42.getText().toString();
                        hour = setAlarm41.getText().toString();

                        b=0;
                        a4=1;
                        sec4 = Integer.valueOf(sec);
                        min4 = Integer.valueOf(min);
                        hour4= Integer.valueOf(hour);

                        if(sec4 > 59 || min4 > 59 || hour4 >23){
                            hour4 = hour4 % 24 + min4/60;
                            min4 = min4%60 + sec4/60;
                            sec4 = sec4 % 60;

                        }
                        tota4 = (hour4*3600 + min4*60 + sec4);

                        TickCounter4 obj4 = new TickCounter4(tota4*1000 + 1000 , 1000);
                        obj4.start();

                        break;
                    case 5 :
                        sec = setAlarm53.getText().toString();
                        min = setAlarm52.getText().toString();
                        hour = setAlarm51.getText().toString();

                        b=0;
                        a5=1;
                        sec5 = Integer.valueOf(sec);
                        min5 = Integer.valueOf(min);
                        hour5= Integer.valueOf(hour);


                        if(sec5 > 59 || min5 > 59 || hour5 >23){
                            hour5 = hour5 % 24 + min5/60;
                            min5 = min5%60 + sec5/60;
                            sec5 = sec5 % 60;

                        }

                        tota5 = (hour5*3600 + min5*60 + sec5);

                        TickCounter5 obj5 = new TickCounter5(tota5*1000 + 1000 , 1000);
                        obj5.start();

                        break;
                }






                InputMethodManager imm1 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(etname1.getWindowToken(),0);
                startTop.setVisibility(View.VISIBLE);
                savebut1.setVisibility(View.INVISIBLE);

        }
    }




    private void playAlarm(int t) {
        final MediaPlayer ourSong;
        ourSong = MediaPlayer.create(Farmer.this, R.raw.iphone_5_alarm);
        switch (t) {
            case 1 :
            Thread timer1 = new Thread() {
                public void run() {
                    int tot = (thour * 3600 + tmin * 60 + tsec) * 1000;
                    try {
                        sleep(tot);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if(alertTone1.isChecked())
                            ourSong.start();
                        rang = 1;

                    }
                }
            };
                timer1.start();


                break;
            case 2 :
                Thread timer2 = new Thread() {
                    public void run() {
                        int tot = (thour * 3600 + tmin * 60 + tsec) * 1000;
                        try {
                            sleep(tot);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            if(alertTone2.isChecked())
                                ourSong.start();
                            timeset2.setText("Alarm Time  00:00:00");
                        }
                    }
                };

                timer2.start();
                break;
            case 3 :
                Thread timer3 = new Thread() {
                    public void run() {
                        int tot = (thour * 3600 + tmin * 60 + tsec) * 1000;
                        try {
                            sleep(tot);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            if(alertTone3.isChecked())
                                ourSong.start();
                            timeset3.setText("Alarm Time  00:00:00");
                        }
                    }
                };

                timer3.start();
                break;
            case 4 :
                Thread timer4 = new Thread() {
                    public void run() {
                        int tot = (thour * 3600 + tmin * 60 + tsec) * 1000;
                        try {
                            sleep(tot);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            if(alertTone4.isChecked())
                                ourSong.start();
                            timeset4.setText("Alarm Time  00:00:00");
                        }
                    }
                };

                timer4.start();
                break;
            case 5 :
                Thread timer5 = new Thread() {
                    public void run() {
                        int tot = (thour * 3600 + tmin * 60 + tsec) * 1000;
                        try {
                            sleep(tot);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            if(alertTone5.isChecked())
                                ourSong.start();
                            timeset5.setText("Alarm Time  00:00:00");
                        }
                    }
                };

                timer5.start();
                break;

        }
        if(rang == 1){
            timeset1.setText("Alarm Time  00:00:00");
            if(notificationPanel1.isChecked()){
                notify.setSmallIcon(R.mipmap.oldclock);
                notify.setTicker(" Time Has Been Achieved");
                notify.setContentTitle("Alarm");
                notify.setContentText("Click to set new Alarm");
                notify.setWhen(System.currentTimeMillis());

                Intent comeback = new Intent(this, Farmer.class);
                PendingIntent pcomeback = PendingIntent.getActivity(this,0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                notify.setContentIntent(pcomeback);

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueid, notify.build());
            }
        }


    }



    @Override
    public boolean onLongClick(View v) {
        savebut.setVisibility(View.VISIBLE);
        startTop.setVisibility(View.INVISIBLE);
        switch(v.getId()){

            case R.id.tvFarmerName1:
                name1.setVisibility(View.INVISIBLE);
                etname1.setVisibility(View.VISIBLE);

                a=1;
                break;
            case R.id.tvFarmerName2:
                name2.setVisibility(View.INVISIBLE);
                etname2.setVisibility(View.VISIBLE);

                a=2;
                break;
            case R.id.tvFarmerName3:
                name3.setVisibility(View.INVISIBLE);
                etname3.setVisibility(View.VISIBLE);

                a=3;
                break;
            case R.id.tvFarmerName4:
                name4.setVisibility(View.INVISIBLE);
                etname4.setVisibility(View.VISIBLE);

                a=4;
                break;
            case R.id.tvFarmerName5:
                name5.setVisibility(View.INVISIBLE);
                etname5.setVisibility(View.VISIBLE);

                a=5;
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sharepf.edit();
        if(setOnLockScreen1.isChecked())
            editor.putString("11","" + setOnLockScreen1);
        else
            editor.putString("11","0");
        if(setOnLockScreen2.isChecked())
            editor.putString("22","" + setOnLockScreen2);
        else
            editor.putString("22","0");
        if(setOnLockScreen3.isChecked())
            editor.putString("33","" + setOnLockScreen3);
        else
            editor.putString("33","0");
        if(setOnLockScreen4.isChecked())
            editor.putString("44","" + setOnLockScreen4);
        else
            editor.putString("44","0");
        if(setOnLockScreen5.isChecked())
            editor.putString("55",""+setOnLockScreen5);
        else
            editor.putString("55","0");
        editor.commit();
    }
}
