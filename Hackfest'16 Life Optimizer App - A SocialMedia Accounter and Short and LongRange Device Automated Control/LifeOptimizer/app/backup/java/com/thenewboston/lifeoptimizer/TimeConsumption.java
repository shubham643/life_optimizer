package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shubham on 3/2/2016.
 */
public class TimeConsumption extends Activity{

    TextView t[]=new TextView[10];
    TextView tv[] = new TextView[10];

    String names[]={"com.whatsapp","com.facebook.katani","com.instagram.android","com.android.chrome","com.bsb.hike","com.quora.android","com.supercell.clashofclans","com.snapchat.android","com.twitter.android"};
    String previous="";
    long startingTime=System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_consumption);
        initialize();
        CounterClass obj=new CounterClass(6000000,1000);
        obj.start();
    /*    ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                t[0].setText(appProcess.processName);
            }
        }
    */
    /*    final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo( this.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        t[0].setText(applicationName);
    */
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
        ActivityManager activityManager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = getApplicationContext().getPackageManager();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        ActivityManager.RunningAppProcessInfo appProcess = appProcesses.get(0);
        if(appProcess.processName.equals(previous))
            return;
        long endingTime = millisUntilFinished;
        long ans = (startingTime-endingTime) / 1000;
        startingTime = endingTime;
        previous=appProcess.processName;
        for (int i = 0; i < names.length; i++) {
            if (appProcess.processName.equals(names[i])) {
    /*            CharSequence c = null;
                try {
                    c = pm.getApplicationLabel(pm.getApplicationInfo(appProcess.processName, PackageManager.GET_META_DATA));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
    */
                String pastTime = t[i].getText().toString();
                long ttt = Long.valueOf(pastTime);
                t[i].setText(Long.toString(ans + ttt));
                String showpt = t[i].getText().toString();
                long tsm = Long.valueOf(showpt);

                tv[i].setText(Long.toString(tsm/3600) + ":" + Long.toString((tsm%3600)/60) + ":" + Long.toString(tsm%60));
            }
        }
    }

    @Override
    public void onFinish() {

    }
}

/*    @Override
    protected void onPause() {
        super.onPause();
    //    while(true) {
            ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
            PackageManager pm = this.getPackageManager();
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            ActivityManager.RunningAppProcessInfo appProcess = appProcesses.get(0);
            long endingTime = System.currentTimeMillis();
            long ans = (endingTime - startingTime) / 1000;
            startingTime = endingTime;
            for (int i = 0; i < names.length; i++) {
                if (appProcess.processName.equals(names[i])) {
                    CharSequence c = null;
                    try {
                        c = pm.getApplicationLabel(pm.getApplicationInfo(appProcess.processName, PackageManager.GET_META_DATA));
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    String pastTime = t[i].getText().toString();
                    long ttt = Long.valueOf(pastTime);
                    t[i].setText(Long.toString(ans + ttt));
                }
            }
    //    }
    }
    */
}
